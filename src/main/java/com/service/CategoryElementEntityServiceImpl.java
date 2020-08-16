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
    private ICategoryElementEntityCRUD iCategoryElementEntityCRUD;

    @Override
    public CategoryElementEntity findCategoryElementById(long id) {
        return iCategoryElementEntityCRUD.findCategoryElementById(id);
    }

    @Override
    public void insertAllCategoryElement(CategoryEntity categoryEntity) {
        iCategoryElementEntityCRUD.insertAllCategoryElement(categoryEntity);
    }

    @Override
    public List<CategoryElementEntity> findByCategory(CategoryEntity categoryEntity) {
         return iCategoryElementEntityCRUD.findByCategory(categoryEntity);
    }

    @Override
    public List<CategoryElementEntity> findCategoryElementEntityByName(String name) {
        List<CategoryElementEntity> categoryElementEntityList = iCategoryElementEntityCRUD.findCategoryElementByName(name);
        return categoryElementEntityList;
    }
}
