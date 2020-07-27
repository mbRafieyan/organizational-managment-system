package service;


import model.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface CategoryEntityService {

    List<CategoryEntity> selectAllCategory();

    List<CategoryEntity> findByCategoryName(String name);

    Map<Long, CategoryEntity> insertAllCategory();
}
