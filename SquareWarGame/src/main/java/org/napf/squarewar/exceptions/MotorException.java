package org.napf.squarewar.exceptions;

/**
 * Exception indicating that something is wrong with the motor or the interaction with it.
 */
public class MotorException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MotorException() {
        super();
    }

    public MotorException(String message) {
        super(message);
    }

    public MotorException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
