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
        this.orderTime = new SimpleDateFormat("HH:mm").format(orderDate);
        this.orderData = new SimpleDateFormat("dd.MM").format(orderDate);
    }

    public void setTicketString(String ticketString) {
        this.ticketString = ticketString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (userId != order.userId) return false;
        if (eventId != order.eventId) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null)
            return false;
        if (ticketString != null ? !ticketString.equals(order.ticketString) : order.ticketString != null)
            return false;
        if (orderData != null ? !orderData.equals(order.orderData) : order.orderData != null)
            return false;
        return orderTime != null ? orderTime.equals(order.orderTime) : order.orderTime == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + eventId;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (ticketString != null ? ticketString.hashCode() : 0);
        result = 31 * result + (orderData != null ? orderData.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", orderDate=" + orderDate +
                ", ticketString='" + ticketString + '\'' +
                ", orderData='" + orderData + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
