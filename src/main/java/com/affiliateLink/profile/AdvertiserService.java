package com.affiliateLink.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service 
public class AdvertiserService {

    private final AdvertiserRepository advertiserRepository;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public AdvertiserService(AdvertiserRepository advertiserRepository , ProfileRepository profileRepository , ProfileService profileService){
        this.advertiserRepository = advertiserRepository;
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    public Advertiser save(String email , String companyName , String sector , String website){
        profileService.validateEmailAvailability(email);
        Advertiser advertiser = new Advertiser(email, companyName, sector, website);
        return profileRepository.save(advertiser);
    }

    public List<Advertiser> getAdvertisers(){
        return advertiserRepository.findAll();
    }

    public Optional<Advertiser> getAdvertiser(Long id){
        return advertiserRepository.findById(id);
    }

}
