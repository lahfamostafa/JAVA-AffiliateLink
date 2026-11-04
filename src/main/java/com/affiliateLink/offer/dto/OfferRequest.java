package com.affiliateLink.offer.dto;

import java.util.Set;

public record OfferRequest(Long advertiserId, String titre, String description, String landingUrl, String niche ,Set<String> tags) {
    
}
