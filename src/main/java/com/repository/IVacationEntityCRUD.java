package com.repository;


import com.model.EmployeeEntity;
import com.model.VacationsEntity;

import java.util.List;

public interface IVacationEntityCRUD {

    void insert(VacationsEntity vacationsEntity);

    void update(VacationsEntity vacationsEntity);

    List<VacationsEntity> selectAll();

    VacationsEntity selectById(long id);

    void delete(VacationsEntity vacationsEntity);

    int findByEmployee_Date(VacationsEntity vacationsEntity);
}
