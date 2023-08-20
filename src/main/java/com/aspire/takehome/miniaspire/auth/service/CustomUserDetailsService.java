package com.aspire.takehome.miniaspire.auth.service;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.dao.UserDao;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = null;
        try {
            user = userDao.fetchByUsername(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

}
