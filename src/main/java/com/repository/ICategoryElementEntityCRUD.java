package com.repository;

import com.model.CategoryElementEntity;
import com.model.CategoryEntity;

import java.util.List;

public interface ICategoryElementEntityCRUD {

    CategoryElementEntity findCategoryElementById(long id);

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findByCategory(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findCategoryElementByName(String name);
}
