package com.affiliateLink.profile;

import jakarta.persistence.*;

@Entity 
@Table(name = "publishers")
public class Publisher extends Profile{
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String niche;

    @Column(length = 2000)
    private String bio;
    private String country;

    protected Publisher(){}
    
    public Publisher(String email, String firstName, String lastName, String niche, String bio, String country) {
        super(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.niche = niche;
        this.bio = bio;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNiche() {
        return niche;
    }

    public String getBio() {
        return bio;
    }

    public String getCountry() {
        return country;
    }

}
