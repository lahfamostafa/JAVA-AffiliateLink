package com.affiliateLink.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.profile.Entity.ProfileStatus;
import com.affiliateLink.profile.Repository.AdvertiserRepository;

@Service 
@Transactional (readOnly = true)
public class AdvertiserProfileApiImpl implements AdvertiserProfileApi{

    private final AdvertiserRepository advertiserRepository;

    public AdvertiserProfileApiImpl(AdvertiserRepository advertiserRepository){
        this.advertiserRepository = advertiserRepository;
    }

    @Override
    public boolean isActiveAdvertiser(Long advertiserId) {
        return advertiserRepository
            .findById(advertiserId)
            .filter(advertiser -> advertiser.getStatus() == ProfileStatus.ACTIVE)
            .isPresent();
    }
    
}
