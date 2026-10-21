package com.affiliateLink.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "advertisers")
public class Advertiser extends Profile {

    @Column(nullable = false)
    private String companyName;

    private String sector;

    private String website;

    protected Advertiser() {
    }

    public Advertiser(
            String email,
            String companyName,
            String sector,
            String website) {

        super(email);
        this.companyName = companyName;
        this.sector = sector;
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSector() {
        return sector;
    }

    public String getWebsite() {
        return website;
    }
}