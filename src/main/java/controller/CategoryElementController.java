package controller;

import model.CategoryElementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CategoryElementEntityService;

@Controller
public class CategoryElementController {

    /*@Autowired
    CategoryElementEntityService categoryElementEntityService;*/

    @RequestMapping("/categoryElement")
    public String test2(){
        return "category_element";
    }
}
