package pl.adamd.crm.api.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.adamd.crm.api.offer.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "SELECT o FROM Offer o WHERE o.clientId = :clientId")
    List<Offer> findOfferByCustomer(@Param("clientId") Long clientId);
}
