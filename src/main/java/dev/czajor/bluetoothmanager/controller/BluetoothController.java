package dev.czajor.bluetoothmanager.controller;

import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.mapper.DeviceMapper;
import dev.czajor.bluetoothmanager.model.DeviceDto;
import dev.czajor.bluetoothmanager.service.AvailableDevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BluetoothController {
    private final AvailableDevicesService availableDevicesService;
    private final DeviceMapper mapper;

    @GetMapping(value = "/devices",
            produces = APPLICATION_JSON_VALUE)
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
