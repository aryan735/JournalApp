package com.Astra.journalApp.controller;

import com.Astra.journalApp.Repository.UserRepo;
import com.Astra.journalApp.entity.User;
import com.Astra.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userDb = userService.findByUsername(username);
        if(userDb !=null){
            userDb.setUsername(user.getUsername());
            userDb.setPassword(user.getPassword());
            userService.saveNewUser(userDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
