package com.affiliateLink.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional 
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public Publisher createPublisher(String email,String firstName , String lastName , String niche , String bio , String country){
        validateEmailAvailability(email);
        Publisher publisher = new Publisher(email, firstName, lastName, niche, bio, country);
        return profileRepository.save(publisher);
    }

    public Advertiser createAdvertiser(String email , String companyName , String sector , String website){
        validateEmailAvailability(email);
        Advertiser advertiser = new Advertiser(email, companyName, sector, website);
        return profileRepository.save(advertiser);
    }

    private void validateEmailAvailability(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "L'adresse e-mail est obligatoire."
            );
        }

        if (profileRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Cette adresse e-mail est déjà utilisée."
            );
        }
    }
}
