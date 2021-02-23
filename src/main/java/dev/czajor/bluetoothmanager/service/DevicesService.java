package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.exception.DeviceNotFoundException;
import dev.czajor.bluetoothmanager.repository.DevicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevicesService {
    private final DevicesRepository devicesRepository;
    private final ConnectionService connectionService;

    public void saveDevicesToRepository(List<Device> devices) {
        devices.forEach(devicesRepository::save);
    }

    public List<Device> getAll() {
        return devicesRepository.findAll().orElse(Collections.emptyList());
    }

    public boolean connectToDevice(String address) throws DeviceNotFoundException {
        Optional<Device> device = devicesRepository.findById(address);
        if(!device.isPresent()) {
            throw new DeviceNotFoundException(
                    String.format("Device %s doesn't exist in database!", address));
        }
        return connectionService.connect(device.get());
    }

}
