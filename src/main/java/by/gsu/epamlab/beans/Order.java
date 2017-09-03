package by.gsu.epamlab.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

    private int id;
    private int userId;
    private int eventId;
    private Date orderDate;
    private String ticketString;
    private String orderData;
    private String orderTime;
    private String eventName;

    public Order(int id, int userId, int eventId, Date orderDate, String ticketString) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.orderDate = orderDate;
        this.ticketString = ticketString;
        this.orderTime = new SimpleDateFormat("HH:mm").format(orderDate);
        this.orderData = new SimpleDateFormat("dd.MM").format(orderDate);
        this.eventName = "";
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getOrderData() {
        return orderData;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getEventName() {
        return eventName;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getEventId() {
        return eventId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getTicketString() {
        return ticketString;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", orderDate=" + orderDate +
                ", ticketString='" + ticketString + '\'' +
                '}';
    }
}
