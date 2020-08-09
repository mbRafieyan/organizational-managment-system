package com.service;

import com.model.CategoryElementEntity;
import com.model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.repository.IEmployeeEntityCRUD;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeEntityServiceImpl implements IEmployeeEntityService {

    @Autowired
    private IEmployeeEntityCRUD iEmployeeEntityCRUD;

    @Override
    public void addEmployeeEntity(EmployeeEntity employeeEntity) {
        iEmployeeEntityCRUD.insert(employeeEntity);
    }

    @Override
    public void updateEmployeeEntity(EmployeeEntity employeeEntity, EmployeeEntity oldEmployeeEntity) {
        iEmployeeEntityCRUD.update(employeeEntity, oldEmployeeEntity);
    }

    @Override
    public List<EmployeeEntity> getEmployeeEntities() {
        return iEmployeeEntityCRUD.selectAll();
    }

    @Override
    public EmployeeEntity getEmployeeEntityById(long id) {
        return iEmployeeEntityCRUD.selectById(id);
    }

    @Override
    public void insertAdminEmployee(EmployeeEntity employeeEntity, CategoryElementEntity categoryElementEntity) {
        iEmployeeEntityCRUD.insertAdminEmployee(employeeEntity, categoryElementEntity);
    }

    @Override
    public List<EmployeeEntity> findByEmployeeName(String name) {
        return iEmployeeEntityCRUD.findByEmployeeName(name);
    }

    @Override
    public List<EmployeeEntity> findByManager(EmployeeEntity employeeEntity) {
        List<EmployeeEntity> childEmployeeList = iEmployeeEntityCRUD.findByManager(employeeEntity);
        return childEmployeeList;
    }

    @Override
    public void deleteEmployeeEntity(EmployeeEntity employeeEntity) {
        iEmployeeEntityCRUD.delete(employeeEntity);
    }

    @Override
    public Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity) {
        Map<Long, String> employeeEntityList = iEmployeeEntityCRUD.findByEmployeeRole(categoryElementEntity);
        return employeeEntityList;
    }

    @Override
    public List<EmployeeEntity> findEmployeeForSelect2(String name) {
        return iEmployeeEntityCRUD.findEmployeeForSelect2(name);
    }
}
