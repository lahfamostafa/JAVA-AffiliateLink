package com.affiliateLink.offer.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.offer.Entity.Offer;
import com.affiliateLink.offer.Repository.OfferRepository;
import com.affiliateLink.offer.dto.OfferRequest;
import com.affiliateLink.profile.AdvertiserProfileApi;

@Service 
public class OfferService {
    private final OfferRepository offerRepository;
    private final AdvertiserProfileApi impl;

    public OfferService(OfferRepository offerRepository , AdvertiserProfileApi impl){
        this.offerRepository = offerRepository;
        this.impl = impl;
    }

    @Transactional 
    public Offer save(OfferRequest request){
        if (!impl.isActiveAdvertiser(request.advertiserId())) {
            throw new IllegalArgumentException("Annonceur introuvable ou désactivé");
        }
        Offer offer = offerRepository.save(new Offer(request.advertiserId(), request.titre(), request.description(), request.landingUrl(), request.niche() , request.tags()));
        return offer;
    }
}
