package com.repository;

import com.model.VacationsEntity;
import com.util.Convertor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class VacationEntityCRUDImpl implements IVacationEntityCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(VacationsEntity vacationsEntity) {

        String faStartDate = vacationsEntity.getVacationStart();
        String faEndDate = vacationsEntity.getVacationEnd();
        String enStartDate = Convertor.persianCharacterToEnglish(faStartDate);
        String enEndDate = Convertor.persianCharacterToEnglish(faEndDate);

        String  GregorianStartDate = Convertor.persianDateToGregorian(enStartDate);
        String  GregorianEndDate = Convertor.persianDateToGregorian(enEndDate);

        vacationsEntity.setVacationStart(GregorianStartDate);
        vacationsEntity.setVacationEnd(GregorianEndDate);
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

    @Override
    public List<VacationsEntity> findByEmployee_Date(VacationsEntity vacationsEntity) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM t_vacations WHERE C_REQUESTEDEMPLOYEEID='employeeId' AND C_ACTIVE='1' AND");
        stringBuilder.append("((UNIX_TIMESTAMP('startDate') >  UNIX_TIMESTAMP(C_VACATIONSTART) AND UNIX_TIMESTAMP('startDate') <  UNIX_TIMESTAMP(C_VACATIONEND))");
        stringBuilder.append("OR(UNIX_TIMESTAMP('endDate') > UNIX_TIMESTAMP(C_VACATIONSTART) AND UNIX_TIMESTAMP('endDate') < UNIX_TIMESTAMP(C_VACATIONEND))");
        stringBuilder.append("OR(UNIX_TIMESTAMP('startDate') >  UNIX_TIMESTAMP(C_VACATIONSTART) AND UNIX_TIMESTAMP('endDate') < UNIX_TIMESTAMP(C_VACATIONEND)))");

        String faStartDate = vacationsEntity.getVacationStart();
        String faEndDate = vacationsEntity.getVacationEnd();
        String enStartDate = Convertor.persianCharacterToEnglish(faStartDate);
        String enEndDate = Convertor.persianCharacterToEnglish(faEndDate);

        String  GregorianStartDate = Convertor.persianDateToGregorian(enStartDate);
        String  GregorianEndDate = Convertor.persianDateToGregorian(enEndDate);

        String queryStr = stringBuilder.toString().replace("employeeId", String.valueOf(vacationsEntity.getEmployeeEntity().getId()))
                .replace("startDate", GregorianStartDate)
                .replace("endDate", GregorianEndDate);

        Query query = entityManager.createNativeQuery(queryStr);
        List<VacationsEntity> resultList = query.getResultList();

        return resultList;
    }
}
