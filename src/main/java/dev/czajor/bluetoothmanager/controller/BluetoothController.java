package dev.czajor.bluetoothmanager.controller;

import dev.czajor.bluetoothmanager.mapper.DeviceMapper;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import dev.czajor.bluetoothmanager.object.Device;
import dev.czajor.bluetoothmanager.service.AvailableDevicesService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tinyb.BluetoothManager;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BluetoothController {
    private BluetoothManager bluetoothManager;
    private AvailableDevicesService availableDevicesService;

    private DeviceMapper mapper;//= Mappers.getMapper(DeviceDeviceDtoMapper.DeviceDeviceDtoMapper.class);

    @GetMapping("/devices")
    public List<DeviceDto> getDevices() {
        bluetoothManager.startDiscovery();
        List<Device> devices = availableDevicesService.getAll(bluetoothManager);
        bluetoothManager.stopDiscovery();
        return mapper.mapToDeviceDtos(devices);
    }
}
