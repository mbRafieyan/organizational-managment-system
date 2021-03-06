package com.service;


import com.model.VacationsEntity;

import java.util.List;

public interface IVacationsEntityService {

    String addVacationsEntity(VacationsEntity vacationsEntity);

    void updateVacationsEntity(VacationsEntity vacationsEntity);

    List<VacationsEntity> getVacationsEntities();

    VacationsEntity getVacationsEntityById(long id);

    void deleteVacationsEntity(VacationsEntity vacationsEntity);

    int findByEmployee_Date(VacationsEntity vacationsEntity);

}
