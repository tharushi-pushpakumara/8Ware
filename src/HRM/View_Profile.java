/*
 * EmployeeRegistration.java
 *
 * Created on July 26, 2016, 1:42 AM
 */

package HRM;

import ireports.ViewIReport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author  dell notebook
 */
public class View_Profile extends javax.swing.JFrame {

    String eid;
    String gender;
    /** Creates new form EmployeeRegistration */
        public View_Profile(String p) {
        initComponents();
           eid=p;
        
    
    
               // cmbDepartment.setEnabled(false);
               // txtNIC.setEnabled(false);
               // txtPhone.setEnabled(false);
               // cmbDepartment.setEnabled(false);
               // cmbDepartment.setEnabled(false);
               // cmbDepartment.setEnabled(false);
               // txtName.setEnabled(false);
                txtAddress.setEnabled(false);
                cmbDepartment.setEnabled(false);cmbDesignation.setEnabled(false);cmbType.setEnabled(false);
             
        try {        
    Connection c=DBAccess.getConnection();
    Statement s=c.createStatement();
    ResultSet rs=s.executeQuery("select * from employee where emp_id='" + eid + "'");
    
    while(rs.next())
    {
    txtID.setText(rs.getString("emp_id"));
    txtNIC.setText(rs.getString("nic"));
    txtPhone.setText(rs.getString("phone"));
    txtName.setText(rs.getString("ename"));
    txtAddress.setText(rs.getString("address"));
    txtEmail.setText(rs.getString("email"));
    gender=rs.getString("gender");
    

    //setting the value to the cmbDesignation
            if (rs.getString("designation").equals("employee")) {
                cmbDesignation.setSelectedIndex(1);
            } else if (rs.getString("designation").equals("manager")) {
                cmbDesignation.setSelectedIndex(2);
            } else if (rs.getString("designation").equals("technician")) {
                cmbDesignation.setSelectedIndex(3);
            } else if (rs.getString("designation").equals("driver")) {
                cmbDesignation.setSelectedIndex(4);
            } else {
                cmbDesignation.setSelectedIndex(0);
            }

            //setting the value to the cmbDepartment
            if (rs.getString("department_name").equals("HR")) {
                cmbDepartment.setSelectedIndex(1);
            } else if (rs.getString("department_name").equals("Accounts")) {
                cmbDepartment.setSelectedIndex(2);
            } else if (rs.getString("department_name").equals("Technical")) {
                cmbDepartment.setSelectedIndex(3);
            } else if (rs.getString("department_name").equals("Marketing")) {
                cmbDepartment.setSelectedIndex(4);
            } else {
                cmbDepartment.setSelectedIndex(0);
            }

            //setting the value to the cmbType
            if (rs.getString("type").equals("Training")) {
                cmbType.setSelectedIndex(1);
            } else if (rs.getString("type").equals("Probation")) {
                cmbType.setSelectedIndex(2);
            } else if (rs.getString("type").equals("Temporary")) {
                cmbType.setSelectedIndex(3);
            } else if (rs.getString("type").equals("Permanent")) {
                cmbType.setSelectedIndex(4);
            } else {
                cmbType.setSelectedIndex(0);
            }
        if(gender.equals("Female"))
        {
        chFemale.setEnabled(false); 
        chMale.setEnabled(false); 
        chFemale.setSelected(true); 
        chMale.setSelected(false);  
        }
        else{
        chFemale.setEnabled(false); 
        chMale.setEnabled(false); 
        chFemale.setSelected(false); 
        chMale.setSelected(true);  
        }
       
    }
    
    } catch (Exception e) {
    e.printStackTrace();
    }
    }
    public View_Profile() {
        initComponents();
            
    
    
               // cmbDepartment.setEnabled(false);
               // txtNIC.setEnabled(false);
               // txtPhone.setEnabled(false);
               // cmbDepartment.setEnabled(false);
               // cmbDepartment.setEnabled(false);
               // cmbDepartment.setEnabled(false);
               // txtName.setEnabled(false);
                txtAddress.setEnabled(false);
                cmbDepartment.setEnabled(false);cmbDesignation.setEnabled(false);cmbType.setEnabled(false);
             
        try {        
    Connection c=DBAccess.getConnection();
    Statement s=c.createStatement();
    ResultSet rs=s.executeQuery("select * from employee where emp_id='" + eid + "'");
    
    while(rs.next())
    {
    txtID.setText(rs.getString("emp_id"));
    txtNIC.setText(rs.getString("nic"));
    txtPhone.setText(rs.getString("phone"));
    txtName.setText(rs.getString("ename"));
    txtAddress.setText(rs.getString("address"));
    txtEmail.setText(rs.getString("email"));
    gender=rs.getString("gender");
    

    //setting the value to the cmbDesignation
            if (rs.getString("designation").equals("employee")) {
                cmbDesignation.setSelectedIndex(1);
            } else if (rs.getString("designation").equals("manager")) {
                cmbDesignation.setSelectedIndex(2);
            } else if (rs.getString("designation").equals("technician")) {
                cmbDesignation.setSelectedIndex(3);
            } else if (rs.getString("designation").equals("driver")) {
                cmbDesignation.setSelectedIndex(4);
            } else {
                cmbDesignation.setSelectedIndex(0);
            }

            //setting the value to the cmbDepartment
            if (rs.getString("department_name").equals("HR")) {
                cmbDepartment.setSelectedIndex(1);
            } else if (rs.getString("department_name").equals("Accounts")) {
                cmbDepartment.setSelectedIndex(2);
            } else if (rs.getString("department_name").equals("Technical")) {
                cmbDepartment.setSelectedIndex(3);
            } else if (rs.getString("department_name").equals("Marketing")) {
                cmbDepartment.setSelectedIndex(4);
            } else {
                cmbDepartment.setSelectedIndex(0);
            }

            //setting the value to the cmbType
            if (rs.getString("type").equals("Training")) {
                cmbType.setSelectedIndex(1);
            } else if (rs.getString("type").equals("Probation")) {
                cmbType.setSelectedIndex(2);
            } else if (rs.getString("type").equals("Temporary")) {
                cmbType.setSelectedIndex(3);
            } else if (rs.getString("type").equals("Permanent")) {
                cmbType.setSelectedIndex(4);
            } else {
                cmbType.setSelectedIndex(0);
            }
        if(gender.equals("Female"))
        {
        chFemale.setEnabled(false); 
        chMale.setEnabled(false); 
        chFemale.setSelected(true); 
        chMale.setSelected(false);  
        }
        else{
        chFemale.setEnabled(false); 
        chMale.setEnabled(false); 
        chFemale.setSelected(false); 
        chMale.setSelected(true);  
        }
       
    }
    
    } catch (Exception e) {
    e.printStackTrace();
    }
    }
    
    /*public View_Profile(String userID) throws SQLException
    {
     initComponents();
     
    try {
    
    eid=userID;
    
    txtID.setEnabled(false);
    txtNIC.setEnabled(false);
    txtPhone.setEnabled(false);
    cmbDesignation.setEnabled(false);
    cmbDepartment.setEnabled(false);
    cmbType.setEnabled(false);
    txtName.setEnabled(false);
    txtAddress.setEnabled(false);
    txtEmail.setEnabled(false);
    
    Connection c=DBAccess.mycon();
    Statement s=c.createStatement();
    ResultSet rs=s.executeQuery("select * from employee where empID='" + eid + "'");
    
    while(rs.next())
    {
    txtID.setText(rs.getString("emp_id"));
    txtNIC.setText(rs.getString("nic"));
    txtPhone.setText(rs.getString("phone"));
    cmbDesignation.setSelectedItem(rs.getString("designation"));
    cmbDepartment.setSelectedItem(rs.getString("department_name"));
    cmbType.setSelectedItem(rs.getString("department_type"));
    txtName.setText(rs.getString("ename"));
    txtAddress.setText(rs.getString("address"));
    txtEmail.setText(rs.getString("email"));
    gender=rs.getString("gender");
    
    if(gender=="Female")
    {
    chFemale.setSelected(true);
    chFemale.setEnabled(false);
    chMale.setEnabled(false);
    }
    else
    {
    chMale.setSelected(true);
    chMale.setEnabled(false);
    chFemale.setEnabled(false);
    }
    }
    
    } catch (Exception e) {
    e.printStackTrace();
    }
    
    }*/
    
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
        lblName = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        lblEmail = new javax.swing.JLabel();
        lblNIC = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        chMale = new javax.swing.JRadioButton();
        chFemale = new javax.swing.JRadioButton();
        txtID = new javax.swing.JLabel();
        txtNIC = new javax.swing.JLabel();
        txtPhone = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        lblDesignation1 = new javax.swing.JLabel();
        lblDepartment = new javax.swing.JLabel();
        cmbDepartment = new javax.swing.JComboBox();
        cmbDesignation = new javax.swing.JComboBox();
        btnAttendance = new javax.swing.JButton();
        btnLoan = new javax.swing.JButton();
        btnSalary = new javax.swing.JButton();
        btnLeaves = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("8WARE TOTAL MANAGEMENT SYSTEM");
        setExtendedState(6);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 20, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Employee Profile");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Logged in as Employer");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 80, -1, -1));

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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 110, 60, 20));

        jSeparator2.setForeground(new java.awt.Color(51, 153, 0));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1300, 10));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblName.setText("Employee Name");
        jPanel1.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        lblAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAddress.setText("Address");
        jPanel1.add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 200, -1));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setText("Email");
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        lblNIC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNIC.setText("NIC");
        jPanel1.add(lblNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, -1));

        lblGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGender.setText("Gender");
        jPanel1.add(lblGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        lblPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPhone.setText("Phone");
        jPanel1.add(lblPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, -1, -1));

        lblId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId.setText("Employee ID");
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 190, 20));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 190, 20));

        chMale.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chMale.setText("Male");
        jPanel1.add(chMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, -1, -1));

        chFemale.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chFemale.setText("Female");
        jPanel1.add(chFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, -1));

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 190, 20));

        txtNIC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 190, 20));

        txtPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 190, 20));

        lblType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblType.setText("Type");
        jPanel1.add(lblType, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));

        cmbType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select a type", "Training", "Probation", "Temporary", "Permanent" }));
        jPanel1.add(cmbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 190, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 750, 370));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Department Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDesignation1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDesignation1.setText("Designation");
        jPanel2.add(lblDesignation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        lblDepartment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDepartment.setText("Department");
        jPanel2.add(lblDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        cmbDepartment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select a department", "HR", "Accounts", "Technical", "Marketing" }));
        jPanel2.add(cmbDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 190, -1));

        cmbDesignation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbDesignation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select a designation", "employee", "manager", "technician", "driver" }));
        jPanel2.add(cmbDesignation, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 190, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 160, 440, 370));

        btnAttendance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAttendance.setText("Attendance");
        btnAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttendanceActionPerformed(evt);
            }
        });
        getContentPane().add(btnAttendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, -1, -1));

        btnLoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLoan.setText("Loan");
        btnLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoanActionPerformed(evt);
            }
        });
        getContentPane().add(btnLoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, -1, -1));

        btnSalary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalary.setText("Salary");
        btnSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalaryActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        btnLeaves.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLeaves.setText("Leaves");
        btnLeaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeavesActionPerformed(evt);
            }
        });
        getContentPane().add(btnLeaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rect.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1725, 713));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    new EMPHOME(eid).setVisible(true);
    this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

private void btnLeavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeavesActionPerformed
// TODO add your handling code here:
        try {
        ViewIReport.myleaves(eid);
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnLeavesActionPerformed

private void btnSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalaryActionPerformed
// TODO add your handling code here:
              try {
        ViewIReport.mySal(eid);
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnSalaryActionPerformed

private void btnLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoanActionPerformed
// TODO add your handling code here:
            try {
        ViewIReport.myloans(eid);
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnLoanActionPerformed

private void btnAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttendanceActionPerformed
// TODO add your handling code here:
             try {
        ViewIReport.myAtt(eid);
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnAttendanceActionPerformed

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
                new View_Profile().setVisible(true);
            }
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttendance;
    private javax.swing.JButton btnLeaves;
    private javax.swing.JButton btnLoan;
    private javax.swing.JButton btnSalary;
    private javax.swing.JRadioButton chFemale;
    private javax.swing.JRadioButton chMale;
    private javax.swing.JComboBox cmbDepartment;
    private javax.swing.JComboBox cmbDesignation;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblDesignation1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNIC;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblType;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtNIC;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtPhone;
    // End of variables declaration//GEN-END:variables

}
