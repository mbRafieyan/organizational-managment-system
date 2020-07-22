package dao;

import model.CategoryElementEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryElementEntityDaoImpl implements CategoryElementEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(CategoryElementEntity categoryElementEntity) {
        entityManager.persist(categoryElementEntity);
    }

    @Override
    public void update(CategoryElementEntity categoryElementEntity) {
        entityManager.merge(categoryElementEntity);
    }

    @Override
    public List<CategoryElementEntity> selectAll() {
        Query query = entityManager.createQuery("select c from CategoryElementEntity c");
        return (List<CategoryElementEntity>) query.getResultList();
    }

    @Override
    public CategoryElementEntity selectById(long id) {
        return entityManager.find(CategoryElementEntity.class, id);
    }

    @Override
    public void delete(CategoryElementEntity categoryElementEntity) {
        entityManager.remove(categoryElementEntity);
    }
}
