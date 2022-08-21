package pl.adamd.crm.api.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adamd.crm.api.materials.MaterialMapper;
import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.materials.entity.Material;
import pl.adamd.crm.api.materials.service.material.MaterialService;
import pl.adamd.crm.api.offer.dto.OfferDto;
import pl.adamd.crm.api.offer.entity.Offer;
import pl.adamd.crm.api.offer.mapper.OfferMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.adamd.crm.api.common.Utils.setIfNotNull;

@Service
@AllArgsConstructor
public class OfferViewServiceImpl implements OfferViewService {
    private final OfferService service;
    private final OfferMapper mapper;
    private final MaterialService materialService;
    private final MaterialMapper materialMapper;

    @Override
    @Transactional
    public List<OfferDto> getAll() {
        List<Offer> offerList = service.findAll();
        List<OfferDto> offerDtoList = mapper.mapListToDto(offerList);
        return offerDtoList.stream().sorted(Comparator.comparing(OfferDto::getClientFullName)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public OfferDto add(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setClientFullName(offerDto.getClientFullName());
        offer.setClientId(offer.getClientId());
        offer.setMaterialList(offerDto.getMaterialList());
        materialCost(offer);

        return mapper.mapEntityToDto(offer);
    }

    @Override
    @Transactional
    public void addOffer(Offer offer) {
        materialCost(offer);
    }

    @Override
    public List<MaterialDto> findAllMaterials() {
       return materialMapper.mapMaterialsListToDto(materialService.findAll());
    }

    private void materialCost(Offer offer) {
        offer.setCostOfMaterials(costOfMaterials(offer));
        offer.setMyWarmthACost(offer.getCostOfMaterials().subtract(BigDecimal.valueOf(7000)));
        offer.setMyWarmthBCost(offer.getCostOfMaterials().subtract(BigDecimal.valueOf(21000)));
        offer.setCleanAirA(getCleanAirACost(offer));
        offer.setCleanAirB(offer.getCleanAirA().subtract(offer.getCleanAirA().multiply(BigDecimal.valueOf(0.12))));

        service.save(offer);
    }

    @Override
    @Transactional
    public OfferDto getOne(Long id) {
        Offer offer = service.findById(id);
        return mapper.mapEntityToDto(offer);
    }

    @Override
    @Transactional
    public OfferDto modify(Long id, OfferDto offerDto) {
        Offer offer = service.findById(id);

        setIfNotNull(offerDto.getMaterialList(), offer::setMaterialList);
        setIfNotNull(offerDto.getClientFullName(), offer::setClientFullName);
        setIfNotNull(offerDto.getId(), offer::setId);

        materialCost(offer);

        return mapper.mapEntityToDto(offer);
    }

//TODO................
    @Override
    public List<Offer> findByCustomerId(Long id) {
        return service.getByCustomerId(id);
    }


    private BigDecimal getCleanAirACost(Offer offer) {
        BigDecimal cleanAirACost;
        if (offer.getCostOfMaterials().floatValue() <= 30000) {
            cleanAirACost = offer.getCostOfMaterials().subtract(offer.getCostOfMaterials().multiply(BigDecimal.valueOf(0.45)));
        } else if (offer.getCostOfMaterials().floatValue() > 30000 && offer.getCostOfMaterials().floatValue() < 45000) {
            BigDecimal restCount = offer.getCostOfMaterials().subtract(BigDecimal.valueOf(30000));
            cleanAirACost = offer.getCostOfMaterials().subtract(restCount.multiply(BigDecimal.valueOf(0.3))).subtract(BigDecimal.valueOf(13500));
        } else {
            cleanAirACost = offer.getCostOfMaterials().subtract(BigDecimal.valueOf(18000));
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
