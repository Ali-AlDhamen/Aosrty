package lib.features.partnerHome;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import lib.API.Database;
import lib.common.Audio;
import lib.features.partnerHome.panels.AddItemPanel;
import lib.features.partnerHome.panels.DiscountCodePanel;
import lib.features.partnerHome.panels.PartnerNavBar;
import lib.features.partnerHome.panels.StoreItemsPanel;
import lib.features.partnerHome.panels.StoreOrdersPanel;
import lib.models.DiscountCode;
import lib.models.Order;
import lib.models.Partner;
import lib.models.Product;
import lib.models.Store;
import lib.theme.AppColors;

import java.awt.event.*;

public class PartnerHomeScreen extends JFrame
{

    static public Store store = null;
    static public Partner partner = null;
    static private StoreItemsPanel storeItemsPanel;
    static private AddItemPanel addItemPanel;
    static private StoreOrdersPanel storeOrdersPanel;
    static private DiscountCodePanel discountCodePanel;
    static private Container contentPane;

    static public ArrayList<Product> products = new ArrayList<Product>();
    static public ArrayList<DiscountCode> discountCodes = new ArrayList<DiscountCode>();

    static public ArrayList<Order> storeOrders = new ArrayList<Order>();
    

    public PartnerHomeScreen()
    {

        super("Partner Home");
        Audio.playAudio("PROJECT/src/main/assets/audios/lost.wav");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(AppColors.darkBlue);
        setLayout(new BorderLayout());
        add(new PartnerNavBar(), BorderLayout.WEST);

        addWindowListener(
                new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent windowEvent)
                    {
                        Database.closeDB();
                        System.exit(0);
                    }
                });

        contentPane = getContentPane();
        storeItemsPanel = new StoreItemsPanel();
        addItemPanel = new AddItemPanel();
        storeOrdersPanel = new StoreOrdersPanel();
        discountCodePanel = new DiscountCodePanel();

        this.add(storeItemsPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    static public void switchToStoreItemsPanel()
    {

        contentPane.removeAll();
        storeItemsPanel = new StoreItemsPanel();
        contentPane.add(new PartnerNavBar(), BorderLayout.WEST);
        contentPane.add(storeItemsPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToAddItemPanel()
    {

        contentPane.removeAll();
        addItemPanel = new AddItemPanel();
        contentPane.add(new PartnerNavBar(), BorderLayout.WEST);
        contentPane.add(addItemPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToStoreOrdersPanel()
    {

        contentPane.removeAll();
        Database.getStoreOrders();
        storeOrdersPanel = new StoreOrdersPanel();
        contentPane.add(new PartnerNavBar(), BorderLayout.WEST);
        contentPane.add(storeOrdersPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToDiscountCodePanel()
    {

        contentPane.removeAll();
        discountCodePanel = new DiscountCodePanel();
        contentPane.add(new PartnerNavBar(), BorderLayout.WEST);
        contentPane.add(discountCodePanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void refresh()
    {

        Database.getProducts(Database.currentStore.getId());
        contentPane.removeAll();
        storeItemsPanel = new StoreItemsPanel();
        contentPane.add(new PartnerNavBar(), BorderLayout.WEST);
        contentPane.add(storeItemsPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void codeRefresh(){
            
            Database.getDiscountCodes(Database.currentStore.getId());
            contentPane.removeAll();
            discountCodePanel = new DiscountCodePanel();
            contentPane.add(new PartnerNavBar(), BorderLayout.WEST);
            contentPane.add(discountCodePanel, BorderLayout.CENTER);
            contentPane.revalidate();
            contentPane.repaint();
    }

    static  public void orderRefresh(){
            
            Database.getStoreOrders();
            contentPane.removeAll();
            storeOrdersPanel = new StoreOrdersPanel();
            contentPane.add(new PartnerNavBar(), BorderLayout.WEST);
            contentPane.add(storeOrdersPanel, BorderLayout.CENTER);
            contentPane.revalidate();
            contentPane.repaint();
    }

   

}
