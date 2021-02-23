package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinyb.BluetoothException;
import tinyb.BluetoothManager;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Service
public class ConnectionService {
    @Autowired
    private final BluetoothManager bluetoothManager;

    public boolean connect(final Device device) throws BluetoothException {
        return bluetoothManager.getDevices().stream()
                .filter(dev -> dev.getAddress().equals(device.getAddress()))
                .findFirst().get().connect();
    }

    public boolean disconnect(final Device device) throws BluetoothException {
        return bluetoothManager.getDevices().stream()
                .filter(dev -> dev.getAddress().equals(device.getAddress()))
                .findFirst().get().disconnect();
    }

    public boolean isConnected(final Device device) throws BluetoothException {
        return bluetoothManager.getDevices().stream()
                .filter(dev -> dev.getAddress().equals(device.getAddress()))
                .findFirst().get().getConnected();
    }
}
