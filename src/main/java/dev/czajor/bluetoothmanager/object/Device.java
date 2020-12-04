package dev.czajor.bluetoothmanager.object;

import lombok.Builder;
import lombok.Value;
import tinyb.BluetoothDevice;

@Value
@Builder
public class Device {
    BluetoothDevice device;
}
