package com.aspire.takehome.miniaspire.auth.service;

import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.auth.dto.UserAuthRequestDTO;

public interface UserRegistrationService {

    UserEntity registerCustomer(UserAuthRequestDTO registrationDTO);

}
