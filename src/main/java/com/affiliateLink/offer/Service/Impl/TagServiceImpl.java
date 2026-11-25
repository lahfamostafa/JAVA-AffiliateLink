package com.affiliateLink.offer.Service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.affiliateLink.offer.Entity.Tag;
import com.affiliateLink.offer.Repository.TagRepository;
import com.affiliateLink.offer.Service.TagService;

@Service 
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
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

    @Override
    public Tag findById(Long id){
        return tagRepository.findById(id).get();
    }
}
