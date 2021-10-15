package com.fernandofuentesfullstack.accounts.model.dto;

import com.fernandofuentesfullstack.accounts.model.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class CustomerDetails {

    private Account accounts;
    private List<Loan> loans;
    private List<Card> cards;
}
