package repository;


import model.VacationsEntity;

import java.util.List;

public interface IVocationEntityCRUD {

    void insert(VacationsEntity vacationsEntity);

    void update(VacationsEntity vacationsEntity);

    List<VacationsEntity> selectAll();

    VacationsEntity selectById(long id);

    void delete(VacationsEntity vacationsEntity);
}
