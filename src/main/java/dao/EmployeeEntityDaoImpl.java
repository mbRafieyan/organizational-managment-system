package dao;

import model.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeEntityDaoImpl implements EmployeeEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(EmployeeEntity employeeEntity) {
        entityManager.persist(employeeEntity);
    }

    @Override
    public void update(EmployeeEntity employeeEntity) {
        entityManager.merge(employeeEntity);
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        Query query = entityManager.createQuery("select c from EmployeeEntity c");
        return (List<EmployeeEntity>) query.getResultList();
    }

    @Override
    public EmployeeEntity selectById(long id) {
        return entityManager.find(EmployeeEntity.class, id);
    }

    @Override
    public void delete(EmployeeEntity employeeEntity) {
        entityManager.remove(employeeEntity);
    }
}
