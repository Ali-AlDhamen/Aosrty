package lib.models;

import javax.swing.ImageIcon;

public class Product {


    private int id;
    private String name;
    private double price;
    private int storeId;
    private String status;
    private ImageIcon image;


    public Product(){}


    public Product(int id, String name, double price, int storeId, String status, ImageIcon image)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.storeId = storeId;
        this.status = status;
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
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public int getStoreId()
    {
        return storeId;
    }
    public void setStoreId(int storeId)
    {
        this.storeId = storeId;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public ImageIcon getImage()
    {
        return image;
    }
    public void setImage(ImageIcon image)
    {
        this.image = image;
    }

    
}
