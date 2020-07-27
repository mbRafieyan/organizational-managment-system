package service;

import dao.CategoryEntityDao;
import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryEntityServiceImpl implements CategoryEntityService {

    @Autowired
    private CategoryEntityDao categoryEntityDao;

    @Override
    public List<CategoryEntity> selectAllCategory() {
        return categoryEntityDao.selectAllCategory();
    }

    @Override
    public List<CategoryEntity> findByCategoryName(String name) {
        return categoryEntityDao.findByCategoryName(name);
    }

    @Override
    public Map<Long, CategoryEntity> insertAllCategory() {
        return categoryEntityDao.insertAllCategory();
    }

}
