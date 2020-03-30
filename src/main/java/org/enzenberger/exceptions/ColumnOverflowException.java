package org.enzenberger.exceptions;

public class ColumnOverflowException extends FourWinsException {
    public ColumnOverflowException(){super();}

    public ColumnOverflowException(String message){super(message);}

    public ColumnOverflowException(String message, Exception e){super(message, e);}
}
