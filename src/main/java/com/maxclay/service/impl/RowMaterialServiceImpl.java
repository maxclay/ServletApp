package com.maxclay.service.impl;

import com.maxclay.dao.RowMaterialDao;
import com.maxclay.dao.VendorDao;
import com.maxclay.dto.RowMaterialDto;
import com.maxclay.exception.ResourceNotFoundException;
import com.maxclay.model.RowMaterial;
import com.maxclay.model.Vendor;
import com.maxclay.service.RowMaterialService;

import java.util.List;

/**
 * @author maxclay
 */
public class RowMaterialServiceImpl implements RowMaterialService {

    private RowMaterialDao rowMaterialDao;
    private VendorDao vendorDao;

    public RowMaterialServiceImpl(RowMaterialDao rowMaterialDao, VendorDao vendorDao) {

        this.rowMaterialDao = rowMaterialDao;
        this.vendorDao = vendorDao;
    }

    public RowMaterial save(RowMaterial rowMaterial) {
        return rowMaterialDao.save(rowMaterial);
    }

    public RowMaterial save(RowMaterialDto rowMaterialDto) {

        if (rowMaterialDto == null) {
            throw new IllegalArgumentException("rowMaterialDto can't be null");
        }

        Long vendorId = rowMaterialDto.getVendor();
        if (vendorId == null) {
            throw new IllegalArgumentException("Vendor's id can't be null");
        }

        if (!vendorDao.exists(vendorId)) {
            throw new ResourceNotFoundException("Vendor not found");
        }

        Vendor vendor = vendorDao.get(rowMaterialDto.getVendor());
        RowMaterial rowMaterial = new RowMaterial();
        rowMaterial.setName(rowMaterialDto.getName());
        rowMaterial.setWeight(rowMaterialDto.getWeight());
        rowMaterial.setVendor(vendor);

        return save(rowMaterial);
    }

    public RowMaterial save(Long id, RowMaterialDto rowMaterialDto) {

        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        if (!exists(id)) {
            throw new ResourceNotFoundException("RowMaterial not found");
        }

        if (rowMaterialDto == null) {
            throw new IllegalArgumentException("rowMaterialDto can't be null");
        }

        Long vendorId = rowMaterialDto.getVendor();
        if (vendorId == null) {
            throw new IllegalArgumentException("Vendor's id can't be null");
        }

        if (!vendorDao.exists(vendorId)) {
            throw new ResourceNotFoundException("Vendor not found");
        }

        Vendor vendor = vendorDao.get(rowMaterialDto.getVendor());
        RowMaterial rowMaterial = get(id);
        rowMaterial.setName(rowMaterialDto.getName());
        rowMaterial.setWeight(rowMaterialDto.getWeight());
        rowMaterial.setVendor(vendor);

        return save(rowMaterial);
    }

    public RowMaterial get(Long id) {
        return rowMaterialDao.get(id);
    }

    public List<RowMaterial> getAll() {
        return rowMaterialDao.getAll();
    }

    public void delete(Long id) {
        rowMaterialDao.delete(id);
    }

    public void delete(RowMaterial rowMaterial) {

        if (rowMaterial == null) {
            throw new IllegalArgumentException("Vehicle could not be null");
        }

        if (rowMaterial.getId() == null) {
            throw new IllegalArgumentException("Vehicle's identifier could not be null");
        }

        delete(rowMaterial.getId());
    }

    public void deleteAll() {
        rowMaterialDao.deleteAll();
    }

    public boolean exists(Long id) {
        return rowMaterialDao.exists(id);
    }

    public boolean exists(RowMaterial rowMaterial) {

        if (rowMaterial == null) {
            throw new IllegalArgumentException("Vehicle could not be null");
        }

        if (rowMaterial.getId() == null) {
            throw new IllegalArgumentException("Vehicle's identifier could not be null");
        }

        return exists(rowMaterial.getId());
    }

}
