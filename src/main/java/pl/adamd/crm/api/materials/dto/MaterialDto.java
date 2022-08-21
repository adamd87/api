package pl.adamd.crm.api.materials.dto;

import lombok.Data;

@Data
public class MaterialDto {

    private Long id;

    private String name;
    private String producer;
    private String power;
    private String category;
    private String price;
}
