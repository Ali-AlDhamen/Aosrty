package lib.models;

import javax.swing.*;


public class Store
{


    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private int ordersNumber;
    private int ownerId;
    private ImageIcon image;
    

    public ImageIcon getImage()
    {
        return image;
    }

    public void setImage(ImageIcon image)
    {
        this.image = image;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public int getOrdersNumber()
    {
        return ordersNumber;
    }

    public void setOrdersNumber(int ordersNumber)
    {
        this.ordersNumber = ordersNumber;
    }

    public int getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(int ownerId)
    {
        this.ownerId = ownerId;
    }

    public Store(int id, String name, String address, String phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Store()
    {

    }

    public Store(int id, String name, String address, String phoneNumber, int ordersNumber, int ownerId, ImageIcon image)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ordersNumber = ordersNumber;
        this.ownerId = ownerId;
        this.image = image;
    }

}
