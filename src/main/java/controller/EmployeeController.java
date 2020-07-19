package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import service.EmployeeEntityService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeEntityService employeeEntityService;
}
