package com.example.microservices.Currency_exchange_service.Controller;

import com.example.microservices.Currency_exchange_service.Entity.CurrencyExchangeEntity;
import com.example.microservices.Currency_exchange_service.Repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeEntity retreiveExchangedValue(
            @PathVariable String from, @PathVariable String to
    ){
        CurrencyExchangeEntity currencyExchangeEntity = repository.findByFromAndTo(from,to);
        if(currencyExchangeEntity == null){
            throw new RuntimeException("Unable to find data");

        }

        String port = environment.getProperty("local.server.port");
        currencyExchangeEntity.setEnvironment(port);

        return currencyExchangeEntity;

    }

}
