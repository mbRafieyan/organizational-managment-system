package com.editors;

import com.model.EmployeeEntity;
import com.service.IEmployeeEntityService;

import java.beans.PropertyEditorSupport;

public class EmployeeEntityEditor extends PropertyEditorSupport {

    private IEmployeeEntityService iEmployeeEntityService;

    public EmployeeEntityEditor() {
    }

    public EmployeeEntityEditor(IEmployeeEntityService iEmployeeEntityService) {
        this.iEmployeeEntityService = iEmployeeEntityService;
    }

    @Override
    public String getAsText() {
        EmployeeEntity value = (EmployeeEntity) getValue();
        return value != null ? String.valueOf(value.getId()) : "";
    }

    @Override
    public void setAsText(String id) {
        setValue((id == null || id.equals("")) ? null : iEmployeeEntityService.getEmployeeEntityById(Long.valueOf(id)));
    }
}
