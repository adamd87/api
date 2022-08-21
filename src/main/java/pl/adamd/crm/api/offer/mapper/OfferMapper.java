package pl.adamd.crm.api.offer.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.adamd.crm.api.offer.dto.OfferDto;
import pl.adamd.crm.api.offer.entity.Offer;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OfferMapper {
    OfferDto mapEntityToDto(Offer offer);
    List<OfferDto> mapListToDto(List<Offer> offerList);
}
