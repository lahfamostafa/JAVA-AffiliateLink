package com.affiliateLink.profile;

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

    public void setId(Long id) {
        this.id = id;
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

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
