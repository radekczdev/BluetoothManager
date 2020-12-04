package dev.czajor.bluetoothmanager.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tinyb.BluetoothManager;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TinyBInitializer {
    private final BluetoothManager manager;

    public TinyBInitializer() {
        this.manager = BluetoothManager.getBluetoothManager();
    }

    public boolean startDiscovery() {
        return manager.startDiscovery();
    }

    public boolean stopDiscovery() {
        return manager.stopDiscovery();
    }
}
