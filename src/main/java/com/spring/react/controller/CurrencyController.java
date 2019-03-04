package com.spring.react.controller;

import com.spring.react.model.CurrencyModel;
import com.spring.react.model.DisplayCurrencyModel;
import com.spring.react.service.CurrencyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class CurrencyController {

    private final DisplayCurrencyModel displayCurrencyModel;
    private final CurrencyCalculator currencyCalculator;

    @Autowired
    public CurrencyController(DisplayCurrencyModel displayCurrencyModel, CurrencyCalculator currencyCalculator) {
        this.displayCurrencyModel = displayCurrencyModel;
        this.currencyCalculator = currencyCalculator;
    }

    @GetMapping("/api/getAll")
    public List<CurrencyModel> getAllCurrencyList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CurrencyModel>> responseEntity;
        HttpEntity<String> headerInfo = getStringHttpEntity();
        responseEntity = getListResponseEntity(restTemplate, headerInfo);

        return new ArrayList<>(Objects.requireNonNull(responseEntity.getBody()));
    }

    @GetMapping("/api/btcCurrencies")
    public DisplayCurrencyModel getBTCCurrency() {
        RestTemplate restTemplate = new RestTemplate();
        DisplayCurrencyModel displayCurrencyModelResult;
        List<CurrencyModel> currencyModel = new ArrayList<>();
        HttpEntity<String> headerInfo = getStringHttpEntity();
        displayCurrencyModelResult = currencyCalculator.getDisplayCurrencyModel(restTemplate, displayCurrencyModel, currencyModel, headerInfo, "BTC");

        return displayCurrencyModelResult;
    }

    @GetMapping("/api/etcCurrencies")
    public DisplayCurrencyModel getETCCurrency() {
        RestTemplate restTemplate = new RestTemplate();
        DisplayCurrencyModel displayCurrencyModelResult;
        List<CurrencyModel> currencyModel = new ArrayList<>();
        HttpEntity<String> headerInfo = getStringHttpEntity();
        displayCurrencyModelResult = currencyCalculator.getDisplayCurrencyModel(restTemplate, displayCurrencyModel, currencyModel, headerInfo, "ETC");

        return displayCurrencyModelResult;
    }

    @GetMapping("/api/ltcCurrencies")
    public DisplayCurrencyModel getLTCCurrency() {
        RestTemplate restTemplate = new RestTemplate();
        DisplayCurrencyModel displayCurrencyModelResult;
        List<CurrencyModel> currencyModel = new ArrayList<>();
        HttpEntity<String> headerInfo = getStringHttpEntity();
        displayCurrencyModelResult = currencyCalculator.getDisplayCurrencyModel(restTemplate, displayCurrencyModel, currencyModel, headerInfo, "LTC");

        return displayCurrencyModelResult;
    }

    private ResponseEntity<List<CurrencyModel>> getListResponseEntity(RestTemplate restTemplate, HttpEntity<String> headerInfo) {
        ResponseEntity<List<CurrencyModel>> responseEntity;
        final String uri = "http://localhost:8082/database/currencies";
        responseEntity = restTemplate.exchange(uri, HttpMethod.GET, headerInfo, new ParameterizedTypeReference<List<CurrencyModel>>() {
        });
        return responseEntity;
    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Basic dXNlcjp1c2Vy");

        return new HttpEntity<>(httpHeaders);
    }
}
