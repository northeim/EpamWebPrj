package by.gsu.epamlab.model.modelUtils;

import javax.servlet.http.HttpServletRequest;

public interface ITableOperation {

    void addRecord(HttpServletRequest req);
    void editRecord(HttpServletRequest req);
    void deleteRecord(HttpServletRequest req);

}
