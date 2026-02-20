package com.ecommerce.backend.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token ;
    private String email ;
    private String fullName;
    private String role ;
    private UUID userId ;

}
