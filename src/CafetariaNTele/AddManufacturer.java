package CafetariaNTele;


import HRM.Login;
import static com.oracle.webservices.internal.api.databinding.DatabindingModeFeature.ID;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.sql.PreparedStatement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Visal
 */
public class AddManufacturer extends javax.swing.JFrame {

    /**
     * Creates new form AddManufacturer
     */
    PreparedStatement pst=null;
    ResultSet rset=null;
    ResultSet rs5=null;
    DBAccess db=new DBAccess();
    private String ev;
    public AddManufacturer() {
        initComponents();
        System.out.println("good here");
        String sql ="SELECT * FROM 8ware.manufacturer";
        String query1="select UserName from 8ware.log";
        String query2="select ManufactID from 8ware.manufacturer";
        System.out.println("good here too");
        
         
        
        try {
        Connection c = DBAccess.getConnection();
        Statement stmt = c.createStatement();
        Statement stm = c.createStatement();
        Statement st = c.createStatement();
        Statement s1 = c.createStatement();
//     
            ResultSet rs = stmt.executeQuery(query1);
            
            
            //to set user name
            while (rs.next()) {
            String usr = rs.getString("UserName");
           UserLbl.setText(usr);
         }
            
            ResultSet rs1 = stm.executeQuery(sql);
           ManuTable1.setModel(DbUtils.resultSetToTableModel(rs1));
           //adding data to combo
           ResultSet rs2 = st.executeQuery(query2);
            while (rs2.next()) {
           ManuIDCmb.addItem(rs2.getString("ManufactID"));
            }
            
            
        
    


        
    } catch (Exception e) {
    }
    }
//   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogoutBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UserLbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        HomeBtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        nameTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        contactTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AddrTxt = new javax.swing.JTextArea();
        AddItemBtn = new javax.swing.JButton();
        PhoTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        NameLbl = new javax.swing.JLabel();
        ContactLbl = new javax.swing.JLabel();
        PhoneLbl = new javax.swing.JLabel();
        AddrLbl = new javax.swing.JLabel();
        nameTxt1 = new javax.swing.JTextField();
        contactTxt1 = new javax.swing.JTextField();
        PhoTxt1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        AddrTxt1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        ManuIDCmb = new javax.swing.JComboBox<>();
        UpdBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        emailTxt1 = new javax.swing.JTextField();
        DelBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ManuTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LogoutBtn.setText("Logout");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 43)); // NOI18N
        jLabel2.setText("Cafeteria Inventory");

        jLabel3.setText("Logged in as ");

        UserLbl.setText("admin");
        UserLbl.setName("userLabel"); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(0, 153, 0));

        HomeBtn.setText("Home");
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeBtnActionPerformed(evt);
            }
        });

        jLabel6.setText(" Name");

        jLabel7.setText("Contact Person");

        contactTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contactTxtFocusGained(evt);
            }
        });
        contactTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactTxtMouseClicked(evt);
            }
        });
        contactTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contactTxtKeyPressed(evt);
            }
        });

        jLabel12.setText("Phone");

        jLabel5.setText("Address");

        AddrTxt.setColumns(20);
        AddrTxt.setRows(5);
        AddrTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddrTxtMouseClicked(evt);
            }
        });
        AddrTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AddrTxtKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(AddrTxt);

        AddItemBtn.setText("Add ");
        AddItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemBtnActionPerformed(evt);
            }
        });

        PhoTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhoTxtMouseClicked(evt);
            }
        });
        PhoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoTxtActionPerformed(evt);
            }
        });
        PhoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PhoTxtKeyPressed(evt);
            }
        });

        jLabel8.setText("Email");

        emailTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailTxtMouseClicked(evt);
            }
        });
        emailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxtActionPerformed(evt);
            }
        });
        emailTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailTxtKeyPressed(evt);
            }
        });

        jButton1.setText("Demo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(199, 199, 199)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(contactTxt)
                            .addComponent(PhoTxt)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                            .addComponent(nameTxt))))
                .addGap(147, 147, 147))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddItemBtn)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Add Manufacturer", jPanel1);

        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel2MouseMoved(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });

        NameLbl.setText(" Name");

        ContactLbl.setText("Contact Person");

        PhoneLbl.setText("Phone");

        AddrLbl.setText("Address");

        nameTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTxt1ActionPerformed(evt);
            }
        });

        contactTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactTxt1ActionPerformed(evt);
            }
        });

        PhoTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoTxt1ActionPerformed(evt);
            }
        });

        AddrTxt1.setColumns(20);
        AddrTxt1.setRows(5);
        jScrollPane3.setViewportView(AddrTxt1);

        jLabel1.setText("Manufacturer ID");

        ManuIDCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ManuIDCmbItemStateChanged(evt);
            }
        });
        ManuIDCmb.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ManuIDCmbFocusGained(evt);
            }
        });
        ManuIDCmb.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ManuIDCmbPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        ManuIDCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManuIDCmbActionPerformed(evt);
            }
        });

        UpdBtn.setText("Update");
        UpdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Email");

        emailTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxt1ActionPerformed(evt);
            }
        });

        DelBtn.setText("Delete");
        DelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelBtnActionPerformed(evt);
            }
        });

        ManuTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        ManuTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ManuTable1MouseMoved(evt);
            }
        });
        ManuTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ManuTable1FocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(ManuTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(DelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(UpdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(ContactLbl)
                                    .addComponent(AddrLbl)
                                    .addComponent(jLabel4)
                                    .addComponent(PhoneLbl)
                                    .addComponent(NameLbl))
                                .addGap(146, 146, 146)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ManuIDCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(contactTxt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameTxt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PhoTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ManuIDCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameLbl))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contactTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ContactLbl))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PhoneLbl)
                            .addComponent(PhoTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(emailTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(AddrLbl)
                                .addGap(0, 69, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdBtn)
                    .addComponent(DelBtn))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Update Manufacturer", jPanel2);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics1/rect.png"))); // NOI18N
        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(16, 16, 16)
                                .addComponent(UserLbl)
                                .addGap(105, 105, 105))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(HomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(UserLbl))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
                  new Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void HomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeBtnActionPerformed
        this.dispose();
        MainMenu m=new MainMenu();
        m.setVisible(true);
    }//GEN-LAST:event_HomeBtnActionPerformed

    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseReleased

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        ManuIDCmb.removeAllItems();
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            Statement stm = c.createStatement();
            Statement st = c.createStatement();
            Statement s1 = c.createStatement();
            String query2="select ManufactID from 8ware.manufacturer";
            //

            //adding data to combo
            ResultSet rs2 = st.executeQuery(query2);
            while (rs2.next()) {
                ManuIDCmb.addItem(rs2.getString("ManufactID"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel2MouseMoved

    private void ManuTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ManuTable1FocusGained
        // TODO add your handling code here:
         try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();

            String query1="select * from 8ware.manufacturer";
            ResultSet rs1 = stmt.executeQuery(query1);
            ManuTable1.setModel(DbUtils.resultSetToTableModel(rs1));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ManuTable1FocusGained

    private void DelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelBtnActionPerformed
        // TODO add your handling code here:

        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            String a= ManuIDCmb.getSelectedItem().toString();
            String query2="delete from 8ware.manufacturer where ManufactID = '"+a+"'";
            //

            //adding data to combo
            stmt.executeUpdate(query2);
            JOptionPane.showMessageDialog(this, "Success");
            nameTxt1.setText("");
            contactTxt1.setText("");
            PhoTxt1.setText("");
            emailTxt1.setText("");
            AddrTxt1.setText("");

        } catch (Exception e) {
        }
    }//GEN-LAST:event_DelBtnActionPerformed

    private void emailTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTxt1ActionPerformed

    private void UpdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdBtnActionPerformed
        // TODO add your handling code here:
        String name=nameTxt1.getText();
        String contact=contactTxt1.getText();
        String phone=PhoTxt1.getText();
        String address=AddrTxt1.getText();

        String name1=nameTxt1.getText();
        String contact1=contactTxt1.getText();
        String phone1=PhoTxt1.getText();
        String email1=emailTxt1.getText();
        String address1=AddrTxt1.getText();
        boolean check = true;
        String manidd=ManuIDCmb.getSelectedItem().toString();

        String email = emailTxt1.getText();

        if((name.equals(""))||(name.matches(".*\\d.*")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Name", "Error", JOptionPane.ERROR_MESSAGE);
            nameTxt1.setText("");
        }
        if((contact.equals(""))||(contactTxt1.getText().matches(".*\\d.*")))
        {
            check = false;
            name1="go";
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Contact Name", "Error", JOptionPane.ERROR_MESSAGE);
            contactTxt1.setText("");
        }

        if((phone.equals(""))||(phone.contains("^[a-zA-Z]+$"))||(phone.length()!=10))
        {
            check = false;
            contact1="go";
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
            PhoTxt1.setText("");
        }
        if(email.equals(""))
        {
            check = false;
            phone1="go";
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            emailTxt1.setText("");
        }
        if((!email.contains("@"))||(!email.contains(".")))
        {
            check = false;
            //System.out.println("lol");
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            String ev="yes";
        }

        if(address.equals(""))
        {
            check = false;
            email1="go";
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Address", "Error", JOptionPane.ERROR_MESSAGE);
            emailTxt1.setText("");
        }

        if(check)
        {
            System.out.println("lol");

            try {
                String qrrr="UPDATE `8ware`.manufacturer SET `Name` = '"+name+"', `ContactPerson` = '"+contact+"', `Phone` = '"+phone+"',`Email`='"+email+"', `Address` = '"+address+"' WHERE `ManufactID` = '"+manidd+"' ";
                System.out.println("Go till here");
                Connection c = DBAccess.getConnection();
                Statement stmt = c.createStatement();
                stmt.executeUpdate(qrrr);
                JOptionPane.showMessageDialog(this, "Success !");
                nameTxt.setText("");
                contactTxt.setText("");
                PhoTxt.setText("");
                AddrTxt.setText("");
                emailTxt.setText("");
                System.out.println("goes till here");

            } catch (Exception exception) {
            }

            //} catch (Exception e) {
            //  e.printStackTrace();
            //}
        }

        //}
    }//GEN-LAST:event_UpdBtnActionPerformed

    private void ManuIDCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManuIDCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ManuIDCmbActionPerformed

    private void ManuIDCmbPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ManuIDCmbPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        //        nameTxt1.setText("");
        //        contactTxt1.setText("");
        //        PhoTxt1.setText("");
        //        emailTxt1.setText("");
        //        AddrTxt1.setText("");

        String manid=ManuIDCmb.getSelectedItem().toString();

        //String query="INSERT INTO 8ware.log(`UserName`, `Password`) VALUES ("+"\""+username+"\""+","+"\""+pwd+"\""+")";
        String qrr="select * from 8ware.manufacturer where ManufactID = '"+manid+"'";
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeQuery(qrr);
            //JOptionPane.showMessageDialog(this, "Success !");
            ResultSet ns = stmt.getResultSet ();

            ns.next();
            String nm=ns.getString("Name");
            String cp=ns.getString("ContactPerson");
            String ad=ns.getString("Address");
            String ph=ns.getString("Phone");
            String em=ns.getString("Email");
            nameTxt1.setText(nm);
            contactTxt1.setText(cp);
            PhoTxt1.setText(ph);
            AddrTxt1.setText(ad);
            emailTxt1.setText(em);
            //String qrnr="select * from 8ware.manufacturer where ManufactID = '"+manid+"'";

        } catch (Exception e) {
            System.out.println("error");
        }

    }//GEN-LAST:event_ManuIDCmbPopupMenuWillBecomeInvisible

    private void ManuIDCmbFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ManuIDCmbFocusGained
        // TODO add your handling code here:
        ManuIDCmb.removeAllItems();
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            Statement stm = c.createStatement();
            Statement st = c.createStatement();
            Statement s1 = c.createStatement();
            String query2="select ManufactID from 8ware.manufacturer";
            //

            //adding data to combo
            ResultSet rs2 = st.executeQuery(query2);
            while (rs2.next()) {
                ManuIDCmb.addItem(rs2.getString("ManufactID"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ManuIDCmbFocusGained

    private void ManuIDCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ManuIDCmbItemStateChanged
        // TODO add your handling code here:

        System.out.println("good here too tooo");

    }//GEN-LAST:event_ManuIDCmbItemStateChanged

    private void PhoTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoTxt1ActionPerformed

    private void contactTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactTxt1ActionPerformed

    private void nameTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTxt1ActionPerformed

    private void emailTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTxtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_emailTxtKeyPressed

    private void emailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTxtActionPerformed

    private void emailTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailTxtMouseClicked
        // TODO add your handling code here:
        String p=PhoTxt.getText();
        if((p.matches("^[a-zA-Z]+$"))||(p.equals(""))||(p.length()!=10))
        {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
            PhoTxt.setText("");
        }
    }//GEN-LAST:event_emailTxtMouseClicked

    private void PhoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoTxtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_PhoTxtKeyPressed

    private void PhoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoTxtActionPerformed

    private void PhoTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhoTxtMouseClicked
        // TODO add your handling code here:
        String contact=contactTxt.getText();
        if((contact.matches(".*\\d.*"))||(contact.equals("")))
        {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Contact Name", "Error", JOptionPane.ERROR_MESSAGE);
            contactTxt.setText("");
        }
    }//GEN-LAST:event_PhoTxtMouseClicked

    private void AddItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemBtnActionPerformed
        // TODO add your handling code here:
        String name=nameTxt.getText();
        String contact=contactTxt.getText();
        String phone=PhoTxt.getText();
        String address=AddrTxt.getText();
        String email = emailTxt.getText();
        boolean check = true;

        String ID = "Man"+((int)(Math.random()*9000)+1000);
        String qry="select ManufactID from 8ware.manufacturer";

        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeQuery(qry);
            //JOptionPane.showMessageDialog(this, "Success !");
            ResultSet rs = stmt.getResultSet ();

            while (rs.next ())
            {

                String ids = rs.getString ("ManufactID");

                if(ids==ID)
                {
                    ID = "Man"+((int)(Math.random()*9000)+1000);
                }
            }

        } catch (Exception e) {
        }

        if((name.equals(""))||(name.matches(".*\\d.*")))
        {
            check = false;
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Name", "Error", JOptionPane.ERROR_MESSAGE);
            nameTxt1.setText("");
        }
        if((contact.equals(""))||(contact.matches(".*\\d.*")))
        {
            check = false;

            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Contact Name", "Error", JOptionPane.ERROR_MESSAGE);
            contactTxt1.setText("");
        }

        if((phone.equals(""))||(phone.contains("^[a-zA-Z]+$"))||(phone.length()!=10))
        {
            check = false;

            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
            PhoTxt1.setText("");
        }
        if(email.equals(""))
        {
            check = false;

            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            emailTxt1.setText("");
        }
        if((!email.contains("@"))||(!email.contains(".")))
        {
            check = false;
            //System.out.println("lol");
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            String ev="yes";
        }

        if(address.equals(""))
        {
            check = false;

            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Address", "Error", JOptionPane.ERROR_MESSAGE);
            emailTxt1.setText("");
        }
        if(check){

            String query="INSERT INTO 8ware.manufacturer(`ManufactID`,`Name`, `ContactPerson`,`Phone`,`Email`,`Address`) VALUES ("+"\""+ID+"\""+","+"\""+name+"\""+","+"\""+contact+"\""+","+"\""+phone+"\""+",'"+email+"',"+"\""+address+"\""+")";

            try {
                Connection c = DBAccess.getConnection();
                Statement stmt = c.createStatement();
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Success !");
                nameTxt.setText("");
                contactTxt.setText("");
                PhoTxt.setText("");
                AddrTxt.setText("");
                emailTxt.setText("");

            } catch (Exception e) {
                System.out.println("error");

            }

        }
        //        else {
            //            Component panel = null;
            //            JOptionPane.showMessageDialog(panel,"Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
            //        }
    }//GEN-LAST:event_AddItemBtnActionPerformed

    private void AddrTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddrTxtKeyPressed
        // TODO add your handling code here:

        //        if((e.matches("^[a-zA-Z]+$"))||(e.equals("")))
        //        {
            //            Component panel = null;
            //            JOptionPane.showMessageDialog(panel,"Please enter a valid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
            //            nameTxt.setText("");
            //        }
    }//GEN-LAST:event_AddrTxtKeyPressed

    private void AddrTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddrTxtMouseClicked
        // TODO add your handling code here:
        String em=emailTxt.getText();
        if(em.equals(""))
        {
            Component panel=null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            emailTxt.setText("");
        }
        else{
            try {

                String email[]=em.split("@");
                String em1=email[1].toString();
                String emw=em1;
                System.out.println(emw);
                //String se[]=emw.split(".");
                //            String eq=se[0].toString();
                //            String e1=eq;
                //
                //            String ew=se[1].toString();
                //              String e2=ew;
                //if((e1.contains("."))||(e1.equals(""))||(e2.contains("."))||(e2.equals("")))
                //                    {
                    //                      Component panel=null;
                    //         JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
                    //         emailTxt.setText("");
                    //                    }

                if((emw.equals("")))
                {
                    Component panel=null;
                    JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
                    emailTxt.setText("");
                }
                else if(emw.contains("."))
                {
                    String ev="yes";
                }
                else if(ev!="yes")
                {
                    Component panel=null;
                    JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
                    emailTxt.setText("");
                }

            }

            catch (Exception e) {
                Component panel=null;
                JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
                emailTxt.setText("");
            }
            //        try{
                //            String email[]=em.split("@");
                //            String em1=email[1].toString();
                //            String emw=em1;
                //           String se[]=emw.split(".");
                //        }
            //        catch(Exception e){
                //            Component panel=null;
                //         JOptionPane.showMessageDialog(panel,"Please enter a valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
                //         emailTxt.setText("");
                //        }

        }

    }//GEN-LAST:event_AddrTxtMouseClicked

    private void contactTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactTxtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_contactTxtKeyPressed

    private void contactTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactTxtMouseClicked
        // TODO add your handling code here:
        String name=nameTxt.getText();
        if((name.matches(".*\\d.*"))||(name.equals("")))
        {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid Name", "Error", JOptionPane.ERROR_MESSAGE);
            nameTxt.setText("");
        }
    }//GEN-LAST:event_contactTxtMouseClicked

    private void contactTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactTxtFocusGained
        // TODO add your handling code here:
        //        Component panel = null;
        //            JOptionPane.showMessageDialog(panel,"Invalid inputs entered", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_contactTxtFocusGained

    private void ManuTable1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManuTable1MouseMoved
        // TODO add your handling code here:
        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();

            String query1="select * from 8ware.manufacturer";
            ResultSet rs1 = stmt.executeQuery(query1);
            ManuTable1.setModel(DbUtils.resultSetToTableModel(rs1));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ManuTable1MouseMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          // TODO add your handling code here:
        nameTxt.setText("DemoManufactName");
        contactTxt.setText("DemoContactName");
        PhoTxt.setText("0712356587");
        emailTxt.setText("demo@demo.com");
        AddrTxt.setText("demoAddress");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        nameTxt.setText("");
        contactTxt.setText("");
        PhoTxt.setText("");
        emailTxt.setText("");
        AddrTxt.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed


    
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
            java.util.logging.Logger.getLogger(AddManufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddManufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddManufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddManufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddManufacturer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItemBtn;
    private javax.swing.JLabel AddrLbl;
    private javax.swing.JTextArea AddrTxt;
    private javax.swing.JTextArea AddrTxt1;
    private javax.swing.JLabel ContactLbl;
    private javax.swing.JButton DelBtn;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JComboBox<String> ManuIDCmb;
    private javax.swing.JTable ManuTable1;
    private javax.swing.JLabel NameLbl;
    private javax.swing.JTextField PhoTxt;
    private javax.swing.JTextField PhoTxt1;
    private javax.swing.JLabel PhoneLbl;
    private javax.swing.JButton UpdBtn;
    private javax.swing.JLabel UserLbl;
    private javax.swing.JTextField contactTxt;
    private javax.swing.JTextField contactTxt1;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField emailTxt1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JTextField nameTxt1;
    // End of variables declaration//GEN-END:variables
}
