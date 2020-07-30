package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {


    @RequestMapping("/email")
    public ModelAndView test3(){

        ModelAndView modelAndView = new ModelAndView("email");
        return modelAndView;
    }
}
