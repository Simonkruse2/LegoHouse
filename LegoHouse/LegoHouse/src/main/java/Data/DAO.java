
package Data;

import Logic.Order;
import Logic.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {

    public void createUser(String email, String password) {
        try {
            DBConnector c = new DBConnector();
            String query = "insert into users\n"
                    + "values('" + email + "',0,'" + password + "');";

            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeUser(String email) {
        try {
            DBConnector c = new DBConnector();
            String query = "delete from users\n"
                    + "where email = '" + email + "';";

            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkLogin(String email, String password) {
        String _password = "";
        try {
            DBConnector c = new DBConnector();

            String query = "SELECT password FROM `users` WHERE email = '" + email + "';";
            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                _password = rs.getString("password");
            }
            return _password.equals(password);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User getUser(String email) {
        User u = null;
        try {
            DBConnector c = new DBConnector();

            String query = "SELECT * \n"
                    + "FROM users\n"
                    + "WHERE email = '" + email + "';";
            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                boolean admin = rs.getBoolean("admin");

                u = new User(email, admin);

            }
            return u;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getUsers() {
        try {
            DBConnector c = new DBConnector();
            String query = "SELECT * FROM legohouse.users;";
            ArrayList<User> list = new ArrayList<>();
            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String email = rs.getString("email");
                boolean admin = rs.getBoolean("admin");
                list.add(new User(email, admin));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void makeOrder(int width, int length, int height, String bandType, String email) {
        try {
            DBConnector c = new DBConnector();
            String query = "insert into orders\n"
                    + "values(0," + width + "," + length + "," + height + ",'"
                    + bandType + "'" + ",'" + email + "',0);";

            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Order> getMyOrderList(String email) {
        try {
            DBConnector c = new DBConnector();
            String query = "SELECT * \n"
                    + "FROM legohouse.orders\n"
                    + "where email = '" + email + "';";
            ArrayList<Order> list = new ArrayList<>();
            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int orderID = rs.getInt("order_id");
                int width = rs.getInt("width");
                int length = rs.getInt("length");
                int height = rs.getInt("height");
                String bandType = rs.getString("bandType");
                boolean isShipped = rs.getBoolean("isShipped");
                Order order = new Order(width, length, height, bandType, email);
                order.setIsShipped(isShipped);
                order.setOrderID(orderID);
                list.add(order);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Order> getOrderList() {
        try {
            DBConnector c = new DBConnector();
            String query = "SELECT * FROM legohouse.orders;";
            ArrayList<Order> list = new ArrayList<>();
            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int orderID = rs.getInt("order_id");
                int width = rs.getInt("width");
                int length = rs.getInt("length");
                int height = rs.getInt("height");
                String bandType = rs.getString("bandType");
                String email = rs.getString("email");
                boolean isShipped = rs.getBoolean("isShipped");

                Order order = new Order(width, length, height, bandType, email);
                order.setIsShipped(isShipped);
                order.setOrderID(orderID);
                list.add(order);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void shipOrder(int orderID) {
        try {
            DBConnector c = new DBConnector();
            String query = "update orders\n"
                    + "set isShipped = 1\n"
                    + "where order_id = " + orderID + ";";

            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Order getOrder(int orderID) {
        Order order = null;
        try {
            DBConnector c = new DBConnector();
            String query = "SELECT * FROM legohouse.orders\n"
                    + "where order_id = " + orderID + ";";
            Connection connection = c.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int width = rs.getInt("width");
                int length = rs.getInt("length");
                int height = rs.getInt("height");
                String bandType = rs.getString("bandType");
                String email = rs.getString("email");
                order = new Order(width, length, height, bandType, email);
            }
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int[] calcBricks(int width, int length, int height, int bandType) {
        int x4 = 0;
        int x2 = 0;
        int x1 = 0;

        int[] list = new int[6];

        list[0] = x4;
        list[1] = x2;
        list[2] = x1;
        list[3] = x4;
        list[4] = x2;
        list[5] = x1;

        switch (bandType) {
            // Der er kun lavet udregning for den mest simple type, resten kan tilføjes efter lyst.
            case 1:
                if (width < 4) {
                    list[0] = 0;
                }
                if (width % 4 == 0) {
                    list[0] = (width / 4) * 2 * height;
                }
                if (width > 4 && width % 4 != 0) {
                    list[0] = ((width - (width % 4)) / 4) * 2 * height;
                }
                if (width % 4 == 2) {
                    list[1] = 1 * 2 * height;
                }
                if (width % 4 == 1) {
                    list[2] = 1 * 2 * height;
                }
                if (width % 4 > 2) {
                    list[1] = 1 * 2 * height;
                    list[2] = 1 * 2 * height;
                }
                if (length < 4) {
                    list[3] = 0;
                }
                if (length % 4 == 0) {
                    list[3] = (length / 4) * 2 * height;
                }
                if (length > 4 && length % 4 != 0) {
                    list[3] = ((length - (length % 4)) / 4) * 2 * height;
                }
                if (length % 4 == 2) {
                    list[4] = 1 * 2 * height;
                }
                if (length % 4 == 1) {
                    list[5] = 1 * 2 * height;
                }
                if (length % 4 > 2) {
                    list[4] = 1 * 2 * height;
                    list[5] = 1 * 2 * height;
                }
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                throw new AssertionError();
        }

        return list;
    }
}
