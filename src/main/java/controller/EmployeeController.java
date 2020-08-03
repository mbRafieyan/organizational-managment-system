package controller;

import model.CategoryElementEntity;
import model.CategoryEntity;
import model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.ICategoryElementEntityService;
import service.ICategoryEntityService;
import service.IEmployeeEntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/viewEmployee"})
public class EmployeeController {

    @Autowired
    private IEmployeeEntityService iEmployeeEntityService;

    @Autowired
    private ICategoryEntityService iCategoryEntityService;

    @Autowired
    private ICategoryElementEntityService ICategoryElementEntityService;

    @RequestMapping(value = {"/viewAddEmployee"}, method = RequestMethod.GET)
    public ModelAndView viewAddEmployee() {

        EmployeeEntity employeeEntity = new EmployeeEntity();

        ModelAndView modelAndView = getModelAndView("updateEmployee");
        modelAndView.addObject("employeeEntity", employeeEntity);
        modelAndView.addObject("subject", "Add");

        return modelAndView;
    }

    @RequestMapping(value = {"", "/{page}"}, method = RequestMethod.GET)
    public ModelAndView getAllEmployee(@PathVariable(required = false, name = "page") String page, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("employee");
        modelAndView.addObject("subject", "Add");

        PagedListHolder<EmployeeEntity> employeeEntities;

        if (page == null) {

            employeeEntities = new PagedListHolder<EmployeeEntity>();
            List<EmployeeEntity> employeeEntityList = iEmployeeEntityService.getEmployeeEntities();
            employeeEntities.setSource(employeeEntityList);
            employeeEntities.setPageSize(2);

            request.getSession().setAttribute("employeeEntityList", employeeEntities);

        } else if (page.equals("prev")) {

            employeeEntities = (PagedListHolder<EmployeeEntity>) request.getSession().getAttribute("employeeEntityList");
            employeeEntities.previousPage();

        } else if (page.equals("next")) {

            employeeEntities = (PagedListHolder<EmployeeEntity>) request.getSession().getAttribute("employeeEntityList");
            employeeEntities.nextPage();

        } else {

            int pageNum = Integer.parseInt(page);
            employeeEntities = (PagedListHolder<EmployeeEntity>) request.getSession().getAttribute("employeeEntityList");
            employeeEntities.setPage(pageNum - 1);
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/updateEmployee"}, method = RequestMethod.POST)
    public ModelAndView updateEmployee(HttpServletRequest request, @ModelAttribute("employeeEntity") EmployeeEntity employeeEntity) {

        ModelAndView modelAndView = getModelAndView("employee");

        long employeeRole = Long.parseLong(request.getParameter("emRole"));
        long employeeManager = Long.parseLong(request.getParameter("emManager"));

        if (employeeRole > 0) {
            CategoryElementEntity categoryElementEntityRole = ICategoryElementEntityService.findCategoryElementById(employeeRole);
            employeeEntity.setEmployeeRole(categoryElementEntityRole);
        }

        if (employeeManager > 0) {
            EmployeeEntity employeeEntityManager = iEmployeeEntityService.getEmployeeEntityById(employeeManager);
            employeeEntity.setEmployeeManager(employeeEntityManager);
        }

        if (employeeEntity.getId() == 0) {

            iEmployeeEntityService.addEmployeeEntity(employeeEntity);
            modelAndView.addObject("subject", "Add");
            modelAndView.addObject("successMassage", "The Record Was Successfully Added");

        } else {

            EmployeeEntity oldEmployeeEntity = iEmployeeEntityService.getEmployeeEntityById(employeeEntity.getId());
            iEmployeeEntityService.updateEmployeeEntity(employeeEntity, oldEmployeeEntity);
            modelAndView.addObject("subject", "Update");
            modelAndView.addObject("successMassage", "The Record Was Successfully Updated");
        }

        modelAndView.addObject(employeeEntity);
        return modelAndView;
    }

    @RequestMapping(value = {"/updateEmployee/{employeeId}"}, method = RequestMethod.GET)
    public ModelAndView viewUpdateEmployee(@PathVariable(name = "employeeId") String employeeId) {

        EmployeeEntity employeeEntity = iEmployeeEntityService.getEmployeeEntityById(Long.parseLong(employeeId));
        ModelAndView modelAndView = getModelAndView("updateEmployee");
        modelAndView.addObject(employeeEntity);
        modelAndView.addObject("subject", "Update");

        return modelAndView;
    }

    @RequestMapping(value = {"/deleteEmployee/{employeeId}"}, method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable(name = "employeeId") String employeeId) {

        ModelAndView modelAndView = new ModelAndView("employee");

        EmployeeEntity employeeEntity = iEmployeeEntityService.getEmployeeEntityById(Long.parseLong(employeeId));
        List<EmployeeEntity> childEmployeeEntityList = iEmployeeEntityService.findByManager(employeeEntity);

        if (childEmployeeEntityList.size() == 0) {
            iEmployeeEntityService.deleteEmployeeEntity(employeeEntity);
        } else {

            modelAndView.addObject("warningDelete", "The Record Has a Child");
        }

        return modelAndView;
    }

    private ModelAndView getModelAndView(String viewName) {

        ModelAndView modelAndView = new ModelAndView(viewName);

        List<CategoryEntity> employeeRoleList = iCategoryEntityService.findByCategoryName("employeeRole");
        CategoryEntity roleCategory = employeeRoleList.get(0);
        Map<Long, String> roleCategoryElementMap = ICategoryElementEntityService.findByCategory(roleCategory);

        List<CategoryElementEntity> managerCategoryElementList = ICategoryElementEntityService.findCategoryElementEntityByName("Manager");

        Map<Long, String> managerEmployeeEntityMap = new HashMap<>();

        for (CategoryElementEntity ce : managerCategoryElementList) {

            Map<Long, String> employeeEntityMap = iEmployeeEntityService.findByEmployeeRole(ce);

            for (Map.Entry<Long, String> entry : employeeEntityMap.entrySet()) {
                managerEmployeeEntityMap.put(entry.getKey(), entry.getValue());
            }
        }

        modelAndView.addObject("roleCategoryElementMap", roleCategoryElementMap);
        modelAndView.addObject("managerEmployeeEntityMap", managerEmployeeEntityMap);

        return modelAndView;
    }
}
