/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

//import Sales.DBAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
//import Sales.DBAccess;

/**
 *
 * @author harithaperera
 */
public class OOAgreement {
    
    private static String CLIENTIDFK;
    private static String AGREEMENTID;
    private static String TYPE;
    private static String[] ASESTS;
    private static String FROM;
    private static String TO;

    /**
     * @param aCLIENTID the CLIENTID to set
     */
    public static void setCLIENTID(String aCLIENTID) {
        CLIENTIDFK = aCLIENTID;
    }

    /**
     * @param aAGREEMENTID the AGREEMENTID to set
     */
    public static void setAGREEMENTID(String aAGREEMENTID) {
        AGREEMENTID = aAGREEMENTID;
    }

    /**
     * @param aTYPE the TYPE to set
     */
    public static void setTYPE(String aTYPE) {
        TYPE = aTYPE;
    }

    /**
     * @param aASESTS the ASESTS to set
     */
    public static void setASESTS(String[] aASESTS) {
        ASESTS = aASESTS;
    }

    /**
     * @param aFROM the FROM to set
     */
    public static void setFROM(String aFROM) {
        FROM = aFROM;
    }

    /**
     * @param aTO the TO to set
     */
    public static void setTO(String aTO) {
        TO = aTO;
    }
    
    public static boolean insert(int len) throws ClassNotFoundException, Exception ///
    {
        PreparedStatement ps = null;
        boolean status = false;
        
        try{
            Connection con = DBAccess.getConnection();
            ps = con.prepareStatement("INSERT INTO Agreement(agreementId,type,lastRenewDate,expiryDate,clientIdFKk) VALUES('"+AGREEMENTID+"','"+TYPE+"','"+FROM+"','"+TO+"','"+CLIENTIDFK+"')");
            ps.executeUpdate();
            ps.close();
            
            for(int i=0;i<len;i++){
                ps = con.prepareStatement("INSERT INTO Agreement_Assest(assest,agreementIdFk) VALUES('"+ASESTS[i]+"','"+AGREEMENTID+"')");
                ps.executeUpdate();
                ps.close();
            }
            
            status = true;
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
        
        return status;
    }
    
    public static boolean update(int len) throws ClassNotFoundException, Exception////cannot add new assests(use if)
    {
        PreparedStatement ps = null;
        boolean status = false;
        
        try{
            Connection con = DBAccess.getConnection();
            ps = con.prepareStatement("UPDATE Agreement SET type = '"+TYPE+"', lastRenewDate = '"+FROM+"',expiryDate = '"+TO+"' , clientIdFKk = '"+CLIENTIDFK+"'  WHERE agreementId = '"+AGREEMENTID+"'");
            ps.executeUpdate();
            ps.close();
            
            for(int i=0;i<len;i++){
                ps = con.prepareStatement("UPDATE Agreement_Assest SET assest = '"+ASESTS[i]+"'  WHERE agreementIdFk = '"+AGREEMENTID+"'");
                ps.executeUpdate();
                ps.close();
            }
            
            status = true;
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
        
        return status;  
    }
    
    public static boolean delete() throws ClassNotFoundException, Exception
    {
        int rowCount = 0;//returning row count got from exec uppdate method
        
        try{
            Connection con = DBAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Agreement WHERE agreementId = '"+AGREEMENTID+"'"); 
            rowCount = ps.executeUpdate();
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
        
        return rowCount>0;
    }
    
    public static ResultSet dispalyTableExpiary() throws ClassNotFoundException, Exception/////
    {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-d");
        
        ResultSet rs = null;
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT Agreement.agreementId,Agreement.`type`,Agreement.lastRenewDate,Agreement.expiryDate,Agreement.clientIdFKk,Agreement_Assest.assest FROM Agreement,Agreement_Assest WHERE expiryDate <= '"+date.format(new Date())+"' and Agreement.agreementId = Agreement_Assest.agreementIdFk");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }
}
