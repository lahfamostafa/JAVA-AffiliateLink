package com.affiliateLink.offer.dto;

import java.util.Set;

import com.affiliateLink.offer.Entity.Campaign;
import com.affiliateLink.offer.Entity.Tag;

public record OfferRequest(Long advertiserId, String titre, String description, String landingUrl, String niche ,Set<Tag> tags) {
    
}
