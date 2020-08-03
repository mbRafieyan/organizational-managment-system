package controller;

import model.CategoryElementEntity;
import model.CategoryEntity;
import model.EmployeeEntity;
import model.VacationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/viewVacation"})
public class VacationsController {

    @Autowired
    private IVacationsEntityService vacationsEntityService;

    @Autowired
    private ICategoryEntityService iCategoryEntityService;

    @Autowired
    private ICategoryElementEntityService iCategoryElementEntityService;

    @Autowired
    private IEmployeeEntityService iEmployeeEntityService;

    @RequestMapping(value = {"/viewUpdateVacations"}, method = RequestMethod.GET)
    public ModelAndView viewUpdateVacations(){

        VacationsEntity vacationsEntity = new VacationsEntity();
        ModelAndView modelAndView = getModelAndView("updateVacation");
        modelAndView.addObject("vacationsEntity", vacationsEntity);
        modelAndView.addObject("subject", "Request");

        return  modelAndView;
    }

    @RequestMapping(value = {"/addVacation"}, method = RequestMethod.POST)
    public ModelAndView addVacation(HttpServletRequest request, @ModelAttribute("vacationsEntity") VacationsEntity vacationsEntity){

        ModelAndView modelAndView = new ModelAndView("vacations");

        long employeeId = Long.parseLong(request.getParameter("selectedEmployee"));
        long vacationCategoryTypeId = Long.parseLong(request.getParameter("vacationType"));

        if(employeeId>0){
            EmployeeEntity employeeEntity = iEmployeeEntityService.getEmployeeEntityById(employeeId);
            vacationsEntity.setEmployeeEntity(employeeEntity);
        }

        if(vacationCategoryTypeId>0){
            CategoryElementEntity vacationTypeCategoryElement = iCategoryElementEntityService.findCategoryElementById(vacationCategoryTypeId);
            vacationsEntity.setVacationTypeCee(vacationTypeCategoryElement);
        }

        List<CategoryElementEntity> categoryElementEntityList = iCategoryElementEntityService.findCategoryElementEntityByName("indeterminate");
        vacationsEntity.setVacationStatusCee(categoryElementEntityList.get(0));
        vacationsEntityService.addVacationsEntity(vacationsEntity);
        modelAndView.addObject("successMassage", "The Record Was Successfully Added");

        return modelAndView;
    }

    @RequestMapping(value = {"/updateVacation/{vacationId}/{vacationStatusId}"}, method = RequestMethod.GET)
    public ModelAndView updateVacation(@PathVariable(name = "vacationId") String vacationId, @PathVariable(name = "vacationStatusId") String vacationStatusId){

        ModelAndView modelAndView = new ModelAndView("vacations");

        VacationsEntity vacation = vacationsEntityService.getVacationsEntityById(Long.valueOf(vacationId));
        CategoryElementEntity vacationStatus = iCategoryElementEntityService.findCategoryElementById(Long.valueOf(vacationStatusId));
        vacation.setVacationStatusCee(vacationStatus);
        vacationsEntityService.updateVacationsEntity(vacation);
        modelAndView.addObject("successMassage", "The Record Was Successfully Updated");

        return modelAndView;
    }


    @RequestMapping(value = {"", "/{page}"}, method = RequestMethod.GET)
    public ModelAndView getAllVacation(@PathVariable(required = false, name = "page") String page, HttpServletRequest request) {

        ModelAndView modelAndView = getModelAndView("vacations");

        PagedListHolder<VacationsEntity>  vacationsEntities;

        if (page == null) {

            vacationsEntities = new PagedListHolder<VacationsEntity>();
            List<VacationsEntity> vacationsEntityList = vacationsEntityService.getVacationsEntities();
            vacationsEntities.setSource(vacationsEntityList);
            vacationsEntities.setPageSize(2);

            request.getSession().setAttribute("vacationsEntityList", vacationsEntities);

        } else if (page.equals("prev")) {

            vacationsEntities = (PagedListHolder<VacationsEntity>) request.getSession().getAttribute("vacationsEntityList");
            vacationsEntities.previousPage();

        } else if (page.equals("next")) {

            vacationsEntities = (PagedListHolder<VacationsEntity>) request.getSession().getAttribute("vacationsEntityList");
            vacationsEntities.nextPage();

        } else {

            int pageNum = Integer.parseInt(page);
            vacationsEntities = (PagedListHolder<VacationsEntity>) request.getSession().getAttribute("vacationsEntityList");
            vacationsEntities.setPage(pageNum - 1);
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/deleteVacation/{vacationId}"}, method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable(name = "vacationId") String vacationId) {

        ModelAndView modelAndView = new ModelAndView("vacations");
        VacationsEntity vacationsEntity = vacationsEntityService.getVacationsEntityById(Long.valueOf(vacationId));
        vacationsEntityService.deleteVacationsEntity(vacationsEntity);

        return modelAndView;
    }


    private ModelAndView getModelAndView(String viewName) {

        ModelAndView modelAndView = new ModelAndView(viewName);

        List<CategoryEntity> vacationTypeList = iCategoryEntityService.findByCategoryName("vacationType");
        CategoryEntity typeCategory = vacationTypeList.get(0);
        Map<Long, String> vacationTypeMap = iCategoryElementEntityService.findByCategory(typeCategory);

        List<CategoryEntity> vacationStatusList = iCategoryEntityService.findByCategoryName("vacationStatus");
        CategoryEntity statusCategory = vacationStatusList.get(0);
        Map<Long, String> vacationStatusMap = iCategoryElementEntityService.findByCategory(statusCategory);

        List<EmployeeEntity> employeeEntities = iEmployeeEntityService.getEmployeeEntities();
        Map<Long, String> employeeEntityMap = new HashMap<>();

        for(EmployeeEntity employeeEntity :employeeEntities){
            employeeEntityMap.put(employeeEntity.getId(), employeeEntity.getFirstName() + " " + employeeEntity.getLastName());
        }

        modelAndView.addObject("employeeEntityMap", employeeEntityMap);
        modelAndView.addObject("vacationTypeMap", vacationTypeMap);
        modelAndView.addObject("vacationStatusMap", vacationStatusMap);

        return modelAndView;
    }
}
