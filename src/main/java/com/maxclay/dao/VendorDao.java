package com.maxclay.dao;

import com.maxclay.model.Vendor;

import java.util.List;

/**
 * @author maxclay
 */
public interface VendorDao {

    Vendor save(Vendor vendor);

    Vendor get(Long id);

    List<Vendor> getAll();

    void delete(Long id);

    void deleteAll();

    boolean exists(Long id);

}
