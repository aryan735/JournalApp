package com.Astra.journalApp.service;


import com.Astra.journalApp.Repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
//@AutoConfigureMockMvc
public class UserServiceTests {
@Autowired
private UserRepo userRepo;

//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Raj",
//            "Ram",
//            "Anu"
//    })
//    public void testFindByUsername(String name){
//
//     assertNotNull(userRepo.findByUsername(name),"Not found "+name);
////        assertFalse(user.getJournalEntries().isEmpty());
//    }
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,2,3",
//            "2,2,4",
//            "3,3,6"
//    })
//    public void test(int a, int b,int expected){
//        assertEquals(expected,a+b);
//    }





}
