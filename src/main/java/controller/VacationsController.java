package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.VacationsEntityService;

@Controller
public class VacationsController {

   /* @Autowired
    VacationsEntityService vacationsEntityService;*/

    @RequestMapping("/vacations")
    public String test5(){
        return "vacations";
    }
}
