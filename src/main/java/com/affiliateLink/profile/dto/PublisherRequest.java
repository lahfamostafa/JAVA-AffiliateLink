package com.affiliateLink.profile.dto;

import jakarta.validation.constraints.*;

public record PublisherRequest(
    @NotBlank (message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    String email,
    
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 100, message = "Le prénom ne doit pas dépasser 100 caractères")
    String firstName , 
    
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
    String lastName , 
    
    @Size(max = 255, message = "La niche ne doit pas dépasser 255 caractères")
    String niche , 
    
    @Size(max = 2000, message = "La bio ne doit pas dépasser 2000 caractères")
    String bio , 
    
    @Size(max = 100, message = "Le pays ne doit pas dépasser 100 caractères")
    String country
) {} 
