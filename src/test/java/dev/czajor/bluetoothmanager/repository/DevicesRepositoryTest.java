package dev.czajor.bluetoothmanager.repository;

import com.hazelcast.core.HazelcastInstance;
import dev.czajor.bluetoothmanager.domain.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class DevicesRepositoryTest {
    @Autowired
    private DevicesRepository devicesRepository;
    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Test
    void save() {
        Device device = Device.builder()
                .address("TEST_ADDRESS")
                .connected(true)
                .devClass(123)
                .name("TEST_NAME")
                .type("TEST_TYPE")
                .build();
        devicesRepository.save(device);
        Optional<Device> device1 = devicesRepository.findById(device.getAddress());
        assertNotEquals(Optional.empty(), device1);
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByConnectionStatus() {
    }
}