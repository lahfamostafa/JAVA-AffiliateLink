package com.affiliateLink.profile.Service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.exception.EmailAlreadyExistsException;
import com.affiliateLink.exception.ProfileNotFoundException;
import com.affiliateLink.profile.ProfileSuspended;
import com.affiliateLink.profile.Entity.Advertiser;
import com.affiliateLink.profile.Entity.Profile;
import com.affiliateLink.profile.Entity.ProfileStatus;
import com.affiliateLink.profile.Repository.ProfileRepository;
import com.affiliateLink.profile.Service.ProfileService;

@Service 
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProfileServiceImpl(ProfileRepository profileRepository, ApplicationEventPublisher eventPublisher){
        this.profileRepository = profileRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional 
    public void changeStatus(Long id){
        Profile profile = profileRepository.findById(id)
            .orElseThrow(() -> 
                new ProfileNotFoundException("Profil introuvable avec l'id : " + id));

        if (profile.getStatus() == ProfileStatus.ACTIVE) {
            profile.suspend();

            if (profile instanceof Advertiser) {
                eventPublisher.publishEvent(
                    new ProfileSuspended(profile.getId())
                );
            }
        }else profile.activate();
    }

    public void validateEmailAvailability(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "L'adresse e-mail est obligatoire."
            );
        }

        if (profileRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(
                    "Cette adresse e-mail est déjà utilisée."
            );
        }
    }
}
