package com.maxclay.service;

import com.maxclay.model.Vehicle;

import java.util.List;

/**
 * Specifies methods to operate on the instances of {@link com.maxclay.model.Vehicle Vehicle} class. Usually used as a
 * layer between DAO(database access object) and client code, which uses service. For more information
 * see <a href="https://en.wikipedia.org/wiki/Service_layers_pattern">Definition of Service layer pattern</a>.
 *
 * @author maxclay
 */
public interface VehicleService {

    Vehicle save(Vehicle vehicle);

    Vehicle get(Long id);

    List<Vehicle> getAll();

    void delete(Long id);

    void delete(Vehicle vehicle);

    boolean exists(Long id);

    boolean exists(Vehicle vehicle);

}
