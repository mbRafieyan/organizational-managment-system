package service;


import model.CategoryElementEntity;
import model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface CategoryElementEntityService {

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    Map<Long, String> findByCategory(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findByCategoryElementEntityName(String name);
}
