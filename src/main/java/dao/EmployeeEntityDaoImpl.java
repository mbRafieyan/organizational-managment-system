package dao;

import model.EmployeeEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeEntityDaoImpl implements EmployeeEntityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(EmployeeEntity employeeEntity) {
        sessionFactory.getCurrentSession().persist(employeeEntity);
    }

    @Override
    public void update(EmployeeEntity employeeEntity) {
        sessionFactory.getCurrentSession().merge(employeeEntity);
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from EmployeeEntity");
        return (List<EmployeeEntity>) query.list();
    }

    @Override
    public EmployeeEntity selectById(long id) {
        return sessionFactory.getCurrentSession().find(EmployeeEntity.class, id);
    }

    @Override
    public void delete(EmployeeEntity employeeEntity) {
        sessionFactory.getCurrentSession().delete(employeeEntity);
    }
}
