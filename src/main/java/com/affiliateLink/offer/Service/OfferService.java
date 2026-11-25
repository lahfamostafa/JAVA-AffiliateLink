package com.affiliateLink.offer.Service;

import com.affiliateLink.offer.Entity.Offer;
import com.affiliateLink.offer.dto.OfferRequest;
import com.affiliateLink.offer.dto.OfferResponse;

public interface OfferService {

    Offer findById(Long id);

    OfferResponse save(OfferRequest request);

    OfferResponse findOfferId(Long offerId);

    OfferResponse updateOffer(Long id, OfferRequest request);
}
