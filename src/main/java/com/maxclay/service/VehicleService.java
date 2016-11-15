package com.maxclay.service;

import com.maxclay.model.Vehicle;

import java.util.List;

/**
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
