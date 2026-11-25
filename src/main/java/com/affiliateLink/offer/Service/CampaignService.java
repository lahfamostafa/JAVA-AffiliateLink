package com.affiliateLink.offer.Service;

import java.util.List;

import com.affiliateLink.offer.Entity.CampaignStatus;
import com.affiliateLink.offer.dto.CampaignRequest;
import com.affiliateLink.offer.dto.CampaignResponse;
import com.affiliateLink.offer.dto.CampaignStatusRequest;

public interface CampaignService {

    CampaignResponse create(Long offerId, CampaignRequest request);

    List<CampaignResponse> getCampaignsByStatus(CampaignStatus status);

    void updateStatus(Long id, CampaignStatusRequest status);

    void pauseActiveCampaignsByAdvertiser(Long advertiserId);

}
