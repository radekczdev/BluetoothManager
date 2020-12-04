package dev.czajor.bluetoothManager.service;

import dev.czajor.bluetoothManager.object.Device;

public class ConnectionService {
    public static boolean connect(final Device device) {
        return device.getDevice().disconnect();
    }

    public static boolean disconnect(final Device device) {
        return device.getDevice().disconnect();
    }

    public static boolean isConnected(final Device device) {
        return device.getDevice().getConnected();
    }
}
