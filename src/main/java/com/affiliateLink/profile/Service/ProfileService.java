package com.affiliateLink.profile.Service;

public interface ProfileService {
    
    void changeStatus(Long id);

    void validateEmailAvailability(String email);
}
