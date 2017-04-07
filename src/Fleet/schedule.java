package Fleet;


import HRM.Login;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class schedule extends javax.swing.JFrame {
    
    Date d1 = new Date();

Connection conn = null;                                              
ResultSet rs = null;                                                 
PreparedStatement pst = null;

    public schedule() throws Exception {
        initComponents();
        this.setResizable(false);//not resizable now
        this.setVisible(true);
        conn = DBAccess.getConnection(); 
         
        txt_devID_Dev.setText(incrementDeliveryID());
        //txt_devID_sev.setText(incrementDeliveryID());
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        
        FillComboVehicle();
       Update_JobDev_Table();
        Update_table_service_Service();
    }

    
    
    public void close(){             
    WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);    
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);      
} 
    
    public  String  incrementDeliveryID() {
        String nextAgreementIdStr = null;
        try{
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT COUNT(*)FROM delivery");
             while(rs.next())
            {
                int currentId = Integer.parseInt(rs.getString("count(*)"));
                int nextAgreementId = currentId+1;
                nextAgreementIdStr = "DV".concat(Integer.toString(nextAgreementId));
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);   
        }
    return nextAgreementIdStr;
    }
     
    
    private void Update_JobDev_Table(){                                        
        jTable1.setBackground((Color.GRAY));
        jTable1.setForeground(Color.white);
 
        try{
           String sql1="Select * from job_dev";  
           pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
            rs=pst.executeQuery();                      //execute a query and retrive the result.
           jTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{                                                       
            rs.close();                                             
            pst.close();                                           
        }catch(Exception e) {                                     
        }                                                         
    }                                                                                                                          //4
    }   
  
    private void Update_table_service_Service(){                                        
        table_delivery.setBackground((Color.GRAY));
        table_delivery.setForeground(Color.white);
      
        String sql1="Select  *   from delivery  WHERE `Type`='Service' ";  
   
        try{               
            pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
            rs=pst.executeQuery();                      //execute a query and retrive the result.
            table_delivery.setModel(DbUtils.resultSetToTableModel(rs));  
            rs.close();                                             
 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{                                                       
            rs.close();                                             
            pst.close();                                           
            //conn.close();                                       
        }catch(Exception e) {                                     
        }                                                         
    }                                                                                                                          //4
    }
     
             
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        cmd_delete_Dev = new javax.swing.JButton();
        cmd_update_Dev = new javax.swing.JButton();
        cmd_clear_Dev = new javax.swing.JButton();
        cmd_save_Dev = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_customerID_Dev = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_devID_Dev = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        ComboBox1_VehicleID_Dev = new javax.swing.JComboBox();
        jDateChooser_date_Dev = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_address_dev = new javax.swing.JTextArea();
        txt_jobID_Dev = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ComboBox1_ststusaa_Dev = new javax.swing.JComboBox();
        txt_technitionID_Dev = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_delivery = new javax.swing.JTable();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        txt_search_Dev = new javax.swing.JTextField();
        cud_search_Dev = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        cmd_report_Dev = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txt_time = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        cmd_home1 = new javax.swing.JButton();
        cmd_logout1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFocusCycleRoot(true);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_delete_Dev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_delete_Dev.setText("DELETE");
        cmd_delete_Dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_delete_DevActionPerformed(evt);
            }
        });

        cmd_update_Dev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_update_Dev.setText("UPDATE");
        cmd_update_Dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_update_DevActionPerformed(evt);
            }
        });

        cmd_clear_Dev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_clear_Dev.setText("REFRESH");
        cmd_clear_Dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_clear_DevActionPerformed(evt);
            }
        });

        cmd_save_Dev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_save_Dev.setText("SAVE");
        cmd_save_Dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_save_DevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmd_save_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd_update_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmd_clear_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd_delete_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmd_clear_Dev, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(cmd_save_Dev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmd_delete_Dev, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(cmd_update_Dev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Services");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Technition ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Vehicle ID");

        txt_customerID_Dev.setEnabled(false);
        txt_customerID_Dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_customerID_DevActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Date");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Customer Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Address");

        txt_devID_Dev.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Delivery ID");

        jDateChooser_date_Dev.setDateFormatString("\nyyyy-MM-dd");
        jDateChooser_date_Dev.setMinSelectableDate(d1);

        jTextArea_address_dev.setColumns(20);
        jTextArea_address_dev.setRows(5);
        jTextArea_address_dev.setEnabled(false);
        jScrollPane3.setViewportView(jTextArea_address_dev);

        txt_jobID_Dev.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Job ID");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Status");

        ComboBox1_ststusaa_Dev.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Not-Finished", "Finished" }));

        txt_technitionID_Dev.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBox1_ststusaa_Dev, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_devID_Dev)
                    .addComponent(txt_jobID_Dev)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ComboBox1_VehicleID_Dev, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_customerID_Dev, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser_date_Dev, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txt_technitionID_Dev))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txt_devID_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jobID_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_customerID_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ComboBox1_VehicleID_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_technitionID_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser_date_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBox1_ststusaa_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        table_delivery.setModel(new javax.swing.table.DefaultTableModel(
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
        table_delivery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_deliveryMouseClicked(evt);
            }
        });
        table_delivery.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table_deliveryKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_delivery);

        jPanel55.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel56.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_search_Dev.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        cud_search_Dev.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        cud_search_Dev.setText("Search");
        cud_search_Dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cud_search_DevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_search_Dev, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cud_search_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_search_Dev, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(cud_search_Dev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel67.setText("Search By Job ID");

        jPanel57.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_report_Dev.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cmd_report_Dev.setForeground(new java.awt.Color(204, 0, 51));
        cmd_report_Dev.setText("REPORT");
        cmd_report_Dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_report_DevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_report_Dev, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("New Jobs ");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Delivery Table");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(475, 475, 475))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("      SERVICES        ", jPanel1);

        txt_time.setFont(new java.awt.Font("Algerian", 1, 48)); // NOI18N
        txt_time.setForeground(new java.awt.Color(0, 0, 153));
        txt_time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rect.png"))); // NOI18N

        cmd_home1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_home1.setText("HOME");
        cmd_home1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_home1ActionPerformed(evt);
            }
        });

        cmd_logout1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmd_logout1.setText("LOGOUT");
        cmd_logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_logout1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 204));
        jLabel13.setText("                     Logged in as Fleet Manager");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addComponent(cmd_home1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmd_logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmd_logout1)
                    .addComponent(cmd_home1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(697, 697, 697)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean Emphty(){
        if(txt_devID_Dev.getText().length()==0){
            return true;
        }else{
            return false;
        }
    }
     
    private boolean EmphtyService(){
        if(txt_devID_Dev.getText().length()==0 ){
            return true;
        }else{
            return false;
        }
    }
    
    
    private void FillComboVehicle(){
        try{
            String sql = "select * from vehicle where `status`='not-active'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("vehicleID");
                ComboBox1_VehicleID_Dev.addItem(name);
            }
            
        }
        catch(Exception e){}
    }
    
    
    
    public void clearServiceDev(){
        ComboBox1_VehicleID_Dev.enable();
        ((JTextField)jDateChooser_date_Dev.getDateEditor().getUiComponent()).enable();
        cmd_save_Dev.setEnabled(true);
        cmd_update_Dev.setEnabled(true);
        txt_devID_Dev.setText(incrementDeliveryID());
     //   txt_devID_sev.setText(incrementDeliveryID());
        ComboBox1_VehicleID_Dev.removeAllItems();
         FillComboVehicle();
        txt_jobID_Dev.setText("");
        txt_customerID_Dev.setText("");
        ComboBox1_VehicleID_Dev.setSelectedItem("");
         txt_technitionID_Dev.setText("");
        ((JTextField)jDateChooser_date_Dev.getDateEditor().getUiComponent()).setText("");
        jTextArea_address_dev.setText("");
        ComboBox1_ststusaa_Dev.setSelectedItem("");
        txt_search_Dev.setText("");
     }
    
     
    
    private void cmd_home1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_home1ActionPerformed
         close();
        home s;                                                           
        try {
            s = new home(); //4
            s.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(schedule.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_cmd_home1ActionPerformed

    private void cmd_logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_logout1ActionPerformed
                new Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_cmd_logout1ActionPerformed

    
    private void cmd_report_DevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_report_DevActionPerformed
        try{
            String report ="src\\Fleet\\first_report1.jrxml";
            JasperDesign jd=JRXmlLoader.load("src\\Fleet\\devService.jrxml");
            String sql = "SELECT `delivery`.delivaryID,`delivery`.orderID,`delivery`.customerID,`delivery`.vehicleID, `delivery`.employeeID, DATE_FORMAT(`delivery`.devDate,'%Y-%m-%d'), `delivery`.customerAdres, `delivery`.Type, `delivery`.status FROM delivery WHERE `Type`='Service'";
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
    }//GEN-LAST:event_cmd_report_DevActionPerformed

    private void cud_search_DevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cud_search_DevActionPerformed
        table_delivery.setBackground((Color.GRAY)); 
        table_delivery.setForeground(Color.WHITE);
        if(txt_search_Dev.getText().length() ==0){
            JOptionPane.showMessageDialog(null, "Add Order ID");
        }else{
        String sql1="Select  *   from delivery where orderID=? AND `type`='Service'";   
        try{               
            pst =conn.prepareStatement(sql1);            //carry SQL query to the DB.  
            pst.setString(1, txt_search_Dev.getText());
            rs=pst.executeQuery();
            table_delivery.setModel(DbUtils.resultSetToTableModel(rs));  
          
       // pst.execute();                                  
   rs.close();                                             
 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{                                                       
            rs.close();                                             
            pst.close();                                           
            //conn.close();                                       
        }catch(Exception e) {                                     
        }                                                         
    }   }
    }//GEN-LAST:event_cud_search_DevActionPerformed

    private void table_deliveryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_deliveryKeyPressed

    }//GEN-LAST:event_table_deliveryKeyPressed

    private void table_deliveryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_deliveryMouseClicked
        clearServiceDev();
        cmd_save_Dev.setEnabled(false);
        
        try{
            int row=table_delivery.getSelectedRow();
            String Table_click2=(table_delivery.getModel().getValueAt(row,1).toString());
            String sql2 ="select * from delivery where `orderID`='"+Table_click2+"'";

            pst =conn.prepareStatement(sql2);
            rs=pst.executeQuery();
            if(rs.next()){

                String add1= rs.getString("delivaryID");
                txt_devID_Dev.setText(add1);

                String add2= rs.getString("orderID");
                txt_jobID_Dev.setText(add2);
                
                String add3= rs.getString("customerID");
                txt_customerID_Dev.setText(add3);
                
                String add4= rs.getString("vehicleID");
                ComboBox1_VehicleID_Dev.removeItem(add4);
                ComboBox1_VehicleID_Dev.addItem(add4);
                ComboBox1_VehicleID_Dev.setSelectedItem(add4);
                                
                String add5= rs.getString("employeeID");
                txt_technitionID_Dev.setText(add5);

                String add6= rs.getString("devDate");
                ((JTextField)jDateChooser_date_Dev.getDateEditor().getUiComponent()).setText(add6);

                String add7= rs.getString("customerAdres");
                jTextArea_address_dev.setText(add7);
                
                String add8= rs.getString("status");
                ComboBox1_ststusaa_Dev.setSelectedItem(add8);
               
            }
            
            String value8 = ComboBox1_ststusaa_Dev.getSelectedItem().toString();
            
             if( value8 == "Finished"){
                    cmd_save_Dev.setEnabled(false);
                    cmd_update_Dev.setEnabled(false);
                    ComboBox1_VehicleID_Dev.disable();
             }else{
                 ComboBox1_VehicleID_Dev.disable();
                ((JTextField)jDateChooser_date_Dev.getDateEditor().getUiComponent()).disable();
             }
             
            rs.close();
              pst.close();

         
              
              
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                rs.close();
                pst.close();
                //cmd_save_Sev.disable();
                //conn.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_table_deliveryMouseClicked

    private void txt_customerID_DevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_customerID_DevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_customerID_DevActionPerformed

    private void cmd_save_DevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_save_DevActionPerformed
        if(EmphtyService()){
             JOptionPane.showMessageDialog(null, "All Fields Should be filled");
         }
         else{        
        String sql = "INSERT INTO delivery (`delivaryID`, `orderID`, `customerID`, `vehicleID`, `employeeID`, `devDate`, `customerAdres`, `status`, `Type`) values (?,?,?,?,?,?,?,?,'Service')";
       
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, txt_devID_Dev.getText());
            pst.setString(2, txt_jobID_Dev.getText());
            pst.setString(3, txt_customerID_Dev.getText());
            pst.setString(4, ComboBox1_VehicleID_Dev.getSelectedItem().toString());
            pst.setString(5, txt_technitionID_Dev.getText());
            pst.setString(6, ((JTextField)jDateChooser_date_Dev.getDateEditor().getUiComponent()).getText());
            pst.setString(7, jTextArea_address_dev.getText());
            pst.setString(8, ComboBox1_ststusaa_Dev.getSelectedItem().toString());   pst.execute();
            JOptionPane.showMessageDialog(null, "Saved");
            
            if( ComboBox1_ststusaa_Dev.getSelectedItem().toString() == "Not-Finished"){
                String value1 = ComboBox1_VehicleID_Dev.getSelectedItem().toString();
                String sql2="UPDATE vehicle SET `status`='active'  WHERE `vehicleID`='"+value1+"'";
                pst=conn.prepareStatement(sql2);
                pst.execute();
            }
            else if( ComboBox1_ststusaa_Dev.getSelectedItem().toString() == "Finished"){
                 String value1 = ComboBox1_VehicleID_Dev.getSelectedItem().toString();
                 String sql2="UPDATE vehicle SET `status`='not-active'  WHERE `vehicleID`='"+value1+"'";
                 pst=conn.prepareStatement(sql2);
                 pst.execute();
                 
                Add_Mileage am = new Add_Mileage();
                am.setVisible(true);
            }     
            
                String value1 = txt_jobID_Dev.getText();
                 String sqlx = "delete from job_dev where job_id='"+value1+"'"; 
                 pst=conn.prepareStatement(sqlx);
                 pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                rs.close();
                pst.close();
                Update_table_service_Service();
                Update_JobDev_Table();
                clearServiceDev();
            }catch(Exception e) {
            }
        }
         }
    }//GEN-LAST:event_cmd_save_DevActionPerformed

    private void cmd_clear_DevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_clear_DevActionPerformed
   clearServiceDev();
        Update_table_service_Service();
    }//GEN-LAST:event_cmd_clear_DevActionPerformed

    private void cmd_update_DevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_update_DevActionPerformed
         if(EmphtyService()){
             JOptionPane.showMessageDialog(null, "All Fields Should be filled");
         }
         else{ 
        try{
            String valueA = ComboBox1_VehicleID_Dev.getSelectedItem().toString();       
                       
            String value1 = txt_devID_Dev.getText();
            String value2 =  txt_jobID_Dev.getText();
            String value3 = txt_customerID_Dev.getText();
            String value4 = ComboBox1_VehicleID_Dev.getSelectedItem().toString();
            String value5 = txt_technitionID_Dev.getText();
            String value6 = ((JTextField)jDateChooser_date_Dev.getDateEditor().getUiComponent()).getText();
            String value7 = jTextArea_address_dev.getText();
            String value8 = ComboBox1_ststusaa_Dev.getSelectedItem().toString();
            
            String sql1="Select * from delivery where  `orderID`='"+value2+"'";
            pst =conn.prepareStatement(sql1);
            rs=pst.executeQuery();
            if(rs.next()){
                String add5= rs.getString("vehicleID");
                String sqlq="UPDATE vehicle SET `status`='not-active'  WHERE `vehicleID`='"+add5+"' ";
                pst =conn.prepareStatement(sqlq);
                pst.execute();
            }
            
           String sql="UPDATE delivery SET `delivaryID`='"+value1+"', `orderID`='"+value2+"', `customerID`='"+value3+"', `vehicleID`='"+value4+"', `employeeID`='"+value5+"', `devDate`='"+value6+"', `customerAdres`='"+value7+"', `status`='"+value8+"'  WHERE `orderID`='"+value2+"' ";
            pst =conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");

            
            if( value8 == "Not-Finished"){
                  String sql2="UPDATE vehicle SET `status`='active'  WHERE `vehicleID`='"+valueA+"'";
                    pst=conn.prepareStatement(sql2);
                    pst.execute();
            }
            else if( value8 == "Finished"){
                String sql2="UPDATE vehicle SET `status`='not-active'  WHERE `vehicleID`='"+valueA+"'";
                 pst=conn.prepareStatement(sql2);
                 pst.execute();
                Add_Mileage am = new Add_Mileage();
                am.jTextField1.setText(valueA);
                am.setVisible(true);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{
                rs.close();
                pst.close();
               Update_table_service_Service();
                Update_JobDev_Table();
                clearServiceDev();
            }catch(Exception e) {
            }
        }
        }
    }//GEN-LAST:event_cmd_update_DevActionPerformed

    private void cmd_delete_DevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_delete_DevActionPerformed
        try{
            String value2 =  txt_jobID_Dev.getText();
            String sql1="Select * from delivery where  `orderID`='"+value2+"'";
            pst =conn.prepareStatement(sql1);
            rs=pst.executeQuery();
            if(rs.next()){
                String add5= rs.getString("vehicleID");
                String sqlq="UPDATE vehicle SET `status`='not-active'  WHERE `vehicleID`='"+add5+"' ";
                pst =conn.prepareStatement(sqlq);
                pst.execute();
              
            }
            
            
            String sql2 = "delete from delivery where orderID=? ";
            pst =conn.prepareStatement(sql2);
            pst.setString(1, txt_jobID_Dev.getText());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Deleted");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try{   
                rs.close();
                pst.close();
                Update_table_service_Service();
                Update_JobDev_Table();
                clearServiceDev();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_cmd_delete_DevActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        clearServiceDev();
        cmd_save_Dev.setEnabled(true);
         try{ 
            int row=jTable1.getSelectedRow();
            String Table_click1=(jTable1.getModel().getValueAt(row,0).toString());
            //String Table_click2=(table_registration.getModel().getValueAt(row,2).toString());
            String sql1 ="select * from job_dev where job_id='"+Table_click1+"' ";
            String sql2 ="select * from delivery where orderID='"+Table_click1+"' ";

            pst =conn.prepareStatement(sql1);
            rs=pst.executeQuery();
            if(rs.next()){

                String add1= rs.getString("job_id");
                txt_jobID_Dev.setText(add1);

                String add2= rs.getString("client");
                txt_customerID_Dev.setText(add2);
//
                String add4= rs.getString("technition");
                txt_technitionID_Dev.setText(add4);
                
                
                String sql3 ="select * from client where clientId='"+add2+"' ";
                pst =conn.prepareStatement(sql3);
                rs=pst.executeQuery();
                if(rs.next()){
                    String add5= rs.getString("address");
                    jTextArea_address_dev.setText(add5);
                }
            }
            rs.close();
            pst.close();

            pst=conn.prepareStatement(sql2);
            rs=pst.executeQuery();
            if(rs.next()){
                String add4= rs.getString("delivaryID");
                txt_devID_Dev.setText(add4);

                String add5= rs.getString("vehicleID");
                ComboBox1_VehicleID_Dev.removeItem(add5);
                ComboBox1_VehicleID_Dev.addItem(add5);
                ComboBox1_VehicleID_Dev.setSelectedItem(add5);

                String add6= rs.getString("employeeID");
                txt_technitionID_Dev.setText(add6);
                
                String add7= rs.getString("customerAdres");
                jTextArea_address_dev.setText(add7);

                String add8= rs.getString("status");
                ComboBox1_ststusaa_Dev.setSelectedItem(add8);
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
    }//GEN-LAST:event_jTable1MouseClicked

    
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
            java.util.logging.Logger.getLogger(schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new schedule().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(schedule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBox1_VehicleID_Dev;
    private javax.swing.JComboBox ComboBox1_ststusaa_Dev;
    private javax.swing.JButton cmd_clear_Dev;
    private javax.swing.JButton cmd_delete_Dev;
    private javax.swing.JButton cmd_home1;
    private javax.swing.JButton cmd_logout1;
    private javax.swing.JButton cmd_report_Dev;
    private javax.swing.JButton cmd_save_Dev;
    private javax.swing.JButton cmd_update_Dev;
    private javax.swing.JButton cud_search_Dev;
    private com.toedter.calendar.JDateChooser jDateChooser_date_Dev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea_address_dev;
    private javax.swing.JTable table_delivery;
    private javax.swing.JTextField txt_customerID_Dev;
    private javax.swing.JTextField txt_devID_Dev;
    private javax.swing.JTextField txt_jobID_Dev;
    private javax.swing.JTextField txt_search_Dev;
    private javax.swing.JTextField txt_technitionID_Dev;
    private javax.swing.JLabel txt_time;
    // End of variables declaration//GEN-END:variables
}
