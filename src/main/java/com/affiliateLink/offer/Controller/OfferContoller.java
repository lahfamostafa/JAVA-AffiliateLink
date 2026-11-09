package com.affiliateLink.offer.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.affiliateLink.offer.Service.OfferService;
import com.affiliateLink.offer.dto.OfferRequest;
import com.affiliateLink.offer.dto.OfferResponse;

import jakarta.validation.Valid;



@RestController 
@RequestMapping ("/api") 
public class OfferContoller {

    private final OfferService offerService;

    public OfferContoller(OfferService offerService){
        this.offerService = offerService;
    }

    @PostMapping("/offres")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferResponse createOffer(@Valid @RequestBody OfferRequest request){
        return offerService.save(request);
    }

    @GetMapping("/offres/{id}")
    public OfferResponse getOffer(@PathVariable("id") Long offerId) {
        return offerService.findOfferId(offerId);
    }
    
    
}
