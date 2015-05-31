package org.pshishkanov.sherlock.web.security.service;

import org.pshishkanov.sherlock.web.security.model.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by pshishkanov on 21/04/15.
 */

public interface AccountService {

    Optional<Account> getAccountByUsername(String username);

    Optional<Account> create(Account account);
}
