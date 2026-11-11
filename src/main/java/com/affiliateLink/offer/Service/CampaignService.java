package com.affiliateLink.offer.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.offer.Entity.Campaign;
import com.affiliateLink.offer.Entity.Offer;
import com.affiliateLink.offer.Repository.CampaignRepository;
import com.affiliateLink.offer.dto.CampaignRequest;
import com.affiliateLink.offer.dto.CampaignResponse;


@Service 
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final OfferService offerService;

    public CampaignService(CampaignRepository campaignRepository, OfferService offerService){
        this.campaignRepository = campaignRepository;
        this.offerService = offerService;
    }

    @Transactional 
    public CampaignResponse create(Long offerId, CampaignRequest request){
        Offer offer = offerService.findById(offerId);
        Campaign campaign = new Campaign(request.name(), request.startDate(), request.endDate(), request.budget(), request.payoutModel(), request.payoutValue(), offer);
        Campaign savedCampaign = campaignRepository.save(campaign);
        return new CampaignResponse(savedCampaign.getId(), savedCampaign.getOffer().getId(), savedCampaign.getName(), savedCampaign.getStartDate(), savedCampaign.getEndDate(), savedCampaign.getBudget(), savedCampaign.getPayoutModel(), savedCampaign.getPayoutValue(), savedCampaign.getStatus());
    }
}
