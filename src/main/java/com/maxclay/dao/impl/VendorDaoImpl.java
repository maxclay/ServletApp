package com.maxclay.dao.impl;

import com.maxclay.dao.VendorDao;
import com.maxclay.model.Vendor;

import java.util.List;

/**
 * @author maxclay
 */
public class VendorDaoImpl implements VendorDao {

    private GeneralDao<Vendor> generalDao;

    public VendorDaoImpl() {
        generalDao = new GeneralDao<Vendor>();
    }

    public Vendor save(Vendor vendor) {
        return generalDao.save(vendor);
    }

    public Vendor get(Long id) {
        return generalDao.get(id, Vendor.class);
    }

    public List<Vendor> getAll() {
        return generalDao.getAll(Vendor.class);
    }

    public void delete(Long id) {
        generalDao.delete(id, Vendor.class);
    }

    public void deleteAll() {
        generalDao.deleteAll(Vendor.class);
    }

    public boolean exists(Long id) {
        return generalDao.exists(id, Vendor.class);
    }

}
