package com.aspire.takehome.miniaspire.auth.service;

import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.auth.dto.UserAuthDTO;

public interface CustomerService {
    UserEntity registerCustomer(UserAuthDTO registrationDTO);
}
