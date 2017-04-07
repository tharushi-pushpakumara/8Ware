/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Sales;

import pkg8ware_totalmanagementsystem.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.Date;

public class DBAccess {
     
    static private Connection  connection;

    
    public static Connection getConnection() throws Exception{
        if(connection == null){
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/8ware?zeroDateTimeBehavior=convertToNull","root","");
        }
        return connection;
    } 
        
    
    public static boolean queryexec(String qry){
            //JOptionPane.showMessageDialog(null,"I came here");
            boolean status=false;
            try {
            Connection c =getConnection();
            //JOptionPane.showMessageDialog(null,"now here");
            Statement stmt = c.createStatement();
           // JOptionPane.showMessageDialog(null,"connection ok!");
            stmt.executeUpdate(qry);
            //JOptionPane.showMessageDialog(null,"query executed");
            status=true;
        
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return status;    
            
        }
    
    
     //PETTY_CASH
     
    public boolean addPaymentPetty(String BID,String Dscrptn,String date,String pt,float price,String eid) throws SQLException, Exception{
         
        boolean stts=false;
        String query = "INSERT INTO petty_cash (bill_id,price,date,emp_id,payment_type,description) values ("+"\""+BID+"\""+","+"\""+price+"\""+","+"\""+date+"\""+","+"\""+eid+"\""+","+"\""+pt+"\""+","+"\""+Dscrptn+"\""+")";
        //JOptionPane.showMessageDialog(null, "i'm here");
        stts=queryexec(query);
        return stts;
    }
    
    public boolean updatePaymentPetty(String BID,String Dscrptn,String date,String pt,float Price) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="UPDATE petty_cash SET description = '"+Dscrptn+"', date = '"+date+"', payment_type ='"+pt+"', price ='"+Price+"' WHERE bill_id = '"+BID+"'";
        //JOptionPane.showMessageDialog(null, "i'm here");
        stts=queryexec(query);
        return stts;
    }
     
    public ResultSet fillGridPetty() throws Exception{
       
        String sql ="SELECT * FROM petty_cash";
        Connection c =getConnection();
        //JOptionPane.showMessageDialog(null,"now here");
        Statement stmt = c.createStatement();
        //JOptionPane.showMessageDialog(null,"connection ok!");
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
    
    public ResultSet getDataPetty(String BID) throws SQLException, Exception{
         
        
        String query ="SELECT * FROM petty_cash WHERE bill_id='"+BID+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        //JOptionPane.showMessageDialog(null, "i'm in here");
       
        return res;
    }
    
    public boolean deletePetty(String bid) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="DELETE FROM petty_cash WHERE bill_id = '"+bid+"'";
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        stts=queryexec(query);
        //JOptionPane.showMessageDialog(null, "hey i'm in dlt btn");
        
        return stts;
        
    }
    
    //SALES
    
    public boolean updateSales(String oid,String cid,String in,String date,String pt,float Price) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="UPDATE invoice SET order_id = '"+oid+"', clientIdFkkk = '"+cid+"',date='"+date+"', payment_type ='"+pt+"', grandTotal ='"+Price+"' WHERE invoice_number = '"+in+"'";
        //JOptionPane.showMessageDialog(null, "hey i'm here in updatesale");
        stts=queryexec(query);
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        return stts;
    }
    
    public ResultSet fillGridSales() throws Exception{
       
        String sql ="SELECT invoice_number,order_id,clientIdFkkk,date,payment_type,grandTotal FROM invoice";
        Connection c =getConnection();
        //JOptionPane.showMessageDialog(null,"now here");
        Statement stmt = c.createStatement();
        //JOptionPane.showMessageDialog(null,"connection ok!");
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
     public ResultSet getDataSales(String IN) throws SQLException, Exception{
         
        
        String query ="SELECT * FROM invoice WHERE invoice_number='"+IN+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        //JOptionPane.showMessageDialog(null, "i'm here");
       
        return res;
    }
     
     public boolean deleteSales(String IN) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="delete from invoice where invoice_number='"+IN+"'";
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        stts=queryexec(query);
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        return stts;
    }
     
     //PURCHASINGS
     
    public boolean updatePurchase(String po,String sid,String bn,String date,String pt,float Price,String item) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="UPDATE purchasings SET date = '"+date+"', bill_number = '"+bn+"',supplier_id = '"+sid+"', payment_type ='"+pt+"', price ='"+Price+"',item='"+item+"' WHERE po_number = '"+po+"'";
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        stts=queryexec(query);
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        return stts;
    }
    
    public ResultSet fillGridpurchase() throws Exception{
       
        String sql ="SELECT * FROM purchasings";
        Connection c =getConnection();
        //JOptionPane.showMessageDialog(null,"now here");
        Statement stmt = c.createStatement();
        //JOptionPane.showMessageDialog(null,"connection ok!");
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
     public ResultSet getDataPurchase(String oid) throws SQLException, Exception{
         
        
        String query ="SELECT * FROM purchasings WHERE po_number='"+oid+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        //JOptionPane.showMessageDialog(null, "i'm here");
       
        return res;
    }
     
     public boolean deletePurchase(String oid) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="delete from purchasings where po_number='"+oid+"'";
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        stts=queryexec(query);
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        return stts;
    }
     
     //PAYMENTS
     
     public boolean updatePayments(String bid,String dis,String date,String pt,float Price) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="UPDATE payments SET date = '"+date+"',description = '"+dis+"', payment_type ='"+pt+"', price ='"+Price+"' WHERE billID = '"+bid+"'";
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        stts=queryexec(query);
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        return stts;
    }
    
    public ResultSet fillGridPayments() throws Exception{
       
        String sql ="SELECT * FROM payments";
        Connection c =getConnection();
        //JOptionPane.showMessageDialog(null,"now here");
        Statement stmt = c.createStatement();
        //JOptionPane.showMessageDialog(null,"connection ok!");
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
     public ResultSet getDataPayments(String bid) throws SQLException, Exception{
         
        
        String query ="SELECT * FROM payments WHERE billID='"+bid+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        //JOptionPane.showMessageDialog(null, "i'm here");
       
        return res;
    }
     
     public boolean deletePayments(String bid) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="delete from payments where billID='"+bid+"'";
        ///JOptionPane.showMessageDialog(null, "hey i'm here");
        stts=queryexec(query);
        //JOptionPane.showMessageDialog(null, "hey i'm here");
        return stts;
    }
     
     //SALARY
     
     public ResultSet getDataSalary(String eid,String date) throws SQLException, Exception{
         
        
        String query ="SELECT emp_id,date,net_salary FROM salary WHERE emp_id='"+eid+"' and date = '"+date+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        //JOptionPane.showMessageDialog(null, "i'm here");
       
        return res;
    }
     
     public ResultSet fillGridSalary() throws Exception{
       
        String sql ="SELECT emp_id,net_salary,date FROM salary";
        Connection c =getConnection();
        //JOptionPane.showMessageDialog(null,"now here");
        Statement stmt = c.createStatement();
        //JOptionPane.showMessageDialog(null,"connection ok!");
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
   
    
}
