package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnector {

    private Connection connection = null;

    //Constants
    private static final String IP = "178.62.232.91";
    private static final String PORT = "3306";
    public static final String DATABASE = "legohouse";
    private static final String USERNAME = "testuser2";
    private static final String PASSWORD = "password123";

    public DBConnector() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
        Properties props = new Properties();
        props.put("user", USERNAME);
        props.put("password", PASSWORD);
        props.put("allowMultiQueries", true);
        props.put("useUnicode", true);
        props.put("useJDBCCompliantTimezoneShift", true);
        props.put("useLegacyDatetimeCode", false);
        props.put("serverTimezone", "CET");
        this.connection = DriverManager.getConnection(url, props);
    }

    public Connection getConnection() {
        return this.connection;
    }
}