package by.gsu.epamlab.model.factory;

import by.gsu.epamlab.dao.*;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDaoFactory {

    private static final Map<Class<? extends AbstractDaoFactory>, AbstractDaoFactory> MAP =
            new HashMap<Class<? extends AbstractDaoFactory>, AbstractDaoFactory>();

    static {
        MAP.put(MemoryDaoFactory.class, new MemoryDaoFactory());
        MAP.put(DataBaseDaoFactory.class, new DataBaseDaoFactory());
    }

    public static AbstractDaoFactory getDaoFactory(Class<? extends AbstractDaoFactory> clazz) {
        if (MAP.containsKey(clazz)) {
            return MAP.get(clazz);
        } else {
            throw new IllegalArgumentException(clazz.getName());
        }
    }

    public abstract IAuthorDao getAuthorDao();
    public abstract IEventDao getEventDao();
    public abstract IFilmDao getFilmDao();
    public abstract IOrderDao getOrderDao();
    public abstract IRoleDao getRoleDao();
    public abstract IUserDao getUserDao();

}
