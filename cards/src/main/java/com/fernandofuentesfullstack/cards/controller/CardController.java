package com.fernandofuentesfullstack.cards.controller;

import com.fernandofuentesfullstack.cards.model.Card;
import com.fernandofuentesfullstack.cards.model.Customer;
import com.fernandofuentesfullstack.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/myCards")
    public List<Card> getCardDetails(@RequestBody Customer customer) {

        List<Card> cards = cardRepository.findByCustomerId(customer.getCustomerId());

        if (cards != null) {
            return cards;
        } else {
            return null;
        }

    }
}
