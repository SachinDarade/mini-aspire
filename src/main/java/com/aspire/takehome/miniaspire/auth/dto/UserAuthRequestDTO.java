package com.aspire.takehome.miniaspire.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class UserAuthRequestDTO {
    private String username;
    private String password;
}

