package com.repository;


import com.model.EmailEntity;
import com.model.EmployeeEntity;

import java.util.List;

public interface IEmailEntityCRUD {

    void insert(EmailEntity emailEntity);

    List<EmailEntity> findSentEmailBySenderEmployee(EmployeeEntity senderEmployee);

    List<EmailEntity> findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee);

    EmailEntity findEmailById(long emailId);

    void deleteEmailEntity(EmailEntity emailEntity);

    byte[] getEmailFile(EmailEntity emailEntity);
}
