package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VacationsController {

   /* @Autowired
    VacationsEntityService vacationsEntityService;*/

    @RequestMapping("/vacations")
    public ModelAndView test5(){

        ModelAndView modelAndView = new ModelAndView("vacations");
        return modelAndView;
    }
}
