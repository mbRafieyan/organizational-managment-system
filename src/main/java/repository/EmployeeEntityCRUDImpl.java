package repository;

import model.CategoryElementEntity;
import model.EmployeeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class EmployeeEntityCRUDImpl implements IEmployeeEntityCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(EmployeeEntity employeeEntity) {

        employeeEntity.setActive(true);
        employeeEntity.setVersion(1);
        employeeEntity.setCreateDate(new Date().toString());

        entityManager.persist(employeeEntity);
    }

    @Override
    public void update(EmployeeEntity employeeEntity) {
        entityManager.merge(employeeEntity);
    }

    @Override
    public EmployeeEntity selectById(long id) {
        return entityManager.find(EmployeeEntity.class, id);
    }

    @Override
    public List<EmployeeEntity> findByManager(EmployeeEntity employeeEntity) {
        Query query = entityManager.createQuery("select e from EmployeeEntity e where e.employeeManager = :employeeManager");
        query.setParameter("employeeManager", employeeEntity.getEmployeeManager());
        List<EmployeeEntity> childEmployeeList = query.getResultList();
        return childEmployeeList;
    }

    @Override
    public void delete(EmployeeEntity employeeEntity) {
        EmployeeEntity employee = entityManager.find(EmployeeEntity.class, employeeEntity.getId());
        entityManager.remove(employee);
    }


    @Override
    public List<EmployeeEntity> selectAll() {
        Query query = entityManager.createQuery("select c from EmployeeEntity c");
        List<EmployeeEntity> EmployeeEntityList = query.getResultList();
        return EmployeeEntityList;
    }

    @Override
    public Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity) {

        Query query = entityManager.createQuery("select e from EmployeeEntity e where e.employeeRole = :categoryElementEntity");
        query.setParameter("categoryElementEntity", categoryElementEntity);
        List<EmployeeEntity> employeeEntityList = query.getResultList();

        Map<Long, String> employeeEntityMap = new HashMap<>();

        for (EmployeeEntity employeeEntity : employeeEntityList) {
            String fullName = employeeEntity.getFirstName() + " " + employeeEntity.getLastName();
            employeeEntityMap.put(employeeEntity.getId(), fullName);
        }

        return employeeEntityMap;
    }
}
