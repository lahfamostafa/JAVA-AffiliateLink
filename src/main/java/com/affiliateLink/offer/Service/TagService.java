package com.affiliateLink.offer.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.affiliateLink.offer.Entity.Tag;
import com.affiliateLink.offer.Repository.TagRepository;

@Service 
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public Set<Tag> getOrCreateTags(Set<String> tagNames){
        Set<Tag> tags = new HashSet<>();

        if (tagNames == null) {
            return tags;
        }
        for (String tagName : tagNames) {
            String normalizedName = tagName.trim().toLowerCase();
            Tag tag = tagRepository.findByName(normalizedName).orElseGet(() ->  {
                Tag newTag = new Tag(normalizedName);
                return tagRepository.save(newTag);
            });
            tags.add(tag);
        }
        return tags;
    }

    public Tag findById(Long id){
        return tagRepository.findById(id).get();
    }
}
