package org.pshishkanov.sherlock.web.api;

import org.pshishkanov.sherlock.web.response.Response;
import org.pshishkanov.sherlock.web.security.model.Account;
import org.pshishkanov.sherlock.web.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Response createUser(@RequestBody Account account) {
        if (accountService.create(account).isPresent()) {
            return new Response("OK", "/sherlock/api/user/create");
        } else {
            return new Response("ERROR", "/sherlock/api/user/create");
        }
    }
}
