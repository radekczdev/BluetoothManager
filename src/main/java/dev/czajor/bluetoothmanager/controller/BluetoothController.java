package dev.czajor.bluetoothmanager.controller;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.exception.CouldNotRemoveObjectsException;
import dev.czajor.bluetoothmanager.exception.DeviceNotFoundException;
import dev.czajor.bluetoothmanager.mapper.DeviceMapper;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import dev.czajor.bluetoothmanager.service.ConnectionService;
import dev.czajor.bluetoothmanager.service.DevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/devices")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BluetoothController {
    private final DevicesService devicesService;
    private final ConnectionService connectionService;
    private final DeviceMapper mapper;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<DeviceDto> getDevicesFromDatabase() {
        return mapper.mapToDeviceDtos(devicesService.getAll());
    }

    @GetMapping(value = "/refresh",
            produces = APPLICATION_JSON_VALUE)
    public List<DeviceDto> refreshDevices() throws CouldNotRemoveObjectsException {
        return mapper.mapToDeviceDtos(devicesService.refreshDatabase());
    }

    @PutMapping(value = "/connect/{address}",
            produces = APPLICATION_JSON_VALUE)
    public DeviceDto connectToDevice(@PathVariable String address) throws CouldNotRemoveObjectsException, DeviceNotFoundException {
        Device device = connectionService.connect(address);
        return mapper.mapToDeviceDto(device);
    }

    @PutMapping(value = "/disconnect/{address}",
            produces = APPLICATION_JSON_VALUE)
    public DeviceDto disconnectFromDevice(@PathVariable String address) throws CouldNotRemoveObjectsException, DeviceNotFoundException {
        Device device = connectionService.disconnect(address);
        return mapper.mapToDeviceDto(device);
    }
}
