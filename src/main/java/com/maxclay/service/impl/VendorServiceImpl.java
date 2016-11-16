package com.maxclay.service.impl;

import com.maxclay.dao.VendorDao;
import com.maxclay.model.Vendor;
import com.maxclay.service.VendorService;

import java.util.List;

/**
 * @author maxclay
 */
public class VendorServiceImpl implements VendorService {

    private VendorDao vendorDao;

    public VendorServiceImpl(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public Vendor save(Vendor vendor) {
        return vendorDao.save(vendor);
    }

    public Vendor get(Long id) {
        return vendorDao.get(id);
    }

    public List<Vendor> getAll() {
        return vendorDao.getAll();
    }

    public void delete(Long id) {
        vendorDao.delete(id);
    }

    public void delete(Vendor vendor) {

        if (vendor == null) {
            throw new IllegalArgumentException("Vendor could not be null");
        }

        if (vendor.getId() == null) {
            throw new IllegalArgumentException("Vendor's identifier could not be null");
        }

        delete(vendor.getId());
    }

    public void deleteAll() {
        vendorDao.deleteAll();
    }

    public boolean exists(Long id) {
        return vendorDao.exists(id);
    }

    public boolean exists(Vendor vendor) {

        if (vendor == null) {
            throw new IllegalArgumentException("Vendor could not be null");
        }

        if (vendor.getId() == null) {
            throw new IllegalArgumentException("Vendor's identifier could not be null");
        }

        return exists(vendor.getId());
    }
}
