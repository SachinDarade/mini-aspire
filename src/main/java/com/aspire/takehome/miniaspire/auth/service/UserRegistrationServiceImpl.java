package com.aspire.takehome.miniaspire.auth.service;

import com.aspire.takehome.miniaspire.auth.dto.UserAuthDTO;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;

    @Override
    public UserEntity registerCustomer(UserAuthDTO registrationDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        return userRepository.save(user);
    }
}
