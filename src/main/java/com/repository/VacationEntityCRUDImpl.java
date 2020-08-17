package com.repository;

import com.ibm.icu.text.SimpleDateFormat;
import com.model.VacationsEntity;
import com.util.Convertor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class VacationEntityCRUDImpl implements IVacationEntityCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(VacationsEntity vacationsEntity) {

        String startDate = vacationsEntity.getVacationStart();
        String endDate = vacationsEntity.getVacationEnd();
        String enStartDate = Convertor.persianCharacterToEnglish(startDate);
        String enEndDate = Convertor.persianCharacterToEnglish(endDate);

        vacationsEntity.setVacationStart(enStartDate);
        vacationsEntity.setVacationEnd(enEndDate);
        vacationsEntity.setActive(true);
        vacationsEntity.setCreateDate(new Date().toString());
        vacationsEntity.setVersion(1);
        entityManager.persist(vacationsEntity);
    }

    @Override
    public void update(VacationsEntity vacationsEntity) {

        vacationsEntity.setVersion(vacationsEntity.getVersion() + 1);
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
        vacationsEntity.setVersion(vacationsEntity.getVersion() + 1);

        entityManager.merge(vacationsEntity);
    }

    @Override
    public int findByEmployee_Date(VacationsEntity vacationsEntity) {

        String faStartDate = vacationsEntity.getVacationStart();
        String faEndDate = vacationsEntity.getVacationEnd();
        String enStartDate = Convertor.persianCharacterToEnglish(faStartDate);
        String enEndDate = Convertor.persianCharacterToEnglish(faEndDate);

        Query query = entityManager.createQuery("select v from VacationsEntity v where v.employee.id= :employeeId and v.active=true ");
        query.setParameter("employeeId", vacationsEntity.getEmployee().getId());
        List<VacationsEntity> resultList = query.getResultList();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int overlapCount = 0;
        try {
            Date start = format.parse(enStartDate);
            Date end = format.parse(enEndDate);

            for (VacationsEntity ve : resultList) {

                Date veStart = format.parse(ve.getVacationStart());
                Date veEnd = format.parse(ve.getVacationEnd());

                if ((veStart.after(start) && veStart.before(end))
                        || (veEnd.after(start) && veEnd.before(end))
                        || (veStart.before(start) && veEnd.after(end))) {

                    overlapCount++;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return overlapCount;
    }
}
