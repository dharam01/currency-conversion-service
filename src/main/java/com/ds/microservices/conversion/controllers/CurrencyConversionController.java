package com.ds.microservices.conversion.controllers;

import com.ds.microservices.conversion.models.CurrencyConversion;
import com.ds.microservices.conversion.proxies.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeServiceProxy proxy;
    @GetMapping(path = "/currency-converter/{from}/to/{to}/amount/{amount}")
    public CurrencyConversion convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {
        // Feign is is rest service client
        CurrencyConversion converter = proxy.getCurrencyExchange(from, to);
        return new CurrencyConversion(converter.getId(), from, to,
                converter.getConversionMultiple(), converter.getPort(),
                amount, amount.multiply(converter.getConversionMultiple()));
    }

    /*@GetMapping(path = "/currency-converter/{from}/to/{to}/amount/{amount}")
    public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {

        // Rest template to invoke rest api
        Map<String, String> params = new HashMap<>();
        params.put("from", from);
        params.put("to", to);
        ResponseEntity<CurrencyConversion> response = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/{from}/to/{to}",
                        CurrencyConversion.class, params);
        CurrencyConversion converter = response.getBody();
        return new CurrencyConversion(converter.getId(), from, to,
                converter.getConversionMultiple(), converter.getPort(),
                amount, amount.multiply(converter.getConversionMultiple()));
    }*/
}
