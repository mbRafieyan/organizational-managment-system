package dao;

import model.EmailEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EmailEntityDaoImpl implements EmailEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(EmailEntity emailEntity) {
        entityManager.persist(emailEntity);
    }
}
