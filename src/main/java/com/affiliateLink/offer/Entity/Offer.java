
package com.affiliateLink.offer.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table(name = "offers")
@Setter 
@Getter 
public class Offer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "advertiser_id", nullable = false)
    private Long advertiserId;

    @Column (nullable = false)
    private String title;

    @Column (columnDefinition = "TEXT")
    private String description;

    @Column (name = "landing_url", nullable = false)
    private String landingUrl;

    @Column (nullable = false)
    private String niche;

    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany 
    @JoinTable (
        name = "offer_tags",
        joinColumns = @JoinColumn (name = "offer_id"),
        inverseJoinColumns = @JoinColumn (name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "offer")
    private Set<Campaign> campaigns = new HashSet<>();

    public Offer(Long advertiserId, String title, String description, String landingUrl, String niche, Set<Tag> tags) {
        this.advertiserId = advertiserId;
        this.title = title;
        this.description = description;
        this.landingUrl = landingUrl;
        this.niche = niche;
        this.tags = tags;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
