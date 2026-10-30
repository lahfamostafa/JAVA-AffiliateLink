package com.affiliateLink.offer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliateLink.offer.Entity.Offer;

public interface OfferRepository extends JpaRepository<Offer,Long>{
    
}
