package uz.pdp.warehouseproject.dto.receive;

import lombok.Data;
import uz.pdp.warehouseproject.entity.Client;

import javax.validation.constraints.NotBlank;

@Data
public class InputReceiveDto {

    @NotBlank
    private int warehouseId;

    @NotBlank
    private int currencyId;

    @NotBlank
    private int supplierId;

    private String factureNumber;

    private String code;

}
