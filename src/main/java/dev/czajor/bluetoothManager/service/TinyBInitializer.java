package dev.czajor.bluetoothManager.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tinyb.BluetoothException;
import tinyb.BluetoothManager;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TinyBInitializer implements AutoCloseable {
    private BluetoothManager manager;

    public TinyBInitializer() {
        this.manager = BluetoothManager.getBluetoothManager();
    }

    public boolean startDiscovery() throws BluetoothException {
        return manager.startDiscovery();
    }

    @Override
    public void close() throws BluetoothException {
        manager.stopDiscovery();
    }
}
