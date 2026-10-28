package com.affiliateLink.offer.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table (name = "campaigns")
@Getter 
@Setter 
public class Campaign {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private LocalDate startDate;

    @Column (nullable = false)
    private LocalDate endDate;

    @Column (nullable = false)
    private BigDecimal budget;

    @Column (nullable = false)
    private String payoutModel;

    @Column (nullable = false)
    private CampaignStatus status;
}
