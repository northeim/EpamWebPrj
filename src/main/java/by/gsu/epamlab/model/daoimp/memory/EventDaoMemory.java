package by.gsu.epamlab.model.daoimp.memory;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Event;
import by.gsu.epamlab.model.dao.IEventDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventDaoMemory implements IEventDao {

    private static final Map<Integer, Event> EVENTS = new HashMap<Integer, Event>();
    private static int id = 1;

    static {
        Event event;
        try {
            event = new Event(id, "Film",
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2017-09-30T00:00"),
                    1);
        } catch (ParseException e) {
            throw new DataBaseException(e.getMessage());
        }
        EVENTS.put(id, event);
        generateId();
    }

    private static int generateId() {
        return id++;
    }

    public List<Event> getAll() {
        List<Event> events = new ArrayList<Event>();
        for (Map.Entry<Integer, Event> entry: EVENTS.entrySet()) {
            if (entry.getValue().getEventDate().after(new Date())) {
                events.add(entry.getValue());
            }
        }
        return events;
    }

    public List<Event> getAllWithOld() {
        return new ArrayList<Event>(EVENTS.values());
    }

    public Event getById(int id) {
        Event event;
        if (EVENTS.containsKey(id)) {
            event = EVENTS.get(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_EVENT_IN_DB);
        }
        return event;
    }

    public int insert(Event event) {
        synchronized (EVENTS) {
            event.setId(id);
            EVENTS.put(id, event);
            generateId();
        }
        return 0;
    }

    public int update(Event event) {
        if (EVENTS.containsKey(event.getId())) {
            EVENTS.put(event.getId(), event);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_EVENT_IN_DB);
        }
        return 0;
    }

    public int delete(int id) {
        if (EVENTS.containsKey(id)) {
            EVENTS.remove(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_EVENT_IN_DB);
        }
        return 0;
    }
}
