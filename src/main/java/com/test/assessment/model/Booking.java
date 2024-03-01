package com.test.assessment.model;

public class Booking {
    private String customerName;
    private int tableSize;
    private String date;
    private String time;

    public Booking(String customerName, int tableSize, String date, String time) {
        this.customerName = customerName;
        this.tableSize = tableSize;
        this.date = date;
        this.time = time;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTableSize() {
        return tableSize;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}