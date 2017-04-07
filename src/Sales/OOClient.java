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
import javax.swing.JOptionPane;
//import Sales.DBAccess;

/**
 *
 * @author harithaperera
 */
public class OOClient {
    
    private static String CLIENTID;
    private static String NAME;
    private static String ADDRESS;
    private static String MOBILE;
    private static String TELEPHONE;
    private static String FAX;
    private static String EMAIL;
    private static String CNAME;
    private static String CMOBILE;
    private static String CEMAIL;
    private static String COID;

    /**
     * @param aCLIENTID the CLIENTID to set
     */
    public static void setCLIENTID(String aCLIENTID) {
        CLIENTID = aCLIENTID;
    }

    /**
     * @param aNAME the NAME to set
     */
    public static void setNAME(String aNAME) {
        NAME = aNAME;
    }

    /**
     * @param aADDRESS the ADDRESS to set
     */
    public static void setADDRESS(String aADDRESS) {
        ADDRESS = aADDRESS;
    }

    /**
     * @param aMOBILE the MOBILE to set
     */
    public static void setMOBILE(String aMOBILE) {
        MOBILE = aMOBILE;
    }

    /**
     * @param aTELEPHONE the TELEPHONE to set
     */
    public static void setTELEPHONE(String aTELEPHONE) {
        TELEPHONE = aTELEPHONE;
    }

    /**
     * @param aFAX the FAX to set
     */
    public static void setFAX(String aFAX) {
        FAX = aFAX;
    }

    /**
     * @param aEMAIL the EMAIL to set
     */
    public static void setEMAIL(String aEMAIL) {
        EMAIL = aEMAIL;
    }

    
    /**
     * @param aCNAME the CNAME to set
     */
    public static void setCNAME(String aCNAME) {
        CNAME = aCNAME;
    }

    /**
     * @param aCMOBILE the CMOBILE to set
     */
    public static void setCMOBILE(String aCMOBILE) {
        CMOBILE = aCMOBILE;
    }

    /**
     * @param aCEMAIL the CEMAIL to set
     */
    public static void setCEMAIL(String aCEMAIL) {
        CEMAIL = aCEMAIL;
    }
               
    
    public static boolean insert() throws ClassNotFoundException, Exception /////
    {
        int rowCount[] = null;//returning row count got from exec uppdate method
        
        String [] queries = {"INSERT INTO Cordinator(coId,coName,coMobile,coEmail) VALUES('"+getCOID()+"','"+getCNAME()+"','"+getCMOBILE()+"','"+getCEMAIL()+"')",
                            "INSERT INTO Client(clientId,name,address,mobile,telephone,fax,email) VALUES('"+getCLIENTID()+"','"+getNAME()+"','"+getADDRESS()+"','"+getMOBILE()+"','"+getTELEPHONE()+"','"+getFAX()+"','"+getEMAIL()+"')",
                            "INSERT INTO Client_Cordi(clientIdFk,coIdFk) VALUES('"+getCLIENTID()+"','"+getCOID()+"')"};
         
        try{
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            
            for(String query : queries){
                st.addBatch(query);
            }
            
            rowCount = st.executeBatch();
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
            JOptionPane.showMessageDialog(null, "Error: Hit reset and try again..");
        }
        
        return rowCount[0]>0 && rowCount[1]>0 && rowCount[2]>0;
        
    }
    
    public static boolean delete() throws ClassNotFoundException, Exception
    {
        int rowCount[] = null;//returning row count got from exec uppdate method        int rowCount = 0;//returning row count got from exec uppdate method
        
        String [] queries = {"DELETE FROM Client WHERE clientId = '"+getCLIENTID()+"'"};//use cascading on delete
        
        try{
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            
            for(String query : queries){
                st.addBatch(query);
            }
            
        rowCount = st.executeBatch();

             

        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
            
        return rowCount[0]>0;

    }
    
    public static boolean update() throws ClassNotFoundException, Exception/////
    {
        int rowCount[] = null;//returning row count got from exec uppdate method
        
        String [] queries = {"UPDATE Client SET name = '"+getNAME()+"', address = '"+getADDRESS()+"', mobile = '"+getMOBILE()+"',telephone = '"+getTELEPHONE()+"' , fax = '"+getFAX()+"' , email = '"+getEMAIL()+"' WHERE clientId = '"+getCLIENTID()+"'",
                             "UPDATE Cordinator SET coName = '"+getCNAME()+"' , coMobile = '"+getCMOBILE()+"' , coEmail = '"+getCEMAIL()+"' WHERE coId = '"+COID+"'",
                             "UPDATE Client_Cordi SET clientIdFk = '"+getCLIENTID()+"' , coIdFk = '"+COID+"'  WHERE clientIdFk = '"+getCLIENTID()+"' and coIdFk = '"+COID+"' "};
        
        //UPDATE dbmol.client_cordi SET `clientIdFk` = 'sdfClientTest', `coIdFk` = 545 WHERE clientIdFk = 'oldvalue' AND coIdFk = oldvalue;

        
        try{
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            
            for(String query : queries){
                st.addBatch(query);
            }
            
        rowCount = st.executeBatch();

        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
            
        return rowCount[0]>0 && rowCount[1]>0 &&rowCount[2]>0;
    }
    
    public static void searchClientById() throws ClassNotFoundException, Exception/////
    {
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM Client WHERE clientId ='"+getCLIENTID()+"'");
            ResultSet rs1=st1.executeQuery("SELECT * FROM Cordinator WHERE clientIdFk ='"+getCLIENTID()+"'");
            
            while(rs.next())
            {
                NAME = rs.getString("name");
                ADDRESS = rs.getString("address");
                MOBILE = rs.getString("mobile");
                TELEPHONE = rs.getString("telephone");
                FAX = rs.getString("fax");
                EMAIL = rs.getString("email");
            }
            
            while(rs1.next())
            {
                CNAME = rs1.getString("coName");
                CMOBILE = rs1.getString("coMobile");//usee getters and send these to each text 
                CEMAIL = rs1.getString("coEmail");
            }
            
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
    }
    
    
    
    public static ResultSet refreshTableClient() throws ClassNotFoundException, Exception////aDD agreement ID
    {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT distinct Client.clientId,Client.`name`,Client.address,Client.mobile,Client.telephone,Client.fax,Client.email,Cordinator.coName,Cordinator.coMobile,Cordinator.coEmail \n" +
"FROM Cordinator,Client,Client_Cordi\n" +
"where Cordinator.coId = Client_Cordi.coIdFk and Client.clientId = Client_Cordi.clientIdFk");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }
    
    public static ResultSet searchTableClientName() throws ClassNotFoundException, Exception////
    {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Client WHERE name = '"+NAME+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }
    
    public static ResultSet searchTableClientId() throws ClassNotFoundException, Exception////
    {
        ResultSet rs = null;
        
        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Client WHERE clientId = '"+CLIENTID+"'");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }

    /**
     * @return the CLIENTID
     */
    public static String getCLIENTID() {
        return CLIENTID;
    }

    /**
     * @return the NAME
     */
    public static String getNAME() {
        return NAME;
    }

    /**
     * @return the ADDRESS
     */
    public static String getADDRESS() {
        return ADDRESS;
    }

    /**
     * @return the MOBILE
     */
    public static String getMOBILE() {
        return MOBILE;
    }

    /**
     * @return the TELEPHONE
     */
    public static String getTELEPHONE() {
        return TELEPHONE;
    }

    /**
     * @return the FAX
     */
    public static String getFAX() {
        return FAX;
    }

    /**
     * @return the EMAIL
     */
    public static String getEMAIL() {
        return EMAIL;
    }

    /**
     * @return the CNAME
     */
    public static String getCNAME() {
        return CNAME;
    }

    /**
     * @return the CMOBILE
     */
    public static String getCMOBILE() {
        return CMOBILE;
    }

    /**
     * @return the CEMAIL
     */
    public static String getCEMAIL() {
        return CEMAIL;
    }

    /**
     * @return the COID
     */
    public static String getCOID() {
        return COID;
    }

    /**
     * @param aCOID the COID to set
     */
    public static void setCOID(String aCOID) {
        COID = aCOID;
    }

    

    
}
