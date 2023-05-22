package app.errors;

public class GenericException extends RuntimeException {
    private String message;
    public GenericException() {}

    public GenericException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message!=null ? message : "Something went wrong";
    }
}
