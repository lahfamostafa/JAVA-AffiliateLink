package com.affiliateLink.offer.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliateLink.offer.Entity.Campaign;
import com.affiliateLink.offer.Entity.CampaignStatus;

public interface CampaignRepository extends JpaRepository<Campaign,Long>{
    List<Campaign> getCampaignsByStatus(CampaignStatus status);
    boolean existsByOffer_IdAndNameIgnoreCase(Long offerId, String name);
    Optional<Campaign> findByOffer_IdAndStatus(Long offerId, CampaignStatus status);
}
