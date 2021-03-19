package dev.czajor.bluetoothmanager.repository;

import dev.czajor.bluetoothmanager.exception.CouldNotRemoveObjectsException;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    Optional<T> save(T object);
    Optional<T> delete(String address);
    void deleteAll() throws CouldNotRemoveObjectsException;
    Optional<T> findById(String id);
    Optional<List<T>> findAll();
    Optional<List<T>> findByConnectionStatus(boolean isConnected);
}
