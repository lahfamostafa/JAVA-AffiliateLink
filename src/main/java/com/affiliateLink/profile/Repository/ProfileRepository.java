package com.affiliateLink.profile.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliateLink.profile.Entity.Profile;;
public interface ProfileRepository extends JpaRepository<Profile,Long>{
    Optional<Profile> findByEmail(String email);
    boolean existsByEmail(String email);
}
