package com.service;

import com.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.ICategoryEntityCRUD;

import java.util.List;
import java.util.Map;

@Service
public class CategoryEntityServiceImpl implements ICategoryEntityService {

    @Autowired
    private ICategoryEntityCRUD iCategoryEntityCRUD;

    @Override
    public List<CategoryEntity> selectAllCategory() {
        return iCategoryEntityCRUD.selectAllCategory();
    }

    @Override
    public List<CategoryEntity> findByCategoryName(String name) {
        return iCategoryEntityCRUD.findByCategoryName(name);
    }

    @Override
    public Map<Long, CategoryEntity> insertAllCategory() {
        return iCategoryEntityCRUD.insertAllCategory();
    }

    @Override
    public CategoryEntity findByCategoryId(long id) {
        return iCategoryEntityCRUD.findByCategoryId(id);
    }

}
