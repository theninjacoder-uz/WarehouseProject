package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    @Query("select (count(m) > 0) from Measurement m where m.name = ?1 and m.active = ?2")
    boolean existsByNameAndActive(String name, boolean active);
}
