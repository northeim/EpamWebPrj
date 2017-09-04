package by.gsu.epamlab.model.modelUtils.admin;


import by.gsu.epamlab.controllers.Constant;

public class TableFactory {

    public  static ITableOperation getITableObj(String tableName) {

        ITableOperation obj = null;
        if (Constant.DBTables.USERS.equals(tableName)) {
            obj = new UsersTable();
        } else if (Constant.DBTables.AUTHORS.equals(tableName)) {
            obj = new AuthorsTable();
        } else if (Constant.DBTables.EVENTS.equals(tableName)) {
            obj = new EventsTable();
        } else if (Constant.DBTables.FILMS.equals(tableName)) {
            obj = new FilmsTable();
        } else if (Constant.DBTables.ORDERS.equals(tableName)) {
            obj = new OrdersTable();
        } else if (Constant.DBTables.ROLE.equals(tableName)) {
            obj = new RoleTable();
        }
        return obj;
    }

}
