package pl.adamd.crm.api.offer.service;


import pl.adamd.crm.api.offer.entity.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> findAll();

    void save(Offer offer);

    Offer findById(Long id);

    List<Offer> getByCustomerId(Long id);
}
