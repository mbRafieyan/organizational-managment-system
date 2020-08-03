package service;

import model.CategoryElementEntity;
import model.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeEntityService {

    void addEmployeeEntity(EmployeeEntity employeeEntity);

    void updateEmployeeEntity(EmployeeEntity employeeEntity, EmployeeEntity oldEmployeeEntity);

    void deleteEmployeeEntity(EmployeeEntity employeeEntity);

    List<EmployeeEntity> getEmployeeEntities();

    EmployeeEntity getEmployeeEntityById(long id);

    List<EmployeeEntity>  findByManager(EmployeeEntity employeeEntity);

    Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity);
}
