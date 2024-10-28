package com.mkyong.web;

// import java.sql.Date;

public class Bus {
    
    // private String booking_date;
    private int bus_no;
    private String bus_name;
    private int avail_seats;
    private String customer_name;
    private int id;
    // public String getBooking_date() {
    //     return booking_date;
    // }
    // public void setBooking_date(String booking_date) {
    //     this.booking_date = booking_date;
    // }
    public int getBus_no() {
        return bus_no;
    }
    public void setBus_no(int bus_no) {
        this.bus_no = bus_no;
    }
    public String getBus_name() {
        return bus_name;
    }
    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }
    public int getAvail_seats() {
        return avail_seats;
    }
    public void setAvail_seats(int avail_seats) {
        this.avail_seats = avail_seats;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Bus [ bus_no=" + bus_no + ", bus_name=" + bus_name + ", avail_seats="
                + avail_seats + ", customer_name=" + customer_name + ", id=" + id + "]";
    }

    
    

}
