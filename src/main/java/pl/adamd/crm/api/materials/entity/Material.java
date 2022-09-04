package pl.adamd.crm.api.materials.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import pl.adamd.crm.api.offer.entity.Offer;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materials")
@DynamicUpdate
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
// TODO DODAWANIE PRZY ZREALIZOWANIU UMOWY NUMERU SERYJNEKO JAKO KOMENTARZ
    @NotNull
    private String name;
    private String producer;
    private String power;
    private String category;
    private BigDecimal price;
    @ManyToMany
    private List<Offer> offers;

}
