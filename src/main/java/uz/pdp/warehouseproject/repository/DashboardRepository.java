package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.dao.DailyIncomeInfoDao;
import uz.pdp.warehouseproject.entity.Product;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Product, Integer> {

    @Query("select sum(ip.amount) as totalSum, count(ip.id) as count from InputProduct ip where ip.input.date = ?1")
    List<DailyIncomeInfoDao> getDailyIncomeInfo();

}
