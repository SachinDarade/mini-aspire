package com.aspire.takehome.miniaspire.dal.dao;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class UserDao {

    private final UserRepository userRepository;

    public UserEntity fetchByUsername(String username) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User doesn't exist by username: " + username);
        }
        return user.get();
    }

}
