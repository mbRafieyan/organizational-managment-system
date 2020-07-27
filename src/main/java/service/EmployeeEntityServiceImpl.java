package service;

import dao.EmployeeEntityDao;
import model.CategoryElementEntity;
import model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeEntityServiceImpl implements EmployeeEntityService {

    @Autowired
    private EmployeeEntityDao employeeEntityDao;

    @Override
    public void addEmployeeEntity(EmployeeEntity employeeEntity) {
        employeeEntityDao.insert(employeeEntity);
    }

    @Override
    public void updateEmployeeEntity(EmployeeEntity employeeEntity) {
        employeeEntityDao.update(employeeEntity);
    }

    @Override
    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntityDao.selectAll();
    }

    @Override
    public EmployeeEntity getEmployeeEntityById(long id) {
        return employeeEntityDao.selectById(id);
    }

    @Override
    public void deleteEmployeeEntity(EmployeeEntity employeeEntity) {
        employeeEntityDao.delete(employeeEntity);
    }

    @Override
    public Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity) {
        Map<Long, String> employeeEntityList = employeeEntityDao.findByEmployeeRole(categoryElementEntity);
        return employeeEntityList;
    }
}
