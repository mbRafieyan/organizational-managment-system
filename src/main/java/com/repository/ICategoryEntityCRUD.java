package com.repository;

import com.model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface ICategoryEntityCRUD {

    List<CategoryEntity> selectAllCategory();

    List<CategoryEntity> findByCategoryName(String name);

    Map<Long, CategoryEntity> insertAllCategory();
}
