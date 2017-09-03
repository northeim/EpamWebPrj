package by.gsu.epamlab.model.beans;

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

    public Order(int id, int userId, int eventId, Date orderDate, String ticketString) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.orderDate = orderDate;
        this.ticketString = ticketString;
        this.orderTime = new SimpleDateFormat("HH:mm").format(orderDate);
        this.orderData = new SimpleDateFormat("dd.MM").format(orderDate);
    }


    public String getOrderData() {
        return orderData;
    }

    public String getOrderTime() {
        return orderTime;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTicketString(String ticketString) {
        this.ticketString = ticketString;
    }

    public void setOrderData(String orderData) {
        this.orderData = orderData;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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
