package org.pshishkanov.sherlock.web.api;

import org.pshishkanov.sherlock.web.response.ApiResponse;
import org.pshishkanov.sherlock.web.security.model.Account;
import org.pshishkanov.sherlock.web.security.model.AccountValidator;
import org.pshishkanov.sherlock.web.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by pshishkanov on 14/05/15.
 */

@RestController
@RequestMapping(value = "/sherlock/api")
public class UserController {

    private AccountService accountService;

    @Autowired
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/create")
    public ApiResponse createUser(@Valid @RequestBody Account account) {
        if (accountService.create(account).isPresent()) {
            return new ApiResponse("OK");
        } else {
            return new ApiResponse("ERROR");
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new AccountValidator());
    }
}
