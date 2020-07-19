package service;


import model.VacationsEntity;

import java.util.List;

public interface VacationsEntityService {

     void addVacationsEntity(VacationsEntity vacationsEntity);

     void updateVacationsEntity(VacationsEntity vacationsEntity);

     List<VacationsEntity> getVacationsEntities();

     VacationsEntity getVacationsEntityById(long id);

     void deleteVacationsEntity(VacationsEntity vacationsEntity);
}