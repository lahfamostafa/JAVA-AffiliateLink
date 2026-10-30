package com.affiliateLink.offer.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table (name = "tags")
@Getter 
@Setter
public class Tag {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false ,unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Offer> offers = new HashSet<>();

    @PrePersist
    @PreUpdate
    protected void normalizeName() {
        if (name != null) {
            name = name.trim().toLowerCase(java.util.Locale.ROOT);
        }
    }
}
