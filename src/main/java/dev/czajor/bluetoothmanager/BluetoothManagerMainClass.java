package dev.czajor.bluetoothmanager;

import dev.czajor.bluetoothmanager.object.Device;
import dev.czajor.bluetoothmanager.service.AvailableDevicesService;
import dev.czajor.bluetoothmanager.service.ConnectionService;
import dev.czajor.bluetoothmanager.service.TinyBInitializer;
import tinyb.BluetoothException;
import tinyb.BluetoothManager;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class BluetoothManagerMainClass {
    static boolean running = true;

    public static void main(String[] args) throws InterruptedException, IOException {

        final Lock lock = new ReentrantLock();
        final Condition cv = lock.newCondition();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running = false;
            lock.lock();
            try {
                cv.signalAll();
            } finally {
                lock.unlock();
            }

        }));


        while (running) {
            try {
                TinyBInitializer initializer = new TinyBInitializer();
                BluetoothManager manager = initializer.getManager();
                initializer.startDiscovery();
                List<Device> devices = AvailableDevicesService.getAll(manager);
                initializer.stopDiscovery();
                IntStream.range(0, devices.size())
                        .forEach(index -> {
                            System.out.println(index + " : ");
                            AvailableDevicesService.printDeviceInformation(devices.get(index).getDevice());
                        });

                Console console = System.console();
                int choosenDevice = Integer.parseInt(console.readLine("Choose Device: "));
                System.out.println("Choosen number: " + choosenDevice);
                Device device = devices.get(choosenDevice);
                if (ConnectionService.isConnected(device)) {
                    ConnectionService.disconnect(device);
                } else {
                    ConnectionService.connect(device);
                }
            } catch (BluetoothException e) {
                System.out.println("Connection thrown error: " + e);
            }

            lock.lock();
            try {
                cv.await(1, TimeUnit.SECONDS);
            } finally {
                lock.unlock();
            }
        }
    }



}
