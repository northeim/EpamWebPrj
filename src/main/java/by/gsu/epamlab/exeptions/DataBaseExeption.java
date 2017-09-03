package by.gsu.epamlab.exeptions;

public class DataBaseExeption extends RuntimeException {

    private String value;

    public DataBaseExeption(String value) {
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
