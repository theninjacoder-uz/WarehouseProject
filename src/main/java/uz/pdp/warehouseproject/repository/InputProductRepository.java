package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
