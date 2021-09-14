package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.component.DollarCalculator;
import com.example.springcalculator.component.MarketApi;
import com.example.springcalculator.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorApiController.class) //web에서 필요한 것만 로딩
@AutoConfigureMockMvc
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiTest {

    @MockBean
    private MarketApi marketApi;
    
    @Autowired
    private MockMvc mockMvc; //mvc를 mocking으로 테스트하겠다는 것

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    //get
    public void sunTest() throws Exception {
        //http://localhost:8989/api/sum
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8989/api/sum")
                        .queryParam("x","10")
                        .queryParam("y","10")

        ).andExpect( //기댓값
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    //post
    public void minusTest() throws Exception {
        Req req = new Req();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);
        //json 문자열로 전환

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8989/api/minus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(MockMvcResultHandlers.print());
    }
}
