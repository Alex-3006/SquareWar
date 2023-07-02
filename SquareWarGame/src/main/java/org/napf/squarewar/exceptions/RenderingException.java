package org.napf.squarewar.exceptions;

/**
 * Exception occurring when the rendering process within GameView encounters a problem
 */
public class RenderingException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RenderingException() {
        super();
    }

    public RenderingException(String message) {
        super(message);
    }

    public RenderingException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
