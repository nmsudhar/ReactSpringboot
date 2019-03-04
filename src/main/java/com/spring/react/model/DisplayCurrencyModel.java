package com.spring.react.model;

import org.springframework.stereotype.Service;

@Service
public class DisplayCurrencyModel {

    String currencyName;
    String updatedDate;
    String minimumPriceTime;
    String maximumPriceTime;
    double minimumPriceValue;
    double maximumPriceValue;
    double profit = 0.00;

    public void setMinimumPriceTime(String minimumPriceTime) {
        this.minimumPriceTime = minimumPriceTime;
    }

    public void setMaximumPriceTime(String maximumPriceTime) {
        this.maximumPriceTime = maximumPriceTime;
    }

    public void setMinimumPriceValue(double minimumPriceValue) {
        this.minimumPriceValue = minimumPriceValue;
    }

    public void setMaximumPriceValue(double maximumPriceValue) {
        this.maximumPriceValue = maximumPriceValue;
    }

    public String getMinimumPriceTime() {
        return minimumPriceTime;
    }

    public String getMaximumPriceTime() {
        return maximumPriceTime;
    }

    public double getMinimumPriceValue() {
        return minimumPriceValue;
    }

    public double getMaximumPriceValue() {
        return maximumPriceValue;
    }

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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
