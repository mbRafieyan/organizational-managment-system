package com.repository;

import com.model.EmailEntity;
import com.model.EmployeeEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.*;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class EmailEntityCRUDImpl implements IEmailEntityCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager blobEntityManager;

    public static final Logger logger = Logger.getLogger(EmailEntityCRUDImpl.class);

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

    @Override
    public EmailEntity findEmailById(long emailId) {
        return entityManager.find(EmailEntity.class, emailId);
    }

    @Override
    public void deleteEmailEntity(EmailEntity emailEntity) {
        Query nativeQuery = entityManager.createNativeQuery("UPDATE T_EMAIL SET C_ACTIVE= :active, C_VERSION= :version, C_MODIFIEDDATE= :date WHERE ID= :id");
        nativeQuery.setParameter("active", '0');
        nativeQuery.setParameter("version", emailEntity.getVersion() + 1);
        nativeQuery.setParameter("date", new Date().toString());
        nativeQuery.setParameter("id", emailEntity.getId());

        nativeQuery.executeUpdate();
    }

    @Override
    public  byte[] getEmailFile(EmailEntity emailEntity) {

        try {
            Query query = blobEntityManager.createQuery("select e.attachment from EmailEntity e where e.id = :id");
            query.setParameter("id", emailEntity.getId());
            List<Blob> resultList = query.getResultList();
            Blob blob = resultList.get(0);
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return bytes;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
