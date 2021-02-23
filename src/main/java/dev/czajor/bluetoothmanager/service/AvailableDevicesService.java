package dev.czajor.bluetoothmanager.service;

import dev.czajor.bluetoothmanager.domain.Device;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tinyb.BluetoothDevice;
import tinyb.BluetoothException;

import java.util.*;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableDevicesService {
    private final TinyBInitializer tinyBInitializer;
    Logger logger = LoggerFactory.getLogger(AvailableDevicesService.class);

    public List<Device> getAll() {
        List<Device> devices = new ArrayList<>();
        try {
            logger.info("Starting to search devices...");
            tinyBInitializer.startDiscovery();
            List<BluetoothDevice> devicesRaw = Optional.ofNullable(tinyBInitializer.getDevices()).orElse(Collections.emptyList());
            logger.info("Found {} devices", devicesRaw.size());
            devices = devicesRaw.stream()
                    .filter(Objects::nonNull)
                    .map(dev -> new Device(dev,
                            dev.getName(),
                            dev.getAddress(),
                            dev.getBluetoothClass(),
                            dev.getBluetoothType().name(),
                            dev.getConnected()))
                    .collect(Collectors.toList());
        } catch (BluetoothException exception) {
            logger.error("Error: ", exception);
        }
        finally {
            tinyBInitializer.stopDiscovery();
        }
        return devices;
    }

    public void printDeviceInformation(Device device) {
        logger.info("Address = {}", device.getAddress());
        logger.info(" Name = {}", device.getName());
        logger.info(" Class = {}", device.getClass());
        logger.info(" Type = {}", device.getType());
        logger.info(" Connected = {}", device.getConnected());
    }
}
