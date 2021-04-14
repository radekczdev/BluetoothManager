package dev.czajor.bluetoothmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device implements BluetoothObject {
    private String name;
    @Id
    private String address;
    private int devClass;
    private String type;
    private boolean connected;
    private boolean isPaired;
}
