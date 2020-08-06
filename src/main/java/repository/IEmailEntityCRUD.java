package repository;


import model.EmailEntity;
import model.EmployeeEntity;

import java.util.List;

public interface IEmailEntityCRUD {

    void insert(EmailEntity emailEntity);

    List<EmailEntity>  findSentEmailBySenderEmployee(EmployeeEntity senderEmployee);

    List<EmailEntity>  findInboxEmailByReceiverEmployee(EmployeeEntity receiverEmployee);

}
