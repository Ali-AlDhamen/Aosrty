package lib.features.customerHome;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

import lib.API.Database;
import lib.common.Audio;
import lib.features.customerHome.panels.*;
import lib.models.Order;
import lib.models.OrderItem;
import lib.models.Product;
import lib.models.Store;
import lib.theme.AppColors;

public class CustomerHomeScreen extends JFrame
{
    static private ContactUsPanel contactUsPanel;
    static private StoresPanel storesPanel;
    static private ReportPanel reportPanel;
    static private OrdersPanel ordersPanel;
    static private CartPanel CartPanel;
    static private Container contentPane;

    static public Store openedStore = null;

    static public ArrayList<Store> stores = new ArrayList<Store>();
    static public ArrayList<Product> products = new ArrayList<Product>();
    static public ArrayList<OrderItem> cart = new ArrayList<OrderItem>();
    static public ArrayList<Order> orders = new ArrayList<Order>();

    public static Order order = new Order();
    public static boolean firstPage = true;

    public CustomerHomeScreen()
    {
        super("Customer Home");
        Audio.loopAudio("PROJECT/src/main/assets/audios/skor.wav");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(AppColors.darkBlue);
        setLayout(new BorderLayout());
        add(new NavBar(), BorderLayout.WEST);
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
        contactUsPanel = new ContactUsPanel();
        storesPanel = new StoresPanel();
        reportPanel = new ReportPanel();
        ordersPanel = new OrdersPanel();
        CartPanel = new CartPanel();

        add(storesPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    static public void switchToStoresPanel()
    {
        contentPane.removeAll();
        storesPanel = new StoresPanel();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(storesPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();

    }

    static public void switchToContactUsPanel()
    {
        contentPane.removeAll();
        contactUsPanel = new ContactUsPanel();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(contactUsPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToReportPanel()
    {
        contentPane.removeAll();
        reportPanel = new ReportPanel();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(reportPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToOrdersPanel()
    {
        contentPane.removeAll();
        ordersPanel = new OrdersPanel();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(ordersPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToCartPanel()
    {
        contentPane.removeAll();
        CartPanel = new CartPanel();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(CartPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToStoreItemsPanel(int id)
    {

        if (openedStore != null && id != openedStore.getId())
        {
            cart.clear();
        }
        Database.getCustomerProducts(id);
        contentPane.removeAll();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(new StoreItemsPanel(), BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    // cart panel refresh
    static public void refreshCartPanel()
    {
        contentPane.removeAll();
        CartPanel = new CartPanel();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(CartPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

   
    static public void refreshOrdersPanel()
    {
        Database.getCustomerOrders();
        contentPane.removeAll();
        ordersPanel = new OrdersPanel();
        contentPane.add(new NavBar(), BorderLayout.WEST);
        contentPane.add(ordersPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    // refresh main frame
    static public void refreshMainFrame()
    {
        new CustomerHomeScreen();
    }

}
