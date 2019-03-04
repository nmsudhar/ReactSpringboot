package com.spring.react.controller;

import com.spring.react.model.CurrencyModel;
import com.spring.react.model.DisplayCurrencyModel;
import com.spring.react.service.CurrencyCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private DisplayCurrencyModel displayCurrencyModel;

    @MockBean
    private CurrencyCalculator currencyCalculator;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CurrencyController currencyController;

    @Test
    public void getAllCurrencyList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> headerInfo = getStringHttpEntity();
        final String uri = "http://localhost:8082/database/currencies";

        ResponseEntity<List<CurrencyModel>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, headerInfo, new ParameterizedTypeReference<List<CurrencyModel>>() {
        });

        assertTrue(responseEntity.getBody().size() > 0);

    }

    @Test
    public void shouldGetBTCCurrency() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/btcCurrencies")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    public void shouldNotGetBTCCurrency() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/btcCurrencie")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);

    }

    @Test
    public void shouldGetETCCurrency() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/etcCurrencies")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void shouldNotGetETCCurrency() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/etcCurrencie")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void shouldGetLTCCurrency() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/ltcCurrencies")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void shouldNotGetLTCCurrency() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/ltcCurrencie")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Basic dXNlcjp1c2Vy");

        return new HttpEntity<>(httpHeaders);
    }
}
