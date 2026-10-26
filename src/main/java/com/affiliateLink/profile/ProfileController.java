package com.affiliateLink.profile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.affiliateLink.profile.dto.AdvertiserRequest;
import com.affiliateLink.profile.dto.PublisherRequest;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping("/api")
public class ProfileController {
    private final PublisherService publisherService;
    private final AdvertiserService advertiserService;
    private final ProfileService profileService;

    public ProfileController(PublisherService publisherService , AdvertiserService advertiserService , ProfileService profileService){
        this.publisherService = publisherService;
        this.advertiserService = advertiserService;
        this.profileService = profileService;
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher createPublisher(@Valid @RequestBody PublisherRequest request) {
        return publisherService.save(request.email(), request.firstName(), request.lastName(), request.niche(), request.bio(), request.country());
    }
    
    @PostMapping("/advertisers")
    @ResponseStatus(HttpStatus.CREATED)
    public Advertiser createAdvertiser(@Valid  @RequestBody AdvertiserRequest request) {        
        return advertiserService.save(request.email(), request.companyName(), request.sector(), request.website());
    }
    
    @GetMapping("/publishers")
    public List<Publisher> getPublishers() {
        return publisherService.getPublishers();
    }

    @GetMapping("/advertisers")
    public List<Advertiser> getAdvertisers() {
        return advertiserService.getAdvertisers();
    }

    @GetMapping("/advertisers/{id}")
    public ResponseEntity<?> getAdvertiser(@PathVariable Long id) {
        Optional<Advertiser> advertiser = advertiserService.getAdvertiser(id);
        if (advertiser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(advertiser.get());
    }
    
    @GetMapping("/publishers/{id}")
    public ResponseEntity<?> getPublisher(@PathVariable Long id) {
        Optional<Publisher> publisher = publisherService.getPublisher(id);
        if (publisher.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(publisher.get());
    }
    
    @PatchMapping("/admin/profiles/{id}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable Long id){
        profileService.changeStatus(id);
        return ResponseEntity.noContent().build();
    }
    
}
