package bears.beats.battlestargalactica.controller.v1;

import bears.beats.battlestargalactica.api.v1.dto.CategoryDTO;
import bears.beats.battlestargalactica.api.v1.dto.CategoryListDTO;
import bears.beats.battlestargalactica.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(CategoryController.BASEURL)
public class CategoryController {

    public static final String BASEURL = "/api/v1/categories";
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        log.debug("Getting all categories");
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        log.debug("Getting category with name " + name);
        return categoryService.getCategoryByName(name);
    }
}
