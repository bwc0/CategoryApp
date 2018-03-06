package bears.beats.battlestargalatica.repository;

import bears.beats.battlestargalatica.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findByName(String name);

}
