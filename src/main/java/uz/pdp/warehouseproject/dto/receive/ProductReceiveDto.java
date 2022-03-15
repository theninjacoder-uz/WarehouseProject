package uz.pdp.warehouseproject.dto.receive;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductReceiveDto {
     private String name;

     @NotBlank
     private Integer categoryId;
     private Integer photoId;

     @NotBlank
     private Integer measurementId;
}
