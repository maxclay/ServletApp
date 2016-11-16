package com.maxclay.dao.impl;

import com.maxclay.dao.VehicleDao;
import com.maxclay.model.Vehicle;

import java.util.List;

/**
 * @author maxclay
 */
public class VehicleDaoImpl implements VehicleDao {

    private GeneralDao<Vehicle> generalDao;

    public VehicleDaoImpl() {
        generalDao = new GeneralDao<Vehicle>();
    }

    public Vehicle save(Vehicle vehicle) {
        return generalDao.save(vehicle);
    }

    public Vehicle get(Long id) {
        return generalDao.get(id, Vehicle.class);
    }

    public List<Vehicle> getAll() {
        return generalDao.getAll(Vehicle.class);
    }


    public void delete(Long id) {
        generalDao.delete(id, Vehicle.class);
    }

    public void deleteAll() {
        generalDao.deleteAll(Vehicle.class);
    }

    public boolean exists(Long id) {
        return generalDao.exists(id, Vehicle.class);
    }

}
