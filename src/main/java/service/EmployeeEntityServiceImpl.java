package service;

import model.CategoryElementEntity;
import model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.IEmployeeEntityCRUD;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeEntityServiceImpl implements IEmployeeEntityService {

    @Autowired
    private IEmployeeEntityCRUD IEmployeeEntityCRUD;

    @Override
    public void addEmployeeEntity(EmployeeEntity employeeEntity) {
        IEmployeeEntityCRUD.insert(employeeEntity);
    }

    @Override
    public void updateEmployeeEntity(EmployeeEntity employeeEntity, EmployeeEntity oldEmployeeEntity) {
        IEmployeeEntityCRUD.update(employeeEntity, oldEmployeeEntity);
    }

    @Override
    public List<EmployeeEntity> getEmployeeEntities() {
        return IEmployeeEntityCRUD.selectAll();
    }

    @Override
    public EmployeeEntity getEmployeeEntityById(long id) {
        return IEmployeeEntityCRUD.selectById(id);
    }

    @Override
    public List<EmployeeEntity> findByManager(EmployeeEntity employeeEntity) {
        List<EmployeeEntity> childEmployeeList = IEmployeeEntityCRUD.findByManager(employeeEntity);
        return childEmployeeList;
    }

    @Override
    public void deleteEmployeeEntity(EmployeeEntity employeeEntity) {
        IEmployeeEntityCRUD.delete(employeeEntity);
    }

    @Override
    public Map<Long, String> findByEmployeeRole(CategoryElementEntity categoryElementEntity) {
        Map<Long, String> employeeEntityList = IEmployeeEntityCRUD.findByEmployeeRole(categoryElementEntity);
        return employeeEntityList;
    }
}
