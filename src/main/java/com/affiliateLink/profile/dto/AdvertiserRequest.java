package com.affiliateLink.profile.dto;

import jakarta.validation.constraints.*;

public record AdvertiserRequest(

    @NotBlank (message = "L'email est obligatoire")
    @Email (message = "L'email doit être valide")
    String email,

    @NotBlank(message = "Le nom de l'entreprise est obligatoire")
    @Size (max = 255, message = "Le nom de l'entreprise ne doit pas dépasser 255 caractères")
    String companyName,

    @Size(max = 255, message = "Le secteur ne doit pas dépasser 255 caractères")
    String sector,

    @Size(max = 255, message = "Le site web ne doit pas dépasser 255 caractères")
    String website

) {}