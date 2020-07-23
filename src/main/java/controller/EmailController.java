package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.EmailEntityService;

@Controller
public class EmailController {



    @RequestMapping("/email")
    public String test3(){
        return "email";
    }

}
