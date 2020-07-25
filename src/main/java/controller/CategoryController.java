package controller;

import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CategoryEntityService;

@Controller
public class CategoryController {

    @Autowired
    CategoryEntityService categoryEntityService;

    @RequestMapping("/category")
    public String showViewCategory(){
        return "category";
    }

    @RequestMapping("/updateCategory")
    public String showViewUpdateCategory(){
        return "updateCategory";
    }

    @RequestMapping(value = {"/saveCategory"}, method = RequestMethod.POST)
    public String addCategory(CategoryEntity categoryEntity){
        categoryEntityService.addCategoryEntity(categoryEntity);
        return "redirect:/category";
    }
}
