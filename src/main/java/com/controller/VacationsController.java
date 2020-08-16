package com.controller;

import com.editors.EmployeeEntityEditor;
import com.model.CategoryElementEntity;
import com.model.CategoryEntity;
import com.model.EmployeeEntity;
import com.model.VacationsEntity;
import com.service.ICategoryElementEntityService;
import com.service.ICategoryEntityService;
import com.service.IEmployeeEntityService;
import com.service.IVacationsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    public ModelAndView viewUpdateVacations() {

        VacationsEntity vacationsEntity = new VacationsEntity();
        ModelAndView modelAndView = getModelAndView("updateVacation");
        modelAndView.addObject("vacationsEntity", vacationsEntity);
        modelAndView.addObject("subject", "Request");

        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(EmployeeEntity.class, new EmployeeEntityEditor(this.iEmployeeEntityService));
    }

    @RequestMapping(value = {"/addVacation"}, method = RequestMethod.POST)
    public ModelAndView addVacation(HttpServletRequest request, @Valid @ModelAttribute("vacationsEntity") VacationsEntity vacationsEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = getModelAndView("updateVacation");
            return modelAndView;
        } else {

            ModelAndView modelAndView = getModelAndView("vacations");

            long vacationCategoryTypeId = Long.parseLong(request.getParameter("vacationType"));

            if (vacationCategoryTypeId > 0) {
                CategoryElementEntity vacationTypeCategoryElement = iCategoryElementEntityService.findCategoryElementById(vacationCategoryTypeId);
                vacationsEntity.setVacationTypeCee(vacationTypeCategoryElement);
            }

            List<CategoryElementEntity> categoryElementEntityList = iCategoryElementEntityService.findCategoryElementEntityByName("indeterminate");
            vacationsEntity.setVacationStatusCee(categoryElementEntityList.get(0));
            String massage = vacationsEntityService.addVacationsEntity(vacationsEntity);

            if (!massage.equals("failed")) {
                modelAndView.addObject("successMassage", "The Record Was Successfully Added");
            } else {
                modelAndView.addObject("failedMassage", "Vacation was not recorded due to overlap");
            }

            return modelAndView;
        }
    }

    @RequestMapping(value = {"/updateVacation/{vacationId}/{vacationStatusId}"}, method = RequestMethod.GET)
    public ModelAndView updateVacation(@PathVariable(name = "vacationId") String vacationId, @PathVariable(name = "vacationStatusId") String vacationStatusId) {

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

        PagedListHolder<VacationsEntity> vacationsEntities;

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

        List<CategoryEntity> categoryVacationTypeList = iCategoryEntityService.findByCategoryName("vacationType");
        CategoryEntity typeCategory = categoryVacationTypeList.get(0);
        List<CategoryElementEntity> vacationTypeList = iCategoryElementEntityService.findByCategory(typeCategory);

        List<CategoryEntity> categoryVacationStatusList = iCategoryEntityService.findByCategoryName("vacationStatus");
        CategoryEntity statusCategory = categoryVacationStatusList.get(0);
        List<CategoryElementEntity> vacationStatusList = iCategoryElementEntityService.findByCategory(statusCategory);

        List<EmployeeEntity> employeeEntities = iEmployeeEntityService.getEmployeeEntities();

        modelAndView.addObject("employeeEntities", employeeEntities);
        modelAndView.addObject("vacationTypeList", vacationTypeList);
        modelAndView.addObject("vacationStatusList", vacationStatusList);

        return modelAndView;
    }
}
