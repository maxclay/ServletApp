package com.maxclay.dao.impl;

import com.maxclay.dao.RowMaterialDao;
import com.maxclay.model.RowMaterial;

import java.util.List;

/**
 * @author maxclay
 */
public class RowMaterialDaoImpl implements RowMaterialDao {

    private GeneralDao<RowMaterial> generalDao;

    public RowMaterialDaoImpl() {
        generalDao = new GeneralDao<RowMaterial>();
    }

    public RowMaterial save(RowMaterial rowMaterial) {
        return generalDao.save(rowMaterial);
    }

    public RowMaterial get(Long id) {
        return generalDao.get(id, RowMaterial.class);
    }

    public List<RowMaterial> getAll() {
        return generalDao.getAll(RowMaterial.class);
    }

    public void delete(Long id) {
        generalDao.delete(id, RowMaterial.class);
    }

    public void deleteAll() {
        generalDao.deleteAll(RowMaterial.class);
    }

    public boolean exists(Long id) {
        return generalDao.exists(id, RowMaterial.class);
    }

}
