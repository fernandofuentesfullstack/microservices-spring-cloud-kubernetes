package com.fernandofuentesfullstack.accounts.service.client;

import com.fernandofuentesfullstack.accounts.model.dto.Card;
import com.fernandofuentesfullstack.accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = "application/json")
    List<Card> getCardDetails(@RequestBody Customer customer);
}
