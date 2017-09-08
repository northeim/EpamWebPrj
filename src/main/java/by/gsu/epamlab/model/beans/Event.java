package by.gsu.epamlab.model.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {

    private int id;
    private String eventName;
    private Date eventDate;
    private String eventData;
    private String eventTime;
    private int filmId;

    public Event(int id, String eventName, Date eventDate, int filmId) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = new SimpleDateFormat("HH:mm").format(eventDate);
        this.eventData = new SimpleDateFormat("dd.MM").format(eventDate);
        this.filmId = filmId;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventData() {
        return eventData;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
        this.eventTime = new SimpleDateFormat("HH:mm").format(eventDate);
        this.eventData = new SimpleDateFormat("dd.MM").format(eventDate);
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (filmId != event.filmId) return false;
        if (eventName != null ? !eventName.equals(event.eventName) : event.eventName != null)
            return false;
        if (eventDate != null ? !eventDate.equals(event.eventDate) : event.eventDate != null)
            return false;
        if (eventData != null ? !eventData.equals(event.eventData) : event.eventData != null)
            return false;
        return eventTime != null ? eventTime.equals(event.eventTime) : event.eventTime == null;
    }

    @Override
    public int hashCode() {
        int result = eventName != null ? eventName.hashCode() : 0;
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        result = 31 * result + (eventData != null ? eventData.hashCode() : 0);
        result = 31 * result + (eventTime != null ? eventTime.hashCode() : 0);
        result = 31 * result + filmId;
        return result;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", eventData='" + eventData + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", filmId=" + filmId +
                '}';
    }

}
