package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.VacationsEntityService;

@Controller
@RequestMapping("/vacations")
public class VacationsController {

    @Autowired
    VacationsEntityService vacationsEntityService;
}
