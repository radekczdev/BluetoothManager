package dev.czajor.bluetoothmanager.mapper;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeviceMapper {

    public Device mapToDevice(DeviceDto source) {
        return new Device(source.getName(),
                source.getAddress(),
                Integer.parseInt(source.getDevClass()),
                source.getType(),
                source.getConnected().equals("yes"),
                source.isPaired()
        );
    }

    public DeviceDto mapToDeviceDto(Device source) {
        return new DeviceDto(source.getName(),
                source.getAddress(),
                Integer.toString(source.getDevClass()),
                source.isConnected() ? "yes" : "no",
                source.getType(),
                source.isPaired()
        );
    }

    public List<DeviceDto> mapToDeviceDtos(List<Device> source) {
        return source.stream().map(this::mapToDeviceDto).collect(Collectors.toList());
    }

}
