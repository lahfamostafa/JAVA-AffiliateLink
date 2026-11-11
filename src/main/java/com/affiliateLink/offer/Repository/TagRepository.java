package com.affiliateLink.offer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.affiliateLink.offer.Entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long>{
    @Query ("Select t from Tag t where t.name = :name")
    Optional<Tag> findByName(@Param("name") String name);
}
