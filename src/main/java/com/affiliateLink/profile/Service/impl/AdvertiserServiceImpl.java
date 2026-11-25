package com.affiliateLink.profile.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.affiliateLink.profile.Entity.Advertiser;
import com.affiliateLink.profile.Repository.AdvertiserRepository;
import com.affiliateLink.profile.Repository.ProfileRepository;
import com.affiliateLink.profile.Service.AdvertiserService;
import com.affiliateLink.profile.Service.ProfileService;
import com.affiliateLink.profile.dto.AdvertiserRequest;

@Service 
public class AdvertiserServiceImpl implements AdvertiserService{

    private final AdvertiserRepository advertiserRepository;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public AdvertiserServiceImpl(AdvertiserRepository advertiserRepository , ProfileRepository profileRepository , ProfileService profileService){
        this.advertiserRepository = advertiserRepository;
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    public Advertiser save(AdvertiserRequest request){
        profileService.validateEmailAvailability(request.email());
        Advertiser advertiser = new Advertiser(request.email(), request.companyName(), request.sector(), request.website());
        return profileRepository.save(advertiser);
    }

    public List<Advertiser> getAdvertisers(){
        return advertiserRepository.findAll();
    }

    public Optional<Advertiser> getAdvertiser(Long id){
        return advertiserRepository.findById(id);
    }

}
