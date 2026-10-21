package com.affiliateLink.profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;;
public interface ProfileRepository extends JpaRepository<Profile,Long>{
    Optional<Profile> findByEmail(String email);
    boolean existsByEmail(String email);
}
