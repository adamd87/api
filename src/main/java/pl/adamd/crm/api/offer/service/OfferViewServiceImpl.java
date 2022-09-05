package pl.adamd.crm.api.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adamd.crm.api.materials.entity.Material;
import pl.adamd.crm.api.materials.service.material.MaterialService;
import pl.adamd.crm.api.offer.dto.OfferDto;
import pl.adamd.crm.api.offer.entity.Offer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static pl.adamd.crm.api.common.Utils.setIfNotNull;

@Service
@AllArgsConstructor
public class OfferViewServiceImpl implements OfferViewService {
    private final OfferService service;
    private final MaterialService materialService;

    @Override
    @Transactional
    public List<Offer> getAll() {
        return service.findAll();

    }

    @Override
    @Transactional
    public Offer add(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setClientFullName(offerDto.getClientFullName());
        offer.setClientId(offer.getClientId());
        offer.setMaterialList(offerDto.getMaterialList());
        materialCost(offer);

        return offer;
    }

    @Override
    @Transactional
    public void addOffer(Offer offer) {
        materialCost(offer);
    }

    @Override
    public List<Material> findAllMaterials() {
        return materialService.findAll();
    }

    private void materialCost(Offer offer) {
        offer.setCostOfMaterials(costOfMaterials(offer));
        offer.setMyWarmthACost(offer.getCostOfMaterials()
                                    .subtract(BigDecimal.valueOf(7000)));
        offer.setMyWarmthBCost(offer.getCostOfMaterials()
                                    .subtract(BigDecimal.valueOf(21000)));
        offer.setCleanAirA(getCleanAirACost(offer));
        offer.setCleanAirB(offer.getCleanAirA()
                                .subtract(offer.getCleanAirA()
                                               .multiply(BigDecimal.valueOf(0.12))));

        service.save(offer);
    }

    @Override
    @Transactional
    public Offer getOne(Long id) {
        return service.findById(id);

    }

    @Override
    @Transactional
    public Offer modify(Long id, OfferDto offerDto) {
        Offer offer = service.findById(id);

        setIfNotNull(offerDto.getMaterialList(), offer::setMaterialList);
        setIfNotNull(offerDto.getClientFullName(), offer::setClientFullName);
        setIfNotNull(offerDto.getId(), offer::setId);

        materialCost(offer);

        return offer;
    }

    //TODO................
    @Override
    public List<Offer> findByCustomerId(Long id) {
        return service.getByCustomerId(id);
    }


    private BigDecimal getCleanAirACost(Offer offer) {
        BigDecimal cleanAirACost;
        if (offer.getCostOfMaterials()
                 .floatValue() <= 30000) {
            cleanAirACost = offer.getCostOfMaterials()
                                 .subtract(offer.getCostOfMaterials()
                                                .multiply(BigDecimal.valueOf(0.45)));
        }
        else if (offer.getCostOfMaterials()
                      .floatValue() > 30000 && offer.getCostOfMaterials()
                                                    .floatValue() < 45000) {
            BigDecimal restCount = offer.getCostOfMaterials()
                                        .subtract(BigDecimal.valueOf(30000));
            cleanAirACost = offer.getCostOfMaterials()
                                 .subtract(restCount.multiply(BigDecimal.valueOf(0.3)))
                                 .subtract(BigDecimal.valueOf(13500));
        }
        else {
            cleanAirACost = offer.getCostOfMaterials()
                                 .subtract(BigDecimal.valueOf(18000));
        }
        return cleanAirACost;
    }

    private BigDecimal costOfMaterials(Offer offer) {
        BigDecimal costOfMaterials = new BigDecimal(BigInteger.ZERO);
        for (Material material : offer.getMaterialList()) {
            costOfMaterials = costOfMaterials.add(material.getPrice());
        }
        return costOfMaterials;
    }
}
