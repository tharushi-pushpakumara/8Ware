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
import java.util.logging.Level;
import java.util.logging.Logger;
//import pkg8ware_totalmanagementsystem.DBAccess;

/**
 *
 * @author harithaperera
 */
public class OOSalesPerformance {
    
    private static String CLIENTNAME;
    private static String[] INVOICENO;
    private static String FROM;
    private static String TO;
    public static String[] TYPE;
    private static String ITEMNAME;
    private static String CLIENTCODE;
    private static String ITEMCODE;
    private static double TOTALAMOUNT;
    private static double DUEPAYMENT;
    private static double TOTALPAYMENT;
    public static int[] SaleQTY;

    /**
     * @return the CLIENTNAME
     */
    public static String getCLIENTNAME() {
        return CLIENTNAME;
    }

    /**
     * @param aCLIENTNAME the CLIENTNAME to set
     */
    public static void setCLIENTNAME(String aCLIENTNAME) {
        CLIENTNAME = aCLIENTNAME;
    }

    /**
     * @return the INVOICENO
     */
    public static String[] getINVOICENO() {
        return INVOICENO;
    }

    /**
     * @param aINVOICENO the INVOICENO to set
     */
    public static void setINVOICENO(String[] aINVOICENO) {
        INVOICENO = aINVOICENO;
    }

    /**
     * @return the FROM
     */
    public static String getFROM() {
        return FROM;
    }

    /**
     * @param aFROM the FROM to set
     */
    public static void setFROM(String aFROM) {
        FROM = aFROM;
    }

    /**
     * @return the TO
     */
    public static String getTO() {
        return TO;
    }

    /**
     * @param aTO the TO to set
     */
    public static void setTO(String aTO) {
        TO = aTO;
    }

    /**
     * @return the ITEMNAME
     */
    public static String getITEMNAME() {
        return ITEMNAME;
    }

    /**
     * @param aITEMNAME the ITEMNAME to set
     */
    public static void setITEMNAME(String aITEMNAME) {
        ITEMNAME = aITEMNAME;
    }

    /**
     * @return the CLIENTCODE
     */
    public static String getCLIENTCODE() {
        return CLIENTCODE;
    }

    /**
     * @param aCLIENTCODE the CLIENTCODE to set
     */
    public static void setCLIENTCODE(String aCLIENTCODE) {
        CLIENTCODE = aCLIENTCODE;
    }

    /**
     * @return the ITEMCODE
     */
    public static String getITEMCODE() {
        return ITEMCODE;
    }

    /**
     * @param aITEMCODE the ITEMCODE to set
     */
    public static void setITEMCODE(String aITEMCODE) {
        ITEMCODE = aITEMCODE;
    }

    /**
     * @return the TOTALAMOUNT
     */
    public static double getTOTALAMOUNT() {
        return TOTALAMOUNT;
    }

    /**
     * @param aTOTALAMOUNT the TOTALAMOUNT to set
     */
    public static void setTOTALAMOUNT(double aTOTALAMOUNT) {
        TOTALAMOUNT = aTOTALAMOUNT;
    }

    /**
     * @return the DUEPAYMENT
     */
    public static double getDUEPAYMENT() {
        return DUEPAYMENT;
    }

    /**
     * @param aDUEPAYMENT the DUEPAYMENT to set
     */
    public static void setDUEPAYMENT(double aDUEPAYMENT) {
        DUEPAYMENT = aDUEPAYMENT;
    }

    /**
     * @return the TOTALPAYMENT
     */
    public static double getTOTALPAYMENT() {
        return TOTALPAYMENT;
    }

    /**
     * @param aTOTALPAYMENT the TOTALPAYMENT to set
     */
    public static void setTOTALPAYMENT(double aTOTALPAYMENT) {
        TOTALPAYMENT = aTOTALPAYMENT;
    }

    public static ResultSet searchSalesPerYear(String from,String to) throws ClassNotFoundException, Exception  {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("select distinct invoice.order_id,invoice.invoice_number,invoice.clientIdFkkk,invoice.payment_type,DATE_FORMAT(Invoice.`date`,'%Y')as 'Year',invoice.discount,invoice.grandTotal,invoice.totalPayment,invoice.duePayment \n" +
"from invoice,Client \n" +
"where invoice.clientIdFkkk = Client.clientId and invoice.`date` >= '"+from+"' AND invoice.`date` <= '"+to+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }

    public static ResultSet searchSalesPerMonth(String from, String to) throws Exception {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("select distinct invoice.order_id,invoice.invoice_number,invoice.clientIdFkkk,invoice.payment_type,DATE_FORMAT(Invoice.`date`,'%Y-%m')as 'Year',invoice.discount,invoice.grandTotal,invoice.totalPayment,invoice.duePayment \n" +
"from invoice,Client \n" +
"where invoice.clientIdFkkk = Client.clientId and invoice.`date` >= '"+from+"' AND invoice.`date` <= '"+to+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;    
    }

    public static ResultSet searchSalesPerDay(String from, String to) throws Exception {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("select distinct invoice.order_id,invoice.invoice_number,invoice.clientIdFkkk,invoice.payment_type,DATE_FORMAT(Invoice.`date`,'%Y-%m-%d')as 'Year',invoice.discount,invoice.grandTotal,invoice.totalPayment,invoice.duePayment \n" +
"from invoice,Client \n" +
"where invoice.clientIdFkkk = Client.clientId and invoice.`date` >= '"+from+"' AND invoice.`date` <= '"+to+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;   
    }

    public static ResultSet searchSalesByClientId(String cId) throws Exception {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT distinct Invoice.invoice_number,Invoice.order_id,DATE_FORMAT(Invoice.`date`,'%Y/%m/%d') as 'invoice_Date',Client.clientId,Client.`name`,Invoice.grandTotal,Invoice.totalPayment,Invoice.duePayment \n" +
"FROM Client,Invoice \n" +
"where Client.clientId = Invoice.clientIdFkkk and Client.clientId = '"+cId+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;    
    }
    
    
    public static ResultSet searchSalesByClientName(String cName) throws Exception {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT distinct Invoice.invoice_number,Invoice.order_id,DATE_FORMAT(Invoice.`date`,'%Y/%m/%d') as 'invoice_Date',Client.clientId,Client.`name`,Invoice.grandTotal,Invoice.totalPayment,Invoice.duePayment \n" +
"FROM Client,Invoice \n" +
"where Client.clientId = Invoice.clientIdFkkk and Client.`name` = '"+cName+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;    
    }

    public static ResultSet searchTableItemCode(String itemCode) throws Exception {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("Select distinct citem.item_code,citem.`type`,citem.model,citem.u_price,(Sum(Invoice_Detail.saleQty)*citem.u_price) as 'Total Payment'\n" +
"from citem,Invoice_Detail\n" +
"where Invoice_Detail.itemCode = citem.item_code and citem.item_code = '"+itemCode+"' \n" +
"group by citem.item_code,citem.u_price,citem.`type`,citem.model");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;   
    }

    public static ResultSet searchTableItemName(String itemname) throws Exception {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("Select distinct citem.item_code,citem.`type`,citem.model,citem.u_price,(Sum(Invoice_Detail.saleQty)*citem.u_price) as 'Total Payment'\n" +
"from citem,Invoice_Detail\n" +
"where Invoice_Detail.itemCode = citem.item_code and citem.`type` = '"+itemname+"' \n" +
"group by citem.item_code,citem.u_price,citem.`type`,citem.model");
            System.out.println("sdfsdfsdf");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;  
    }

    public static ResultSet searchSalesByInvoiceNoItemCode(String[] invoiceno, String itemcode) throws Exception {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("Select distinct citem.item_code,citem.`type`,citem.model,citem.u_price,(Sum(Invoice_Detail.saleQty)*citem.u_price) as 'Total Payment' from citem,Invoice_Detail where Invoice_Detail.itemCode = citem.item_code and citem.item_code = '"+itemcode+"' and Invoice_Detail.invoiceNoFk = '"+invoiceno[0]+"' group by citem.item_code,citem.u_price,citem.`type`,citem.model");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs; 
    }


    /**
     * @return the TYPE
     */
    public static String[] getTYPE() {
        return TYPE;
    }

    /**
     * @param aTYPE the TYPE to set
     */
    public static void setTYPE(String[] aTYPE) {
        TYPE = aTYPE;
    }

    /**
     * @return the SaleQTY
     */
    public static int[] getSaleQTY() {
        return SaleQTY;
    }

    /**
     * @param aSaleQTY the SaleQTY to set
     */
    public static void setSaleQTY(int[] aSaleQTY) {
        SaleQTY = aSaleQTY;
    }
    
}
