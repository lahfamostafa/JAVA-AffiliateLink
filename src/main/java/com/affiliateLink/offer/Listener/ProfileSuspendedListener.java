package com.affiliateLink.offer.Listener;

import org.springframework.stereotype.Component;

import com.affiliateLink.offer.Service.CampaignService;
import com.affiliateLink.profile.ProfileSuspended;

import org.springframework.modulith.events.ApplicationModuleListener;

@Component 
public class ProfileSuspendedListener {
    private final CampaignService campaignService;

    public ProfileSuspendedListener(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @ApplicationModuleListener
public void on(ProfileSuspended event) {
    System.out.println("EVENT RECU : " + event.profileId());

    campaignService.pauseActiveCampaignsByAdvertiser(
        event.profileId()
    );
}
}
