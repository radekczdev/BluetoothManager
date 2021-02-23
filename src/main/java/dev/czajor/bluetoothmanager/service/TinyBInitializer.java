package dev.czajor.bluetoothmanager.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinyb.BluetoothDevice;
import tinyb.BluetoothException;
import tinyb.BluetoothManager;

import java.util.List;

@RequiredArgsConstructor
@Getter
//@NoArgsConstructor
@Service
public class TinyBInitializer {
    private BluetoothManager bluetoothManager;

    @Autowired
    public TinyBInitializer(BluetoothManager bluetoothManager) {
        this.bluetoothManager = bluetoothManager;
    }

    public boolean startDiscovery() throws BluetoothException {
        return bluetoothManager.startDiscovery();
    }

    public void stopDiscovery() throws BluetoothException {
        bluetoothManager.stopDiscovery();
    }

    public List<BluetoothDevice> getDevices() throws BluetoothException {
        return bluetoothManager.getDevices();
    }
}
