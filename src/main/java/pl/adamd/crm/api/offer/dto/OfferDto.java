package pl.adamd.crm.api.offer.dto;

import lombok.Data;
import pl.adamd.crm.api.materials.entity.Material;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OfferDto {
    private Long id;
    private Long clientId;
    private String clientFullName;
    private List<Material> materialList;
    private BigDecimal costOfMaterials;
    private BigDecimal myWarmthACost;
    private BigDecimal myWarmthBCost;
    private BigDecimal cleanAirA;
    private BigDecimal cleanAirB;
}
