/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accounts;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author dell notebook
 */
public class validates 
{

    public boolean isNumeric(String str) 
    {

        for (int x = 0; x < str.length(); x++) 
        {
            if (Character.isDigit(str.charAt(x))) 
            {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(String str) 
    {

        return "".equals(str);
    }

    public boolean isFirstLetterCapital(String str) 
    {

        return Character.isUpperCase(str.charAt(0));
    }

    public boolean isOtherLettersCapital(String str) 
    {

        for (int x = 1; x < str.length(); x++) 
        {
            if (Character.isUpperCase(str.charAt(x))) 
            {
                return true;
            }
        }
        return false;
    }

  public  boolean isValidEmailAddress(String email) { //email
           String str = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           Pattern pattern = Pattern.compile(str);
           Matcher matcher = pattern.matcher(email);
           return matcher.matches();
    }

    public boolean isNIC(String str)
    {
        if (str.length() == 10) 
        {
            for (int i = 0; i < 9; i++) 
            {
                if (!Character.isDigit(str.charAt(i))) 
                {
                    JOptionPane.showMessageDialog(null, "Enter numbers as first 9 charachters", "wrong pass", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            if (str.charAt(9) != 'v') 
            {
                JOptionPane.showMessageDialog(null, "Enter  v as final  charachter", "wrong pass", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Enter  10 characters as id ", "wrong pass", JOptionPane.ERROR_MESSAGE);
            return false;

        }
        return true;
    }
  
    public boolean isPhoneNumberLenth(String str) 
    {
        return str.length() <= 10;
    }
    
        public boolean isIDLenth(String str) 
    {
        return str.length() == 5;
    }

    public boolean isLetter(String str) 
    {
        for (int x = 0; x < str.length(); x++) 
        {
            if (Character.isLetter(str.charAt(x))) 
            {
                return true;
            }
        }
        return false;
    }
    
 public static boolean isValidPhone(String phone) { //10 digit number [0-9]
           String str = "\\d{10}";
           Pattern pattern = Pattern.compile(str);
           Matcher matcher = pattern.matcher(phone);
           return matcher.matches();
    }
    //*
    public static boolean isValidClientId(String id) { //any num of numbers[0-9] .is optional
        if(id.startsWith("C"))
        {
            return id.length()==5;
        }else
            return false;
    }
    //*
     public static boolean isValidTecnicianId(String id) { //any num of numbers[0-9] .is optional
        
         if(id.startsWith("T"))
        {
            return id.length()==5;
        }else
            return false;
    }
    //*
    public static boolean isValidAgreementId(String id) { //any num of numbers[0-9] .is optional
        
        if(id.startsWith("A"))
        {
            return id.length()==5;
        }else
            return false;
    }

    //*
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

   //*
//    public static boolean isValidItemCode(String addItemF) {
//        int count = 0;
//        
//        try {
//            Connection c=DB.mycon();
//            java.sql.Statement st = c.createStatement();
//            ResultSet rs = st.executeQuery("SELECT count(item_code) FROM citem WHERE item_code = '"+addItemF+"'");
//            
//            while(rs.next()){
//               count = rs.getInt("count(item_code)");
//            }
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return count==1;
//    }
    
    //*
        public static boolean isValidJobId(String id) { //any num of numbers[0-9] .is optional
        if(id.startsWith("J"))
        {
            return id.length()==5;
        }else
            return false;
    }
 
    
}
