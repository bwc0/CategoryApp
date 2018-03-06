package bears.beats.battlestargalatica.api.v1.mapper;

import bears.beats.battlestargalatica.api.v1.dto.CategoryDTO;
import bears.beats.battlestargalatica.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    private static final String YUMMY = "Yummy";
    private static final Long ID = 1L;
    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTOTest() {

        Category category = new Category();
        category.setName(YUMMY);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getId(), categoryDTO.getId());
    }
}