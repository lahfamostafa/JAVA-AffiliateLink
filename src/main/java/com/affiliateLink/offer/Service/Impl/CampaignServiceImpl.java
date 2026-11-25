
package com.affiliateLink.offer.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.exception.*;
import com.affiliateLink.offer.Entity.*;
import com.affiliateLink.offer.Repository.CampaignRepository;
import com.affiliateLink.offer.Service.CampaignService;
import com.affiliateLink.offer.Service.OfferService;
import com.affiliateLink.offer.dto.*;

@Service 
public class CampaignServiceImpl implements CampaignService{
    private final CampaignRepository campaignRepository;
    private final OfferService offerService;

    public CampaignServiceImpl(CampaignRepository campaignRepository, OfferService offerService) {
        this.campaignRepository = campaignRepository;
        this.offerService = offerService;
    }

    @Override 
    @Transactional
    public CampaignResponse create(Long offerId, CampaignRequest request) {

        Offer offer = offerService.findById(offerId);

        if (campaignRepository.existsByOffer_IdAndNameIgnoreCase(
            offerId, request.name())) {

            throw new CampaignAlreadyExistsException(
                "Une campagne avec ce nom existe déjà pour cette offre"
            );
        }

        Campaign campaign = new Campaign(
            request.name(),
            request.startDate(),
            request.endDate(),
            request.budget(),
            request.payoutModel(),
            request.payoutValue(),
            offer
        );

        Campaign savedCampaign = campaignRepository.save(campaign);

        return toResponse(savedCampaign);
    }

    @Override
    @Transactional
    public List<CampaignResponse> getCampaignsByStatus(CampaignStatus status) {

        List<Campaign> campaigns = campaignRepository.getCampaignsByStatus(status);

        List<CampaignResponse> responses = new ArrayList<>();

        for (Campaign campaign : campaigns) {
            responses.add(toResponse(campaign));
        }

        return responses;
    }

    @Override
    @Transactional
    public void updateStatus(Long id, CampaignStatusRequest status) {

        Campaign campaign = campaignRepository.findById(id)
            .orElseThrow(() ->
                new CampaignNotExistsException(
                    "Cette campagne n'existe pas"
                )
            );

        switch (status.status()) {

            case ACTIVE:
                Optional<Campaign> activeCampaign =
                    campaignRepository.findByOffer_IdAndStatus(
                        campaign.getOffer().getId(),
                        CampaignStatus.ACTIVE
                    );

                if (activeCampaign.isPresent() && !activeCampaign.get().getId().equals(campaign.getId())) {
                    activeCampaign.get().pause();
                }

                campaign.activate();
                break;

            case PAUSED:

                campaign.pause();
                break;

            case ENDED:

                campaign.end();
                break;

            case DRAFT:

                throw new CampaignStatusException(
                    "Impossible de revenir au statut DRAFT"
                );
        }
    }

    @Override
    @Transactional
    public void pauseActiveCampaignsByAdvertiser(Long advertiserId){
        List<Campaign> campaigns = campaignRepository.findByOffer_AdvertiserIdAndStatus(advertiserId, CampaignStatus.ACTIVE);

        for (Campaign campaign : campaigns) {
            campaign.pause();
        }
    }

    private CampaignResponse toResponse(Campaign campaign) {

        return new CampaignResponse(
            campaign.getId(),
            campaign.getOffer().getId(),
            campaign.getName(),
            campaign.getStartDate(),
            campaign.getEndDate(),
            campaign.getBudget(),
            campaign.getPayoutModel(),
            campaign.getPayoutValue(),
            campaign.getStatus()
        );
    }

}
