package com.Astra.journalApp.service;

import com.Astra.journalApp.Repository.UserRepo;
import com.Astra.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

   @Mock
   private UserRepo userRepo;

   @Disabled
   @BeforeEach
   void setUp(){
       MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    public void loadUserByUsernameTest(){
    when(userRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("abc").roles(new ArrayList<>()).build());
        UserDetails user = userDetailService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
