package dev.czajor.bluetoothmanager.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
   private String name;
   private String address;
   private String devClass;
   private String connected;
   private String type;
   private boolean isPaired;
}
