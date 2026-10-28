package com.affiliateLink.profile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliateLink.profile.Entity.Advertiser;

public interface AdvertiserRepository extends JpaRepository<Advertiser,Long>{
    
}
