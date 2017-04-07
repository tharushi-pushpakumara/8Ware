/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package  Services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Menaka
 */
public class validation {
    
    
     public static boolean isValidEmailAddress(String email) { //email
           String str = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           Pattern pattern = Pattern.compile(str);
           Matcher matcher = pattern.matcher(email);
           return matcher.matches();
    }

    public static boolean isValidPhone(String phone) { //10 digit number [0-9]
           String str = "\\d{10}";
           Pattern pattern = Pattern.compile(str);
           Matcher matcher = pattern.matcher(phone);
           return matcher.matches();
    }
    
    public static boolean isValidClientId(String id) { //any num of numbers[0-9] .is optional
        if(id.startsWith("C"))
        {
            return id.length()==5;
        }else
            return false;
    }
    
     public static boolean isValidTecnicianId(String id) { //any num of numbers[0-9] .is optional
        
         if(id.startsWith("T"))
        {
            return id.length()==5;
        }else
            return false;
    }
     
     public static boolean isValidJobId(String id) { //any num of numbers[0-9] .is optional
        
         if(id.startsWith("J"))
        {
            return id.length()==5;
        }else
            return false;
    }
     
     
    
    public static boolean isValidAgreementId(String id) { //any num of numbers[0-9] .is optional
        
        if(id.startsWith("A"))
        {
            return id.length()==5;
        }else
            return false;
    }

    
    public static boolean isValidDate(String no) {//mysql type date validatetion
           if(no.matches("^\\d{4}-\\d{2}-\\d{2}$"))
               return false;
           else{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    dateFormat.parse(no);
                    return true;
                }catch(Exception e){
                    return false;
                }
                    
           }
    }

   
    public static boolean isValidItemCode(String addItemF) throws Exception {
        int count = 0;
        
        try {
            Connection con =DBAccess.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(item_code) FROM citem WHERE item_code = '"+addItemF+"'");
            
            while(rs.next()){
               count = rs.getInt("count(item_code)");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count==1;
    }
}
