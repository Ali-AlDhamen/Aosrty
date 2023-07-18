package lib.API;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import lib.App;
import lib.features.customerHome.CustomerHomeScreen;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.models.Customer;
import lib.models.DiscountCode;
import lib.models.Order;
import lib.models.OrderItem;
import lib.models.Partner;
import lib.models.Product;
import lib.models.Store;
import lib.models.User;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;

public class Database
{

    private static Connection connection = null;
    private static PreparedStatement statement = null;
    public static User currentUser;
    public static Store currentStore;
    public static PartnerHomeScreen partnerHomeScreen = null;

    private static Connection connectDB()
    {
        String DATABASE_URL = "jdbc:mysql://localhost:3306/aosrty";
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            return connection;
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static void closeDB()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static ResultSet Query(String query)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {
            statement = connection.prepareStatement(query);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            return rs;

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static int registerCustomer(String name, String email, String password)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {
            statement = connection.prepareStatement("INSERT INTO customer (customer_name, customer_email, customer_password) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);

            // Execute the statement
            statement.executeUpdate();
            loginCustomer(email, password);

            return 1;

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally
        {
            closeDB();
        }
    }

    public static int loginCustomer(String email, String password)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {
            statement = connection.prepareStatement("SELECT * FROM customer WHERE customer_email = ? AND customer_password = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                currentUser = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"), rs.getString("customer_email"),
                        rs.getString("customer_password"));
                return 1;
            }
            else
            {
                return 0;
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally
        {
            closeDB();
        }
    }

    public static int registerStore(String partnerName, String storeName, String email, String password, InputStream image, String phone, String address)
    {

        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {
            connection = connectDB();
            if (checkPartner(email, password) == 1)
            {
                statement = connection.prepareStatement(
                        "INSERT INTO store (store_image , store_name, store_address , store_phonenumber, store_owner_id ) VALUES (?, ?, ?, ?, ?)");
                statement.setBlob(1, image);
                statement.setString(2, storeName);
                statement.setString(3, address);
                statement.setString(4, phone);
                statement.setInt(5, currentUser.getId());

                // Execute the statement
                statement.executeUpdate();

            }
            else
            {
                statement = connection.prepareStatement("INSERT INTO partner (partner_name, partner_email, partner_password) VALUES (?, ?, ?)");
                statement.setString(1, partnerName);
                statement.setString(2, email);
                statement.setString(3, password);

                // Execute the statement
                statement.executeUpdate();

                checkPartner(email, password);

                statement = connection.prepareStatement(
                        "INSERT INTO store (store_image , store_name, store_address , store_phonenumber, store_owner_id ) VALUES (?, ?, ?, ?, ?)");
                statement.setBlob(1, image);
                statement.setString(2, storeName);
                statement.setString(3, address);
                statement.setString(4, phone);
                statement.setInt(5, currentUser.getId());

                // Execute the statement
                statement.executeUpdate();
            }

            loginStore(email, password, storeName);
            

            return 1;

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally
        {
            closeDB();
        }

    }

    public static int checkPartner(String email, String password)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {
            statement = connection.prepareStatement("SELECT * FROM partner WHERE partner_email = ? AND partner_password = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                currentUser = new Partner(rs.getInt("partner_id"), rs.getString("partner_name"), rs.getString("partner_email"),
                        rs.getString("partner_password"));
                return 1;
            }
            else
            {
                return 0;
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

    }

    public static int loginStore(String email, String password, String storeName)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement

        try
        {
            checkPartner(email, password);

            statement = connection.prepareStatement(
                    "SELECT * FROM partner JOIN store ON partner.partner_id = store.store_owner_id WHERE partner_email = ? AND partner_password = ? AND store_name = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, storeName);
            // Execute the statement
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                InputStream inputStream = rs.getBinaryStream("store_image");
                try
                {
                    BufferedImage image = ImageIO.read(inputStream);
                    Image image2 = image.getScaledInstance(1, 1, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(image2);

                    currentStore = new Store(rs.getInt("store_id"), rs.getString("store_name"), rs.getString("store_address"),
                            rs.getString("store_phonenumber"), rs.getInt("store_orders_number"), rs.getInt("store_owner_id"), imageIcon);

                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "IOException error", JOptionPane.ERROR_MESSAGE);

                }
                App.partnerLoginScreen.dispose();
                App.partnerHomeScreen = new PartnerHomeScreen();

                return 1;
            }
            else
            {

                JOptionPane.showMessageDialog(null, "incorrect email or password or store name", "failed login", JOptionPane.ERROR_MESSAGE);
                return 0;
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally
        {
            closeDB();
        }

    }

    public static void addProduct(String productName, double productPrice, int storeID, InputStream productImage)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {
            statement = connection.prepareStatement(
                    "INSERT INTO product (product_image ,product_name , product_price  , product_store_id, product_status) VALUES (?, ?, ?, ?, ?)");
            statement.setBlob(1, productImage);
            statement.setString(2, productName);
            statement.setDouble(3, productPrice);
            statement.setInt(4, storeID);
            statement.setString(5, "available");

            // Execute the statement
            int res = statement.executeUpdate();
            if (res == 1)
            {
                JOptionPane.showMessageDialog(null, "product added successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "product not added", "failed", JOptionPane.ERROR_MESSAGE);
            }
            partnerHomeScreen.refresh();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void getProducts(int id)
    {
        // Get the connection
        connection = connectDB();
        PartnerHomeScreen.products.clear();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM product WHERE product_store_id = ?");
            statement.setInt(1, id);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                InputStream inputStream = rs.getBinaryStream("product_image");
                try
                {
                    BufferedImage image = ImageIO.read(inputStream);
                    Image image2 = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(image2);

                    Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("product_price"),
                            rs.getInt("product_store_id"), rs.getString("product_status"), imageIcon);

                    PartnerHomeScreen.products.add(product);
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "IOException error", JOptionPane.ERROR_MESSAGE);

                }
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }

    }

    public static void changeProductStatus(int id, String status)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("UPDATE product SET product_status = ? WHERE product_id = ?");
            statement.setString(1, status);
            statement.setInt(2, id);

            // Execute the statement
            int res = statement.executeUpdate();
            if (res == 1)
            {
                JOptionPane.showMessageDialog(null, "product status changed successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "product status not changed", "failed", JOptionPane.ERROR_MESSAGE);
            }
            partnerHomeScreen.refresh();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void deleteProduct(int id)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("DELETE FROM product WHERE product_id = ?");
            statement.setInt(1, id);

            // Execute the statement
            int res = statement.executeUpdate();
            if (res == 1)
            {
                JOptionPane.showMessageDialog(null, "product deleted successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "product not deleted", "failed", JOptionPane.ERROR_MESSAGE);
            }
            partnerHomeScreen.refresh();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void updateProduct(int id , String name , double price){
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("UPDATE product SET product_name = ? , product_price = ? WHERE product_id = ?");
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, id);

            // Execute the statement
            int res = statement.executeUpdate();
            if (res == 1)
            {
                JOptionPane.showMessageDialog(null, "product updated successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "product not updated", "failed", JOptionPane.ERROR_MESSAGE);
            }
            PartnerHomeScreen.refresh();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }

    }

    public static void updateProduct(int id, String name, double price, InputStream image)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("UPDATE product SET product_name = ? , product_price = ? , product_image = ? WHERE product_id = ?");
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setBlob(3, image);
            statement.setInt(4, id);

            // Execute the statement
            int res = statement.executeUpdate();
            if (res == 1)
            {
                JOptionPane.showMessageDialog(null, "product updated successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "product not updated", "failed", JOptionPane.ERROR_MESSAGE);
            }
            PartnerHomeScreen.refresh();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void addDiscountCode(String discountCodeName, double discountValue, int storeID)
    {
        // Get the connection

        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("INSERT INTO code (code_name , code_value , code_store_id) VALUES (? , ? , ?)");
            statement.setString(1, discountCodeName);
            statement.setDouble(2, discountValue);
            statement.setInt(3, storeID);

            // Execute the statement
            int res = statement.executeUpdate();
            if (res == 1)
            {
                JOptionPane.showMessageDialog(null, "discount code added successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "discount code not added", "failed", JOptionPane.ERROR_MESSAGE);
            }
            partnerHomeScreen.codeRefresh();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void getDiscountCodes(int storeID)
    {
        // Get the connection
        connection = connectDB();
        PartnerHomeScreen.discountCodes.clear();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM code WHERE code_store_id = ?");
            statement.setInt(1, storeID);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                DiscountCode discountCode = new DiscountCode(rs.getInt("code_id"), rs.getString("code_name"), rs.getDouble("code_value"),
                        rs.getInt("code_store_id"));
                PartnerHomeScreen.discountCodes.add(discountCode);
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void deleteDiscountCode(int id)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("DELETE FROM code WHERE code_id = ?");
            statement.setInt(1, id);

            // Execute the statement
            int res = statement.executeUpdate();
            if (res == 1)
            {
                JOptionPane.showMessageDialog(null, "discount code deleted successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "discount code not deleted", "failed", JOptionPane.ERROR_MESSAGE);
            }
            partnerHomeScreen.codeRefresh();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }

    }

    public static void getStores()
    {
        // Get the connection
        connection = connectDB();
        CustomerHomeScreen.stores.clear();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM store ");

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                try
                {
                    InputStream is = rs.getBinaryStream("store_image");
                    BufferedImage image = ImageIO.read(is);
                    Image image1 = image.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(image1);

                    Store store = new Store(rs.getInt("store_id"), rs.getString("store_name"), rs.getString("store_address"),
                            rs.getString("store_phonenumber"), rs.getInt("store_orders_number"), rs.getInt("store_owner_id"), imageIcon);
                    CustomerHomeScreen.stores.add(store);

                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "IOException error", JOptionPane.ERROR_MESSAGE);

                }

            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void getCustomerProducts(int id)
    {
        // Get the connection
        connection = connectDB();
        CustomerHomeScreen.products.clear();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM product WHERE product_store_id = ?");
            statement.setInt(1, id);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                try
                {
                    InputStream inputStream = rs.getBinaryStream("product_image");
                    BufferedImage image = ImageIO.read(inputStream);
                    Image image2 = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(image2);

                    Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("product_price"),
                            rs.getInt("product_store_id"), rs.getString("product_status"), imageIcon);
                    if (product.getStatus().equals("available"))
                    {
                        CustomerHomeScreen.products.add(product);
                    }

                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "IOException error", JOptionPane.ERROR_MESSAGE);

                }

            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void addOrder(Order order)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement(
                    "INSERT INTO orders (order_customer_id, order_store_id, order_cost, order_status, order_address) VALUES (?, ?, ?, ? , ?)");
            statement.setInt(1, order.getCustomer_id());
            statement.setInt(2, order.getStore_id());
            statement.setDouble(3, order.getCost());
            statement.setString(4, order.getStatus());
            statement.setString(5, order.getAddress());

            // Execute the statement
            int res = statement.executeUpdate();
            if (res >= 1)
            {
                JOptionPane.showMessageDialog(null, "your order has been placed successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "order not added", "failed", JOptionPane.ERROR_MESSAGE);
            }
            CustomerHomeScreen.refreshOrdersPanel();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static void addOrderItems(ArrayList<OrderItem> orderItems)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {
            int orderId = getLastOrderId();

            statement = connection.prepareStatement(
                    "INSERT INTO orderitem ( orderitem_quantity, orderitem_product_id, orderitem_order_id ) VALUES (?, ?, ?)");
            for (OrderItem orderItem : orderItems)
            {

                statement.setInt(1, orderItem.getOrderitem_quantity());
                statement.setInt(2, orderItem.getOrderitem_product_id());
                statement.setInt(3, orderId);

                // Execute the statement
                statement.executeUpdate();

            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }

    }

    public static int getLastOrderId()
    {
        // Get the connection
        connection = connectDB();
        int id = 0;
        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("order_id");
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        }
        return id;

    }

    public static void sendReport(String text)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("INSERT INTO report (report_content, customer_id, report_datetime) VALUES (?,?,?)");
            statement.setString(1, text);
            statement.setInt(2, currentUser.getId());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            // Execute the statement
            int res = statement.executeUpdate();


            String emailMessage;
            emailMessage = "Dear Admin, \n\n";
            emailMessage += "A customer has sent a complaint. \n\n";
            emailMessage += "Complaint: " + text + "\n\n";
            emailMessage += "Customer ID: " + currentUser.getId() + "\n\n";
            emailMessage += "Customer Name: " + currentUser.getName() + "\n\n";
            emailMessage += "Customer Email: " + currentUser.getEmail() + "\n\n";


            
            SendEmail.sendEmailWithMessage("ali77dhamen@hotmail.com", emailMessage, "complaint");
            if (res >= 1)
            {
                JOptionPane.showMessageDialog(null, "Your complaint has been submitted. Thank you for your feedback.");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "report not sent", "failed", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
    }

    public static String getStoreNameById(int id)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM store WHERE store_id = ?");
            statement.setInt(1, id);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {

                return rs.getString("store_name");
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
        return null;
    }

    public static void getCustomerOrders()
    {
        // Get the connection
        connection = connectDB();
        CustomerHomeScreen.orders.clear();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM orders WHERE order_customer_id = ?");
            statement.setInt(1, currentUser.getId());

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {

                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setTime(rs.getTimestamp("order_time"));
                order.setStatus(rs.getString("order_status"));
                order.setCost(rs.getDouble("order_cost"));
                order.setAddress(rs.getString("order_address"));
                order.setStore_id(rs.getInt("order_store_id"));
                order.setCustomer_id(rs.getInt("order_customer_id"));
                CustomerHomeScreen.orders.add(order);
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();

        }
    }

    public static String getCustomerNameByID(int id)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM customer WHERE customer_id = ?");
            statement.setInt(1, id);

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {

                return rs.getString("customer_name");
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }
        return null;
    }

    public static void getStoreOrders()
    {
        // Get the connection
        connection = connectDB();
        PartnerHomeScreen.storeOrders.clear();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("SELECT * FROM orders WHERE order_store_id = ?");
            statement.setInt(1, currentStore.getId());

            // Execute the statement
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {

                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setTime(rs.getTimestamp("order_time"));
                order.setStatus(rs.getString("order_status"));
                order.setCost(rs.getDouble("order_cost"));
                order.setAddress(rs.getString("order_address"));
                order.setStore_id(rs.getInt("order_store_id"));
                order.setCustomer_id(rs.getInt("order_customer_id"));
                PartnerHomeScreen.storeOrders.add(order);
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();

        }
    }

    public static void changeOrderStatus(int id, String status)
    {
        // Get the connection
        connection = connectDB();

        // Create the statement
        try
        {

            statement = connection.prepareStatement("UPDATE orders SET order_status = ? WHERE order_id = ?");
            statement.setString(1, status);
            statement.setInt(2, id);

            // Execute the statement
            int res = statement.executeUpdate();
            if (res >= 1)
            {
                JOptionPane.showMessageDialog(null, "Order status changed to " + status +" successfully");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Order status not changed", "failed", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException error", JOptionPane.ERROR_MESSAGE);
        } finally
        {
            closeDB();
        }

    }

}
