package com.service;


import com.model.EmailEntity;
import com.model.EmployeeEntity;

import java.io.InputStream;
import java.util.List;

public interface IEmailEntityService {

    void sendEmailEntity(EmailEntity emailEntity);

    List<EmailEntity> findSentEmailBySenderEmployee(EmployeeEntity senderEmployee);

    List<EmailEntity>  findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee);

    EmailEntity findEmailById(long emailId);

    void deleteEmailEntity(EmailEntity emailEntity);

    byte[] getEmailFile(EmailEntity emailEntity);
}
