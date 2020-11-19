package dev.czajor.BluetoothManager;

import tinyb.BluetoothDevice;
import tinyb.BluetoothException;
import tinyb.BluetoothManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class BluetoothManagerMainClass {
    static boolean running = true;

    public static void main(String[] args) throws InterruptedException, IOException {

        if (args.length < 1) {
            System.err.println("Run with <device_address> argument");
            System.exit(-1);
        }

        /*
         * To start looking of the device, we first must initialize the TinyB library. The way of interacting with the
         * library is through the BluetoothManager. There can be only one BluetoothManager at one time, and the
         * reference to it is obtained through the getBluetoothManager method.
         */
        BluetoothManager manager = BluetoothManager.getBluetoothManager();

        /*
         * The manager will try to initialize a BluetoothAdapter if any adapter is present in the system. To initialize
         * discovery we can call startDiscovery, which will put the default adapter in discovery mode.
         */
        boolean discoveryStarted = manager.startDiscovery();

        System.out.println("The discovery started: " + (discoveryStarted ? "true" : "false"));
        BluetoothDevice sensor = getDevice(args[0]);

        /*
         * After we find the device we can stop looking for other devices.
         */
        try {
            manager.stopDiscovery();
        } catch (BluetoothException e) {
            System.err.println("Discovery could not be stopped.");
        }

        if (sensor == null) {
            System.err.println("No sensor found with the provided address.");
            System.exit(-1);
        }

        System.out.print("Found device: ");
        printDevice(sensor);

        if (sensor.connect())
            System.out.println("Sensor with the provided address connected");
        else {
            System.out.println("Could not connect device.");
            System.exit(-1);
        }

        final Lock lock = new ReentrantLock();
        final Condition cv = lock.newCondition();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                running = false;
                lock.lock();
                try {
                    cv.signalAll();
                } finally {
                    lock.unlock();
                }

            }
        });


//        BluetoothGattService tempService = getService(sensor, "f000aa00-0451-4000-b000-000000000000");
//
//        if (tempService == null) {
//            System.err.println("This device does not have the temperature service we are looking for.");
//            sensor.disconnect();
//            System.exit(-1);
//        }
//        System.out.println("Found service " + tempService.getUUID());
//
//        BluetoothGattCharacteristic tempValue = getCharacteristic(tempService, "f000aa01-0451-4000-b000-000000000000");
//        BluetoothGattCharacteristic tempConfig = getCharacteristic(tempService, "f000aa02-0451-4000-b000-000000000000");
//        BluetoothGattCharacteristic tempPeriod = getCharacteristic(tempService, "f000aa03-0451-4000-b000-000000000000");
//
//        if (tempValue == null || tempConfig == null || tempPeriod == null) {
//            System.err.println("Could not find the correct characteristics.");
//            sensor.disconnect();
//            System.exit(-1);
//        }
//
//        System.out.println("Found the temperature characteristics");

        /*
         * Turn on the Temperature Service by writing 1 in the configuration characteristic, as mentioned in the PDF
         * mentioned above. We could also modify the update interval, by writing in the period characteristic, but the
         * default 1s is good enough for our purposes.
         */
//        byte[] config = {0x01};
//        tempConfig.writeValue(config);

        /*
         * Each second read the value characteristic and display it in a human readable format.
         */
        while (running) {
//            byte[] tempRaw = tempValue.readValue();
//            System.out.print("Temp raw = {");
//            for (byte b : tempRaw) {
//                System.out.print(String.format("%02x,", b));
//            }
//            System.out.print("}");

            /*
             * The temperature service returns the data in an encoded format which can be found in the wiki. Convert the
             * raw temperature format to celsius and print it. Conversion for object temperature depends on ambient
             * according to wiki, but assume result is good enough for our purposes without conversion.
             */
//            int objectTempRaw = (tempRaw[0] & 0xff) | (tempRaw[1] << 8);
//            int ambientTempRaw = (tempRaw[2] & 0xff) | (tempRaw[3] << 8);
//
//            float objectTempCelsius = convertCelsius(objectTempRaw);
//            float ambientTempCelsius = convertCelsius(ambientTempRaw);
//
//            System.out.println(
//                    String.format(" Temp: Object = %fC, Ambient = %fC", objectTempCelsius, ambientTempCelsius));

            lock.lock();
            try {
                cv.await(1, TimeUnit.SECONDS);
            } finally {
                lock.unlock();
            }
        }
        sensor.disconnect();

    }

    static void printDevice(BluetoothDevice device) {
        System.out.print("Address = " + device.getAddress());
        System.out.print(" Name = " + device.getName());
        System.out.print(" Connected = " + device.getConnected());
        System.out.print(" Class = " + device.getBluetoothClass());
        System.out.print(" Type = " + device.getBluetoothType());
        System.out.print(" Type = " + device.getConnected());
        System.out.println();
    }

    static BluetoothDevice getDevice(String address) throws InterruptedException, IOException {
        BluetoothManager manager = BluetoothManager.getBluetoothManager();
        BluetoothDevice sensor = null;
        for (int i = 0; (i < 15) && running; ++i) {
            List<BluetoothDevice> list = manager.getDevices();
            if (list == null)
                return null;

            Optional<BluetoothDevice> device = list.stream().filter(a -> a.getName().contains("Samsung 7 Series")).findFirst();
//            System.in.read();
//            System.out.println("Disconnecting TV");
//            device.ifPresent(a -> a.disconnect());
//            Thread.sleep(4000);
//            System.out.println("Press enter to connect to TV");
//            System.in.read();
//            device.ifPresent(a -> a.connect());

            for (BluetoothDevice dev : list) {
                printDevice(dev);
                /*
                 * Here we check if the address matches.
                 */
            }

            if (sensor != null) {
                return sensor;
            }
            Thread.sleep(4000);
        }
        return null;
    }
}
