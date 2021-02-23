package dev.czajor.bluetoothmanager.config;

import dev.czajor.bluetoothmanager.service.TinyBInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import tinyb.BluetoothManager;

@Configuration
public class BluetoothServicesConfig {

    @Bean(value = "bluetoothManager")
    public BluetoothManager bluetoothManager() {
        return BluetoothManager.getBluetoothManager();
    }

//    @Bean
//    @DependsOn("bluetoothManager")
//    public TinyBInitializer tinyBInitializer() {
//        return new TinyBInitializer();
//    }
}
