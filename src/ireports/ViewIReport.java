/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ireports;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JRDesignViewer.*;
import net.sf.jasperreports.view.JRViewer;

/*
 * ViewIReport.java
 *
 * Created on July 26, 2016, 1:42 AM
 */
public class ViewIReport extends JFrame {

    public ViewIReport(String fileName) {
        this(fileName, null);
    }

    /* public ViewIReport(String fileName,HashMap parameter1,HashMap parameter2){
    super("View Report");
    
    
    
    try{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/8ware", "root","");
    
    
    JasperPrint print = JasperFillManager.fillReport(fileName, parameter1, con);
    JRViewer viewer = new JRViewer(print);
    
    Container c = getContentPane();
    c.add(viewer);
    }
    catch(ClassNotFoundException cnfe){
    cnfe.printStackTrace();
    }
    catch(SQLException sqle){
    sqle.printStackTrace();
    }
    catch(JRException jre){
    jre.printStackTrace();
    }
    catch(Exception e){
    e.printStackTrace();
    }
    setBounds(10, 10, 1200, 700);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }*/
    public ViewIReport(String fileName, HashMap parameter) {
        super("View Report");



        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/8ware", "root", "");


            JasperPrint print = JasperFillManager.fillReport(fileName, parameter, con);
            JRViewer viewer = new JRViewer(print);

            Container c = getContentPane();
            c.add(viewer);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (JRException jre) {
            jre.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setBounds(10, 10, 1200, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void allemp() {
       
        ViewIReport viewer = new ViewIReport("src\\ireports\\EmployeeAll.jasper");
        viewer.setVisible(true);
    }

    public static void allsal() {
        
        ViewIReport viewer = new ViewIReport("src\\ireports\\SalaryAll.jasper");
        viewer.setVisible(true);
    }

    public static void allloans() {
       
        ViewIReport viewer = new ViewIReport("src\\ireports\\LoanAllDetails.jasper");
        viewer.setVisible(true);
    }

    public static void allleaves() {
       
        ViewIReport viewer = new ViewIReport("src\\ireports\\LeavesAll.jasper");
        viewer.setVisible(true);
    }

    public static void allatt() {
       
        ViewIReport viewer = new ViewIReport("src\\ireports\\AttendanceAllDetails.jasper");
        viewer.setVisible(true);
    }

    public static void myleaves(String emp_id) {
        HashMap parameter1 = new HashMap();
        parameter1.put("pp", emp_id);
        ViewIReport viewer = new ViewIReport("src\\ireports\\EmployeeLeaves.jasper", parameter1);
        viewer.setVisible(true);
    }

    public static void myloans(String pemp_id) {
        HashMap parameter2 = new HashMap();
        parameter2.put("ppp", pemp_id);
        ViewIReport viewer = new ViewIReport("src\\ireports\\EmployeeLoans.jasper", parameter2);
        viewer.setVisible(true);
    }

    public static void mySal(String ppemp_id) {
        HashMap parameterSal = new HashMap();
        parameterSal.put("pppp", ppemp_id);
        ViewIReport viewer = new ViewIReport("src\\ireports\\EmployeeSal.jasper", parameterSal);
        viewer.setVisible(true);
    }
    
        public static void myAtt(String pppemp_id) {
        HashMap parametermyAtt = new HashMap();
        parametermyAtt.put("p", pppemp_id);
        ViewIReport viewer = new ViewIReport("src\\ireports\\EmployeeAttendance.jasper", parametermyAtt);
        viewer.setVisible(true);
    }
}
