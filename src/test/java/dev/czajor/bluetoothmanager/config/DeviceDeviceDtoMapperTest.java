package dev.czajor.bluetoothmanager.config;

import dev.czajor.bluetoothmanager.mapper.DeviceMapper;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import dev.czajor.bluetoothmanager.object.LoadedDevice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tinyb.BluetoothDevice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DeviceLoadedDeviceDtoMapperTest {
    @Autowired
    private DeviceMapper mapper; //= Mappers.getMapper(DeviceMapperInterface.class);

    @MockBean
    private BluetoothDevice bluetoothDevice;

    @Test
    public void mapToDevice() {
        LoadedDevice device = new LoadedDevice(
                bluetoothDevice,
                "address",
                "devClass",
                123,
                "type");
        DeviceDto deviceDto = mapper.mapToDeviceDto(device);
        assertNotNull(deviceDto);
    }



}