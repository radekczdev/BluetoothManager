package dev.czajor.bluetoothmanager.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

//    public TinyBInitializer() {
//        this.bluetoothManager = BluetoothManager.getBluetoothManager();
//    }

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
