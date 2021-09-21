package com.fernandofuentesfullstack.loans.controller;

import com.fernandofuentesfullstack.loans.model.Customer;
import com.fernandofuentesfullstack.loans.model.Loan;
import com.fernandofuentesfullstack.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @PostMapping("/myLoans")
    public List<Loan> getLoanDetails(@RequestBody Customer customer) {

        List<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());

        if (loans != null) {
            return loans;
        } else {
            return null;
        }

    }
}
