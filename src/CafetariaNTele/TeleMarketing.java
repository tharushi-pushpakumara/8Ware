package CafetariaNTele;


import HRM.Login;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Visal
 */

public class TeleMarketing extends javax.swing.JFrame {

    /**
     * Creates new form TeleMarketing
     */
    PreparedStatement pst=null;
    ResultSet rset=null;
    ResultSet rset1=null;

    public TeleMarketing(ResultSet rset1, JTextField AddrTxt, JTextField AddrTxt1, JComboBox<String> ClIDcmb, JTextField EmailTxt, JTextField EmailTxt1, JLabel IDlbl, JLabel IDlbl1, JComboBox<String> IPcmb, JComboBox<String> IPcmb1, JComboBox<String> IScmb, JComboBox<String> IScmb1, JLabel IdLbl, JTextArea InterestedTxt, JTextArea InterestedTxt1, JButton LogoutBtn, JTextField NameTxt, JTextField NameTxt1, JTextField PNameTxt, JTextField PNameTxt1, JTextField PNameTxt2, JTextField PNameTxt3, JTextField PhoneTxt, JTextField PhoneTxt1, JComboBox<String> PtypeCmb, JComboBox<String> PtypeCmb1, JComboBox<String> PtypeCmb2, JComboBox<String> PtypeCmb3, JButton jButton1, JButton jButton10, JButton jButton11, JButton jButton12, JButton jButton13, JButton jButton14, JButton jButton15, JButton jButton16, JButton jButton17, JButton jButton18, JButton jButton19, JButton jButton2, JButton jButton20, JButton jButton21, JButton jButton22, JButton jButton3, JButton jButton4, JButton jButton5, JButton jButton6, JButton jButton7, JButton jButton8, JButton jButton9, JLabel jLabel1, JLabel jLabel10, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel19, JLabel jLabel2, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JLabel jLabel26, JLabel jLabel27, JLabel jLabel28, JLabel jLabel29, JLabel jLabel3, JLabel jLabel30, JLabel jLabel31, JLabel jLabel32, JLabel jLabel33, JLabel jLabel4, JLabel jLabel5, JLabel jLabel6, JLabel jLabel7, JLabel jLabel8, JLabel jLabel9, JPanel jPanel1, JPanel jPanel2, JPanel jPanel3, JPanel jPanel4, JPanel jPanel5, JPanel jPanel6, JPanel jPanel7, JPanel jPanel8, JPanel jPanel9, JScrollPane jScrollPane1, JScrollPane jScrollPane2, JScrollPane jScrollPane3, JScrollPane jScrollPane4, JScrollPane jScrollPane5, JSeparator jSeparator1, JTabbedPane jTabbedPane1, JTable jTable1, JTable jTable2, JTable jTable3, JTextField priceTxt, JTextField priceTxt1, JTextField priceTxt2, JTextField priceTxt3) throws HeadlessException {
        this.rset1 = rset1;
        this.AddrTxt = AddrTxt;
        this.AddrTxt1 = AddrTxt1;
        this.ClIDcmb = ClIDcmb;
        this.EmailTxt = EmailTxt;
        this.EmailTxt1 = EmailTxt1;
        this.IDlbl = IDlbl;
        this.IDlbl1 = IDlbl1;
        this.IPcmb = IPcmb;
        this.IPcmb1 = IPcmb1;
        this.IScmb = IScmb;
        this.IScmb1 = IScmb1;
        this.IdLbl = IdLbl;
        this.InterestedTxt = InterestedTxt;
        this.InterestedTxt1 = InterestedTxt1;
        this.LogoutBtn = LogoutBtn;
        this.NameTxt = NameTxt;
        this.NameTxt1 = NameTxt1;
        this.PNameTxt = PNameTxt;
        this.PNameTxt1 = PNameTxt1;
        this.PNameTxt2 = PNameTxt2;
        this.PNameTxt3 = PNameTxt3;
        this.PhoneTxt = PhoneTxt;
        this.PhoneTxt1 = PhoneTxt1;
        this.PtypeCmb = PtypeCmb;
        this.PtypeCmb1 = PtypeCmb1;
        this.PtypeCmb2 = PtypeCmb2;
        this.PtypeCmb3 = PtypeCmb3;
        this.jButton1 = jButton1;
        this.jButton10 = jButton10;
        this.jButton11 = jButton11;
        this.jButton12 = jButton12;
        this.jButton13 = jButton13;
        this.jButton14 = jButton14;
        this.jButton15 = jButton15;
        this.jButton16 = jButton16;
        this.jButton17 = jButton17;
        this.jButton18 = jButton18;
        this.jButton19 = jButton19;
        this.jButton2 = jButton2;
        this.jButton20 = jButton20;
        this.jButton21 = jButton21;
        this.jButton22 = jButton22;
        this.jButton3 = jButton3;
        this.jButton4 = jButton4;
        this.jButton5 = jButton5;
        this.jButton6 = jButton6;
        this.jButton7 = jButton7;
        this.jButton8 = jButton8;
        this.jButton9 = jButton9;
        this.jLabel1 = jLabel1;
        this.jLabel10 = jLabel10;
       // this.jLabel11 = jLabel11;
        this.jLabel12 = jLabel12;
        this.jLabel13 = jLabel13;
        this.jLabel14 = jLabel14;
        this.jLabel15 = jLabel15;
        this.jLabel16 = jLabel16;
        this.jLabel17 = jLabel17;
        this.jLabel18 = jLabel18;
        this.jLabel19 = jLabel19;
        this.jLabel2 = jLabel2;
        this.jLabel20 = jLabel20;
        this.jLabel21 = jLabel21;
        this.jLabel22 = jLabel22;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jLabel26 = jLabel26;
        this.jLabel27 = jLabel27;
        this.jLabel28 = jLabel28;
        this.jLabel29 = jLabel29;
        this.jLabel3 = jLabel3;
        this.jLabel30 = jLabel30;
        this.jLabel31 = jLabel31;
        this.jLabel32 = jLabel32;
        this.jLabel33 = jLabel33;
        this.jLabel4 = jLabel4;
        this.jLabel5 = jLabel5;
        this.jLabel6 = jLabel6;
        this.jLabel7 = jLabel7;
        this.jLabel8 = jLabel8;
        this.jLabel9 = jLabel9;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jPanel3 = jPanel3;
        this.jPanel4 = jPanel4;
        this.jPanel5 = jPanel5;
        this.jPanel6 = jPanel6;
        this.jPanel7 = jPanel7;
        this.jPanel8 = jPanel8;
        this.jPanel9 = jPanel9;
        this.jScrollPane1 = jScrollPane1;
        this.jScrollPane2 = jScrollPane2;
        this.jScrollPane3 = jScrollPane3;
        this.jScrollPane4 = jScrollPane4;
        this.jScrollPane5 = jScrollPane5;
        this.jSeparator1 = jSeparator1;
        this.jTabbedPane1 = jTabbedPane1;
        this.jTable1 = jTable1;
        this.jTable2 = jTable2;
        this.jTable3 = jTable3;
        this.priceTxt = priceTxt;
        this.priceTxt1 = priceTxt1;
        this.priceTxt2 = priceTxt2;
        this.priceTxt3 = priceTxt3;
    }
    ResultSet rs5=null;
    DBAccess db=new DBAccess();
    Connection con=null;
    public TeleMarketing() throws Exception {
        initComponents();
        
        try {
           con=DBAccess.getConnection();
            Statement s=con.createStatement();
            Statement s1=con.createStatement();
            Statement s2=con.createStatement();
            ResultSet rs=s.executeQuery("select * from 8ware.product");
            //ResultSet rs2=s.executeQuery("select * from 8ware.person");
            Vector v=new Vector();
            while(rs.next())
            {
                v.add(rs.getString("Pro_Name"));
            }
            IPcmb.setModel(new DefaultComboBoxModel(v));
            IPcmb1.setModel(new DefaultComboBoxModel(v));
            
             ResultSet rs1=s1.executeQuery("select * from 8ware.services");
            Vector v1=new Vector();
            while(rs1.next())
            {
                v1.add(rs1.getString("Ser_Name"));
            }
            IScmb.setModel(new DefaultComboBoxModel(v1));
            IScmb1.setModel(new DefaultComboBoxModel(v1));
            
             ResultSet rs2=s2.executeQuery("select * from 8ware.person");
            Vector v2=new Vector();
            while(rs2.next())
            {
                v2.add(rs2.getString("Per_ID"));
            }
           ClIDcmb.setModel(new DefaultComboBoxModel(v2));
            
        } catch (Exception ex) {
            Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        
 
       
        System.out.println("good here");
       
        
        System.out.println("good here too");
        
         
        
        try {
        Connection c = DBAccess.getConnection();
        Statement stmt = c.createStatement();
       
        Statement st = c.createStatement();
        
        String query1="select Ser_Name from 8ware.product";
        String query2="select Pro_Name from 8ware.services";
//     
            ResultSet rs = stmt.executeQuery(query1);
            Vector v=new Vector();
            while(rs.next()){
                v.add(rs.getString("Ser_Name"));
            }
            IScmb.setModel(new DefaultComboBoxModel(v));
            IScmb1.setModel(new DefaultComboBoxModel(v));
            
          
           ResultSet rs2 = st.executeQuery(query2);
            while (rs2.next()) {
           IPcmb.addItem(rs2.getString("Pro_Name"));
           IPcmb1.addItem(rs2.getString("Pro_Name"));
            }
  
    } catch (Exception e) {
    }
        
        Update_Table();
        Update_Table2();
        Update_Table3();
    }
    
    private void Update_Table(){           
       
        String sql1="Select  * from `8ware`.person ";  
        String sql2="Select  * from `8ware`.product ";
       try{               
            pst =con.prepareStatement(sql1); 
            //pst =con.prepareStatement(sql2); 
            //carry SQL query to the DB.  
            rset=pst.executeQuery();                      //execute a query and retrive the result.
            jTable1.setModel(DbUtils.resultSetToTableModel(rset));  
           // jTable2.setModel(DbUtils.resultSetToTableModel(rset));  
            rset.close(); 
            pst.close(); 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{         
            rset.close();                                             
            pst.close();                                                                               
        }catch(Exception e) {                                     
        }                                                         
    }
       
    } 
    private void Update_Table2(){           
       
        
        String sql2="Select  * from 8ware.product ";
       try{               
            //pst =con.prepareStatement(sql1); 
            pst =con.prepareStatement(sql2); 
            //carry SQL query to the DB.  
            rset=pst.executeQuery();                      //execute a query and retrive the result.
            //jTable1.setModel(DbUtils.resultSetToTableModel(rset));  
            jTable2.setModel(DbUtils.resultSetToTableModel(rset));  
            rset.close(); 
            pst.close(); 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{         
            rset.close();                                             
            pst.close();                                                                               
        }catch(Exception e) {                                     
        }                                                         
    }
       
    }
    private void Update_Table3(){           
       
        //String sql1="Select  * from `8ware`.person ";  
        String sqlq="Select * from 8ware.services ";
       try{               
            pst =con.prepareStatement(sqlq); 
            //pst =con.prepareStatement(sql2); 
            //carry SQL query to the DB.  
            rset=pst.executeQuery();                      //execute a query and retrive the result.
            jTable3.setModel(DbUtils.resultSetToTableModel(rset));  
           // jTable2.setModel(DbUtils.resultSetToTableModel(rset));  
            rset.close(); 
            pst.close(); 
    }catch(Exception e){                                            
           JOptionPane.showMessageDialog(null, e);                 
    }finally {                                                      // close the connection with the DB
        try{         
            rset.close();                                             
            pst.close();                                                                               
        }catch(Exception e) {                                     
        }                                                         
    }
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        LogoutBtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NameTxt = new javax.swing.JTextField();
        PhoneTxt = new javax.swing.JTextField();
        EmailTxt = new javax.swing.JTextField();
        AddrTxt = new javax.swing.JTextField();
        IScmb = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        InterestedTxt = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        IPcmb = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        NameTxt1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PhoneTxt1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        EmailTxt1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        AddrTxt1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        IPcmb1 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        IScmb1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        InterestedTxt1 = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        IdLbl = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        ClIDcmb = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        PNameTxt = new javax.swing.JTextField();
        PtypeCmb = new javax.swing.JComboBox<>();
        priceTxt = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        IDlbl = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        PNameTxt1 = new javax.swing.JTextField();
        PtypeCmb1 = new javax.swing.JComboBox<>();
        priceTxt1 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        PNameTxt2 = new javax.swing.JTextField();
        PtypeCmb2 = new javax.swing.JComboBox<>();
        priceTxt2 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        PNameTxt3 = new javax.swing.JTextField();
        PtypeCmb3 = new javax.swing.JComboBox<>();
        priceTxt3 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        IDlbl1 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSeparator1.setForeground(new java.awt.Color(0, 153, 0));

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 43)); // NOI18N
        jLabel2.setText("TeleMarketing ");

        LogoutBtn.setText("Logout");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setText("Name ");

        jLabel3.setText("Phone");

        jLabel4.setText("Email");

        jLabel5.setText("Address");

        jLabel6.setText("Interested Products");

        jLabel7.setText("Interested Services");

        NameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTxtActionPerformed(evt);
            }
        });

        IScmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IScmbItemStateChanged(evt);
            }
        });
        IScmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IScmbMouseClicked(evt);
            }
        });

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        InterestedTxt.setColumns(20);
        InterestedTxt.setRows(5);
        jScrollPane1.setViewportView(InterestedTxt);

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Interested Products and Services");

        IPcmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        IPcmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IPcmbMouseClicked(evt);
            }
        });

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Add Inquiry");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Demo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                            .addComponent(NameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(78, 78, 78)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(IPcmb, javax.swing.GroupLayout.Alignment.LEADING, 0, 389, Short.MAX_VALUE)
                                .addComponent(AddrTxt)
                                .addComponent(PhoneTxt)
                                .addComponent(EmailTxt, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(2, 2, 2))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jScrollPane1)))
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(78, 78, 78)
                        .addComponent(IScmb, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(PhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(AddrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(IPcmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(IScmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton5)))
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("New Inquiry", jPanel1);

        jLabel9.setText("Name ");

        NameTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTxt1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Phone");

        PhoneTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneTxt1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Email");

        jLabel13.setText("Address");

        jLabel14.setText("Interested Products");

        IPcmb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        IPcmb1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                IPcmb1MouseMoved(evt);
            }
        });
        IPcmb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IPcmb1MouseClicked(evt);
            }
        });
        IPcmb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IPcmb1ActionPerformed(evt);
            }
        });

        jLabel15.setText("Interested Services");

        IScmb1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IScmb1ItemStateChanged(evt);
            }
        });
        IScmb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IScmb1MouseClicked(evt);
            }
        });
        IScmb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IScmb1ActionPerformed(evt);
            }
        });

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        InterestedTxt1.setColumns(20);
        InterestedTxt1.setRows(5);
        jScrollPane2.setViewportView(InterestedTxt1);

        jLabel16.setText("Interested Products and Services");

        jButton10.setText("Clear");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

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
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable1MouseMoved(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jLabel17.setText("Interested person table");

        jButton11.setText("Clear");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Update");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel18.setText("ID");

        IdLbl.setText(".......");

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Generate Quotation"));

        jLabel20.setText("Client ID");

        ClIDcmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ClIDcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClIDcmbActionPerformed(evt);
            }
        });

        jButton9.setText("Generate Quotation");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ClIDcmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(ClIDcmb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IPcmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IScmb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(IdLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(EmailTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AddrTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PhoneTxt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NameTxt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(IdLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NameTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PhoneTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EmailTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(AddrTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IPcmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IScmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton12)
                            .addComponent(jButton8)
                            .addComponent(jButton10))
                        .addComponent(jButton11)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inquiries", jPanel2);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Product"));

        jLabel19.setText("Product Name");

        jLabel21.setText("Type");

        jLabel22.setText("Price");

        PNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNameTxtActionPerformed(evt);
            }
        });

        PtypeCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "Computer ", "Monitor", "Router", "Printer", "Server\t", "Dongle", "Access Point", "Switch", "Mouse", "Keyboard", "Headphone", "All in One ", "UPS", "VGA ", "Ram", "Sound Card" }));

        jButton13.setText("Update");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Clear");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel26.setText("Product ID");

        IDlbl.setText("............");

        jButton17.setText("Delete");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22))
                    .addComponent(jLabel26))
                .addGap(57, 57, 57)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IDlbl)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PNameTxt)
                        .addComponent(PtypeCmb, 0, 275, Short.MAX_VALUE)
                        .addComponent(priceTxt)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 60, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(IDlbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(PNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(PtypeCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14)
                    .addComponent(jButton17))
                .addContainerGap())
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable2MouseMoved(evt);
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Product"));

        jLabel23.setText("Product Name");

        jLabel24.setText("Type");

        jLabel25.setText("Price");

        PNameTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNameTxt1ActionPerformed(evt);
            }
        });

        PtypeCmb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "Computer ", "Monitor", "Router", "Printer", "Server\t", "Dongle", "Access Point", "Switch", "Mouse", "Keyboard", "Headphone", "All in One ", "UPS", "VGA ", "Ram", "Sound Card" }));

        jButton15.setText("Add");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Clear");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGap(57, 57, 57)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PNameTxt1)
                    .addComponent(PtypeCmb1, 0, 293, Short.MAX_VALUE)
                    .addComponent(priceTxt1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 42, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(PNameTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(PtypeCmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(priceTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(616, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(266, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Products", jPanel3);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Service"));

        jLabel27.setText("Service Name");

        jLabel28.setText("Type");

        jLabel29.setText("Price");

        PNameTxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNameTxt2ActionPerformed(evt);
            }
        });

        PtypeCmb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internet Solutions", "Security Solutions", "Maintainence Solutions", "Network Solutions", "Domain Services", "Email Services", "CCTV Services", "Web Solutions" }));
        PtypeCmb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PtypeCmb2ActionPerformed(evt);
            }
        });

        jButton18.setText("Add");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Clear");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(57, 57, 57)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PNameTxt2)
                    .addComponent(PtypeCmb2, 0, 293, Short.MAX_VALUE)
                    .addComponent(priceTxt2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 42, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(PNameTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(PtypeCmb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(priceTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton19)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Service"));

        jLabel30.setText("Service Name");

        jLabel31.setText("Type");

        jLabel32.setText("Price");

        PNameTxt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNameTxt3ActionPerformed(evt);
            }
        });

        PtypeCmb3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internet Solutions", "Security Solutions", "Maintainence Solutions", "Network Solutions", "Domain Services", "Email Services", "CCTV Services", "Web Solutions" }));

        jButton20.setText("Update");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Clear");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel33.setText("Product ID");

        IDlbl1.setText("............");

        jButton22.setText("Delete");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel31)
                        .addComponent(jLabel32))
                    .addComponent(jLabel33))
                .addGap(57, 57, 57)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IDlbl1)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PNameTxt3)
                        .addComponent(PtypeCmb3, 0, 275, Short.MAX_VALUE)
                        .addComponent(priceTxt3)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 60, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(IDlbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(PNameTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(PtypeCmb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(priceTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton22))
                .addContainerGap())
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable3MouseMoved(evt);
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(628, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap(600, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jScrollPane5)
                    .addGap(6, 6, 6)))
        );

        jTabbedPane1.addTab("Services", jPanel4);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics1/rect.png"))); // NOI18N
        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31)
                        .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
               new Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void NameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//     InterestedTxt.append(IPcmb.getSelectedItem().toString());
//        InterestedTxt.append("\n");

       try {

            pst=con.prepareStatement("select Price from 8ware.product where Pro_Name='"+IPcmb.getSelectedItem().toString()+"'");
           
            ResultSet rs=pst.executeQuery();

            //adding data to combo
            while (rs.next()) {
                System.out.println(Double.toString(rs.getDouble("Price")));
                //DescLbl.append();
                
                
                InterestedTxt.append(IPcmb.getSelectedItem().toString()+"    Price = Rs."+rs.getDouble("Price"));
                InterestedTxt.append("\n");
            }
       }
       catch(Exception e){
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        NameTxt.setText("");
        PhoneTxt.setText("");
        EmailTxt.setText("");
        AddrTxt.setText("");
        InterestedTxt.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void IScmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IScmbItemStateChanged
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_IScmbItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
      try {

            pst=con.prepareStatement("select Price from 8ware.services where Ser_Name='"+IScmb.getSelectedItem().toString()+"'");
           
            ResultSet rs1=pst.executeQuery();

            //adding data to combo
            while (rs1.next()) {
                System.out.println(Double.toString(rs1.getDouble("Price")));
                //DescLbl.append();
                System.out.println("yes");
                
                InterestedTxt.append(IScmb.getSelectedItem().toString()+"    Price = Rs."+rs1.getDouble("Price"));
                InterestedTxt.append("\n");
            }
       }
       catch(Exception e){
           
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        boolean check = true;
        String ID = "Tele"+((int)(Math.random()*9000)+1000);
        String qry="select Per_ID from 8ware.person";

        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeQuery(qry);
            //JOptionPane.showMessageDialog(this, "Success !");
            ResultSet rs = stmt.getResultSet ();

            while (rs.next ())
            {

                String ids = rs.getString ("Per_ID");

                if(ids==ID)
                {
                    ID = "Man"+((int)(Math.random()*9000)+1000);
                }
            }

        } catch (Exception e) {
        }
        
        
        
        if((NameTxt.getText().equals(""))||(NameTxt.getText().matches(".*\\d.*")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Name", "Error", JOptionPane.ERROR_MESSAGE);
            NameTxt.setText("");
        }
        if((PhoneTxt.getText().equals(""))||(PhoneTxt.getText().contains("^[a-zA-Z]+$"))||(PhoneTxt.getText().length()!=10))
        {
            check = false;
            
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
            PhoneTxt.setText("");
        }

      
        if(EmailTxt.getText().equals(""))
        {
            check = false;
        
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            EmailTxt.setText("");
        }
        if((!EmailTxt.getText().contains("@"))||(!EmailTxt.getText().contains(".")))
        {
            check = false;
            //System.out.println("lol");
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            String ev="yes";
        }

        if(AddrTxt.getText().equals(""))
        {
            check = false;
          
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Address", "Error", JOptionPane.ERROR_MESSAGE);
            AddrTxt.setText("");
            System.out.println("hooray");
        }
        if(InterestedTxt.getText().equals(""))
        {
            check=false;
             Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please select interested Items", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("hooraaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhh");
        }

        if(check)
        {
        String qrrr="INSERT INTO `8ware`.`person` (`Per_ID`,`P_name`,`P_phone`,`P_email`,`P_address`,`Interested_Items`) VALUES('"+ID+"','"+NameTxt.getText()+"','"+PhoneTxt.getText()+"','"+EmailTxt.getText()+"','"+AddrTxt.getText()+"','"+InterestedTxt.getText()+"')";

            try {
              Connection c = DBAccess.getConnection();
            pst=c.prepareStatement(qrrr);
                pst.execute(qrrr);
                JOptionPane.showMessageDialog(this, "Success !");
                NameTxt.setText("");
                PhoneTxt.setText("");
                EmailTxt.setText("");
                AddrTxt.setText("");
            
                System.out.println("goes till here");
                InterestedTxt1.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeleMarketing.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        NameTxt.setText("DemoName");
        PhoneTxt.setText("0766500916");
        EmailTxt.setText("demo@demo.com");
        AddrTxt.setText("DemoAddress");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void NameTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTxt1ActionPerformed

    private void IScmb1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IScmb1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_IScmb1ItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {

            pst=con.prepareStatement("select Price from product where Pro_Name='"+IPcmb1.getSelectedItem().toString()+"'");
           
            ResultSet rs=pst.executeQuery();

            //adding data to combo
            while (rs.next()) {
                System.out.println(Double.toString(rs.getDouble("Price")));
                //DescLbl.append();
                
                
                InterestedTxt1.append(IPcmb1.getSelectedItem().toString()+"    Price = Rs."+rs.getDouble("Price"));
                InterestedTxt1.append("\n");
            }
       }
       catch(Exception e){
           
       }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
          try {

            pst=con.prepareStatement("select Price from 8ware.services where Ser_Name='"+IScmb1.getSelectedItem().toString()+"'");
           
            ResultSet rs1=pst.executeQuery();

            //adding data to combo
            while (rs1.next()) {
                System.out.println(Double.toString(rs1.getDouble("Price")));
                //DescLbl.append();
                System.out.println("yes");
                
                InterestedTxt1.append(IScmb1.getSelectedItem().toString()+"    Price = Rs."+rs1.getDouble("Price"));
                InterestedTxt1.append("\n");
            }
       }
       catch(Exception e){
           
       }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        NameTxt1.setText("");
        PhoneTxt1.setText("");
        EmailTxt1.setText("");
        AddrTxt1.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  
        try{
            int row=jTable1.getSelectedRow();
            String Table_click1=(jTable1.getModel().getValueAt(row,0).toString());
//            String sql1 ="select * from vehicle_registration where bill_ID='"+Table_click1+"' ";
//            String sql2 ="select * from payments where billID='"+Table_click1+"' ";
//            
            String sql1 = "Select  * from `8ware`.person where `Per_ID`='"+Table_click1+"'";
            
            pst =con.prepareStatement(sql1);
            rset=pst.executeQuery();
            if(rset.next()){
                String id=rset.getString("Per_ID");
                IdLbl.setText(id);
                
                String add1= rset.getString("P_name");
                NameTxt1.setText(add1);
                
                String add2= rset.getString("P_phone");
                PhoneTxt1.setText(add2);
                
                String add3= rset.getString("P_email");
                EmailTxt1.setText(add3);
                
                String add4= rset.getString("P_address");
                AddrTxt1.setText(add4);
                
                String add5= rset.getString("Interested_Items");
                InterestedTxt1.setText(add5);
                     
            }   
              rset.close();
              pst.close();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                rset.close();
                pst.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        boolean check=true;
        
        if((NameTxt1.getText().equals(""))||(NameTxt1.getText().matches(".*\\d.*")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Name", "Error", JOptionPane.ERROR_MESSAGE);
            NameTxt1.setText("");
        }
        if((PhoneTxt1.getText().equals(""))||(PhoneTxt1.getText().contains("^[a-zA-Z]+$"))||(PhoneTxt1.getText().length()!=10))
        {
            check = false;
            
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
            PhoneTxt1.setText("");
        }

      
        if(EmailTxt1.getText().equals(""))
        {
            check = false;
        
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            EmailTxt1.setText("");
        }
        if((!EmailTxt1.getText().contains("@"))||(!EmailTxt1.getText().contains(".")))
        {
            check = false;
            //System.out.println("lol");
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            String ev="yes";
        }

        if(AddrTxt1.getText().equals(""))
        {
            check = false;
          
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Address", "Error", JOptionPane.ERROR_MESSAGE);
            AddrTxt1.setText("");
            System.out.println("hooray");
        }
        if(InterestedTxt1.getText().equals(""))
        {
            check=false;
             Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please select interested Items", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("elakiri");
        }

        if(check)
        {
        String qrrr="UPDATE `8ware`.person SET `P_name` = '"+NameTxt1.getText().toString()+"', `P_phone` = '"+PhoneTxt1.getText().toString()+"', `P_email` = '"+EmailTxt1.getText().toString()+"',`P_address`='"+AddrTxt1.getText().toString()+"', `Interested_Items` = '"+InterestedTxt1.getText().toString()+"' WHERE `Per_ID` = '"+IdLbl.getText().toString()+"' ";

            try {
              Connection c = DBAccess.getConnection();
            pst=c.prepareStatement(qrrr);
                pst.execute(qrrr);
                JOptionPane.showMessageDialog(this, "Success !");
                NameTxt.setText("");
                PhoneTxt.setText("");
                EmailTxt.setText("");
                AddrTxt.setText("");
            
                System.out.println("goes till here");

            } catch (Exception e) {
                Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter data to update", "Error", JOptionPane.ERROR_MESSAGE);
        
            }
            finally{
            try {
                pst.close();
            } catch (SQLException ex) {
               Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter data to update", "Error", JOptionPane.ERROR_MESSAGE);
        
            }
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        InterestedTxt1.setText("");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void PhoneTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneTxt1ActionPerformed

    private void IScmb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IScmb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IScmb1ActionPerformed

    private void jTable1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseMoved
        // TODO add your handling code here:
        Update_Table();
    }//GEN-LAST:event_jTable1MouseMoved

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            String a= IdLbl.getText().toString();
            String query2="delete from 8ware.person where Per_ID = '"+a+"'";
            //

            //adding data to combo
            stmt.executeUpdate(query2);
            JOptionPane.showMessageDialog(this, "Success");
            
             NameTxt1.setText("");
        PhoneTxt1.setText("");
        EmailTxt1.setText("");
        AddrTxt1.setText("");
        InterestedTxt1.setText("");

        } catch (Exception e) {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter data to update", "Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void IPcmb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IPcmb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IPcmb1ActionPerformed

    private void ClIDcmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClIDcmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClIDcmbActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
                      
        try{
            String value1 = ClIDcmb.getSelectedItem().toString();
            System.out.println("value : " +  value1); 
           // String report ="C:\\Users\\chami\\OneDrive\\Documents\\NetBeansProjects\\proj_copy\\first_report1.jrxml";
          JasperDesign jd=JRXmlLoader.load("src\\CafetariaNTele\\quo.jrxml");
            String sql = "SELECT 8ware.person.`Per_ID` AS person_Per_ID,person.`P_name` AS person_P_name,person.`P_phone` AS person_P_phone,person.`P_email` AS person_P_email, person.`P_address` AS person_P_address, person.`Interested_Items` AS person_Interested_Items FROM 8ware.person  WHERE `Per_ID`='"+value1+"'";
            
            
            
            JRDesignQuery newQuery = new JRDesignQuery();
           newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("gggg");
        }finally {                                                      //  close the connection
            try{
            }catch(Exception e) {
            }
        }
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void PNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PNameTxtActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        
        boolean check=true;
        if((PNameTxt.getText().toString().equals("")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Product Name", "Error", JOptionPane.ERROR_MESSAGE);
            PNameTxt.setText("");
        }
        

        if((priceTxt.getText().toString().equals(""))||(priceTxt.getText().toString().equals("\"^[a-zA-Z]+$\"")))
        {
            check = false;

            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Price", "Error", JOptionPane.ERROR_MESSAGE);
            priceTxt.setText("");
        }
        if(check){
            

            String qrrr="UPDATE 8ware.product SET `Pro_Name` = '"+PNameTxt.getText().toString()+"', `Pro_Type` = '"+PtypeCmb.getSelectedItem().toString()+"', `Price` = '"+priceTxt.getText().toString()+"' WHERE `Pro_ID` = '"+IDlbl.getText().toString()+"' ";

            try {
              Connection c = DBAccess.getConnection();
            pst=c.prepareStatement(qrrr);
                pst.execute(qrrr);
                JOptionPane.showMessageDialog(this, "Success !");
                PNameTxt.setText("");
                priceTxt.setText("");
            IDlbl.setText("");
                System.out.println("goes till here");
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeleMarketing.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void IPcmb1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IPcmb1MouseMoved

        
        
        
    
    }//GEN-LAST:event_IPcmb1MouseMoved

    private void IPcmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IPcmbMouseClicked
        // TODO add your handling code here:
        boolean check = true;

        

        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            
            //JOptionPane.showMessageDialog(this, "Success !");
            
            ResultSet rs1=stmt.executeQuery("select * from 8ware.product");
            //ResultSet rs2=s.executeQuery("select * from 8ware.person");
            Vector v=new Vector();
            while(rs1.next())
            {
                v.add(rs1.getString("Pro_Name"));
            }
            IPcmb.setModel(new DefaultComboBoxModel(v));
            IPcmb1.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_IPcmbMouseClicked

    private void IPcmb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IPcmb1MouseClicked
        // TODO add your handling code here:
        
         try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            
            //JOptionPane.showMessageDialog(this, "Success !");
            
            ResultSet rs1=stmt.executeQuery("select * from 8ware.product");
            //ResultSet rs2=s.executeQuery("select * from 8ware.person");
            Vector v=new Vector();
            while(rs1.next())
            {
                v.add(rs1.getString("Pro_Name"));
            }
            IPcmb.setModel(new DefaultComboBoxModel(v));
            IPcmb1.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_IPcmb1MouseClicked

    private void PNameTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNameTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PNameTxt1ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        
        boolean check=true;
        if((PNameTxt1.getText().toString().equals("")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Product Name", "Error", JOptionPane.ERROR_MESSAGE);
            PNameTxt1.setText("");
        }
        

        if((priceTxt1.getText().toString().equals(""))||(priceTxt1.getText().toString().equals("\"^[a-zA-Z]+$\"")))
        {
            check = false;

            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Price", "Error", JOptionPane.ERROR_MESSAGE);
            priceTxt1.setText("");
        }
        if(check){
            String ID="Pro"+((int)(Math.random()*9000)+1000);
        String qry="select Pro_ID from 8ware.product";

        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            //stmt.executeQuery(qry);
            //JOptionPane.showMessageDialog(this, "Success !");
            ResultSet rs = stmt.getResultSet ();

            while (rs.next ())
            {

                String ids = rs.getString ("Pro_ID");

                if(ids==ID)
                {
                    ID = "Pro"+((int)(Math.random()*9000)+1000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            

            String qrrr="INSERT INTO 8ware.product(`Pro_ID`,`Pro_Name` ,`Pro_Type`,`Price`) VALUES('"+ID+"','"+PNameTxt1.getText().toString()+"','"+PtypeCmb1.getSelectedItem().toString()+"','"+priceTxt1.getText().toString()+"')";

            try {
              Connection c = DBAccess.getConnection();
            pst=c.prepareStatement(qrrr);
                pst.execute(qrrr);
                JOptionPane.showMessageDialog(this, "Success !");
                PNameTxt1.setText("");
                priceTxt1.setText("");
            //IDlbl.setText("");
                System.out.println("goes till here");
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeleMarketing.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        
        try{
            int row=jTable2.getSelectedRow();
            String Table_click1=(jTable2.getModel().getValueAt(row,0).toString());
//            String sql1 ="select * from vehicle_registration where bill_ID='"+Table_click1+"' ";
//            String sql2 ="select * from payments where billID='"+Table_click1+"' ";
//            
            String sql1 = "Select  * from `8ware`.product where `Pro_ID`='"+Table_click1+"'";
            
            pst =con.prepareStatement(sql1);
            rset=pst.executeQuery();
            if(rset.next()){
                String id=rset.getString("Pro_ID");
                IDlbl.setText(id);
                
                String add1= rset.getString("Pro_name");
                PNameTxt.setText(add1);
                
                String add2= rset.getString("Price");
                priceTxt.setText(add2);
                
                     
            }   
              rset.close();
              pst.close();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                rset.close();
                pst.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        PNameTxt.setText("");
        priceTxt.setText("");
        IDlbl.setText("");
        
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTable2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseMoved
        // TODO add your handling code here:
        Update_Table2();
    }//GEN-LAST:event_jTable2MouseMoved

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        PNameTxt1.setText("");
        priceTxt1.setText("");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void PNameTxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNameTxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PNameTxt2ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        boolean check=true;
        if((PNameTxt2.getText().toString().equals(""))||(PNameTxt2.getText().toString().matches(".*\\d.*")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Service Name", "Error", JOptionPane.ERROR_MESSAGE);
            PNameTxt2.setText("");
        }
        

        if((priceTxt2.getText().toString().equals(""))||(priceTxt2.getText().toString().equals("\"^[a-zA-Z]+$\"")))
        {
            check = false;

            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Price", "Error", JOptionPane.ERROR_MESSAGE);
            priceTxt2.setText("");
        }
        if(check){
            String ID="Ser"+((int)(Math.random()*9000)+1000);
        String qry="select * from 8ware.services";

        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeQuery(qry);
            //JOptionPane.showMessageDialog(this, "Success !");
            ResultSet rs = stmt.getResultSet ();

            while (rs.next ())
            {

                String ids = rs.getString ("Ser_ID");

                if(ids==ID)
                {
                    ID = "Ser"+((int)(Math.random()*9000)+1000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            

            String qrrr="INSERT INTO 8ware.services(`Ser_ID`,`Ser_Name` ,`SerType`,`Price`) VALUES('"+ID+"','"+PNameTxt2.getText().toString()+"','"+PtypeCmb2.getSelectedItem().toString()+"','"+priceTxt2.getText().toString()+"')";

            try {
              Connection c = DBAccess.getConnection();
            pst=c.prepareStatement(qrrr);
                pst.execute(qrrr);
                JOptionPane.showMessageDialog(this, "Success !");
                PNameTxt2.setText("");
                priceTxt2.setText("");
            //IDlbl.setText("");
                System.out.println("goes till here");
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeleMarketing.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        PNameTxt2.setText("");
        priceTxt2.setText("");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void PNameTxt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNameTxt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PNameTxt3ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        boolean check=true;
        if((PNameTxt3.getText().toString().equals(""))||(PNameTxt2.getText().toString().matches(".*\\d.*")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Product Name", "Error", JOptionPane.ERROR_MESSAGE);
            PNameTxt3.setText("");
        }
        

        if((priceTxt3.getText().toString().equals(""))||(priceTxt3.getText().toString().equals("\"^[a-zA-Z]+$\"")))
        {
            check = false;

            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Price", "Error", JOptionPane.ERROR_MESSAGE);
            priceTxt3.setText("");
        }
        if(check){
            

            String qrrr="UPDATE 8ware.services SET `Ser_Name` = '"+PNameTxt3.getText().toString()+"', `SerType` = '"+PtypeCmb3.getSelectedItem().toString()+"', `Price` = '"+priceTxt3.getText().toString()+"' WHERE `Ser_ID` = '"+IDlbl1.getText().toString()+"' ";

            try {
              Connection c = DBAccess.getConnection();
            pst=c.prepareStatement(qrrr);
                pst.execute(qrrr);
                JOptionPane.showMessageDialog(this, "Success !");
                PNameTxt3.setText("");
                priceTxt3.setText("");
            IDlbl1.setText("");
                System.out.println("goes till here");
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeleMarketing.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        PNameTxt3.setText("");
        priceTxt3.setText("");
        IDlbl1.setText("");
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTable3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseMoved
        // TODO add your handling code here:
        Update_Table3();
    }//GEN-LAST:event_jTable3MouseMoved

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        try{
            int row=jTable3.getSelectedRow();
            String Table_click1=(jTable3.getModel().getValueAt(row,0).toString());
//            String sql1 ="select * from vehicle_registration where bill_ID='"+Table_click1+"' ";
//            String sql2 ="select * from payments where billID='"+Table_click1+"' ";
//            
            String sql1 = "Select  * from `8ware`.services where `Ser_ID`='"+Table_click1+"'";
            
            pst =con.prepareStatement(sql1);
            rset=pst.executeQuery();
            if(rset.next()){
                String id=rset.getString("Ser_ID");
                IDlbl1.setText(id);
                
                String add1= rset.getString("Ser_name");
                PNameTxt3.setText(add1);
                
                String add2= rset.getString("Price");
                priceTxt3.setText(add2);
                
                     
            }   
              rset.close();
              pst.close();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally {                                                      //  close the connection
            try{
                rset.close();
                pst.close();
            }catch(Exception e) {
            }
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            String a= IDlbl.getText().toString();
            String query2="delete from 8ware.product where Pro_ID = '"+a+"'";
            //

            //adding data to combo
            stmt.executeUpdate(query2);
            JOptionPane.showMessageDialog(this, "Success");
            
             PNameTxt.setText("");
        IDlbl.setText("");
        priceTxt.setText("");
        

        } catch (Exception e) {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter data to update", "Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void PtypeCmb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PtypeCmb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PtypeCmb2ActionPerformed

    private void IScmb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IScmb1MouseClicked
        // TODO add your handling code here:
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            
            //JOptionPane.showMessageDialog(this, "Success !");
            
            ResultSet rs1=stmt.executeQuery("select * from 8ware.services");
            //ResultSet rs2=s.executeQuery("select * from 8ware.person");
            Vector v=new Vector();
            while(rs1.next())
            {
                v.add(rs1.getString("Ser_Name"));
            }
            IScmb.setModel(new DefaultComboBoxModel(v));
            IScmb1.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_IScmb1MouseClicked

    private void IScmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IScmbMouseClicked
        // TODO add your handling code here:
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            
            //JOptionPane.showMessageDialog(this, "Success !");
            
            ResultSet rs1=stmt.executeQuery("select * from 8ware.services");
            //ResultSet rs2=s.executeQuery("select * from 8ware.person");
            Vector v=new Vector();
            while(rs1.next())
            {
                v.add(rs1.getString("Ser_Name"));
            }
            IScmb.setModel(new DefaultComboBoxModel(v));
            IScmb1.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_IScmbMouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            String a= IDlbl1.getText().toString();
            String query2="delete from 8ware.services where Ser_ID = '"+a+"'";
            //

            //adding data to combo
            stmt.executeUpdate(query2);
            JOptionPane.showMessageDialog(this, "Success");
            
             PNameTxt3.setText("");
        IDlbl1.setText("");
        priceTxt3.setText("");
        

        } catch (Exception e) {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter data to update", "Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }//GEN-LAST:event_jButton22ActionPerformed

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
            java.util.logging.Logger.getLogger(TeleMarketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeleMarketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeleMarketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeleMarketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TeleMarketing().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(TeleMarketing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddrTxt;
    private javax.swing.JTextField AddrTxt1;
    private javax.swing.JComboBox<String> ClIDcmb;
    private javax.swing.JTextField EmailTxt;
    private javax.swing.JTextField EmailTxt1;
    private javax.swing.JLabel IDlbl;
    private javax.swing.JLabel IDlbl1;
    private javax.swing.JComboBox<String> IPcmb;
    private javax.swing.JComboBox<String> IPcmb1;
    private javax.swing.JComboBox<String> IScmb;
    private javax.swing.JComboBox<String> IScmb1;
    private javax.swing.JLabel IdLbl;
    private javax.swing.JTextArea InterestedTxt;
    private javax.swing.JTextArea InterestedTxt1;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JTextField NameTxt;
    private javax.swing.JTextField NameTxt1;
    private javax.swing.JTextField PNameTxt;
    private javax.swing.JTextField PNameTxt1;
    private javax.swing.JTextField PNameTxt2;
    private javax.swing.JTextField PNameTxt3;
    private javax.swing.JTextField PhoneTxt;
    private javax.swing.JTextField PhoneTxt1;
    private javax.swing.JComboBox<String> PtypeCmb;
    private javax.swing.JComboBox<String> PtypeCmb1;
    private javax.swing.JComboBox<String> PtypeCmb2;
    private javax.swing.JComboBox<String> PtypeCmb3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField priceTxt;
    private javax.swing.JTextField priceTxt1;
    private javax.swing.JTextField priceTxt2;
    private javax.swing.JTextField priceTxt3;
    // End of variables declaration//GEN-END:variables
}
