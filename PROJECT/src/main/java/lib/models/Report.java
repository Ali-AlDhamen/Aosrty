package lib.models;

import java.sql.Timestamp;

public class Report {

    private int id;
    private String content;
    private int customerID;
    private Timestamp time;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    public int getCustomerID()
    {
        return customerID;
    }
    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }
    public Timestamp getTime()
    {
        return time;
    }
    public void setTime(Timestamp time)
    {
        this.time = time;
    }


    Report(){}

    Report(int id, String content, int customerID, Timestamp time){
        this.id = id;
        this.content = content;
        this.customerID = customerID;
        this.time = time;
    }

    
}
