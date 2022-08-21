package pl.adamd.crm.api.offer.service;

import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.offer.dto.OfferDto;
import pl.adamd.crm.api.offer.entity.Offer;

import java.util.List;

public interface OfferViewService {
    List<OfferDto> getAll();

    OfferDto add(OfferDto offerDto);

    OfferDto getOne(Long id);

    OfferDto modify(Long id, OfferDto offerDto);

    List<Offer> findByCustomerId(Long id);

    void addOffer(Offer offer);

    List<MaterialDto> findAllMaterials();
}
