package com.affiliateLink.profile.Service;

import java.util.List;
import java.util.Optional;

import com.affiliateLink.profile.Entity.Advertiser;
import com.affiliateLink.profile.dto.AdvertiserRequest;

public interface AdvertiserService {

    Advertiser save(AdvertiserRequest request);

    List<Advertiser> getAdvertisers();

    Optional<Advertiser> getAdvertiser(Long id);
}
