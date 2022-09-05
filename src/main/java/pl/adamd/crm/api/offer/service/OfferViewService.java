package pl.adamd.crm.api.offer.service;

import pl.adamd.crm.api.materials.entity.Material;
import pl.adamd.crm.api.offer.dto.OfferDto;
import pl.adamd.crm.api.offer.entity.Offer;

import java.util.List;

public interface OfferViewService {
    List<Offer> getAll();

    Offer add(OfferDto offerDto);

    Offer getOne(Long id);

    Offer modify(Long id, OfferDto offerDto);

    List<Offer> findByCustomerId(Long id);

    void addOffer(Offer offer);

    List<Material> findAllMaterials();
}
