package dao;

import model.CategoryEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryEntityDaoImpl implements CategoryEntityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(CategoryEntity categoryEntity) {
        sessionFactory.getCurrentSession().persist(categoryEntity);
    }

    @Override
    public void update(CategoryEntity categoryEntity) {
        sessionFactory.getCurrentSession().merge(categoryEntity);
    }

    @Override
    public List<CategoryEntity> selectAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from CategoryEntity");
        return (List<CategoryEntity> )query.list();
    }

    @Override
    public CategoryEntity selectById(long id) {
        return sessionFactory.getCurrentSession().find(CategoryEntity.class, id);
    }

    @Override
    public void delete(CategoryEntity categoryEntity) {
        sessionFactory.getCurrentSession().delete(categoryEntity);
    }
}
