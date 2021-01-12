package dev.czajor.bluetoothmanager.controller;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.mapper.DeviceMapper;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import dev.czajor.bluetoothmanager.service.AvailableDevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequiredArgsConstructor
public class BluetoothController {
    private AvailableDevicesService availableDevicesService;

    private DeviceMapper mapper;//= Mappers.getMapper(DeviceDeviceDtoMapper.DeviceDeviceDtoMapper.class);

    @GetMapping("/devices")
    public List<DeviceDto> getDevices() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        final Condition cv = lock.newCondition();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            lock.lock();
            try {
                cv.signalAll();
            } finally {
                lock.unlock();
            }

        }));
        List<Device> devices = availableDevicesService.getAll();
        lock.lock();
        try {
            cv.await(1, TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }
        return mapper.mapToDeviceDtos(devices);
    }
}
