package dev.czajor.bluetoothManager;

import dev.czajor.bluetoothManager.object.Device;
import dev.czajor.bluetoothManager.service.AvailableDevicesService;
import dev.czajor.bluetoothManager.service.ConnectionService;
import dev.czajor.bluetoothManager.service.TinyBInitializer;
import tinyb.BluetoothDevice;
import tinyb.BluetoothException;
import tinyb.BluetoothManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
            try (TinyBInitializer initializer = new TinyBInitializer()) {
                BluetoothManager manager = initializer.getManager();
                initializer.startDiscovery();
                List<Device> devices = AvailableDevicesService.getAll(manager);
                Device device = devices.stream().filter(dev -> dev.getDevice().getName().contains("Samsung 7")).findFirst().get();
                AvailableDevicesService.printDeviceInformation(device.getDevice());
//                if (ConnectionService.isConnected(device)) {
//                    ConnectionService.disconnect(device);
//                } else {
//                    ConnectionService.connect(device);
//                }
            } catch (BluetoothException e) {
                System.out.println("Connection thrown error: 0" + e);
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
