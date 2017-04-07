/*
 * EmployeeRegistration.java
 *
 * Created on July 26, 2016, 1:42 AM
 */

package HRM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import ireports.ViewIReport;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author  dell notebook
 */
public class AddandCheckLeaves extends javax.swing.JFrame {
    String emp_id;//currently hard coding emp id.otherwise it should be passed through the constructor parameter
    String type;//leave type
    int hrs = 0;//for the loop using to get the sum
    int days = 0;//for the loop using to get the sum
    int taken_hrs = 0;
    int taken_days = 0;
    int tot_leaves =0;
    char ch;
    int remaing_leaves = 0;
    int defined_leaves = 0;
    int sum=0;
    
    int h1, h2 = 0;
    int d1, d2 = 0;
    int max1 = 0;
    int max2 = 0;
    
    Calendar now = Calendar.getInstance();
    int dateYear=(now.get(Calendar.YEAR));
    String year=Integer.toString(dateYear);
    
    /*int refersh_taken_hrs = 0;
    int refersh_taken_days = 0;
    int refersh_tot_leaves =0;
    int refersh_remaing_leaves = 0;
    int refersh_defined_leaves = 0;*/
    
    
    
    
        validate check=new validate();
    /** Creates new form EmployeeRegistration */
    public AddandCheckLeaves() {
        initComponents();
        //load();
        refresh();
        //String xx=Integer.toString(x);
       // txtRemaining_Leaves.setText(xx);
        


    }
    public AddandCheckLeaves(String p) {
        initComponents();
        emp_id=p;
        //load();
        refresh();
    //String xx=Integer.toString(x);
    // txtRemaining_Leaves.setText(xx);



    }

    
    public void load()
    {
                try {

            cmbType.setSelectedIndex(0);
            panelHandleHours.setVisible(false);
            panelHandleDays.setVisible(false);

            txtLeave_Hrs.setText("");
           // txtCome_In_Hrs.setText("");
            txtLeave_Date.setText("");
           // txtCome_In_Date.setText("");

            Connection c = DBAccess.getConnection();
            Statement s = c.createStatement();
            
            //ResultSet rs = s.executeQuery("select * from add_leaves where emp_id='" + emp_id + "'");
            ResultSet rs = s.executeQuery("select leave_id,emp_id,hours,days,type,year,sum(hours)as h1,sum(days)as d1 from add_leaves where emp_id='" + emp_id + "' and year='"+year+"' group by emp_id,leave_id,year");
            
            //should automatically upload here from db
            //System.out.println("okkkkk");
            boolean status = rs.next();
            if(status)
            {
                System.out.println("Records");
                //while (rs.next()) 
               // while(rs.next())
               // {
                    System.out.println("okkkkk");
                    String str = rs.getString("emp_id");
                    String ty=(rs.getString("type"));
                    System.out.println(str+"\t"+ty);
                    System.out.println("Year:- "+year);
                    System.out.println("Sum Hours :- "+rs.getString("h1"));
                    System.out.println("Sum Days :- "+rs.getString("d1"));
                    //if (str.equals("e001")) 
                    //{
                        
                        /*taken_hrs = Integer.parseInt(rs.getString("hours"));
                    taken_days = Integer.parseInt(rs.getString("days"));*/
                    
                    taken_hrs = Integer.parseInt(rs.getString("h1"));
                        taken_days = Integer.parseInt(rs.getString("d1"));

                        //System.out.println(taken_hrs+"*************************"+taken_days);
                        
                        tot_leaves = (((taken_days * 24) + taken_hrs)+tot_leaves);
                        
                        txtLeaves_Taken.setText(Integer.toString(tot_leaves));

                        ch = emp_id.charAt(0);

                        if (ch == 'E') 
                        {
                            defined_leaves = 264;
                        } else if (ch == 'T') 
                        {
                            defined_leaves = 264;
                        } else if (ch == 'M') 
                        {
                            defined_leaves = 360;
                        }

                        remaing_leaves = defined_leaves - tot_leaves;

                        txtRemaining_Leaves.setText(Integer.toString(remaing_leaves));
                        
                    //}
               // }
            }
            else 
            {
                //System.out.println("Empty Records");
                //System.out.print("dhkvfsek");
                JOptionPane.showMessageDialog(null, "No any leaves have taken !! ");
                
                txtLeaves_Taken.setText("0");
                taken_hrs = 0;
                taken_days = 0;
                tot_leaves = 0;

                ch = emp_id.charAt(0);

                if (ch == 'E') 
                {
                    defined_leaves = 264;
                } else if (ch == 'T') 
                {
                    defined_leaves = 264;
                } else if (ch == 'M') 
                {
                    defined_leaves = 360;
                }

                remaing_leaves = defined_leaves - tot_leaves;

                txtRemaining_Leaves.setText(Integer.toString(remaing_leaves));

                }
                
            }

         catch (Exception e) {
            e.printStackTrace();
        }
               /* h2=Integer.parseInt(txtLeave_Hrs.getText());
        
       // d1=Integer.parseInt(txtCome_In_Date.getText());
        d2=Integer.parseInt(txtLeave_Date.getText());
        
        //remaing_leaves=Integer.parseInt(txtRemaining_Leaves.getText());
        sum=h2+d2;
            if(remaing_leaves>sum)
            {
                System.out.print("remaing_leaves :- "+remaing_leaves);
                return remaing_leaves;
            }
            else
            {
                System.out.print("remaing_leaves :- "+remaing_leaves);
                return 999;
            }*/
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        lblLeaves_Taken = new javax.swing.JLabel();
        txtLeaves_Taken = new javax.swing.JTextField();
        lblRemaining_Leaves = new javax.swing.JLabel();
        txtRemaining_Leaves = new javax.swing.JTextField();
        btnView_Leaves1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox();
        btnAdd_Leaves = new javax.swing.JButton();
        panelHandleHours = new javax.swing.JPanel();
        lblLeave_Date2 = new javax.swing.JLabel();
        txtLeave_Hrs = new javax.swing.JTextField();
        panelHandleDays = new javax.swing.JPanel();
        lblLeave_Date1 = new javax.swing.JLabel();
        txtLeave_Date = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("8WARE TOTAL MANAGEMENT SYSTEM");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Add and Check Leaves");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Logged in as Employer");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("Home");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 60, 20));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Logout");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 60, 20));

        jSeparator2.setForeground(new java.awt.Color(51, 153, 0));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 880, 10));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leave Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLeaves_Taken.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLeaves_Taken.setText("Leaves Taken");
        jPanel3.add(lblLeaves_Taken, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txtLeaves_Taken.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel3.add(txtLeaves_Taken, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 190, -1));

        lblRemaining_Leaves.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRemaining_Leaves.setText("Remaining Leaves");
        jPanel3.add(lblRemaining_Leaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        txtRemaining_Leaves.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel3.add(txtRemaining_Leaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 190, -1));

        btnView_Leaves1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnView_Leaves1.setText("View Leaves");
        btnView_Leaves1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView_Leaves1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnView_Leaves1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 410, 320));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Leaves ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblType.setText("Type");
        jPanel4.add(lblType, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        cmbType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "ShortLeave", "HalfDay", "Casual", "Sick", "AnnualLeave" }));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });
        jPanel4.add(cmbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 190, -1));

        btnAdd_Leaves.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd_Leaves.setText("Add Leaves");
        btnAdd_Leaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_LeavesActionPerformed(evt);
            }
        });
        jPanel4.add(btnAdd_Leaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        panelHandleHours.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelHandleHours.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLeave_Date2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLeave_Date2.setText("Leave Hours");
        panelHandleHours.add(lblLeave_Date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtLeave_Hrs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLeave_Hrs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLeave_HrsFocusLost(evt);
            }
        });
        txtLeave_Hrs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLeave_HrsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLeave_HrsKeyTyped(evt);
            }
        });
        panelHandleHours.add(txtLeave_Hrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 70, -1));

        jPanel4.add(panelHandleHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 190, 70));

        panelHandleDays.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelHandleDays.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLeave_Date1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLeave_Date1.setText("Leave Dates");
        panelHandleDays.add(lblLeave_Date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtLeave_Date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLeave_Date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLeave_DateFocusLost(evt);
            }
        });
        txtLeave_Date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLeave_DateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLeave_DateKeyTyped(evt);
            }
        });
        panelHandleDays.add(txtLeave_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 60, -1));

        jPanel4.add(panelHandleDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 180, 70));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 400, 320));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rect.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(987, 697));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    new EMPHOME(emp_id).setVisible(true);
    this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

private void btnView_Leaves1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_Leaves1ActionPerformed
// TODO add your handling code here:
    //generating a report
    try {
        ViewIReport.myleaves(emp_id);
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnView_Leaves1ActionPerformed

private void btnAdd_LeavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_LeavesActionPerformed
// TODO add your handling code here:
    //sending to the db
    //should pass eid into this from through login page from the parameterized constructor of this form
    try {

        
       // h1=Float.parseFloat(txtCome_In_Hrs.getText());
        h2=Integer.parseInt(txtLeave_Hrs.getText());
        
       // d1=Integer.parseInt(txtCome_In_Date.getText());
        d2=Integer.parseInt(txtLeave_Date.getText());
        
       remaing_leaves=Integer.parseInt(txtRemaining_Leaves.getText());
        System.out.println(Integer.toString(remaing_leaves));
        sum=(h2+(d2*24));
        /*if(h1>h2)
            max1=h1;
        else
            max1=h2;
        
        if(max1==h1)
            hrs=h1-h2;
        else
            hrs=h2-h1;
        
        ///////////////////////////////////
        
        if(d1>d2)
            max2=d1;
        else
            max2=d2;
        
        if(max2==d1)
            hrs=d1-d2;
        else
            hrs=d2-d1;*/

        //remaing_leaves=refresh();
        if(remaing_leaves>sum) 
        {
            Connection c = DBAccess.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate("Insert into add_leaves (emp_id,hours,days,type,year) values('" + emp_id + "','" + h2 + "','" + d2 + "','" + cmbType.getSelectedItem().toString() + "','" + year + "')");
            JOptionPane.showMessageDialog(rootPane, "Saved!!!");
            refresh();
            //load();
            
        }
        else
        {
            System.out.println("rem :- "+remaing_leaves);
            JOptionPane.showMessageDialog(rootPane, "Can't add more leaves!!");
            cmbType.setSelectedIndex(0);
            panelHandleHours.setVisible(false);
            panelHandleDays.setVisible(false);
            txtLeave_Hrs.setText("");
            txtLeave_Date.setText("");
        }
        
  //System.out.println(" :- "+days);
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnAdd_LeavesActionPerformed
public void refresh()
{
    int refersh_taken_hrs = 0;
            int refersh_taken_days = 0;
            int refersh_tot_leaves =0;
            int refersh_remaing_leaves = 0;
            int refersh_defined_leaves = 0;
        try {

            
            
            
            cmbType.setSelectedIndex(0);
            panelHandleHours.setVisible(false);
            panelHandleDays.setVisible(false);

            txtLeave_Hrs.setText("");
           // txtCome_In_Hrs.setText("");
            txtLeave_Date.setText("");
           // txtCome_In_Date.setText("");

            Connection c = DBAccess.getConnection();
            Statement s = c.createStatement();
            
            //ResultSet rs = s.executeQuery("select * from add_leaves where emp_id='" + emp_id + "'");
            ResultSet rs = s.executeQuery("select emp_id,hours,days,type,year,sum(hours)as h1,sum(days)as d1 from add_leaves where emp_id='" + emp_id + "' and year='"+year+"' group by emp_id,leave_id,year");
            
            //should automatically upload here from db
            //System.out.println("okkkkk");
            boolean status = rs.next();
            if(status)
            {
                System.out.println("Records");
                //while (rs.next()) 
                rs.previous();
                while(rs.next())
                {
                    System.out.println("okkkkk");
                    String str = rs.getString("emp_id");
                    String ty=(rs.getString("type"));
                    System.out.println(str+"\t"+ty);
                    System.out.println("YEar :- "+year);
                    
                    //if (str.equals("e001")) 
                    //{
                        
                        /*taken_hrs = Integer.parseInt(rs.getString("hours"));
                    taken_days = Integer.parseInt(rs.getString("days"));*/
                    
                    refersh_taken_hrs += Integer.parseInt(rs.getString("h1"));
                    refersh_taken_days += Integer.parseInt(rs.getString("d1"));

                        System.out.println(refersh_taken_hrs+"*************************"+refersh_taken_days);
                        
                        
                        
                    //}
                }
                
                refersh_tot_leaves = (((refersh_taken_days * 24) + refersh_taken_hrs)+refersh_tot_leaves);
                        
                        txtLeaves_Taken.setText(Integer.toString(refersh_tot_leaves));

                        ch = emp_id.charAt(0);

                        if (ch == 'E') 
                        {
                            refersh_defined_leaves = 264;
                        } else if (ch == 'T') 
                        {
                            refersh_defined_leaves = 264;
                        } else if (ch == 'M') 
                        {
                            refersh_defined_leaves = 360;
                        }

                        refersh_remaing_leaves = refersh_defined_leaves - refersh_tot_leaves;
                        System.out.println("refersh_tot_leaves :- "+refersh_tot_leaves);

                        txtRemaining_Leaves.setText(Integer.toString(refersh_remaing_leaves));
                
                
            }
            else 
            {
                //System.out.println("Empty Records");
                //System.out.print("dhkvfsek");
                JOptionPane.showMessageDialog(null, "No any leaves have taken !! ");
                
                txtLeaves_Taken.setText("0");
                refersh_taken_hrs = 0;
                refersh_taken_days = 0;
                refersh_tot_leaves = 0;

                ch = emp_id.charAt(0);

                if (ch == 'E') 
                {
                    refersh_defined_leaves = 264;
                } else if (ch == 'T') 
                {
                    refersh_defined_leaves = 264;
                } else if (ch == 'M') 
                {
                    refersh_defined_leaves = 360;
                }

                refersh_remaing_leaves = refersh_defined_leaves - refersh_tot_leaves;

                txtRemaining_Leaves.setText(Integer.toString(refersh_remaing_leaves));

                }
            
                
            
            }

         catch (Exception e) {
            e.printStackTrace();
        }
            
          /*  h2=Integer.parseInt(txtLeave_Hrs.getText());
        
       // d1=Integer.parseInt(txtCome_In_Date.getText());
        d2=Integer.parseInt(txtLeave_Date.getText());
        
        //remaing_leaves=Integer.parseInt(txtRemaining_Leaves.getText());
        sum=h2+d2;
            if(refersh_remaing_leaves>sum)
            {
                System.out.print("refersh_remaing_leaves :- "+refersh_remaing_leaves);
                return refersh_remaing_leaves;
            }
            else
            {
                System.out.print("refersh_remaing_leaves :- "+refersh_remaing_leaves);
                return 999;
            }*/
}
private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
// TODO add your handling code here:
    try {
        
        type=cmbType.getSelectedItem().toString();
        
        if(type=="ShortLeave" || type=="HalfDay")
        {
            panelHandleHours.setVisible(true);
            panelHandleDays.setVisible(false);
            txtLeave_Hrs.setText("");
            //txtCome_In_Hrs.setText("");
            txtLeave_Date.setText("0");
            //txtCome_In_Date.setText("0");
            d2=0;
        }
        else if(type=="Casual" || type=="Sick" || type=="AnnualLeave")
        {
            panelHandleDays.setVisible(true);
            panelHandleHours.setVisible(false);
            txtLeave_Hrs.setText("0");
            //txtCome_In_Hrs.setText("0");
            txtLeave_Date.setText("");
            h2=0;
          //  txtCome_In_Date.setText("");
        }
        else
        {
            panelHandleDays.setVisible(false);
            panelHandleHours.setVisible(false);
            txtLeave_Hrs.setText("0");
            //txtCome_In_Hrs.setText("0");
            txtLeave_Date.setText("");
            h2=0;
            d2=0;
          //  txtCome_In_Date.setText("");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_cmbTypeActionPerformed

private void txtLeave_HrsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLeave_HrsFocusLost
// TODO add your handling code here:
        if(!check.isNumeric(txtLeave_Hrs.getText()))
    {
        JOptionPane.showMessageDialog(rootPane, "Pls enter only numbers !!");
         txtLeave_Hrs.setText("");
         d2=0;
    }
}//GEN-LAST:event_txtLeave_HrsFocusLost

private void txtLeave_DateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLeave_DateFocusLost
// TODO add your handling code here:
            if(!check.isNumeric(txtLeave_Date.getText()))
    {
        JOptionPane.showMessageDialog(rootPane, "Pls enter only numbers !!");
         txtLeave_Date.setText("");
         h2=0;
    }
}//GEN-LAST:event_txtLeave_DateFocusLost

private void txtLeave_HrsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeave_HrsKeyReleased
// TODO add your handling code here:

}//GEN-LAST:event_txtLeave_HrsKeyReleased

private void txtLeave_DateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeave_DateKeyReleased
// TODO add your handling code here:
 
}//GEN-LAST:event_txtLeave_DateKeyReleased

private void txtLeave_HrsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeave_HrsKeyTyped
// TODO add your handling code here:

}//GEN-LAST:event_txtLeave_HrsKeyTyped

private void txtLeave_DateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeave_DateKeyTyped
// TODO add your handling code here:

}//GEN-LAST:event_txtLeave_DateKeyTyped

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    new Login().setVisible(true);
    this.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddandCheckLeaves().setVisible(true);
            }
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd_Leaves;
    private javax.swing.JButton btnView_Leaves1;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblLeave_Date1;
    private javax.swing.JLabel lblLeave_Date2;
    private javax.swing.JLabel lblLeaves_Taken;
    private javax.swing.JLabel lblRemaining_Leaves;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel panelHandleDays;
    private javax.swing.JPanel panelHandleHours;
    private javax.swing.JTextField txtLeave_Date;
    private javax.swing.JTextField txtLeave_Hrs;
    private javax.swing.JTextField txtLeaves_Taken;
    private javax.swing.JTextField txtRemaining_Leaves;
    // End of variables declaration//GEN-END:variables

}
