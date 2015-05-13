package org.pshishkanov.sherlock.security.repository;

import org.pshishkanov.sherlock.security.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by pshishkanov on 21/04/15.
 */
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByUsername(String username);
}
