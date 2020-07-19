package dao;

import model.EmailEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmailEntityDaoImpl implements EmailEntityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(EmailEntity emailEntity) {
        sessionFactory.getCurrentSession().persist(emailEntity);
    }
}
