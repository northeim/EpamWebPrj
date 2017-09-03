package by.gsu.epamlab.exeptions;

public class ValidationExeption extends RuntimeException {

    private String value;

    public ValidationExeption(String message) {
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
