package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableDevicesService {
    private final TinyBInitializer tinyBInitializer;

    public List<Device> getAll() {
        List<Device> devices;
        try {
            tinyBInitializer.startDiscovery();
            devices = Optional.ofNullable(tinyBInitializer.getDevices()).orElse(Collections.emptyList()).stream()
                    .filter(Objects::nonNull)
                    .map(dev -> new Device(dev,
                            dev.getName(),
                            dev.getAddress(),
                            dev.getBluetoothClass(),
                            dev.getBluetoothType().name()))
                    .collect(Collectors.toList());
        } finally {
            tinyBInitializer.stopDiscovery();
        }
        return devices;
    }

    public void printDeviceInformation(Device device) {
        System.out.println("Address = " + device.getAddress());
        System.out.println(" Name = " + device.getName());
        System.out.println(" Class = " + device.getClass());
        System.out.println(" Type = " + device.getType());
        System.out.println(" Connected = " + device.getConnected());
        System.out.println();
    }
}
