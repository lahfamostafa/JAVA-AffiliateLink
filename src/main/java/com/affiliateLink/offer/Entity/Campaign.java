package com.affiliateLink.offer.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.affiliateLink.exception.CamaignStatusException;

import jakarta.persistence.*;
import lombok.Getter;

@Entity 
@Table(name = "campaigns")
@Getter 
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

    public Campaign(String name, LocalDate startDate, LocalDate endDate, BigDecimal budget, PayoutModel payoutModel,
            BigDecimal payoutValue, Offer offer) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.payoutModel = payoutModel;
        this.payoutValue = payoutValue;
        this.offer = offer;
        offer.addCampaign(this);
    }

    public void activate(){
        if (status != (CampaignStatus.DRAFT) && 
            status != (CampaignStatus.PAUSED)) {
                throw new CamaignStatusException("Impossible d'activer cette campagne");
        }

        if (endDate.isBefore(LocalDate.now())) {
            throw new CamaignStatusException("Impossible d'activer une campagne terminée");
        }

        for (Campaign campaign : offer.getCampaigns()) {
            if (campaign != this &&
                campaign.getStatus() == CampaignStatus.ACTIVE) {

                campaign.pause();
            }
        }
        this.status = CampaignStatus.ACTIVE;
    }

    public void pause(){
        if (status.equals(CampaignStatus.ACTIVE)) {
                this.status = CampaignStatus.PAUSED;
                return ;
        }
        throw new CamaignStatusException("Impossible de faire ce changementt dans le status");
    }

    public void complete(){
        if (status.equals(CampaignStatus.ACTIVE) || 
            status.equals(CampaignStatus.PAUSED)) {
                this.status = CampaignStatus.ENDED;
                return ;
        }
        throw new CamaignStatusException("Impossible de faire ce changementt dans le status");
    }
}
