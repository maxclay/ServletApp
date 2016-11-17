package com.maxclay.service;

import com.maxclay.dto.RowMaterialDto;
import com.maxclay.model.RowMaterial;

import java.util.List;

/**
 * @author maxclay
 */
public interface RowMaterialService {

    RowMaterial save(RowMaterial rowMaterial);

    RowMaterial save(RowMaterialDto rowMaterialDto);

    RowMaterial save(Long id, RowMaterialDto rowMaterialDto);

    RowMaterial get(Long id);

    List<RowMaterial> getAll();

    void delete(Long id);

    void delete(RowMaterial rowMaterial);

    void deleteAll();

    boolean exists(Long id);

    boolean exists(RowMaterial rowMaterial);

}
