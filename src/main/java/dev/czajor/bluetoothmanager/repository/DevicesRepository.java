package dev.czajor.bluetoothmanager.repository;

import dev.czajor.bluetoothmanager.domain.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevicesRepository extends CrudRepository<Device, String> {

    @Override
    Device save(Device device);

    void deleteById(String address);

    Optional<Device> findById(String address);

    @Override
    List<Device> findAll();

    List<Device> findAllByConnected(boolean connected);

}
