package repository;

import model.CategoryElementEntity;
import model.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeEntityCRUD {

    void insert(EmployeeEntity employeeEntity);

    void update(EmployeeEntity employeeEntity);

    void delete(EmployeeEntity employeeEntity);

    List<EmployeeEntity> selectAll();

    EmployeeEntity selectById(long id);

    List<EmployeeEntity>  findByManager(EmployeeEntity employeeEntity);

    Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity);
}
