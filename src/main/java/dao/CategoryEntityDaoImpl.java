package dao;

import model.CategoryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class CategoryEntityDaoImpl implements CategoryEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(CategoryEntity categoryEntity) {

        categoryEntity.setCreateDate(new Date());
        categoryEntity.setActive(true);
        categoryEntity.setVersion(1.0F);
        entityManager.persist(categoryEntity);
    }

    @Override
    public void update(CategoryEntity categoryEntity) {
        entityManager.merge(categoryEntity);
    }

    @Override
    public List<CategoryEntity> selectAll() {
        Query query = entityManager.createQuery("select c from CategoryEntity c");
        return (List<CategoryEntity>) query.getResultList();
    }

    @Override
    public CategoryEntity selectById(long id) {
        return entityManager.find(CategoryEntity.class, id);
    }

    @Override
    public void delete(CategoryEntity categoryEntity) {
        entityManager.remove(categoryEntity);
    }
}
