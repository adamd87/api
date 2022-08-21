package pl.adamd.crm.api.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamd.crm.api.offer.entity.Offer;
import pl.adamd.crm.api.offer.repository.OfferRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class OfferServiceImpl implements OfferService{
    private final OfferRepository repository;

    @Override
    public List<Offer> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Offer offer) {
        repository.save(offer);
    }

    @Override
    public Offer findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Offer> getByCustomerId(Long id) {
        return repository.findOfferByCustomer(id);
    }
}
