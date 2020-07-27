package service;

import dao.CategoryElementEntityDao;
import model.CategoryElementEntity;
import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryElementEntityServiceImpl implements CategoryElementEntityService {

    @Autowired
    private CategoryElementEntityDao categoryElementEntityDao;

    @Override
    public void insertAllCategoryElement(CategoryEntity categoryEntity) {
        categoryElementEntityDao.insertAllCategoryElement(categoryEntity);
    }

    @Override
    public List<CategoryElementEntity> findByCategory(CategoryEntity categoryEntity) {
        List<CategoryElementEntity> CategoryElementEntityList = categoryElementEntityDao.findByCategory(categoryEntity);
        return CategoryElementEntityList;
    }
}
