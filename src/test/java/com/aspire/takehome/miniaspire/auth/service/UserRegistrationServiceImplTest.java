package com.aspire.takehome.miniaspire.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.aspire.takehome.miniaspire.auth.dto.UserAuthRequestDTO;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

public class UserRegistrationServiceImplTest {

    private UserRegistrationServiceImpl userRegistrationService;
    private UserRepository userRepository;

    @Before
    public void setup() {
        userRepository = mock(UserRepository.class);
        userRegistrationService = new UserRegistrationServiceImpl(userRepository);
    }

    @Test
    public void testRegisterCustomer_Success() {
        UserAuthRequestDTO registrationDTO = new UserAuthRequestDTO("testuser", "password");
        UserEntity savedUser = new UserEntity();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setPassword("password");

        when(userRepository.save(any())).thenReturn(savedUser);

        UserEntity registeredUser = userRegistrationService.registerCustomer(registrationDTO);

        assertNotNull(registeredUser);
        assertEquals(savedUser.getUsername(), registeredUser.getUsername());
        assertEquals(savedUser.getPassword(), registeredUser.getPassword());

        verify(userRepository, times(1)).save(any());
    }

}
