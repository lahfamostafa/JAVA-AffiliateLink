
package com.affiliateLink.offer.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.affiliateLink.exception.CampaignStatusException;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "campaigns")
@Getter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "payout_model", nullable = false)
    private PayoutModel payoutModel;

    @Column(name = "payout_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal payoutValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CampaignStatus status = CampaignStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    protected Campaign() {}

    public Campaign(
            String name,
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal budget,
            PayoutModel payoutModel,
            BigDecimal payoutValue,
            Offer offer) {

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.payoutModel = payoutModel;
        this.payoutValue = payoutValue;
        this.offer = offer;
        offer.addCampaign(this);
    }

    public void activate() {
        if (status != CampaignStatus.DRAFT && status != CampaignStatus.PAUSED) {
            throw new CampaignStatusException("Impossible d'activer cette campagne"
            );
        }

        if (endDate.isBefore(LocalDate.now())) {
            throw new CampaignStatusException("Impossible d'activer une campagne terminée");
        }

        this.status = CampaignStatus.ACTIVE;
    }

    public void pause() {
        if (status != CampaignStatus.ACTIVE) {
            throw new CampaignStatusException("Seule une campagne ACTIVE peut être mise en pause");
        }
        this.status = CampaignStatus.PAUSED;
    }

    public void end() {
        if (status != CampaignStatus.ACTIVE && status != CampaignStatus.PAUSED) {
            throw new CampaignStatusException("Seule une campagne ACTIVE ou PAUSED peut être terminée");
        }

        this.status = CampaignStatus.ENDED;
    }
}
