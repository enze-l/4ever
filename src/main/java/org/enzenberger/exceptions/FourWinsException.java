package org.enzenberger.exceptions;

public class FourWinsException extends Exception {
    FourWinsException(){super();}

    FourWinsException(String message){super(message);}

    FourWinsException(String message, Exception e){super(message, e);}
}
