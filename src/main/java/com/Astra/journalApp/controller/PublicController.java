package com.Astra.journalApp.controller;

import com.Astra.journalApp.entity.User;
import com.Astra.journalApp.service.UserDetailServiceImpl;
import com.Astra.journalApp.service.UserService;
import com.Astra.journalApp.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {


    private final UserService userService;

    private final AuthenticationManager authenticationManager;

      private final JwtUtil jwtUtil;

      private final UserDetailServiceImpl userDetailService;
    @Autowired
    public PublicController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailServiceImpl userDetailService) {
        this.userService = userService;
        this.authenticationManager=authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/health-check")
    public  String healthCheck(){
        return "ok";
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }catch (Exception e){
log.error("Exception occurred while createAuthenticationToken ",e);
return new ResponseEntity<>("Incorrect username or passwored ", HttpStatus.BAD_REQUEST);
        }
    }

}
