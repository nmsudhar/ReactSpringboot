package com.spring.react.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyModel {

    private String currencyName;
    private String updatedDate;
    private List<QuotesModel> quotes = new ArrayList<>();

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<QuotesModel> getQuotes() {
        return quotes;
    }
}
