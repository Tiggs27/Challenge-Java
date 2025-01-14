package com.Tiago.calculator.controller;

import com.Tiago.calculator.result.CalculatorResult;
import com.Tiago.calculator.service.CalculatorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CalculatorService calculatorService;

    public CalculatorController(KafkaTemplate<String, String> kafkaTemplate, CalculatorService calculatorService) {
        this.kafkaTemplate = kafkaTemplate;
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "calculate", groupId = "calculator-group")
    public void performCalculation(String message) {
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> payload = objectMapper.readValue(message, new TypeReference<Map<String, String>>() {});

            String operation = payload.get("operation");
            String a = payload.get("a");
            String b = payload.get("b");

            CalculatorResult result;
            switch (operation) {
                case "add":
                    result = calculatorService.add(a, b);
                    break;
                case "sub":
                    result = calculatorService.sub(a, b);
                    break;
                case "mul":
                    result = calculatorService.mul(a, b);
                    break;
                case "div":
                    result = calculatorService.div(a, b);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + operation);
            }

            String response = objectMapper.writeValueAsString(result);

            kafkaTemplate.send("calculate", response);

        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON message: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error performing calculation: " + e.getMessage());
        }
    }


}
