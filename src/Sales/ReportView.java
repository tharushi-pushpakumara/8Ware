/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sales;

import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Hasindu
 */
public class ReportView extends JFrame
{
    public ReportView(String fileName) throws SQLException//static forms
    {
        this(fileName, null);
    }
    public ReportView(String fileName, HashMap para) throws SQLException//for parameterized
    {
        super("8Ware TotalManagement Systems Report");

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/8ware?zeroDateTimeBehavior=convertToNull","root","" );
            JasperPrint print = JasperFillManager.fillReport(fileName, para, con);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);            
        } 
        catch (JRException ex1)
        {
            System.out.println(ex1.getMessage());
        }
        setBounds(10, 10, 900, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
}
