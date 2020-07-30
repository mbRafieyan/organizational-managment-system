package service;

import model.VacationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.IVocationEntityCRUD;

import java.util.List;

@Service
public class VacationsEntityServiceImpl implements IVacationsEntityService {

    @Autowired
    private IVocationEntityCRUD IVocationEntityCRUD;

    @Override
    @Transactional
    public void addVacationsEntity(VacationsEntity vacationsEntity) {
        IVocationEntityCRUD.insert(vacationsEntity);
    }

    @Override
    @Transactional
    public void updateVacationsEntity(VacationsEntity vacationsEntity) {
        IVocationEntityCRUD.update(vacationsEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacationsEntity> getVacationsEntities() {
        return IVocationEntityCRUD.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public VacationsEntity getVacationsEntityById(long id) {
        return IVocationEntityCRUD.selectById(id);
    }

    @Override
    @Transactional
    public void deleteVacationsEntity(VacationsEntity vacationsEntity) {
        IVocationEntityCRUD.delete(vacationsEntity);
    }
}
