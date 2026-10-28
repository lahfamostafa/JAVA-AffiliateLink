package com.affiliateLink.offer.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table (name = "offres")
@Setter 
@Getter 
public class Offer {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private Long advertiserId;

    @Column (nullable = false)
    private String titre;

    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private String landingUrl;

    @Column (nullable = false)
    private String niche;

    @Column (nullable = false)
    private LocalDate createdAt;
}
