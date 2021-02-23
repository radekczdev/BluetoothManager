package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.repository.DevicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DevicesService {
    private final SystemBluetoothService systemBluetoothService;
    private final DevicesRepository devicesRepository;

    public void saveDevicesToRepository() {
        List<Device> devices = systemBluetoothService.getDiscoveredDevices();
        devices.forEach(devicesRepository::save);
    }

    public List<Device> getAll() {
        return devicesRepository.findAll().orElse(Collections.emptyList());
    }

}
