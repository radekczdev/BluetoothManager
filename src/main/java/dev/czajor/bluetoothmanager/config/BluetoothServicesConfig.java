package dev.czajor.bluetoothmanager.config;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tinyb.BluetoothManager;

@Configuration
public class BluetoothServicesConfig {

    @Bean(value = "bluetoothManager")
    public BluetoothManager bluetoothManager() {
        return BluetoothManager.getBluetoothManager();
    }

    @Bean(value = "devicesDatabase")
    public HazelcastInstance hazelcastInstance(@Autowired HazelcastInstance hazelcastInstance) {
        return hazelcastInstance;
    }
}
