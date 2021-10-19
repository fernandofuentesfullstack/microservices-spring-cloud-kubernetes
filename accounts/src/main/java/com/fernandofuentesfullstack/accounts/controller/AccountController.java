package com.fernandofuentesfullstack.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fernandofuentesfullstack.accounts.config.AccountsServiceConfig;
import com.fernandofuentesfullstack.accounts.model.Account;
import com.fernandofuentesfullstack.accounts.model.Customer;
import com.fernandofuentesfullstack.accounts.model.Properties;
import com.fernandofuentesfullstack.accounts.model.dto.Card;
import com.fernandofuentesfullstack.accounts.model.dto.CustomerDetails;
import com.fernandofuentesfullstack.accounts.model.dto.Loan;
import com.fernandofuentesfullstack.accounts.repository.AccountRepository;
import com.fernandofuentesfullstack.accounts.service.client.CardsFeignClient;
import com.fernandofuentesfullstack.accounts.service.client.LoansFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountsServiceConfig accountsServiceConfig;

    @Autowired
    LoansFeignClient loansFeignClient;

    @Autowired
    CardsFeignClient cardsFeignClient;

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

    @GetMapping("/accounts/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

    @PostMapping("/myCustomerDetails")
    // @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallBack")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "myCustomerDetailsFallBack")
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {

        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Loan> loans = loansFeignClient.getLoanDetails(customer);
        List<Card> cards = cardsFeignClient.getCardDetails(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(account);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);

        return customerDetails;

    }

    private CustomerDetails myCustomerDetailsFallBack(Customer customer, Throwable throwable) {
        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Loan> loans = loansFeignClient.getLoanDetails(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(account);
        customerDetails.setLoans(loans);
        return customerDetails;
    }

    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        return "Hello, Welcome to fernandofuentesfullstack";
    }

    private String sayHelloFallback(Throwable throwable) {
        return "Hi, Welcome to fernandofuentesfullstack from fallback method";
    }
}
