package com.affiliateLink.profile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.affiliateLink.profile.dto.AdvertiserRequest;
import com.affiliateLink.profile.dto.PublisherRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api")
public class PublisherController {
    private final ProfileService profileService;

    public PublisherController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher createPublisher(@RequestBody PublisherRequest request) {
        return profileService.createPublisher(request.email(), request.firstName(), request.lastName(), request.niche(), request.bio(), request.country());
    }
    
    @PostMapping("/advertisers")
    public Advertiser createAdvertiser(@RequestBody AdvertiserRequest request) {        
        return profileService.createAdvertiser(request.email(), request.companyName(), request.sector(), request.website());
    }
    
}
