package service;

import dao.CategoryElementEntityDao;
import model.CategoryElementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryElementEntityServiceImpl implements CategoryElementEntityService {

    @Autowired
    private CategoryElementEntityDao categoryElementEntityDao;

    @Override
    @Transactional
    public void addCategoryElementEntity(CategoryElementEntity categoryElementEntity) {
        categoryElementEntityDao.insert(categoryElementEntity);
    }

    @Override
    @Transactional
    public void updateCategoryElementEntity(CategoryElementEntity categoryElementEntity) {
        categoryElementEntityDao.update(categoryElementEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryElementEntity> getCategoryElementEntities() {
        return categoryElementEntityDao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryElementEntity getCategoryElementEntityById(long id) {
        return categoryElementEntityDao.selectById(id);
    }

    @Override
    @Transactional
    public void deleteCategoryElementEntity(CategoryElementEntity categoryElementEntity) {
        categoryElementEntityDao.delete(categoryElementEntity);
    }
}
