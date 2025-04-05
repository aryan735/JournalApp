package com.Astra.journalApp.cache;


import com.Astra.journalApp.Repository.ConfigJournalAppRepository;
import com.Astra.journalApp.entity.ConfigJournalEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {


    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appCache;

    @PostConstruct   //when the AppCache bean create this method will call automatically.
    public void init(){
        appCache= new HashMap<>();//this will clear the hashmap and will do new entries.
        List<ConfigJournalEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalEntity configJournalEntity : all){
            appCache.put(configJournalEntity.getKey(), configJournalEntity.getValue());
        }
    }
}
