/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HRM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell notebook
 */
public class DBAccess {
    
    public static Connection getConnection() throws ClassNotFoundException,SQLException
    {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/8ware", "root", "");
        return c;
    }

}
