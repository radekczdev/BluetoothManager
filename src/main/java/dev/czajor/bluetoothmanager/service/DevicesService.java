package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.exception.CouldNotRemoveObjectsException;
import dev.czajor.bluetoothmanager.exception.DeviceNotFoundException;
import dev.czajor.bluetoothmanager.repository.DevicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DevicesService {
    private final DevicesRepository devicesRepository;
    private final SystemBluetoothService systemBluetoothService;

    public void saveDevicesToRepository(List<Device> devices) {
        devices.forEach(devicesRepository::save);
    }

    public List<Device> refreshDatabase() throws CouldNotRemoveObjectsException {
        devicesRepository.deleteAll();
        systemBluetoothService.getDiscoveredDevices()
                .forEach(devicesRepository::save);
        return devicesRepository.findAll().orElse(Collections.emptyList());
    }

    public List<Device> getAll() {
        return devicesRepository.findAll().orElse(Collections.emptyList());
    }

    public Device getByAddress(String address) throws DeviceNotFoundException {
        return devicesRepository.findById(address).orElseThrow(
                () -> new DeviceNotFoundException(String.format("Device with address %s doesn't exist in database", address)));
    }
}
