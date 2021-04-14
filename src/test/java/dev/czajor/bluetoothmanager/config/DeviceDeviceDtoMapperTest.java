package dev.czajor.bluetoothmanager.config;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.mapper.DeviceMapper;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tinyb.BluetoothDevice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DeviceDeviceDtoMapperTest {
    @Autowired
    private DeviceMapper mapper;

    @MockBean
    private BluetoothDevice bluetoothDevice;

    @Test
    void mapToDevice() {
        Device device = new Device(
                "address",
                "devClass",
                123,
                "type",
                true,
                true);
        DeviceDto deviceDto = mapper.mapToDeviceDto(device);
        assertNotNull(deviceDto);
    }


}