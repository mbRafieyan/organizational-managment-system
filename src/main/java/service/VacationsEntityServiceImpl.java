package service;

import dao.VacationsEntityDao;
import model.VacationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacationsEntityServiceImpl implements VacationsEntityService {

    @Autowired
    private VacationsEntityDao vacationsEntityDao;

    @Override
    @Transactional
    public void addVacationsEntity(VacationsEntity vacationsEntity) {
        vacationsEntityDao.insert(vacationsEntity);
    }

    @Override
    @Transactional
    public void updateVacationsEntity(VacationsEntity vacationsEntity) {
        vacationsEntityDao.update(vacationsEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacationsEntity> getVacationsEntities() {
        return vacationsEntityDao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public VacationsEntity getVacationsEntityById(long id) {
        return vacationsEntityDao.selectById(id);
    }

    @Override
    @Transactional
    public void deleteVacationsEntity(VacationsEntity vacationsEntity) {
        vacationsEntityDao.delete(vacationsEntity);
    }
}
