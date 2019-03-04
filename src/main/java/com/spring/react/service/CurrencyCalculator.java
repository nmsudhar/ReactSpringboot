package com.spring.react.service;

import com.spring.react.model.CurrencyModel;
import com.spring.react.model.DisplayCurrencyModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyCalculator {

    public DisplayCurrencyModel getDisplayCurrencyModel(RestTemplate restTemplate, DisplayCurrencyModel displayCurrencyModel, List<CurrencyModel> currencyModel, HttpEntity<String> headerInfo, String currencyName) {
        double maximumPriceValue = Double.MIN_VALUE;
        String maximumPriceTime = null;
        Map<String, Double> minValue = new HashMap<>();

        ResponseEntity<CurrencyModel> responseEntityResult = restTemplate.exchange("http://localhost:8082/database/currencies/" + currencyName, HttpMethod.GET, headerInfo, new ParameterizedTypeReference<CurrencyModel>() {
        });
        currencyModel.add(responseEntityResult.getBody());

        for (CurrencyModel aCurrencyModel : currencyModel) {
            for (int k = 0; k < aCurrencyModel.getQuotes().size(); k++) {
                double priceValue = Double.valueOf(aCurrencyModel.getQuotes().get(k).getPrice());
                String priceTime = aCurrencyModel.getQuotes().get(k).getTime();
                if (priceValue > maximumPriceValue) {
                    maximumPriceValue = priceValue;
                    maximumPriceTime = priceTime;
                    minValue.put(priceTime, priceValue);
                }
                displayCurrencyModel.setCurrencyName(aCurrencyModel.getCurrencyName());
                displayCurrencyModel.setUpdatedDate(aCurrencyModel.getUpdatedDate());
                displayCurrencyModel.setMaximumPriceTime(maximumPriceTime);
                displayCurrencyModel.setMaximumPriceValue(maximumPriceValue);
            }
            double minimumPriceValue = getMinimumPriceValue(displayCurrencyModel, minValue);
            getProfitValue(displayCurrencyModel, maximumPriceValue, minimumPriceValue);
        }
        return displayCurrencyModel;
    }

    private double getMinimumPriceValue(DisplayCurrencyModel displayCurrencyModel, Map<String, Double> minValue) {
        Map<String, Double> sortedByCount = minValue.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        String minimumPriceTime = sortedByCount.entrySet().iterator().next().getKey();
        double minimumPriceValue = sortedByCount.entrySet().iterator().next().getValue();
        displayCurrencyModel.setMinimumPriceTime(minimumPriceTime);
        displayCurrencyModel.setMinimumPriceValue(minimumPriceValue);
        return minimumPriceValue;
    }

    private void getProfitValue(DisplayCurrencyModel displayCurrencyModel, double maximumPriceValue, double minimumPriceValue) {
        DecimalFormat df = new DecimalFormat("####0.00");
        displayCurrencyModel.setProfit(Double.valueOf(df.format(maximumPriceValue - minimumPriceValue)));
    }

}
