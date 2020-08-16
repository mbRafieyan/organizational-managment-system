package com.service;


import com.model.CategoryElementEntity;
import com.model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface ICategoryElementEntityService {

    CategoryElementEntity findCategoryElementById(long id);

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findByCategory(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findCategoryElementEntityByName(String name);
}
