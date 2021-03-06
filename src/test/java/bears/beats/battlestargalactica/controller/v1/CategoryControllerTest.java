package bears.beats.battlestargalactica.controller.v1;

import bears.beats.battlestargalactica.api.v1.dto.CategoryDTO;
import bears.beats.battlestargalactica.controller.RestResponseEntityExceptionHandler;
import bears.beats.battlestargalactica.controller.v1.CategoryController;
import bears.beats.battlestargalactica.service.CategoryService;
import bears.beats.battlestargalactica.service.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    private static final String NAME = "Dexter";

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllCategoriesTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName(NAME);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(2L);
        categoryDTO1.setName("John");

        List<CategoryDTO> categories = Arrays.asList(categoryDTO, categoryDTO1);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get(CategoryController.BASEURL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void getCategoryByNameTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDTO);

        mockMvc.perform(get(CategoryController.BASEURL + "/Dexter")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void getCategoryByNameNotFoundTest() throws Exception {

        when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CategoryController.BASEURL + "/BOOM")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}