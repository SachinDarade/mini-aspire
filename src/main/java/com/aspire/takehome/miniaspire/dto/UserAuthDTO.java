package com.aspire.takehome.miniaspire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserAuthDTO {
    private String username;
    private String password;
}

