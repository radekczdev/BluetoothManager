package dev.czajor.bluetoothmanager.domain;

import lombok.*;
import tinyb.BluetoothDevice;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Device implements BluetoothObject {
    private final BluetoothDevice bluetoothDevice;
    private final String name;
    private final String address;
    private final int devClass;
    private final String type;
    private final boolean connected;

    public boolean getConnected() {
        return bluetoothDevice.getConnected();
    }
}
