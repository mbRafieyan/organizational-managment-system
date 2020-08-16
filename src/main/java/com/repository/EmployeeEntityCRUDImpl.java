package com.repository;

import com.model.CategoryElementEntity;
import com.model.EmployeeEntity;
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
    public void update(EmployeeEntity employeeEntity, EmployeeEntity oldEmployeeEntity) {

        employeeEntity.setCreateDate(oldEmployeeEntity.getCreateDate());
        employeeEntity.setModifiedDate(new Date().toString());
        employeeEntity.setActive(oldEmployeeEntity.getActive());
        employeeEntity.setVersion(oldEmployeeEntity.getVersion()+1);
        entityManager.merge(employeeEntity);
    }

    @Override
    public EmployeeEntity selectById(long id) {
        return entityManager.find(EmployeeEntity.class, id);
    }

    @Override
    public void insertAdminEmployee(EmployeeEntity employeeEntity, CategoryElementEntity categoryElementEntity) {

        employeeEntity.setFirstName("admin");
        employeeEntity.setLastName("admin");
        employeeEntity.setCreateDate(new Date().toString());
        employeeEntity.setEmailAddress("admin@dotin.com");
        employeeEntity.setVersion(1);
        employeeEntity.setActive(true);
        employeeEntity.setEmployeeRole(categoryElementEntity);

        entityManager.persist(employeeEntity);
    }

    @Override
    public List<EmployeeEntity> findByManager(EmployeeEntity employeeEntity) {
        Query query = entityManager.createQuery("select e from EmployeeEntity e where e.employeeManager = :employeeManager and e.active = true");
        query.setParameter("employeeManager", employeeEntity);
        List<EmployeeEntity> childEmployeeList = query.getResultList();
        return childEmployeeList;
    }

    @Override
    public void delete(EmployeeEntity employeeEntity) {
        EmployeeEntity employee = entityManager.find(EmployeeEntity.class, employeeEntity.getId());
        employee.setActive(false);
        employee.setModifiedDate(new Date().toString());
        employee.setVersion(employeeEntity.getVersion()+1);
        entityManager.merge(employee);
    }


    @Override
    public List<EmployeeEntity> selectAll() {
        Query query = entityManager.createQuery("select c from EmployeeEntity c where c.active = true and c.lastName <> :name");
        query.setParameter("name","admin");
        List<EmployeeEntity> EmployeeEntityList = query.getResultList();
        return EmployeeEntityList;
    }

    @Override
    public Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity) {

        Query query = entityManager.createQuery("select e from EmployeeEntity e where e.employeeRole = :categoryElementEntity and e.active = true");
        query.setParameter("categoryElementEntity", categoryElementEntity);
        List<EmployeeEntity> employeeEntityList = query.getResultList();

        Map<Long, String> employeeEntityMap = new HashMap<>();

        for (EmployeeEntity employeeEntity : employeeEntityList) {
            String fullName = employeeEntity.getFirstName() + " " + employeeEntity.getLastName();
            employeeEntityMap.put(employeeEntity.getId(), fullName);
        }

        return employeeEntityMap;
    }

    @Override
    public List<EmployeeEntity> findByEmployeeName(String name) {
        Query query = entityManager.createQuery("select e from EmployeeEntity e where e.firstName = :name");
        query.setParameter("name", name);
        List<EmployeeEntity> employeeEntityList = query.getResultList();
        return employeeEntityList;
    }

    @Override
    public List<EmployeeEntity> findEmployeeForSelect2(String name) {
        Query query = entityManager.createQuery("select e from EmployeeEntity e where (e.firstName like :name or e.lastName like :name) and e.active = true");
        query.setParameter("name", "%" + name +"%");
        return query.getResultList();
    }

}
