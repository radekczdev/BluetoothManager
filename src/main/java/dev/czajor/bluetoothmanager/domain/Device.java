package dev.czajor.bluetoothmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@AllArgsConstructor
public class Device extends SerializationClass implements BluetoothObject {
    private final String name;
    @Id
    private final String address;
    private final int devClass;
    private final String type;
    private final boolean connected;
}
