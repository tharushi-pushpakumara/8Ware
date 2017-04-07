/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import HRM.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Menaka
 */
public class ServiceManagement extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //java.util.Date Todaydate = new java.util.Date();

    /**
     * Creates new form ServiceManagement
     */
    public ServiceManagement() throws Exception {
        initComponents();
        
        conn = DBAccess.getConnection();
        
        java.util.Date date1=new java.util.Date();
        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String dd=sdf.format(date1);
        AIDATE.setText(dd);

        /*try {
            Connection c=DBconnect.connect();
            Statement s=c.createStatement();
            ResultSet rs=s.executeQuery("SELECT * FROM citem");
            Vector v=new Vector();
            while(rs.next())
            {
                v.add(rs.getString("item_code"));
            }
            JTIDCOM.setModel(new DefaultComboBoxModel(v));
            //JTIDCOM.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Itemcode();
        Jobcode();
        vjobid();
        vclient();
        vclientid();
        itemid();
        vitemid();
        jobtech();
        agreementid();
        technicianid();
        vtechnicianid();
        tableload();
        tableloadtech();
        tableloadjob();
        tableloaditem();
        viewAgreement();
        //tableloadAgreement(); 

    }

    void Itemcode() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT MAX(item_code) FROM citem");
            while (rs.next()) {
                String i1 = rs.getString("MAX(item_code)");
                int i2 = Integer.parseInt(i1.substring(1));
                int i3 = (i2 + 1);
                AIIC.setText("I" + i3);

            }
        } catch (Exception e) {
            AIIC.setText("I" + 101);
        }
    }

    void Jobcode() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT MAX(job_id) FROM job");
            while (rs.next()) {
                String i1 = rs.getString("MAX(job_id)");
                int i2 = Integer.parseInt(i1.substring(1));
                int i3 = (i2 + 1);
                ajjobidtxt.setText("J" + i3);

            }
        } catch (Exception e) {
            AIIC.setText("I" + 1001);
        }
    }

    public void tableload() {
        try {
            String Sql = "SELECT clientId,name,address,mobile,telephone,fax,email FROM client";

            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            DefaultTableModel d = (DefaultTableModel) ctablel.getModel();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("clientId"));
                v.add(rs.getString("name"));
                v.add(rs.getString("address"));
                v.add(rs.getString("mobile"));
                v.add(rs.getString("telephone"));
                 v.add(rs.getString("fax"));
                v.add(rs.getString("email"));
                d.addRow(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void serchResult(String name) {

        try {
            String Sql = "SELECT clientId,name,address,mobile,telephone,fax,email  FROM client WHERE clientId LIKE '%" + name + "%' ";
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            ctablel.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void tableloadtech() {
        try {
            String Sql = "SELECT tech_Id,tech_name,tech_add,tech_phone,tech_email FROM technician";

            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            DefaultTableModel d1 = (DefaultTableModel) VTTABEL.getModel();
            while (rs.next()) {
                Vector v1 = new Vector();
                v1.add(rs.getString("tech_Id"));
                v1.add(rs.getString("tech_name"));
                v1.add(rs.getString("tech_add"));
                v1.add(rs.getString("tech_phone"));
                v1.add(rs.getString("tech_email"));
                d1.addRow(v1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void serchResulttech(String name) {

        try {
            String Sql = "SELECT tech_Id,tech_name,tech_add,tech_phone,tech_email  FROM technician WHERE tech_Id LIKE '%" + name + "%' ";
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            VTTABEL.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void tableloadjob() {
        try {
            String Sql = "SELECT job_id,client,technician,priority,status FROM job";

            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            JOBTABLE.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void serchResultjob(String name) {

        try {
            String Sql = "SELECT job_id,client,technician,priority,status FROM job WHERE job_Id LIKE '%" + name + "%' ";
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            JOBTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void tableloaditem() {
        try {
            String Sql = "SELECT item_code,agreement_id,type,date,client,technician FROM citem ";

            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            ITEMTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void serchResultitem(String name) {

        try {
            String Sql = "SELECT item_code,agreement_id,type,date,client,technician FROM citem WHERE item_code LIKE '%" + name + "%' ";
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            ITEMTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /* public static ResultSet dispalyTableExpiary() throws ClassNotFoundException/////
    {
        
        
        ResultSet rs = null;
        try {
            Connection con = DBconnect_1.connect();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT Agreement.agreementId,Agreement.`type`,Agreement.lastRenewDate,Agreement.expiryDate,Agreement.clientIdFKk,Agreement_Assest.assest FROM Agreement,Agreement_Assest WHERE Agreement.agreementId = Agreement_Assest.agreementIdFk");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }

        return rs;
    }*/
 /*public void tableloadAgreement() {
        try {
            String Sql = "SELECT agreementId,type,lastRenewDate,expiryDate,clientIdFKk FROM agreement";

            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            JOBTABLE.setModel(DbUtils.resultSetToTableModel(rs));
           
        } catch (Exception e) {
            System.out.println(e);
        }

    }*/
    void viewAgreement() {
        try {
            DefaultTableModel d = (DefaultTableModel) VAGRTABLE.getModel();

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from agreement");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("agreementId"));
                v.add(rs.getString("type"));
                v.add(rs.getString("lastRenewDate"));
                v.add(rs.getString("expiryDate"));
                v.add(rs.getString("clientIdFkk"));

                d.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void itemid() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT item_code FROM citem");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("item_code"));
            }
            JID.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void agreementid() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT agreementId FROM agreement");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("agreementId"));
            }
            CATAI.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void technicianid() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT emp_id FROM employee");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("emp_id"));
            }
            ATTID.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void vtechnicianid() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT emp_id FROM employee");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("emp_id"));
            }
            VTID.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void vjobid() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT job_id FROM job");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("job_id"));
            }
            VJJIDTXT.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void vitemid() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT item_code FROM citem");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("item_code"));
            }
            VIID1.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void vclient() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT name FROM client");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("name"));
            }
            ajclienttxt.setModel(new DefaultComboBoxModel(v));
            ITATID.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void jobtech() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT tech_name FROM technician");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("tech_name"));
            }
            ajtechniciantxt.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     void vclientid() {
        try {
            ResultSet rs = DBAccess.getConnection().createStatement().executeQuery("SELECT clientId FROM client");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("clientId"));
            }
          VCID1.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    

    void printReport() {

        String type = AGTXT.getText();

        try {

            JasperDesign jd = JRXmlLoader.load("C:\\Users\\Tharushi\\Documents\\NetBeansProjects\\8Ware\\src\\Services\\AGR.jrxml");
            String sql = "SELECT * FROM agreement WHERE agreementId like '%" + type + "%' ";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Eneter valid Agreement Id");
            System.out.println(e);
        }

    }

    void viewAgreements() {
        try {
            JasperReport r = JasperCompileManager.compileReport("C:\\Users\\Tharushi\\Documents\\NetBeansProjects\\8Ware\\src\\Services\\agreement.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(r, null, DBAccess.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*void print() {
        try {
            JasperReport r = JasperCompileManager.compileReport("C:\\Users\\Menaka\\Desktop\\AGR.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(r, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        ATadd = new javax.swing.JButton();
        ATCancel = new javax.swing.JButton();
        ATTEMAIL = new javax.swing.JTextField();
        ATTADD = new javax.swing.JTextField();
        ATTTEL = new javax.swing.JTextField();
        ATTNAME = new javax.swing.JTextField();
        ATEM = new javax.swing.JLabel();
        ATID = new javax.swing.JLabel();
        ATNA = new javax.swing.JLabel();
        ATTEL = new javax.swing.JLabel();
        ATADD = new javax.swing.JLabel();
        ATTID = new javax.swing.JComboBox<>();
        ATD = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        VTTABEL = new javax.swing.JTable();
        VTTN = new javax.swing.JTextField();
        ATEM1 = new javax.swing.JLabel();
        ATTEL1 = new javax.swing.JLabel();
        ATADD1 = new javax.swing.JLabel();
        ATNA1 = new javax.swing.JLabel();
        VTUPDATE = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        VTTE = new javax.swing.JTextField();
        VTD = new javax.swing.JButton();
        VTS = new javax.swing.JButton();
        VTTA = new javax.swing.JTextField();
        VTTTE = new javax.swing.JTextField();
        VTC = new javax.swing.JButton();
        VTID = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JOBTABLE = new javax.swing.JTable();
        VJDELETE = new javax.swing.JButton();
        VJUPDATE = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        VJJIDL = new javax.swing.JLabel();
        VJSEARCH = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        VJPRIO = new javax.swing.JComboBox();
        VJSTAT = new javax.swing.JComboBox();
        VJCLIENT = new javax.swing.JTextField();
        VJTECH = new javax.swing.JTextField();
        VJJIDTXT = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        ajmobiletxt = new javax.swing.JTextField();
        ajemailtxt = new javax.swing.JTextField();
        AJITEM = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        AJSERVICE = new javax.swing.JButton();
        ajjobid = new javax.swing.JLabel();
        ajjobidtxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        AJSER = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ajprioritycom = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        JSCOM = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        ajstatuscom = new javax.swing.JComboBox();
        AJADD = new javax.swing.JButton();
        AJCANCEL = new javax.swing.JButton();
        JID = new javax.swing.JComboBox<>();
        AJDE = new javax.swing.JButton();
        ajclienttxt = new javax.swing.JComboBox<>();
        ajtechniciantxt = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        AIC = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        AIID = new javax.swing.JTextArea();
        AITYP = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        AIADD = new javax.swing.JButton();
        AIYES = new javax.swing.JButton();
        AINO = new javax.swing.JButton();
        AITXT = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        CATAREA = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        TATTXT = new javax.swing.JTextField();
        AIAID = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ATMOD = new javax.swing.JTextField();
        AIIC = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        CATAI = new javax.swing.JComboBox<>();
        AID = new javax.swing.JButton();
        ITATID = new javax.swing.JComboBox<>();
        AIDATE = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        VIID = new javax.swing.JLabel();
        VIS = new javax.swing.JButton();
        VITYPE = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        ITEMTABLE = new javax.swing.JTable();
        VIC = new javax.swing.JButton();
        AIU = new javax.swing.JButton();
        VID = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        VICLIENT = new javax.swing.JTextField();
        VITECH = new javax.swing.JTextField();
        VIID1 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        CVSearch = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        ctablel = new javax.swing.JTable();
        VCNAME = new javax.swing.JLabel();
        VCID = new javax.swing.JLabel();
        VCDel = new javax.swing.JButton();
        VCTEL = new javax.swing.JLabel();
        VCADD = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        VCEMAIL = new javax.swing.JLabel();
        VCADD1 = new javax.swing.JTextField();
        VCNAME1 = new javax.swing.JTextField();
        VCEMAIL1 = new javax.swing.JTextField();
        VCTEL1 = new javax.swing.JTextField();
        VCID1 = new javax.swing.JComboBox<>();
        VCTEL2 = new javax.swing.JLabel();
        VCMOB = new javax.swing.JTextField();
        VCEMAIL2 = new javax.swing.JLabel();
        VCTEL3 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        VAGRTABLE = new javax.swing.JTable();
        AGPR = new javax.swing.JButton();
        AGTXT = new javax.swing.JTextField();
        AGVI = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ATadd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATadd.setText("ADD");
        ATadd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        ATadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATaddActionPerformed(evt);
            }
        });
        jPanel4.add(ATadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 112, -1));

        ATCancel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATCancel.setText("CANCEL");
        ATCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        ATCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATCancelActionPerformed(evt);
            }
        });
        jPanel4.add(ATCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 112, -1));

        ATTEMAIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATTEMAILActionPerformed(evt);
            }
        });
        jPanel4.add(ATTEMAIL, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 480, -1));

        ATTADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATTADDActionPerformed(evt);
            }
        });
        jPanel4.add(ATTADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 483, -1));

        ATTTEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATTTELActionPerformed(evt);
            }
        });
        jPanel4.add(ATTTEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 480, -1));

        ATTNAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATTNAMEActionPerformed(evt);
            }
        });
        jPanel4.add(ATTNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 483, -1));

        ATEM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATEM.setText("E-mail");
        jPanel4.add(ATEM, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        ATID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATID.setText("Technician Id");
        jPanel4.add(ATID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        ATNA.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATNA.setText("Technician Name");
        jPanel4.add(ATNA, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        ATTEL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATTEL.setText("Tele:number");
        jPanel4.add(ATTEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        ATADD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATADD.setText("Address");
        jPanel4.add(ATADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        ATTID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(ATTID, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 483, -1));

        ATD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATD.setText("DEMO");
        ATD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        ATD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATDActionPerformed(evt);
            }
        });
        jPanel4.add(ATD, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, 98, -1));

        jTabbedPane2.addTab("Add Technician", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VTTABEL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Technician Id", "Technician Name", "Address", "Telephone", "Email"
            }
        ));
        VTTABEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VTTABELMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VTTABEL);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 289, 644, 138));
        jPanel5.add(VTTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 89, 188, -1));

        ATEM1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATEM1.setText("E-mail");
        jPanel5.add(ATEM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 248, -1, -1));

        ATTEL1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATTEL1.setText("Tele:number");
        jPanel5.add(ATTEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 192, -1, -1));

        ATADD1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATADD1.setText("Address");
        jPanel5.add(ATADD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 138, -1, -1));

        ATNA1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ATNA1.setText("Technician Name");
        jPanel5.add(ATNA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 86, -1, -1));

        VTUPDATE.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VTUPDATE.setText("Update");
        VTUPDATE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VTUPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VTUPDATEActionPerformed(evt);
            }
        });
        jPanel5.add(VTUPDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 454, 92, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Technician Id");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 34, -1, -1));
        jPanel5.add(VTTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 251, 188, -1));

        VTD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VTD.setText("Delete");
        VTD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VTDActionPerformed(evt);
            }
        });
        jPanel5.add(VTD, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 454, 69, -1));

        VTS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VTS.setText("Search");
        VTS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VTSActionPerformed(evt);
            }
        });
        jPanel5.add(VTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 33, 75, -1));
        jPanel5.add(VTTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 138, 188, -1));
        jPanel5.add(VTTTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 195, 188, -1));

        VTC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VTC.setText("Cancel");
        VTC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        jPanel5.add(VTC, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 454, 67, -1));

        VTID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(VTID, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 180, -1));

        jTabbedPane2.addTab("View Technician", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("Technician", jPanel1);

        JOBTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Job Id", "Client", "Due  Date", "Technician", "priority", "Status"
            }
        ));
        JOBTABLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOBTABLEMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JOBTABLE);

        VJDELETE.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VJDELETE.setText("Delete");
        VJDELETE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VJDELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VJDELETEActionPerformed(evt);
            }
        });

        VJUPDATE.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VJUPDATE.setText("Update");
        VJUPDATE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VJUPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VJUPDATEActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton4.setText("Cancel");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));

        VJJIDL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VJJIDL.setText("Job Id");

        VJSEARCH.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VJSEARCH.setText("Search");
        VJSEARCH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VJSEARCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VJSEARCHActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Client");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setText("Technician");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Priority");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel27.setText("Status");

        VJPRIO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT PRIORITY", "HIGHT", "LOW", "MEDIUM" }));

        VJSTAT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT STATUS", "Job Complete", "Pending", "Technical Officer  On Rout To Customer", "Repair In Progress" }));
        VJSTAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VJSTATActionPerformed(evt);
            }
        });

        VJCLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VJCLIENTActionPerformed(evt);
            }
        });

        VJJIDTXT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel16)
                .addGap(28, 28, 28)
                .addComponent(VJTECH, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel23)
                .addGap(28, 28, 28)
                .addComponent(VJPRIO, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel27)
                .addGap(28, 28, 28)
                .addComponent(VJSTAT, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(VJDELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(VJUPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(VJJIDL)
                        .addGap(28, 28, 28)
                        .addComponent(VJJIDTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addComponent(VJCLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addComponent(VJSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(VJJIDL)
                        .addComponent(VJJIDTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(VJSEARCH))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(VJCLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(VJTECH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(VJPRIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(VJSTAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VJDELETE)
                    .addComponent(VJUPDATE)
                    .addComponent(jButton4)))
        );

        jTabbedPane3.addTab("View Job", jPanel7);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ajmobiletxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajmobiletxtActionPerformed(evt);
            }
        });
        jPanel6.add(ajmobiletxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 254, 300, -1));

        ajemailtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajemailtxtActionPerformed(evt);
            }
        });
        jPanel6.add(ajemailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 304, 300, -1));

        AJITEM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AJITEM.setText("Item");
        AJITEM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AJITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AJITEMActionPerformed(evt);
            }
        });
        jPanel6.add(AJITEM, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, 60, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText("Priority");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(629, 337, -1, -1));

        AJSERVICE.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AJSERVICE.setText("Service");
        AJSERVICE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AJSERVICE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AJSERVICEActionPerformed(evt);
            }
        });
        jPanel6.add(AJSERVICE, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, 70, -1));

        ajjobid.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ajjobid.setText("Job Id");
        jPanel6.add(ajjobid, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 72, -1, -1));

        ajjobidtxt.setEditable(false);
        jPanel6.add(ajjobidtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 75, 298, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setText("Type");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Client");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 215, -1, 14));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 102, 0));
        jLabel6.setText("Client Details");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 150, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText(" Email");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Contact Mobile");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        AJSER.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AJSER.setText("Service");
        jPanel6.add(AJSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 102, 0));
        jLabel10.setText("Technician Details");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 67, -1, -1));

        ajprioritycom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT PRIORITY", "HIGHT", "LOW", "MEDIUM" }));
        jPanel6.add(ajprioritycom, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 337, 259, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Technician");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, -1, -1));

        JSCOM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internet", "Network", "Email", "Sorftware", "WebSites", "Other" }));
        jPanel6.add(JSCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 259, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Status");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 387, -1, -1));

        ajstatuscom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT STATUS", "Job Complete", "Pending", "Technical Officer  On Rout To Customer", "Repair In Progress" }));
        ajstatuscom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajstatuscomActionPerformed(evt);
            }
        });
        jPanel6.add(ajstatuscom, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 390, 260, -1));

        AJADD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AJADD.setText("ADD");
        AJADD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AJADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AJADDActionPerformed(evt);
            }
        });
        jPanel6.add(AJADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 59, -1));

        AJCANCEL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AJCANCEL.setText("CANCEL");
        AJCANCEL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        jPanel6.add(AJCANCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 86, -1));

        JID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(JID, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 260, -1));

        AJDE.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AJDE.setText("DEMO");
        AJDE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AJDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AJDEActionPerformed(evt);
            }
        });
        jPanel6.add(AJDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 80, -1));

        ajclienttxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(ajclienttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 212, 300, -1));

        ajtechniciantxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(ajtechniciantxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 260, -1));

        jTabbedPane3.addTab("Add Job", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Job", jPanel2);

        AIC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AIC.setText("CANCEL");
        AIC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AICActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Type");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Item Description");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setText("Date");

        AIID.setColumns(20);
        AIID.setRows(5);
        jScrollPane4.setViewportView(AIID);

        AITYP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ITEM", "Monitor", "Lap Top", "UPS", "Server", "Printer", "Computer" }));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 102, 0));
        jLabel22.setText("ON AGREEMENT?");

        AIADD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AIADD.setText("ADD");
        AIADD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AIADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AIADDActionPerformed(evt);
            }
        });

        AIYES.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AIYES.setText("Yes");
        AIYES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AIYESActionPerformed(evt);
            }
        });

        AINO.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AINO.setText("No");
        AINO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AINOActionPerformed(evt);
            }
        });

        AITXT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AITXT.setText("Client");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Additional Client info");

        CATAREA.setColumns(20);
        CATAREA.setRows(5);
        jScrollPane6.setViewportView(CATAREA);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Handled By");

        AIAID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AIAID.setText("Agreement Id");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setText("Model");

        AIIC.setEditable(false);

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel31.setText("Item Code");

        CATAI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        AID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AID.setText("DEMO");
        AID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AIDActionPerformed(evt);
            }
        });

        ITATID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        AIDATE.setText("date");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(AIIC, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260)
                        .addComponent(jLabel22))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(AITYP, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(AIDATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(240, 240, 240)
                        .addComponent(AIYES, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(AINO, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(ATMOD, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(AIAID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(CATAI, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(525, 525, 525)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(TATTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(AIADD, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AID, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AIC, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(AITXT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(ITATID, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(199, 199, 199))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(AIIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel22)))
                .addGap(8, 8, 8)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(AIDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel19))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(AITYP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AIYES)
                            .addComponent(AINO))))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ATMOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AIAID)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(CATAI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AITXT)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(ITATID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(TATTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AIADD)
                    .addComponent(AID)
                    .addComponent(AIC)))
        );

        jTabbedPane4.addTab("Add Item", jPanel8);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Type");

        VIID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VIID.setText("Item Code");

        VIS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VIS.setText("Search");
        VIS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VISActionPerformed(evt);
            }
        });

        VITYPE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ITEM", "Monitor", "Lap Top", "UPS", "Server", "Printer", "Computer" }));

        ITEMTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Type", "Date", "Client", "Agreement Id", "Handle By"
            }
        ));
        ITEMTABLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ITEMTABLEMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ITEMTABLE);
        if (ITEMTABLE.getColumnModel().getColumnCount() > 0) {
            ITEMTABLE.getColumnModel().getColumn(4).setResizable(false);
        }

        VIC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VIC.setText("Cancel");
        VIC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));

        AIU.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AIU.setText("Update");
        AIU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AIU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AIUActionPerformed(evt);
            }
        });

        VID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VID.setText("Delete");
        VID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIDActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("Client");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel28.setText("Handle By");

        VIID1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(VID, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(AIU, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(VIC, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11)
                            .addComponent(VIID))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VICLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VITECH, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(VIID1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(VITYPE, 0, 203, Short.MAX_VALUE))
                                .addGap(49, 49, 49)
                                .addComponent(VIS, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(286, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VIID)
                    .addComponent(VIS)
                    .addComponent(VIID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VITYPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(VICLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VITECH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VID)
                    .addComponent(AIU)
                    .addComponent(VIC))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("View Item", jPanel9);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTabbedPane1.addTab("Item", jPanel3);

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton6.setText("Cancel");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 499, 70, -1));

        CVSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CVSearch.setText("Search");
        CVSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        CVSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CVSearchActionPerformed(evt);
            }
        });
        jPanel10.add(CVSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 69, 87, -1));

        ctablel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        ctablel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctablelMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(ctablel);

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 641, 141));

        VCNAME.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCNAME.setText("Client Name ");
        jPanel10.add(VCNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        VCID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCID.setText("Client Id");
        jPanel10.add(VCID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        VCDel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCDel.setText("Delete");
        VCDel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        VCDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VCDelActionPerformed(evt);
            }
        });
        jPanel10.add(VCDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 499, 69, -1));

        VCTEL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCTEL.setText("Mobile");
        jPanel10.add(VCTEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        VCADD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCADD.setText("Address");
        jPanel10.add(VCADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jButton7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton7.setText("Update");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 499, 80, -1));

        VCEMAIL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCEMAIL.setText("Fax");
        jPanel10.add(VCEMAIL, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 50, -1));
        jPanel10.add(VCADD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 166, -1));
        jPanel10.add(VCNAME1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 166, -1));
        jPanel10.add(VCEMAIL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 166, -1));
        jPanel10.add(VCTEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 166, -1));

        VCID1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel10.add(VCID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 166, -1));

        VCTEL2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCTEL2.setText("Tel:no");
        jPanel10.add(VCTEL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));
        jPanel10.add(VCMOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 170, -1));

        VCEMAIL2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        VCEMAIL2.setText("E-mail");
        jPanel10.add(VCEMAIL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));
        jPanel10.add(VCTEL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 166, -1));

        jTabbedPane1.addTab("View Client", jPanel10);

        VAGRTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Agrrement Id", "Type", "LastRDate", "ExpiryDate", "Client Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(VAGRTABLE);

        AGPR.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AGPR.setText("Print");
        AGPR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AGPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AGPRActionPerformed(evt);
            }
        });

        AGVI.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AGVI.setText("View");
        AGVI.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        AGVI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AGVIActionPerformed(evt);
            }
        });

        jLabel13.setText("EnterAgreement Id");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(AGTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(AGPR, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(AGVI, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(jLabel13)
                .addGap(27, 27, 27)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AGPR)
                    .addComponent(AGTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AGVI))
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Agreement", jPanel11);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Services/rect_1.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 153));
        jLabel25.setText("SERVICE MANAGEMENT");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 153, 0));
        jLabel26.setText("Login As Administrator");

        jButton11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton11.setText("LOGOUT");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ATaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATaddActionPerformed

        technicians tech = new technicians();
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to add");

        if (x == 0) {

            tech.tech_id = ATTID.getSelectedItem().toString();
            tech.tech_name = ATTNAME.getText();
            tech.tech_add = ATTADD.getText();
            tech.tech_phone = ATTTEL.getText();
            tech.tech_email = ATTEMAIL.getText();

            if (validation.isValidTecnicianId(tech.tech_id)) {
                if (validation.isValidEmailAddress(tech.tech_email)) {
                    if (validation.isValidPhone(tech.tech_phone)) {

                        try {
                            String q = "INSERT INTO technician VALUES ('" + tech.tech_id + "','" + tech.tech_name + "','" + tech.tech_add + "','" + tech.tech_phone + "','" + tech.tech_email + "')";
                            pst = conn.prepareStatement(q);
                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Data Successfully Added");
                            ATTID.setSelectedItem("");
                            ATTNAME.setText("");
                            ATTADD.setText("");
                            ATTTEL.setText("");
                            ATTEMAIL.setText("");

                        } catch (SQLException e) {

                            System.out.println(e);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "wrong Phone Number  ");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "wrong Email Address ");
                }

            } else {
                JOptionPane.showMessageDialog(null, "wrong Technician Id ");
            }

        }// TODO add your handling code here:
    }//GEN-LAST:event_ATaddActionPerformed

    private void ATCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATCancelActionPerformed
        /* this.dispose();
        main m = new main();
        m.setVisible(true);*/
    }//GEN-LAST:event_ATCancelActionPerformed

    private void ATTEMAILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATTEMAILActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ATTEMAILActionPerformed

    private void ATTADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATTADDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ATTADDActionPerformed

    private void ATTTELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATTTELActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ATTTELActionPerformed

    private void ATTNAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATTNAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ATTNAMEActionPerformed

    private void VTUPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VTUPDATEActionPerformed

        technicians tech1 = new technicians();
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to add");

        if (x == 0) {

            tech1.tech_id = VTID.getSelectedItem().toString();
            tech1.tech_name = VTTN.getText();
            tech1.tech_add = VTTA.getText();
            tech1.tech_phone = VTTTE.getText();
            tech1.tech_email = VTTE.getText();

            String sql1 = "UPDATE technician SET  tech_name = '" + tech1.tech_name + "', tech_add = '" + tech1.tech_add + "',tech_phone ='" + tech1.tech_phone + "',tech_email='" + tech1.tech_email + "'  WHERE tech_id= '" + tech1.tech_id + "'";
            try {
                pst = conn.prepareStatement(sql1);
                pst.execute();
                tableloadtech();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            clear();
        }
    }

    public void clear() {

        VTTN.setText("");
        VTTA.setText("");
        VCTEL1.setText("");
        VTTTE.setText("");
        VTTE.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_VTUPDATEActionPerformed

    private void ajmobiletxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajmobiletxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajmobiletxtActionPerformed

    private void ajemailtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajemailtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajemailtxtActionPerformed

    private void AJITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AJITEMActionPerformed
        JSCOM.setVisible(false);
        JID.setVisible(true);
        AJSER.setText("Item Id");
        // TODO add your handling code here:
    }//GEN-LAST:event_AJITEMActionPerformed

    private void AJSERVICEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AJSERVICEActionPerformed
        JSCOM.setVisible(true);
        JID.setVisible(false);
        AJSER.setText("Service");// TODO add your handling code here:
    }//GEN-LAST:event_AJSERVICEActionPerformed

    private void VISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VISActionPerformed

        serchResultitem(VIID1.getSelectedItem().toString());        // TODO add your handling code here:
    }//GEN-LAST:event_VISActionPerformed

    private void AICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AICActionPerformed

    private void AIADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AIADDActionPerformed

        item it = new item();
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to add");

        if (x == 0) {

            it.item_code = AIIC.getText();
            it.agreement_id = CATAI.getSelectedItem().toString();
            /*it.type = AITYP.getSelectedItem().toString();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            it.date = sdf.format(AIDATE.getDate());*/
            //java.sql.Date pd = java.sql.Date.valueOf(it.date);
            it.model = ATMOD.getText();
            it.item_desc = AIID.getText();
            it.client_name = ITATID.getSelectedItem().toString();
            it.client_info = CATAREA.getText();
            it.technician = TATTXT.getText();

            try {
                String q = "INSERT INTO citem(item_code,agreement_id,type,date,model,item_desc,client,client_info,technician) VALUES ('" + it.item_code + "','" + it.agreement_id + "','" + it.type + "','" + AIDATE.getText() + "','" + it.model + "','" + it.item_desc + "','" + it.client_name + "','" + it.client_info + "','" + it.technician + "')";
                pst = conn.prepareStatement(q);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data Successfully Added");

                AIIC.setText("");
                CATAI.setSelectedItem("");
                AITYP.setSelectedItem("");
                //AIDATE.setCalendar(null);
                ATMOD.setText("");
                AIID.setText("");
                ITATID.setSelectedItem("");
                CATAREA.setText("");
                TATTXT.setText("");

                Itemcode();

            } catch (SQLException e) {

                System.out.println(e);

            }

        }   // TODO add your handling code here:
    }//GEN-LAST:event_AIADDActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void CVSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CVSearchActionPerformed
        serchResult(VCID1.getSelectedItem().toString());
    }//GEN-LAST:event_CVSearchActionPerformed

    private void ctablelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctablelMouseClicked
        int row = ctablel.getSelectedRow();

        VCID1.setSelectedItem(ctablel.getValueAt(row, 0).toString());
        VCNAME1.setText(ctablel.getValueAt(row, 1).toString());
        VCADD1.setText(ctablel.getValueAt(row, 2).toString());
        VCMOB.setText(ctablel.getValueAt(row, 3).toString());
       VCTEL3.setText(ctablel.getValueAt(row, 4).toString());
      VCTEL1.setText(ctablel.getValueAt(row, 5).toString());
       VCEMAIL1.setText(ctablel.getValueAt(row, 6).toString());
    }//GEN-LAST:event_ctablelMouseClicked

    private void VCDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VCDelActionPerformed
        int no = JOptionPane.showConfirmDialog(null, "Do you Want to Delete");

        if (no == 0) {

            client cli = new client();

            cli.clientId = VCID1.getSelectedItem().toString();

            try {
                String Sql = "DELETE FROM client  WHERE clientId = '" + cli.clientId + "' ";
                pst = conn.prepareStatement(Sql);
                pst.execute();
                tableload();

                VCID1.setSelectedItem("");
                VCNAME1.setText("");
                VCADD1.setText("");
                VCTEL1.setText("");
                VCEMAIL1.setText("");

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_VCDelActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        
        client it = new client();
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to add");

        if (x == 0) {

            it.clientId = VCID1.getSelectedItem().toString();
            it.name = VCNAME1.getText();
            it.address = VCADD1.getText();
            it.mobile = VCMOB.getText();
            it.telephone = VCTEL3.getText();
            it.fax = VCTEL1.getText();
            it.email = VCEMAIL1.getText();

            String sql1 = "UPDATE client SET  name = '" + it.name + "', address = '" + it.address + "',mobile ='" + it.mobile + "',telephone ='" + it.telephone + "',fax ='" + it.fax + "',email ='" + it.email + "' WHERE clientId = '" + it.clientId + "' ";
            try {
                pst = conn.prepareStatement(sql1);
                pst.execute();
                tableload();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            clearclient();
        
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

     public void clearclient() {

        ctablel.setEditingRow(0);
       VCID1.setSelectedItem("");
       VCNAME1.setText("");
       VCADD1.setText("");
       VCMOB.setText("");
       VCTEL3.setText("");
       VCTEL1.setText("");
       VCEMAIL1.setText("");
        // TODO add your handling code here:
    }             
    
    private void VTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VTSActionPerformed
        serchResulttech(VTID.getSelectedItem().toString());

        // TODO add your handling code here:
    }//GEN-LAST:event_VTSActionPerformed

    private void VTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VTDActionPerformed

        int no = JOptionPane.showConfirmDialog(null, "Do you Want to Delete");

        if (no == 0) {

            technicians tech = new technicians();

            tech.tech_id = VTID.getSelectedItem().toString();

            try {
                String Sql = "DELETE FROM technician  WHERE tech_id = '" + tech.tech_id + "' ";
                pst = conn.prepareStatement(Sql);
                pst.execute();
                tableloadjob();

                VTID.setSelectedItem("");
                VTTN.setText("");
                VTTA.setText("");
                VCTEL1.setText("");
                VTTTE.setText("");
                VTTE.setText("");

            } catch (Exception e) {
                System.out.println(e);
            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_VTDActionPerformed

    private void VTTABELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VTTABELMouseClicked

        int row = VTTABEL.getSelectedRow();

        ATTID.setSelectedItem(VTTABEL.getValueAt(row, 0).toString());
        VTTN.setText(VTTABEL.getValueAt(row, 1).toString());
        VTTA.setText(VTTABEL.getValueAt(row, 2).toString());
        VTTTE.setText(VTTABEL.getValueAt(row, 3).toString());
        VTTE.setText(VTTABEL.getValueAt(row, 4).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_VTTABELMouseClicked

    private void AJADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AJADDActionPerformed

        jobs job = new jobs();
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to add");

        if (x == 0) {

            job.job_id = ajjobidtxt.getText();
            job.client = ajclienttxt.getSelectedItem().toString();
            //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //job.due_date = sdf.format(ajduedatetxt.getDate());
            //java.sql.Date pd = java.sql.Date.valueOf(job.due_date);
            job.technician = ajtechniciantxt.getSelectedItem().toString();
            job.priority = ajprioritycom.getSelectedItem().toString();
            job.mobile = ajmobiletxt.getText();
            job.email = ajemailtxt.getText();
            job.item_id = JID.getSelectedItem().toString();
            job.service = JSCOM.getSelectedItem().toString();
            job.status = ajstatuscom.getSelectedItem().toString();

            if (validation.isValidEmailAddress(job.email)) {
                if (validation.isValidPhone(job.mobile)) {
                    try {
                        String q = "INSERT INTO job  VALUES ('" + job.job_id + "','" + job.client + "','" + job.technician + "','" + job.priority + "','" + job.mobile + "','"+job.email+"','" + job.item_id + "','" + job.service + "','" + job.status + "')";
                        pst = conn.prepareStatement(q);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Data Successfully Added");
                        ajjobidtxt.setText("");
                        ajclienttxt.setSelectedItem("");
                        //ajduedatetxt.setCalendar(null);
                        ajtechniciantxt.setSelectedItem("");
                        ajprioritycom.setSelectedItem("");
                        ajmobiletxt.setText("");
                        ajemailtxt.setText("");
                        JID.setSelectedItem("");
                        JSCOM.setSelectedItem("");
                        ajstatuscom.setSelectedItem("");

                        Jobcode();

                        pst.close();
                        String sql = "INSERT INTO job_dev (`job_id`, `client`, `technition`) VALUES ('"+job.job_id+"' , '"+job.client+"', '"+job.technician+"') ";
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        pst.close();
                        
                    } catch (SQLException e) {

                        System.out.println(e);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "wrong Phone Number  ");
                }
            } else {
                JOptionPane.showMessageDialog(null, "wrong Email Address ");
            }

        }
// TODO add your handling code here:
    }//GEN-LAST:event_AJADDActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
                  new Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void VJDELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VJDELETEActionPerformed

        int no = JOptionPane.showConfirmDialog(null, "Do you Want to Delete");

        if (no == 0) {

            jobs job = new jobs();

            job.job_id = VJJIDTXT.getSelectedItem().toString();

            try {
                String Sql = "DELETE FROM job  WHERE job_Id = '" + job.job_id + "' ";
                pst = conn.prepareStatement(Sql);
                pst.execute();
                tableloadjob();

            } catch (Exception e) {
                System.out.println(e);
            }

        }// TODO add your handling code here:
    }//GEN-LAST:event_VJDELETEActionPerformed

    private void VJSEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VJSEARCHActionPerformed
        serchResultjob(VJJIDTXT.getSelectedItem().toString());        // TODO add your handling code here:
    }//GEN-LAST:event_VJSEARCHActionPerformed

    private void JOBTABLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JOBTABLEMouseClicked

        int row = JOBTABLE.getSelectedRow();

        /*System.out.println(JOBTABLE.getValueAt(row, 0).toString());
        System.out.println(JOBTABLE.getValueAt(row, 1).toString());
        System.out.println(JOBTABLE.getValueAt(row, 2).toString());
        System.out.println(JOBTABLE.getValueAt(row, 3).toString());
        System.out.println(JOBTABLE.getValueAt(row, 4).toString());
         */
        VJJIDTXT.setSelectedItem(JOBTABLE.getValueAt(row, 0).toString());
        VJCLIENT.setText(JOBTABLE.getValueAt(row, 1).toString());
        VJTECH.setText(JOBTABLE.getValueAt(row, 2).toString());
        VJPRIO.setSelectedItem(JOBTABLE.getValueAt(row, 3).toString());
        VJSTAT.setSelectedItem(JOBTABLE.getValueAt(row, 4).toString());// TODO add your handling code here:
    }//GEN-LAST:event_JOBTABLEMouseClicked

    private void AIYESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AIYESActionPerformed
        CATAI.setVisible(true);
        AIAID.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_AIYESActionPerformed

    private void AINOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AINOActionPerformed
        CATAI.setVisible(false);
        AIAID.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_AINOActionPerformed

    private void VJCLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VJCLIENTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VJCLIENTActionPerformed

    private void AIUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AIUActionPerformed

        item it = new item();
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to add");

        if (x == 0) {

            it.item_code = VIID1.getSelectedItem().toString();
            it.type = VJPRIO.getSelectedItem().toString();
            it.client_name = VICLIENT.getText();
            it.technician = VITECH.getText();

            String sql1 = "UPDATE citem SET  type = '" + it.type + "', client = '" + it.client_name + "',technician ='" + it.technician + "'WHERE item_code = '" + it.item_code + "' ";
            try {
                pst = conn.prepareStatement(sql1);
                pst.execute();
                tableloaditem();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            clearitem();
        }
    }

    public void clearitem() {

        ITEMTABLE.setEditingRow(0);
        VIID1.setSelectedItem("");
        VJPRIO.setSelectedItem("");
        VICLIENT.setText("");
        VITECH.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_AIUActionPerformed

    private void VJUPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VJUPDATEActionPerformed

        jobs job1 = new jobs();
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to add");

        if (x == 0) {

            job1.job_id = VJJIDTXT.getSelectedItem().toString();
            job1.client = VJCLIENT.getText();
            job1.technician = VJTECH.getText();
            job1.priority = VJPRIO.getSelectedItem().toString();
            job1.status = VJSTAT.getSelectedItem().toString();

            String sql1 = "UPDATE job SET  client = '" + job1.client + "', technician = '" + job1.technician + "',priority ='" + job1.priority + "',status='" + job1.status + "'  WHERE job_id= '" + job1.job_id + "'";
            try {
                pst = conn.prepareStatement(sql1);
                pst.execute();
                tableloadjob();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            clearjob();
        }
    }

    public void clearjob() {

        VJJIDTXT.setSelectedItem("");
        VJCLIENT.setText("");
        VJTECH.setText("");
        VJPRIO.setSelectedItem("Item 1");
        VJSTAT.setSelectedItem("Item 1");
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_VJUPDATEActionPerformed

    private void VIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIDActionPerformed

        int no = JOptionPane.showConfirmDialog(null, "Do you Want to Delete");

        if (no == 0) {

            item it = new item();

            it.item_code = VIID1.getSelectedItem().toString();

            try {
                String Sql = "DELETE FROM citem  WHERE item_code = '" + it.item_code + "' ";
                pst = conn.prepareStatement(Sql);
                pst.execute();
                tableloaditem();

            } catch (Exception e) {
                System.out.println(e);
            }

        }// TODO add your handling code here:
    }//GEN-LAST:event_VIDActionPerformed

    private void ITEMTABLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ITEMTABLEMouseClicked

        int row = ITEMTABLE.getSelectedRow();

        VIID1.setSelectedItem(ITEMTABLE.getValueAt(row, 0).toString());
        VITYPE.setSelectedItem(ITEMTABLE.getValueAt(row, 2).toString());
        VICLIENT.setText(ITEMTABLE.getValueAt(row, 4).toString());
        VITECH.setText(ITEMTABLE.getValueAt(row, 5).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_ITEMTABLEMouseClicked

    private void VJSTATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VJSTATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VJSTATActionPerformed

    private void AGPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGPRActionPerformed
        printReport();        // TODO add your handling code here:
    }//GEN-LAST:event_AGPRActionPerformed

    private void AGVIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGVIActionPerformed
        try {
            JasperReport r = JasperCompileManager.compileReport("C:\\Users\\Tharushi\\Documents\\NetBeansProjects\\8Ware\\src\\Services\\agreement.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(r, null, DBAccess.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_AGVIActionPerformed

    private void ATDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATDActionPerformed
        ATTNAME.setText("NAME");
        ATTADD.setText("ADDRESS");
        ATTTEL.setText("0111111111");
        ATTEMAIL.setText("mail@gmail.com");
// TODO add your handling code here:
    }//GEN-LAST:event_ATDActionPerformed

    private void AIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AIDActionPerformed
        ATMOD.setText("MODEL");
        AIID.setText("DESCRIPTION");

        CATAREA.setText("CLIENT INFO");
        TATTXT.setText("TECHNICIAN NAME");        // TODO add your handling code here:
    }//GEN-LAST:event_AIDActionPerformed

    private void AJDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AJDEActionPerformed
         ajmobiletxt.setText("0111111111");
         ajemailtxt.setText("mail@gmail.com");// TODO add your handling code here:
    }//GEN-LAST:event_AJDEActionPerformed

    private void ajstatuscomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajstatuscomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajstatuscomActionPerformed

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
            java.util.logging.Logger.getLogger(ServiceManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServiceManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServiceManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServiceManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ServiceManagement().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ServiceManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AGPR;
    private javax.swing.JTextField AGTXT;
    private javax.swing.JButton AGVI;
    private javax.swing.JButton AIADD;
    private javax.swing.JLabel AIAID;
    private javax.swing.JButton AIC;
    private javax.swing.JButton AID;
    private javax.swing.JLabel AIDATE;
    private javax.swing.JTextField AIIC;
    private javax.swing.JTextArea AIID;
    private javax.swing.JButton AINO;
    private javax.swing.JLabel AITXT;
    private javax.swing.JComboBox AITYP;
    private javax.swing.JButton AIU;
    private javax.swing.JButton AIYES;
    private javax.swing.JButton AJADD;
    private javax.swing.JButton AJCANCEL;
    private javax.swing.JButton AJDE;
    private javax.swing.JButton AJITEM;
    private javax.swing.JLabel AJSER;
    private javax.swing.JButton AJSERVICE;
    private javax.swing.JLabel ATADD;
    private javax.swing.JLabel ATADD1;
    private javax.swing.JButton ATCancel;
    private javax.swing.JButton ATD;
    private javax.swing.JLabel ATEM;
    private javax.swing.JLabel ATEM1;
    private javax.swing.JLabel ATID;
    private javax.swing.JTextField ATMOD;
    private javax.swing.JLabel ATNA;
    private javax.swing.JLabel ATNA1;
    private javax.swing.JTextField ATTADD;
    private javax.swing.JLabel ATTEL;
    private javax.swing.JLabel ATTEL1;
    private javax.swing.JTextField ATTEMAIL;
    private javax.swing.JComboBox<String> ATTID;
    private javax.swing.JTextField ATTNAME;
    private javax.swing.JTextField ATTTEL;
    private javax.swing.JButton ATadd;
    private javax.swing.JComboBox<String> CATAI;
    private javax.swing.JTextArea CATAREA;
    private javax.swing.JButton CVSearch;
    private javax.swing.JComboBox<String> ITATID;
    private javax.swing.JTable ITEMTABLE;
    private javax.swing.JComboBox<String> JID;
    private javax.swing.JTable JOBTABLE;
    private javax.swing.JComboBox JSCOM;
    private javax.swing.JTextField TATTXT;
    private javax.swing.JTable VAGRTABLE;
    private javax.swing.JLabel VCADD;
    private javax.swing.JTextField VCADD1;
    private javax.swing.JButton VCDel;
    private javax.swing.JLabel VCEMAIL;
    private javax.swing.JTextField VCEMAIL1;
    private javax.swing.JLabel VCEMAIL2;
    private javax.swing.JLabel VCID;
    private javax.swing.JComboBox<String> VCID1;
    private javax.swing.JTextField VCMOB;
    private javax.swing.JLabel VCNAME;
    private javax.swing.JTextField VCNAME1;
    private javax.swing.JLabel VCTEL;
    private javax.swing.JTextField VCTEL1;
    private javax.swing.JLabel VCTEL2;
    private javax.swing.JTextField VCTEL3;
    private javax.swing.JButton VIC;
    private javax.swing.JTextField VICLIENT;
    private javax.swing.JButton VID;
    private javax.swing.JLabel VIID;
    private javax.swing.JComboBox<String> VIID1;
    private javax.swing.JButton VIS;
    private javax.swing.JTextField VITECH;
    private javax.swing.JComboBox VITYPE;
    private javax.swing.JTextField VJCLIENT;
    private javax.swing.JButton VJDELETE;
    private javax.swing.JLabel VJJIDL;
    private javax.swing.JComboBox<String> VJJIDTXT;
    private javax.swing.JComboBox VJPRIO;
    private javax.swing.JButton VJSEARCH;
    private javax.swing.JComboBox VJSTAT;
    private javax.swing.JTextField VJTECH;
    private javax.swing.JButton VJUPDATE;
    private javax.swing.JButton VTC;
    private javax.swing.JButton VTD;
    private javax.swing.JComboBox<String> VTID;
    private javax.swing.JButton VTS;
    private javax.swing.JTextField VTTA;
    private javax.swing.JTable VTTABEL;
    private javax.swing.JTextField VTTE;
    private javax.swing.JTextField VTTN;
    private javax.swing.JTextField VTTTE;
    private javax.swing.JButton VTUPDATE;
    private javax.swing.JComboBox<String> ajclienttxt;
    private javax.swing.JTextField ajemailtxt;
    private javax.swing.JLabel ajjobid;
    private javax.swing.JTextField ajjobidtxt;
    private javax.swing.JTextField ajmobiletxt;
    private javax.swing.JComboBox ajprioritycom;
    private javax.swing.JComboBox ajstatuscom;
    private javax.swing.JComboBox<String> ajtechniciantxt;
    private javax.swing.JTable ctablel;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    // End of variables declaration//GEN-END:variables

    //private void serchResult(String text) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
