package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select (count(c) > 0) from Category c where c.name = ?1 and c.parentCategory.id = ?2")
    boolean existsByNameAndParentCategoryId(String name, Integer parentCategory_id);
}
