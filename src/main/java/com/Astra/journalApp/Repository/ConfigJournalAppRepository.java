package com.Astra.journalApp.Repository;

import com.Astra.journalApp.entity.ConfigJournalEntity;
import com.Astra.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalEntity, ObjectId> {
}
