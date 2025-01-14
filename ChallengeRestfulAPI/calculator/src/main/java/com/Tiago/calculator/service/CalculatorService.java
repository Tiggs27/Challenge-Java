package com.Tiago.calculator.service;

import com.Tiago.calculator.result.CalculatorResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculatorService {

    public CalculatorResult add(String a, String b){
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        BigDecimal result = num1.add(num2);

        return new CalculatorResult(result.toPlainString());
    }
    public CalculatorResult sub(String a, String b){
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        BigDecimal result = num1.subtract(num2);

        return new CalculatorResult(result.toPlainString());
    }
    public CalculatorResult mul(String a, String b){
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        BigDecimal result = num1.multiply(num2);

        return new CalculatorResult(result.toPlainString());
    }
    public CalculatorResult div(String a, String b){
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        
        if (num2.compareTo(BigDecimal.ZERO)  == 0 ){
            return new CalculatorResult("Cannot divide by zero!");
        }
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        BigDecimal result = num1.divide(num2, mc);

        return new CalculatorResult(result.toPlainString());
    }
}

