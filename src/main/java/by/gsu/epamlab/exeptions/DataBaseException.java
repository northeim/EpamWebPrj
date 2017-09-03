package by.gsu.epamlab.exeptions;

public class DataBaseException extends RuntimeException {

    private String value;

    public DataBaseException(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DataBaseExeption{" +
                "value='" + value + '\'' +
                '}';
    }
}
