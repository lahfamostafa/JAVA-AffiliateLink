package com.affiliateLink.profile.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.exception.EmailAlreadyExistsException;
import com.affiliateLink.exception.ProfileNotFoundException;
import com.affiliateLink.profile.Entity.Profile;
import com.affiliateLink.profile.Repository.ProfileRepository;

@Service 
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    @Transactional 
    public void changeStatus(Long id){
        Profile profile = profileRepository.findById(id)
            .orElseThrow(() -> 
                new ProfileNotFoundException("Profil introuvable avec l'id : " + id));

        switch (profile.getStatus()) {
            case PENDING:
                profile.activate();
                break;
        
            case ACTIVE:
                profile.suspend();
                break;
        
            case SUSPENDED:
                profile.pend();
                break;
        }
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
