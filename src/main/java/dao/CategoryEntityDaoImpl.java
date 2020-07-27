package dao;

import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CategoryEntityDaoImpl implements CategoryEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Environment env;

    @Override
    public List<CategoryEntity> selectAllCategory() {
        Query query = entityManager.createQuery("select c from CategoryEntity c");
        return query.getResultList();
    }

    @Override
    public List<CategoryEntity> findByCategoryName(String name) {
        Query query = entityManager.createQuery("select c from CategoryEntity c where c.categoryName =:name");
        query.setParameter("name", name);

        return (List<CategoryEntity>) query.getResultList();
    }

    @Override
    public Map<Long, CategoryEntity> insertAllCategory() {

        Map<Long, CategoryEntity> categoryMap = new HashMap<>();

        String categoryNamesStr = env.getProperty("category.name");
        String[] categoryNameArray = categoryNamesStr.split(",");

        for (String name : categoryNameArray) {

            CategoryEntity categoryEntity = new CategoryEntity();

            categoryEntity.setCategoryName(name);
            categoryEntity.setVersion(1.0F);
            categoryEntity.setActive(true);
            categoryEntity.setCreateDate(new Date());

            entityManager.persist(categoryEntity);
            categoryMap.put(categoryEntity.getId(), categoryEntity);
        }

        return categoryMap;
    }
}
