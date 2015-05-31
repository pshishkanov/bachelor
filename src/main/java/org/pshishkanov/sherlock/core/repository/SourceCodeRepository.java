package org.pshishkanov.sherlock.core.repository;

import org.pshishkanov.sherlock.core.model.SourceCode;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by pshishkanov on 12/04/15.
 */

public interface SourceCodeRepository extends MongoRepository<SourceCode, String> {



}
