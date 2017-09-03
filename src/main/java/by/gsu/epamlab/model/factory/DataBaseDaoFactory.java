package by.gsu.epamlab.model.factory;

import by.gsu.epamlab.dao.*;
import by.gsu.epamlab.daoimp.database.*;

public class DataBaseDaoFactory extends AbstractDaoFactory {

    private IAuthorDao authorDao;
    private IEventDao eventDao;
    private IFilmDao filmDao;
    private IOrderDao orderDao;
    private IRoleDao roleDao;
    private IUserDao userDao;

    public IAuthorDao getAuthorDao() {
        if (authorDao == null) {
            authorDao = new AuthorDaoDataBase();
        }
        return authorDao;
    }

    public IEventDao getEventDao() {
        if (eventDao == null) {
            eventDao = new EventDaoDataBase();
        }
        return eventDao;
    }

    public IFilmDao getFilmDao() {
        if (filmDao == null) {
            filmDao = new FilmDaoDataBase();
        }
        return filmDao;
    }

    public IOrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoDataBase();
        }
        return orderDao;
    }

    public IRoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoDataBase();
        }
        return roleDao;
    }

    public IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoDataBase();
        }
        return userDao;
    }
}
