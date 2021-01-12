package dev.czajor.bluetoothmanager.mapper;

import dev.czajor.bluetoothmanager.model.DeviceDto;
import dev.czajor.bluetoothmanager.domain.Device;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceMapper {
    public DeviceDto mapToDeviceDto(Device source) {
        return new DeviceDto(source.getName(),
                source.getAddress(),
                Integer.toString(source.getDevClass()),
                source.getConnected(),
                source.getType());
    }
    public List<DeviceDto> mapToDeviceDtos(List<Device> source) {
        return source.stream().map(this::mapToDeviceDto).collect(Collectors.toList());
    }
//    public Device mapToDevice(DeviceDto destination);
//    public List<Device> mapToDevices(List<DeviceDto> source);
}
