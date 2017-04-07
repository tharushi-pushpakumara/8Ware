/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;


import java.awt.Component;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author YASHVINE
 */
public class Validations {

    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()-=+[]{}|:\";',./<>?";
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 20;
    public static final int Phone = 10;
   
//Password
    public static boolean isAcceptablePassword(String password) {
        int UC_Count = 0;
        int LC_Count = 0;
        int DC_Count = 0;
        int SC_Count = 0;
        if (password.isEmpty()) {
            System.out.println("empty string.");
            return false;

        }
        password = password.trim();

        int len = password.length();
        if (len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
            JOptionPane.showMessageDialog(null, "wrong size, it must have at least 8 characters and less than 20");
            return false;
        }
        char[] aC = password.toCharArray();
        for (char c : aC) {
            if (Character.isUpperCase(c)) {
                UC_Count++;
            } else if (Character.isLowerCase(c)) {
                LC_Count++;
            } else if (Character.isDigit(c)) {
                DC_Count++;
            } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                SC_Count++;
            } else {
                JOptionPane.showMessageDialog(null, c + " is an invalid character in the password.");
                return false;
            }
        }
        if (UC_Count < 1 || LC_Count < 1 || DC_Count < 1 || SC_Count < 1) {
            JOptionPane.showMessageDialog(null, "Incorrect Password");
            return false;
        }

        return true;

    }

//Contact Number
    public static boolean isAcceptableContact_Number(String cNumber) {
        int UC_Count = 0;
        int LC_Count = 0;
        int DC_Count = 0;
        int SC_Count = 0;

        if (cNumber.isEmpty()) {
            System.out.println("empty string.");
            return false;

        }
        cNumber = cNumber.trim();

        int len = cNumber.length();
        if (len != Phone) {
            JOptionPane.showMessageDialog(null, "Contact Number must 10 digits");
            return false;
        }
        char[] aC = cNumber.toCharArray();
        for (char c : aC) {
            if (Character.isUpperCase(c)) {
                UC_Count++;
            } else if (Character.isLowerCase(c)) {
                LC_Count++;
            } else if (Character.isDigit(c)) {
                DC_Count++;
            } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                SC_Count++;
            }

        }
        if (UC_Count > 0 || LC_Count > 0 || DC_Count != Phone || SC_Count > 0) {
            JOptionPane.showMessageDialog(null, "Incorrect Contact Number");
            return false;
        }

        return true;

    }

    public static boolean isAcceptableNIC(String NIC) {
        int UC_Count = 0;
        int LC_Count = 0;
        int DC_Count = 0;
        int SC_Count = 0;
        if (NIC.isEmpty()) {
            System.out.println("empty string.");
            return false;

        }
        int len = NIC.length();
        if (len != 10) {
            JOptionPane.showMessageDialog(null, "Must have 9 digits with a charactor");
            return false;
        }
        char[] aC = NIC.toCharArray();
        for (char c : aC) {
            if (Character.isUpperCase(c)) {
                UC_Count++;
            } else if (Character.isLowerCase(c)) {
                LC_Count++;
            } else if (Character.isDigit(c)) {
                DC_Count++;
            } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                SC_Count++;
            } 
        }
        if (UC_Count != 1 || LC_Count > 0 || DC_Count != 9 || SC_Count > 0) {
            JOptionPane.showMessageDialog(null, "Incorrect NIC");
            return false;
        }

        return true;

    }

    public static void resizeColumnWidth(JTable table) {

        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < (table.getColumnCount() - 1); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {

        if (date != null) {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;
        }
        if (date == null){
            return null;
        }
        return null;
    }
    
    
   //Only letters
    public static boolean ContainOnlyLetters(String word) {
        int UC_Count = 0;
        int LC_Count = 0;
        int DC_Count = 0;
        int SC_Count = 0;
        if (word.isEmpty()) {
            System.out.println("empty string.");
            return false;

        }
        word = word.trim();

       
        char[] aC = word.toCharArray();
        for (char c : aC) {
            if (Character.isUpperCase(c)) {
                UC_Count++;
            } else if (Character.isLowerCase(c)) {
                LC_Count++;
            } else if (Character.isDigit(c)) {
                DC_Count++;
            } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                SC_Count++;
            }
        }
        if (DC_Count >0 || SC_Count >0) {
            JOptionPane.showMessageDialog(null, "Incorrect word \n Only letters");
            return false;
        }

        return true;

    }
}
