package dao;

import model.CategoryElementEntity;
import model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface CategoryElementEntityDao {

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    Map<Long, String> findByCategory(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findByCategoryElementName(String name);
}
