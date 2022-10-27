package it.jdk.project.repository;

/**
 * RepoException
 */
public class RepoException extends Exception{

    private String code = "0000";

    /**
     * No argument constructor
     */
    public RepoException() {}

    /**
     * Costructor for Error Code
     * @param message Error Message
     * @param code Error Code
     */
    public RepoException(String message, String code) {
        super (message);
        this.code = code;
    }

    /**
     * Message Constructor
     * @param message Error Message
     */
    public RepoException(String message) {
        super (message);
    }

    /**
     * Throwable Constructor
     * @param cause Cause
     */
    public RepoException(Throwable cause) {
        super (cause);
    }

    /**
     * Message and Throwable Constructor
     * @param message Error message
     * @param cause Cause
     */
    public RepoException(String message, Throwable cause) {
        super (message, cause);
    }

    /**
     * Error code
     * @return error code
     */
    public String getCode() {
        return code;
    }
}
