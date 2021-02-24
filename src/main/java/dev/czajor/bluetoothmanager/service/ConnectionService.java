package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.exception.CouldNotRemoveObjectsException;
import dev.czajor.bluetoothmanager.exception.DeviceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinyb.BluetoothDevice;
import tinyb.BluetoothManager;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Service
public class ConnectionService {
    public static final String DEVICE_DOES_NOT_EXIST = "Cannot connect - device doesn't exist";
    private final BluetoothManager bluetoothManager;
    private final DevicesService devicesService;
    private final ReentrantLock lock = new ReentrantLock();

    public Device connect(final String address) throws DeviceNotFoundException, CouldNotRemoveObjectsException {
        lock.lock();
        try {
            getDevice(address).
                    orElseThrow(() -> new DeviceNotFoundException(DEVICE_DOES_NOT_EXIST)).
                    connect();
            devicesService.refreshDatabase();
        } finally {
            lock.unlock();
        }
        return devicesService.getByAddress(address);
    }

    public Device disconnect(final Device device) throws DeviceNotFoundException, CouldNotRemoveObjectsException {
        lock.lock();
        String address = device.getAddress();
        try {
            getDevice(address).
                    orElseThrow(() -> new DeviceNotFoundException(DEVICE_DOES_NOT_EXIST)).
                    disconnect();
            devicesService.refreshDatabase();
        } finally {
            lock.unlock();
        }
        return devicesService.getByAddress(address);
    }

    public Device isConnected(final Device device) throws DeviceNotFoundException, CouldNotRemoveObjectsException {
        lock.lock();
        String address = device.getAddress();
        try {
            getDevice(address).
                    orElseThrow(() -> new DeviceNotFoundException(DEVICE_DOES_NOT_EXIST)).
                    getConnected();
            devicesService.refreshDatabase();
        } finally {
            lock.unlock();
        }
        return devicesService.getByAddress(address);
    }

    public Optional<BluetoothDevice> getDevice(String address) {
        return bluetoothManager.getDevices().stream()
                .filter(dev -> dev.getAddress().equals(address))
                .findFirst();
    }
}
