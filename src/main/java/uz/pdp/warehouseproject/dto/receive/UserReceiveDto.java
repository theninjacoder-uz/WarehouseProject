package uz.pdp.warehouseproject.dto.receive;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
public class UserReceiveDto {
    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String password;

    private ArrayList<Integer> warehouses;
}
