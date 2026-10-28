package com.affiliateLink.offer.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table (name = "tags")
@Getter 
@Setter
public class Tag {
    @Id 
    private Long id;

    @Column (nullable = false ,unique = true)
    private String name;
}
