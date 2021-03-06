package CafetariaNTele;


import HRM.Login;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.*;
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
public class Cashier extends javax.swing.JFrame {

    /**
     * Creates new form Cashier
     * 
     * 
     */
     Connection con;
    public Cashier() {
        initComponents();
        try {
           con=DBAccess.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
        }
        HiddenTotal.setVisible(false);
        String query1="select UserName from 8ware.log";
        String query2="select * from 8ware.cafeteriaItems";  
        String sql="select * from 8ware.Cashier";
        try {
        Connection c = DBAccess.getConnection();
        Statement stmt = c.createStatement();
        Statement stm = c.createStatement();
        Statement st = c.createStatement();
            stmt.execute(query1);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
            String usr = rs.getString("UserName");
           UserLbl.setText(usr);
            }
            
            ResultSet rs1 = stm.executeQuery(sql);
           table.setModel(DbUtils.resultSetToTableModel(rs1));
           //adding data to combo
           ResultSet rs2 = st.executeQuery(query2);
            while (rs2.next()) {
           itemCmb.addItem(rs2.getString("ItemName"));
            }

        
    } catch (Exception e) {
        e.printStackTrace();
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

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        UserLbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        HomeBtn = new javax.swing.JButton();
        LogoutBtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ClearBtn1 = new javax.swing.JButton();
        QtyText1 = new javax.swing.JTextField();
        itemCmb = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        GetBalanceBtn1 = new javax.swing.JButton();
        BalanceLbl1 = new javax.swing.JLabel();
        PaidAmountTxt1 = new javax.swing.JTextField();
        TotalAmountLbl1 = new javax.swing.JLabel();
        AddBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        descTxt = new javax.swing.JTextArea();
        HiddenTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        Report = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Transaction");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 43)); // NOI18N
        jLabel2.setText("Cafeteria Cashier ");

        jLabel3.setText("Logged in as ");

        jSeparator2.setForeground(new java.awt.Color(0, 153, 0));

        UserLbl.setText("admin");
        UserLbl.setName("userLabel"); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(0, 153, 0));

        HomeBtn.setText("Home");
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeBtnActionPerformed(evt);
            }
        });

        LogoutBtn.setText("Logout");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        jLabel11.setText("Item");

        jLabel12.setText("Quantity");

        ClearBtn1.setText("Clear");
        ClearBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtn1ActionPerformed(evt);
            }
        });

        QtyText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QtyText1ActionPerformed(evt);
            }
        });
        QtyText1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QtyText1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QtyText1KeyTyped(evt);
            }
        });

        itemCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCmbActionPerformed(evt);
            }
        });

        jLabel13.setText("Total Amount");

        jLabel14.setText("Paid Amount");

        jLabel15.setText("Balance");

        GetBalanceBtn1.setText("Get Balance");
        GetBalanceBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetBalanceBtn1ActionPerformed(evt);
            }
        });

        BalanceLbl1.setText(".............................");

        PaidAmountTxt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PaidAmountTxt1MouseClicked(evt);
            }
        });
        PaidAmountTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaidAmountTxt1ActionPerformed(evt);
            }
        });
        PaidAmountTxt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PaidAmountTxt1KeyReleased(evt);
            }
        });

        TotalAmountLbl1.setText(".............................");

        AddBtn.setText("Add");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        descTxt.setColumns(20);
        descTxt.setRows(5);
        jScrollPane3.setViewportView(descTxt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(334, 334, 334)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(HiddenTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TotalAmountLbl1)
                                .addGap(8, 8, 8))
                            .addComponent(PaidAmountTxt1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(itemCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 356, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(QtyText1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(GetBalanceBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(AddBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(ClearBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BalanceLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(itemCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QtyText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TotalAmountLbl1)
                    .addComponent(HiddenTotal))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(PaidAmountTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(BalanceLbl1))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GetBalanceBtn1)
                    .addComponent(ClearBtn1)
                    .addComponent(AddBtn))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Transaction", jPanel1);

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tableMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        Report.setText("Report");
        Report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Report)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(Report)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("View Transaction", jPanel2);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics1/rect.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jLabel3)
                                .addGap(10, 10, 10)
                                .addComponent(UserLbl))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(HomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2)
                            .addComponent(jTabbedPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(UserLbl))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        MainMenu m=new MainMenu();
        m.setVisible(true);
    }//GEN-LAST:event_HomeBtnActionPerformed

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
              new Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        // TODO add your handling code here:
        
        String paid=PaidAmountTxt1.getText();
        
        
        if(paid.equals(""))
        {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter Paid amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else 
        {
            
        double paidAmount = Double.parseDouble(PaidAmountTxt1.getText());
        double tota =Double.parseDouble(HiddenTotal.getText()) ;
        if(tota>paidAmount)
        {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please pay the correct amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            BalanceLbl1.setText("Rs."+Double.toString(paidAmount-tota));
        }

        String ID="Cas"+((int)(Math.random()*9000)+1000);
        String qry="select TransactionID from 8ware.Cashier";

        try {
            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeQuery(qry);
            //JOptionPane.showMessageDialog(this, "Success !");
            ResultSet rs = stmt.getResultSet ();

            while (rs.next ())
            {

                String ids = rs.getString ("TransactionID");

                if(ids==ID)
                {
                    ID = "Cas"+((int)(Math.random()*9000)+1000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //else if()
        //        else if(name.matches("^[0-9]+$")) {
            //            Component panel = null;
            //            JOptionPane.showMessageDialog(panel,"Invalid inputs entered", "Error", JOptionPane.ERROR_MESSAGE);
            //        }
        String tot=TotalAmountLbl1.getText();
        String pa=PaidAmountTxt1.getText();
        
        String ba=BalanceLbl1.getText();
        String PurItems=descTxt.getText();
        Calendar date=Calendar.getInstance();
        String query="INSERT INTO 8ware.Cashier(`TransactionID`,`PurchasedItems` ,`TotalAmount`,`PaidAmount`,`Balance`) VALUES('"+ID+"','"+PurItems+"','"+tot+"','"+pa+"','"+ba+"')";
        try {

            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Success !");
            TotalAmountLbl1.setText("");
            PaidAmountTxt1.setText("");
            BalanceLbl1.setText("");
            descTxt.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }//GEN-LAST:event_AddBtnActionPerformed

    private void GetBalanceBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetBalanceBtn1ActionPerformed
         //TODO add your handling code here:
         
        String paid=PaidAmountTxt1.getText();
        
        if(paid.equals(""))
        {
             Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter Paid amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
        double paidAmount = Double.parseDouble(PaidAmountTxt1.getText());
        double tot =Double.parseDouble(HiddenTotal.getText()) ;
        if(tot>paidAmount)
        {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please pay the correct amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            BalanceLbl1.setText("Rs."+Double.toString(paidAmount-tot));
        }
        }
    }//GEN-LAST:event_GetBalanceBtn1ActionPerformed

    private void itemCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCmbActionPerformed

    private void QtyText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QtyText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QtyText1ActionPerformed

    private void ClearBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtn1ActionPerformed
        // TODO add your handling code here:
        totAmount=0;
        QtyText1.setText("");
        PaidAmountTxt1.setText("");
        BalanceLbl1.setText("");
        TotalAmountLbl1.setText("");
        descTxt.setText("");
        HiddenTotal.setText("");

    }//GEN-LAST:event_ClearBtn1ActionPerformed

    private void QtyText1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QtyText1KeyTyped
        // TODO add your handling code here:
       // String items=(String) itemCmb.getSelectedItem();
        //String qty=QtyText1.getText();
//        String items = itemCmb.getSelectedItem().toString();
//        int qty = Integer.parseInt(QtyText1.getText());
//        
//        
//        try {
//
//            Connection c = DB_Class.getConnection();
//            Statement stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery("select Price from 8ware.cafeteriaitems where ItemName='"+items+"'");
//
//            //adding data to combo
//            while (rs.next()) {
//                System.out.println(Double.toString(rs.getDouble("Price")));
//                //DescLbl.append();
//                descTxt.append(items+" , Quantity ="+qty+" , Price ="+rs.getDouble("Price"));
//                totAmount =totAmount+ (rs.getDouble("Price")*qty);
//                TotalAmountLbl1.setText(Double.toString(totAmount));
//                
//            }
//            items=null;
//            QtyText1.setText("");
//            descTxt.append("/n");
//
//        }catch(Exception ex){
//
//        }
        
        
        
    }//GEN-LAST:event_QtyText1KeyTyped

    private void QtyText1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QtyText1KeyReleased
        // TODO add your handling code here:
        String items = itemCmb.getSelectedItem().toString();
        
        String qt=QtyText1.getText();
        if(qt.matches("^[a-zA-Z]+$"))
        {
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
            QtyText1.setText("");
        }
        else{
        
        int qty = Integer.parseInt(QtyText1.getText());
        try {

            Connection c = DBAccess.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select Price from 8ware.cafeteriaitems where ItemName='"+items+"'");

            //adding data to combo
            while (rs.next()) {
                System.out.println(Double.toString(rs.getDouble("Price")));
                //DescLbl.append();
                
                totAmount =totAmount+ (rs.getDouble("Price")*qty);
                
                TotalAmountLbl1.setText("Rs."+Double.toString(totAmount));
                HiddenTotal.setText(Double.toString(totAmount));
                descTxt.append(items+" - "+qty+" * Rs."+rs.getDouble("Price")+" = "+(rs.getDouble("Price")*qty)+" , ");
            }
            items=null;
            QtyText1.setText("");
            descTxt.append("\n");

        }catch(Exception ex){

        }
        }
    }//GEN-LAST:event_QtyText1KeyReleased

    private void PaidAmountTxt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaidAmountTxt1KeyReleased
        // TODO add your handling code here:
//         double paidAmount = Double.parseDouble(PaidAmountTxt1.getText());
//        double tot =Double.parseDouble(TotalAmountLbl1.getText()) ;
//        if(tot>paidAmount)
//        {
//            Component panel = null;
//            JOptionPane.showMessageDialog(panel,"Please pay the correct amount", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        else{
//            BalanceLbl1.setText(Double.toString(paidAmount-tot));
//        }
    }//GEN-LAST:event_PaidAmountTxt1KeyReleased

    private void PaidAmountTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaidAmountTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaidAmountTxt1ActionPerformed

    private void ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportActionPerformed
        // TODO add your handling code here:
        try {

         String report ="src\\CafetariaNTele\\CashierReport.jrxml";
            JasperReport JASP_REP=JasperCompileManager.compileReport(report);
            JasperPrint JASP_PRINT=JasperFillManager.fillReport(JASP_REP,null,con);
            JasperViewer.viewReport(JASP_PRINT);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
        
        
        
//        try {
//            String report ="C:\\Users\\Visal\\Documents\\NetBeansProjects\\ItpProject\\src\\CashierReport.jrxml";
//            JasperReport JASP_REP=JasperCompileManager.compileReport(report);
//            JasperPrint JASP_PRINT=JasperFillManager.fillReport(JASP_REP,null,
//                    )
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_ReportActionPerformed

    private void PaidAmountTxt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaidAmountTxt1MouseClicked
        // TODO add your handling code here:
        String qt=descTxt.getText();
        
        if((qt.equals(""))){
            Component panel = null;
            JOptionPane.showMessageDialog(panel,"Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_PaidAmountTxt1MouseClicked

    private void tableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseMoved
        // TODO add your handling code here:
        String sql="select * from 8ware.Cashier";
        try {
        Connection c = DBAccess.getConnection();
        Statement stmt = c.createStatement();
        Statement stm = c.createStatement();
        Statement st = c.createStatement();
            
            
            ResultSet rs1 = stm.executeQuery(sql);
           table.setModel(DbUtils.resultSetToTableModel(rs1));
           //adding data to combo
          

        
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_tableMouseMoved
double totAmount = 0;
String arr[];
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
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cashier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JLabel BalanceLbl1;
    private javax.swing.JButton ClearBtn1;
    private javax.swing.JButton GetBalanceBtn1;
    private javax.swing.JLabel HiddenTotal;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JTextField PaidAmountTxt1;
    private javax.swing.JTextField QtyText1;
    private javax.swing.JButton Report;
    private javax.swing.JLabel TotalAmountLbl1;
    private javax.swing.JLabel UserLbl;
    private javax.swing.JTextArea descTxt;
    private javax.swing.JComboBox<String> itemCmb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
