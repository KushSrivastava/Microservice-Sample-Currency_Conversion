package com.example.microservices.Currency_exchange_service.Repository;

import com.example.microservices.Currency_exchange_service.Entity.CurrencyExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {
    CurrencyExchangeEntity findByFromAndTo(String from, String to);
}
