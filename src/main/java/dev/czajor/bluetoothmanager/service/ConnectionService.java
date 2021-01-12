package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import tinyb.BluetoothException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Service
public class ConnectionService {
    public static boolean connect(final Device device) throws BluetoothException {
        return device.getBluetoothDevice().connect();
    }

    public static boolean disconnect(final Device device) throws BluetoothException {
        return device.getBluetoothDevice().disconnect();
    }

    public static boolean isConnected(final Device device) throws BluetoothException {
        return device.getBluetoothDevice().getConnected();
    }
}
