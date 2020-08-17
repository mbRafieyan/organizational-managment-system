package com.repository;

import com.model.CategoryElementEntity;
import com.model.CategoryEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Repository
@Transactional
@PropertySources({@PropertySource("classpath:application.properties"), @PropertySource("classpath:language_fa_IR.properties")})
public class CategoryElementEntityCRUDImpl implements ICategoryElementEntityCRUD {

    public static final Logger logger = Logger.getLogger(CategoryElementEntityCRUDImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Environment env;

    @Override
    public CategoryElementEntity findCategoryElementById(long id) {
        return entityManager.find(CategoryElementEntity.class, id);
    }

    @Override
    public void insertAllCategoryElement(CategoryEntity categoryEntity) {

        String property = "categoryElement." + categoryEntity.getCategoryName();
        String categoryElementsStr = env.getProperty(property);
        String[] categoryElementsArray = categoryElementsStr.split(",");

        Locale.setDefault(new Locale("fa", "IR"));
        ResourceBundle bundle = ResourceBundle.getBundle("language");

        for (String categoryElementName : categoryElementsArray) {

            CategoryElementEntity categoryElementEntity = new CategoryElementEntity();

            categoryElementEntity.setCategoryEntity(categoryEntity);
            categoryElementEntity.setCode(categoryElementName);
            try {
                categoryElementEntity.setName(new String(bundle.getString(categoryElementName).getBytes("ISO-8859-1")));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
            categoryElementEntity.setActive(true);
            categoryElementEntity.setVersion(1);
            categoryElementEntity.setCreateDate(new Date().toString());

            this.entityManager.persist(categoryElementEntity);
        }
    }

    @Override
    public List<CategoryElementEntity> findByCategory(CategoryEntity categoryEntity) {

        Query query = entityManager.createQuery("select c from CategoryElementEntity c where c.categoryEntity =:categoryEntity");
        query.setParameter("categoryEntity", categoryEntity);

        List<CategoryElementEntity> categoryElementEntities = query.getResultList();
        List<CategoryElementEntity> categoryElementEntityList = new ArrayList<>();
        for (CategoryElementEntity ce : categoryElementEntities) {
            if (!ce.getName().equals("administrator")) {
                categoryElementEntityList.add(ce);
            }
        }
        return categoryElementEntityList;
    }

    @Override
    public List<CategoryElementEntity> findCategoryElementByName(String name) {

        Query query = entityManager.createQuery("select c from CategoryElementEntity c where c.code like :name");
        query.setParameter("name", "%" + name + "%");
        List<CategoryElementEntity> CategoryElementEntityList = query.getResultList();
        return CategoryElementEntityList;
    }
}
