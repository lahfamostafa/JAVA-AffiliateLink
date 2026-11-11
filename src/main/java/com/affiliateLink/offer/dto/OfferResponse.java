package com.affiliateLink.offer.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.affiliateLink.offer.Entity.Tag;

public record OfferResponse(Long id,Long advertiserId,String title,String description,String landingUrl,String niche,LocalDateTime createdAt,Set<Tag> tags) {
    
}
