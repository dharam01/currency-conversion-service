package com.ds.microservices.conversion.proxies;

import com.ds.microservices.conversion.models.CurrencyConversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url = "localhost:8000")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name = "zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
    //@GetMapping(path = "/currency-exchange/{from}/to/{to}")
    @GetMapping(path = "currency-exchange-service/currency-exchange/{from}/to/{to}")
    public CurrencyConversion getCurrencyExchange(@PathVariable String from, @PathVariable String to) ;
}
