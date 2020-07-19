package service;


import model.CategoryEntity;

import java.util.List;

public interface CategoryEntityService {

     void addCategoryEntity(CategoryEntity categoryEntity);

     void updateCategoryEntity(CategoryEntity categoryEntity);

     List<CategoryEntity> getCategoryEntities();

     CategoryEntity getCategoryEntityById(long id);

     void deleteCategoryEntity(CategoryEntity categoryEntity);
}
