package by.gsu.epamlab.exeptions;

public class ValidationException extends RuntimeException {

    private String value;

    public ValidationException(String message) {
        this.value = message;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ValidationExeption{" +
                "value='" + value + '\'' +
                '}';
    }
}
