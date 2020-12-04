package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.object.Device;
import tinyb.BluetoothDevice;
import tinyb.BluetoothManager;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AvailableDevicesService {
    public static List<Device> getAll(BluetoothManager manager) {
        return Optional.ofNullable(manager.getDevices()).orElse(Collections.emptyList()).stream()
                .map(dev -> Device.builder()
                        .device(dev).build())
                .collect(Collectors.toList());
    }

    public static void printDeviceInformation(BluetoothDevice device) {
        System.out.println("Address = " + device.getAddress());
        System.out.println(" Name = " + device.getName());
        System.out.println(" Class = " + device.getBluetoothClass());
        System.out.println(" Type = " + device.getBluetoothType());
        System.out.println(" Connected = " + device.getConnected());
        System.out.println();
    }
}
