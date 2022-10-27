package it.jdk.project.rest;

public class ServiceException extends Exception {

    private String code = "0000";

    public ServiceException() {}

    
    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    
    public ServiceException(String message) {
        super (message);
    }

    
    public ServiceException(Throwable cause) {
        super (cause);
    }

    
    public ServiceException(String message, Throwable cause) {
        super (message, cause);
    }

   
    public String getCode() {
        return code;
    }
}