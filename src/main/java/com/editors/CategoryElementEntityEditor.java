package com.editors;

import com.model.CategoryElementEntity;
import com.service.ICategoryElementEntityService;

import java.beans.PropertyEditorSupport;

public class CategoryElementEntityEditor extends PropertyEditorSupport {

    private ICategoryElementEntityService iCategoryElementEntityService;

    public CategoryElementEntityEditor() {
    }

    public CategoryElementEntityEditor(ICategoryElementEntityService iCategoryElementEntityService) {
        this.iCategoryElementEntityService = iCategoryElementEntityService;
    }

    @Override
    public String getAsText() {
        CategoryElementEntity value = (CategoryElementEntity) getValue();
        return value != null ? String.valueOf(value.getId()) : "";
    }

    @Override
    public void setAsText(String id) {
        setValue((id == null || id.equals("")) ? null : iCategoryElementEntityService.findCategoryElementById(Long.valueOf(id)));
    }
}
