package dev.czajor.bluetoothmanager.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class DeviceDto {
    String name;
    String address;
    String devClass;
    String connected;
    String type;
}
