package controller;

import model.CategoryElementEntity;
import model.EmailEntity;
import model.EmployeeEntity;
import org.hibernate.engine.jdbc.BlobProxy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ICategoryElementEntityService;
import service.ICategoryEntityService;
import service.IEmailEntityService;
import service.IEmployeeEntityService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/email"})
public class EmailController {

    @Autowired
    IEmailEntityService iEmailEntityService;

    @Autowired
    IEmployeeEntityService iEmployeeEntityService;

    @Autowired
    ICategoryEntityService iCategoryEntityService;

    @Autowired
    ICategoryElementEntityService iCategoryElementEntityService;

    @RequestMapping(value = {"/viewEmail"})
    public ModelAndView viewEmail() {

        ModelAndView modelAndView = new ModelAndView("email");
        return modelAndView;
    }

    @RequestMapping(value = {"/sentEmail"})
    public ModelAndView viewSentEmail() {

        ModelAndView modelAndView = new ModelAndView("sentEmail");
        List<EmployeeEntity> adminList = iEmployeeEntityService.findByEmployeeName("admin");
        List<EmailEntity> sentEmails = iEmailEntityService.findSentEmailBySenderEmployee(adminList.get(0));
        modelAndView.addObject("employeeId", adminList.get(0).getId());
        modelAndView.addObject("sentEmails", sentEmails);
        return modelAndView;
    }

    @RequestMapping(value = {"/inboxEmail"})
    public ModelAndView viewInboxEmail() {

        ModelAndView modelAndView = new ModelAndView("inboxEmail");
        return modelAndView;
    }

    @RequestMapping(value = {"/viewAddEmail"}, method = RequestMethod.GET)
    public ModelAndView viewAddEmail() {

        EmailEntity emailEntity = new EmailEntity();
        ModelAndView modelAndView = new ModelAndView("updateEmail");
        modelAndView.addObject("emailEntity", emailEntity);
        modelAndView.addObject("subject", "Send");
        return modelAndView;
    }

    @RequestMapping(value = {"/sendEmail"}, method = RequestMethod.POST)
    public ModelAndView sendEmail(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file, @ModelAttribute("emailEntity") EmailEntity emailEntity) throws IOException {

        ModelAndView modelAndView = new ModelAndView("email");

        String selectedEmployeeStr = request.getParameter("selectedIds");

        String[] selectedEmployeeArray = selectedEmployeeStr.split(",");

        List<EmployeeEntity> receiverList = new ArrayList<>();

        for (String employeeEntityId : selectedEmployeeArray) {
            EmployeeEntity selectedEmployee = iEmployeeEntityService.getEmployeeEntityById(Long.valueOf(employeeEntityId));
            receiverList.add(selectedEmployee);
        }

        List<EmployeeEntity> adminList = iEmployeeEntityService.findByEmployeeName("admin");
        emailEntity.setSenderEmployee(adminList.get(0));
        emailEntity.setRecievers(receiverList);

        Blob blobFile = BlobProxy.generateProxy(file.getBytes());
        emailEntity.setAttachment(blobFile);

        iEmailEntityService.sendEmailEntity(emailEntity);
        return modelAndView;
    }

    @RequestMapping(value = {"/searchEmployee"}, method = RequestMethod.GET)
    @ResponseBody
    public JSONArray searchEmployee(HttpServletRequest request) {

        String param = request.getParameter("param");

        List<EmployeeEntity> employeeEntityList = iEmployeeEntityService.findEmployeeForSelect2(param);

        JSONArray jsonArray = new JSONArray();
        for (EmployeeEntity ee : employeeEntityList) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("employeeId", ee.getId());
            jsonObject.put("employeeName", ee.getFirstName() + " " + ee.getLastName());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    @RequestMapping(value = {"", "/{mailBoxName}/{employeeId}/{page}"}, method = RequestMethod.GET)
    public ModelAndView getAllEmployee(@PathVariable(name = "mailBoxName") String mailBoxName,
                                       @PathVariable(name = "employeeId") String employeeId,
                                       @PathVariable(required = false, name = "page") String page, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("sentEmail");

        PagedListHolder<EmailEntity> emailEntities;
        if (page == null) {

            emailEntities = new PagedListHolder<EmailEntity>();
            List<EmailEntity> emailEntityList = getSentOrInboxEmails(mailBoxName, Long.valueOf(employeeId));
            emailEntities.setSource(emailEntityList);
            emailEntities.setPageSize(2);

            request.getSession().setAttribute("emailEntities", emailEntities);

        } else if (page.equals("prev")) {

            emailEntities = (PagedListHolder<EmailEntity>) request.getSession().getAttribute("emailEntities");
            emailEntities.previousPage();

        } else if (page.equals("next")) {

            emailEntities = (PagedListHolder<EmailEntity>) request.getSession().getAttribute("emailEntities");
            emailEntities.nextPage();

        } else {

            int pageNum = Integer.parseInt(page);
            emailEntities = (PagedListHolder<EmailEntity>) request.getSession().getAttribute("emailEntities");
            emailEntities.setPage(pageNum - 1);
        }
        return modelAndView;
    }

    private List<EmailEntity> getSentOrInboxEmails(String mailBoxName, long employeeId){

        EmployeeEntity employee = iEmployeeEntityService.getEmployeeEntityById(employeeId);
        List<EmailEntity> emailList = new ArrayList<>();

        if(mailBoxName.equals("sent")){
            emailList = iEmailEntityService.findSentEmailBySenderEmployee(employee);
        } else {
            emailList = iEmailEntityService.findInboxEmailByReceiverEmployee(employee);
        }

        return emailList;
    }
}
