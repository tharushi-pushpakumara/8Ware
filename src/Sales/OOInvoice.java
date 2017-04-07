/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

//import pkg8ware_totalmanagementsystem.DBAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import pkg8ware_totalmanagementsystem.DBAccess;

/**
 *
 * @author harithaperera
 */
public class OOInvoice {
    
    private static String[] ITEMCODE;
    private static String ITEMNAME;
    private static String INVOICENO;
    private static String INVOICEDATE;
    private static String CLIENTIDFK;
    private static String REMARKS;
    private static double DISCOUNT;
    private static double TOTALAMOUNT;
    private static double DUEPAYMENT;
    private static double TOTALPAYMENT;
    private static double GRANDTOTAL;
    private static int[] SALEQTY;
    private static String ORDERID;
    private static String PAYMENTTYPE;

    public static ResultSet searchTableItemCode() throws ClassNotFoundException, Exception
    {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT item_code,type,item_desc,u_price FROM citem WHERE item_code = '"+ITEMCODE+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }
    
    public static ResultSet searchTableItemName() throws ClassNotFoundException, Exception
    {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT item_code,type,item_desc,u_price FROM citem WHERE type = '"+ITEMNAME+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }
    
    public static ResultSet refreshTableInvoice() throws ClassNotFoundException, Exception
    {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT item_code,type,item_desc,u_price FROM citem");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }

    /**
     * @param aITEMCODE the ITEMCODE to set
     */
    public static void setITEMCODE(String[] aITEMCODE) {
        ITEMCODE = aITEMCODE;
    }

    /**
     * @param aITEMNAME the ITEMNAME to set
     */
    public static void setITEMNAME(String aITEMNAME) {
        ITEMNAME = aITEMNAME;
    }

    /**
     * @param aINVOICENO the INVOICENO to set
     */
    public static void setINVOICENO(String aINVOICENO) {
        INVOICENO = aINVOICENO;
    }

    /**
     * @param aINVOICEDATE the INVOICEDATE to set
     */
    public static void setINVOICEDATE(String aINVOICEDATE) {
        INVOICEDATE = aINVOICEDATE;
    }

    /**
     * @param aCLIENTIDFK the CLIENTIDFK to set
     */
    public static void setCLIENTIDFK(String aCLIENTIDFK) {
        CLIENTIDFK = aCLIENTIDFK;
    }

    /**
     * @param aREMARKS the REMARKS to set
     */
    public static void setREMARKS(String aREMARKS) {
        REMARKS = aREMARKS;
    }

    /**
     * @param aDISCOUNT the DISCOUNT to set
     */
    public static void setDISCOUNT(double aDISCOUNT) {
        DISCOUNT = aDISCOUNT;
    }

    /**
     * @param aTOTALAMOUNT the TOTALAMOUNT to set
     */
    public static void setTOTALAMOUNT(double aTOTALAMOUNT) {
        TOTALAMOUNT = aTOTALAMOUNT;
    }

    /**
     * @param aDUEPAYMENT the DUEPAYMENT to set
     */
    public static void setDUEPAYMENT(double aDUEPAYMENT) {
        DUEPAYMENT = aDUEPAYMENT;
    }

    /**
     * @param aTOTALPAYMENT the TOTALPAYMENT to set
     */
    public static void setTOTALPAYMENT(double aTOTALPAYMENT) {
        TOTALPAYMENT = aTOTALPAYMENT;
    }

    /**
     * @param aGRANDTOTAL the GRANDTOTAL to set
     */
    public static void setGRANDTOTAL(double aGRANDTOTAL) {
        GRANDTOTAL = aGRANDTOTAL;
    }

    

    /**
     * @param aSALEQTY the SALEQTY to set
     */
    public static void setSALEQTY(int[] aSALEQTY) {
        SALEQTY = aSALEQTY;
    }
    
    public static boolean insertInvoice(int len) throws ClassNotFoundException, Exception 
    {
        boolean status = false;
        PreparedStatement ps; 
        
        try{
            Connection con = DBAccess.getConnection();
            ps = con.prepareStatement("INSERT INTO Invoice(order_id,invoice_number,date,payment_type,remarks,clientIdFkkk,discount,grandTotal,totalPayment,duePayment,totalAmount) VALUES('"+ORDERID+"','"+INVOICENO+"','"+INVOICEDATE+"','"+PAYMENTTYPE+"','"+REMARKS+"','"+CLIENTIDFK+"',"+DISCOUNT+","+GRANDTOTAL+","+TOTALPAYMENT+","+DUEPAYMENT+","+TOTALAMOUNT+")");
            ps.executeUpdate();
            ps.close();
            
            for(int i=0;i<len;i++){
                ps = con.prepareStatement("INSERT INTO Invoice_Detail(invoiceNoFk,itemCode,saleQty) VALUES('"+INVOICENO+"','"+ITEMCODE[i]+"',"+SALEQTY[i]+")");
                ps.executeUpdate();
                ps.close();
            }
            
            status = true;
        }catch(SQLException ex1){
            System.out.println(ex1.getMessage());
        }
         
        return status;
    }
   
    /*public static boolean updateItem(int saleQty,String itemCode) {
        int rowCount = 0;
        int stockQty = 0;
        
        try{
            Connection con = db_connect.connect();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT stockQty from ITEM where itemCode = '"+itemCode+"'");
            while(rs.next())
            {
               stockQty = rs.getInt("stockQty");
            }
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(OOInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        try{
            Connection con = db_connect.connect();
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("UPDATE ITEM SET stockQty = "+(stockQty-saleQty)+" WHERE itemCode = '"+itemCode+"'");
            rowCount = ps.executeUpdate();
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(OOInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowCount>0;
    }*/

    /**
     * @return the ORDERID
     */
    public static String getORDERID() {
        return ORDERID;
    }

    /**
     * @param aORDERID the ORDERID to set
     */
    public static void setORDERID(String aORDERID) {
        ORDERID = aORDERID;
    }

    /**
     * @return the PAYMENTTYPE
     */
    public static String getPAYMENTTYPE() {
        return PAYMENTTYPE;
    }

    /**
     * @param aPAYMENTTYPE the PAYMENTTYPE to set
     */
    public static void setPAYMENTTYPE(String aPAYMENTTYPE) {
        PAYMENTTYPE = aPAYMENTTYPE;
    }
}
