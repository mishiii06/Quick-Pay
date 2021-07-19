import java.awt.CardLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import one.connect.ConnectionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.quick.Company;
import org.quick.ElectricityBill;
import org.quick.GasBill;
import org.quick.Product;
import org.quick.ShopBill;
import org.quick.TelephoneBill;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class Home extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form Home
     */
    CardLayout cl;
    String array[]={""};
    int q,w,e,r,esum,ssum,gsum,tsum,t,m;
    DefaultTableModel dtm1;
    public Home() 
    {
        String comName="", regNo="", gstNo="";
        String str1="";
        initComponents();
        SessionFactory sf=ConnectionFactory.emergencyConnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        Criteria ct=session.createCriteria(Company.class);
        ct.add(Restrictions.eq("companyId", Login.companyId));
        List<Company> list=ct.list();
        for(Company com: list)
        {
            String str=com.getBillType();
            array=str.split(",", 10);
            comName=com.getCompanyName();
            regNo=com.getRegistrationId();
            gstNo=com.getGstNo();
        }
        for(int i=0;i<array.length;i++)
        {
            jComboBox1.addItem(array[i]);
            jComboBox4.addItem(array[i]);
            jComboBox7.addItem(array[i]);
            if(array[i].equals("Shop Bill"))
            {
                str1=array[i];
            }
        }
        if(str1.equals("Shop Bill"))
        {
            jComboBox3.setVisible(true);
            jLabel18.setVisible(true);
            jLabel21.setVisible(true);
            jLabel24.setVisible(true);
            jLabel22.setVisible(true);
        }
        else
        {
            jComboBox3.setVisible(false);
            jLabel24.setVisible(false);
            jLabel22.setVisible(false);
        }
        cl=(CardLayout)jPanel1.getLayout();
        cl.show(jPanel1, "dash");
        jLabel15.setText(comName);
        jLabel16.setText(regNo);
        jLabel14.setText(gstNo);
        
        for(int i=0;i<array.length;i++)
        {
            if(array[i].equals("Electricity Bill"))
            {
                Criteria crit=session.createCriteria(ElectricityBill.class);
                crit.add(Restrictions.eq("companyId",Login.companyId));
                List<ElectricityBill> list1=crit.list();
                for(ElectricityBill eb: list1)
                {
                    esum=esum+Integer.parseInt(eb.getTotal());
                    q++;
                }
            }
            if(array[i].equals("Shop Bill"))
            {
                Criteria crit=session.createCriteria(ShopBill.class);
                crit.add(Restrictions.eq("companyId",Login.companyId));
                List<ShopBill> list1=crit.list();
                for(ShopBill sb: list1)
                {
                    ssum=ssum+Integer.parseInt(sb.getTotal());
                    w++;
                }
            }
            if(array[i].equals("Gas Bill"))
            {
                Criteria crit=session.createCriteria(GasBill.class);
                crit.add(Restrictions.eq("companyId",Login.companyId));
                List<GasBill> list1=crit.list();
                for(GasBill gb: list1)
                {
                    gsum=gsum+Integer.parseInt(gb.getTotal());
                    e++;
                }
            }
            if(array[i].equals("Telephone Bill"))
            {
                Criteria crit=session.createCriteria(TelephoneBill.class);
                crit.add(Restrictions.eq("companyId",Login.companyId));
                List<TelephoneBill> list1=crit.list();
                for(TelephoneBill tb: list1)
                {
                    tsum=tsum+Integer.parseInt(tb.getTotal());
                    r++;
                }
            }
        }
        esum=esum+ssum+gsum+tsum;
        Criteria crit=session.createCriteria(Product.class);
        List<Product> list1=crit.list();
        for(Product pro: list1)
        {
            t++;
        }
        jLabel17.setText(Integer.toString(q+w+e+r));
        jLabel18.setText(Integer.toString(q+w+e+r));
        jLabel19.setText(Integer.toString(esum));
        jLabel22.setText(Integer.toString(t));
        Thread th=new Thread(this);
        th.start();
        
    }
    @Override
    public void run()
    {
        for(int i=0;i<array.length;i++)
        {
            if(array[i].equals("Electricity Bill"))
            {
                jLabel21.setText("Total Number of Electricity Bills:");
                jLabel18.setText(Integer.toString(q));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(array[i].equals("Shop Bill"))
            {
                jLabel21.setText("Total Number of Shop Bills:");
                jLabel18.setText(Integer.toString(w));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(array[i].equals("Gas Bill"))
            {
                jLabel21.setText("Total Number of Gas Bills:");
                jLabel18.setText(Integer.toString(e));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(array[i].equals("Telephone Bill"))
            {
                jLabel21.setText("Total Number of Telephone Bills:");
                jLabel18.setText(Integer.toString(r));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        run();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel142 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jTextField28 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel150 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel151 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel152 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jComboBox7 = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel156 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton13 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel2.setText("Quick Pay");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickPay.gif"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 80));

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NPL Infotech");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, 170, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contact No.: 9346268080");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 180, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Email: npl.info@gmail.com");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 70, 180, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dash.gif"))); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 160, 170));

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Created and Designed by:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 180, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("View Invoice");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 160, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 680, 160, -1));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Settings", "Change Password", "Update Profile", "Delete Bill" }));
        jComboBox2.setToolTipText("");
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, 160, 30));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Products", "Add Product", "Delete Product", "Update Product", "View Products" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 180, 30));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Generate Bill" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 160, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 160, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 100, 1200, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 15)); // NOI18N
        jLabel9.setText("Payments Made Easy");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 150, -1));

        jLabel10.setBackground(new java.awt.Color(0, 51, 204));
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jLabel10.setOpaque(true);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 300, 690));

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("GST Number:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Company Name:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Registration Number:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Total Number of Bills:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Total Amount Received:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Total Products ");

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setText("Dashboard");
        jLabel103.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(159, 159, 159))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel103)
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel1.add(jPanel2, "dash");

        jPanel3.setMinimumSize(new java.awt.Dimension(900, 444));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("View Invoice");
        jLabel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 58, 228, 42));

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Invoice Type" }));
        jPanel3.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 189, 186, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Enter Bill Number");
        jLabel27.setPreferredSize(new java.awt.Dimension(170, 30));
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 303, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 303, 170, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("View");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 413, -1, -1));

        jPanel1.add(jPanel3, "invoice");

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(900, 690));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel28.setText("Invoice");
        jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 33, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Date");
        jLabel29.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 121, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Bill Number");
        jLabel30.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 164, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Customer Name");
        jLabel31.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 250, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("Company ID");
        jLabel32.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 207, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Contact Number");
        jLabel33.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 293, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("jLabel29");
        jLabel34.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Phases");
        jLabel35.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("IVRS No.");
        jLabel36.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("jLabel29");
        jLabel37.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("Address");
        jLabel38.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Due Date");
        jLabel39.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("jLabel29");
        jLabel40.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, -1, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("jLabel29");
        jLabel41.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setText("Print");
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 546, 90, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setText("Meter No.");
        jLabel42.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("Units Consumed");
        jLabel43.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 379, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("Amount Per Unit");
        jLabel44.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 422, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setText("Sub Total");
        jLabel45.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 465, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("jLabel29");
        jLabel46.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 379, -1, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("jLabel29");
        jLabel47.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("jLabel29");
        jLabel48.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 422, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setText("jLabel29");
        jLabel49.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 465, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setText("jLabel29");
        jLabel50.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setText("jLabel29");
        jLabel51.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setText("jLabel29");
        jLabel52.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, -1, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel53.setText("jLabel29");
        jLabel53.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setText("jLabel29");
        jLabel54.setPreferredSize(new java.awt.Dimension(175, 25));
        jPanel4.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 379, 357, 25));

        jLabel56.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 422, 357, 25));

        jLabel57.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 465, 357, 25));

        jLabel58.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 508, 357, 25));

        jPanel1.add(jPanel4, "einvoice");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Quantity", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel142.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel142.setText("Invoice");
        jLabel142.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel117.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel117.setText("jLabel117");

        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel118.setText("jLabel117");

        jLabel119.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel119.setText("jLabel117");

        jLabel122.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel122.setText("jLabel117");

        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel124.setText("Helpline");

        jLabel125.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel125.setText("jLabel117");

        jLabel128.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel128.setText("jLabel117");

        jLabel135.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel135.setText("jLabel117");

        jLabel136.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel136.setText("jLabel117");

        jLabel137.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel137.setText("jLabel117");

        jLabel138.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel138.setText("jLabel117");

        jLabel139.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel139.setText("jLabel117");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(158, 158, 158)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(72, 72, 72))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(jLabel142)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel142)
                .addGap(52, 52, 52)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, "sinvoice");

        jPanel6.setOpaque(false);

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel75.setText("Invoice");
        jLabel75.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel76.setText("jLabel76");

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel77.setText("jLabel76");

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel78.setText("jLabel76");

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel79.setText("jLabel76");

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel80.setText("jLabel76");

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel81.setText("jLabel76");

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel82.setText("jLabel76");

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel83.setText("jLabel76");

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel84.setText("jLabel76");

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel85.setText("jLabel76");

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("jLabel76");

        jLabel87.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("jLabel76");

        jLabel88.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("jLabel76");

        jLabel89.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("jLabel76");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(jLabel75))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(130, 130, 130)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel75)
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel6, "tinvoice");

        jPanel7.setOpaque(false);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel59.setText("Invoice");
        jLabel59.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setText("jLabel60");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel61.setText("jLabel60");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setText("jLabel60");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setText("jLabel60");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel64.setText("jLabel60");

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("jLabel60");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel66.setText("jLabel60");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel67.setText("jLabel60");

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel68.setText("jLabel60");

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel69.setText("jLabel60");

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel70.setText("jLabel60");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("jLabel60");

        jLabel72.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("jLabel60");

        jLabel73.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("jLabel60");

        jLabel74.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("jLabel60");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(384, 384, 384)
                        .addComponent(jLabel59))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );

        jPanel1.add(jPanel7, "ginvoice");

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel90.setText("Electricity Bill");
        jLabel90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel91.setText("Customer Name: ");
        jPanel8.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 200, 25));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel93.setText("Contact Number:");
        jPanel8.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 200, 25));

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel94.setText("Address: ");
        jPanel8.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 200, 25));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel95.setText("IVRS Number: ");
        jPanel8.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 200, 25));

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel96.setText("Meter Number:");
        jPanel8.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 200, 25));

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel97.setText("Phases:");
        jPanel8.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 200, 25));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel98.setText("Units Consumed: ");
        jPanel8.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 200, 25));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel99.setText("Bill Date:");
        jPanel8.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 200, 25));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 200, 25));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 200, 25));

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 200, 25));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 200, 25));

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 200, 25));

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, 200, 25));

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, 200, 25));

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jPanel8.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 175, 175));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("Generate");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 530, -1, -1));

        jTextField28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, 200, 25));

        jPanel1.add(jPanel8, "ebill");

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel100.setText("Shop Bill");
        jLabel100.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, -1));

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel101.setText("Customer Name: ");
        jPanel9.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 200, 25));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel102.setText("Contact Number:");
        jPanel9.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 200, 25));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel104.setText("Product ID:");
        jPanel9.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 200, 25));

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel105.setText("Product Quantity:");
        jPanel9.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 200, 25));

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel106.setText("Discount(in %):");
        jPanel9.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 200, 25));

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel107.setText("Warranty:");
        jPanel9.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 200, 25));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel108.setText("Bill Date:");
        jPanel9.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 200, 25));

        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jPanel9.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 175, 175));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 200, 30));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 200, 30));

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 200, 30));

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 200, 30));

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 200, 30));

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 200, 30));

        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, 200, 30));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setText("Generate");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 520, -1, -1));

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel110.setText("Tax: ");
        jPanel9.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 200, 25));

        jTextField18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 200, 30));

        jPanel1.add(jPanel9, "sbill");

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton8.setText("Generate");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, -1, -1));

        jTextField22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 200, 25));

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, 200, 25));

        jTextField24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 200, 25));

        jTextField25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 200, 25));

        jTextField26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 200, 25));

        jTextField27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 200, 25));

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel111.setText("Customer Name: ");
        jPanel10.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 25));

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel112.setText("Contact Number:");
        jPanel10.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 200, 25));

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel113.setText("Address: ");
        jPanel10.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 200, 25));

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel114.setText("AGL Number:");
        jPanel10.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 200, 25));

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel115.setText("Units Comsumed:");
        jPanel10.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 200, 25));

        jLabel116.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel116.setText("Bill Date:");
        jPanel10.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 200, 25));

        jLabel120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jPanel10.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 175, 175));

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel121.setText("Gas Bill");
        jLabel121.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        jPanel1.add(jPanel10, "gbill");

        jPanel11.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanel11.setOpaque(false);
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setOpaque(false);
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton10.setText("Generate");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 550, -1, -1));

        jTextField32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });
        jPanel18.add(jTextField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 200, 25));

        jTextField33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel18.add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, 200, 25));

        jTextField34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel18.add(jTextField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 200, 25));

        jTextField35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel18.add(jTextField35, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 200, 25));

        jTextField36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField36ActionPerformed(evt);
            }
        });
        jPanel18.add(jTextField36, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 200, 25));

        jTextField37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField37ActionPerformed(evt);
            }
        });
        jPanel18.add(jTextField37, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 200, 25));

        jLabel126.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel126.setText("Customer Name: ");
        jPanel18.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 25));

        jLabel127.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel127.setText("Contact Number:");
        jPanel18.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 200, 25));

        jLabel129.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel129.setText("Address: ");
        jPanel18.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 200, 25));

        jLabel130.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel130.setText("Telephone ID:");
        jPanel18.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 200, 25));

        jLabel131.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel131.setText("Plan:");
        jPanel18.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 200, 25));

        jLabel132.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel132.setText("Bill Date:");
        jPanel18.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, 200, 25));

        jLabel133.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jPanel18.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 175, 175));

        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel134.setText("Telephone Bill");
        jLabel134.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        jLabel140.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel140.setText("Plan Duration(in months):");
        jPanel18.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 220, 25));

        jTextField38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField38ActionPerformed(evt);
            }
        });
        jPanel18.add(jTextField38, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 200, 25));

        jPanel11.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 690));

        jPanel1.add(jPanel11, "tbill");

        jPanel12.setOpaque(false);

        jLabel123.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel123.setText("Add Product");
        jLabel123.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel141.setText("Enter Product ID:");

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel143.setText("Enter Product Name:");

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel144.setText("Enter Product Price:");

        jLabel145.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel145.setText("Enter Product Quantity:");

        jLabel146.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel146.setText("Enter Product Warranty:");

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton9.setText("Add");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(238, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(347, 347, 347))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(408, 408, 408))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel123)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jButton9)
                .addGap(155, 155, 155))
        );

        jPanel1.add(jPanel12, "addpro");

        jPanel13.setOpaque(false);

        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel147.setText("Delete Product");
        jLabel147.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel149.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel149.setText("Enter Product ID:");

        jTextField30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton11.setText("Delete");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 228, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(329, 329, 329))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addGap(403, 403, 403))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel147)
                .addGap(119, 119, 119)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102)
                .addComponent(jButton11)
                .addContainerGap(301, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel13, "deletepro");

        jPanel14.setOpaque(false);

        jLabel150.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel150.setText("Update Product");
        jLabel150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Choice to Update", "Product Name", "Product Price", "Product Quantity", "Product Warranty" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel151.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel151.setText("Enter Product ID:");

        jTextField31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel152.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jTextField39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton12.setText("Update");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 239, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(337, 337, 337))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addGap(396, 396, 396))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel150)
                .addGap(93, 93, 93)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addComponent(jButton12)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel14, "updatepro");

        jPanel15.setOpaque(false);

        jLabel148.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel148.setText("Product List");

        jTable2.setBackground(new java.awt.Color(102, 204, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Product Price", "Product Quantity", "Product Quantity"
            }
        ));
        jTable2.setOpaque(false);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addComponent(jLabel148)
                .addContainerGap(368, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel148)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel15, "viewpro");

        jLabel153.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("Delete Bill");
        jLabel153.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel154.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel154.setText("Enter Bill Number:");

        jTextField40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton15.setText("Delete");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jComboBox7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Bill Type" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(362, 362, 362)
                .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 255, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jButton15)
                                .addGap(70, 70, 70)))
                        .addGap(334, 334, 334))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(227, 227, 227))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel153)
                .addGap(77, 77, 77)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addComponent(jButton15)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel16, "deletebill");

        jLabel155.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel155.setText("Update Profile");
        jLabel155.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Choice to Update", "Company Name", "Registration ID", "Contact Number", "Address", "GST Number", "Bill Type" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jLabel156.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel156.setText("jLabel156");

        jTextField41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField41ActionPerformed(evt);
            }
        });

        jLabel157.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel157.setText("Select Bill Type to be Updated: ");

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton1.setText("Electricity Bill");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton2.setText("Shop Bill");

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton3.setText("Telephone Bill");

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton4.setText("Gas Bill");

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton13.setText("Update");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton2)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addContainerGap(209, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox6, 0, 231, Short.MAX_VALUE))
                        .addGap(327, 327, 327))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addGap(400, 400, 400))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel155)
                .addGap(88, 88, 88)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3)
                .addGap(56, 56, 56)
                .addComponent(jButton13)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel17, "updateprofile");

        jLabel158.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel158.setText("Change Password");
        jLabel158.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel159.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel159.setText("Enter Old Password:");

        jLabel160.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel160.setText("Enter New Password:");

        jLabel161.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel161.setText("Confirm New Password:");

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton14.setText("Change");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addGap(405, 405, 405))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel158)
                .addGap(85, 85, 85)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jButton14)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel19, "change");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 900, 690));

        jLabel1.setBackground(new java.awt.Color(102, 204, 255));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cl.show(jPanel1,"invoice");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String choice=(String)jComboBox2.getSelectedItem();
        if(choice.equals("Change Password"))
        {
            cl.show(jPanel1, "change");
        }
        if(choice.equals("Update Profile"))
        {
            cl.show(jPanel1, "updateprofile");
            jTextField41.setVisible(false);
            jLabel156.setVisible(false);
            jLabel157.setVisible(false);
            jRadioButton1.setVisible(false);
            jRadioButton2.setVisible(false);
            jRadioButton3.setVisible(false);
            jRadioButton4.setVisible(false);
        }
        if(choice.equals("Update Bill"))
        {
            
        }
        if(choice.equals("Delete Bill"))
        {
            cl.show(jPanel1, "deletebill");
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cl.show(jPanel1, "dash");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SessionFactory sf=ConnectionFactory.emergencyConnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        Criteria crit=session.createCriteria(Company.class);
        crit.add(Restrictions.eq("companyId", Login.companyId));
        List<Company> list1=crit.list();
        String address="", contact="";
        for(Company com: list1)
        {
            address=com.getAddress();
            contact=com.getContactNo();
        }
        String str=(String)jComboBox4.getSelectedItem();
        int billNo=Integer.parseInt(jTextField1.getText());
        if(str.equals("Electricity Bill"))
        {
            Criteria crit1=session.createCriteria(ElectricityBill.class);
            crit1.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", billNo)));
            List<ElectricityBill> list=crit1.list();
            if(list.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Invoice Not Found");
            }
            else
            {
                for(ElectricityBill eb: list)
                {
                    String date=eb.getDate();
                    String contactNo=eb.getContactNo();
                    String cust=eb.getCustomerName();
                    String due=eb.getDueDate();
                    String ivrs=eb.getIvrsNo();
                    String add=eb.getAddress();
                    String meter=eb.getMeterNo();
                    String phases=eb.getPhases();
                    String ppu=eb.getPricePerUnit();
                    String units=eb.getUnitConsumed();
                    String total=eb.getTotal();
                    jLabel54.setText(date);
                    jLabel53.setText(Integer.toString(billNo));
                    jLabel50.setText(Login.companyId);
                    jLabel41.setText(cust);
                    jLabel40.setText(contactNo);
                    jLabel34.setText(due);
                    jLabel37.setText(add);
                    jLabel51.setText(ivrs);
                    jLabel52.setText(meter);
                    jLabel47.setText(phases);
                    jLabel46.setText(units);
                    jLabel48.setText("Price Per Unit: Rs. "+ppu+"/-");
                    jLabel49.setText("Sub Total: Rs. "+total+"/-");
                    jLabel55.setText("Helpline");
                    jLabel56.setText("Contact Number: "+contact);
                    jLabel57.setText("Email: "+Login.companyId+"@gmail.com");
                    jLabel58.setText("Address: "+address);
                }
                cl.show(jPanel1, "einvoice");
            }
        }

        if(str.equals("Shop Bill"))
        {
            Criteria crit1=session.createCriteria(ShopBill.class);
            crit1.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", billNo)));
            List<ElectricityBill> list=crit1.list();
            if(list.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Invoice Not Found");
            }
            cl.show(jPanel1, "sinvoice");
        }
        if(str.equals("Gas Bill"))
        {
            Criteria crit1=session.createCriteria(GasBill.class);
            crit1.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", billNo)));
            List<GasBill> list=crit1.list();
            if(list.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Invoice Not Found");
            }
            else
            {
                for(GasBill gb: list)
                {
                    String date=gb.getDate();
                    String cust=gb.getCustomerName();
                    String add=gb.getAddress();
                    String agl=gb.getAglNo();
                    String contactNo=gb.getContactNo();
                    String due=gb.getDueDate();
                    String units=gb.getUnitConsumed();
                    String ppu=gb.getPricePerUnit();
                    String total=gb.getTotal();
                    jLabel60.setText("Date: "+date);
                    jLabel61.setText("Bill No.: "+Integer.toString(billNo));
                    jLabel62.setText("Company ID: "+Login.companyId);
                    jLabel63.setText("Customer Name: "+cust);
                    jLabel64.setText("Contact No.: "+contactNo);
                    jLabel65.setText("Due Date: "+due);
                    jLabel66.setText("Address: "+add);
                    jLabel67.setText("AGL No.: "+agl);
                    jLabel68.setText("Units Consumed: "+units);
                    jLabel69.setText("Price per unit: Rs "+ppu+"/-");
                    jLabel70.setText("Price per unit: Rs "+total+"/-");
                    jLabel71.setText("Helpline");
                    jLabel72.setText("Contact Number: "+contact);
                    jLabel73.setText("Email: "+Login.companyId+"@gmail.com");
                    jLabel74.setText("Address: "+address);  
                }
                    cl.show(jPanel1, "ginvoice");
            }
        }
        if(str.equals("Telephone Bill"))
        {
            Criteria crit1=session.createCriteria(TelephoneBill.class);
            crit1.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", billNo)));
            List<TelephoneBill> list=crit1.list();
            if(list.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Invoice Not Found");
            }
            else
            {
                for(TelephoneBill tb: list)
                {
                    String date=tb.getDate();
                    String cust=tb.getCustomerName();
                    String add=tb.getAddress();
                    String tele=tb.getTelephoneId();
                    String contactNo=tb.getContactNo();
                    String due=tb.getDueDate();
                    String plan=tb.getPlan();
                    String total=tb.getTotal();
                    jLabel76.setText("Date: "+date);
                    jLabel77.setText("Bill No.: "+Integer.toString(billNo));
                    jLabel78.setText("Company ID: "+Login.companyId);
                    jLabel79.setText("Customer Name: "+cust);
                    jLabel80.setText("Due Date: "+due);
                    jLabel81.setText("Contact No.: "+contactNo);
                    jLabel82.setText("Telephone ID: "+tele);
                    jLabel83.setText("Address: "+add);
                    jLabel84.setText("Plan: Rs "+plan+"/-");
                    jLabel85.setText("Sub Total: Rs "+total+"/-");
                    jLabel86.setText("Helpline");
                    jLabel87.setText("Contact Number: "+contact);
                    jLabel88.setText("Email: "+Login.companyId+"@gmail.com");
                    jLabel89.setText("Address: "+address);
                }
                    cl.show(jPanel1, "tinvoice");
            }
        }
        if(str.equals("Invoice Type"))
        {
            JOptionPane.showMessageDialog(this, "Please Select an Invoice Type");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String str=(String)jComboBox1.getSelectedItem();
        if(str.equals("Electricity Bill"))
        {
            cl.show(jPanel1, "ebill");
        }
        if(str.equals("Shop Bill"))
        {
            cl.show(jPanel1, "sbill");
        }
        if(str.equals("Gas Bill"))
        {
            cl.show(jPanel1, "gbill");
        }
        if(str.equals("Telephone Bill"))
        {
            cl.show(jPanel1, "tbill");
        }
        if(str.equals("Generate Bill"))
        {
            JOptionPane.showMessageDialog(this, "Please Select Bill Generating type");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jTextField36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField36ActionPerformed

    private void jTextField37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField37ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int serial=0;
        String cust=jTextField2.getText();
        String contact=jTextField3.getText();
        String address=jTextField4.getText();
        String ivrs=jTextField6.getText();
        String meter=jTextField7.getText();
        String phases=jTextField8.getText();
        String units=jTextField28.getText();
        String date=jTextField9.getText();
        
        SessionFactory sf=ConnectionFactory.emergencyConnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        int total=60+(5*Integer.parseInt(units));
        ElectricityBill e=new ElectricityBill(Login.companyId, cust, contact, address, ivrs, date, "7 Days from Bill Date", meter, phases, units, "5", Integer.toBinaryString(total), "Paid");
        session.save(e);
        tx.commit();
        
        Criteria ct=session.createCriteria(ElectricityBill.class);
        ct.add(Restrictions.and(Restrictions.eq("ivrsNo", ivrs),Restrictions.eq("date", date)));
        List<ElectricityBill> list=ct.list();
        for(ElectricityBill eb1: list)
        {
            serial=eb1.getSerialNo();
        }
        Criteria crit=session.createCriteria(Company.class);
        crit.add(Restrictions.eq("companyId", Login.companyId));
        List<Company> list1=crit.list();
        String address1="", contact1="";
        for(Company com: list1)
        {
            address1=com.getAddress();
            contact1=com.getContactNo();
        }
                    jLabel54.setText(date);
                    jLabel53.setText(Integer.toString(serial));
                    jLabel50.setText(Login.companyId);
                    jLabel41.setText(cust);
                    jLabel40.setText(contact);
                    jLabel34.setText("7 Days from Bill Date");
                    jLabel37.setText(address);
                    jLabel51.setText(ivrs);
                    jLabel52.setText(meter);
                    jLabel47.setText(phases);
                    jLabel46.setText(units);
                    jLabel48.setText("Price Per Unit: Rs. 5/-");
                    jLabel49.setText("Sub Total: Rs. "+total+"/-");
                    jLabel55.setText("Helpline");
                    jLabel56.setText("Contact Number: "+contact1);
                    jLabel57.setText("Email: "+Login.companyId+"@gmail.com");
                    jLabel58.setText("Address: "+address1);
                cl.show(jPanel1, "einvoice");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int total=0,tax1=0,dis=0;
        DefaultTableModel dtm;
        String cust=jTextField10.getText();
        String contact=jTextField11.getText();
        String id=jTextField13.getText();
        String quantity=jTextField14.getText();
        String discount=jTextField15.getText();
        String warranty=jTextField16.getText();
        String tax=jTextField18.getText();
        String date=jTextField17.getText();
        String[] array1=id.split(",", 10);
        String[] array2=quantity.split(",", 10);
        
        SessionFactory sf=ConnectionFactory.emergencyConnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        for(int i=0;i<array1.length;i++)
        {
          Criteria ct=session.createCriteria(Product.class);
          ct.add(Restrictions.and(Restrictions.eq("productId", array1[i]),Restrictions.eq("companyId", Login.companyId)));
          List<Product> list=ct.list();
          for(Product pro: list)
          {
              total=total+(Integer.parseInt(array2[i])*Integer.parseInt(pro.getProductPrice()));
          }
        }
          tax1=(total*Integer.parseInt(tax))/100;
          dis=(total*Integer.parseInt(discount))/100;
          total=total+tax1-dis;
        ShopBill sb=new ShopBill(Login.companyId, cust, contact, date, id, quantity, Integer.toString(dis), warranty, Integer.toString(tax1), Integer.toString(total), "Paid");
        session.save(sb);
        tx.commit();
        int billNo=0;
        Criteria ct1=session.createCriteria(ShopBill.class);
        ct1.add(Restrictions.and(Restrictions.eq("date", date), Restrictions.eq("productName", id)));
        List<ShopBill> list=ct1.list();
        for(ShopBill sb1: list)
        {
            billNo=sb1.getSerialNo();
        }
        Criteria crit=session.createCriteria(Company.class);
        crit.add(Restrictions.eq("companyId", Login.companyId));
        List<Company> list1=crit.list();
        String address1="", contact1="";
        for(Company com: list1)
        {
            address1=com.getAddress();
            contact1=com.getContactNo();
        }
            String name="",price="",total1="";
            for(int i=0;i<array1.length;i++)
            {
                Criteria ct=session.createCriteria(Product.class);
                ct.add(Restrictions.and(Restrictions.eq("productId", array1[i]), Restrictions.eq("companyId", Login.companyId)));
                List<Product> list2=ct.list();
                for(Product pro: list2)
                {
                    name=name+pro.getProductName()+",";
                    price=price+pro.getProductPrice()+",";
                    total1=total1+(Integer.parseInt(array2[i])*Integer.parseInt(pro.getProductPrice()))+",";
                }
            }
            quantity=quantity+",";
            dtm=(DefaultTableModel)jTable1.getModel();
            String array3[]=name.split(",",10);
            String array4[]=price.split(",",10);
            String array5[]=total1.split(",",10);
            String array6[]=quantity.split(",",10);
            for(int i=0;i<array3.length;i++)
            {
                Object obj[]={array3[i],array6[i],array4[i],array5[i]};
                dtm.addRow(obj);
            }
                    jLabel117.setText("Date: "+date);
                    jLabel118.setText("Bill Number: "+Integer.toString(billNo));
                    jLabel119.setText("Company ID: "+Login.companyId);
                    jLabel41.setText("Customer Name: "+cust);
                    jLabel122.setText("Contact Number: "+contact);
                    jLabel136.setText("Warranty: "+warranty);
                    jLabel137.setText("Tax: "+tax);
                    jLabel138.setText("Discount: "+Integer.toString(dis));
                    jLabel139.setText("Total: "+Integer.toString(total));
                    jLabel124.setText("Helpline");
                    jLabel125.setText("Contact Number: "+contact1);
                    jLabel128.setText("Email: "+Login.companyId+"@gmail.com");
                    jLabel135.setText("Address: "+address1);
                    cl.show(jPanel1, "sinvoice");
                   
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int billNo=0;
        String cust=jTextField27.getText();
        String contact=jTextField26.getText();
        String address=jTextField25.getText();
        String agl=jTextField24.getText();
        String units=jTextField23.getText();
        String date=jTextField22.getText();
        
        SessionFactory sf=ConnectionFactory.emergencyConnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        int total=55*Integer.parseInt(units);
        GasBill gb=new GasBill(Login.companyId, cust, contact, address, agl, date, "7 Days from Bill Date", units, "55", Integer.toString(total), "Paid");
        session.save(gb);
        tx.commit();
        
        Criteria ct=session.createCriteria(GasBill.class);
        ct.add(Restrictions.and(Restrictions.eq("aglNo", agl), Restrictions.eq("date", date)));
        List<GasBill> list=ct.list();
        for(GasBill gb1: list)
        {
            billNo=gb1.getSerialNo();
        }
        Criteria crit=session.createCriteria(Company.class);
        crit.add(Restrictions.eq("companyId", Login.companyId));
        List<Company> list1=crit.list();
        String address1="", contact1="";
        for(Company com: list1)
        {
            address1=com.getAddress();
            contact1=com.getContactNo();
        }
                    jLabel60.setText("Date: "+date);
                    jLabel61.setText("Bill No.: "+Integer.toString(billNo));
                    jLabel62.setText("Company ID: "+Login.companyId);
                    jLabel63.setText("Customer Name: "+cust);
                    jLabel64.setText("Contact No.: "+contact);
                    jLabel65.setText("Due Date: 7 Days from Bill Date");
                    jLabel66.setText("Address: "+address);
                    jLabel67.setText("AGL No.: "+agl);
                    jLabel68.setText("Units Consumed: "+units);
                    jLabel69.setText("Price per unit: Rs 55/-");
                    jLabel70.setText("Sub Total: Rs "+Integer.toString(total)+"/-");
                    jLabel71.setText("Helpline");
                    jLabel72.setText("Contact Number: "+contact1);
                    jLabel73.setText("Email: "+Login.companyId+"@gmail.com");
                    jLabel74.setText("Address: "+address1);  
                    cl.show(jPanel1, "ginvoice");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int billNo=0;
        String cust=jTextField37.getText();
        String contact=jTextField36.getText();
        String address=jTextField35.getText();
        String tele=jTextField34.getText();
        String plan=jTextField33.getText();
        String months=jTextField38.getText();
        String date=jTextField32.getText();
        
        SessionFactory sf=ConnectionFactory.emergencyConnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        int total=Integer.parseInt(plan)*Integer.parseInt(months);
        TelephoneBill gb=new TelephoneBill(Login.companyId, cust, contact, address, tele, date, "7 Days from Bill Date", plan, Integer.toString(total), months);
        session.save(gb);
        tx.commit();
        
        Criteria ct=session.createCriteria(TelephoneBill.class);
        ct.add(Restrictions.and(Restrictions.eq("telephoneId", tele), Restrictions.eq("date", date)));
        List<TelephoneBill> list=ct.list();
        for(TelephoneBill tb: list)
        {
            billNo=tb.getSerialNo();
        }
        Criteria crit=session.createCriteria(Company.class);
        crit.add(Restrictions.eq("companyId", Login.companyId));
        List<Company> list1=crit.list();
        String address1="", contact1="";
        for(Company com: list1)
        {
            address1=com.getAddress();
            contact1=com.getContactNo();
        }
                    jLabel76.setText("Date: "+date);
                    jLabel77.setText("Bill No.: "+Integer.toString(billNo));
                    jLabel78.setText("Company ID: "+Login.companyId);
                    jLabel79.setText("Customer Name: "+cust);
                    jLabel80.setText("Due Date: 7 Days from Bill Date");
                    jLabel81.setText("Contact No.: "+contact);
                    jLabel82.setText("Telephone ID: "+tele);
                    jLabel83.setText("Address: "+address);
                    jLabel84.setText("Plan: Rs "+plan+"/-");
                    jLabel85.setText("Sub Total: Rs "+total+"/-");
                    jLabel86.setText("Helpline");
                    jLabel87.setText("Contact Number: "+contact1);
                    jLabel88.setText("Email: "+Login.companyId+"@gmail.com");
                    jLabel89.setText("Address: "+address1);
                    cl.show(jPanel1, "tinvoice");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

        String choice=(String)jComboBox3.getSelectedItem();
        if(choice.equals("Add Product"))
        {
            cl.show(jPanel1, "addpro");
        }
        if(choice.equals("Delete Product"))
        {
            cl.show(jPanel1, "deletepro");
        }
        if(choice.equals("Update Product"))
        {
            jLabel152.setVisible(false);
            jTextField39.setVisible(false);
            cl.show(jPanel1, "updatepro");
        }
        if(choice.equals("View Products"))
        {
            if(m>1)
            {
                dtm1.setRowCount(0);
            }
            view();
            
            cl.show(jPanel1, "viewpro");
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed
public void view()
{

            SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
            Criteria ct=session.createCriteria(Product.class);
            ct.add(Restrictions.eq("companyId", Login.companyId));
            List<Product> list=ct.list();
            dtm1=(DefaultTableModel)jTable2.getModel();                
            for(Product pro: list)
            {
                Object obj[]={pro.getProductId(), pro.getProductName(), pro.getProductPrice(), pro.getProductQuantity(), pro.getWarranty()};
                dtm1.addRow(obj);
            }
            m++;
}
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
            String id=jTextField12.getText();
            String name=jTextField19.getText();
            String price=jTextField20.getText();
            String quantity=jTextField21.getText();
            String warranty=jTextField29.getText();
            
            SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
            Product pro=new Product(Login.companyId, id, name, price, quantity, warranty, "Available");
            session.save(pro);
            tx.commit();
            JOptionPane.showMessageDialog(this, "Product Added");
            jTextField12.setText("");
            jTextField19.setText("");
            jTextField20.setText("");
            jTextField21.setText("");
            jTextField29.setText("");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int serial=0;    
            SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
            String id=jTextField30.getText();
            Criteria ct=session.createCriteria(Product.class);
            ct.add(Restrictions.and(Restrictions.eq("productId", id),Restrictions.eq("companyId", Login.companyId)));
            List<Product> list=ct.list();
            for(Product pro: list)
            {
                serial=pro.getSerialNo();
            }
            Product pr=(Product)session.get(Product.class, serial);
            session.delete(pr);
            tx.commit();
            JOptionPane.showMessageDialog(this, "Product Deleted");
            jTextField30.setText("");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        String choice=(String)jComboBox5.getSelectedItem();
        if(choice.equals("Product Name"))
        {
            jLabel152.setVisible(true);
            jTextField39.setVisible(true);
            jLabel152.setText("Enter Updated Product Name");
        }
        if(choice.equals("Product Price"))
        {
            jLabel152.setVisible(true);
            jTextField39.setVisible(true);
            jLabel152.setText("Enter Updated Product Price");
        }
        if(choice.equals("Product Quantity"))
        {
            jLabel152.setVisible(true);
            jTextField39.setVisible(true);
            jLabel152.setText("Enter Updated Product Quantity");
        }
        if(choice.equals("Product Warranty"))
        {
            jLabel152.setVisible(true);
            jTextField39.setVisible(true);
            jLabel152.setText("Enter Updated Product Warranty");
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
            int serial=0;
            SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
            Criteria ct=session.createCriteria(Product.class);
            String id=jTextField31.getText();
            ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("productId", id)));
            List<Product> list=ct.list();
            for(Product pro: list)
            {
                serial=pro.getSerialNo();
            }
            Product pr=(Product)session.get(Product.class, serial);
            String choice=(String)jComboBox5.getSelectedItem();
        if(choice.equals("Product Name"))
        {
            String uName=jTextField39.getText();
            pr.setProductName(uName);
            session.update(pr);
            tx.commit();
        }
        if(choice.equals("Product Price"))
        {
            String uPrice=jTextField39.getText();
            pr.setProductPrice(uPrice);
            session.update(pr);
            tx.commit();
        }
        if(choice.equals("Product Quantity"))
        {
            String uQuan=jTextField39.getText();
            pr.setProductQuantity(uQuan);
            session.update(pr);
            tx.commit();
        }
        if(choice.equals("Product Warranty"))
        {
            String uWar=jTextField39.getText();
            pr.setWarranty(uWar);
            session.update(pr);
            tx.commit();
        }
        JOptionPane.showMessageDialog(this, "Product Updated");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField41ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        String choice=(String)jComboBox6.getSelectedItem();
        SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
        if(choice.equals("Company Name"))
        {
            jLabel156.setVisible(true);
            jTextField41.setVisible(true);
            jLabel156.setText("Enter Updated Company Name:");
        }
        if(choice.equals("Registration ID"))
        {
            jLabel156.setVisible(true);
            jTextField41.setVisible(true);
            jLabel156.setText("Enter Updated Registration ID:");
        }
        if(choice.equals("Contact Number"))
        {
            jLabel156.setVisible(true);
            jTextField41.setVisible(true);
            jLabel156.setText("Enter Updated Contact Number:");
        }
        if(choice.equals("Address"))
        {
            jLabel156.setVisible(true);
            jTextField41.setVisible(true);
            jLabel156.setText("Enter Updated Address:");
        }
        if(choice.equals("GST Number"))
        {
            jLabel156.setVisible(true);
            jTextField41.setVisible(true);
            jLabel156.setText("Enter Updated GST Number:");
        }
        if(choice.equals("Bill Type"))
        {
            jLabel157.setVisible(true);
            jRadioButton1.setVisible(true);
            jRadioButton2.setVisible(true);
            jRadioButton3.setVisible(true);
            jRadioButton4.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String choice=(String)jComboBox6.getSelectedItem();
        SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
            String updated=jTextField41.getText();
            if(choice.equals("Company Name"))
        {
            Company com=(Company)session.get(Company.class, Login.companyId);
            com.setCompanyName(updated);
            session.update(com);
            tx.commit();
        }
        if(choice.equals("Registration ID"))
        {
            Company com=(Company)session.get(Company.class, Login.companyId);
            com.setRegistrationId(updated);
            session.update(com);
            tx.commit();
        }
        if(choice.equals("Contact Number"))
        {
            Company com=(Company)session.get(Company.class, Login.companyId);
            com.setContactNo(updated);
            session.update(com);
            tx.commit();
        }
        if(choice.equals("Address"))
        {
            Company com=(Company)session.get(Company.class, Login.companyId);
            com.setAddress(updated);
            session.update(com);
            tx.commit();
        }
        if(choice.equals("GST Number"))
        {
            Company com=(Company)session.get(Company.class, Login.companyId);
            com.setGstNo(updated);
            session.update(com);
            tx.commit();
        }
        if(choice.equals("Bill Type"))
        {
            
            String billTypeElec="", billTypeShop="", billTypeGas="", billTypeTele="";
        if(jRadioButton1.isSelected()==true)
        {
            billTypeElec=jRadioButton1.getActionCommand()+",";
        }
        if(jRadioButton2.isSelected()==true)
        {
            billTypeShop=jRadioButton2.getActionCommand()+",";
        }
        if(jRadioButton4.isSelected()==true)
        {
            billTypeGas=jRadioButton4.getActionCommand()+",";
        }
        if(jRadioButton3.isSelected()==true)
        {
            billTypeTele=jRadioButton3.getActionCommand()+",";
        }
        String billType=billTypeElec+billTypeShop+billTypeGas+billTypeTele;
        int length=billType.length();
        char ch[]=new char[length];
        char ch1[]=new char[length-1];
        for(int i=0;i<length;i++)
        {
            ch[i]=billType.charAt(i);
        }
        for(int j=0;j<length-1;j++)
        {
            ch1[j]=ch[j];
        }
        String billType1=new String(ch1);
            Company com=(Company)session.get(Company.class, Login.companyId);
            com.setBillType(billType1);
            session.update(com);
            tx.commit();
        }
        JOptionPane.showMessageDialog(this, "Profile Updated");
        dispose();
        Home home=new Home();
        home.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
        char[] opass1=jPasswordField1.getPassword();
        char[] npass1=jPasswordField2.getPassword();
        char[] cnpass1=jPasswordField3.getPassword();
        String opass=new String(opass1);
        String npass=new String(npass1);
        String cnpass=new String(cnpass1);
        Criteria ct=session.createCriteria(Company.class);
        ct.add(Restrictions.and(Restrictions.eq("companyId",Login.companyId), Restrictions.eq("password", opass)));
        List<Company> list=ct.list();
        if(list.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Old Password does not match");
        }
        else
        {
            if(npass.equals(cnpass))
            {
                Company com=(Company)session.get(Company.class, Login.companyId);
                com.setPassword(npass);
                session.update(com);
                tx.commit();
                JOptionPane.showMessageDialog(this, "Password Changed");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "New Password and Confirm New Password does not match");
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String type=(String)jComboBox7.getSelectedItem();
        String billNo=jTextField40.getText();
        SessionFactory sf=ConnectionFactory.emergencyConnection();
            Session session=sf.openSession();
            Transaction tx=session.beginTransaction();
            if(type.equals("Electricity Bill"))
            {
                Criteria ct=session.createCriteria(ElectricityBill.class);
                ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(billNo))));
                List<ElectricityBill> list=ct.list();
                if(list.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Bill Not Found");
                }
                else
                {
                    ElectricityBill eb=(ElectricityBill)session.get(ElectricityBill.class, Integer.parseInt(billNo));
                    session.delete(eb);
                    tx.commit();
                    JOptionPane.showMessageDialog(this, "Bill Deleted");
                }
            }
            if(type.equals("Shop Bill"))
            {
                Criteria ct=session.createCriteria(ShopBill.class);
                ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(billNo))));
                List<ShopBill> list=ct.list();
                if(list.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Bill Not Found");
                }
                else
                {
                    ShopBill eb=(ShopBill)session.get(ShopBill.class, Integer.parseInt(billNo));
                    session.delete(eb);
                    tx.commit();
                    JOptionPane.showMessageDialog(this, "Bill Deleted");
                }
            }
            if(type.equals("Gas Bill"))
            {
                Criteria ct=session.createCriteria(GasBill.class);
                ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(billNo))));
                List<GasBill> list=ct.list();
                if(list.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Bill Not Found");
                }
                else
                {
                    GasBill eb=(GasBill)session.get(GasBill.class, Integer.parseInt(billNo));
                    session.delete(eb);
                    tx.commit();
                    JOptionPane.showMessageDialog(this, "Bill Deleted");
                }
            }
            if(type.equals("Telephone Bill"))
            {
                Criteria ct=session.createCriteria(TelephoneBill.class);
                ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(billNo))));
                List<TelephoneBill> list=ct.list();
                if(list.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Bill Not Found");
                }
                else
                {
                    TelephoneBill eb=(TelephoneBill)session.get(TelephoneBill.class, Integer.parseInt(billNo));
                    session.delete(eb);
                    tx.commit();
                    JOptionPane.showMessageDialog(this, "Bill Deleted");
                }
            }
    }//GEN-LAST:event_jButton15ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
