package com.assignment.exceptions;

public class StockNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2872189372891731L;

	public StockNotFoundException() {
        super();
    }
	
	public StockNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StockNotFoundException(final String message) {
        super(message);
    }

    public StockNotFoundException(final Throwable cause) {
        super(cause);
    }
}
