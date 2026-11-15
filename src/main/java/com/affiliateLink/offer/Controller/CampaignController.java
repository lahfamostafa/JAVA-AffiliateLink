package com.affiliateLink.offer.Controller;

import com.affiliateLink.offer.Entity.CampaignStatus;
import com.affiliateLink.offer.Service.CampaignService;
import com.affiliateLink.offer.dto.CampaignRequest;
import com.affiliateLink.offer.dto.CampaignResponse;
import com.affiliateLink.offer.dto.CampaignStatusRequest;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
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

    @GetMapping("/campaigns")
    public List<CampaignResponse> campaignsStatus(@RequestParam CampaignStatus status) {
        return campaignService.getCampaignsByStatus(status);
    }

    @PatchMapping("/campaigns/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable Long id , @RequestBody CampaignStatusRequest status){
        campaignService.updateStatus(id,status);
    }
    
}
