package repository;

import model.EmailEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class EmailEntityCRUDImpl implements IEmailEntityCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(EmailEntity emailEntity) {
        entityManager.persist(emailEntity);
    }
}
