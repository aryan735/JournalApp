package com.Astra.journalApp.service;

import com.Astra.journalApp.Repository.UserRepo;
import com.Astra.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {


    @Autowired
    private UserRepo userRepo;


/*  private static  final Logger logger = LoggerFactory.getLogger(UserService.class);
 we won't use this instead of this we can use the @Slf4j annotation
 it created all getters and setter and the instance by the name log.
    */

    private static  final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return true;
        }catch (Exception e){
            //there will print only error,warn and info becoz it is enabled by default
            log.error("Error Occurred for {}",user.getUsername());
            log.warn("hehhhhhhh");
            log.info("hehhhhhhh");
            log.debug("hehhhhhhh");
            log.trace("hehhhhhhh");
            return false;
        }
    }

    /*
    Important Note :
    2025-03-17T15:51:14.085+05:30   --> Time Stamp
     ERROR - Login level
     5560 ---  process id
      [nio-8081-exec-2] ---> Thread's name
      c.Astra.journalApp.service.UserService  ----> Logger name
       Error Occurred Raj  ----> Message
*/
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public void saveUser(User myEntry){
        userRepo.save(myEntry);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }
  public Optional<User> findById(ObjectId myId){
        return userRepo.findById(myId);
  }
  public void deleteById(ObjectId myId){
        userRepo.deleteById(myId);
  }

  public User findByUsername(String username){
      return  userRepo.findByUsername(username);
  }
}
//Controller ---> Service ----> Repository