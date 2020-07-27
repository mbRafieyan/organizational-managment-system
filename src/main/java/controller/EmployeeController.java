package controller;

import model.CategoryElementEntity;
import model.CategoryEntity;
import model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CategoryElementEntityService;
import service.CategoryEntityService;
import service.EmployeeEntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeEntityService employeeEntityService;

    @Autowired
    private CategoryEntityService categoryEntityService;

    @Autowired
    private CategoryElementEntityService categoryElementEntityService;

    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }

    @RequestMapping(value = "/addEmployeeView", method = RequestMethod.GET)
    public String displayAddEmployeeView(Model model) {

        List<CategoryEntity> employeeRoleList = categoryEntityService.findByCategoryName("employeeRole");
        CategoryEntity roleCategory = employeeRoleList.get(0);
        Map<Long, String> roleCategoryElementMap = categoryElementEntityService.findByCategory(roleCategory);

        List<CategoryElementEntity> managerCategoryElementList = categoryElementEntityService.findByCategoryElementEntityName("Manager");

        Map<Long, String>  managerEmployeeEntityMap = new HashMap<>();

        for (CategoryElementEntity ce : managerCategoryElementList) {

            Map<Long, String> employeeEntity = employeeEntityService.findByEmployeeRole(ce);

            for (Map.Entry<Long, String> entry : employeeEntity.entrySet()) {
                managerEmployeeEntityMap.put(entry.getKey(), entry.getValue());
            }
        }

        model.addAttribute("roleCategoryElementMap", roleCategoryElementMap);
        model.addAttribute("managerEmployeeEntityMap", managerEmployeeEntityMap);

        return "updateEmployee";
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public String saveEmployee(EmployeeEntity employeeEntity){
        employeeEntityService.addEmployeeEntity(employeeEntity);
        return "redirect:/employee";
    }
}
