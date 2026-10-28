package com.affiliateLink.profile.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@Table(name = "profiles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Profile {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfileStatus status = ProfileStatus.PENDING;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt;

    protected Profile() { }

    protected Profile(String email) {
        this.email = email;
        this.status = ProfileStatus.PENDING;
    }

    @PrePersist 
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void activate() {
        this.status = ProfileStatus.ACTIVE;
    }

    public void suspend() {
        this.status = ProfileStatus.SUSPENDED;
    }

    public void pend() {
        this.status = ProfileStatus.PENDING;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
