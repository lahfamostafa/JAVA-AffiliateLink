package com.affiliateLink.offer.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.exception.OfferAlreadyExistsException;
import com.affiliateLink.exception.OfferNotExistsException;
import com.affiliateLink.offer.Entity.Offer;
import com.affiliateLink.offer.Entity.Tag;
import com.affiliateLink.offer.Repository.OfferRepository;
import com.affiliateLink.offer.dto.OfferRequest;
import com.affiliateLink.offer.dto.OfferResponse;
import com.affiliateLink.profile.AdvertiserProfileApi;

@Service 
public class OfferService {
    private final OfferRepository offerRepository;
    private final AdvertiserProfileApi impl;
    private final TagService tagService;

    public OfferService(OfferRepository offerRepository , AdvertiserProfileApi impl, TagService tagService){
        this.offerRepository = offerRepository;
        this.impl = impl;
        this.tagService = tagService;
    }

    @Transactional 
    public OfferResponse save(OfferRequest request){
        if (offerRepository.existsByAdvertiserIdAndTitle(request.advertiserId(), request.titre())) {
            throw new OfferAlreadyExistsException("Cette offre existe déjà pour cet annonceur");
        }
        if (!impl.isActiveAdvertiser(request.advertiserId())) {
            throw new IllegalArgumentException("Annonceur introuvable ou désactivé");
        }
        Set<Tag> tags = tagService.getOrCreateTags(request.tags());

        Offer savedOffer = offerRepository.save(new Offer(request.advertiserId(), request.titre(), request.description(), request.landingUrl(), request.niche() , tags));
        OfferResponse response = new OfferResponse(savedOffer.getId(), savedOffer.getAdvertiserId(), savedOffer.getTitle(), savedOffer.getDescription(), savedOffer.getLandingUrl(), savedOffer.getNiche(), savedOffer.getCreatedAt(), tags);
        return response;
    }

    public OfferResponse findOfferId(Long offerId){
        Offer offer = offerRepository.findById(offerId)
            .orElseThrow(()->
                new OfferNotExistsException("L'offre n'existe pas!")
            );

        return new OfferResponse(offerId, offer.getAdvertiserId(), offer.getTitle(), offer.getDescription(), offer.getLandingUrl(), offer.getNiche(), offer.getCreatedAt(), offer.getTags());
    }

}
