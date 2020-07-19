package service;

import dao.EmailEntityDao;
import model.EmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailEntityServiceImpl implements EmailEntityService {

    @Autowired
    private EmailEntityDao emailEntityDao;

    @Override
    @Transactional
    public void addEmailEntity(EmailEntity emailEntity) {
        emailEntityDao.insert(emailEntity);
    }
}
