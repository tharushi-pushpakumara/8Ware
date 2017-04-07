/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

//import functions.db_connect;
import com.mysql.jdbc.StringUtils;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import pkg8ware_totalmanagementsystem.DBAccess;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

/**
 *
 * @author harithaperera
 */
public class Validate {

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
    
    public static boolean isValidYear(String yr) { //10 digit number [0-9]
        String str = "^(19|20)\\d{2}$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(yr);
        return matcher.matches();
    }

    public static boolean isValidClientId(String id) { //any num of numbers[0-9] .is optional
        if (id.startsWith("C")) {
            return id.length() <= 5 && id.length() >= 2;
        } else {
            return false;
        }
    }

    public static boolean isValidInvoiceId(String id) { //any num of numbers[0-9] .is optional
        if (id.startsWith("I")) {
            return id.length() <= 5 && id.length() >= 2;
        } else {
            return false;
        }
    }

    public static boolean isValidOrderId(String id) { //any num of numbers[0-9] .is optional
        if (id.startsWith("O")) {
            return id.length() <= 5 && id.length() >= 2;
        } else {
            return false;
        }
    }

    public static boolean isValidAgreementId(String id) { //any num of numbers[0-9] .is optional
        if (id.startsWith("A")) {
            return id.length() <= 5 && id.length() >= 2;
        } else {
            return false;
        }
    }

    public static boolean isValidSaleAmount(int amount) { //any num of numbers[0-9] .is optional
        try {
            if (amount > 0) {
                if (String.valueOf(amount).matches("[a-zA-Z]+")) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidPrice(String amount) { //any num of numbers[0-9] .is optional

        String str = "[0-9]+(?:\\.[0-9]+)?";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(amount);
        return matcher.matches();
    }

    public static boolean isValidDiscount(String amount) {
        try {
            return Integer.parseInt(amount) <= 100 && Integer.parseInt(amount) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidGenaralTxtFNO(String no) {//only allow numbers
        String str = "[0-9]";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(no);
        //return matcher.matches();
        return true;

    }

    public static boolean isValidGenaralTxtFChar(String ch) {//only allow numbers
        String str = "[0-9]";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(ch);
        //return matcher.matches();
        return true;

    }

    public static boolean isValidCurrentDate(String no) throws ParseException {//mysql type date validatetion++ DONOT CONTAIN PAST DATES
        if (no.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            return false;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateFormat.parse(no);
                if (dateFormat.parse(no).before(new Date())) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }

        }
    }

    public static boolean isValidCordinatorId(String id) {
        if (id.startsWith("E")) {
            return id.length() <= 5 && id.length() >= 2;
        } else {
            return false;
        }
    }

    public static boolean isValidItemCode(String addItemF) throws Exception {
        int count = 0;

        try {
            Connection con = DBAccess.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(item_code) FROM citem WHERE item_code = '" + addItemF + "'");

            while (rs.next()) {
                count = rs.getInt("count(item_code)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count == 1;
    }

    /*public static boolean isValidStockAmount(int rqAmount,String itemCode) {
        int stockOnHand = 0;
 
        try {
            Connection con = db_connect.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT stockQty FROM Item WHERE itemCode = '"+itemCode+"'");
            
            while(rs.next()){
               stockOnHand = rs.getInt("stockQty");
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stockOnHand>=rqAmount&& rqAmount>0;
    }*/
}
