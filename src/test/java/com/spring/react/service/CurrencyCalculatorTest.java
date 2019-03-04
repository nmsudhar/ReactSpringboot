package com.spring.react.service;

import com.spring.react.model.CurrencyModel;
import com.spring.react.model.DisplayCurrencyModel;
import com.spring.react.model.QuotesModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyCalculator.class)
public class CurrencyCalculatorTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    CurrencyModel currencyModel;

    @Mock
    private DisplayCurrencyModel displayCurrencyModel;

    @InjectMocks
    CurrencyCalculator currencyCalculator;

    @Test
    public void shouldGetBTCCurrencyModelValues() {
        HttpEntity<String> headerInfo = getStringHttpEntity();
        List<CurrencyModel> currencyModelList = new ArrayList<>();
        CurrencyModel currencyModelResponseEnt = getBTCCurrencyData();
        ResponseEntity<CurrencyModel> responseEntityResult = new ResponseEntity(currencyModelResponseEnt, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:8082/database/currencies/" + "BTC", HttpMethod.GET, headerInfo, new ParameterizedTypeReference<CurrencyModel>() {
        })).thenReturn(responseEntityResult);
        DisplayCurrencyModel displayCurrencyModelValue = new DisplayCurrencyModel();

        DisplayCurrencyModel displayCurrencyModelResult = currencyCalculator.getDisplayCurrencyModel(restTemplate, displayCurrencyModelValue, currencyModelList, headerInfo, "BTC");

        assertThat(displayCurrencyModelResult.getMaximumPriceTime(), is("1230"));
        assertThat(displayCurrencyModelResult.getMaximumPriceValue(), is(37.01));
        assertThat(displayCurrencyModelResult.getMinimumPriceTime(), is("0915"));
        assertThat(displayCurrencyModelResult.getMinimumPriceValue(), is(34.98));
        assertThat(displayCurrencyModelResult.getProfit(), is(2.03));
    }

    @Test
    public void shouldNotGetBTCCurrencyModelValues() {
        HttpEntity<String> headerInfo = getStringHttpEntity();
        List<CurrencyModel> currencyModelList = new ArrayList<>();
        CurrencyModel currencyModelResponseEnt = getBTCCurrencyData();
        ResponseEntity<CurrencyModel> responseEntityResult = new ResponseEntity(currencyModelResponseEnt, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:8082/database/currencies/" + "BTC", HttpMethod.GET, headerInfo, new ParameterizedTypeReference<CurrencyModel>() {
        })).thenReturn(responseEntityResult);
        DisplayCurrencyModel displayCurrencyModelValue = new DisplayCurrencyModel();

        DisplayCurrencyModel displayCurrencyModelResult = currencyCalculator.getDisplayCurrencyModel(restTemplate, displayCurrencyModelValue, currencyModelList, headerInfo, "BTC");

        assertThat(displayCurrencyModelResult.getMaximumPriceTime(), is("1230"));
        assertNotEquals(displayCurrencyModelResult.getMaximumPriceValue(), 36.01);
        assertThat(displayCurrencyModelResult.getMinimumPriceTime(), is("0915"));
        assertNotEquals(displayCurrencyModelResult.getMinimumPriceValue(), is(30.98));
        assertThat(displayCurrencyModelResult.getProfit(), is(2.03));
    }

    @Test
    public void shouldGetETCCurrencyModelValues() {
        HttpEntity<String> headerInfo = getStringHttpEntity();
        List<CurrencyModel> currencyModelList = new ArrayList<>();
        CurrencyModel currencyModelResponseEnt = getETCCurrencyData();
        ResponseEntity<CurrencyModel> responseEntityResult = new ResponseEntity(currencyModelResponseEnt, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:8082/database/currencies/" + "ETC", HttpMethod.GET, headerInfo, new ParameterizedTypeReference<CurrencyModel>() {
        })).thenReturn(responseEntityResult);
        DisplayCurrencyModel displayCurrencyModelValue = new DisplayCurrencyModel();

        DisplayCurrencyModel displayCurrencyModelResult = currencyCalculator.getDisplayCurrencyModel(restTemplate, displayCurrencyModelValue, currencyModelList, headerInfo, "ETC");

        assertThat(displayCurrencyModelResult.getMaximumPriceTime(), is("1700"));
        assertThat(displayCurrencyModelResult.getMaximumPriceValue(), is(2.15));
        assertThat(displayCurrencyModelResult.getMinimumPriceTime(), is("0900"));
        assertThat(displayCurrencyModelResult.getMinimumPriceValue(), is(1.45));
        assertThat(displayCurrencyModelResult.getProfit(), is(0.7));
    }

    @Test
    public void shouldGetLTCCurrencyModelValues() {
        HttpEntity<String> headerInfo = getStringHttpEntity();
        List<CurrencyModel> currencyModelList = new ArrayList<>();
        CurrencyModel currencyModelResponseEnt = getLTCCurrencyData();
        ResponseEntity<CurrencyModel> responseEntityResult = new ResponseEntity(currencyModelResponseEnt, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:8082/database/currencies/" + "LTC", HttpMethod.GET, headerInfo, new ParameterizedTypeReference<CurrencyModel>() {
        })).thenReturn(responseEntityResult);
        DisplayCurrencyModel displayCurrencyModelValue = new DisplayCurrencyModel();

        DisplayCurrencyModel displayCurrencyModelResult = currencyCalculator.getDisplayCurrencyModel(restTemplate, displayCurrencyModelValue, currencyModelList, headerInfo, "LTC");

        assertThat(displayCurrencyModelResult.getMaximumPriceTime(), is("1245"));
        assertThat(displayCurrencyModelResult.getMaximumPriceValue(), is(15.03));
        assertThat(displayCurrencyModelResult.getMinimumPriceTime(), is("0930"));
        assertThat(displayCurrencyModelResult.getMinimumPriceValue(), is(14.32));
        assertThat(displayCurrencyModelResult.getProfit(), is(0.71));
    }

    private CurrencyModel getBTCCurrencyData() {
        CurrencyModel currencyModelResponseEnt = new CurrencyModel();
        currencyModelResponseEnt.setCurrencyName("BTC");
        QuotesModel quotesModelFirst = new QuotesModel();
        quotesModelFirst.setPrice("34.98");
        quotesModelFirst.setTime("0915");
        currencyModelResponseEnt.getQuotes().add(quotesModelFirst);
        QuotesModel quotesModelSecond = new QuotesModel();
        quotesModelSecond.setPrice("36.13");
        quotesModelSecond.setTime("1045");
        currencyModelResponseEnt.getQuotes().add(quotesModelSecond);
        QuotesModel quotesModelThird = new QuotesModel();
        quotesModelThird.setPrice("37.01");
        quotesModelThird.setTime("1230");
        currencyModelResponseEnt.getQuotes().add(quotesModelThird);
        QuotesModel quotesModelForth = new QuotesModel();
        quotesModelForth.setPrice("35.98");
        quotesModelForth.setTime("1400");
        currencyModelResponseEnt.getQuotes().add(quotesModelForth);
        QuotesModel quotesModelFifth = new QuotesModel();
        quotesModelFifth.setPrice("33.56");
        quotesModelFifth.setTime("1530");
        currencyModelResponseEnt.getQuotes().add(quotesModelFifth);
        return currencyModelResponseEnt;
    }

    private CurrencyModel getETCCurrencyData() {
        CurrencyModel currencyModelResponseEnt = new CurrencyModel();
        currencyModelResponseEnt.setCurrencyName("ETC");
        QuotesModel quotesModelFirst = new QuotesModel();
        quotesModelFirst.setPrice("1.45");
        quotesModelFirst.setTime("0900");
        currencyModelResponseEnt.getQuotes().add(quotesModelFirst);
        QuotesModel quotesModelSecond = new QuotesModel();
        quotesModelSecond.setPrice("1.87");
        quotesModelSecond.setTime("1030");
        currencyModelResponseEnt.getQuotes().add(quotesModelSecond);
        QuotesModel quotesModelThird = new QuotesModel();
        quotesModelThird.setPrice("1.55");
        quotesModelThird.setTime("1245");
        currencyModelResponseEnt.getQuotes().add(quotesModelThird);
        QuotesModel quotesModelForth = new QuotesModel();
        quotesModelForth.setPrice("2.01");
        quotesModelForth.setTime("1515");
        currencyModelResponseEnt.getQuotes().add(quotesModelForth);
        QuotesModel quotesModelFifth = new QuotesModel();
        quotesModelFifth.setPrice("2.15");
        quotesModelFifth.setTime("1700");
        currencyModelResponseEnt.getQuotes().add(quotesModelFifth);
        return currencyModelResponseEnt;
    }

    private CurrencyModel getLTCCurrencyData() {
        CurrencyModel currencyModelResponseEnt = new CurrencyModel();
        currencyModelResponseEnt.setCurrencyName("LTC");
        QuotesModel quotesModelFirst = new QuotesModel();
        quotesModelFirst.setPrice("14.32");
        quotesModelFirst.setTime("0930");
        currencyModelResponseEnt.getQuotes().add(quotesModelFirst);
        QuotesModel quotesModelSecond = new QuotesModel();
        quotesModelSecond.setPrice("14.87");
        quotesModelSecond.setTime("1115");
        currencyModelResponseEnt.getQuotes().add(quotesModelSecond);
        QuotesModel quotesModelThird = new QuotesModel();
        quotesModelThird.setPrice("15.03");
        quotesModelThird.setTime("1245");
        currencyModelResponseEnt.getQuotes().add(quotesModelThird);
        QuotesModel quotesModelForth = new QuotesModel();
        quotesModelForth.setPrice("14.76");
        quotesModelForth.setTime("1400");
        currencyModelResponseEnt.getQuotes().add(quotesModelForth);
        QuotesModel quotesModelFifth = new QuotesModel();
        quotesModelFifth.setPrice("14.15");
        quotesModelFifth.setTime("1700");
        currencyModelResponseEnt.getQuotes().add(quotesModelFifth);
        return currencyModelResponseEnt;
    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Basic dXNlcjp1c2Vy");

        return new HttpEntity<>(httpHeaders);
    }
}
