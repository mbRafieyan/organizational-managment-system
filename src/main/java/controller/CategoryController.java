package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CategoryEntityService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryEntityService categoryEntityService;
}