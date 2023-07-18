package lib.models;

import java.sql.Timestamp;

public class Order {
    // order_id INT PRIMARY KEY AUTO_INCREMENT,
    // order_time TIMESTAMP NOT NULL,
    // order_status VARCHAR(255) NOT NULL,
    // order_customer_id INT,
    // order_store_id INT,
    // order_cost DECIMAL(10,2) NOT NULL,
    // order_address varchar(255),

    private int id;
    private Timestamp time;
    private String status;
    private int customer_id;
    private int store_id;
    private double cost;
    private String address;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public Timestamp getTime()
    {
        return time;
    }
    public void setTime(Timestamp time)
    {
        this.time = time;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public int getCustomer_id()
    {
        return customer_id;
    }
    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }
    public int getStore_id()
    {
        return store_id;
    }
    public void setStore_id(int store_id)
    {
        this.store_id = store_id;
    }
    public double getCost()
    {
        return cost;
    }
    public void setCost(double cost)
    {
        this.cost = cost;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public Order(){}

    Order(int id, Timestamp time, String status, int customer_id, int store_id, double cost, String address){
        this.id = id;
        this.time = time;
        this.status = status;
        this.customer_id = customer_id;
        this.store_id = store_id;
        this.cost = cost;
        this.address = address;
    }


    
}
