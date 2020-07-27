package dao;

import model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface CategoryEntityDao {

    List<CategoryEntity> selectAllCategory();

    List<CategoryEntity> findByCategoryName(String name);

    Map<Long, CategoryEntity> insertAllCategory();
}
