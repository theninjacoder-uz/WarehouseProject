package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
