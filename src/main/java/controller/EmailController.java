package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.EmailEntityService;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailEntityService emailEntityService;
}
