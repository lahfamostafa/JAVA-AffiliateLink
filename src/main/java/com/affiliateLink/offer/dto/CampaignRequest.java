package com.affiliateLink.offer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.affiliateLink.offer.Entity.PayoutModel;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CampaignRequest(
    @NotNull 
    String name, 
    
    @NotNull 
    LocalDate startDate, 
    
    @NotNull 
    LocalDate endDate, 

    @DecimalMin("0.01") 
    BigDecimal budget, 
    
    @NotNull 
    PayoutModel payoutModel, 
    
    @NotNull 
    BigDecimal payoutValue
) {}