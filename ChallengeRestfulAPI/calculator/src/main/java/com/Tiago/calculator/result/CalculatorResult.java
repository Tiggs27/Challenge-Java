package com.Tiago.calculator.result;

public class CalculatorResult {
    private String result;

    public CalculatorResult(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }
    
    @Override
    public String toString(){
        return "Result:" + result;
    }
}
