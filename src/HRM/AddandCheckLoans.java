/*
 * EmployeeRegistration.java
 *
 * Created on July 26, 2016, 1:42 AM
 */

package HRM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


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
public class AddandCheckLoans extends javax.swing.JFrame {
String emp_id;
String ename;
String type;
double interest=0;
double need_amount=0;
int time_year=0;
int time_month=0;
int time_tot_months=0;
double monthly_installment=0;

validate check=new validate();
    /** Creates new form EmployeeRegistration */
    public AddandCheckLoans() {
        initComponents();

        try {
            Connection c = DBAccess.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select emp_id,ename from employee where emp_id='" + emp_id + "'");
            
            while(rs.next())
            {
                emp_id=rs.getString("emp_id");
            ename=rs.getString("ename");
            
            txtId.setText(emp_id);
            txtName.setText(ename);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public AddandCheckLoans(String p) {
        initComponents();

        emp_id=p;
        
        try {
            Connection c = DBAccess.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select emp_id,ename from employee where emp_id='" + emp_id + "'");
            
            while(rs.next())
            {
                emp_id=rs.getString("emp_id");
            ename=rs.getString("ename");
            
            txtId.setText(emp_id);
            txtName.setText(ename);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        panelEmployeeInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnView_Loan = new javax.swing.JButton();
        panelLoanInfo = new javax.swing.JPanel();
        lblAmount = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        cmbType = new javax.swing.JComboBox();
        lblType = new javax.swing.JLabel();
        cmbYears = new javax.swing.JSpinner();
        lblName2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbMonths = new javax.swing.JSpinner();
        txtIntereset = new javax.swing.JTextField();
        lblInterest = new javax.swing.JLabel();
        lblMonthly_Installment = new javax.swing.JLabel();
        txtMonthly_Installment = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("8WARE TOTAL MANAGEMENT SYSTEM");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 20, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Add and Check Loans");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Logged in as Employer");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("Home");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 60, 20));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Logout");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 120, 60, 20));

        jSeparator2.setForeground(new java.awt.Color(51, 153, 0));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 830, 10));

        panelEmployeeInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelEmployeeInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID");
        panelEmployeeInfo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        txtId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelEmployeeInfo.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 190, -1));

        lblName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblName.setText("Name");
        panelEmployeeInfo.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelEmployeeInfo.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 190, -1));

        btnView_Loan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnView_Loan.setText("View Loan");
        btnView_Loan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView_LoanActionPerformed(evt);
            }
        });
        panelEmployeeInfo.add(btnView_Loan, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        getContentPane().add(panelEmployeeInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 360, 340));

        panelLoanInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loan Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelLoanInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAmount.setText("Amount");
        panelLoanInfo.add(lblAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        txtAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAmountFocusLost(evt);
            }
        });
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAmountKeyReleased(evt);
            }
        });
        panelLoanInfo.add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 190, -1));

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        panelLoanInfo.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, -1, -1));

        cmbType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Housing", "Vehicle", "Educational" }));
        cmbType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbTypeMouseReleased(evt);
            }
        });
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });
        cmbType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTypeKeyReleased(evt);
            }
        });
        panelLoanInfo.add(cmbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 190, -1));

        lblType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblType.setText("Type");
        panelLoanInfo.add(lblType, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        cmbYears.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbYears.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        cmbYears.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbYearsMouseReleased(evt);
            }
        });
        cmbYears.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cmbYearsStateChanged(evt);
            }
        });
        cmbYears.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbYearsKeyPressed(evt);
            }
        });
        panelLoanInfo.add(cmbYears, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 40, -1));

        lblName2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblName2.setText("Time Period");
        panelLoanInfo.add(lblName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel6.setText("Years:");
        panelLoanInfo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, -1));

        jLabel7.setText("Months:");
        panelLoanInfo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        cmbMonths.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbMonths.setModel(new javax.swing.SpinnerNumberModel(0, 0, 12, 1));
        cmbMonths.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cmbMonthsStateChanged(evt);
            }
        });
        panelLoanInfo.add(cmbMonths, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 40, -1));

        txtIntereset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIntereset.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtInteresetFocusLost(evt);
            }
        });
        panelLoanInfo.add(txtIntereset, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 190, -1));

        lblInterest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblInterest.setText("Interest");
        panelLoanInfo.add(lblInterest, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        lblMonthly_Installment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMonthly_Installment.setText("Monthly Installment");
        panelLoanInfo.add(lblMonthly_Installment, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        txtMonthly_Installment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLoanInfo.add(txtMonthly_Installment, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 190, -1));

        getContentPane().add(panelLoanInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 440, 350));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rect.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(929, 575));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
// TODO add your handling code here:
    
    try {
                type=cmbType.getSelectedItem().toString();
    
    if(type=="Housing")
    {
        interest=0.5;
    }
    else if(type=="Vehicle")
    {
        interest=0.3;
    }
    else if (type=="Educational")
    {
        interest=0.1;
    }
    
    txtIntereset.setText(Double.toString(interest));
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}//GEN-LAST:event_cmbTypeActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    new EMPHOME(emp_id).setVisible(true);
    this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

private void btnView_LoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_LoanActionPerformed
// TODO add your handling code here:
    //generating a report
        try {
        ViewIReport.myloans(emp_id);
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnView_LoanActionPerformed

private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
// TODO add your handling code here:
    //sending to the db
    try {
        Connection c = DBAccess.getConnection();
        Statement s = c.createStatement();
        s.executeUpdate("Insert into add_loans (emp_id,time_period,type,monthly_installment,amount,interest) values('" + emp_id + "','" + time_tot_months + "','" + type + "','" + monthly_installment + "','" + need_amount + "','" + interest + "')");
        JOptionPane.showMessageDialog(panelLoanInfo, "Saved !!!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnAddActionPerformed

private void txtAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyReleased
// TODO add your handling code here:
    need_amount=Double.parseDouble(txtAmount.getText());
}//GEN-LAST:event_txtAmountKeyReleased

private void cmbTypeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTypeKeyReleased
// TODO add your handling code here:

}//GEN-LAST:event_cmbTypeKeyReleased

private void cmbTypeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbTypeMouseReleased
// TODO add your handling code here:

}//GEN-LAST:event_cmbTypeMouseReleased

private void cmbYearsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbYearsMouseReleased
// TODO add your handling code here:

}//GEN-LAST:event_cmbYearsMouseReleased

private void cmbYearsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbYearsKeyPressed
// TODO add your handling code here:

}//GEN-LAST:event_cmbYearsKeyPressed

private void cmbYearsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cmbYearsStateChanged
// TODO add your handling code here:
            time_year=Integer.parseInt(cmbYears.getValue().toString());
    time_month=Integer.parseInt(cmbMonths.getValue().toString());
    
    time_tot_months=(time_month+(time_year*12));
    
    monthly_installment=((need_amount/time_tot_months)*interest);
    
    txtMonthly_Installment.setText(Double.toString(monthly_installment));
}//GEN-LAST:event_cmbYearsStateChanged

private void cmbMonthsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cmbMonthsStateChanged
// TODO add your handling code here:
                time_year=Integer.parseInt(cmbYears.getValue().toString());
    time_month=Integer.parseInt(cmbMonths.getValue().toString());
    
    time_tot_months=(time_month+(time_year*12));
    
    monthly_installment=((need_amount/time_tot_months)*interest);
    
    txtMonthly_Installment.setText(Double.toString(monthly_installment));
}//GEN-LAST:event_cmbMonthsStateChanged

private void txtInteresetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtInteresetFocusLost
// TODO add your handling code here:
  
}//GEN-LAST:event_txtInteresetFocusLost

private void txtAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAmountFocusLost
// TODO add your handling code here:
        if(!check.isNumeric(txtAmount.getText()))
    {
        JOptionPane.showMessageDialog(rootPane, "Pls enter an valid  amount !!");
         txtAmount.setText("");
    }
}//GEN-LAST:event_txtAmountFocusLost

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
                new AddandCheckLoans().setVisible(true);
            }
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnView_Loan;
    private javax.swing.JSpinner cmbMonths;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JSpinner cmbYears;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblInterest;
    private javax.swing.JLabel lblMonthly_Installment;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel panelEmployeeInfo;
    private javax.swing.JPanel panelLoanInfo;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIntereset;
    private javax.swing.JTextField txtMonthly_Installment;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

}