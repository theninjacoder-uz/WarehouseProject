package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    @Query("select (count(c) > 0) from Currency c where c.name = ?1 and c.active = ?2")
    boolean existsByNameAndActive(String name, boolean active);
}
