package com.aspire.takehome.miniaspire.auth.controller;

import com.aspire.takehome.miniaspire.auth.dto.UserAuthRequestDTO;
import com.aspire.takehome.miniaspire.auth.dto.UserAuthResponseDTO;
import com.aspire.takehome.miniaspire.auth.dto.UserRegistrationResponseDTO;
import com.aspire.takehome.miniaspire.auth.service.UserRegistrationService;
import com.aspire.takehome.miniaspire.auth.util.JwtUtil;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/auth")
@Slf4j
public class AuthController {

    private final UserRegistrationService userRegistrationService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDTO> registerCustomer(@RequestBody UserAuthRequestDTO registrationDTO) {
        UserEntity user = userRegistrationService.registerCustomer(registrationDTO);
        return new ResponseEntity<>(
                new UserRegistrationResponseDTO(user.getId()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponseDTO> generateToken(@RequestBody UserAuthRequestDTO authRequest) throws Exception {
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
        return new ResponseEntity<>(
                new UserAuthResponseDTO(
                        authRequest.getUsername(),
                        jwtUtil.generateToken(authRequest.getUsername())
                ),
                HttpStatus.OK
        );
    }

}

