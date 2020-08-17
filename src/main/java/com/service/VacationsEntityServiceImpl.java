package com.service;

import com.model.VacationsEntity;
import com.repository.IVacationEntityCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacationsEntityServiceImpl implements IVacationsEntityService {

    @Autowired
    private IVacationEntityCRUD iVacationEntityCRUD;

    @Override
    @Transactional
    public String addVacationsEntity(VacationsEntity vacationsEntity) {

        String massage = "";
        int overlapCount = iVacationEntityCRUD.findByEmployee_Date(vacationsEntity);

        if ( overlapCount == 0) {
            iVacationEntityCRUD.insert(vacationsEntity);
        } else {
            massage = "failed";
        }
        return massage;
    }

    @Override
    @Transactional
    public void updateVacationsEntity(VacationsEntity vacationsEntity) {
        iVacationEntityCRUD.update(vacationsEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacationsEntity> getVacationsEntities() {
        return iVacationEntityCRUD.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public VacationsEntity getVacationsEntityById(long id) {
        return iVacationEntityCRUD.selectById(id);
    }

    @Override
    @Transactional
    public void deleteVacationsEntity(VacationsEntity vacationsEntity) {
        iVacationEntityCRUD.delete(vacationsEntity);
    }

    @Override
    @Transactional
    public int findByEmployee_Date(VacationsEntity vacationsEntity) {
        return iVacationEntityCRUD.findByEmployee_Date(vacationsEntity);
    }
}
