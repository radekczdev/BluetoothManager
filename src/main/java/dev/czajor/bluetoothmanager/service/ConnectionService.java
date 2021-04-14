package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.exception.CouldNotRemoveObjectsException;
import dev.czajor.bluetoothmanager.exception.DeviceNotConnectedException;
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
    public static final String DEVICE_NOT_CONNECTED = "Device with address: %s is not connected";
    private final BluetoothManager bluetoothManager;
    private final DevicesService devicesService;
    private final ReentrantLock lock = new ReentrantLock();

    public Device connect(final String address) throws DeviceNotFoundException, CouldNotRemoveObjectsException {
        lock.lock();
        try {
            if (!isConnected(address)) {
                getDevice(address).
                        orElseThrow(() -> new DeviceNotFoundException(DEVICE_DOES_NOT_EXIST)).
                        connect();
            }
            devicesService.refreshDatabase();
        } finally {
            lock.unlock();
        }
        return devicesService.getByAddress(address);
    }

    public Device disconnect(final String address) throws DeviceNotFoundException, CouldNotRemoveObjectsException, DeviceNotConnectedException {
        lock.lock();
        try {
            if (isConnected(address)) {
                getDevice(address).
                        orElseThrow(() -> new DeviceNotFoundException(DEVICE_DOES_NOT_EXIST)).
                        disconnect();
            } else {
                throw new DeviceNotConnectedException(String.format(DEVICE_NOT_CONNECTED, address));
            }
            devicesService.refreshDatabase();
        } finally {
            lock.unlock();
        }
        return devicesService.getByAddress(address);
    }

    public boolean isConnected(final String address) throws DeviceNotFoundException {
        lock.lock();
        boolean isConnected;
        try {
            devicesService.refreshDatabase();
            isConnected = getDevice(address).
                    orElseThrow(() -> new DeviceNotFoundException(DEVICE_DOES_NOT_EXIST)).
                    getConnected();
        } finally {
            lock.unlock();
        }
        return isConnected;
    }

    public Optional<BluetoothDevice> getDevice(final String address) {
        return bluetoothManager.getDevices().stream()
                .filter(dev -> dev.getAddress().equals(address))
                .findFirst();
    }
}
