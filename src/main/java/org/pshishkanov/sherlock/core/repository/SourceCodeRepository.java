package org.pshishkanov.sherlock.core.repository;

import org.pshishkanov.sherlock.core.model.SourceCode;
import org.pshishkanov.sherlock.web.security.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pshishkanov on 12/04/15.
 */

public interface SourceCodeRepository extends MongoRepository<SourceCode, String> {

    List<SourceCode> findByLanguage(String language);

}
