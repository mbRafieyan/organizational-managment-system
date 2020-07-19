package dao;

import model.CategoryElementEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryElementEntityDaoImpl implements CategoryElementEntityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(CategoryElementEntity categoryElementEntity) {
        sessionFactory.getCurrentSession().persist(categoryElementEntity);
    }

    @Override
    public void update(CategoryElementEntity categoryElementEntity) {
        sessionFactory.getCurrentSession().merge(categoryElementEntity);
    }

    @Override
    public List<CategoryElementEntity> selectAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from CategoryElementEntity");
        return (List<CategoryElementEntity>) query.list();
    }

    @Override
    public CategoryElementEntity selectById(long id) {
        return sessionFactory.getCurrentSession().find(CategoryElementEntity.class, id);
    }

    @Override
    public void delete(CategoryElementEntity categoryElementEntity) {
        sessionFactory.getCurrentSession().delete(categoryElementEntity);
    }
}
