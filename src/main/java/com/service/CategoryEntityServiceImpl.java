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
    private ICategoryEntityCRUD ICategoryEntityCRUD;

    @Override
    public List<CategoryEntity> selectAllCategory() {
        return ICategoryEntityCRUD.selectAllCategory();
    }

    @Override
    public List<CategoryEntity> findByCategoryName(String name) {
        return ICategoryEntityCRUD.findByCategoryName(name);
    }

    @Override
    public Map<Long, CategoryEntity> insertAllCategory() {
        return ICategoryEntityCRUD.insertAllCategory();
    }

}
