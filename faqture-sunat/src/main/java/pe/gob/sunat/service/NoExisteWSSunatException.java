package pe.gob.sunat.service;

public class NoExisteWSSunatException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
     * Creates a new instance of <code>noWSSunat</code> without detail message.
     */
    String sws;
    
    public NoExisteWSSunatException() {
    }

    /**
     * Constructs an instance of <code>noWSSunat</code> with the specified
     * detail message.
     *
     * @param sws the detail message.
     */
    public NoExisteWSSunatException(String sws) {
        
        this.sws = sws;
    }

    public String getSws() {
        return sws;
    }
    
}