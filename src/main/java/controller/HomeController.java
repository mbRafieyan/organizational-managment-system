package controller;

import model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ICategoryElementEntityService;
import service.ICategoryEntityService;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private ICategoryEntityService ICategoryEntityService;

    @Autowired
    private ICategoryElementEntityService ICategoryElementEntityService;

    @RequestMapping("/")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("home");

        List<CategoryEntity> categoryEntities = ICategoryEntityService.selectAllCategory();

        if (categoryEntities.size() == 0) {
            Map<Long, CategoryEntity> categoryMap = ICategoryEntityService.insertAllCategory();

            for (Map.Entry<Long, CategoryEntity> entry : categoryMap.entrySet()) {
                ICategoryElementEntityService.insertAllCategoryElement(entry.getValue());
            }
        }
        return modelAndView;
    }
}
