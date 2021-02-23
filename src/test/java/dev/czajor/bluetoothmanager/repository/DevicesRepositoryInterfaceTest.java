package dev.czajor.bluetoothmanager.repository;

import dev.czajor.bluetoothmanager.domain.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class DevicesRepositoryInterfaceTest {
    @Autowired
    private DevicesRepositoryInterface devicesRepository;

    @Test
    void save() {
        Device device = Device.builder()
                .bluetoothDevice(null)
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