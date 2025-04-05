package com.Astra.journalApp.repository;


import com.Astra.journalApp.Repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSaveNewUser(){
            userRepository.getUsersForSA();
    }
}



