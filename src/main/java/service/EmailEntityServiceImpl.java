package service;

import model.EmailEntity;
import model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.IEmailEntityCRUD;

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
}
