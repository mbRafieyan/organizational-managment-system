package controller;

import model.CategoryElementEntity;
import model.CategoryEntity;
import model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ICategoryElementEntityService;
import service.ICategoryEntityService;
import service.IEmployeeEntityService;

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
