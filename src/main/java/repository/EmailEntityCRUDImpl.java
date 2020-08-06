package repository;

import model.EmailEntity;
import model.EmployeeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class EmailEntityCRUDImpl implements IEmailEntityCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(EmailEntity emailEntity) {

        emailEntity.setActive(true);
        emailEntity.setVersion(1);
        emailEntity.setCreateDate(new Date().toString());
        entityManager.persist(emailEntity);
    }

    @Override
    public List<EmailEntity> findSentEmailBySenderEmployee(EmployeeEntity senderEmployee) {
        Query query = entityManager.createQuery("select e from EmailEntity e where e.senderEmployee = :senderEmployee and e.active = true");
        query.setParameter("senderEmployee", senderEmployee);
        List<EmailEntity> emailEntityList = query.getResultList();
        return emailEntityList;
    }

    @Override
    public List<EmailEntity> findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee) {
        Query query = entityManager.createQuery("select e from EmailEntity e where :receiverEmployee member e.recievers");
        query.setParameter("receiverEmployee", receiverEmployee);
        List<EmailEntity> emailEntityList = query.getResultList();
        return emailEntityList;
    }

}
