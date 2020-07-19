package service;

import dao.CategoryEntityDao;
import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryEntityServiceImpl implements CategoryEntityService {

    @Autowired
    private CategoryEntityDao categoryEntityDao;

    @Override
    @Transactional
    public void addCategoryEntity(CategoryEntity categoryEntity) {
        categoryEntityDao.insert(categoryEntity);
    }

    @Override
    @Transactional
    public void updateCategoryEntity(CategoryEntity categoryEntity) {
        categoryEntityDao.update(categoryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryEntity> getCategoryEntities() {
        return categoryEntityDao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryEntity getCategoryEntityById(long id) {
        return categoryEntityDao.selectById(id);

    }

    @Override
    @Transactional
    public void deleteCategoryEntity(CategoryEntity categoryEntity) {
        categoryEntityDao.delete(categoryEntity);
    }
}
