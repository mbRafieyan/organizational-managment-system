package controller;

import model.CategoryElementEntity;
import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CategoryElementEntityService;
import service.CategoryEntityService;
import service.EmployeeEntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeEntityService employeeEntityService;

    @Autowired
    private CategoryEntityService categoryEntityService;

    @Autowired
    private CategoryElementEntityService categoryElementEntityService;

    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }

    @RequestMapping(value = "/addEmployee", method= RequestMethod.GET)
    public String addEmployee(Model model, HttpServletRequest request){

       /* List<CategoryEntity> employeeRoleList = categoryEntityService.findByCategoryName("employeeRole");
        CategoryEntity roleCategory = employeeRoleList.get(0);
        List<CategoryElementEntity> roleCategoryElementList = categoryElementEntityService.findByCategory(roleCategory);

        Model roleCategoryElementList1 = model.addAllAttributes("roleCategoryElementList", roleCategoryElementList);*/

        return "updateEmployee";
    }
}
