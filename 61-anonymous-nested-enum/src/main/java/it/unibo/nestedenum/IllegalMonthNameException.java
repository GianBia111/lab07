package it.unibo.nestednum;

public class IllegalMonthNameException extends IllegalArgumentException{
    public IllegalMonthNameException(String message){
        super(message);
    }
}