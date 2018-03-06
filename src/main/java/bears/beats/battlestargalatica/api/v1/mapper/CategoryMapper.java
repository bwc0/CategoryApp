package bears.beats.battlestargalatica.api.v1.mapper;

import bears.beats.battlestargalatica.api.v1.dto.CategoryDTO;
import bears.beats.battlestargalatica.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
