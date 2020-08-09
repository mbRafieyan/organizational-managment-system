package com.repository;

import com.model.VacationsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class VocationEntityCRUDImpl implements IVocationEntityCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(VacationsEntity vacationsEntity) {

        vacationsEntity.setActive(true);
        vacationsEntity.setCreateDate(new Date().toString());
        vacationsEntity.setVersion(1);
        entityManager.persist(vacationsEntity);
    }

    @Override
    public void update(VacationsEntity vacationsEntity) {

        vacationsEntity.setVersion(vacationsEntity.getVersion()+1);
        vacationsEntity.setModifiedDate(new Date().toString());
        entityManager.merge(vacationsEntity);
    }

    @Override
    public List<VacationsEntity> selectAll() {
        Query query = entityManager.createQuery("select v from VacationsEntity v where v.active = true ");
        List<VacationsEntity> VacationsEntityList = query.getResultList();
        return VacationsEntityList;
    }

    @Override
    public VacationsEntity selectById(long id) {
        return entityManager.find(VacationsEntity.class, id);
    }

    @Override
    public void delete(VacationsEntity vacationsEntity) {

        vacationsEntity.setActive(false);
        vacationsEntity.setModifiedDate(new Date().toString());
        vacationsEntity.setVersion(vacationsEntity.getVersion()+1);

        entityManager.merge(vacationsEntity);
    }
}
