/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import static accounts.DBAccess.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Tharushi
 */
public class queries {
    
    
    
    public static boolean queryexec(String qry){
            
        boolean status=false;
            try {
            Connection c =getConnection();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(qry);
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
        stts=queryexec(query);
        return stts;
    
    }
    
    public boolean updatePaymentPetty(String BID,String Dscrptn,String date,String pt,float Price) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="UPDATE petty_cash SET description = '"+Dscrptn+"', date = '"+date+"', payment_type ='"+pt+"', price ='"+Price+"' WHERE bill_id = '"+BID+"'";
        stts=queryexec(query);
        return stts;
    
    }
     
    public ResultSet fillGridPetty() throws Exception{
       
        String sql ="SELECT * FROM petty_cash";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    
    }
    
    
    public ResultSet getDataPetty(String BID) throws SQLException, Exception{
         
        String query ="SELECT * FROM petty_cash WHERE bill_id='"+BID+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
    
    }
    
    public boolean deletePetty(String bid) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="DELETE FROM petty_cash WHERE bill_id = '"+bid+"'";
        stts=queryexec(query);
        return stts;
        
    }
    
    public ResultSet getEid() throws Exception{
        
        String query ="SELECT emp_id FROM employee";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
    
    }
    
    //MONTHLY PAYMENTS
    
    public boolean addMontlyPayment(String BID,String Dscrptn,String date,String pt,float price) throws SQLException, Exception{
         
        boolean stts=false;
        String query = "INSERT INTO payments (billID,price,date,payment_type,description) values ("+"\""+BID+"\""+","+"\""+price+"\""+","+"\""+date+"\""+","+"\""+pt+"\""+","+"\""+Dscrptn+"\""+")";
        stts=queryexec(query);
        return stts;
    
    }
    
    public boolean updateMonthlyPayment(String BID,String Dscrptn,String date,String pt,float Price) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="UPDATE payments SET description = '"+Dscrptn+"', date = '"+date+"', payment_type ='"+pt+"', price ='"+Price+"' WHERE billID = '"+BID+"'";
        stts=queryexec(query);
        return stts;
    
    }
     
    public ResultSet fillGridmonthly() throws Exception{
       
        String sql ="SELECT billID,price,date,payment_type,description FROM payments where description IN ('Electricity Bill','water Bill','LandPhone Bill','Moblie Bill','Internet Bill','Monthly Rental','Tax')";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    
    }
    
    
    public ResultSet getDataMonthly(String BID) throws SQLException, Exception{
         
        String query ="SELECT billID,price,date,payment_type,description FROM payments WHERE billID='"+BID+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
    
    }
    
    public boolean deletemonthly(String bid) throws SQLException, Exception{
         
        boolean stts=false;
        String query ="DELETE FROM payments WHERE billID = '"+bid+"'";
        stts=queryexec(query);
        return stts;
        
    }
    
    
    //SALES
    
    public ResultSet fillGridSales() throws Exception{
       
        String sql ="SELECT invoice_number,order_id,clientIdFkkk,date,payment_type,grandTotal FROM invoice";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
        
    }
    
     public ResultSet getDataSales(String IN) throws SQLException, Exception{
         
        String query ="SELECT * FROM invoice WHERE invoice_number='"+IN+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
        
    }
    
    public ResultSet getin() throws Exception{
        
        String query ="SELECT invoice_number FROM invoice";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
        
    }
     
    
     
     //PURCHASINGS
     
    
    
    public ResultSet fillGridpurchase() throws Exception{
       
        String sql ="SELECT * FROM purchasings";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    
    }
    
     public ResultSet getDataPurchase(String oid) throws SQLException, Exception{
         
        String query ="SELECT * FROM purchasings WHERE po_number='"+oid+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
       return res;
    
     }
     
     public ResultSet getpo() throws Exception{
        
         String query ="select po_number from purchasings";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
    
     }
     
     
     //PAYMENTS
     
    public ResultSet fillGridPayments() throws Exception{
       
        String sql ="SELECT billID,date,price,description,payment_type FROM payments";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    
    }
    
     public ResultSet getDataPayments(String bid) throws SQLException, Exception{
         
       String query ="SELECT * FROM payments WHERE billID='"+bid+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
    
     }
     
    
     
     public ResultSet getbid() throws Exception{
        
        String query ="select billID from payments";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
    
     }
     
     
     //SALARY
     
     public ResultSet getDataSalary(String SID) throws SQLException, Exception{
         
        String query ="SELECT SID,emp_id,date,net_salary FROM salary WHERE SID='"+SID+"'";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet res= stmt.executeQuery(query);
        return res;
    
     }
     
     public ResultSet fillGridSalary() throws Exception{
       
        String sql ="SELECT SID,emp_id,net_salary,date FROM salary";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    
     }
     
     
     public ResultSet getsid() throws Exception {

        String query = "select SID from salary";
        Connection c = getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(query);
        return res;

    }
     
     public static ResultSet getdata(String sql)throws Exception{
        
         Statement s=getConnection().createStatement();
        ResultSet rs= s.executeQuery(sql);
        return rs;
    
     }
     
     
     
     //EXPENDITURE
     
    public boolean addtoexpenditure(String bid,String date,String des,float price) throws SQLException, Exception{
        
        
        boolean stts=false;
        String query = "INSERT INTO accounts_exp (billID,Date,Description,Amount) "
                + "values ("+"\""+bid+"\""+","+"\""+date+"\""+","+"\""+des+"\""+","+"\""+price+"\""+")";
        stts=queryexec(query);
        return stts;
    
    }
     
     //CURRENT DETAILS CASH FOR THE CURRENT MONTH
    
     public ResultSet pettycash() throws Exception{
        
        String sql ="select sum(price) as expenditure from petty_cash where payment_type = 'Cash' and "
                + "month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    public ResultSet payments() throws Exception{
        
        String sql ="select sum(price) as expenditure from payments where payment_type = 'Cash' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    public ResultSet salary() throws Exception{
        
        String sql ="select sum(net_salary) as expenditure from salary where month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    public ResultSet purchase() throws Exception{
        
        String sql ="select sum(price) as expenditure from purchasings where payment_type = 'Cash' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    public ResultSet sales() throws Exception{
        
        String sql ="select sum(grandTotal) as expenditure from invoice where payment_type = 'Cash' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    //CURRENT DETAILS BANKED FOR THE CURRENT MONTH
     
     public ResultSet pettycashB() throws Exception{
        
        String sql ="select sum(price) as expenditure from petty_cash where payment_type = "
                + "'Cheque' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    public ResultSet paymentsB() throws Exception{
        
        String sql ="select sum(price) as expenditure from payments where payment_type = 'Cheque' and "
                + "month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
   
    public ResultSet purchaseB() throws Exception{
        
        String sql ="select sum(price) as expenditure from purchasings where payment_type = 'Cheque' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    public ResultSet salesB() throws Exception{
        
        String sql ="select sum(grandTotal) as expenditure from invoice where payment_type = 'Cheque' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE())";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    //CURRENT DETAILS CASH FOR THE LAST MONTH
    
     public ResultSet lpettycash() throws Exception{
        
        String sql ="select sum(price) as expenditure from petty_cash where payment_type = 'Cash' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    public ResultSet lpayments() throws Exception{
        
        String sql ="select sum(price) as expenditure from payments where payment_type = 'Cash' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    public ResultSet lsalary() throws Exception{
        
        String sql ="select sum(net_salary) as expenditure from salary where month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    public ResultSet lpurchase() throws Exception{
        
        String sql ="select sum(price) as expenditure from purchasings where payment_type = 'Cash' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    public ResultSet lsales() throws Exception{
        
        String sql ="select sum(grandTotal) as expenditure from invoice where payment_type = 'Cash' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    //CURRENT DETAILS BANKED FOR THE LAST MONTH
     
     public ResultSet lpettycashB() throws Exception{
        
        String sql ="select sum(price) as expenditure from petty_cash where payment_type = 'Cheque' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    public ResultSet lpaymentsB() throws Exception{
        
        String sql ="select sum(price) as expenditure from payments where payment_type = 'Cheque' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
   
    public ResultSet lpurchaseB() throws Exception{
        
        String sql ="select sum(price) as expenditure from purchasings where payment_type = 'Cheque' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
    
    public ResultSet lsalesB() throws Exception{
        
        String sql ="select sum(grandTotal) as expenditure from invoice where payment_type = 'Cheque' and month(STR_TO_DATE(date, '%d/%m/%Y'))= month(CURRENT_DATE() - INTERVAL 1 MONTH)";
        Connection c =getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
     
    }
    
   
    
}

    

