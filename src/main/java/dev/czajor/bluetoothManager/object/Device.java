package dev.czajor.bluetoothManager.object;

import lombok.Builder;
import lombok.Value;
import tinyb.BluetoothDevice;

@Value
@Builder
public class Device {
    BluetoothDevice device;
}
