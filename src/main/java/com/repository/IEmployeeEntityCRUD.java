package com.repository;

import com.model.CategoryElementEntity;
import com.model.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeEntityCRUD {

    void insert(EmployeeEntity employeeEntity);

    void update(EmployeeEntity employeeEntity, EmployeeEntity oldEmployeeEntity);

    void delete(EmployeeEntity employeeEntity);

    List<EmployeeEntity> selectAll();

    EmployeeEntity selectById(long id);

    void insertAdminEmployee(EmployeeEntity employeeEntity, CategoryElementEntity categoryElementEntity);

    List<EmployeeEntity> findByManager(EmployeeEntity employeeEntity);

    Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity);

    List<EmployeeEntity> findByEmployeeName(String name);

    List<EmployeeEntity> findEmployeeForSelect2(String name);

}
