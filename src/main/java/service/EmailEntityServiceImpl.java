package service;

import model.EmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.IEmailEntityCRUD;

@Service
public class EmailEntityServiceImpl implements IEmailEntityService {

    @Autowired
    private IEmailEntityCRUD IEmailEntityCRUD;

    @Override
    @Transactional
    public void addEmailEntity(EmailEntity emailEntity) {
        IEmailEntityCRUD.insert(emailEntity);
    }
}
