package org.pshishkanov.sherlock.web.api;

import org.pshishkanov.sherlock.core.model.SourceCode;
import org.pshishkanov.sherlock.web.security.model.Account;
import org.pshishkanov.sherlock.web.security.service.AccountService;
import org.pshishkanov.sherlock.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pshishkanov on 22/04/15.
 */

@RestController
@RequestMapping(value = "/sherlock/api")
public class WebController {

    private AccountService accountService;

    @Autowired
    public WebController(AccountService accountService) {
        this.accountService = accountService;
    }





    /* =================== Test API ==================== */
    @RequestMapping(method = RequestMethod.GET, value = "/ok")
    public Response getOK() {
        return new Response("OK", "/sherlock/api/ok");
    }
}
