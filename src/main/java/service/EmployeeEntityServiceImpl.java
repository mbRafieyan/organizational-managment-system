package service;

import dao.EmployeeEntityDao;
import model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeEntityServiceImpl implements EmployeeEntityService {

    @Autowired
    private EmployeeEntityDao employeeEntityDao;

    @Override
    @Transactional
    public void addEmployeeEntity(EmployeeEntity employeeEntity) {
        employeeEntityDao.insert(employeeEntity);
    }

    @Override
    @Transactional
    public void updateEmployeeEntity(EmployeeEntity employeeEntity) {
        employeeEntityDao.update(employeeEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntityDao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeEntity getEmployeeEntityById(long id) {
        return employeeEntityDao.selectById(id);
    }

    @Override
    @Transactional
    public void deleteEmployeeEntity(EmployeeEntity employeeEntity) {
        employeeEntityDao.delete(employeeEntity);
    }
}
