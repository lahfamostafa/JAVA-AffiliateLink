package com.affiliateLink.profile.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.affiliateLink.profile.Entity.Publisher;
import com.affiliateLink.profile.Repository.ProfileRepository;
import com.affiliateLink.profile.Repository.PublisherRepository;

@Service 
public class PublisherService {
    
    private final ProfileRepository profileRepository;
    private final PublisherRepository publisherRepository;
    private final ProfileService profileService;
    
    public PublisherService(ProfileRepository profileRepository , PublisherRepository publisherRepository , ProfileService profileService){
        this.profileRepository = profileRepository;
        this.publisherRepository = publisherRepository;
        this.profileService = profileService;
    }

    public Publisher save(String email,String firstName , String lastName , String niche , String bio , String country){
        profileService.validateEmailAvailability(email);
        Publisher publisher = new Publisher(email, firstName, lastName, niche, bio, country);
        return profileRepository.save(publisher);
    }

    public List<Publisher> getPublishers(){
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisher(Long id){
        return publisherRepository.findById(id);
    }

}
