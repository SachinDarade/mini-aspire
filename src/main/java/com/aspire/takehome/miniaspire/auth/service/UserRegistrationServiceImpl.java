package com.aspire.takehome.miniaspire.auth.service;

import com.aspire.takehome.miniaspire.auth.dto.UserAuthRequestDTO;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserEntity registerCustomer(UserAuthRequestDTO registrationDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        return userRepository.save(user);
    }
}
