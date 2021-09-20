package com.fernandofuentesfullstack.accounts.controller;

import com.fernandofuentesfullstack.accounts.model.Account;
import com.fernandofuentesfullstack.accounts.model.Customer;
import com.fernandofuentesfullstack.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello Fernando Fuentes";
    }

    @PostMapping("/myAccount")
    public Account getAccountDetails(@RequestBody Customer customer) {
        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        if (account != null) {
            return account;
        } else {
            return null;
        }
    }
}
