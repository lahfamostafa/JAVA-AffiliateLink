package com.affiliateLink.exception;

public class CampaignAlreadyExistsException extends RuntimeException {
    public CampaignAlreadyExistsException(String message){
        super(message);
    }
}
