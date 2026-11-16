package com.affiliateLink.exception;

public class CampaignNotExistsException extends RuntimeException{
    public CampaignNotExistsException(String message){
        super(message);
    }
}
