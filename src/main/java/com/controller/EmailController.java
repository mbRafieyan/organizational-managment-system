package com.controller;

import com.editors.EmployeeEntityEditor;
import com.model.EmailEntity;
import com.model.EmployeeEntity;
import com.service.ICategoryElementEntityService;
import com.service.ICategoryEntityService;
import com.service.IEmailEntityService;
import com.service.IEmployeeEntityService;
import org.apache.log4j.Logger;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.config.TikaConfigSerializer;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.serialization.JsonMetadataDeserializer;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.parser.*;
import org.apache.tika.parser.external.CompositeExternalParser;
import org.apache.tika.sax.BodyContentHandler;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.SerializableBlobProxy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = {"/email"})
public class EmailController {

    private final Logger logger = Logger.getLogger(EmailController.class);

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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(EmployeeEntity.class, new EmployeeEntityEditor(this.iEmployeeEntityService));
    }

    @RequestMapping(value = {"/sendEmail"}, method = RequestMethod.POST)
    public ModelAndView sendEmail(HttpServletRequest request, @RequestParam MultipartFile file, @ModelAttribute("emailEntity") @Valid EmailEntity emailEntity, BindingResult bindingResult) {

        new FileSizeValidator().validate(file, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("updateEmail");
            return modelAndView;
        } else {
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
                if (blobFile.length() > 0)
                    emailEntity.setAttachment(blobFile);

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            iEmailEntityService.sendEmailEntity(emailEntity);
            modelAndView.addObject("successMassage", "Email is sent");
            return modelAndView;
        }
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

        String mailBoxName = request.getParameter("mailBoxName");
        ModelAndView modelAndView = new ModelAndView(mailBoxName);
        List<EmployeeEntity> adminList = iEmployeeEntityService.findByEmployeeName("admin");

        PagedListHolder<EmailEntity> emailEntities;
        if (page == null) {

            emailEntities = new PagedListHolder<EmailEntity>();
            List<EmailEntity> emailEntityList = getSentOrInboxEmails(mailBoxName, adminList.get(0).getId());
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

    @RequestMapping(value = "/deleteEmail", method = RequestMethod.GET)
    public ModelAndView deleteEmail(HttpServletRequest request) {

        String emailId = request.getParameter("emailId");
        String mailBoxName = request.getParameter("mailBoxName");

        ModelAndView modelAndView = new ModelAndView(mailBoxName);
        EmailEntity emailEntity = iEmailEntityService.findEmailById(Long.valueOf(emailId));
        iEmailEntityService.deleteEmailEntity(emailEntity);

        List<EmployeeEntity> admin = iEmployeeEntityService.findByEmployeeName("admin");
        PagedListHolder<EmailEntity> emailEntities = new PagedListHolder<EmailEntity>();
        List<EmailEntity> emailEntityList = getSentOrInboxEmails(mailBoxName, admin.get(0).getId());
        emailEntities.setSource(emailEntityList);
        emailEntities.setPageSize(2);

        request.getSession().setAttribute("emailEntities", emailEntities);
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

            BodyContentHandler contentHandler = new BodyContentHandler(40000000);
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

        if (mailBoxName.equals("sentEmail")) {
            emailList = iEmailEntityService.findSentEmailBySenderEmployee(employee);
        } else {
            emailList = iEmailEntityService.findInboxEmailByReceiverEmployee(employee);
        }

        return emailList;
    }
}
