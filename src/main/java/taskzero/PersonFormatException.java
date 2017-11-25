package taskzero;

public class PersonFormatException extends Exception {

    private String message;

    public PersonFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
