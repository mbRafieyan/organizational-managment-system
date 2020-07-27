package controller;

import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CategoryElementEntityService;
import service.CategoryEntityService;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private CategoryEntityService categoryEntityService;

    @Autowired
    private CategoryElementEntityService categoryElementEntityService;

    @RequestMapping("/")
    public String home() {

        List<CategoryEntity> categoryEntities = categoryEntityService.selectAllCategory();

        if (categoryEntities.size() == 0) {
            Map<Long, CategoryEntity> categoryMap = categoryEntityService.insertAllCategory();

            for (Map.Entry<Long, CategoryEntity> entry : categoryMap.entrySet()) {
                categoryElementEntityService.insertAllCategoryElement(entry.getValue());
            }
        }
        return "home";
    }
}
