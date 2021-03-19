package dev.czajor.bluetoothmanager.repository;

import com.hazelcast.core.HazelcastInstance;
import dev.czajor.bluetoothmanager.domain.Device;
import dev.czajor.bluetoothmanager.exception.CouldNotRemoveObjectsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class DevicesRepository implements RepositoryInterface<Device> {
    private static final String DEVICES_DATABASE = "devices";
    private final HazelcastInstance devicesDatabase;

    private ConcurrentMap<String, Device> getDevicesMap() {
        return devicesDatabase.getMap(DEVICES_DATABASE);
    }

    @Override
    public Optional<Device> save(Device device) {
        return Optional.of(
                getDevicesMap().merge(device.getAddress(), device, (oldDev, newDev) -> newDev));
    }

    @Override
    public Optional<Device> delete(String address) {
        return Optional.ofNullable(
                getDevicesMap().remove(address));
    }

    @Override
    public void deleteAll() throws CouldNotRemoveObjectsException {
        getDevicesMap().clear();
        if (!getDevicesMap().isEmpty()) {
            throw new CouldNotRemoveObjectsException("Devices map is not empty!");
        }
    }

    @Override
    public Optional<Device> findById(String address) {
        return Optional.ofNullable(
                getDevicesMap().get(address));
    }

    @Override
    public Optional<List<Device>> findAll() {
        return Optional.of(new ArrayList<>(getDevicesMap().values()));
    }

    @Override
    public Optional<List<Device>> findByConnectionStatus(boolean isConnected) {
        return Optional.of(getDevicesMap().values().stream()
                .filter(device -> device.isConnected() == isConnected)
                .collect(Collectors.toList()));
    }
}
