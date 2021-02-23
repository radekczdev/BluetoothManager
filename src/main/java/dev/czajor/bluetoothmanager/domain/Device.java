package dev.czajor.bluetoothmanager.domain;

import lombok.*;
import tinyb.BluetoothDevice;

@Getter
@Builder
//@EqualsAndHashCode
@AllArgsConstructor
public class Device extends SerializationClass implements BluetoothObject{
    private final String name;
    private final String address;
    private final int devClass;
    private final String type;
    private final boolean connected;
}
