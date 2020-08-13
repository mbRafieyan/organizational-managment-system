package com.repository;


import com.model.EmailEntity;
import com.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.InputStream;
import java.util.List;

public interface IEmailEntityCRUD {

    void insert(EmailEntity emailEntity);

    List<EmailEntity>  findSentEmailBySenderEmployee(EmployeeEntity senderEmployee);

    List<EmailEntity>  findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee);

    EmailEntity findEmailById(long emailId);

    void deleteEmailEntity(EmailEntity emailEntity);

    byte[] getEmailFile(EmailEntity emailEntity);
}
