package com.service;

import com.model.EmailEntity;
import com.model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.repository.IEmailEntityCRUD;

import java.io.InputStream;
import java.util.List;

@Service
public class EmailEntityServiceImpl implements IEmailEntityService {


    @Autowired
    private IEmailEntityCRUD iEmailEntityCRUD;

    @Override
    @Transactional
    public void sendEmailEntity(EmailEntity emailEntity) {
        iEmailEntityCRUD.insert(emailEntity);
    }

    @Override
    public List<EmailEntity> findSentEmailBySenderEmployee(EmployeeEntity senderEmployee) {
        return iEmailEntityCRUD.findSentEmailBySenderEmployee(senderEmployee);
    }

    @Override
    public List<EmailEntity> findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee) {
        return iEmailEntityCRUD.findInboxEmailByReceiverEmployee(receiverEmployee);
    }

    @Override
    public EmailEntity findEmailById(long emailId) {
        return iEmailEntityCRUD.findEmailById(emailId);
    }

    @Override
    public void deleteEmailEntity(EmailEntity emailEntity) {
        iEmailEntityCRUD.deleteEmailEntity(emailEntity);
    }

    @Override
    public byte[] getEmailFile(EmailEntity emailEntity) {
        return iEmailEntityCRUD.getEmailFile(emailEntity);
    }

}
