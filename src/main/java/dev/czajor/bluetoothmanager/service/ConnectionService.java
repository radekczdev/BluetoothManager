package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.object.Device;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tinyb.BluetoothException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionService {
    public static boolean connect(final Device device) throws BluetoothException {
        return device.getDevice().connect();
    }

    public static boolean disconnect(final Device device) throws BluetoothException {
        return device.getDevice().disconnect();
    }

    public static boolean isConnected(final Device device) throws BluetoothException {
        return device.getDevice().getConnected();
    }
}
