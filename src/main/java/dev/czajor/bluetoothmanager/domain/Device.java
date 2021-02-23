package dev.czajor.bluetoothmanager.domain;

import lombok.*;
import tinyb.BluetoothDevice;

@Getter
//@Setter
@EqualsAndHashCode
//@Component
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Device {
    private BluetoothDevice bluetoothDevice;
    private String name;
    private String address;
    private int devClass;
    private String type;

    public String getConnected() {
        return bluetoothDevice.getConnected() ? "yes" : "no";
    }
}
