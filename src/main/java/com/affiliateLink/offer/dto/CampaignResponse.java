package com.affiliateLink.offer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.affiliateLink.offer.Entity.CampaignStatus;
import com.affiliateLink.offer.Entity.PayoutModel;

public record CampaignResponse(
    Long id,
    Long offerId,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal budget,
    PayoutModel payoutModel,
    BigDecimal payoutValue,
    CampaignStatus status
) {}
