package Fleet;


import java.sql.*;
import javax.swing.*;

public class DBAccess {
    //Connection conn = null;
    static private Connection  connection;
    
        public static Connection getConnection() throws Exception{
        if(connection == null){
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/8ware?zeroDateTimeBehavior=convertToNull","root","");
        }
        return connection;
    }
    
}
