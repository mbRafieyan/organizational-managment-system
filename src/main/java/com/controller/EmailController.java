package com.controller;

import com.model.EmailEntity;
import com.model.EmployeeEntity;
import com.service.ICategoryElementEntityService;
import com.service.ICategoryEntityService;
import com.service.IEmailEntityService;
import com.service.IEmployeeEntityService;
import org.apache.log4j.Logger;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.hibernate.engine.jdbc.BlobProxy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
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

    private final Logger logger = Logger.getLogger(EmailController.class);

    @RequestMapping(value = {"/viewEmail"})
    public ModelAndView viewEmail() {

        ModelAndView modelAndView = new ModelAndView("email");
        return modelAndView;
    }

    @RequestMapping(value = {"/viewAddEmail"}, method = RequestMethod.GET)
    public ModelAndView viewAddEmail(HttpServletRequest request) {

        EmailEntity emailEntity;
        String emailId = request.getParameter("emailId");

        if (emailId != null && !emailId.equals(" ")) {
            emailEntity = iEmailEntityService.findEmailById(Long.valueOf(emailId));
        } else {
            emailEntity = new EmailEntity();
        }

        ModelAndView modelAndView = new ModelAndView("updateEmail");
        modelAndView.addObject("emailEntity", emailEntity);
        modelAndView.addObject("subject", "Send");
        return modelAndView;
    }

    @RequestMapping(value = {"/sendEmail"}, method = RequestMethod.POST)
    public ModelAndView sendEmail(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file, @ModelAttribute("emailEntity") EmailEntity emailEntity) {

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

        try {

            Blob blobFile = BlobProxy.generateProxy(file.getBytes());
            emailEntity.setAttachment(blobFile);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

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

    @RequestMapping(value = {"", "/{page}"}, method = RequestMethod.GET)
    public ModelAndView getAllEmail(@PathVariable(required = false, name = "page") String page, HttpServletRequest request) {

        String employeeId = request.getParameter("employeeId");
        String mailBoxName = request.getParameter("mailBoxName");

        ModelAndView modelAndView = new ModelAndView(mailBoxName);

        if (employeeId.equals("0")) {
            List<EmployeeEntity> adminList = iEmployeeEntityService.findByEmployeeName("admin");
            employeeId = String.valueOf(adminList.get(0).getId());
        }

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

    @RequestMapping(value = "/deleteEmail/{emailId}", method = RequestMethod.GET)
    public ModelAndView deleteEmail(@PathVariable(name = "emailId") String emailId) {

        ModelAndView modelAndView = new ModelAndView("sentEmail");
        EmailEntity emailEntity = iEmailEntityService.findEmailById(Long.valueOf(emailId));
        iEmailEntityService.deleteEmailEntity(emailEntity);

        modelAndView.addObject("successMassage", "The record deleted");

        return modelAndView;
    }

    @RequestMapping(value = "/fileRetriever", method = RequestMethod.GET)
    public ResponseEntity<Resource> fileRetriever(HttpServletRequest request) {

        String emailId = request.getParameter("emailId");
        EmailEntity emailEntity = iEmailEntityService.findEmailById(Long.valueOf(emailId));

        try {
            byte[] bytes = iEmailEntityService.getEmailFile(emailEntity);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

            BodyContentHandler contentHandler = new BodyContentHandler(2000000);
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();

            TikaConfig config = TikaConfig.getDefaultConfig();
            Parser autoDetectParser = new AutoDetectParser(config);
            autoDetectParser.parse(inputStream, contentHandler, metadata, context);

            MediaType mediaType = config.getMimeRepository().detect(inputStream, metadata);
            MimeType mimeType = config.getMimeRepository().forName(mediaType.toString());

            return ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.parseMediaType(metadata.get(Metadata.CONTENT_TYPE)))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file" + mimeType.getExtension() + "\"")
                    .body(new ByteArrayResource(bytes));

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (TikaException e) {
            logger.error(e.getMessage(), e);
        } catch (SAXException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private List<EmailEntity> getSentOrInboxEmails(String mailBoxName, long employeeId) {

        EmployeeEntity employee = iEmployeeEntityService.getEmployeeEntityById(employeeId);
        List<EmailEntity> emailList = new ArrayList<>();

        if (mailBoxName.equals("sent")) {
            emailList = iEmailEntityService.findInboxEmailByReceiverEmployee(employee);
        } else {
            emailList = iEmailEntityService.findSentEmailBySenderEmployee(employee);
        }
        return emailList;
    }
}
