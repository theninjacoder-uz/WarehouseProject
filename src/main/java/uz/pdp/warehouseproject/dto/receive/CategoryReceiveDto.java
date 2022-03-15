package uz.pdp.warehouseproject.dto.receive;

import lombok.Data;

@Data
public class CategoryReceiveDto {

    private String name;
    private Integer parentCategoryId;
}
