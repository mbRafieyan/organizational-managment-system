package service;


import model.CategoryElementEntity;

import java.util.List;

public interface CategoryElementEntityService {

     void addCategoryElementEntity(CategoryElementEntity categoryElementEntity);

     void updateCategoryElementEntity(CategoryElementEntity categoryElementEntity);

     List<CategoryElementEntity> getCategoryElementEntities();

     CategoryElementEntity getCategoryElementEntityById(long id);

     void deleteCategoryElementEntity(CategoryElementEntity categoryElementEntity);
}
