/*
 * EmployeeRegistration.java
 *
 * Created on July 26, 2016, 1:42 AM
 */

package HRM;

/**
 *
 * @author  dell notebook
 */
public class EMPMANAGERHOME extends javax.swing.JFrame {
String emp_id;
    /** Creates new form EmployeeRegistration */
    public EMPMANAGERHOME() {
        initComponents();
        //txtName.setEnabled(false);
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
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnCalculate_Salary = new javax.swing.JButton();
        btnRegister_Employee = new javax.swing.JButton();
        btnReports = new javax.swing.JButton();
        btnCalculate_Salary2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("8WARE TOTAL MANAGEMENT SYSTEM");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 20, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Home");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Logged in as Employe Manager");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Logout");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 60, 20));

        jSeparator2.setForeground(new java.awt.Color(51, 153, 0));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 700, 10));

        btnCalculate_Salary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCalculate_Salary.setText("Update Employee");
        btnCalculate_Salary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculate_SalaryActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalculate_Salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, -1));

        btnRegister_Employee.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegister_Employee.setText("Register Employee");
        btnRegister_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegister_EmployeeActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegister_Employee, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        btnReports.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReports.setText("Reports");
        btnReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportsActionPerformed(evt);
            }
        });
        getContentPane().add(btnReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 140, -1));

        btnCalculate_Salary2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCalculate_Salary2.setText("Calculate Salary");
        btnCalculate_Salary2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculate_Salary2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalculate_Salary2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rect.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(806, 495));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void btnRegister_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegister_EmployeeActionPerformed
// TODO add your handling code here:
    new EmployeeRegistration().setVisible(true);
    this.dispose();
}//GEN-LAST:event_btnRegister_EmployeeActionPerformed

private void btnCalculate_SalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculate_SalaryActionPerformed
// TODO add your handling code here:
    new UpdateEmployee().setVisible(true);
    this.dispose();
}//GEN-LAST:event_btnCalculate_SalaryActionPerformed

private void btnCalculate_Salary2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculate_Salary2ActionPerformed
// TODO add your handling code here:
    new SalaryCalculation().setVisible(true);
    this.dispose();
}//GEN-LAST:event_btnCalculate_Salary2ActionPerformed

private void btnReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportsActionPerformed
// TODO add your handling code here:
    new Manager_Reports().setVisible(true);
    this.dispose();
}//GEN-LAST:event_btnReportsActionPerformed

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
                new EMPMANAGERHOME().setVisible(true);
            }
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculate_Salary;
    private javax.swing.JButton btnCalculate_Salary2;
    private javax.swing.JButton btnRegister_Employee;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}