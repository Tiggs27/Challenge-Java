package com.Tiago.rest.controller;

import com.Tiago.calculator.result.CalculatorResult;
import com.Tiago.calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
 * Unit tests for the ControllerRest 
 * Checks the behaviour of the calculations
 */
class ControllerRestTest {

    private MockMvc mockMvc; //for simulating HTTP requests 

    @Mock // To isolate the components 
    private CalculatorService calculatorService;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    /*
     * Sets up the test enviromnent
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ControllerRest controllerRest = new ControllerRest(calculatorService, kafkaTemplate);
        mockMvc = MockMvcBuilders.standaloneSetup(controllerRest).build();
    }

    /*
     *  Tests the operations endpoints and verifies if it verifies if its Ok
     */

    @Test
    void testAddEndpoint() throws Exception {
        CalculatorResult mockResult = new CalculatorResult("15");
        when(calculatorService.add("5", "10")).thenReturn(mockResult);

        mockMvc.perform(get("/calculator/add")
                .param("a", "5")
                .param("b", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testSubEndpoint() throws Exception {
        CalculatorResult mockResult = new CalculatorResult("-5");
        when(calculatorService.sub("5", "10")).thenReturn(mockResult);

        mockMvc.perform(get("/calculator/sub")
                .param("a", "5")
                .param("b", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testMulEndpoint() throws Exception {
        CalculatorResult mockResult = new CalculatorResult("50");
        when(calculatorService.mul("5", "10")).thenReturn(mockResult);

        mockMvc.perform(get("/calculator/mul")
                .param("a", "5")
                .param("b", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testDivEndpoint() throws Exception {
        CalculatorResult mockResult = new CalculatorResult("0.5");
        when(calculatorService.div("5", "10")).thenReturn(mockResult);

        mockMvc.perform(get("/calculator/div")
                .param("a", "5")
                .param("b", "10"))
                .andExpect(status().isOk());
    }
}
