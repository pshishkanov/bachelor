package org.pshishkanov.sherlock.security.service;

import org.pshishkanov.sherlock.security.model.Role;
import org.pshishkanov.sherlock.security.model.Account;
import org.pshishkanov.sherlock.security.model.AccountDTO;
import org.pshishkanov.sherlock.security.repository.AccountRepository;
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
    public Optional<Account> getAccountById(String id) {
        return Optional.ofNullable(accountRepository.findOne(id));
    }

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return Optional.ofNullable(accountRepository.findByUsername(username));
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(AccountDTO accountDTO) {
        if (accountRepository.findByUsername(accountDTO.getUsername()) == null) {
            Account account = new Account();
            account.setUsername(accountDTO.getUsername());
            account.setPasswordHash(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
            account.setRole(Role.ROLE_USER);
            return accountRepository.save(account);
        } else {
            return null;
        }
    }
}
