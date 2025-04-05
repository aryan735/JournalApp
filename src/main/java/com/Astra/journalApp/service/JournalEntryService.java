package com.Astra.journalApp.service;

import com.Astra.journalApp.Repository.JournalRepo;
import com.Astra.journalApp.entity.JournalEntry;
import com.Astra.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {


    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private  UserService userService;

    @Transactional
    public void saveEntry(JournalEntry myEntry, String userName){
        try {
            User user = userService.findByUsername(userName);
            myEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalRepo.save(myEntry);
            user.getJournalEntries().add(saved);

            userService.saveUser(user);

        }catch (Exception e){
            System.out.println(e);
            throw  new RuntimeException("An error occurred while saving the entry....");
        }


    }
    public void saveEntry(JournalEntry myEntry){
      journalRepo.save(myEntry);

    }

    public List<JournalEntry> getAll(){
        return journalRepo.findAll();
    }
  public Optional<JournalEntry> findById(ObjectId myId){
        return journalRepo.findById(myId);
  }

  @Transactional
  public boolean deleteById(ObjectId myId, String userName){
      boolean removed =false;
      try {

          User user = userService.findByUsername(userName);
           removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
          if(removed){
              userService.saveUser(user);
              journalRepo.deleteById(myId);
          }
      }catch (Exception e){
          System.out.println(e);
          throw  new RuntimeException("An error occurred while deleting an Entry! ",e);
      }
      return removed;
  }
}
//Controller ---> Service ----> Repository