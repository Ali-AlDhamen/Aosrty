package lib.common;

import java.awt.Image;
import java.sql.Timestamp;

import javax.swing.*;

public class Helper
{

    public static ImageIcon ScaleDown(String url)
    {
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;

    }

    public static ImageIcon ScaleDown(String url, int up)
    {
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;

    }
    
    public static ImageIcon ScaleImage(String url, int width , int height)
    {
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;

    }


    public static ImageIcon logo(String url)
    {
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;

    }

    public static ImageIcon storeImages(String url)
    {
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;

    }

    public static ImageIcon itemImage(String url)
    {
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;

    }

    public static String convertTimestamp(Timestamp timestamp)
    {
        String date = timestamp.toString();
        String[] dateArray = date.split(" ");
        String[] timeArray = dateArray[1].split("\\.");
        String time = timeArray[0];
        String[] timeArray2 = time.split(":");
        String hour = timeArray2[0];
        String minute = timeArray2[1];
        String second = timeArray2[2];
        String finalTime = hour + ":" + minute + ":" + second;
        return dateArray[0] + " " + finalTime;

    }

}
