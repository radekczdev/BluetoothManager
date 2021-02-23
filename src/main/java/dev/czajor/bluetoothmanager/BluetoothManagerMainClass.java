package dev.czajor.bluetoothmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BluetoothManagerMainClass {
//    static boolean running = true;

    public static void main(String[] args) {
        SpringApplication.run(BluetoothManagerMainClass.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context) throws InterruptedException, IOException {
//
//        final Lock lock = new ReentrantLock();
//        final Condition cv = lock.newCondition();
//
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            running = false;
//            lock.lock();
//            try {
//                cv.signalAll();
//            } finally {
//                lock.unlock();
//            }
//
//        }));
//
//
//        while (running) {
//            try (TinyBInitializer initializer = new TinyBInitializer()){
//
//                BluetoothManager manager = initializer.getManager();
//                initializer.startDiscovery();
//                List<Device> devices = AvailableDevicesService.getAll(manager);
//                IntStream.range(0, devices.size())
//                        .forEach(index -> {
//                            System.out.println(index + " : ");
//                            AvailableDevicesService.printDeviceInformation(devices.get(index).getDevice());
//                        });
//
//                Console console = System.console();
//                int choosenDevice = Integer.parseInt(console.readLine("Choose Device: "));
//                System.out.println("Choosen number: " + choosenDevice);
//                Device device = devices.get(choosenDevice);
//                if (ConnectionService.isConnected(device)) {
//                    ConnectionService.disconnect(device);
//                } else {
//                    ConnectionService.connect(device);
//                }
//            } catch (BluetoothException e) {
//                System.out.println("Connection thrown error: " + e);
//            }
//
//            lock.lock();
//            try {
//                cv.await(1, TimeUnit.SECONDS);
//            } finally {
//                lock.unlock();
//            }
//        }
//    }
//


}
