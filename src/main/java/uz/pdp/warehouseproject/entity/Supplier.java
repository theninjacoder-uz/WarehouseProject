package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.warehouseproject.entity.base.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String phoneNumber;
}
