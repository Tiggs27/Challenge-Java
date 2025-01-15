package com.Tiago.rest.controller;

import com.Tiago.calculator.result.CalculatorResult;
import com.Tiago.calculator.service.CalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/calculator")
/**
 * Rest Controller to do the calculator operations
 * Endpoints:
 *         - Additions;
 *         - Subtractions; 
 *         - Multiplication;
 *         - Division;
 */
public class ControllerRest {

    private final CalculatorService calculatorService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ControllerRest.class);

    @Autowired
    public ControllerRest(CalculatorService calculatorService, KafkaTemplate<String, String> kafkaTemplate) {
        this.calculatorService = calculatorService;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     *  Endpoints for the operations
     * 
     * @param a is the first operand 
     * @param b is the second operand
     * @return result of the operation
     */

    @GetMapping("/add")
    public CalculatorResult add(@RequestParam("a") String a, @RequestParam("b") String b) {
        logger.info("/add request  a = {} and b = {}", a, b);
        CalculatorResult result = calculatorService.add(a, b);
        logger.info("Result: {}", result.getResult());
        kafkaTemplate.send("calculate", "Addition performed: " + a + " + " + b + " = " + result.getResult());
        return result;
    }

    @GetMapping("/sub")
    public CalculatorResult sub(@RequestParam("a") String a, @RequestParam("b") String b) {
        logger.info("/sub request  a = {} and b = {}", a, b);
        CalculatorResult result = calculatorService.sub(a, b);
        logger.info("Result: {}", result.getResult());
        kafkaTemplate.send("calculate", "Subtraction performed: " + a + " - " + b + " = " + result.getResult());
        return result;
    }

    @GetMapping("/mul")
    public CalculatorResult mul(@RequestParam("a") String a, @RequestParam("b") String b) {
        logger.info("/mul request  a = {} and b = {}", a, b);
        CalculatorResult result = calculatorService.mul(a, b);
        logger.info("Result: {}", result.getResult());
        kafkaTemplate.send("calculate", "Multiplication performed: " + a + " * " + b + " = " + result.getResult());
        return result;
    }

    @GetMapping("/div")
    public CalculatorResult div(@RequestParam("a") String a, @RequestParam("b") String b) {
        logger.info("/div request  a = {} and b = {}", a, b);
        CalculatorResult result = calculatorService.div(a, b);
        logger.info("Result: {}", result.getResult());
        kafkaTemplate.send("calculate", "Division performed: " + a + " / " + b + " = " + result.getResult());
        return result;
    }
}
