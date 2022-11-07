package it.unibo.nestedenum;

public class IllegalMonthNameException extends IllegalArgumentException{
    public IllegalMonthNameException(String message){
        super(message);
    }
}