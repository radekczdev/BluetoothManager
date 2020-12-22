package dev.czajor.bluetoothmanager.object;

import lombok.*;
import org.springframework.stereotype.Component;
import tinyb.BluetoothDevice;

@Getter
@Setter
@EqualsAndHashCode
@Component
@AllArgsConstructor
@NoArgsConstructor
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
