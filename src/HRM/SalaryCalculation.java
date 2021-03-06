/*
 * EmployeeRegistration.java
 *
 * Created on July 26, 2016, 1:42 AM
 */
package HRM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author  dell notebook
 */
public class SalaryCalculation extends javax.swing.JFrame {

    String setString1;
    String setString2;
    String emp_id;
    double etf;
    double basic_Salary;
    char ch;
    double hours_rate=0;
    double monthly_installment = 0;
    double monthly_deductions = 0;
    
    double tot_leave_hrs;
    double otrate;//set the valuer occording to the position
    int othrs;//taken from the attendance table
    int working_hrs=0;
    double otfee = othrs * otrate;
    double netSal=0.0;
    int inc=0;
    Calendar now = Calendar.getInstance();
    int dateMonth = (now.get(Calendar.MONTH) + 1);
    String month = Integer.toString(dateMonth);
    int dateYear=(now.get(Calendar.YEAR));
    String year=Integer.toString(dateYear);
            
    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
    Date date=new Date();
    String del=sdf.format(date);
    
    String SID;
    
    
    int commision;
    /** Creates new form EmployeeRegistration */
    public SalaryCalculation() {
        initComponents();
System.out.println("Month :- "+month); 
System.out.println("Year :- "+year); 
txtDate.setText(del);

       // System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
        //txtName.setEnabled(false);
        //txtDate.setText(month);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblNIC = new javax.swing.JLabel();
        txtNIC = new javax.swing.JTextField();
        lblDesignation = new javax.swing.JLabel();
        txtDesignation = new javax.swing.JTextField();
        lblDepartment = new javax.swing.JLabel();
        txtDepartment = new javax.swing.JTextField();
        lblOT = new javax.swing.JLabel();
        txtOT_Hrs = new javax.swing.JTextField();
        lblOT_Rate = new javax.swing.JLabel();
        txtOT_Rate = new javax.swing.JTextField();
        lblWorked_Hrs = new javax.swing.JLabel();
        txtWorked_Hrs = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblBasic_Salary = new javax.swing.JLabel();
        txtBasic_Salary = new javax.swing.JTextField();
        lblMonthly_Deduction = new javax.swing.JLabel();
        txtMonthly_Deduction = new javax.swing.JTextField();
        lblMonthly_Installment = new javax.swing.JLabel();
        txtMonthly_Installment = new javax.swing.JTextField();
        txtDate = new javax.swing.JLabel();
        txtNet_Salary = new javax.swing.JTextField();
        lblNet_Salary1 = new javax.swing.JLabel();
        lblDate1 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("8WARE TOTAL MANAGEMENT SYSTEM");
        setExtendedState(6);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 20, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Salary Calculation");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Logged in as Employer Manager");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 80, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("Home");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 110, 60, 20));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Logout");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 110, 60, 20));

        jSeparator2.setForeground(new java.awt.Color(51, 153, 0));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1300, 10));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId.setText("Employee ID");
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtIDMouseReleased(evt);
            }
        });
        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDFocusLost(evt);
            }
        });
        txtID.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtIDInputMethodTextChanged(evt);
            }
        });
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDKeyTyped(evt);
            }
        });
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 190, -1));

        lblName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblName.setText("Employee Name");
        jPanel1.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 190, -1));

        lblNIC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNIC.setText("NIC");
        jPanel1.add(lblNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        txtNIC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 190, -1));

        lblDesignation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDesignation.setText("Designation");
        jPanel1.add(lblDesignation, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        txtDesignation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtDesignation, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 190, -1));

        lblDepartment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDepartment.setText("Department");
        jPanel1.add(lblDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        txtDepartment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 190, -1));

        lblOT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOT.setText("OT Hrs");
        jPanel1.add(lblOT, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        txtOT_Hrs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtOT_Hrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 190, -1));

        lblOT_Rate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOT_Rate.setText("OT Rate");
        jPanel1.add(lblOT_Rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        txtOT_Rate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtOT_Rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 190, -1));

        lblWorked_Hrs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblWorked_Hrs.setText("Worked Hrs");
        jPanel1.add(lblWorked_Hrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        txtWorked_Hrs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtWorked_Hrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 190, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 540, 460));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salary Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBasic_Salary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblBasic_Salary.setText("Basic Salary");
        jPanel2.add(lblBasic_Salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtBasic_Salary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtBasic_Salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 190, -1));

        lblMonthly_Deduction.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMonthly_Deduction.setText("Monthly Deduction");
        jPanel2.add(lblMonthly_Deduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        txtMonthly_Deduction.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtMonthly_Deduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 190, -1));

        lblMonthly_Installment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMonthly_Installment.setText("Monthly Installment");
        jPanel2.add(lblMonthly_Installment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        txtMonthly_Installment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtMonthly_Installment, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 190, -1));

        txtDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 110, 20));

        txtNet_Salary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtNet_Salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 190, -1));

        lblNet_Salary1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNet_Salary1.setText("Net Salary");
        jPanel2.add(lblNet_Salary1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        lblDate1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDate1.setText("Date");
        jPanel2.add(lblDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, 440, 310));

        btnClear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, -1, -1));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 580, -1, -1));

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 580, -1, -1));

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 580, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rect.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1725, 666));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    new EMPMANAGERHOME().setVisible(true);
    this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
// TODO add your handling code here:
   
    try {
        txtBasic_Salary.setText("");
        txtDate.setText(del);
        txtDepartment.setText("");
        txtDesignation.setText("");
        txtID.setText("");
        txtMonthly_Deduction.setText("");
        txtMonthly_Installment.setText("");
        txtNIC.setText("");
        txtName.setText("");
        txtNet_Salary.setText("");
        txtOT_Hrs.setText("");
        txtOT_Rate.setText("");
        txtWorked_Hrs.setText("");
        

    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnClearActionPerformed

private void txtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyReleased
// TODO add your handling code here:

}//GEN-LAST:event_txtIDKeyReleased
public void search1()
{
            try {

        txtNIC.setText("");
        txtName.setText("");
        txtDepartment.setText("");
        txtDesignation.setText("");

        //setString1 = "like 'txtID.getText()%'";
        setString2 = txtID.getText();
        
        Connection c = DBAccess.getConnection();
        PreparedStatement s1;
        s1 = c.prepareStatement("select * from employee where emp_id='"+txtID.getText()+"'");
       // s1.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs1 = s1.executeQuery();


        while (rs1.next()) {

            txtID.setText(rs1.getString("emp_id"));
            txtNIC.setText(rs1.getString("nic"));
            txtName.setText(rs1.getString("ename"));
            txtDepartment.setText(rs1.getString("department_name"));
            txtDesignation.setText(rs1.getString("designation"));

            emp_id = txtID.getText();
            ch = emp_id.charAt(0);

            if (ch == 'E') {
                basic_Salary = 3000000;
                txtBasic_Salary.setText(Double.toString(basic_Salary));
                hours_rate=basic_Salary/720.0;
                inc=5;
                commision=5;
                etf=(3000000-(3000000*0.20));
                
            } else if (ch == 'M') {
                basic_Salary = 5000000;
                txtBasic_Salary.setText(Double.toString(basic_Salary));
                hours_rate=basic_Salary/720.0;
                inc=10;
                commision=10;
                etf=(5000000-(5000000*0.20));
                
            } else if (ch == 'T') {
                basic_Salary = 200000;
                txtBasic_Salary.setText(Double.toString(basic_Salary));
                hours_rate=basic_Salary/720.0;
                inc=2;
                commision=2;
                etf=(200000-(200000*0.20));
            }
        }

        try {
        Connection cc = DBAccess.getConnection();
        PreparedStatement s2;
       
        s2 = cc.prepareStatement("select emp_id,month,sum(ot_hrs) as ot,sum(no_of_working_hours) as hrs from attendance where emp_id='"+emp_id+"' and month=" + dateMonth + " group by emp_id,month ");
       // s2.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs0 = s2.executeQuery();
        
        boolean status2 = rs0.next();

        if (status2) {
                
                System.out.println(rs0.getString("month"));

                emp_id = txtID.getText();
                
                ch = emp_id.charAt(0);

                if(ch=='i')
                {
                    JOptionPane.showMessageDialog(rootPane, "III");
                }
                else if (ch == 'E') 
                {
                    otrate = 0.5;
                    //JOptionPane.showMessageDialog(rootPane, "EEEEE");
                    txtOT_Rate.setText(Double.toString(otrate));
                    othrs=Integer.parseInt(rs0.getString("ot"));
                    working_hrs=Integer.parseInt(rs0.getString("hrs"));
                    otfee=othrs*otrate;
                    
                    txtOT_Hrs.setText(Integer.toString(othrs));
                    txtWorked_Hrs.setText(Integer.toString(working_hrs));
                    
                } 
                else if (ch == 'M') {
                    otrate = 0.2;
                    txtOT_Rate.setText(Double.toString(otrate));
                    othrs=Integer.parseInt(rs0.getString("ot"));
                    working_hrs=Integer.parseInt(rs0.getString("hrs"));
                    otfee=othrs*otrate;
                    
                    txtOT_Hrs.setText(Integer.toString(othrs));
                    txtWorked_Hrs.setText(Integer.toString(working_hrs));
                    
                } else if (ch == 'T') {
                    otrate = 0.1;
                    txtOT_Rate.setText(Double.toString(otrate));
                    othrs=Integer.parseInt(rs0.getString("ot"));
                    working_hrs=Integer.parseInt(rs0.getString("hrs"));
                    otfee=othrs*otrate;
                    
                    txtOT_Hrs.setText(Integer.toString(othrs));
                    txtWorked_Hrs.setText(Integer.toString(working_hrs));
                    
                }

            
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Non existing user in attendance table!!!");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


        PreparedStatement s3;
        s3 = c.prepareStatement("select monthly_installment from add_loans where emp_id='"+emp_id+"'");
        //s3.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs3 = s3.executeQuery();

        boolean status3 = rs3.next();

        if (status3)
        {
            //while (rs3.next()) {
                
                txtMonthly_Installment.setText(rs3.getString("monthly_installment"));
                monthly_installment = Double.parseDouble(rs3.getString("monthly_installment"));
                

            //}
        } 
        else 
        {
            JOptionPane.showMessageDialog(rootPane, "Non existing user in add loans table!!!");
            monthly_installment=0.0;
            txtMonthly_Installment.setText("0.0");
            txtMonthly_Deduction.setText("0.0");
        }

        PreparedStatement s4;
        s4 = c.prepareStatement("select emp_id,hours from add_leaves where emp_id='"+emp_id+"'");
        //s4.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs4 = s4.executeQuery();
      
        boolean status5 = rs4.next();

        if (status5) 
        {
            while (rs4.next()) 
            {
                tot_leave_hrs=Double.parseDouble(rs4.getString("hours"));
                monthly_deductions=(tot_leave_hrs*hours_rate)+monthly_installment+etf;
                txtMonthly_Deduction.setText(Double.toString(monthly_deductions));
                
                netSal=(basic_Salary-monthly_deductions);
                txtNet_Salary.setText(Double.toString(netSal));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Non existing user in add leaves table!!!");
            tot_leave_hrs=0;
            monthly_deductions=(tot_leave_hrs*hours_rate)+monthly_installment+etf;
            txtMonthly_Deduction.setText(Double.toString(monthly_deductions));
            
            netSal=(basic_Salary-monthly_deductions);
            txtNet_Salary.setText(Double.toString(netSal));
          
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void search2()
{
            try {

        txtNIC.setText("");
        txtName.setText("");
        txtDepartment.setText("");
        txtDesignation.setText("");

        Connection c = DBAccess.getConnection();
        PreparedStatement s1;
        s1 = c.prepareStatement("select * from employee where emp_id='"+txtID.getText()+"'");
       // s1.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs1 = s1.executeQuery();


        while (rs1.next()) {

            txtID.setText(rs1.getString("emp_id"));
            txtNIC.setText(rs1.getString("nic"));
            txtName.setText(rs1.getString("ename"));
            txtDepartment.setText(rs1.getString("department_name"));
            txtDesignation.setText(rs1.getString("designation"));

            emp_id = txtID.getText();
            ch = emp_id.charAt(0);

            if (ch == 'E') {
                basic_Salary = 3000000;
                txtBasic_Salary.setText(Double.toString(basic_Salary));
                hours_rate=basic_Salary/720.0;
                inc=5;
                commision=5;
                etf=(3000000-(3000000*0.20));
                
            } else if (ch == 'M') {
                basic_Salary = 5000000;
                txtBasic_Salary.setText(Double.toString(basic_Salary));
                hours_rate=basic_Salary/720.0;
                inc=10;
                commision=10;
                etf=(5000000-(5000000*0.20));
            } else if (ch == 'T') {
                basic_Salary = 200000;
                txtBasic_Salary.setText(Double.toString(basic_Salary));
                hours_rate=basic_Salary/720.0;
                inc=2;
                commision=2;
                etf=(200000-(200000*0.20));
            }
        }

        try {
        Connection cc = DBAccess.getConnection();
        PreparedStatement s2;
       
        s2 = cc.prepareStatement("select emp_id,month,sum(ot_hrs) as ot,sum(no_of_working_hours) as hrs from attendance where emp_id='"+txtID.getText()+"' and month=" + dateMonth + " group by emp_id,month ");
       // s2.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs0 = s2.executeQuery();
        
        boolean status2 = rs0.next();

        if (status2) {
                
                System.out.println(rs0.getString("month"));

                emp_id = txtID.getText();
                
                ch = emp_id.charAt(0);

                if(ch=='i')
                {
                    JOptionPane.showMessageDialog(rootPane, "III");
                }
                else if (ch == 'E') 
                {
                    otrate = 0.5;
                    //JOptionPane.showMessageDialog(rootPane, "EEEEE");
                    txtOT_Rate.setText(Double.toString(otrate));
                    othrs=Integer.parseInt(rs0.getString("ot"));
                    working_hrs=Integer.parseInt(rs0.getString("hrs"));
                    otfee=othrs*otrate;
                    
                    txtOT_Hrs.setText(Integer.toString(othrs));
                    txtWorked_Hrs.setText(Integer.toString(working_hrs));
                    
                } 
                else if (ch == 'M') {
                    otrate = 0.2;
                    txtOT_Rate.setText(Double.toString(otrate));
                    othrs=Integer.parseInt(rs0.getString("ot"));
                    working_hrs=Integer.parseInt(rs0.getString("hrs"));
                    otfee=othrs*otrate;
                    
                    txtOT_Hrs.setText(Integer.toString(othrs));
                    txtWorked_Hrs.setText(Integer.toString(working_hrs));
                    
                } else if (ch == 'T') {
                    otrate = 0.1;
                    txtOT_Rate.setText(Double.toString(otrate));
                    othrs=Integer.parseInt(rs0.getString("ot"));
                    working_hrs=Integer.parseInt(rs0.getString("hrs"));
                    otfee=othrs*otrate;
                    
                    txtOT_Hrs.setText(Integer.toString(othrs));
                    txtWorked_Hrs.setText(Integer.toString(working_hrs));
                    
                }

            
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Non existing user in attendance table!!!");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


        PreparedStatement s3;
        s3 = c.prepareStatement("select * from add_loans where emp_id='"+txtID.getText()+"'");
        //s3.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs3 = s3.executeQuery();

        boolean status3 = rs3.next();

        if (status3)
        {
            while (rs3.next()) {
                txtMonthly_Installment.setText(rs3.getString("monthly_installment"));
                monthly_installment = Double.parseDouble(rs3.getString("monthly_installment"));
                

            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(rootPane, "Non existing user in add loans table!!!");
            monthly_installment=0.0;
            txtMonthly_Installment.setText("0.0");
            txtMonthly_Deduction.setText("0.0");
        }

        PreparedStatement s4;
        s4 = c.prepareStatement("select emp_id,hours from add_leaves where emp_id='"+txtID.getText()+"'");
        //s4.setString(1, "%" + txtID.getText() + "%");
        ResultSet rs4 = s4.executeQuery();
      
        boolean status5 = rs4.next();

        if (status5) 
        {
            while (rs4.next()) 
            {
                tot_leave_hrs=Double.parseDouble(rs4.getString("hours"));
                monthly_deductions=(tot_leave_hrs*hours_rate)+monthly_installment+etf;
                txtMonthly_Deduction.setText(Double.toString(monthly_deductions));
                
                netSal=(basic_Salary-monthly_deductions);
                txtNet_Salary.setText(Double.toString(netSal));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Non existing user in add leaves table!!!");
            tot_leave_hrs=0;
            monthly_deductions=(tot_leave_hrs*hours_rate)+monthly_installment+etf;
            txtMonthly_Deduction.setText(Double.toString(monthly_deductions));
            
            netSal=(basic_Salary-monthly_deductions);
            txtNet_Salary.setText(Double.toString(netSal));
          
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
            
}

public void SIDAI()
{
    try {
        int count;
        String scount;
        Connection c = DBAccess.getConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select count(SID)as count from salary  ");
        while (rs.next()) {
            count = (Integer.parseInt(rs.getString("count"))) + 1;
            scount = Integer.toString(count);
            SID = ("SID" + scount);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
// TODO add your handling code here:
        try {
        String p1=txtID.getText();
        double p2=Double.parseDouble(txtBasic_Salary.getText());
        double p3=Double.parseDouble(txtMonthly_Deduction.getText());
        double p4=Double.parseDouble(txtMonthly_Installment.getText());
        double p6=Double.parseDouble(txtNet_Salary.getText());
        String btndate;
        //String dateArray[]=new String[3];
        int btnmonth,btnyear=0;
        
        
        
        Connection c=DBAccess.getConnection();
        
            
            PreparedStatement support;
            support = c.prepareStatement("select SID,emp_id,basic,monthly_deduction,loan_deduction,increments,net_salary,commissions,date,month,year from salary where emp_id='" + txtID.getText() + "' and month='"+dateMonth+"' and year='"+dateYear+"'");
            ResultSet resultSupport = support.executeQuery();
            boolean statusSupport = resultSupport.next();

            if (statusSupport) {
                /*while(resultSupport.next())
                {
                btndate=resultSupport.getString("date");
                String dateArray[]=btndate.split("/");
                btnmonth=Integer.parseInt(dateArray[1]);
                btnyear=Integer.parseInt(dateArray[2]);
                System.out.println("btnmonth :- "+btnmonth+"\tbtnyear :- "+btnyear);
                }*/
                JOptionPane.showMessageDialog(rootPane, "Already exits in the Salary Table. Not saving !!!");

            } else {
                SIDAI();
                Statement s = c.createStatement();
                s.executeUpdate("Insert into salary (SID,emp_id,basic,monthly_deduction,loan_deduction,increments,net_salary,commissions,date,month,year)values('" + SID + "','" + p1 + "','" + p2 + "','" + p3 + "','" + p4 + "','" + inc + "','" + p6 + "','" + commision + "','" + del + "','" + dateMonth + "','" + dateYear + "')");
                JOptionPane.showMessageDialog(rootPane, "Saved !!!");
            }
            
    } catch (Exception e) {
        e.printStackTrace();
    }

}//GEN-LAST:event_btnSaveActionPerformed

private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
// TODO add your handling code here:
            try {
        String p1=txtID.getText();
        double p2=Double.parseDouble(txtBasic_Salary.getText());
        double p3=Double.parseDouble(txtMonthly_Deduction.getText());
        double p4=Double.parseDouble(txtMonthly_Installment.getText());
        double p6=Double.parseDouble(txtNet_Salary.getText());
  
        Connection c=DBAccess.getConnection();
        Statement s=c.createStatement();
         s.executeUpdate("Update  salary set basic='"+p2+"',monthly_deduction='"+p3+"',loan_deduction='"+p4+"',increments='"+inc+"',net_salary='"+p6+"',commissions='"+commision+"',date='"+del+"' where emp_id='"+emp_id+"' and month='"+dateMonth+"' and year='"+dateYear+"'");
         JOptionPane.showMessageDialog(rootPane, "Updated !!!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnUpdateActionPerformed

private void txtIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusLost
// TODO add your handling code here:
    search1();
}//GEN-LAST:event_txtIDFocusLost

private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
// TODO add your handling code here:
}//GEN-LAST:event_txtNameFocusLost

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
// TODO add your handling code here:
            try {
         Connection c=DBAccess.getConnection();
    Statement s=c.createStatement();
    s.executeUpdate("DELETE FROM salary  where  emp_id='" + emp_id + "' and month='"+dateMonth+"' and year='"+dateYear+"' ");
    JOptionPane.showMessageDialog(rootPane, "Deleted !!!");
        txtBasic_Salary.setText("");
        txtDate.setText("");
        txtDepartment.setText("");
        txtDesignation.setText("");
        txtID.setText("");
        txtMonthly_Deduction.setText("");
        txtMonthly_Installment.setText("");
        txtNIC.setText("");
        txtName.setText("");
        txtNet_Salary.setText("");
        txtOT_Hrs.setText("");
        txtOT_Rate.setText("");
        txtWorked_Hrs.setText("");
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnDeleteActionPerformed

private void txtIDInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtIDInputMethodTextChanged
// TODO add your handling code here:
    
}//GEN-LAST:event_txtIDInputMethodTextChanged

private void txtIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDMouseReleased
// TODO add your handling code here:
    
}//GEN-LAST:event_txtIDMouseReleased

private void txtIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyTyped
// TODO add your handling code here:
    
}//GEN-LAST:event_txtIDKeyTyped

private void txtIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyPressed
// TODO add your handling code here:
    
}//GEN-LAST:event_txtIDKeyPressed

private void txtIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusGained
// TODO add your handling code here:
    
}//GEN-LAST:event_txtIDFocusGained

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    new Login().setVisible(true);
    this.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new  

              Runnable() {
                 public void run() {
                new SalaryCalculation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBasic_Salary;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblDesignation;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMonthly_Deduction;
    private javax.swing.JLabel lblMonthly_Installment;
    private javax.swing.JLabel lblNIC;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNet_Salary1;
    private javax.swing.JLabel lblOT;
    private javax.swing.JLabel lblOT_Rate;
    private javax.swing.JLabel lblWorked_Hrs;
    private javax.swing.JTextField txtBasic_Salary;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtDesignation;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMonthly_Deduction;
    private javax.swing.JTextField txtMonthly_Installment;
    private javax.swing.JTextField txtNIC;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNet_Salary;
    private javax.swing.JTextField txtOT_Hrs;
    private javax.swing.JTextField txtOT_Rate;
    private javax.swing.JTextField txtWorked_Hrs;
    // End of variables declaration//GEN-END:variables
}
