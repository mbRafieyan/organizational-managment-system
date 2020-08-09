package com.service;

import com.model.CategoryElementEntity;
import com.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.ICategoryElementEntityCRUD;

import java.util.List;
import java.util.Map;

@Service
public class CategoryElementEntityServiceImpl implements ICategoryElementEntityService {

    @Autowired
    private ICategoryElementEntityCRUD ICategoryElementEntityCRUD;

    @Override
    public CategoryElementEntity findCategoryElementById(long id) {
        return ICategoryElementEntityCRUD.findCategoryElementById(id);
    }

    @Override
    public void insertAllCategoryElement(CategoryEntity categoryEntity) {
        ICategoryElementEntityCRUD.insertAllCategoryElement(categoryEntity);
    }

    @Override
    public Map<Long, String> findByCategory(CategoryEntity categoryEntity) {
        Map<Long, String> categoryElementEntityMap = ICategoryElementEntityCRUD.findByCategory(categoryEntity);
        return categoryElementEntityMap;
    }

    @Override
    public List<CategoryElementEntity> findCategoryElementEntityByName(String name) {
        List<CategoryElementEntity> categoryElementEntityList = ICategoryElementEntityCRUD.findCategoryElementByName(name);
        return categoryElementEntityList;
    }
}
