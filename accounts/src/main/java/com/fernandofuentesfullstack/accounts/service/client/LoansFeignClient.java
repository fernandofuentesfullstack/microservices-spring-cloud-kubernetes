package com.fernandofuentesfullstack.accounts.service.client;

import com.fernandofuentesfullstack.accounts.model.Customer;
import com.fernandofuentesfullstack.accounts.model.dto.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "myLoans", consumes = "application/json")
    List<Loan> getLoanDetails(@RequestBody Customer customer);
}
