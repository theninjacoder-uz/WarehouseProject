package uz.pdp.warehouseproject.entity;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.warehouseproject.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Warehouse extends BaseEntity {
}
