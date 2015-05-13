package org.pshishkanov.sherlock.controller.api;

import org.pshishkanov.sherlock.model.RecordDTO;
import org.pshishkanov.sherlock.security.model.AccountDTO;
import org.pshishkanov.sherlock.security.service.AccountService;
import org.pshishkanov.sherlock.controller.response.CheckCallback;
import org.pshishkanov.sherlock.controller.response.Response;
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

    /* =================== Check API =================== */
    @RequestMapping(method = RequestMethod.POST, value = "/check")
    public CheckCallback checkCode(@RequestBody RecordDTO recordDTO) {
        return null;
        // return new Response("OK", "/sherlock/api/check");
    }

    /* =================== User API ==================== */
    @RequestMapping(method = RequestMethod.POST, value = "/user/create")
    public Response createUser(@RequestBody AccountDTO accountDTO) {
        if (accountService.create(accountDTO) != null) {
            return new Response("OK", "/sherlock/api/user/create");
        } else {
            return new Response("ERROR", "/sherlock/api/user/create");
        }
    }

    /* =================== Test API ==================== */
    @RequestMapping(method = RequestMethod.GET, value = "/ok")
    public Response getOK() {
        return new Response("OK", "/sherlock/api/ok");
    }
}
