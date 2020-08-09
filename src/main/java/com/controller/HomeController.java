package com.controller;

import com.model.CategoryElementEntity;
import com.model.CategoryEntity;
import com.model.EmployeeEntity;
import com.service.ICategoryElementEntityService;
import com.service.ICategoryEntityService;
import com.service.IEmployeeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private IEmployeeEntityService iEmployeeEntityService;

    @Autowired
    private ICategoryEntityService iCategoryEntityService;

    @Autowired
    private ICategoryElementEntityService iCategoryElementEntityService;

    @RequestMapping("/")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("home");

        List<CategoryEntity> categoryEntities = iCategoryEntityService.selectAllCategory();

        if (categoryEntities.size() == 0) {
            Map<Long, CategoryEntity> categoryMap = iCategoryEntityService.insertAllCategory();

            for (Map.Entry<Long, CategoryEntity> entry : categoryMap.entrySet()) {
                iCategoryElementEntityService.insertAllCategoryElement(entry.getValue());
            }
        }

        List<EmployeeEntity> admin = iEmployeeEntityService.findByEmployeeName("admin");
        if(admin.size() == 0) {
            //admin employee
            EmployeeEntity employeeEntity = new EmployeeEntity();
            List<CategoryElementEntity> categoryElements = iCategoryElementEntityService.findCategoryElementEntityByName("administrator");
            iEmployeeEntityService.insertAdminEmployee(employeeEntity, categoryElements.get(0));
        }
        return modelAndView;
    }
}
