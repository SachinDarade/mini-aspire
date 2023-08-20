package com.aspire.takehome.miniaspire.service;

import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dto.UserAuthDTO;

public interface CustomerService {
    UserEntity registerCustomer(UserAuthDTO registrationDTO);
}
