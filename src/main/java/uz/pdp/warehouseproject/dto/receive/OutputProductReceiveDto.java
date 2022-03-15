package uz.pdp.warehouseproject.dto.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProductReceiveDto {

    private Integer productId;
    private double amount;
    private double price;
    @NotBlank
    private int outputId;
}
