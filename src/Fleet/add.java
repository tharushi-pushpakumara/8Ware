package Fleet;

//import java.awt.Color;
import HRM.Login;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;             // To Connect to the data base
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;          // JOptionPane class is a class for accepting user input, and displaying results, located in the javax.swing library
import net.proteanit.sql.DbUtils; 
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;

public class add extends javax.swing.JFrame {
    

Connection conn = null;                                              
ResultSet rs = null; 
ResultSet rss = null;    
PreparedStatement pst = null; 

 Date d5 = new Date();

//AccessMethods method = new AccessMethods();

DefaultListModel dlm = new DefaultListModel();

//constructor
    public add() throws SQLException, Exception {
        initComponents();
        this.setResizable(false);//not resizable now
        this.setVisible(true);
        
        conn = DBAccess.getConnection(); 
        
        txt_vehicleID_Add.setText(incrementVID());
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        
        
        
        Update_Insurance_Table();
        Update_Registration_Table();
        Update_FuelFilling_Table();           
    }
    
    
    public  String  incrementVID() {
        String nextAgreementIdStr = null;
        try{
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT COUNT(*)FROM vehicle");
             while(rs.next())
            {
                int currentId = Integer.parseInt(rs.getString("count(*)"));
                int nextAgreementId = currentId+1;
                nextAgreementIdStr = "V00".concat(Integer.toString(nextAgreementId));
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);   
        }
    return nextAgreementIdStr;
    }

    
    private void Update_Payments_tables(){
         table_payment.setBackground((Color.GRAY)); 
         table_payment.setForeground(Color.white);
         table_insurance_payment.setBackground((Color.GRAY)); 
         table_insurance_payment.setForeground(Color.white);
         table_payment_Fuelfilling.setBackground((Color.GRAY)); 
         table_payment_Fuelfilling.setForeground(Color.white);
         String sql2="Select  billID,date,payment_type,price   from payments"; 
         try{               
            pst =conn.prepareStatement(sql2);            //carry SQL query to the DB.  
            rss=pst.executeQuery();                      //execute a query and retrive the result.         
            table_payment.setModel(DbUtils.resultSetToTableModel(rss)); 
            rss.close(); 
            rss=pst.executeQuery();  
            table_insurance_payment.setModel(DbUtils.resultSetToTableModel(rss));  
            rss.close(); 
            rss=pst.executeQuery(); 
            table_payment_Fuelfilling.setModel(DbUtils.resultSetToTableModel(rss));
           
            rss.close();                                             
            pst.close(); 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{                                                       
            rss.close();                                             
            pst.close();  
            
        }catch(Exception e) {                                     
        }                                                         
    }                           
    }
    
    private void Update_Registration_Table(){                                        
        table_registration.setBackground((Color.GRAY)); 
        table_registration.setForeground(Color.white);
       
        String sql1="Select  *   from vehicle_registration ";  
       try{               
            pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
            rs=pst.executeQuery();                      //execute a query and retrive the result.
            table_registration.setModel(DbUtils.resultSetToTableModel(rs));  
            rs.close(); 
            pst.close(); 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{         
            Update_Payments_tables();
            rs.close();                                             
            pst.close();                                                                               
        }catch(Exception e) {                                     
        }                                                         
    }                                                                                                                 //4
    }    
    
    private void Update_Insurance_Table(){                                        
        table_insurance.setBackground((Color.GRAY)); 
        table_insurance.setForeground(Color.white);
   
        String sql1="Select  *   from vehicle_insurance ";  
        try{               
            pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
            rs=pst.executeQuery();                      //execute a query and retrive the result.
            table_insurance.setModel(DbUtils.resultSetToTableModel(rs));  
            rs.close(); 
            pst.close();  
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{   
            Update_Payments_tables();
            rs.close();                                             
            pst.close();                                                                               
        }catch(Exception e) {                                     
        }                                                         
    }                                                                                                                 //4
    } 
    
    private void Update_FuelFilling_Table(){                                        
        table_Fuelfilling.setBackground((Color.GRAY));
        table_Fuelfilling.setForeground(Color.white);
      
        String sql1="Select  *   from fuel_filling ";  
         try{               
            pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
            rs=pst.executeQuery();                      //execute a query and retrive the result.
            table_Fuelfilling.setModel(DbUtils.resultSetToTableModel(rs));  
            rs.close(); 
            pst.close(); 

    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{  
            Update_Payments_tables();
             rs.close(); 
             pst.close();                                       
        }catch(Exception e) {                                     
        }                                                         
    }                                                                                                                          //4
    }              
       
    
// method to close the window when opening a new window.
    public void close(){             
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);    
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);      
    } 
    
    
    public boolean checkAdd_isEmphty(){
        if(!validation.isEmphtyCheck(txt_model_Add.getText())){
            jLabel67.setText("Model should be filled");
            return false;}
        else if(!validation.isEmphtyCheck(txt_chassicNo_Add.getText())){
            jLabel48.setText("Chassic No should be filled");
            return false;}
        else if(!validation.isEmphtyCheck(txt_Colour_Add.getText())){
            jLabel68.setText("Colour should be filled");  
             return false;}
        else if( buttonGroup1.isSelected(null)  ){
            jLabel76.setText("Tranimission type shoule be selected");
             return false;}
        else if( buttonGroup2.isSelected(null)  ){
            jLabel77.setText("Fuel type shoule be selected");
             return false;}
        else if( buttonGroup3.isSelected(null)  ){
            jLabel78.setText("Status shoule be selected");
             return false;}
        else if(!validation.isEmphtyCheck(txt_RegNum_Add.getText())){
            jLabel72.setText("Reg Num should be filled");
             return false;}
        else if(!validation.isEmphtyCheck(txt_InsNum_Add.getText())){
            jLabel69.setText("Ins Num should be filled");  
             return false;}
        else if(!validation.isEmphtyCheck(txt_InsCompany_Add.getText())){
            jLabel70.setText("Ins Company should be filled");
             return false;}
        else
             return true;
    }
    
    public boolean checkReg_isEmphty(){
         if(!validation.isEmphtyCheck(txt_vID_Reg.getText())){
             jLabel79.setText("Vehicle ID should be filled");
            return false;}
         else if(!validation.isEmphtyCheck(txt_billID_Reg.getText())){
            jLabel81.setText("Bill ID should be filled");   
            return false;}
        else if( buttonGroup4.isSelected(null)  ){
            jLabel82.setText("Payment type shoule be selected");
             return false;}
        else if(!validation.isEmphtyCheck(txt_price_Reg.getText())){
            jLabel83.setText("Price should be filled");
             return false;}
        else
             return true;
    }   
    
    public boolean checkIns_isEmphty(){
         if(!validation.isEmphtyCheck(txt_vehicleID_Ins.getText())){
            jLabel22.setText("Vehicle ID should be filled");
            return false;}
         else if(!validation.isEmphtyCheck(txt_billID_Ins.getText())){
            jLabel80.setText("Bill ID should be filled"); 
            return false;}
       else if( buttonGroup5.isSelected(null)  ){
            jLabel86.setText("Payment type shoule be selected");
             return false;}
        else if(!validation.isEmphtyCheck(txt_price_Ins.getText())){
            jLabel87.setText("Price should be filled");
             return false;}
        else
             return true;
    }
      
    public boolean checkFuel_isEmphty(){
         if(!validation.isEmphtyCheck(txt_vehicleID_Fuel.getText())){
            jLabel14.setText("Vehicle ID should be filled");
            return false;}
         else if( buttonGroup6.isSelected(null)  ){
            jLabel94.setText("Fuel type shoule be selected");
            return false;}
        else if(!validation.isEmphtyCheck(txt_amount_Fuel.getText())){
            jLabel93.setText("Amount of liter should be filled"); 
             return false;}
        else if(!validation.isEmphtyCheck(txt_BillID_Fuel.getText())){
            jLabel91.setText("Bill should be filled");
            return false;}
        else if( buttonGroup7.isSelected(null)  ){
            jLabel90.setText("Payment type shoule be selected");
             return false;}
       else if(!validation.isEmphtyCheck(txt_Price_Fuel.getText())){
            jLabel19.setText("Price should be filled"); 
             return false;}
        else
             return true;
    }
    
    public boolean checkMtn_isEmphty(){
         if(!validation.isEmphtyCheck(txt_vehicleID_Mtn.getText())){
            jLabel95.setText("Vehicle ID should be filled");
            return false;}
          else if(!validation.isEmphtyCheck(txt_serviceID_Mtn.getText())){
            jLabel96.setText("Service ID should be filled"); 
            return false;}
         else if(!validation.isEmphtyCheck(txt_BillID_Mtn.getText())){
            jLabel98.setText("Bill ID should be filled");
             return false;}
         else if( buttonGroup8.isSelected(null)  ){
            jLabel99.setText("Payment type shoule be selected");
            return false;}
         else if(!validation.isEmphtyCheck(txt_Price_Mtn.getText())){
            jLabel100.setText("Price should be filled");
             return false;}
        else
             return true;
    }
            
    
    public void clearMtn(){
        cmd_save_Mtn.setEnabled(true);
        jLabel95.setText("         ");
        jLabel96.setText("         ");
        jLabel97.setText("         ");
        jLabel98.setText("         ");        
        jLabel99.setText("         ");       
        jLabel100.setText("         ");
        jLabel101.setText("         ");
        txt_select_Item_Mtn.setText("");
        txt_search_Mtn.setText("");
        txt_vehicleID_Mtn.enable();
        //txt_InsNum_Ins.enable();
        txt_BillID_Mtn.enable();
        txt_vehicleID_Mtn.setText("");
        txt_serviceID_Mtn.setText("");
        txt_BillID_Mtn.setText("");
        buttonGroup8.clearSelection();
        txt_Price_Mtn.setText("");
        dlm.clear();
       jCheckBox1.setSelected(false);
       jCheckBox2.setSelected(false);
       jCheckBox3.setSelected(false);
       jCheckBox4.setSelected(false);
       jCheckBox5.setSelected(false);
       jCheckBox6.setSelected(false);
       jCheckBox7.setSelected(false);
       jCheckBox8.setSelected(false);
       jCheckBox9.setSelected(false);
       jCheckBox10.setSelected(false);
       jCheckBox11.setSelected(false);
       jCheckBox12.setSelected(false);
       jCheckBox13.setSelected(false);
       jCheckBox14.setSelected(false);
       jCheckBox16.setSelected(false);
       jCheckBox17.setSelected(false);
       jCheckBox18.setSelected(false);
       jCheckBox19.setSelected(false);
       jCheckBox20.setSelected(false);
       jCheckBox10.setSelected(false);
       jCheckBox21.setSelected(false);
       jCheckBox22.setSelected(false);
       jCheckBox23.setSelected(false);
       jCheckBox24.setSelected(false);
       jCheckBox25.setSelected(false);
       jCheckBox26.setSelected(false);
       jCheckBox27.setSelected(false);
       jCheckBox20.setSelected(false);
        ((JTextField)jDateChooser_Mtn.getDateEditor().getUiComponent()).setText("");
    
    }        
         
    public void clearFuel(){
        cmd_save_Fuel.setEnabled(true);
        jRadioButton1_Fuel_fuel.enable();
        jRadioButton2_Fuel_fuel.enable();
        txt_vehicleID_Fuel.enable();
        txt_BillID_Fuel.enable();
        txt_vehicleID_Fuel.setText("");
        buttonGroup6.clearSelection();
        txt_amount_Fuel.setText("");
       ((JTextField)jDateChooser_Fuel.getDateEditor().getUiComponent()).setText("");
        buttonGroup7.clearSelection();
        txt_Price_Fuel.setText("");
        txt_BillID_Fuel.setText("");
        jLabel14.setText("                ");
        jLabel94.setText("                ");
        jLabel93.setText("                ");
        jLabel92.setText("                ");
        jLabel91.setText("                ");
        jLabel90.setText("                ");
        jLabel19.setText("                ");
    }  
    
    public void clearIns(){
        cmd_save_Ins.setEnabled(true);
        jLabel22.setText("                ");
        jLabel80.setText("                ");
        jLabel86.setText("                ");
        jLabel87.setText("                ");
        jLabel88.setText("                ");
        jLabel89.setText("                ");
        txt_vehicleID_Ins.enable();
        txt_billID_Ins.enable();
        txt_vehicleID_Ins.setText("");
        txt_billID_Ins.setText("");
        buttonGroup5.clearSelection();
        txt_price_Ins.setText("");
        txt_search_Ins.setText("");
        ((JTextField)DateChooser_Renew_Ins.getDateEditor().getUiComponent()).setText("");
        ((JTextField)DateChooser_nextRenew_Ins.getDateEditor().getUiComponent()).setText("");
    }
        
    public void clearReg(){
        cmd_save_Reg.setEnabled(true);
        txt_vID_Reg.enable();
        txt_billID_Reg.enable();
        txt_vID_Reg.setText("");
        txt_billID_Reg.setText("");
        buttonGroup4.clearSelection();
        txt_price_Reg.setText("");
        txt_search_Reg.setText("");
        ((JTextField)DateChooser_renewDate_Reg.getDateEditor().getUiComponent()).setText(""); 
         ((JTextField)DateChooser_nextRenewDate_Reg.getDateEditor().getUiComponent()).setText("");
        jLabel79.setText("                        ");
        jLabel81.setText("                        ");  
        jLabel82.setText("                        ");
         jLabel83.setText("                       ");
         jLabel84.setText("                       ");
         jLabel85.setText("                        ");
    }   
  
    public void clearVehicleAdd(){
       txt_vehicleID_Add.setText(incrementVID());
        txt_chassicNo_Add.enable();
        txt_RegNum_Add.enable();
        txt_InsNum_Add.enable();
        txt_model_Add.enable();
        ComboBox_Type_Add.enable();
        ((JTextField)DateChooser_RegDate_Add.getDateEditor().getUiComponent()).enable();
        ((JTextField)DateChooser_InsDate_Add.getDateEditor().getUiComponent()).enable();
        jLabel65.setText("                  ");
        jLabel48.setText("                  ");
        jLabel67.setText("                  ");
        txt_model_Add.setText("");
        txt_chassicNo_Add.setText("");
        txt_Colour_Add.setText("");
        txt_GPS_Add.setText("");
        txt_RegNum_Add.setText("");
        txt_InsNum_Add.setText("");
        txt_InsCompany_Add.setText("");
        search_Add.setText("");
        ((JTextField)DateChooser_RegDate_Add.getDateEditor().getUiComponent()).setText("");
        ((JTextField)DateChooser_InsDate_Add.getDateEditor().getUiComponent()).setText("");
        txt_profPic_add.setIcon(null);
        txt_path_Add.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        ComboBox_Type_Add.setSelectedItem("");
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        txt_chassicNo_Add = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txt_Colour_Add = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        RadioButton_Transmission_1_Add = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        RadioButton_Fuel_1_Add = new javax.swing.JRadioButton();
        RadioButton_Fuel_2_Add = new javax.swing.JRadioButton();
        RadioButton_Transmission_2_Add = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        ComboBox_Type_Add = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txt_model_Add = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        RadioButton_status_2_Add = new javax.swing.JRadioButton();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        RadioButton_status_1_Add = new javax.swing.JRadioButton();
        txt_vehicleID_Add = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txt_RegNum_Add = new javax.swing.JTextField();
        DateChooser_RegDate_Add = new com.toedter.calendar.JDateChooser();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_InsNum_Add = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txt_InsCompany_Add = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        DateChooser_InsDate_Add = new com.toedter.calendar.JDateChooser();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txt_path_Add = new javax.swing.JTextField();
        cmd_AttachPic_Add = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_GPS_Add = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        search_Add = new javax.swing.JTextField();
        cmd_Search_Add = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        cmd_clear_Add = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cmd_delete_Add = new javax.swing.JButton();
        cmd_save_Add = new javax.swing.JButton();
        cmd_update_Add = new javax.swing.JButton();
        cmd_report_Add = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        DesktopPane_VehPic_add = new javax.swing.JDesktopPane();
        txt_profPic_add = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        cmd_delete_Reg = new javax.swing.JButton();
        cmd_update_Reg = new javax.swing.JButton();
        cmd_clear_Reg = new javax.swing.JButton();
        cmd_save_Reg = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        txt_billID_Reg = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txt_price_Reg = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        DateChooser_renewDate_Reg = new com.toedter.calendar.JDateChooser();
        DateChooser_nextRenewDate_Reg = new com.toedter.calendar.JDateChooser();
        RadioButton1_Reg = new javax.swing.JRadioButton();
        RadioButton2_Reg = new javax.swing.JRadioButton();
        txt_vID_Reg = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_registration = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        txt_search_Reg = new javax.swing.JTextField();
        cmd_vehicleID_Reg = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        cmd_report_Reg = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_payment = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txt_billID_Ins = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_price_Ins = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        DateChooser_nextRenew_Ins = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        DateChooser_Renew_Ins = new com.toedter.calendar.JDateChooser();
        jRadioButton1_Ins = new javax.swing.JRadioButton();
        jRadioButton2_Ins = new javax.swing.JRadioButton();
        txt_vehicleID_Ins = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        cmd_delete_Ins = new javax.swing.JButton();
        cmd_update_Ins = new javax.swing.JButton();
        cmd_clear_Ins = new javax.swing.JButton();
        cmd_save_Ins = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        txt_search_Ins = new javax.swing.JTextField();
        cmd_search_Ins = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        cmd_report_Reg1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_insurance = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_insurance_payment = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_Fuelfilling = new javax.swing.JTable();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        txt_search_Fuel = new javax.swing.JTextField();
        cud_search_Fuel = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        cmd_report_Reg2 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_payment_Fuelfilling = new javax.swing.JTable();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        txt_BillID_Fuel = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txt_vehicleID_Fuel = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txt_Price_Fuel = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txt_amount_Fuel = new javax.swing.JTextField();
        jDateChooser_Fuel = new com.toedter.calendar.JDateChooser();
        jRadioButton1_Fuel_fuel = new javax.swing.JRadioButton();
        jRadioButton2_Fuel_fuel = new javax.swing.JRadioButton();
        jRadioButton1_payType_Fuel = new javax.swing.JRadioButton();
        jRadioButton2_payType_Fuel = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        cmd_delete_Fuel = new javax.swing.JButton();
        cmd_update_Fuel = new javax.swing.JButton();
        cmd_clear_Fuel = new javax.swing.JButton();
        cmd_save_Fuel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        txt_search_Mtn = new javax.swing.JTextField();
        cud_search_Mtn = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        cmd_report_Reg3 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        cmd_delete_Mtn = new javax.swing.JButton();
        cmd_update_Mtn = new javax.swing.JButton();
        cmd_clear_Mtn = new javax.swing.JButton();
        cmd_save_Mtn = new javax.swing.JButton();
        cmd_tables_Mtn = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        txt_select_Item_Mtn = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        txt_BillID_Mtn = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_vehicleID_Mtn = new javax.swing.JTextField();
        txt_serviceID_Mtn = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txt_Price_Mtn = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        ComboBox_Type_Mtn = new javax.swing.JComboBox();
        jLabel63 = new javax.swing.JLabel();
        jRadioButton1_Mtn = new javax.swing.JRadioButton();
        jRadioButton2_Mtn = new javax.swing.JRadioButton();
        jDateChooser_Mtn = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1_Mtn = new javax.swing.JList();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txt_time = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        cmd_home = new javax.swing.JButton();
        cmd_logout = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFocusCycleRoot(true);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_chassicNo_Add.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_chassicNo_AddFocusLost(evt);
            }
        });
        txt_chassicNo_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_chassicNo_AddKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Chassic No  ");

        jSeparator5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txt_Colour_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Colour_AddKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Transmission           ");

        buttonGroup1.add(RadioButton_Transmission_1_Add);
        RadioButton_Transmission_1_Add.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton_Transmission_1_Add.setText("Auto");
        RadioButton_Transmission_1_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_Transmission_1_AddActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Model               ");

        buttonGroup2.add(RadioButton_Fuel_1_Add);
        RadioButton_Fuel_1_Add.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton_Fuel_1_Add.setText("Petrol");
        RadioButton_Fuel_1_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_Fuel_1_AddActionPerformed(evt);
            }
        });

        buttonGroup2.add(RadioButton_Fuel_2_Add);
        RadioButton_Fuel_2_Add.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton_Fuel_2_Add.setText("Diesel");
        RadioButton_Fuel_2_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_Fuel_2_AddActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioButton_Transmission_2_Add);
        RadioButton_Transmission_2_Add.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton_Transmission_2_Add.setText("Manual");
        RadioButton_Transmission_2_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_Transmission_2_AddActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Status        ");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Colour            ");

        ComboBox_Type_Add.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboBox_Type_Add.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Car", "Bike", "Van", "Three Wheeler" }));
        ComboBox_Type_Add.setPreferredSize(new java.awt.Dimension(6, 20));
        ComboBox_Type_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_Type_AddActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fuel Type      ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Type                 ");

        jSeparator4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txt_model_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_model_AddKeyPressed(evt);
            }
        });

        jSeparator6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonGroup3.add(RadioButton_status_2_Add);
        RadioButton_status_2_Add.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton_status_2_Add.setText("not-active");
        RadioButton_status_2_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_status_2_AddActionPerformed(evt);
            }
        });

        jSeparator7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Vehicle ID  ");

        buttonGroup3.add(RadioButton_status_1_Add);
        RadioButton_status_1_Add.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton_status_1_Add.setText("active");
        RadioButton_status_1_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_status_1_AddActionPerformed(evt);
            }
        });

        txt_vehicleID_Add.setInheritsPopupMenu(true);
        txt_vehicleID_Add.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vehicleID_AddFocusLost(evt);
            }
        });
        txt_vehicleID_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vehicleID_AddActionPerformed(evt);
            }
        });
        txt_vehicleID_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_vehicleID_AddKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vehicleID_AddKeyTyped(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 0, 0));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("                                               ");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(204, 0, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel67.setText("                                                ");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(204, 0, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("                                          ");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(204, 0, 0));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("                                                ");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(204, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel76.setText("                                                         ");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(204, 0, 0));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel77.setText("                                                 ");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(204, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel78.setText("                                                 ");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(31, 31, 31)
                                            .addComponent(txt_model_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(41, 41, 41)
                                            .addComponent(txt_chassicNo_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_Colour_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel33Layout.createSequentialGroup()
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(34, 34, 34)
                                                    .addComponent(RadioButton_Fuel_1_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel33Layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(RadioButton_Transmission_1_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(RadioButton_Transmission_2_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(RadioButton_Fuel_2_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ComboBox_Type_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_vehicleID_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                                .addComponent(RadioButton_status_1_Add)
                                .addGap(41, 41, 41)
                                .addComponent(RadioButton_status_2_Add)
                                .addGap(65, 65, 65))))))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_vehicleID_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ComboBox_Type_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_model_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_chassicNo_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txt_Colour_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(RadioButton_Transmission_1_Add)
                    .addComponent(RadioButton_Transmission_2_Add))
                .addGap(1, 1, 1)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(RadioButton_Fuel_1_Add)
                    .addComponent(RadioButton_Fuel_2_Add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel77)
                .addGap(5, 5, 5)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(RadioButton_status_1_Add)
                    .addComponent(RadioButton_status_2_Add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel78)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel50.setText("Vehicle Details");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Registered Date ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Registration Num");

        txt_RegNum_Add.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RegNum_AddFocusLost(evt);
            }
        });
        txt_RegNum_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RegNum_AddKeyPressed(evt);
            }
        });

        DateChooser_RegDate_Add.setDateFormatString("yyyy-MM-dd");
        DateChooser_RegDate_Add.setMaxSelectableDate(d5);
        DateChooser_RegDate_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DateChooser_RegDate_AddKeyPressed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(204, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText("                                             ");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(204, 0, 0));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText("                                             ");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_RegNum_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateChooser_RegDate_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_RegNum_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(DateChooser_RegDate_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Insured Company    ");

        jSeparator3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txt_InsNum_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_InsNum_AddKeyPressed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Insured Date    ");

        txt_InsCompany_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_InsCompany_AddKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Insurance Num           ");

        DateChooser_InsDate_Add.setDateFormatString("yyyy-MM-dd");
        DateChooser_InsDate_Add.setMaxSelectableDate(d5);
        DateChooser_InsDate_Add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DateChooser_InsDate_AddKeyPressed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(204, 0, 0));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel69.setText("                                           ");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(204, 0, 0));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("                                            ");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(204, 0, 0));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel71.setText("                                          ");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2)
                        .addComponent(jSeparator3)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(14, 14, 14)
                            .addComponent(txt_InsCompany_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel32Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_InsNum_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateChooser_InsDate_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_InsNum_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel69)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_InsCompany_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(4, 4, 4)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(DateChooser_InsDate_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel71)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setText("Registration Details");

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel52.setText("Insurance Details");

        cmd_AttachPic_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_AttachPic_Add.setText("Add Image");
        cmd_AttachPic_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_AttachPic_AddActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("GPS                  ");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(204, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel74.setText("                                                 ");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(204, 0, 0));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel75.setText("                                                       ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_path_Add, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmd_AttachPic_Add)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_GPS_Add))
                                .addGap(24, 24, 24))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_GPS_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_path_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd_AttachPic_Add))
                .addGap(2, 2, 2)
                .addComponent(jLabel75)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel53.setText("Search By Vehicle ID");

        jPanel35.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        search_Add.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        cmd_Search_Add.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        cmd_Search_Add.setText("Search");
        cmd_Search_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_Search_AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search_Add, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmd_Search_Add)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd_Search_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel34.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel45.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_clear_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_clear_Add.setText("REFRESH");
        cmd_clear_Add.setPreferredSize(new java.awt.Dimension(71, 15));
        cmd_clear_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_clear_AddActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("VEHICLES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmd_delete_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_delete_Add.setText("DELETE");
        cmd_delete_Add.setPreferredSize(new java.awt.Dimension(71, 15));
        cmd_delete_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_delete_AddActionPerformed(evt);
            }
        });

        cmd_save_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_save_Add.setText("SAVE");
        cmd_save_Add.setPreferredSize(new java.awt.Dimension(71, 15));
        cmd_save_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_save_AddActionPerformed(evt);
            }
        });

        cmd_update_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_update_Add.setText("UPDATE");
        cmd_update_Add.setPreferredSize(new java.awt.Dimension(71, 15));
        cmd_update_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_update_AddActionPerformed(evt);
            }
        });

        cmd_report_Add.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cmd_report_Add.setForeground(new java.awt.Color(204, 0, 51));
        cmd_report_Add.setText("REPORT");
        cmd_report_Add.setPreferredSize(new java.awt.Dimension(71, 15));
        cmd_report_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_report_AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmd_delete_Add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmd_save_Add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmd_update_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmd_report_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmd_save_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd_update_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmd_clear_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd_delete_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmd_report_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel27.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        DesktopPane_VehPic_add.setBackground(new java.awt.Color(0, 255, 204));

        txt_profPic_add.setForeground(new java.awt.Color(51, 51, 255));
        txt_profPic_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        DesktopPane_VehPic_add.setLayer(txt_profPic_add, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopPane_VehPic_addLayout = new javax.swing.GroupLayout(DesktopPane_VehPic_add);
        DesktopPane_VehPic_add.setLayout(DesktopPane_VehPic_addLayout);
        DesktopPane_VehPic_addLayout.setHorizontalGroup(
            DesktopPane_VehPic_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopPane_VehPic_addLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_profPic_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        DesktopPane_VehPic_addLayout.setVerticalGroup(
            DesktopPane_VehPic_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopPane_VehPic_addLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_profPic_add, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DesktopPane_VehPic_add)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DesktopPane_VehPic_add)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setText("DEMO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("   ADD VEHICLES              ", jPanel1);

        jPanel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel42.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_delete_Reg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_delete_Reg.setText("DELETE");
        cmd_delete_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_delete_RegActionPerformed(evt);
            }
        });

        cmd_update_Reg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_update_Reg.setText("UPDATE");
        cmd_update_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_update_RegActionPerformed(evt);
            }
        });

        cmd_clear_Reg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_clear_Reg.setText("REFRESH");
        cmd_clear_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_clear_RegActionPerformed(evt);
            }
        });

        cmd_save_Reg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_save_Reg.setText("SAVE");
        cmd_save_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_save_RegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_update_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(cmd_save_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(cmd_delete_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(cmd_save_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmd_delete_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(cmd_update_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_billID_Reg.setText("BID");
        txt_billID_Reg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_billID_RegFocusLost(evt);
            }
        });
        txt_billID_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_billID_RegActionPerformed(evt);
            }
        });
        txt_billID_Reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_billID_RegKeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Renewed  Date");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Vehicle ID");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Bill ID");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Expiry Date");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Price");

        txt_price_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_price_RegActionPerformed(evt);
            }
        });
        txt_price_Reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_price_RegKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_price_RegKeyTyped(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Payment Type");

        DateChooser_renewDate_Reg.setDateFormatString("yyyy-MM-dd");
        DateChooser_renewDate_Reg.setMaxSelectableDate(d5);
        DateChooser_renewDate_Reg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DateChooser_renewDate_RegMouseEntered(evt);
            }
        });
        DateChooser_renewDate_Reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DateChooser_renewDate_RegKeyReleased(evt);
            }
        });

        DateChooser_nextRenewDate_Reg.setDateFormatString("yyyy-MM-dd");
        DateChooser_nextRenewDate_Reg.setMinSelectableDate(d5);

        buttonGroup4.add(RadioButton1_Reg);
        RadioButton1_Reg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton1_Reg.setText("Cash");
        RadioButton1_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton1_RegActionPerformed(evt);
            }
        });

        buttonGroup4.add(RadioButton2_Reg);
        RadioButton2_Reg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioButton2_Reg.setText("Cheque");
        RadioButton2_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton2_RegActionPerformed(evt);
            }
        });

        txt_vID_Reg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vID_RegFocusLost(evt);
            }
        });
        txt_vID_Reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_vID_RegKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_vID_RegKeyReleased(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(204, 0, 0));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel79.setText("                                           ");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(204, 0, 0));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel81.setText("                                          ");

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(204, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel82.setText("                                           ");

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(204, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel83.setText("                                            ");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(204, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel84.setText("                                            ");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(204, 0, 0));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel85.setText("                                      ");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateChooser_nextRenewDate_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateChooser_renewDate_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_billID_Reg)
                            .addComponent(txt_price_Reg)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(RadioButton1_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(RadioButton2_Reg)
                                .addGap(19, 19, 19))
                            .addComponent(txt_vID_Reg, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(txt_vID_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txt_billID_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(RadioButton1_Reg)
                    .addComponent(RadioButton2_Reg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txt_price_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateChooser_renewDate_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(DateChooser_nextRenewDate_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel54.setText("Renew Registration");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        table_registration.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_registration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_registrationMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_registration);

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel40.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_search_Reg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        cmd_vehicleID_Reg.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        cmd_vehicleID_Reg.setText("Search");
        cmd_vehicleID_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_vehicleID_RegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_search_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmd_vehicleID_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_search_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(cmd_vehicleID_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel55.setText("Search By Vehicle ID");

        jPanel41.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_report_Reg.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cmd_report_Reg.setForeground(new java.awt.Color(204, 0, 51));
        cmd_report_Reg.setText("REPORT");
        cmd_report_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_report_RegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(466, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel55))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        table_payment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_payment);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Table Registration Renew");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Table Payment");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 31, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(196, 196, 196))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   RENEW REGISTRATION    ", jPanel4);

        jPanel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Vehicle ID");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Expiry Date");

        txt_billID_Ins.setText("BID");
        txt_billID_Ins.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_billID_InsFocusLost(evt);
            }
        });
        txt_billID_Ins.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_billID_InsKeyPressed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Bill ID");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Payment Type");

        txt_price_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_price_InsActionPerformed(evt);
            }
        });
        txt_price_Ins.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_price_InsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_price_InsKeyTyped(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Price");

        DateChooser_nextRenew_Ins.setDateFormatString("yyyy-MM-dd");
        DateChooser_nextRenew_Ins.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DateChooser_nextRenew_InsKeyPressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Renewed  Date");

        DateChooser_Renew_Ins.setDateFormatString("yyyy-MM-dd");
        DateChooser_Renew_Ins.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DateChooser_Renew_InsKeyPressed(evt);
            }
        });

        buttonGroup5.add(jRadioButton1_Ins);
        jRadioButton1_Ins.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1_Ins.setText("Cash");
        jRadioButton1_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1_InsActionPerformed(evt);
            }
        });

        buttonGroup5.add(jRadioButton2_Ins);
        jRadioButton2_Ins.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2_Ins.setText("Cheque");
        jRadioButton2_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2_InsActionPerformed(evt);
            }
        });

        txt_vehicleID_Ins.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vehicleID_InsFocusLost(evt);
            }
        });
        txt_vehicleID_Ins.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_vehicleID_InsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_vehicleID_InsKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("                                        ");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(204, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel80.setText("                                         ");

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(204, 0, 0));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel86.setText("                                                    ");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(204, 0, 0));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel87.setText("                                         ");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(204, 0, 0));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel88.setText("                                      ");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(204, 0, 0));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel89.setText("                                            ");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel86))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateChooser_Renew_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_billID_Ins)
                            .addComponent(txt_price_Ins)
                            .addComponent(DateChooser_nextRenew_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_vehicleID_Ins, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jRadioButton1_Ins)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton2_Ins)
                                .addGap(22, 22, 22)))))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txt_vehicleID_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txt_billID_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jRadioButton1_Ins)
                    .addComponent(jRadioButton2_Ins))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txt_price_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(DateChooser_Renew_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(DateChooser_nextRenew_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addContainerGap())
        );

        jPanel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_delete_Ins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_delete_Ins.setText("DELETE");
        cmd_delete_Ins.setOpaque(false);
        cmd_delete_Ins.setPreferredSize(new java.awt.Dimension(87, 30));
        cmd_delete_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_delete_InsActionPerformed(evt);
            }
        });

        cmd_update_Ins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_update_Ins.setText("UPDATE");
        cmd_update_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_update_InsActionPerformed(evt);
            }
        });

        cmd_clear_Ins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_clear_Ins.setText("REFRESH");
        cmd_clear_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_clear_InsActionPerformed(evt);
            }
        });

        cmd_save_Ins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_save_Ins.setText("SAVE");
        cmd_save_Ins.setPreferredSize(new java.awt.Dimension(71, 30));
        cmd_save_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_save_InsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_update_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(cmd_save_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(cmd_delete_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmd_save_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmd_delete_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd_update_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 11, Short.MAX_VALUE))
        );

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel56.setText("Renew Insurance");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel46.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel47.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_search_Ins.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        cmd_search_Ins.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        cmd_search_Ins.setText("Search");
        cmd_search_Ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_search_InsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_search_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmd_search_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_search_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(cmd_search_Ins, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel57.setText("Search By Vehicle ID");

        jPanel48.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_report_Reg1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cmd_report_Reg1.setForeground(new java.awt.Color(204, 0, 51));
        cmd_report_Reg1.setText("REPORT");
        cmd_report_Reg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_report_Reg1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 511, Short.MAX_VALUE)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(466, Short.MAX_VALUE)))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel57))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        table_insurance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_insurance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_insuranceMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_insurance);

        table_insurance_payment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(table_insurance_payment);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Table Insurance Renew");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Table Payment");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("      RENEW INSURANCE      ", jPanel10);

        jPanel26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        table_Fuelfilling.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_Fuelfilling.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_FuelfillingMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_Fuelfilling);

        jPanel49.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel50.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_search_Fuel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        cud_search_Fuel.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        cud_search_Fuel.setText("Search");
        cud_search_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cud_search_FuelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_search_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cud_search_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cud_search_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel58.setText("Search By Vehicle ID");

        jPanel51.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_report_Reg2.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cmd_report_Reg2.setForeground(new java.awt.Color(204, 0, 51));
        cmd_report_Reg2.setText("REPORT");
        cmd_report_Reg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_report_Reg2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel49Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(466, Short.MAX_VALUE)))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel58))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel49Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        table_payment_Fuelfilling.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(table_payment_Fuelfilling);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Table Fuel Filling");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Table Payment");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel62)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel28.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel43.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_BillID_Fuel.setText("BID");
        txt_BillID_Fuel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BillID_FuelFocusLost(evt);
            }
        });
        txt_BillID_Fuel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BillID_FuelKeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Date");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Fuel Type");

        txt_vehicleID_Fuel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vehicleID_FuelFocusLost(evt);
            }
        });
        txt_vehicleID_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vehicleID_FuelActionPerformed(evt);
            }
        });
        txt_vehicleID_Fuel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_vehicleID_FuelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_vehicleID_FuelKeyReleased(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("VehicleID");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Bill ID");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Price");

        txt_Price_Fuel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Price_FuelKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Price_FuelKeyTyped(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Payment Type");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Amount of Liters");

        txt_amount_Fuel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_amount_FuelKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_amount_FuelKeyTyped(evt);
            }
        });

        jDateChooser_Fuel.setDateFormatString("yyyy-MM-dd");

        buttonGroup6.add(jRadioButton1_Fuel_fuel);
        jRadioButton1_Fuel_fuel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1_Fuel_fuel.setText("Petrol");
        jRadioButton1_Fuel_fuel.setEnabled(false);
        jRadioButton1_Fuel_fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1_Fuel_fuelActionPerformed(evt);
            }
        });

        buttonGroup6.add(jRadioButton2_Fuel_fuel);
        jRadioButton2_Fuel_fuel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2_Fuel_fuel.setText("Diesel");
        jRadioButton2_Fuel_fuel.setEnabled(false);
        jRadioButton2_Fuel_fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2_Fuel_fuelActionPerformed(evt);
            }
        });

        buttonGroup7.add(jRadioButton1_payType_Fuel);
        jRadioButton1_payType_Fuel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1_payType_Fuel.setText("Cash");
        jRadioButton1_payType_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1_payType_FuelActionPerformed(evt);
            }
        });

        buttonGroup7.add(jRadioButton2_payType_Fuel);
        jRadioButton2_payType_Fuel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2_payType_Fuel.setText("Cheque");
        jRadioButton2_payType_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2_payType_FuelActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("                                     ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("                                      ");

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(204, 0, 0));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel90.setText("                                                                   ");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(204, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel91.setText("                                         ");

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(204, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel92.setText("                                       ");

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(204, 0, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel93.setText("                                        ");

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(204, 0, 0));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel94.setText("                                                    ");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jRadioButton1_payType_Fuel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton2_payType_Fuel))
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jRadioButton1_Fuel_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton2_Fuel_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txt_BillID_Fuel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(txt_Price_Fuel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_vehicleID_Fuel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txt_amount_Fuel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_vehicleID_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(2, 2, 2)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel30))
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1_Fuel_fuel)
                        .addComponent(jRadioButton2_Fuel_fuel)))
                .addGap(4, 4, 4)
                .addComponent(jLabel94)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel44))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_amount_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel93)
                .addGap(1, 1, 1)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel18))
                    .addComponent(jDateChooser_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel92)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel32))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_BillID_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel91)
                .addGap(5, 5, 5)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel43))
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1_payType_Fuel)
                        .addComponent(jRadioButton2_payType_Fuel)))
                .addGap(2, 2, 2)
                .addComponent(jLabel90)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txt_Price_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19))
        );

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText(" Fuel Filling ");

        jPanel30.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_delete_Fuel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_delete_Fuel.setText("DELETE");
        cmd_delete_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_delete_FuelActionPerformed(evt);
            }
        });

        cmd_update_Fuel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_update_Fuel.setText("UPDATE");
        cmd_update_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_update_FuelActionPerformed(evt);
            }
        });

        cmd_clear_Fuel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_clear_Fuel.setText("REFRESH");
        cmd_clear_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_clear_FuelActionPerformed(evt);
            }
        });

        cmd_save_Fuel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_save_Fuel.setText("SAVE");
        cmd_save_Fuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_save_FuelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_update_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(cmd_save_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 30, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(cmd_delete_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(cmd_save_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmd_delete_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmd_update_Fuel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("          FUEL FILLING               ", jPanel3);

        jPanel21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel52.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel53.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_search_Mtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        cud_search_Mtn.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        cud_search_Mtn.setText("Search");
        cud_search_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cud_search_MtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_search_Mtn, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cud_search_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_search_Mtn, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(cud_search_Mtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel66.setText("Search By Bill ID");

        jPanel54.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_report_Reg3.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cmd_report_Reg3.setForeground(new java.awt.Color(204, 0, 51));
        cmd_report_Reg3.setText("REPORT");
        cmd_report_Reg3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_report_Reg3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Reg3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_delete_Mtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_delete_Mtn.setText("DELETE");
        cmd_delete_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_delete_MtnActionPerformed(evt);
            }
        });

        cmd_update_Mtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_update_Mtn.setText("UPDATE");
        cmd_update_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_update_MtnActionPerformed(evt);
            }
        });

        cmd_clear_Mtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_clear_Mtn.setText("REFRESH");
        cmd_clear_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_clear_MtnActionPerformed(evt);
            }
        });

        cmd_save_Mtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_save_Mtn.setText("SAVE");
        cmd_save_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_save_MtnActionPerformed(evt);
            }
        });

        cmd_tables_Mtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmd_tables_Mtn.setText("TABLES");
        cmd_tables_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_tables_MtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmd_update_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmd_save_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmd_clear_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmd_delete_Mtn, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(cmd_tables_Mtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_save_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmd_clear_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmd_update_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmd_delete_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmd_tables_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel44.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox2.setText("Battery and Cables");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel36.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 7, 181, -1));

        jCheckBox3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox3.setText("Timing Belts ");
        jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox3MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 33, 181, -1));

        jCheckBox4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox4.setText("Drive Belt");
        jCheckBox4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox4MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 59, 181, -1));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox1.setText("Automatic Transmission Fluid");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 85, -1, -1));

        jCheckBox6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox6.setText("Engine Oil");
        jCheckBox6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox6MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 111, 181, -1));

        jCheckBox10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox10.setText("Power Steering Fluid");
        jCheckBox10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox10MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 137, 181, -1));

        jCheckBox12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox12.setText("Windshield Washer Fluid");
        jCheckBox12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox12MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 163, 181, -1));

        jCheckBox5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox5.setText("Water Pump");
        jCheckBox5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox5MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 189, 163, -1));

        jCheckBox16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox16.setText("Engine Oil Filter");
        jPanel36.add(jCheckBox16, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 242, 111, -1));

        jCheckBox17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox17.setText("Air Filter");
        jPanel36.add(jCheckBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 242, 111, -1));

        jCheckBox18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox18.setText("A/C Filter");
        jPanel36.add(jCheckBox18, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 7, 101, -1));

        jCheckBox20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox20.setText("Deferential Oil");
        jPanel36.add(jCheckBox20, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 33, -1, -1));

        jCheckBox11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox11.setText("Tyres");
        jPanel36.add(jCheckBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 59, 101, -1));

        jCheckBox23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox23.setText("Axel Boots");
        jPanel36.add(jCheckBox23, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 85, 101, -1));

        jCheckBox24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox24.setText("C/V Joint");
        jPanel36.add(jCheckBox24, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 111, 101, -1));

        jCheckBox25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox25.setText("Cluch Plate");
        jPanel36.add(jCheckBox25, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 137, 101, -1));

        jCheckBox26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox26.setText("A/C Repair");
        jPanel36.add(jCheckBox26, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 163, 101, -1));

        jCheckBox27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox27.setText("Hone");
        jPanel36.add(jCheckBox27, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 189, 101, -1));

        jCheckBox19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox19.setText("Spark Plug");
        jPanel36.add(jCheckBox19, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 215, 101, -1));

        jCheckBox14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox14.setText("Wiper Blades");
        jCheckBox14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox14MouseClicked(evt);
            }
        });
        jPanel36.add(jCheckBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 215, 93, -1));

        jCheckBox22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox22.setText("Suspensions Bush");
        jPanel36.add(jCheckBox22, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 137, -1, -1));

        jCheckBox21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox21.setText("Break Pad");
        jPanel36.add(jCheckBox21, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 111, 121, -1));

        jCheckBox9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox9.setText("Lights");
        jPanel36.add(jCheckBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 85, 121, -1));

        jCheckBox8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox8.setText(" Hoses");
        jPanel36.add(jCheckBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 59, 121, -1));

        jCheckBox7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox7.setText("Brake Disk");
        jPanel36.add(jCheckBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 36, 121, -1));

        jCheckBox13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox13.setText("Wheel Bearing");
        jPanel36.add(jCheckBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 10, 121, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 110, -1));
        jPanel36.add(txt_select_Item_Mtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 171, -1));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Select Items");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Maintenance");

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_BillID_Mtn.setText("BID");
        txt_BillID_Mtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BillID_MtnFocusLost(evt);
            }
        });
        txt_BillID_Mtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BillID_MtnKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Date");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Service ID");

        txt_vehicleID_Mtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vehicleID_MtnFocusLost(evt);
            }
        });
        txt_vehicleID_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vehicleID_MtnActionPerformed(evt);
            }
        });
        txt_vehicleID_Mtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_vehicleID_MtnKeyPressed(evt);
            }
        });

        txt_serviceID_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_serviceID_MtnActionPerformed(evt);
            }
        });
        txt_serviceID_Mtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_serviceID_MtnKeyPressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("vehicleID");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Bill ID");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Payment Type");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Price");

        txt_Price_Mtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Price_MtnKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Price_MtnKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Description");

        ComboBox_Type_Mtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboBox_Type_Mtn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Service", "Repair" }));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Type             ");

        buttonGroup8.add(jRadioButton1_Mtn);
        jRadioButton1_Mtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1_Mtn.setText("Cash");
        jRadioButton1_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1_MtnActionPerformed(evt);
            }
        });

        buttonGroup8.add(jRadioButton2_Mtn);
        jRadioButton2_Mtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2_Mtn.setText("Cheque");
        jRadioButton2_Mtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2_MtnActionPerformed(evt);
            }
        });

        jDateChooser_Mtn.setDateFormatString("yyyy-MM-dd");
        jDateChooser_Mtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser_MtnKeyPressed(evt);
            }
        });

        jTextPane1_Mtn.setToolTipText("");
        jScrollPane4.setViewportView(jTextPane1_Mtn);

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(204, 0, 0));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel95.setText("                                                   ");

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(204, 0, 0));
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel96.setText("                                                      ");

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(204, 0, 0));
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel97.setText("                                                   ");

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(204, 0, 0));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel98.setText("                                                     ");

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(204, 0, 0));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel99.setText("                                                    ");

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(204, 0, 0));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel100.setText("                                                   ");

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(204, 0, 0));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel101.setText("                                                     ");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ComboBox_Type_Mtn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_vehicleID_Mtn, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(txt_serviceID_Mtn)))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_BillID_Mtn)
                                    .addComponent(jDateChooser_Mtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel98, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)))
                                .addGap(14, 14, 14))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel101, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel100, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jRadioButton1_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jRadioButton2_Mtn))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Price_Mtn, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txt_vehicleID_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(txt_serviceID_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(ComboBox_Type_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jDateChooser_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txt_BillID_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1_Mtn)
                    .addComponent(jRadioButton2_Mtn)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txt_Price_Mtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel100)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel101)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("         MAINTENANCE        ", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 124, 1360, 577));

        jPanel7.setLayout(null);

        txt_time.setFont(new java.awt.Font("Algerian", 1, 48)); // NOI18N
        txt_time.setForeground(new java.awt.Color(0, 0, 153));
        txt_time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rect.png"))); // NOI18N
        jPanel7.add(txt_time);
        txt_time.setBounds(30, 0, 282, 94);

        cmd_home.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_home.setText("HOME");
        cmd_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_homeActionPerformed(evt);
            }
        });

        cmd_logout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_logout.setText("LOGOUT");
        cmd_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_logoutActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 204));
        jLabel11.setText("                     Logged in as Fleet Manager");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addComponent(cmd_home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmd_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmd_logout)
                    .addComponent(cmd_home))
                .addContainerGap())
        );

        jPanel7.add(jPanel39);
        jPanel39.setBounds(1018, 13, 295, 94);

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1313, 107));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void cmd_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_logoutActionPerformed
                  new Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_cmd_logoutActionPerformed

    private void cmd_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_homeActionPerformed
                close();
                home s;                                                           
    try {
        s = new home(); //4
        s.setVisible(true); 
    } catch (Exception ex) {
        Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_cmd_homeActionPerformed

    private void cmd_delete_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_delete_InsActionPerformed
        String sql1="delete from payments where billID=?";
        String sql2="delete from vehicle_insurance where `vhicle_ID`=? AND `bil_ID`=?";
        try{          
            pst =conn.prepareStatement(sql2);
            pst.setString(1, txt_vehicleID_Ins.getText());
            pst.setString(2, txt_billID_Ins.getText());
            pst.execute();
            pst.close();
            
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_billID_Ins.getText());
            pst.execute();
            pst.close();

            JOptionPane.showMessageDialog(null, "Deleted");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                rs.close();
                pst.close();
                Update_Insurance_Table();
                clearIns();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_delete_InsActionPerformed
    
    private void cmd_update_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_update_InsActionPerformed
         if(checkIns_isEmphty()){
        try{
            String value1 = txt_vehicleID_Ins.getText();
            String value4 = txt_billID_Ins.getText();
            String value5 = payTypeIns;
            String value6 = txt_price_Ins.getText();
            String value7 = ((JTextField)DateChooser_Renew_Ins.getDateEditor().getUiComponent()).getText();
            String value8 = ((JTextField)DateChooser_nextRenew_Ins.getDateEditor().getUiComponent()).getText();
    
            String sql1="UPDATE vehicle_insurance SET   `renewDate`='"+value7+"', `nextRenewDate`='"+value8+"'  WHERE `vhicle_ID`='"+value1+"'  AND `bil_ID`='"+value4+"'";
            String sql2="UPDATE payments SET  `date`='"+value7+"', `payment_type`='"+value5+"', `price`='"+value6+"'  WHERE `vID`='"+value1+"' AND `billID`='"+value4+"'";

            pst =conn.prepareStatement(sql2);
            pst.execute();
            pst.close();
            pst =conn.prepareStatement(sql1);
            pst.execute();
             
            JOptionPane.showMessageDialog(null, "Updated");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                pst.close();
                Update_Insurance_Table();
               // clearIns();
            }catch(Exception e) {
            }
        }   
        }
    }//GEN-LAST:event_cmd_update_InsActionPerformed
 
    private void cmd_clear_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_clear_InsActionPerformed
         clearIns();
         Update_Insurance_Table();
    }//GEN-LAST:event_cmd_clear_InsActionPerformed
    
    private void cmd_save_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_save_InsActionPerformed
        if(checkIns_isEmphty()){
        String sqlIns = "INSERT INTO vehicle_insurance (`vhicle_ID`,  `bil_ID`, `renewDate`, `nextRenewDate`) values (?,?,?,?)";
        String sqlPay = "INSERT INTO payments (`vID`,`billID`, `date`, `payment_type`, `price`,`description`) values (?,?,?,?,?,'vehicle insurance renew')";
        try{
            pst=conn.prepareStatement(sqlPay);
            pst.setString(1, txt_vehicleID_Ins.getText());
            pst.setString(2, txt_billID_Ins.getText());
            pst.setString(3, ((JTextField)DateChooser_Renew_Ins.getDateEditor().getUiComponent()).getText());
            pst.setString(4, payTypeIns);
            pst.setString(5, txt_price_Ins.getText());
            pst.execute();
            pst.close();
            pst=conn.prepareStatement(sqlIns);
            pst.setString(1, txt_vehicleID_Ins.getText());
            pst.setString(2, txt_billID_Ins.getText());
            pst.setString(3, ((JTextField)DateChooser_Renew_Ins.getDateEditor().getUiComponent()).getText());
            pst.setString(4, ((JTextField)DateChooser_nextRenew_Ins.getDateEditor().getUiComponent()).getText());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Saved");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            try{
            pst.close();
            Update_Insurance_Table();
            clearIns();
            }catch(Exception e) {
            }
        }
        }
    }//GEN-LAST:event_cmd_save_InsActionPerformed
  
    private void cmd_delete_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_delete_RegActionPerformed
        String sql1="delete from payments where billID=?";
        String sql2="delete from vehicle_registration where `vehicle_ID`=? AND `bill_ID`=?";
        try{          
            pst =conn.prepareStatement(sql2);
            pst.setString(1, txt_vID_Reg.getText());
            pst.setString(2, txt_billID_Reg.getText());
            pst.execute();
            pst.close();
            
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_billID_Reg.getText());
            pst.execute();
            pst.close();

            JOptionPane.showMessageDialog(null, "Deleted");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                pst.close();
                clearReg();
               Update_Registration_Table();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_delete_RegActionPerformed
   
    private void cmd_update_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_update_RegActionPerformed
        if(checkReg_isEmphty()){
        try{
            String value1 = txt_vID_Reg.getText();
            String value3 = txt_billID_Reg.getText();
            String value4 = payTypeReg;
            String value5 = txt_price_Reg.getText();
            String value6 = ((JTextField)DateChooser_renewDate_Reg.getDateEditor().getUiComponent()).getText();
            String value7 = ((JTextField)DateChooser_nextRenewDate_Reg.getDateEditor().getUiComponent()).getText();
    
            String sql1="UPDATE vehicle_registration SET  `renewedDate`='"+value6+"', `nextRenewDate`='"+value7+"'  WHERE `vehicle_ID`='"+value1+"'  AND `bill_ID`='"+value3+"'";
            String sql2="UPDATE payments SET  `date`='"+value6+"', `payment_type`='"+value4+"', `price`='"+value5+"'  WHERE `vID`='"+value1+"' AND `billID`='"+value3+"'";

            pst =conn.prepareStatement(sql2);
            pst.execute();
            pst.close();
            pst =conn.prepareStatement(sql1);
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Updated");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                pst.close();
                Update_Registration_Table();
                clearReg();
            }catch(Exception e) {
            }
        }   
        }
    }//GEN-LAST:event_cmd_update_RegActionPerformed

    private void cmd_clear_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_clear_RegActionPerformed
        clearReg();
        Update_Registration_Table();
    }//GEN-LAST:event_cmd_clear_RegActionPerformed
   
    private void cmd_save_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_save_RegActionPerformed
        if(checkReg_isEmphty()){
        String sqlReg = "INSERT INTO vehicle_registration (`vehicle_ID`, `bill_ID`, `renewedDate`, `nextRenewDate`) values (?,?,?,?)";
        String sqlPay = "INSERT INTO payments (`vID`,`billID`, `date`, `payment_type`, `price`,`description`) values (?,?,?,?,?,'vehicle registration renew')";
        try{
            pst=conn.prepareStatement(sqlPay);
            pst.setString(1, txt_vID_Reg.getText());
            pst.setString(2, txt_billID_Reg.getText());
            pst.setString(3, ((JTextField)DateChooser_renewDate_Reg.getDateEditor().getUiComponent()).getText());
            pst.setString(4, payTypeReg);
            pst.setString(5, txt_price_Reg.getText());
            pst.execute();
            pst.close();
            pst=conn.prepareStatement(sqlReg);
            pst.setString(1, txt_vID_Reg.getText());
            pst.setString(2, txt_billID_Reg.getText());
            pst.setString(3, ((JTextField)DateChooser_renewDate_Reg.getDateEditor().getUiComponent()).getText());
            pst.setString(4, ((JTextField)DateChooser_nextRenewDate_Reg.getDateEditor().getUiComponent()).getText());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Saved");
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        finally {
            try{
            pst.close();
            Update_Registration_Table();
            clearReg();
            }catch(Exception e) {  }
        }
        }
    }//GEN-LAST:event_cmd_save_RegActionPerformed

    private void table_registrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_registrationMouseClicked
        cmd_save_Reg.setEnabled(false);
        
        try{
            int row=table_registration.getSelectedRow();
            String Table_click1=(table_registration.getModel().getValueAt(row,1).toString());
            String sql1 ="select * from vehicle_registration where bill_ID='"+Table_click1+"' ";
            String sql2 ="select * from payments where billID='"+Table_click1+"' ";
            
            pst =conn.prepareStatement(sql1);
            rs=pst.executeQuery();
            if(rs.next()){
                String add1= rs.getString("vehicle_ID");
                txt_vID_Reg.setText(add1);
                
                String add3= rs.getString("bill_ID");
                txt_billID_Reg.setText(add3);
                
                String add4= rs.getString("renewedDate");
               ((JTextField)DateChooser_renewDate_Reg.getDateEditor().getUiComponent()).setText(add4);
               
                String add5= rs.getString("nextRenewDate");
               ((JTextField)DateChooser_nextRenewDate_Reg.getDateEditor().getUiComponent()).setText(add5);                
            }   
              rs.close();
              pst.close();
              
                pst=conn.prepareStatement(sql2);
                rs=pst.executeQuery();
        
               if(rs.next()){
                    String add6= rs.getString("payment_type");
                    payTypeReg = add6;
                    if(payTypeReg.equals("cash")){
                        RadioButton1_Reg.setSelected(true);
                    }
                    else if(payTypeReg.equals("check")){
                        RadioButton2_Reg.setSelected(true);
                    }

                    String add7= rs.getString("price");
                    txt_price_Reg.setText(add7);
               }
                rs.close();
                 pst.close();  

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
               
                txt_vID_Reg.disable();
                txt_billID_Reg.disable();;
                rs.close();
                pst.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_table_registrationMouseClicked
 
    private void table_insuranceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_insuranceMouseClicked
        cmd_save_Ins.setEnabled(false);
        try{
            int row=table_insurance.getSelectedRow();
            String Table_click1=(table_insurance.getModel().getValueAt(row,1).toString());
            String sql1 ="select * from vehicle_insurance where bil_ID='"+Table_click1+"' ";
            String sql2 ="select * from payments where billID='"+Table_click1+"' ";
            
            pst =conn.prepareStatement(sql1);
            rs=pst.executeQuery();
            if(rs.next()){
            
                String add1= rs.getString("vhicle_ID");
                txt_vehicleID_Ins.setText(add1);
                
                String add4= rs.getString("bil_ID");
                txt_billID_Ins.setText(add4);
                
                String add5= rs.getString("renewDate");
               ((JTextField)DateChooser_Renew_Ins.getDateEditor().getUiComponent()).setText(add5);
               
                String add6= rs.getString("nextRenewDate");
               ((JTextField)DateChooser_nextRenew_Ins.getDateEditor().getUiComponent()).setText(add5);                
            }   
                rs.close();
               pst.close();
                pst=conn.prepareStatement(sql2);
                rs=pst.executeQuery();
        
               if(rs.next()){
                    String add7= rs.getString("payment_type");
                    payTypeIns = add7;
                    if(payTypeIns.equals("cash")){
                        jRadioButton1_Ins.setSelected(true);
                    }
                    else if(payTypeIns.equals("check")){
                        jRadioButton2_Ins.setSelected(true);
                    }

                    String add8= rs.getString("price");
                    txt_price_Ins.setText(add8); 
               }
               rs.close();
               pst.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                txt_vehicleID_Ins.disable();
                txt_billID_Ins.disable();
                rs.close();
                pst.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_table_insuranceMouseClicked
 
    private void txt_vehicleID_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vehicleID_MtnActionPerformed
   
    }//GEN-LAST:event_txt_vehicleID_MtnActionPerformed

    private void cmd_delete_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_delete_MtnActionPerformed
        String sql1="delete from payments where billID=?";
        String sql2="delete from maintenance where `vehicl_ID`=? AND `biill_ID`=?";
        try{          
            pst =conn.prepareStatement(sql2);
            pst.setString(1, txt_vehicleID_Mtn.getText());
            pst.setString(2, txt_BillID_Mtn.getText());
            pst.execute();
            pst.close();
            
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_BillID_Mtn.getText());
            
            pst.execute();
            pst.close();

          
            JOptionPane.showMessageDialog(null, "Deleted");
            //QueryJFrame s = new QueryJFrame();
            //s.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                rs.close();
                pst.close();
                
                clearMtn();
                //conn.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_delete_MtnActionPerformed

    private void cmd_update_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_update_MtnActionPerformed
        if(checkMtn_isEmphty()){
        try{
            String value1 = txt_vehicleID_Mtn.getText();
            String value2 = txt_serviceID_Mtn.getText();
            String value3 = ComboBox_Type_Mtn.getSelectedItem().toString();
            String value4 = ((JTextField)jDateChooser_Mtn.getDateEditor().getUiComponent()).getText();
            String value5 = txt_BillID_Mtn.getText();            
            String value6 = payTypeMtn;
            String value7 = txt_Price_Mtn.getText();
 
           String sql1="UPDATE maintenance SET  `type`='"+value3+"', `date`='"+value4+"' WHERE `vehicl_ID`='"+value1+"'";
           String sql2="UPDATE payments SET   `date`='"+value4+"', `payment_type`='"+value6+"', `price`='"+value7+"' WHERE `vID`='"+value1+"'";
           
            pst =conn.prepareStatement(sql2);
            pst.execute();
            pst.close();
            pst =conn.prepareStatement(sql1);
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Updated");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                rs.close();
                pst.close();
               // Update_Table_vehicle();
            }catch(Exception e) {
            }
        }
        }
    }//GEN-LAST:event_cmd_update_MtnActionPerformed
  
    private void cmd_clear_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_clear_MtnActionPerformed
        clearMtn();
    }//GEN-LAST:event_cmd_clear_MtnActionPerformed

    private void cmd_save_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_save_MtnActionPerformed
        if(checkMtn_isEmphty()){
        String str ;    
        String sqlFuel = "INSERT INTO maintenance (`vehicl_ID`, `maintainID`, `type`, `date`, `biill_ID`) values (?,?,?,?,?)";
        String sqlPay = "INSERT INTO payments (`vID`,`billID`, `date`, `payment_type`, `price`,`description`) values (?,?,?,?,?,'vehicle maintain')";
        try{
            pst=conn.prepareStatement(sqlPay);
            pst.setString(1, txt_vehicleID_Mtn.getText());
            pst.setString(2, txt_BillID_Mtn.getText());
            pst.setString(3, ((JTextField)jDateChooser_Mtn.getDateEditor().getUiComponent()).getText());
            pst.setString(4, payTypeMtn);
            pst.setString(5, txt_Price_Mtn.getText());
           // pst.setString(6, ComboBox_Type_Mtn.getSelectedItem().toString());
            pst.execute();
            
            pst=conn.prepareStatement(sqlFuel);
            pst.setString(1, txt_vehicleID_Mtn.getText());
            pst.setString(2, txt_serviceID_Mtn.getText());
            pst.setString(3, ComboBox_Type_Mtn.getSelectedItem().toString());
            pst.setString(4, ((JTextField)jDateChooser_Mtn.getDateEditor().getUiComponent()).getText());
    //        pst.setString(5, jTextPane1_Mtn.getText());
            pst.setString(5, txt_BillID_Mtn.getText());
            pst.execute();
            //pst.close();
        ListModel lm =  jTextPane1_Mtn.getModel();
        String arr[] = new String[lm.getSize()];
        for(int i =0 ; i<lm.getSize(); i++){
            arr[i] = (String)lm.getElementAt(i);
        }
        for(int i=0 ; i<lm.getSize();i++ ){
            String sqlmaintain = "INSERT INTO maintain_description (`biill_ID`, `date`, `Descript`) values (?,?,?)";
            pst=conn.prepareStatement(sqlmaintain);
            pst.setString(1, txt_BillID_Mtn.getText());
            pst.setString(2, ((JTextField)jDateChooser_Mtn.getDateEditor().getUiComponent()).getText());
            pst.setString(3, arr[i]);
            pst.execute();
        }
        JOptionPane.showMessageDialog(null, "Saved");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            try{
            pst.close();
                // conn.close();
            //Update_FuelFilling_Table();
           // clearReg();
            }catch(Exception e) {
            }
        }
        }   
    }//GEN-LAST:event_cmd_save_MtnActionPerformed

    private void table_FuelfillingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_FuelfillingMouseClicked
        cmd_save_Fuel.setEnabled(false);
        try{
            int row=table_Fuelfilling.getSelectedRow();
            String Table_click1=(table_Fuelfilling.getModel().getValueAt(row,4).toString());
            //String Table_click2=(table_registration.getModel().getValueAt(row,2).toString());
            String sql1 ="select * from fuel_filling where billl_id='"+Table_click1+"' ";
            String sql2 ="select * from payments where billID='"+Table_click1+"' ";
            
            pst =conn.prepareStatement(sql1);
            rs=pst.executeQuery();
            if(rs.next()){
            
                String add1= rs.getString("vehi_ID");
                txt_vehicleID_Fuel.setText(add1);
                
                String add2= rs.getString("fuelType");
                TypeFuel = add2;
                    if(TypeFuel.equals("Petrol")){
                        jRadioButton1_Fuel_fuel.setSelected(true);
                    }
                    else if(TypeFuel.equals("Diesel")){
                        jRadioButton2_Fuel_fuel.setSelected(true);
                    }
                
                String add3= rs.getString("liters");
                txt_amount_Fuel.setText(add3);
                
                String add4= rs.getString("date");
               ((JTextField)jDateChooser_Fuel.getDateEditor().getUiComponent()).setText(add4);
               
                String add5= rs.getString("billl_id");
                txt_BillID_Fuel.setText(add5);              
            }   
                rs.close();
              //  pst.close();
                
                ResultSet rs1;  
                pst=conn.prepareStatement(sql2);
                rs1=pst.executeQuery();
        
               if(rs1.next()){
                    String add6= rs1.getString("payment_type");
                    payTypeFuel = add6;
                    if(payTypeFuel.equals("cash")){
                        jRadioButton1_payType_Fuel.setSelected(true);
                    }
                    else if(payTypeFuel.equals("check")){
                        jRadioButton2_payType_Fuel.setSelected(true);
                    }

                    String add7= rs1.getString("price");
                    txt_Price_Fuel.setText(add7);
                    pst.close();  
               }
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                txt_vehicleID_Fuel.disable();
                //txt_InsNum_Ins.disable();
                txt_BillID_Fuel.disable();
                jRadioButton1_Fuel_fuel.disable();
                jRadioButton2_Fuel_fuel.disable();
                rs.close();
                pst.close();
                //conn.close();
            }catch(Exception e) {
            }
        }
                                
    }//GEN-LAST:event_table_FuelfillingMouseClicked

    private void txt_vehicleID_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vehicleID_FuelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vehicleID_FuelActionPerformed

    private void cmd_delete_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_delete_FuelActionPerformed
        String sql1="delete from payments where billID=?";
        String sql2="delete from fuel_filling where `vehi_ID`=? AND `billl_id`=?";
        try{          
            pst =conn.prepareStatement(sql2);
            pst.setString(1, txt_vehicleID_Fuel.getText());
            pst.setString(2, txt_BillID_Fuel.getText());
            pst.execute();
            pst.close();
            
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_BillID_Fuel.getText());
            
            pst.execute();
            pst.close();

            JOptionPane.showMessageDialog(null, "Deleted");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                rs.close();
                pst.close();
                Update_FuelFilling_Table();
                clearFuel();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_delete_FuelActionPerformed

    private void cmd_update_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_update_FuelActionPerformed
       if(checkFuel_isEmphty()){
        try{
            String value1 = txt_vehicleID_Fuel.getText();
            String value2 = TypeFuel;
            String value3 = txt_amount_Fuel.getText();
            String value4 = ((JTextField)jDateChooser_Fuel.getDateEditor().getUiComponent()).getText();
            String value5 = txt_BillID_Fuel.getText();
            String value6 = payTypeFuel;
            String value7 = txt_Price_Fuel.getText();
    
            String sql1="UPDATE fuel_filling SET `fuelType`='"+value2+"', `liters`='"+value3+"', `date`='"+value4+"', `billl_id`='"+value5+"'  WHERE `vehi_ID`='"+value1+"'";
            String sql2="UPDATE payments SET `vID`='"+value1+"', `date`='"+value4+"', `payment_type`='"+value6+"', `price`='"+value7+"'  WHERE `vID`='"+value1+"' AND `billID`='"+value5+"'";

            pst =conn.prepareStatement(sql2);
            pst.execute();
            pst.close();
            pst =conn.prepareStatement(sql1);
            pst.execute();
             
            JOptionPane.showMessageDialog(null, "Updated");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                rs.close();
                pst.close();
                Update_FuelFilling_Table();
               // clearReg();
            }catch(Exception e) {
            }
        }
       }
    }//GEN-LAST:event_cmd_update_FuelActionPerformed
    
    private void cmd_clear_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_clear_FuelActionPerformed
       clearFuel();
       Update_FuelFilling_Table();
    }//GEN-LAST:event_cmd_clear_FuelActionPerformed

    private void cmd_save_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_save_FuelActionPerformed
        if(checkFuel_isEmphty()){
        String sqlFuel = "INSERT INTO fuel_filling (`vehi_ID`, `fuelType`, `liters`, `date`, `billl_id`) values (?,?,?,?,?)";
        String sqlPay = "INSERT INTO payments (`vID`,`billID`, `date`, `payment_type`, `price`,`description`) values (?,?,?,?,?,'Vehicle Fuel Filling')";
        try{
            pst=conn.prepareStatement(sqlPay);
            pst.setString(1, txt_vehicleID_Fuel.getText());
            pst.setString(2, txt_BillID_Fuel.getText());
            pst.setString(3, ((JTextField)jDateChooser_Fuel.getDateEditor().getUiComponent()).getText());
            pst.setString(4, payTypeFuel);
            pst.setString(5, txt_Price_Fuel.getText());
            pst.execute();
            
            pst=conn.prepareStatement(sqlFuel);
            pst.setString(1, txt_vehicleID_Fuel.getText());
            pst.setString(2, TypeFuel);
            pst.setString(3, txt_amount_Fuel.getText());
            pst.setString(4, ((JTextField)jDateChooser_Fuel.getDateEditor().getUiComponent()).getText());
            pst.setString(5, txt_BillID_Fuel.getText());
            pst.execute();
            //pst.close();
            
            JOptionPane.showMessageDialog(null, "Saved");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            try{
            pst.close();
            Update_FuelFilling_Table();
            }catch(Exception e) {
            }
        }
       }  
    }//GEN-LAST:event_cmd_save_FuelActionPerformed
   
    private void txt_price_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_price_RegActionPerformed
        jLabel83.setText("                        ");
    }//GEN-LAST:event_txt_price_RegActionPerformed
     
    private void txt_price_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_price_InsActionPerformed
      
    }//GEN-LAST:event_txt_price_InsActionPerformed
   
    private void txt_serviceID_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_serviceID_MtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_serviceID_MtnActionPerformed
    
    private void txt_vehicleID_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vehicleID_AddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vehicleID_AddActionPerformed

    private void cmd_clear_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_clear_AddActionPerformed
       clearVehicleAdd();        
    }//GEN-LAST:event_cmd_clear_AddActionPerformed

    private void cmd_delete_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_delete_AddActionPerformed
        String sql1 = "delete from vehicle_insurance where vhicle_ID=? ";
        String sql2 = "delete from vehicle_registration where vehicle_ID=? ";
        String sql3 = "delete from payments where vID=? ";
        String sql4 = "delete from fuel_filling where vehi_ID=? ";
        String sql="delete from vehicle where vehicleID=?";
        try{
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_vehicleID_Add.getText());
            pst.execute();
            pst.close();

            pst =conn.prepareStatement(sql2);
            pst.setString(1, txt_vehicleID_Add.getText());
            pst.execute();
            pst.close();

            pst =conn.prepareStatement(sql4);
            pst.setString(1, txt_vehicleID_Add.getText());
            pst.execute();
            pst.close();
            
            pst =conn.prepareStatement(sql3);
            pst.setString(1, txt_vehicleID_Add.getText());
            pst.execute();
            pst.close();

            pst =conn.prepareStatement(sql);
            pst.setString(1, txt_vehicleID_Add.getText());
            pst.execute();
            pst.close();

            JOptionPane.showMessageDialog(null, "Deleted");
         
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                clearVehicleAdd();
                pst.close();
                Update_Insurance_Table();
                Update_Registration_Table();
                Update_FuelFilling_Table();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_delete_AddActionPerformed
   
    private void cmd_update_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_update_AddActionPerformed
        if(checkAdd_isEmphty()){
        try{
            String value1 = txt_vehicleID_Add.getText();
            String value2 = ComboBox_Type_Add.getSelectedItem().toString();
            String value3 = txt_model_Add.getText();
            String value4 = txt_chassicNo_Add.getText();
            String value6 = txt_Colour_Add.getText();
            String value7 = Transmission;
            String value8 = fuelType;
            String value9 = status;
            String value10 = txt_GPS_Add.getText();
            String value11 = txt_RegNum_Add.getText();
            String value12 = ((JTextField)DateChooser_RegDate_Add.getDateEditor().getUiComponent()).getText();
            String value13 = txt_InsNum_Add.getText();
            String value14 = txt_InsCompany_Add.getText();
            String value15 = ((JTextField)DateChooser_InsDate_Add.getDateEditor().getUiComponent()).getText();
            //byte[] value16 = vehicle_img;
           String sql="UPDATE vehicle SET `vehicleID`='"+value1+"', `type`='"+value2+"', `model`='"+value3+"', `chassicNo`='"+value4+"',  `colour`='"+value6+"', `transmissionType`='"+value7+"', `fuelType`='"+value8+"', `status`='"+value9+"', `GPS`='"+value10+"', `RegNum`='"+value11+"', `RegDate`='"+value12+"',`InsNum`='"+value13+"', `Ins_Company`='"+value14+"', `InsDate`='"+value15+"'  WHERE `vehicleID`='"+value1+"'";
            pst =conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
             clearVehicleAdd();
                pst.close();
            }catch(Exception e) {
            }
        }
        }
    }//GEN-LAST:event_cmd_update_AddActionPerformed
   
    private boolean isemphty(){
    if(!validation.isEmphtyCheck(txt_model_Add.getText())){
            jLabel67.setText("Model should be filled");
            return false;
    }else if(!validation.isEmphtyCheck(txt_chassicNo_Add.getText())){
            jLabel48.setText("Chassic No should be filled");
            return true;
    }else if(!validation.isEmphtyCheck(txt_Colour_Add.getText())){
            jLabel68.setText("Colour should be filled");
            return true;}
    else{ return false;}
}    

    private void cmd_save_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_save_AddActionPerformed
        if(checkAdd_isEmphty()){
        String sql = "INSERT INTO vehicle (`vehicleID`, `type`, `model`, `chassicNo`, `colour`, `transmissionType`, `fuelType`, `status`, `GPS`, `RegNum`, `RegDate`,`InsNum`, `Ins_Company`, `InsDate`, `PrifPic`,`mileage`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'0')";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, txt_vehicleID_Add.getText());
            pst.setString(2, ComboBox_Type_Add.getSelectedItem().toString());
            pst.setString(3, txt_model_Add.getText());
            pst.setString(4, txt_chassicNo_Add.getText());
            pst.setString(5, txt_Colour_Add.getText());
            pst.setString(6, Transmission);
            pst.setString(7, fuelType);
            pst.setString(8, status);
            pst.setString(9, txt_GPS_Add.getText());
            pst.setString(10, txt_RegNum_Add.getText());
            pst.setString(11, ((JTextField)DateChooser_RegDate_Add.getDateEditor().getUiComponent()).getText());
            pst.setString(12, txt_InsNum_Add.getText());
            pst.setString(13, txt_InsCompany_Add.getText());
            pst.setString(14, ((JTextField)DateChooser_InsDate_Add.getDateEditor().getUiComponent()).getText());
            pst.setBytes(15, vehicle_img);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Saved");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                pst.close();
                clearVehicleAdd();
                }catch(Exception e) {
            }
        }
        }     
    }//GEN-LAST:event_cmd_save_AddActionPerformed
   
    private void cmd_report_Reg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_report_Reg1ActionPerformed
        try{
            String report ="src\\Fleet\\first_report1.jrxml";
            JasperDesign jd=JRXmlLoader.load("src\\Fleet\\report3vehicleInsurence.jrxml");
            String sql = "SELECT `vehicle_insurance`.vhicle_ID, `vehicle_insurance`.bil_ID, DATE_FORMAT(`vehicle_insurance`.renewDate,'%Y-%m-%d'),DATE_FORMAT(`vehicle_insurance`.nextRenewDate,'%Y-%m-%d') FROM vehicle_insurance ORDER BY vhicle_ID";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_report_Reg1ActionPerformed
    
    private void cmd_report_Reg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_report_Reg2ActionPerformed
        try{
            String report ="src\\Fleet\\first_report1.jrxml";
            JasperDesign jd=JRXmlLoader.load("src\\Fleet\\fuelFilling.jrxml");
            String sql = "SELECT `fuel_filling`.vehi_ID, `fuel_filling`.fuelType, `fuel_filling`.liters, DATE_FORMAT(`fuel_filling`.date, '%Y-%m-%d'), `fuel_filling`.billl_id FROM fuel_filling ORDER BY vehi_ID";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_report_Reg2ActionPerformed
    
    private void RadioButton_Transmission_1_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_Transmission_1_AddActionPerformed
        Transmission = "Auto";
         jLabel76.setText("                  ");
    }//GEN-LAST:event_RadioButton_Transmission_1_AddActionPerformed
   
    private void RadioButton_Transmission_2_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_Transmission_2_AddActionPerformed
        Transmission = "Manual";
        jLabel76.setText("                  ");
    }//GEN-LAST:event_RadioButton_Transmission_2_AddActionPerformed
   
    private void RadioButton_Fuel_1_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_Fuel_1_AddActionPerformed
        fuelType = "Petrol"; 
        jLabel77.setText("                    ");
    }//GEN-LAST:event_RadioButton_Fuel_1_AddActionPerformed
    
    private void RadioButton_Fuel_2_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_Fuel_2_AddActionPerformed
        fuelType = "Diesel";
        jLabel77.setText("                    ");
    }//GEN-LAST:event_RadioButton_Fuel_2_AddActionPerformed
    
    private void RadioButton_status_1_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_status_1_AddActionPerformed
        status = "active";
        jLabel78.setText("                    ");
    }//GEN-LAST:event_RadioButton_status_1_AddActionPerformed
   
    private void RadioButton_status_2_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_status_2_AddActionPerformed
        status = "not-active";
        jLabel78.setText("                    ");
    }//GEN-LAST:event_RadioButton_status_2_AddActionPerformed
   
    private void cmd_Search_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_Search_AddActionPerformed
        if(!validation.isEmphtyCheck(search_Add.getText()))
           JOptionPane.showMessageDialog(null, "Add Vehicle ID");
        else{
        try{
            String sql ="select * from vehicle where vehicleID=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, search_Add.getText());
            rs=pst.executeQuery();
            if(rs.next()){
		String add1= rs.getString("vehicleID");
                txt_vehicleID_Add.setText(add1);
                
                String add2= rs.getString("type");
                ComboBox_Type_Add.setSelectedItem(add2);
                
                String add3= rs.getString("model");
                txt_model_Add.setText(add3);
                
                String add4= rs.getString("chassicNo");
                txt_chassicNo_Add.setText(add4);
                
                String add6= rs.getString("colour");
                txt_Colour_Add.setText(add6);
                
                String add7= rs.getString("transmissionType");
                Transmission = add7;
                if(Transmission.equals("Auto")){
                    RadioButton_Transmission_1_Add.setSelected(true);
                }
                else if(Transmission.equals("Manual")){
                    RadioButton_Transmission_2_Add.setSelected(true);
                }

                String add8= rs.getString("fuelType");
                fuelType = add8;
                if(fuelType.equals("Petrol")){
                    RadioButton_Fuel_1_Add.setSelected(true);
                }
                else if(fuelType.equals("Diesel")){
                    RadioButton_Fuel_2_Add.setSelected(true);
                }
                
                String add9= rs.getString("status");
                status = add9;
                if(status.equals("active")){
                    RadioButton_status_1_Add.setSelected(true);
                }
                else if(status.equals("not-active")){
                    RadioButton_status_2_Add.setSelected(true);
                }
                
                String add10= rs.getString("GPS");
                txt_GPS_Add.setText(add10);
                
                String add11= rs.getString("RegNum");
                txt_RegNum_Add.setText(add11);
                
                String add12= rs.getString("RegDate");
               ((JTextField)DateChooser_RegDate_Add.getDateEditor().getUiComponent()).setText(add12);
        
                String add13= rs.getString("InsNum");
                txt_InsNum_Add.setText(add13);
                
                String add14= rs.getString("Ins_Company");
                txt_InsCompany_Add.setText(add14);
                
                String add15= rs.getString("InsDate");
                ((JTextField)DateChooser_InsDate_Add.getDateEditor().getUiComponent()).setText(add15);
        
                byte[]add16 = rs.getBytes("PrifPic");
                format = new ImageIcon(add16);
                txt_profPic_add.setIcon(format);        
                
            }else{
                clearVehicleAdd();  
                JOptionPane.showMessageDialog(null, "Wrong Vehicle ID");
            }
        
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                txt_vehicleID_Add.disable();
                txt_chassicNo_Add.disable();
                txt_RegNum_Add.disable();
                txt_InsNum_Add.disable();
                ComboBox_Type_Add.disable();
                txt_model_Add.disable();
                ((JTextField)DateChooser_RegDate_Add.getDateEditor().getUiComponent()).disable();
                ((JTextField)DateChooser_InsDate_Add.getDateEditor().getUiComponent()).disable();
            }catch(Exception e) {
            }
        }
        }
        
    }//GEN-LAST:event_cmd_Search_AddActionPerformed
   
    private void cmd_AttachPic_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_AttachPic_AddActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        txt_path_Add.setText(filename);
        
        try{
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
             byte[] buf = new byte[1024];
             
             for(int  readNum; (readNum = fis.read(buf)) != -1;){
                 bos.write(buf,0,readNum);
             }
             vehicle_img = bos.toByteArray();
        }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_cmd_AttachPic_AddActionPerformed
   
    private void RadioButton1_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton1_RegActionPerformed
        payTypeReg = "cash";
          jLabel82.setText("                        ");
    }//GEN-LAST:event_RadioButton1_RegActionPerformed
   
    private void RadioButton2_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton2_RegActionPerformed
        payTypeReg = "check";
        jLabel82.setText("                        ");
    }//GEN-LAST:event_RadioButton2_RegActionPerformed
    
    private void cmd_vehicleID_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_vehicleID_RegActionPerformed
        table_registration.setBackground((Color.GRAY)); 
        table_registration.setForeground(Color.WHITE);
        if(!validation.isEmphtyCheck(txt_search_Reg.getText()))
           JOptionPane.showMessageDialog(null, "Add Vehicle ID");
        else{
          try{
            String sql1 ="select * from vehicle_registration where vehicle_ID=? ";
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_search_Reg.getText());
            rs=pst.executeQuery();
            if(rs.next()){  
                        String sql2="Select  billID,date,payment_type,price   from payments where vID=? AND description='vehicle registration renew'";  
                      
                        pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
                        pst.setString(1, txt_search_Reg.getText());
                         rss=pst.executeQuery();                      //execute a query and retrive the result.
                        table_registration.setModel(DbUtils.resultSetToTableModel(rss));  
                        rss.close();                                             
                        pst.close(); 
                        pst =conn.prepareStatement(sql2);            //carry SQL query to the DB.  
                        pst.setString(1, txt_search_Reg.getText());
                        rss=pst.executeQuery();                      //execute a query and retrive the result.
                        table_payment.setModel(DbUtils.resultSetToTableModel(rss));     
                         rss.close();                                             
                        pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "Invalid vehicle ID !!!");
            }  
          }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
              rs.close();
              pst.close();
            }catch(Exception e) {
            }
        }
       } 
    }//GEN-LAST:event_cmd_vehicleID_RegActionPerformed

    private void txt_vID_RegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vID_RegKeyReleased

    }//GEN-LAST:event_txt_vID_RegKeyReleased
 
    private void DateChooser_renewDate_RegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DateChooser_renewDate_RegKeyReleased

    }//GEN-LAST:event_DateChooser_renewDate_RegKeyReleased
   
    private void DateChooser_renewDate_RegMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DateChooser_renewDate_RegMouseEntered

    }//GEN-LAST:event_DateChooser_renewDate_RegMouseEntered

    private void txt_price_RegKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_price_RegKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_price_RegKeyTyped
   
    private void jRadioButton1_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1_InsActionPerformed
        payTypeIns = "cash";     
        jLabel86.setText("                ");
    }//GEN-LAST:event_jRadioButton1_InsActionPerformed
    
    private void jRadioButton2_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2_InsActionPerformed
        payTypeIns = "check";
        jLabel86.setText("                ");
    }//GEN-LAST:event_jRadioButton2_InsActionPerformed

    private void txt_vehicleID_InsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleID_InsKeyReleased

    }//GEN-LAST:event_txt_vehicleID_InsKeyReleased
   
    private void cmd_search_InsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_search_InsActionPerformed
        table_insurance.setBackground((Color.GRAY)); 
        table_insurance.setForeground(Color.WHITE);
        if(!validation.isEmphtyCheck(txt_search_Ins.getText()))
           JOptionPane.showMessageDialog(null, "Add Vehicle ID");
        else{
          try{
            String sql1 ="Select  *   from vehicle_insurance where vhicle_ID=?";
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_search_Ins.getText());
            rs=pst.executeQuery();
            if(rs.next()){  
                        //String sql1="Select  *   from vehicle_registration where vehicle_ID=?";  
                        String sql2="Select  billID,date,payment_type,price   from payments where vID=? AND description='vehicle insurance renew'";  
                      
                        pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
                        pst.setString(1, txt_search_Ins.getText());
                         rss=pst.executeQuery();                      //execute a query and retrive the result.
                        table_insurance.setModel(DbUtils.resultSetToTableModel(rss));  
                        rss.close();                                             
                        pst.close(); 
                        pst =conn.prepareStatement(sql2);            //carry SQL query to the DB.  
                        pst.setString(1, txt_search_Ins.getText());
                        rss=pst.executeQuery();                      //execute a query and retrive the result.
                        table_insurance_payment.setModel(DbUtils.resultSetToTableModel(rss));     
                         rss.close();                                             
                        pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "Invalid vehicle ID !!!");
            }  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                 
              rs.close();
              pst.close();
            }catch(Exception e) {
            }
        }
       }     
    }//GEN-LAST:event_cmd_search_InsActionPerformed
   
    private void jRadioButton1_Fuel_fuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1_Fuel_fuelActionPerformed
         TypeFuel = "Petrol";    
         jLabel94.setText("                ");
    }//GEN-LAST:event_jRadioButton1_Fuel_fuelActionPerformed
    
    private void jRadioButton2_Fuel_fuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2_Fuel_fuelActionPerformed
        TypeFuel = "Diesel";
        jLabel94.setText("                ");
    }//GEN-LAST:event_jRadioButton2_Fuel_fuelActionPerformed
    
    private void jRadioButton1_payType_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1_payType_FuelActionPerformed
        payTypeFuel = "cash";
          jLabel90.setText("                ");
    }//GEN-LAST:event_jRadioButton1_payType_FuelActionPerformed
    
    private void jRadioButton2_payType_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2_payType_FuelActionPerformed
        payTypeFuel = "check";
          jLabel90.setText("                ");
    }//GEN-LAST:event_jRadioButton2_payType_FuelActionPerformed
   
    private void cud_search_FuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cud_search_FuelActionPerformed
       table_Fuelfilling.setBackground((Color.GRAY)); 
        table_Fuelfilling.setForeground(Color.WHITE);

        if(!validation.isEmphtyCheck(txt_search_Fuel.getText()))
           JOptionPane.showMessageDialog(null, "Add Vehicle ID");
        else{
            try{
            String sql1 ="Select  *   from fuel_filling where vehi_ID=?";
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_search_Fuel.getText());
            rs=pst.executeQuery();
            if(rs.next()){  
                        //String sql1="Select  *   from vehicle_registration where vehicle_ID=?";  
                        String sql2="Select  billID,date,payment_type,price   from payments where vID=? AND description='Vehicle Fuel Filling'";  
                      
                        pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
                        pst.setString(1, txt_search_Fuel.getText());
                         rss=pst.executeQuery();                      //execute a query and retrive the result.
                        table_Fuelfilling.setModel(DbUtils.resultSetToTableModel(rss));  
                        rss.close();                                             
                        pst.close(); 
                        pst =conn.prepareStatement(sql2);            //carry SQL query to the DB.  
                        pst.setString(1, txt_search_Fuel.getText());
                        rss=pst.executeQuery();                      //execute a query and retrive the result.
                        table_payment_Fuelfilling.setModel(DbUtils.resultSetToTableModel(rss));     
                         rss.close();                                             
                        pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "Invalid vehicle ID !!!");
            }                                             
 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{                                                       
            rs.close();                                             
            pst.close();                                           
            //conn.close();                                       
        }catch(Exception e) {                                     
        }                                                         
    }   
        }
    }//GEN-LAST:event_cud_search_FuelActionPerformed

    private void txt_vehicleID_FuelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleID_FuelKeyReleased
         try{
           String sq1 ="select * from vehicle where vehicleID=?";
            pst=conn.prepareStatement(sq1);
            pst.setString(1, txt_vehicleID_Fuel.getText());
            rs=pst.executeQuery();
            if(rs.next()){
		String add1= rs.getString("fuelType");
                
                TypeFuel = add1;
                    if(TypeFuel.equals("Petrol")){
                        jRadioButton1_Fuel_fuel.setSelected(true);
                    }
                    else if(TypeFuel.equals("Diesel")){
                        jRadioButton2_Fuel_fuel.setSelected(true);
                    }

            }else{
                 buttonGroup6.clearSelection();   
            }
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                rs.close();
              pst.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_txt_vehicleID_FuelKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        vehicleTable vt;                                                           
    try {
        vt = new vehicleTable(); //4
          vt.setVisible(true);
    } catch (Exception ex) {
        Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cud_search_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cud_search_MtnActionPerformed
        
        if(!validation.isEmphtyCheck(txt_search_Mtn.getText()))
           JOptionPane.showMessageDialog(null, "Add Bill ID");
        else{
        
        try{
            cmd_save_Mtn.setEnabled(false);
            String sql1 ="select * from maintenance where biill_ID=?";
            pst=conn.prepareStatement(sql1);
            pst.setString(1, txt_search_Mtn.getText());
            rs=pst.executeQuery();
            if(rs.next()){  
                String add1= rs.getString("vehicl_ID");
                txt_vehicleID_Mtn.setText(add1);
                
                String add2= rs.getString("maintainID");
                txt_serviceID_Mtn.setText(add2);
                
                String add3= rs.getString("type");
                ComboBox_Type_Mtn.setSelectedItem(add3);
                
                 String add12= rs.getString("date");
               ((JTextField)jDateChooser_Mtn.getDateEditor().getUiComponent()).setText(add12);
                
                String add6= rs.getString("biill_ID");
                txt_BillID_Mtn.setText(add6);
            
                String sql2 ="select * from payments where billID=?";
                pst=conn.prepareStatement(sql2);
                pst.setString(1, txt_BillID_Mtn.getText());
                rss=pst.executeQuery();
                if(rss.next()){
        
                    String add7= rss.getString("payment_type");
                    payTypeMtn = add7;
                    if(payTypeMtn.equals("cash")){
                        jRadioButton1_Mtn.setSelected(true);
                    }
                    else if(payTypeMtn.equals("check")){
                        jRadioButton2_Mtn.setSelected(true);
                    }

                    String add10= rss.getString("price");
                    txt_Price_Mtn.setText(add10);
                }rss.close();
            
                String sql3 ="select * from maintain_description where biill_ID=?";
                pst=conn.prepareStatement(sql3);
                pst.setString(1, txt_search_Mtn.getText());
                rss=pst.executeQuery();
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Bill ID !!!");
            }     
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                txt_vehicleID_Mtn.disable();
                txt_BillID_Mtn.disable();
                txt_serviceID_Mtn.disable();
                rs.close();
            }catch(Exception e) {
            }
        } 
         }
    }//GEN-LAST:event_cud_search_MtnActionPerformed

    private void cmd_report_Reg3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_report_Reg3ActionPerformed
          try{
             //String report ="C:\\Users\\chami\\OneDrive\\Documents\\NetBeansProjects\\proj_copy\\src\\VehicleMaintenance.jrxml";
            JasperDesign jd=JRXmlLoader.load("src\\Fleet\\VehicleMaintenance.jrxml");
           // String sq = "select `maintenance`.vehicl_ID,`maintenance`.maintainID, `maintenance`.type, `maintain_description`.biill_ID, `maintain_description`.date, `maintain_description`.Descript FROM `maintenance` ,`maintain_description` WHERE `maintenance`.biill_ID = `maintain_description`.biill_ID";
            String sql =" select `maintenance`.vehicl_ID,`maintenance`.maintainID, `maintenance`.type, `maintain_description`.biill_ID, DATE_FORMAT(`maintain_description`.date,'%Y-%m-%d'), `maintain_description`.Descript FROM `maintenance` ,`maintain_description` WHERE `maintenance`.biill_ID = `maintain_description`.biill_ID";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp , false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_report_Reg3ActionPerformed

    private void jRadioButton1_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1_MtnActionPerformed
         payTypeMtn = "cash";
         jLabel99.setText("         "); 
    }//GEN-LAST:event_jRadioButton1_MtnActionPerformed

    private void jRadioButton2_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2_MtnActionPerformed
         payTypeMtn = "check";
         jLabel99.setText("         "); 
    }//GEN-LAST:event_jRadioButton2_MtnActionPerformed

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseClicked
        
    }//GEN-LAST:event_jCheckBox3MouseClicked

    private void jCheckBox4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox4MouseClicked
     
    }//GEN-LAST:event_jCheckBox4MouseClicked

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
     
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox6MouseClicked
     
    }//GEN-LAST:event_jCheckBox6MouseClicked

    private void jCheckBox10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox10MouseClicked
    
    }//GEN-LAST:event_jCheckBox10MouseClicked

    private void jCheckBox12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox12MouseClicked
  
    }//GEN-LAST:event_jCheckBox12MouseClicked

    private void jCheckBox5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox5MouseClicked
    
    }//GEN-LAST:event_jCheckBox5MouseClicked

    private void jCheckBox14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox14MouseClicked
        String str = "";
     //   if(jCheckBox14.isSelected()){
      //      jTextPane_Mtn.setText(str.concat());
       //     jTextPane_Mtn.setText("");
       // }
    }//GEN-LAST:event_jCheckBox14MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      if(jCheckBox1.isSelected())
        addTextPane(jCheckBox1.getText());
      if(jCheckBox2.isSelected())
        addTextPane(jCheckBox2.getText());
      if(jCheckBox3.isSelected())
        addTextPane(jCheckBox3.getText());
      if(jCheckBox4.isSelected())
        addTextPane(jCheckBox4.getText());
      if(jCheckBox5.isSelected())
        addTextPane(jCheckBox5.getText());
      if(jCheckBox10.isSelected())
        addTextPane(jCheckBox10.getText());
      if(jCheckBox6.isSelected())
        addTextPane(jCheckBox6.getText());
      if(jCheckBox12.isSelected())
        addTextPane(jCheckBox12.getText());
      if(jCheckBox5.isSelected())
        addTextPane(jCheckBox5.getText());
      if(jCheckBox14.isSelected())
        addTextPane(jCheckBox16.getText());     
      if(jCheckBox18.isSelected())
        addTextPane(jCheckBox18.getText());
      if(jCheckBox20.isSelected())
        addTextPane(jCheckBox20.getText());
      if(jCheckBox11.isSelected())
        addTextPane(jCheckBox11.getText());
      if(jCheckBox23.isSelected())
        addTextPane(jCheckBox23.getText());
      if(jCheckBox24.isSelected())
        addTextPane(jCheckBox24.getText());
      if(jCheckBox25.isSelected())
        addTextPane(jCheckBox25.getText());
      if(jCheckBox26.isSelected())
        addTextPane(jCheckBox26.getText());
      if(jCheckBox27.isSelected())
        addTextPane(jCheckBox27.getText());
      if(jCheckBox19.isSelected())
        addTextPane(jCheckBox19.getText());
      if(jCheckBox17.isSelected())
        addTextPane(jCheckBox17.getText());      
       if(jCheckBox13.isSelected())
        addTextPane(jCheckBox13.getText());
      if(jCheckBox7.isSelected())
        addTextPane(jCheckBox7.getText());
      if(jCheckBox8.isSelected())
        addTextPane(jCheckBox8.getText());
      if(jCheckBox9.isSelected())
        addTextPane(jCheckBox9.getText());
      if(jCheckBox21.isSelected())
        addTextPane(jCheckBox21.getText());
      if(jCheckBox22.isSelected())
        addTextPane(jCheckBox22.getText());
      if(  !txt_select_Item_Mtn.getText().isEmpty()){
          addTextPane(txt_select_Item_Mtn.getText());
      }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmd_tables_MtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_tables_MtnActionPerformed
         maintainTable mt;                                                           
    try {
        mt = new maintainTable(); //4
          mt.setVisible(true);
    } catch (Exception ex) {
        Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }//GEN-LAST:event_cmd_tables_MtnActionPerformed

    private void ComboBox_Type_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_Type_AddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBox_Type_AddActionPerformed

    private void txt_vehicleID_AddFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vehicleID_AddFocusLost
        if(txt_vehicleID_Add.getText().length() != 0 && !validation.isVehicleName(txt_vehicleID_Add.getText()))
            jLabel65.setText("Invalid Vehicle ID !!!");
        else
            jLabel65.setText("                   ");
          // JOptionPane.showMessageDialog(null, "Invalid Vehicle ID"); 
           //getToolkit().beep();
        
 
        
    }//GEN-LAST:event_txt_vehicleID_AddFocusLost

    private void txt_vehicleID_AddKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleID_AddKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vehicleID_AddKeyTyped

    private void txt_vID_RegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vID_RegFocusLost
       if(txt_vID_Reg.getText().length() != 0){
        try{
            String sql1 ="select * from vehicle where vehicleID=? ";
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_vID_Reg.getText());
            rs=pst.executeQuery();
            if(rs.next()){             
            }else{
                jLabel79.setText("Invalid vehicle ID !!!");
            }   
              rs.close();
              pst.close();
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
       }
    }//GEN-LAST:event_txt_vID_RegFocusLost

    private void txt_vehicleID_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleID_AddKeyPressed
        jLabel65.setText("                    ");
    }//GEN-LAST:event_txt_vehicleID_AddKeyPressed

    private void txt_model_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_model_AddKeyPressed
         jLabel67.setText("                       ");
    }//GEN-LAST:event_txt_model_AddKeyPressed

    private void txt_chassicNo_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_chassicNo_AddKeyPressed
            jLabel48.setText("                      ");
    }//GEN-LAST:event_txt_chassicNo_AddKeyPressed

    private void txt_Colour_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Colour_AddKeyPressed
         jLabel68.setText("                      ");
    }//GEN-LAST:event_txt_Colour_AddKeyPressed

    private void txt_RegNum_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RegNum_AddKeyPressed
         jLabel72.setText("                      ");
    }//GEN-LAST:event_txt_RegNum_AddKeyPressed

    private void DateChooser_RegDate_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DateChooser_RegDate_AddKeyPressed
        jLabel73.setText("                      ");                                        
    }//GEN-LAST:event_DateChooser_RegDate_AddKeyPressed

    private void txt_InsNum_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_InsNum_AddKeyPressed
        jLabel69.setText("                      ");                                         
    }//GEN-LAST:event_txt_InsNum_AddKeyPressed

    private void txt_InsCompany_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_InsCompany_AddKeyPressed
        jLabel70.setText("                      "); 
    }//GEN-LAST:event_txt_InsCompany_AddKeyPressed

    private void DateChooser_InsDate_AddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DateChooser_InsDate_AddKeyPressed
        jLabel71.setText("                      "); 
    }//GEN-LAST:event_DateChooser_InsDate_AddKeyPressed

    private void txt_chassicNo_AddFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_chassicNo_AddFocusLost
        if(txt_chassicNo_Add.getText().length() != 0 && !validation.isChassicName(txt_chassicNo_Add.getText()))
            jLabel48.setText("Invalid Chassic Num");
        else
            jLabel48.setText("                   ");
        
    }//GEN-LAST:event_txt_chassicNo_AddFocusLost

    private void txt_RegNum_AddFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RegNum_AddFocusLost
 
    }//GEN-LAST:event_txt_RegNum_AddFocusLost

    private void txt_vID_RegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vID_RegKeyPressed
          jLabel79.setText("                        ");
    }//GEN-LAST:event_txt_vID_RegKeyPressed

    private void txt_billID_RegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_billID_RegKeyPressed
          jLabel81.setText("                        ");  
    }//GEN-LAST:event_txt_billID_RegKeyPressed

    private void txt_price_RegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_price_RegKeyPressed
        jLabel83.setText("                        ");  
    }//GEN-LAST:event_txt_price_RegKeyPressed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
      
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
//        clearReg();
//        Update_Registration_Table();
//        clearVehicleAdd();  
//        clearIns();
//        Update_Insurance_Table();
//        clearFuel();
//        Update_FuelFilling_Table();     
//        clearMtn();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void txt_vehicleID_InsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleID_InsKeyPressed
        jLabel22.setText("                ");
    }//GEN-LAST:event_txt_vehicleID_InsKeyPressed

    private void txt_billID_InsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_billID_InsKeyPressed
        jLabel80.setText("                ");
    }//GEN-LAST:event_txt_billID_InsKeyPressed

    private void txt_price_InsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_price_InsKeyPressed
        jLabel87.setText("                ");
    }//GEN-LAST:event_txt_price_InsKeyPressed

    private void DateChooser_Renew_InsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DateChooser_Renew_InsKeyPressed
       jLabel88.setText("                ");
    }//GEN-LAST:event_DateChooser_Renew_InsKeyPressed

    private void DateChooser_nextRenew_InsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DateChooser_nextRenew_InsKeyPressed
        jLabel89.setText("                ");
    }//GEN-LAST:event_DateChooser_nextRenew_InsKeyPressed

    private void txt_vehicleID_InsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vehicleID_InsFocusLost
        if(txt_vehicleID_Ins.getText().length() != 0){
        try{
            String sql1 ="select * from vehicle where vehicleID=? ";
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_vehicleID_Ins.getText());
            rs=pst.executeQuery();
            if(rs.next()){             
            }else{
                jLabel22.setText("Invalid vehicle ID !!!");
            }   
              rs.close();
              pst.close();
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
        }
    }//GEN-LAST:event_txt_vehicleID_InsFocusLost

    private void txt_vehicleID_FuelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleID_FuelKeyPressed
          jLabel14.setText("                ");
    }//GEN-LAST:event_txt_vehicleID_FuelKeyPressed

    private void txt_amount_FuelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_amount_FuelKeyPressed
         jLabel93.setText("                ");
    }//GEN-LAST:event_txt_amount_FuelKeyPressed

    private void txt_BillID_FuelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BillID_FuelKeyPressed
        jLabel91.setText("                ");
    }//GEN-LAST:event_txt_BillID_FuelKeyPressed

    private void txt_Price_FuelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Price_FuelKeyPressed
        jLabel19.setText("                ");
    }//GEN-LAST:event_txt_Price_FuelKeyPressed

    private void txt_vehicleID_FuelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vehicleID_FuelFocusLost
         if(txt_vehicleID_Fuel.getText().length() != 0){
        try{
            String sql1 ="select * from vehicle where vehicleID=? ";
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_vehicleID_Fuel.getText());
            rs=pst.executeQuery();
            if(rs.next()){             
            }else{
                jLabel14.setText("Invalid vehicle ID !!!");
            }   
              rs.close();
              pst.close();
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                rs.close();
              pst.close();
              jRadioButton2_Fuel_fuel.disable();
              jRadioButton1_Fuel_fuel.disable();
            }catch(Exception e) {
            }
        }
         }
    }//GEN-LAST:event_txt_vehicleID_FuelFocusLost

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1FocusGained

    private void txt_vehicleID_MtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleID_MtnKeyPressed
         jLabel95.setText("         ");
    }//GEN-LAST:event_txt_vehicleID_MtnKeyPressed

    private void txt_serviceID_MtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_serviceID_MtnKeyPressed
         jLabel96.setText("         ");
    }//GEN-LAST:event_txt_serviceID_MtnKeyPressed

    private void jDateChooser_MtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser_MtnKeyPressed
       jLabel97.setText("         ");
    }//GEN-LAST:event_jDateChooser_MtnKeyPressed

    private void txt_BillID_MtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BillID_MtnKeyPressed
         jLabel98.setText("         "); 
    }//GEN-LAST:event_txt_BillID_MtnKeyPressed

    private void txt_Price_MtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Price_MtnKeyPressed
        jLabel100.setText("         ");
    }//GEN-LAST:event_txt_Price_MtnKeyPressed

    private void txt_vehicleID_MtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vehicleID_MtnFocusLost
         if(txt_vehicleID_Mtn.getText().length() != 0){
        try{
            String sql1 ="select * from vehicle where vehicleID=? ";
            pst =conn.prepareStatement(sql1);
            pst.setString(1, txt_vehicleID_Mtn.getText());
            rs=pst.executeQuery();
            if(rs.next()){             
            }else{
                jLabel95.setText("Invalid vehicle ID !!!");
            }   
              rs.close();
              pst.close();
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
         }
    }//GEN-LAST:event_txt_vehicleID_MtnFocusLost

    private void cmd_report_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_report_AddActionPerformed
        add_vehicle_report avr;
    try {
        avr = new add_vehicle_report();
         avr.setVisible(true);
    } catch (Exception ex) {
        Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_cmd_report_AddActionPerformed

    private void cmd_report_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_report_RegActionPerformed
        try{
            String report ="src\\Fleet\\first_report1.jrxml";
            JasperDesign jd=JRXmlLoader.load("src\\Fleet\\vehicleRegistration.jrxml");
            String sql = "SELECT `vehicle_registration`.vehicle_ID, `vehicle_registration`.bill_ID, DATE_FORMAT(`vehicle_registration`.renewedDate,'%Y-%m-%d'),DATE_FORMAT(`vehicle_registration`.nextRenewDate,'%Y-%m-%d') FROM vehicle_registration ORDER BY vehicle_ID";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_report_RegActionPerformed

    private void txt_price_InsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_price_InsKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_price_InsKeyTyped

    private void txt_Price_FuelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Price_FuelKeyTyped
    char c=evt.getKeyChar();
    if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_Price_FuelKeyTyped

    private void txt_Price_MtnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Price_MtnKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_Price_MtnKeyTyped

    private void txt_amount_FuelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_amount_FuelKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_amount_FuelKeyTyped

    private void txt_billID_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_billID_RegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_billID_RegActionPerformed

    private void txt_billID_RegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_billID_RegFocusLost
     if(!txt_billID_Ins.getText().startsWith("BID")){
            jLabel80.setText("Bill ID Should start with BID");
        }
    }//GEN-LAST:event_txt_billID_RegFocusLost

    private void txt_billID_InsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_billID_InsFocusLost
        
        if(!txt_billID_Reg.getText().startsWith("BID")){
            jLabel81.setText("Bill ID Should start with BID");
        }
    }//GEN-LAST:event_txt_billID_InsFocusLost

    private void txt_BillID_FuelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BillID_FuelFocusLost
         if(!txt_BillID_Fuel.getText().startsWith("BID")){
            jLabel91.setText("Bill ID Should start with BID");
        }
    }//GEN-LAST:event_txt_BillID_FuelFocusLost

    private void txt_BillID_MtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BillID_MtnFocusLost
        if(!txt_BillID_Mtn.getText().startsWith("BID")){
            jLabel98.setText("Bill ID Should start with BID");
        }
    }//GEN-LAST:event_txt_BillID_MtnFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        ComboBox_Type_Add.setSelectedItem("Car");

        txt_model_Add.setText("Toyota");

        txt_chassicNo_Add.setText("FSRE534352DGDGD");

        txt_Colour_Add.setText("RED");
        
                Transmission = "Auto";
                if(Transmission.equals("Auto")){
                    RadioButton_Transmission_1_Add.setSelected(true);
                }
                else if(Transmission.equals("Manual")){
                    RadioButton_Transmission_2_Add.setSelected(true);
                }

                fuelType = "Petrol";
                if(fuelType.equals("Petrol")){
                    RadioButton_Fuel_1_Add.setSelected(true);
                }
                else if(fuelType.equals("Diesel")){
                    RadioButton_Fuel_2_Add.setSelected(true);
                }

                 
                status = "active";
                if(status.equals("active")){
                    RadioButton_status_1_Add.setSelected(true);
                }
                else if(status.equals("not-active")){
                    RadioButton_status_2_Add.setSelected(true);
                }

        txt_GPS_Add.setText("GS1231");

        txt_RegNum_Add.setText("QR-2343");

        ((JTextField) DateChooser_RegDate_Add.getDateEditor().getUiComponent()).setText("2016-09-25");

        txt_InsNum_Add.setText("1254828");

        txt_InsCompany_Add.setText("Chamidu");

        ((JTextField) DateChooser_InsDate_Add.getDateEditor().getUiComponent()).setText("2016-09-25");

//                byte[]add16 = rs.getBytes("PrifPic");
//                format = new ImageIcon(add16);
        //txt_path_Add.setText("C:\\Users\\Tharushi\\Documents\\NetBeansProjects\\8Ware\\src\\small_183723.jpg");        

    }//GEN-LAST:event_jButton3ActionPerformed

    private void addTextPane(String text) {
        jTextPane1_Mtn.setModel(dlm);
        dlm.addElement(text);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new add().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBox_Type_Add;
    private javax.swing.JComboBox ComboBox_Type_Mtn;
    private com.toedter.calendar.JDateChooser DateChooser_InsDate_Add;
    private com.toedter.calendar.JDateChooser DateChooser_RegDate_Add;
    private com.toedter.calendar.JDateChooser DateChooser_Renew_Ins;
    private com.toedter.calendar.JDateChooser DateChooser_nextRenewDate_Reg;
    private com.toedter.calendar.JDateChooser DateChooser_nextRenew_Ins;
    private com.toedter.calendar.JDateChooser DateChooser_renewDate_Reg;
    private javax.swing.JDesktopPane DesktopPane_VehPic_add;
    private javax.swing.JRadioButton RadioButton1_Reg;
    private javax.swing.JRadioButton RadioButton2_Reg;
    private javax.swing.JRadioButton RadioButton_Fuel_1_Add;
    private javax.swing.JRadioButton RadioButton_Fuel_2_Add;
    private javax.swing.JRadioButton RadioButton_Transmission_1_Add;
    private javax.swing.JRadioButton RadioButton_Transmission_2_Add;
    private javax.swing.JRadioButton RadioButton_status_1_Add;
    private javax.swing.JRadioButton RadioButton_status_2_Add;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JButton cmd_AttachPic_Add;
    private javax.swing.JButton cmd_Search_Add;
    private javax.swing.JButton cmd_clear_Add;
    private javax.swing.JButton cmd_clear_Fuel;
    private javax.swing.JButton cmd_clear_Ins;
    private javax.swing.JButton cmd_clear_Mtn;
    private javax.swing.JButton cmd_clear_Reg;
    private javax.swing.JButton cmd_delete_Add;
    private javax.swing.JButton cmd_delete_Fuel;
    private javax.swing.JButton cmd_delete_Ins;
    private javax.swing.JButton cmd_delete_Mtn;
    private javax.swing.JButton cmd_delete_Reg;
    private javax.swing.JButton cmd_home;
    private javax.swing.JButton cmd_logout;
    private javax.swing.JButton cmd_report_Add;
    private javax.swing.JButton cmd_report_Reg;
    private javax.swing.JButton cmd_report_Reg1;
    private javax.swing.JButton cmd_report_Reg2;
    private javax.swing.JButton cmd_report_Reg3;
    private javax.swing.JButton cmd_save_Add;
    private javax.swing.JButton cmd_save_Fuel;
    private javax.swing.JButton cmd_save_Ins;
    private javax.swing.JButton cmd_save_Mtn;
    private javax.swing.JButton cmd_save_Reg;
    private javax.swing.JButton cmd_search_Ins;
    private javax.swing.JButton cmd_tables_Mtn;
    private javax.swing.JButton cmd_update_Add;
    private javax.swing.JButton cmd_update_Fuel;
    private javax.swing.JButton cmd_update_Ins;
    private javax.swing.JButton cmd_update_Mtn;
    private javax.swing.JButton cmd_update_Reg;
    private javax.swing.JButton cmd_vehicleID_Reg;
    private javax.swing.JButton cud_search_Fuel;
    private javax.swing.JButton cud_search_Mtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private com.toedter.calendar.JDateChooser jDateChooser_Fuel;
    private com.toedter.calendar.JDateChooser jDateChooser_Mtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1_Fuel_fuel;
    private javax.swing.JRadioButton jRadioButton1_Ins;
    private javax.swing.JRadioButton jRadioButton1_Mtn;
    private javax.swing.JRadioButton jRadioButton1_payType_Fuel;
    private javax.swing.JRadioButton jRadioButton2_Fuel_fuel;
    private javax.swing.JRadioButton jRadioButton2_Ins;
    private javax.swing.JRadioButton jRadioButton2_Mtn;
    private javax.swing.JRadioButton jRadioButton2_payType_Fuel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList jTextPane1_Mtn;
    private javax.swing.JTextField search_Add;
    private javax.swing.JTable table_Fuelfilling;
    private javax.swing.JTable table_insurance;
    private javax.swing.JTable table_insurance_payment;
    private javax.swing.JTable table_payment;
    private javax.swing.JTable table_payment_Fuelfilling;
    private javax.swing.JTable table_registration;
    private javax.swing.JTextField txt_BillID_Fuel;
    private javax.swing.JTextField txt_BillID_Mtn;
    private javax.swing.JTextField txt_Colour_Add;
    private javax.swing.JTextField txt_GPS_Add;
    private javax.swing.JTextField txt_InsCompany_Add;
    private javax.swing.JTextField txt_InsNum_Add;
    private javax.swing.JTextField txt_Price_Fuel;
    private javax.swing.JTextField txt_Price_Mtn;
    private javax.swing.JTextField txt_RegNum_Add;
    private javax.swing.JTextField txt_amount_Fuel;
    private javax.swing.JTextField txt_billID_Ins;
    private javax.swing.JTextField txt_billID_Reg;
    private javax.swing.JTextField txt_chassicNo_Add;
    private javax.swing.JTextField txt_model_Add;
    private javax.swing.JTextField txt_path_Add;
    private javax.swing.JTextField txt_price_Ins;
    private javax.swing.JTextField txt_price_Reg;
    private javax.swing.JLabel txt_profPic_add;
    private javax.swing.JTextField txt_search_Fuel;
    private javax.swing.JTextField txt_search_Ins;
    private javax.swing.JTextField txt_search_Mtn;
    private javax.swing.JTextField txt_search_Reg;
    private javax.swing.JTextField txt_select_Item_Mtn;
    private javax.swing.JTextField txt_serviceID_Mtn;
    private javax.swing.JLabel txt_time;
    private javax.swing.JTextField txt_vID_Reg;
    private javax.swing.JTextField txt_vehicleID_Add;
    private javax.swing.JTextField txt_vehicleID_Fuel;
    private javax.swing.JTextField txt_vehicleID_Ins;
    private javax.swing.JTextField txt_vehicleID_Mtn;
    // End of variables declaration//GEN-END:variables

    private ImageIcon format = null;
    
    String filename = null;
    int s = 0;
    byte[] vehicle_img = null;
    
    private String Transmission;
    private String fuelType;
    private String status;
    
    private String payTypeReg;
    private String payTypeIns;
    private String payTypeFuel;
     private String TypeFuel;
    private String  payTypeMtn;




}
