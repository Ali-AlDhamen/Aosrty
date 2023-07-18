package lib.models;

public class OrderItem
{

    private int orderitem_id;
    private String orderitem_name;
    private int orderitem_quantity;
    private int orderitem_product_id;
    private int orderitem_order_id;
    private double orderitem_price;

    public double getOrderitem_price()
    {
        return orderitem_price;
    }

    public void setOrderitem_price(double orderitem_price)
    {
        this.orderitem_price = orderitem_price;
    }

    public String getOrderitem_name()
    {
        return orderitem_name;
    }

    public void setOrderitem_name(String orderitem_name)
    {
        this.orderitem_name = orderitem_name;
    }

    public int getOrderitem_id()
    {
        return orderitem_id;
    }

    public void setOrderitem_id(int orderitem_id)
    {
        this.orderitem_id = orderitem_id;
    }

    public int getOrderitem_quantity()
    {
        return orderitem_quantity;
    }

    public void setOrderitem_quantity(int orderitem_quantity)
    {
        this.orderitem_quantity = orderitem_quantity;
    }

    public int getOrderitem_product_id()
    {
        return orderitem_product_id;
    }

    public void setOrderitem_product_id(int orderitem_product_id)
    {
        this.orderitem_product_id = orderitem_product_id;
    }

    public int getOrderitem_order_id()
    {
        return orderitem_order_id;
    }

    public void setOrderitem_order_id(int orderitem_order_id)
    {
        this.orderitem_order_id = orderitem_order_id;
    }

    public OrderItem()
    {
    }

    public OrderItem(String orderitem_name, int orderitem_id, int orderitem_quantity, int orderitem_product_id, int orderitem_order_id)
    {
        this.orderitem_id = orderitem_id;
        this.orderitem_quantity = orderitem_quantity;
        this.orderitem_product_id = orderitem_product_id;
        this.orderitem_order_id = orderitem_order_id;
        this.orderitem_name = orderitem_name;
    }

    public OrderItem(String orderitem_name, int orderitem_quantity, int orderitem_product_id, int orderitem_order_id)
    {
        this.orderitem_quantity = orderitem_quantity;
        this.orderitem_product_id = orderitem_product_id;
        this.orderitem_order_id = orderitem_order_id;
        this.orderitem_name = orderitem_name;
    }

    public OrderItem(String orderitem_name, int orderitem_quantity, int orderitem_product_id, double orderitem_price)
    {
        this.orderitem_quantity = orderitem_quantity;
        this.orderitem_product_id = orderitem_product_id;
        this.orderitem_name = orderitem_name;
        this.orderitem_price = orderitem_price;
    }

}
