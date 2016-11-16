package com.maxclay.service.impl;

import com.maxclay.dao.VehicleDao;
import com.maxclay.model.Vehicle;
import com.maxclay.service.VehicleService;

import java.util.List;

/**
 * Implementation of {@link com.maxclay.service.VehicleService VehicleService} interface.
 * Operates on the instances of {@link com.maxclay.model.Vehicle Vehicle} class.
 * Uses {@link com.maxclay.dao.VehicleDao VehicleDao} to update instances, persist and retrieve them into/from
 * datasource.
 *
 * @author maxclay
 */
public class VehicleServiceImpl implements VehicleService {

    private VehicleDao vehicleDao;

    public VehicleServiceImpl(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleDao.save(vehicle);
    }

    public Vehicle get(Long id) {
        return vehicleDao.get(id);
    }

    public List<Vehicle> getAll() {
        return vehicleDao.getAll();
    }

    public void delete(Long id) {
        vehicleDao.delete(id);
    }

    public void delete(Vehicle vehicle) {

        if (vehicle.getId() == null) {
            throw new IllegalArgumentException("Vehicle's identifier could not be null");
        }

        delete(vehicle.getId());
    }

    public void deleteAll() {
        vehicleDao.deleteAll();
    }

    public boolean exists(Long id) {
        return vehicleDao.exists(id);
    }

    public boolean exists(Vehicle vehicle) {

        if (vehicle.getId() == null) {
            throw new IllegalArgumentException("Vehicle's identifier could not be null");
        }

        return exists(vehicle.getId());
    }

}
