package dao;

import model.CategoryElementEntity;
import model.CategoryEntity;

import java.util.List;

public interface CategoryElementEntityDao {

    void insertAllCategoryElement(CategoryEntity categoryEntity);

    List<CategoryElementEntity> findByCategory(CategoryEntity categoryEntity);
}
