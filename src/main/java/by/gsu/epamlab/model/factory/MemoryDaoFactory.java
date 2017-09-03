package by.gsu.epamlab.model.factory;

import by.gsu.epamlab.model.dao.*;
import by.gsu.epamlab.model.daoimp.memory.*;

public class MemoryDaoFactory extends AbstractDaoFactory {

    private IAuthorDao authorDao;
    private IEventDao eventDao;
    private IFilmDao filmDao;
    private IOrderDao orderDao;
    private IRoleDao roleDao;
    private IUserDao userDao;

    public IAuthorDao getAuthorDao() {
        if (authorDao == null) {
            authorDao = new AuthorDaoMemory();
        }
        return authorDao;
    }

    public IEventDao getEventDao() {
        if (eventDao == null) {
            eventDao = new EventDaoMemory();
        }
        return eventDao;
    }

    public IFilmDao getFilmDao() {
        if (filmDao == null) {
            filmDao = new FilmDaoMemory();
        }
        return filmDao;
    }

    public IOrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoMemory();
        }
        return orderDao;
    }

    public IRoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoMemory();
        }
        return roleDao;
    }

    public IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoMemory();
        }
        return userDao;
    }
}
