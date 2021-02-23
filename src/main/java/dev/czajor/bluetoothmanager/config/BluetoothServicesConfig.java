package dev.czajor.bluetoothmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tinyb.BluetoothManager;

@Configuration
public class BluetoothServicesConfig {

    @Bean(value = "bluetoothManager")
    public BluetoothManager bluetoothManager() {
        return BluetoothManager.getBluetoothManager();
    }

}
