package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    /*@Autowired
    EmployeeEntityService employeeEntityService;*/



    @RequestMapping("/employee")
    public String test4(){
        return "employee";
    }
}
