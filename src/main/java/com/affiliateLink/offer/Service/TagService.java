package com.affiliateLink.offer.Service;

import java.util.Set;

import com.affiliateLink.offer.Entity.Tag;

public interface TagService {
    
    Set<Tag> getOrCreateTags(Set<String> tagNames);

    Tag findById(Long id);
}
