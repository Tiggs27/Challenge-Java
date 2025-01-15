package com.Tiago.calculator.service;

import com.Tiago.calculator.result.CalculatorResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
/*
 * Class responsible for the operations
 */
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);


    /**
     * Performs the calculation according to the input of two variables
     * 
     * @param a first input
     * @param b second input 
     * @return result from the operation
     * 
     */
    public CalculatorResult add(String a, String b){
        logger.info("ADD: a = {}, b = {}", a, b);
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        BigDecimal result = num1.add(num2);
        logger.info("Result: {}", result);
        return new CalculatorResult(result.toPlainString());
    }
    public CalculatorResult sub(String a, String b){
        logger.info("SUB: a = {}, b = {}", a, b);
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        BigDecimal result = num1.subtract(num2);

        return new CalculatorResult(result.toPlainString());
    }
    public CalculatorResult mul(String a, String b){
        logger.info("MUL: a = {}, b = {}", a, b);
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        BigDecimal result = num1.multiply(num2);
        logger.info("Result: {}", result);
        return new CalculatorResult(result.toPlainString());
    }
    public CalculatorResult div(String a, String b){
        logger.info("DIV: a = {}, b = {}", a, b);
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        
        if (num2.compareTo(BigDecimal.ZERO)  == 0 ){
            logger.error("Division by zero: a = {}, b = {}", a, b);
            return new CalculatorResult("Cannot divide by zero!");
        }
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        BigDecimal result = num1.divide(num2, mc);
        logger.info("Result: {}", result);
        return new CalculatorResult(result.toPlainString());
    }
}

