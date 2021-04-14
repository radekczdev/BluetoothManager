package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.exception.DeviceNotFoundException;
import dev.czajor.bluetoothmanager.repository.DevicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class DevicesService {
    private final DevicesRepository devicesRepository;
    private final SystemBluetoothService systemBluetoothService;
    private final ReentrantLock lock = new ReentrantLock();

    public void saveDevicesToRepository(List<Device> devices) {
        devices.forEach(devicesRepository::save);
    }

    @PostConstruct
    public List<Device> refreshDatabase() {
        lock.lock();
        try {
            devicesRepository.deleteAll();
            saveDevicesToRepository(systemBluetoothService.getDiscoveredDevices());
        } finally {
            lock.unlock();
        }
        return devicesRepository.findAll();
    }

    public List<Device> getAll() {
        return devicesRepository.findAll();
    }

    public List<Device> getPaired() {
        return systemBluetoothService.getPairedDevices();
    }

    public Device getByAddress(String address) throws DeviceNotFoundException {
        return devicesRepository.findById(address).orElseThrow(
                () -> new DeviceNotFoundException(String.format("Device with address %s doesn't exist in database", address)));
    }
}
