package com.repository;

import com.model.CategoryElementEntity;
import com.model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface ICategoryElementEntityCRUD {

    CategoryElementEntity findCategoryElementById(long id);

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    Map<Long, String> findByCategory(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findCategoryElementByName(String name);
}
