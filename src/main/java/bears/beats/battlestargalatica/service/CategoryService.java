package bears.beats.battlestargalatica.service;

import bears.beats.battlestargalatica.api.v1.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
