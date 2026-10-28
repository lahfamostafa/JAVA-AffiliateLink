package com.affiliateLink.profile.Controller;

import org.springframework.web.bind.annotation.*;

import com.affiliateLink.profile.Entity.Advertiser;
import com.affiliateLink.profile.Entity.Publisher;
import com.affiliateLink.profile.Service.AdvertiserService;
import com.affiliateLink.profile.Service.ProfileService;
import com.affiliateLink.profile.Service.PublisherService;
import com.affiliateLink.profile.dto.*;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.*;

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
