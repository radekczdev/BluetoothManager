package dev.czajor.bluetoothmanager.config;

import dev.czajor.bluetoothmanager.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tinyb.BluetoothManager;

import javax.annotation.PostConstruct;

@Configuration
public class BluetoothServicesConfig {

    @Bean(value = "bluetoothManager")
    public BluetoothManager bluetoothManager() {
        return BluetoothManager.getBluetoothManager();
    }

    @PostConstruct
    public void initializeDatabase(@Autowired DevicesService devicesService) {
        devicesService.saveDevicesToRepository();
    }

}
