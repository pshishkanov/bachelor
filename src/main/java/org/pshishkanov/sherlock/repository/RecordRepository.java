package org.pshishkanov.sherlock.repository;

import org.pshishkanov.sherlock.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by pshishkanov on 12/04/15.
 */

public interface RecordRepository extends MongoRepository<Record, String> {

}
