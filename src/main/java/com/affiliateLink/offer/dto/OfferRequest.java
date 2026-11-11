package com.affiliateLink.offer.dto;

import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.*;

public record OfferRequest(
    @NotNull 
    Long advertiserId,

    @NotBlank
    String titre,

    @NotBlank
    String description,

    @NotBlank 
    @URL 
    String landingUrl,

    @NotBlank
    String niche,

    Set<String> tags
) {}
