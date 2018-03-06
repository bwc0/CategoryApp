package bears.beats.battlestargalactica.api.v1.mapper;

import bears.beats.battlestargalactica.api.v1.dto.CategoryDTO;
import bears.beats.battlestargalactica.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
