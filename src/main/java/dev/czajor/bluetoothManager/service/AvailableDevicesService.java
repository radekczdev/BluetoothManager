package dev.czajor.bluetoothManager.service;

import dev.czajor.bluetoothManager.object.Device;
import tinyb.BluetoothDevice;
import tinyb.BluetoothManager;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConnectedDevicesService {
    public static List<Device> getAll(BluetoothManager manager) {
//        BluetoothManager manager = BluetoothManager.getBluetoothManager();
//        BluetoothDevice sensor = null;
//        for (int i = 0; (i < 15) && running; ++i) {
        return Optional.ofNullable(manager.getDevices()).orElse(Collections.emptyList()).stream()
                .map(dev -> Device.builder()
                        .device(dev).build())
                .collect(Collectors.toList());
//            if (list == null)
//                return null;
//
//            Optional<BluetoothDevice> device = list.stream().filter(a -> a.getName().contains("Samsung 7 Series")).findFirst();
    }
}
