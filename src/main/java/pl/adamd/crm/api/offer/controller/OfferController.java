package pl.adamd.crm.api.offer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adamd.crm.api.offer.dto.OfferDto;
import pl.adamd.crm.api.offer.entity.Offer;
import pl.adamd.crm.api.offer.service.OfferViewService;

import java.util.List;

@RestController
@RequestMapping("/offer")
@AllArgsConstructor
public class OfferController {
    private final OfferViewService viewService;

    @GetMapping("/all")
    public ResponseEntity<List<Offer>> getAll() {
        return ResponseEntity.ok(viewService.getAll());
    }

    @GetMapping("/by-id/{id}")
    ResponseEntity<Offer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(viewService.getOne(id));
    }

    @PostMapping("/add")
    ResponseEntity<Offer> addOne(OfferDto offerDto) {
        return ResponseEntity.ok(viewService.add(offerDto));
    }


    @PatchMapping("/update/{id}")
    ResponseEntity<Offer> update(@PathVariable Long id, OfferDto offerDto) {
        return ResponseEntity.ok(viewService.modify(id, offerDto));
    }


}
