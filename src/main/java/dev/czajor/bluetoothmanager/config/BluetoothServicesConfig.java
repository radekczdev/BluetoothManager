package dev.czajor.bluetoothmanager.config;

import dev.czajor.bluetoothmanager.service.TinyBInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tinyb.BluetoothManager;

@Configuration
public class BluetoothServicesConfig {

    @Bean
    public BluetoothManager bluetoothManager() {
        BluetoothManager manager;
        try (TinyBInitializer tinyBInitializer = new TinyBInitializer()) {
            manager = tinyBInitializer.getManager();
        }
        return manager;
    }
}
