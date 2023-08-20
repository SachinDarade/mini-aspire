package com.aspire.takehome.miniaspire.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserAuthResponseDTO {
    private String username;
    private String accessToken;
}
