package com.aspire.takehome.miniaspire.auth.controller;

import com.aspire.takehome.miniaspire.auth.util.JwtUtil;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.auth.dto.UserAuthDTO;
import com.aspire.takehome.miniaspire.auth.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mini-aspire/v1/auth")
@Slf4j
public class AuthController {
    private final UserRegistrationService userRegistrationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody UserAuthDTO registrationDTO) {
        UserEntity user = userRegistrationService.registerCustomer(registrationDTO);
        // Generate JWT token

        return ResponseEntity.ok("");
    }

    @GetMapping     // TODO: Remove this
    public String welcome(Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        log.info("auth user {}", userDetails.getUsername());
        return "Hello bros";
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody UserAuthDTO authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

}

