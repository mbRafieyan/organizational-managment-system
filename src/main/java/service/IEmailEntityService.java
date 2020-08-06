package service;


import model.EmailEntity;
import model.EmployeeEntity;

import java.util.List;

public interface IEmailEntityService {

    void sendEmailEntity(EmailEntity emailEntity);

    List<EmailEntity> findSentEmailBySenderEmployee(EmployeeEntity senderEmployee);

    List<EmailEntity>  findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee);

}
