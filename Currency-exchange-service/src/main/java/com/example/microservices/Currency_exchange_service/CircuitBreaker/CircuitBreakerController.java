package com.example.microservices.Currency_exchange_service.CircuitBreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    //@Retry(name = "default") //by deafult it will follow 3 retried mechanism

    @GetMapping("/sample-api")
    @Retry(name = "sampleApi", fallbackMethod = "fallbackResponse")
    public String sampleApi(){
        logger.info("Sample Api call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/dummy-api",String.class);
        return forEntity.getBody();
    }

    @GetMapping("/sample-api1")
    @CircuitBreaker(name = "default",fallbackMethod = "fallbackResponse")
    //In here it will work as in here we are returning string
    public String getApi(){
        logger.info("Get Api call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/dummy-api",String.class);
        return forEntity.getBody();
    }

    public String fallbackResponse(Exception ex){
        return "FallBack Response";
    }
}
