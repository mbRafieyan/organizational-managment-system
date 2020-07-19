package dao;

import model.VacationsEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacationsEntityDaoImpl implements VacationsEntityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(VacationsEntity vacationsEntity) {
        sessionFactory.getCurrentSession().persist(vacationsEntity);
    }

    @Override
    public void update(VacationsEntity vacationsEntity) {
        sessionFactory.getCurrentSession().merge(vacationsEntity);
    }

    @Override
    public List<VacationsEntity> selectAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from VacationsEntity");
        return (List<VacationsEntity>) query.list();
    }

    @Override
    public VacationsEntity selectById(long id) {
        return sessionFactory.getCurrentSession().find(VacationsEntity.class, id);
    }

    @Override
    public void delete(VacationsEntity vacationsEntity) {
        sessionFactory.getCurrentSession().delete(vacationsEntity);
    }
}
