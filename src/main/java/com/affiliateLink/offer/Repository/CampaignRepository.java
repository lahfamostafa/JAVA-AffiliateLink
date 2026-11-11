package com.affiliateLink.offer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliateLink.offer.Entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign,Long>{
    
}
