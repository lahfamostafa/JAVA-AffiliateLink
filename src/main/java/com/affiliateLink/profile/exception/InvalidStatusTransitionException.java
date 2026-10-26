package com.affiliateLink.profile.exception;

public class InvalidStatusTransitionException extends RuntimeException{
    public InvalidStatusTransitionException(String message){
        super(message);
    }
}
