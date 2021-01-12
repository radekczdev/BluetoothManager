package dev.czajor.bluetoothmanager.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinyb.BluetoothDevice;
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

    public boolean startDiscovery() {
        return bluetoothManager.startDiscovery();
    }

    public void stopDiscovery() {
        bluetoothManager.stopDiscovery();
    }

    public List<BluetoothDevice> getDevices() {
        return bluetoothManager.getDevices();
    }
}
