package com.service;

import com.model.CategoryElementEntity;
import com.model.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeEntityService {

    void addEmployeeEntity(EmployeeEntity employeeEntity);

    void updateEmployeeEntity(EmployeeEntity employeeEntity, EmployeeEntity oldEmployeeEntity);

    void deleteEmployeeEntity(EmployeeEntity employeeEntity);

    List<EmployeeEntity> getEmployeeEntities();

    EmployeeEntity getEmployeeEntityById(long id);

    void insertAdminEmployee(EmployeeEntity employeeEntity, CategoryElementEntity categoryElementEntity);

    List<EmployeeEntity> findByEmployeeName(String name);

    List<EmployeeEntity> findByManager(EmployeeEntity employeeEntity);

    Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity);

    List<EmployeeEntity> findEmployeeForSelect2(String name);

}
