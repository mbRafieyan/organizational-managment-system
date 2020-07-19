package service;

import model.EmployeeEntity;

import java.util.List;

public interface EmployeeEntityService {

     void addEmployeeEntity(EmployeeEntity employeeEntity);

     void updateEmployeeEntity(EmployeeEntity employeeEntity);

     List<EmployeeEntity> getEmployeeEntities();

     EmployeeEntity getEmployeeEntityById(long id);

     void deleteEmployeeEntity(EmployeeEntity employeeEntity);
}
