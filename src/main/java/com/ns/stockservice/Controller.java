package com.ns.stockservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.LocalDateTime.now;

@RestController
public class Controller {

    @GetMapping(value = "/stocks/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StockPrice> prices(@PathVariable String symbol){
        return Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> new StockPrice(symbol,randomStockPrice(), now()));

    }



    private double randomStockPrice() {
        return ThreadLocalRandom.current().nextDouble(100.0);
    }
}
