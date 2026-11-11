package com.affiliateLink.offer.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.affiliateLink.offer.Service.CampaignService;
import com.affiliateLink.offer.dto.CampaignRequest;
import com.affiliateLink.offer.dto.CampaignResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;


@RestController 
@RequestMapping("/api")
public class CampaignController {
    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @PostMapping("/offres/{id}/campaigns")
    public CampaignResponse createCampaign(@PathVariable Long id, @Valid @RequestBody CampaignRequest request) {        
        return campaignService.create(id, request);
    }
    
}
