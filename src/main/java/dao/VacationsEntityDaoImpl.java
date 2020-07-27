package dao;

import model.VacationsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class VacationsEntityDaoImpl implements VacationsEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(VacationsEntity vacationsEntity) {
        entityManager.persist(vacationsEntity);
    }

    @Override
    public void update(VacationsEntity vacationsEntity) {
        entityManager.merge(vacationsEntity);
    }

    @Override
    public List<VacationsEntity> selectAll() {
        Query query = entityManager.createQuery("select c from VacationsEntity c");
        return (List<VacationsEntity>) query.getResultList();
    }

    @Override
    public VacationsEntity selectById(long id) {
        return entityManager.find(VacationsEntity.class, id);
    }

    @Override
    public void delete(VacationsEntity vacationsEntity) {
        entityManager.remove(vacationsEntity);
    }
}
