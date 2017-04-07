package CafetariaNTele;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Visal
 */
import java.sql.Connection;
import java.sql.DriverManager;


public class DBAccess {
    
    static private Connection  connection;

    public static Connection getConnection() throws Exception{
        if(connection == null){
            //JDBC  
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull", "root", "");
        }
        return connection;
    }
   
}
