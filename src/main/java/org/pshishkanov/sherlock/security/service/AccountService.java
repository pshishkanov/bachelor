package org.pshishkanov.sherlock.security.service;

import org.pshishkanov.sherlock.security.model.Account;
import org.pshishkanov.sherlock.security.model.AccountDTO;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by pshishkanov on 21/04/15.
 */

public interface AccountService {

    Optional<Account> getAccountById(String id);

    Optional<Account> getAccountByUsername(String username);

    Collection<Account> getAllAccounts();

    Account create(AccountDTO accountDTO);
}
