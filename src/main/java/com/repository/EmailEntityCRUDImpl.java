package com.repository;

import com.model.EmailEntity;
import com.model.EmployeeEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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

        Query emailNativeQuery = entityManager.createNativeQuery("INSERT INTO t_email(ID, C_ACTIVE, C_CREATEDATE, C_VERSION, C_ATTACHMENT, C_SUBJECT, C_TEXT, C_SENDERID) " +
                "VALUES(:ID, :C_ACTIVE, :C_CREATEDATE, :C_VERSION, :C_ATTACHMENT, :C_SUBJECT, :C_TEXT, :C_SENDERID)");
        emailNativeQuery.setParameter("ID", emailEntity.getId());
        emailNativeQuery.setParameter("C_ACTIVE", 1);
        emailNativeQuery.setParameter("C_CREATEDATE", new Date().toString());
        emailNativeQuery.setParameter("C_VERSION", "1");
        emailNativeQuery.setParameter("C_ATTACHMENT", emailEntity.getAttachment());
        emailNativeQuery.setParameter("C_SUBJECT", emailEntity.getSubject());
        emailNativeQuery.setParameter("C_TEXT", emailEntity.getText());
        emailNativeQuery.setParameter("C_SENDERID", emailEntity.getSenderEmployee().getId());
        emailNativeQuery.executeUpdate();

        List<EmployeeEntity> recievers = emailEntity.getRecievers();

        for (EmployeeEntity reciever :recievers) {
            Query recieverNativeQuery = entityManager.createNativeQuery("INSERT INTO t_reciever(C_EMAILID, C_EMPLOYEEID) VALUES((SELECT ID FROM t_email WHERE ID=LAST_INSERT_ID()), :C_RECIEVER)");
            recieverNativeQuery.setParameter("C_RECIEVER", reciever.getId());
            recieverNativeQuery.executeUpdate();
        }
    }

    @Override
    public List<EmailEntity> findSentEmailBySenderEmployee(EmployeeEntity senderEmployee) {
        Query query = entityManager.createQuery("select e from EmailEntity e where e.senderEmployee.id = :senderEmployeeId and e.active = true");
        query.setParameter("senderEmployeeId", senderEmployee.getId());
        List<EmailEntity> emailEntityList = query.getResultList();
        return emailEntityList;
    }

    @Override
    public List<EmailEntity> findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee) {
        Query query = entityManager.createQuery("select e from EmailEntity e where :receiverEmployee member e.recievers and e.active = true");
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
        Query nativeQuery = entityManager.createNativeQuery("UPDATE t_email SET C_ACTIVE= :active, C_VERSION= :version, C_MODIFIEDDATE= :date WHERE ID= :id");
        nativeQuery.setParameter("active", 0);
        nativeQuery.setParameter("version", emailEntity.getVersion() + 1);
        nativeQuery.setParameter("date", new Date().toString());
        nativeQuery.setParameter("id", emailEntity.getId());

        nativeQuery.executeUpdate();
    }

    @Override
    public byte[] getEmailFile(EmailEntity emailEntity) {

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
