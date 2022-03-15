package uz.pdp.warehouseproject.dto.receive;

import lombok.Data;
import uz.pdp.warehouseproject.entity.Client;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class OutputReceiveDto {

    @NotBlank
    private int warehouseId;

    @NotBlank
    private int currencyId;

    private String factureNumber;

    private String code;

    private Client client;

}
