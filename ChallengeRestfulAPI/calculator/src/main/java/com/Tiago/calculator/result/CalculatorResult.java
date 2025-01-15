package com.Tiago.calculator.result;


/*
 * Class responsible for representing the result of the operations
 */
public class CalculatorResult {
    private String result;

    /*
     * Constructor to initialize 
     */
    public CalculatorResult(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }
    
    /*
     * Converts the result into string
     */
    @Override
    public String toString(){
        return "Result:" + result;
    }
}
