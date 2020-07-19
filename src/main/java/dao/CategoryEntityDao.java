package dao;

import model.CategoryEntity;

import java.util.List;

public interface CategoryEntityDao {

     void insert(CategoryEntity categoryEntity);

     void update(CategoryEntity categoryEntity);

     List<CategoryEntity> selectAll();

     CategoryEntity selectById(long id);

     void delete(CategoryEntity categoryEntity);
}
