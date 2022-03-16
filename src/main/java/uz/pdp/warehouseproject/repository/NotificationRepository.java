package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.entity.Product;

import java.sql.Date;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p from Product p inner join InputProduct ip on p.id = ip.product.id where ip.expireDate <= ?1")
    List<Product> getNotifications(Date dateTime);
}
