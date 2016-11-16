package com.maxclay.dao;

import com.maxclay.model.Vehicle;

import java.util.List;

/**
 * @author maxclay
 */
public interface VehicleDao {

    Vehicle save(Vehicle vehicle);

    Vehicle get(Long id);

    List<Vehicle> getAll();

    void delete(Long id);

    void deleteAll();

    boolean exists(Long id);

}
