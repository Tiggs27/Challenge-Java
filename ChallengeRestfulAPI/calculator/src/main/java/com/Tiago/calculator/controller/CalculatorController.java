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
/*
 * Controller for kafka messages 
 *  Listen for messages in the topic and performs the calculations
 */
public class CalculatorController {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CalculatorService calculatorService;

    /*
     *  Constructor for the dependencies
     */
    public CalculatorController(KafkaTemplate<String, String> kafkaTemplate, CalculatorService calculatorService) {
        this.kafkaTemplate = kafkaTemplate;
        this.calculatorService = calculatorService;
    }


    /*
     * Captures the message from the "calculate", does the calculation that appears in the message and performs it
     */
    @KafkaListener(topics = "calculate", groupId = "calculator-group")
    public void performCalculation(String message) {
        try {
            // Log the received message
            System.out.println("Received message: " + message);

            // Parse the plain text message to extract the operation and operands
            if (message.contains("Addition performed:")) {
                handleAddition(message);
            } else if (message.contains("Subtraction performed:")) {
                handleSubtraction(message);
            } else if (message.contains("Multiplication performed:")) {
                handleMultiplication(message);
            } else if (message.contains("Division performed:")) {
                handleDivision(message);
            } else {
                System.err.println("Unsupported format: " + message);
            }
        } catch (Exception e) {
            System.err.println("Error message: " + e.getMessage());
        }
    }

    /*
     *  Splits the message from kafka and does the calculation for all the operations
     */

    private void handleAddition(String message) {
        try {
            String[] parts = message.split(": ")[1].split(" = ")[0].split(" \\+ ");
            String a = parts[0].trim();
            String b = parts[1].trim();
            CalculatorResult result = calculatorService.add(a, b);
            System.out.println("Addition result: " + result.getResult());
        } catch (Exception e) {
            System.err.println("Error addition: " + e.getMessage());
        }
    }

    private void handleSubtraction(String message) {
        try {
            String[] parts = message.split(": ")[1].split(" = ")[0].split(" - ");
            String a = parts[0].trim();
            String b = parts[1].trim();
            CalculatorResult result = calculatorService.sub(a, b);
            System.out.println("Subtraction result: " + result.getResult());
        } catch (Exception e) {
            System.err.println("Error subtraction: " + e.getMessage());
        }
    }

    private void handleMultiplication(String message) {
        try {
            String[] parts = message.split(": ")[1].split(" = ")[0].split(" \\* ");
            String a = parts[0].trim();
            String b = parts[1].trim();
            CalculatorResult result = calculatorService.mul(a, b);
            System.out.println("Multiplication result: " + result.getResult());
        } catch (Exception e) {
            System.err.println("Error multiplication: " + e.getMessage());
        }
    }

    private void handleDivision(String message) {
        try {
            String[] parts = message.split(": ")[1].split(" = ")[0].split(" / ");
            String a = parts[0].trim();
            String b = parts[1].trim();
            CalculatorResult result = calculatorService.div(a, b);
            System.out.println("Division result: " + result.getResult());
        } catch (Exception e) {
            System.err.println("Error division: " + e.getMessage());
        }
    }



}
