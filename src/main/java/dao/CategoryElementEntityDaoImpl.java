package dao;

import model.CategoryElementEntity;
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
public class CategoryElementEntityDaoImpl implements CategoryElementEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Environment env;

    @Override
    public void insertAllCategoryElement(CategoryEntity categoryEntity) {

        String property = "categoryElement." + categoryEntity.getCategoryName();
        String categoryElementsStr = env.getProperty(property);

        String[] categoryElementsArray = categoryElementsStr.split(",");

        for (String categoryElementName : categoryElementsArray) {

            CategoryElementEntity categoryElementEntity = new CategoryElementEntity();

            categoryElementEntity.setCategoryEntity(categoryEntity);
            categoryElementEntity.setCode(categoryElementName);
            categoryElementEntity.setName(categoryElementName);
            categoryElementEntity.setActive(true);
            categoryElementEntity.setVersion(1.0F);
            categoryElementEntity.setCreateDate(new Date());

            this.entityManager.persist(categoryElementEntity);
        }
    }

    @Override
    public Map<Long, String> findByCategory(CategoryEntity categoryEntity) {

        Query query = entityManager.createQuery("select c from CategoryElementEntity c where c.categoryEntity =:categoryEntity");
        query.setParameter("categoryEntity", categoryEntity);

        List<CategoryElementEntity> categoryElementEntityList = query.getResultList();

        Map<Long, String> categoryElementEntityMap = new HashMap<>();

        for (CategoryElementEntity ce : categoryElementEntityList) {
            categoryElementEntityMap.put(ce.getId(), ce.getName());
        }
        return categoryElementEntityMap;
    }

    @Override
    public List<CategoryElementEntity> findByCategoryElementName(String name) {

        Query query = entityManager.createQuery("select c from CategoryElementEntity c where c.code like :manager");
        query.setParameter("manager", "%Manager%");
        List<CategoryElementEntity> CategoryElementEntityList = query.getResultList();
        return CategoryElementEntityList;
    }
}
