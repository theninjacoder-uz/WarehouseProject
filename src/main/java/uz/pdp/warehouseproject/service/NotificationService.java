package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.repository.InputRepository;
import uz.pdp.warehouseproject.repository.NotificationRepository;
import uz.pdp.warehouseproject.repository.OutputRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Product> getDailyNotification(int dayTillExpiresAt){
        LocalDate localDate = LocalDate.now();
        localDate.plusDays(dayTillExpiresAt);

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        return notificationRepository.getNotifications(new Date(year, month, day));
    }
}
