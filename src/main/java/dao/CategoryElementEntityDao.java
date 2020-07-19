package dao;

import model.CategoryElementEntity;

import java.util.List;

public interface CategoryElementEntityDao {

     void insert(CategoryElementEntity categoryElementEntity);

     void update(CategoryElementEntity categoryElementEntity);

     List<CategoryElementEntity> selectAll();

     CategoryElementEntity selectById(long id);

     void delete(CategoryElementEntity categoryElementEntity);
}
