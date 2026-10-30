package com.affiliateLink.offer.Controller;

import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.affiliateLink.offer.Entity.Offer;
import com.affiliateLink.offer.Service.OfferService;
import com.affiliateLink.offer.dto.OfferRequest;

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
    public Offer createOffer(@Valid @RequestBody OfferRequest request){
        return offerService.save(request);
    }
}
