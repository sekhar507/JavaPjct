package org.core.java;

public class StringCalculator {

    public int add(final String input) {
        if(input.isEmpty()){
            return 0;
        }
        
        
        
        
        else {
            final String[] numbers = input.split(",|\n");
            int sum = 0;
            for(String number: numbers){
            	
                sum += Integer.parseInt(number);
            }

            return sum;
        }
    }

}
