package com.Tiago.rest.controller;

import com.Tiago.calculator.result.CalculatorResult;
import com.Tiago.calculator.service.CalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class ControllerRest {

    private final CalculatorService calculatorService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ControllerRest(CalculatorService calculatorService, KafkaTemplate<String, String> kafkaTemplate) {
        this.calculatorService = calculatorService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/add")
    public CalculatorResult add(@RequestParam("a") String a, @RequestParam("b") String b) {
        CalculatorResult result = calculatorService.add(a, b);
        kafkaTemplate.send("calculate", "Addition performed: " + a + " + " + b + " = " + result.getResult());
        return result;
    }

    @GetMapping("/sub")
    public CalculatorResult sub(@RequestParam("a") String a, @RequestParam("b") String b) {
        CalculatorResult result = calculatorService.sub(a, b);
        kafkaTemplate.send("calculate", "Subtraction performed: " + a + " - " + b + " = " + result.getResult());
        return result;
    }

    @GetMapping("/mul")
    public CalculatorResult mul(@RequestParam("a") String a, @RequestParam("b") String b) {
        CalculatorResult result = calculatorService.mul(a, b);
        kafkaTemplate.send("calculate", "Multiplication performed: " + a + " * " + b + " = " + result.getResult());
        return result;
    }

    @GetMapping("/div")
    public CalculatorResult div(@RequestParam("a") String a, @RequestParam("b") String b) {
        CalculatorResult result = calculatorService.div(a, b);
        kafkaTemplate.send("calculate", "Division performed: " + a + " / " + b + " = " + result.getResult());
        return result;
    }
}
