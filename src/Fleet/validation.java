package Fleet;


//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import javax.swing.JOptionPane;



public class validation {

    
    public static boolean isVehicleName(String str){
        if(str.startsWith("V")){
            if(str.length()==5 )
                return true;
            else
                return false;
        }else
            return false;
    } 
    
    public static boolean isBillID(String str){
        if(str.startsWith("BID")){
             return true;
        }else
            return false;
    }
    
    public static boolean isChassicName(String str){
            if( 10 < str.length() && str.length() < 18)
                return true;
            else
                return false;
    } 
    
  public static boolean isEmphtyCheck(String str){
            if(str.length()!=0)
                return true;
            else
                return false;
    } 
  
   public static boolean isEmphtyCheckDate(String str){
            if(str.length()!=0)
                return true;
            else
                return false;
    } 
  
  

}
