package dao;

import model.EmployeeEntity;

import java.util.List;

public interface EmployeeEntityDao {

     void insert(EmployeeEntity employeeEntity);

     void update(EmployeeEntity employeeEntity);

     List<EmployeeEntity> selectAll();

     EmployeeEntity selectById(long id);

     void delete(EmployeeEntity employeeEntity);
}
