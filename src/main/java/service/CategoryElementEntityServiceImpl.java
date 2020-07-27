package service;

import dao.CategoryElementEntityDao;
import model.CategoryElementEntity;
import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryElementEntityServiceImpl implements CategoryElementEntityService {

    @Autowired
    private CategoryElementEntityDao categoryElementEntityDao;

    @Override
    public void insertAllCategoryElement(CategoryEntity categoryEntity) {
        categoryElementEntityDao.insertAllCategoryElement(categoryEntity);
    }

    @Override
    public Map<Long, String> findByCategory(CategoryEntity categoryEntity) {
        Map<Long, String> categoryElementEntityMap = categoryElementEntityDao.findByCategory(categoryEntity);
        return categoryElementEntityMap;
    }

    @Override
    public List<CategoryElementEntity> findByCategoryElementEntityName(String name) {
        List<CategoryElementEntity> categoryElementEntityList = categoryElementEntityDao.findByCategoryElementName(name);
        return categoryElementEntityList;
    }
}
