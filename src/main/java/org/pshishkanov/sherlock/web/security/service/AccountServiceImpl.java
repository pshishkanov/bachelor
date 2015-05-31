package org.pshishkanov.sherlock.web.security.service;

import org.pshishkanov.sherlock.web.security.model.Role;
import org.pshishkanov.sherlock.web.security.model.Account;
import org.pshishkanov.sherlock.web.security.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by pshishkanov on 21/04/15.
 */

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return Optional.ofNullable(accountRepository.findByUsername(username));
    }

    @Override
    public Optional<Account> create(Account account) {
        if (accountRepository.findByUsername(account.getUsername()) == null) {
            account.setPasswordHash(new BCryptPasswordEncoder().encode(account.getPassword()));
            account.setRole(Role.ROLE_USER);
            return Optional.of(accountRepository.save(account));
        } else {
            return Optional.empty();
        }
    }
}
