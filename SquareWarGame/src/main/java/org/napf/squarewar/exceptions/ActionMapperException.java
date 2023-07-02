package org.napf.squarewar.exceptions;

/**
 * Exception indicating that ActionMapper has a problem with finding a (non-)mapped InputAction
 */
public class ActionMapperException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActionMapperException() {
        super();
    }

    public ActionMapperException(String message) {
        super(message);
    }

    public ActionMapperException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
