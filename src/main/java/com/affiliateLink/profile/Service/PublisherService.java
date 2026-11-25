package com.affiliateLink.profile.Service;

import java.util.List;
import java.util.Optional;

import com.affiliateLink.profile.Entity.Publisher;
import com.affiliateLink.profile.dto.PublisherRequest;

public interface PublisherService {
    
    Publisher save(PublisherRequest request);

    List<Publisher> getPublishers();

    Optional<Publisher> getPublisher(Long id);
}
