package service;


import model.CategoryElementEntity;
import model.CategoryEntity;

import java.util.List;

public interface CategoryElementEntityService {

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findByCategory(CategoryEntity categoryEntity);
}
