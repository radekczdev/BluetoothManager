package dev.czajor.bluetoothmanager.controller;

import dev.czajor.bluetoothmanager.mapper.DeviceMapper;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.service.AvailableDevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BluetoothController {
    private AvailableDevicesService availableDevicesService;

    private DeviceMapper mapper;//= Mappers.getMapper(DeviceDeviceDtoMapper.DeviceDeviceDtoMapper.class);

    @GetMapping("/devices")
    public List<DeviceDto> getDevices() {
        List<Device> devices = availableDevicesService.getAll();
        return mapper.mapToDeviceDtos(devices);
    }
}
