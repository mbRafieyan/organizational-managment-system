package dao;

import model.CategoryElementEntity;
import model.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeEntityDao {

     void insert(EmployeeEntity employeeEntity);

     void update(EmployeeEntity employeeEntity);

     List<EmployeeEntity> selectAll();

     EmployeeEntity selectById(long id);

     void delete(EmployeeEntity employeeEntity);

     Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity);
}
