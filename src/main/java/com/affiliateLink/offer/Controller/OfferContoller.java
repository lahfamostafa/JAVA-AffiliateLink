package com.affiliateLink.offer.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.affiliateLink.offer.Service.OfferService;
import com.affiliateLink.offer.dto.OfferRequest;
import com.affiliateLink.offer.dto.OfferResponse;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/offres") 
public class OfferContoller {

    private final OfferService offerService;

    public OfferContoller(OfferService offerService){
        this.offerService = offerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferResponse createOffer(@Valid @RequestBody OfferRequest request){
        return offerService.save(request);
    }

    @GetMapping("/{id}")
    public OfferResponse getOffer(@PathVariable("id") Long offerId) {
        return offerService.findOfferId(offerId);
    }
    
    @PutMapping("/{id}")
    public OfferResponse modifieOffer(@PathVariable Long id, @Valid @RequestBody OfferRequest request) {        
        return offerService.updateOffer(id, request);
    }
}
