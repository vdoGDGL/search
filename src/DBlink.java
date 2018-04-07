import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBlink {
    public final static String name = "name";
    public final static String ID = "ID";
    public final static String price = "price";

    public static Connection getConnection(){
        String url = "jdbc:mysql://39.106.205.176:3306/value?useSSL=false";
        Connection connector = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connector = (Connection) DriverManager.getConnection(url, "root", "wuke");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        }
        return connector;
    }
}
