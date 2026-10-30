package com.affiliateLink.offer.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "campaigns")
@Getter 
@Setter 
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column (name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column (nullable = false, precision = 12, scale = 2)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Column (name = "payout_model", nullable = false)
    private PayoutModel payoutModel;

    @Column (name = "payout_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal payoutValue;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false, length = 20)
    private CampaignStatus status = CampaignStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn (name = "offer_id",nullable = false)
    private Offer offer ;
}
