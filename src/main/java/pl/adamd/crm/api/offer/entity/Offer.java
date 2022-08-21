package pl.adamd.crm.api.offer.entity;

import lombok.*;
import pl.adamd.crm.api.materials.entity.Material;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long clientId;
    private String clientFullName;
    @OneToMany
    private List<Material> materialList;
    private BigDecimal costOfMaterials;
    private BigDecimal myWarmthACost;
    private BigDecimal myWarmthBCost;
    private BigDecimal cleanAirA;
    private BigDecimal cleanAirB;




}
