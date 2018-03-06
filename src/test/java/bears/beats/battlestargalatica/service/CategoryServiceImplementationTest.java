package bears.beats.battlestargalatica.service;

import bears.beats.battlestargalatica.api.v1.dto.CategoryDTO;
import bears.beats.battlestargalatica.api.v1.mapper.CategoryMapper;
import bears.beats.battlestargalatica.domain.Category;
import bears.beats.battlestargalatica.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplementationTest {

    private static final Long ID = 4L;
    private static final String NAME = "Yummy";
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImplementation(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getCategories() {

        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        assertEquals(categories.size(), categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getId(), categoryDTO.getId());
    }
}