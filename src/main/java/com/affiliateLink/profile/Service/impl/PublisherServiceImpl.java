package com.affiliateLink.profile.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.affiliateLink.profile.Entity.Publisher;
import com.affiliateLink.profile.Repository.ProfileRepository;
import com.affiliateLink.profile.Repository.PublisherRepository;
import com.affiliateLink.profile.Service.ProfileService;
import com.affiliateLink.profile.Service.PublisherService;
import com.affiliateLink.profile.dto.PublisherRequest;

@Service 
public class PublisherServiceImpl implements PublisherService{
    
    private final ProfileRepository profileRepository;
    private final PublisherRepository publisherRepository;
    private final ProfileService profileService;
    
    public PublisherServiceImpl(ProfileRepository profileRepository , PublisherRepository publisherRepository , ProfileService profileService){
        this.profileRepository = profileRepository;
        this.publisherRepository = publisherRepository;
        this.profileService = profileService;
    }

    public Publisher save(PublisherRequest request){
        profileService.validateEmailAvailability(request.email());
        Publisher publisher = new Publisher(request.email(), request.firstName(), request.lastName(), request.niche(), request.bio(), request.country());
        return profileRepository.save(publisher);
    }

    public List<Publisher> getPublishers(){
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisher(Long id){
        return publisherRepository.findById(id);
    }

}
