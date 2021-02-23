package dev.czajor.bluetoothmanager.repository;

import com.hazelcast.org.apache.calcite.runtime.Pattern;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    Optional<T> save(T object);
    Optional<T> delete(String address);
    Optional<T> findById(String id);
    Optional<List<T>> findAll();
    Optional<List<T>> findByConnectionStatus(boolean isConnected);
}
