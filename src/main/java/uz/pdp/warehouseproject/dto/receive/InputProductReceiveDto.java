package uz.pdp.warehouseproject.dto.receive;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class InputProductReceiveDto {

    @NotBlank
    private int productId;
    private double amount;
    private double price;
    private Timestamp expireDate;

    @NotBlank
    private int inputId;
}
