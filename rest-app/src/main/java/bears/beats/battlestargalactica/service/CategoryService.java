package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
