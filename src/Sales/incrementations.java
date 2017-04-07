/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

//import pkg8ware_totalmanagementsystem.DBAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import pkg8ware_totalmanagementsystem.DBAccess;

/**
 *
 * @author harithaperera
 */
public class incrementations {
    
    private static String nextClientIdStr;
    private static String nextAgreementIdStr;
    private static String nextInvoiceIdStr;
    private static String nextOrderIdStr;
    
    public static void nextClientId() throws Exception{////
         //int currentId = 1000;
        
        try {
            Connection con=DBAccess.getConnection();
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("SELECT COUNT(clientId)FROM Client");
            
            while(rs.next())
            {
                int currentId = Integer.parseInt(rs.getString("count(clientId)"));
                int nextClientId = currentId+1;
                nextClientIdStr = "C".concat(Integer.toString(nextClientId));
            }
        } catch (SQLException | NumberFormatException e) {
        }
        
    }
    
    public static void nextAgreementId() throws Exception{///
        //int currentId = 1000;
        
        try {
            Connection con=DBAccess.getConnection();
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("SELECT COUNT(agreementId)FROM Agreement");
            
            while(rs.next())
            {
                int currentId = Integer.parseInt(rs.getString("count(agreementId)"));
                int nextAgreementId = currentId+1;
                nextAgreementIdStr = "A".concat(Integer.toString(nextAgreementId));
            }
        } catch (SQLException | NumberFormatException e) {
        }
        
    }
    
    public static void nextInvoiceId() throws Exception{
                // int currentId = 1000;

        
        try {
            Connection con=DBAccess.getConnection();
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("SELECT COUNT(invoice_number)FROM invoice");
            
            while(rs.next())
            {
                int currentId = Integer.parseInt(rs.getString("count(invoice_number)"));
                int nextInvoiceId = currentId+1;
                nextInvoiceIdStr = "I".concat(Integer.toString(nextInvoiceId));
            }
        } catch (SQLException | NumberFormatException e) {
        }
        
    }
    
    public static void nextOrderId() throws Exception{
                // int currentId = 1000;

        
        try {
            Connection con=DBAccess.getConnection();
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("SELECT COUNT(order_id)FROM invoice");
            
            while(rs.next())
            {
                int currentId = Integer.parseInt(rs.getString("count(order_id)"));
                int nextOrderId = currentId+1;
                nextOrderIdStr = "O".concat(Integer.toString(nextOrderId));
            }
        } catch (SQLException | NumberFormatException e) {
        }
        
    }

    /**
     * @return the nextClientId
     */
    public static String getNextClientIdStr() {
        return nextClientIdStr;
    }

    /**
     * @return the nextAgreementIdStr
     */
    public static String getNextAgreementIdStr() {
        return nextAgreementIdStr;
    }

    /**
     * @return the nextInvoiceIdStr
     */
    public static String getNextInvoiceIdStr() {
        return nextInvoiceIdStr;
    }

    /**
     * @return the nextOrderIdStr
     */
    public static String getNextOrderIdStr() {
        return nextOrderIdStr;
    }

    
}
