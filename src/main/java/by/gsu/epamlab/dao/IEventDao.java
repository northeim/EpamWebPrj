package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.Event;

import java.util.List;

public interface IEventDao {

    List<Event> getAll();
    List<Event> getAllWithOld();
    Event getById(int id);
    int insert(Event event);
    int update(Event event);
    int delete(int id);

}
