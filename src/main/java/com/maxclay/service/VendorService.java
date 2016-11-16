package com.maxclay.service;

import com.maxclay.model.Vendor;

import java.util.List;

/**
 * @author maxclay
 */
public interface VendorService {

    Vendor save(Vendor vendor);

    Vendor get(Long id);

    List<Vendor> getAll();

    void delete(Long id);

    void delete(Vendor vendor);

    void deleteAll();

    boolean exists(Long id);

    boolean exists(Vendor vendor);

}
