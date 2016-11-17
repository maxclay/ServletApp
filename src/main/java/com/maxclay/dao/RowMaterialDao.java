package com.maxclay.dao;

import com.maxclay.model.RowMaterial;

import java.util.List;

/**
 * @author maxclay
 */
public interface RowMaterialDao {

    RowMaterial save(RowMaterial rowMaterial);

    RowMaterial get(Long id);

    List<RowMaterial> getAll();

    void delete(Long id);

    void deleteAll();

    boolean exists(Long id);

}
