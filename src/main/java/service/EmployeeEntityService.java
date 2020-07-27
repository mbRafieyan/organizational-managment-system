package service;

import model.CategoryElementEntity;
import model.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeEntityService {

     void addEmployeeEntity(EmployeeEntity employeeEntity);

     void updateEmployeeEntity(EmployeeEntity employeeEntity);

     List<EmployeeEntity> getEmployeeEntities();

     EmployeeEntity getEmployeeEntityById(long id);

     void deleteEmployeeEntity(EmployeeEntity employeeEntity);

     Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity);
}
