package lib.models;

public class DiscountCode
{
 
    private int code_id;
    private String code_name;
    private double code_value;
    private int code_store_id;

    public int getCode_id()
    {
        return code_id;
    }

    public void setCode_id(int code_id)
    {
        this.code_id = code_id;
    }

    public String getCode_name()
    {
        return code_name;
    }

    public void setCode_name(String code_name)
    {
        this.code_name = code_name;
    }

    public double getCode_value()
    {
        return code_value;
    }

    public void setCode_value(double code_value)
    {
        this.code_value = code_value;
    }

    public int getCode_store_id()
    {
        return code_store_id;
    }

    public void setCode_store_id(int code_store_id)
    {
        this.code_store_id = code_store_id;
    }

    public DiscountCode()
    {
    }

    public DiscountCode(int code_id, String code_name, double code_value, int code_store_id)
    {
        this.code_id = code_id;
        this.code_name = code_name;
        this.code_value = code_value;
        this.code_store_id = code_store_id;
    }

    

}
