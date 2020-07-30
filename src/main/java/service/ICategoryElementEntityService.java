package service;


import model.CategoryElementEntity;
import model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface ICategoryElementEntityService {

    CategoryElementEntity findCategoryElementById(long id);

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    Map<Long, String> findByCategory(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findByCategoryElementEntityByName(String name);
}
