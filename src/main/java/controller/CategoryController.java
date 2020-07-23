package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CategoryEntityService;

@Controller
public class CategoryController {

   /* @Autowired
    CategoryEntityService categoryEntityService;*/


    @RequestMapping("/category")
    public String test1(){
        return "category";
    }
}
