package dao;

import model.CategoryElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryElementEntityDao {

     void insert(CategoryElementEntity categoryElementEntity);

     void update(CategoryElementEntity categoryElementEntity);

     List<CategoryElementEntity> selectAll();

     CategoryElementEntity selectById(long id);

     void delete(CategoryElementEntity categoryElementEntity);
}
