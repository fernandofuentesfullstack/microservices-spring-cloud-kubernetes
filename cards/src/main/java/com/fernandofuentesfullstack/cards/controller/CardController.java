package com.fernandofuentesfullstack.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fernandofuentesfullstack.cards.config.CardsServiceConfig;
import com.fernandofuentesfullstack.cards.model.Card;
import com.fernandofuentesfullstack.cards.model.Customer;
import com.fernandofuentesfullstack.cards.model.Properties;
import com.fernandofuentesfullstack.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardsServiceConfig cardsServiceConfig;

    @PostMapping("/myCards")
    public List<Card> getCardDetails(@RequestBody Customer customer) {
        List<Card> cards = cardRepository.findByCustomerId(customer.getCustomerId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }
    }

    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
