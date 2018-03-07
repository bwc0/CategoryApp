package bears.beats.battlestargalactica.controller.v1;

import bears.beats.battlestargalactica.api.v1.dto.CategoryDTO;
import bears.beats.battlestargalactica.api.v1.dto.CategoryListDTO;
import bears.beats.battlestargalactica.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(CategoryController.BASEURL)
public class CategoryController {

    public static final String BASEURL = "/api/v1/categories";
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        log.debug("Getting All categories");
        return new ResponseEntity<>(
                new CategoryListDTO(categoryService.getAllCategories()),
                HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        log.debug("Getting category with name " + name);
        return new ResponseEntity<>(
                categoryService.getCategoryByName(name), HttpStatus.OK
        );
    }
}
