/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bh_management.system;

import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static java.awt.Color.gray;
import static java.awt.Color.white;

/**
 *
 * @author User
 */
public class AdminForm extends javax.swing.JFrame {
    
    String pcode;
    String roomValue;
    String recode;
    String sendTextField;
    int roomAvail = 4; 
    int totalTenant = 0;

    public AdminForm() {
        initComponents();
        Connect();
        LoadDataPayHistory();
        LoadDataTenant();
        LoadDataArchive();
        numberOfTenants();
        loadDataMessage1();

    
        dashBoardPanel.setVisible(true);
        registerPanel.setVisible(false);
        tenantPanel.setVisible(false);
        paymentPanel.setVisible(false);
        payHistoryPanel.setVisible(false);
        archivePanel.setVisible(false);
        noticePanel.setVisible(false);

        deleteBtn.setEnabled(false);
        
        pword.setEchoChar('*');
        confirmPword.setEchoChar('*');
        
 
        
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    PreparedStatement pst1;
    ResultSet rs1;
    PreparedStatement pst2;
    ResultSet rs2;
    PreparedStatement pst3;
    ResultSet rs3;
    PreparedStatement pst4;
    ResultSet rs4;
    PreparedStatement pst5;
    ResultSet rs5;
    PreparedStatement pst6;
    ResultSet rs6;
    DefaultTableModel df = new DefaultTableModel();

    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bh_ms?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoadingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadDataTenant()
    {
        try {
            int q;
            pst = con.prepareStatement("SELECT * FROM `register`");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)tenantTable.getModel();
            df.setRowCount(0);
            
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("Recode"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Middlename"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Address"));
                    vec.add(rs.getString("Gender"));
                    vec.add(rs.getString("Number"));
                    vec.add(rs.getString("Room"));
                    vec.add(rs.getString("Username"));                    
                }
                 df.addRow(vec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void LoadDataArchive()
    {
        try {
            int q;
            pst = con.prepareStatement("SELECT * FROM `archive`");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)archiveTable.getModel();
            df.setRowCount(0);
            
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("Recode"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Middlename"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Address"));
                    vec.add(rs.getString("Gender"));
                    vec.add(rs.getString("Number"));
                    vec.add(rs.getString("Room"));
                    vec.add(rs.getString("Username"));                
                }
                 df.addRow(vec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void LoadDataPayHistory()
    {
        try {
            int q;
            pst = con.prepareStatement("SELECT * FROM `payment`");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)payHistoryTable.getModel();
            df.setRowCount(0);
            
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("Code"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Date"));
                    vec.add(rs.getString("Amount"));
                    vec.add(rs.getString("Room"));                   
                }
                 df.addRow(vec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   public void numberOfTenants()
   {
       try {
    pst = con.prepareStatement("SELECT COUNT(*) AS total FROM `register`");
    rs = pst.executeQuery();

    if (rs.next()) {
        totalTenant = rs.getInt("total");
        totalTenantLabel.setText(String.valueOf(totalTenant));
    }
} catch (SQLException ex) {
    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
    // Handle the exception or display an error message
}
   }
   
   public void loadDataMessage1() {
    try {
        //UserPanel userpanel = new UserPanel();
        //JTextField userSendField = userpanel.userSendField;
        
        pst = con.prepareStatement("SELECT * FROM `messageuser`");
        rs = pst.executeQuery();
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        while(rs.next()) {
            String message = rs.getString("umessage");
            Date date = rs.getDate("udate");
            String name = rs.getString("uname");
            
            String formattedMessage = "Sender: " + name + " _"+ "     '"+ message + "' "+"  _ " + date.toString();
            listModel.addElement(formattedMessage);
            
        }
        
        adminReceive.setModel(listModel);
    } catch (SQLException ex) {
        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dashBtn = new javax.swing.JPanel();
        dashBoardBtn = new javax.swing.JLabel();
        roomBtn = new javax.swing.JPanel();
        tenantBtn = new javax.swing.JLabel();
        tenantsBtn = new javax.swing.JPanel();
        paymentBtn = new javax.swing.JLabel();
        paymentsBtn = new javax.swing.JPanel();
        payHistoryBtn = new javax.swing.JLabel();
        archiveBtn = new javax.swing.JPanel();
        noticesBtn = new javax.swing.JLabel();
        noticeBtn = new javax.swing.JPanel();
        roomsBtn = new javax.swing.JLabel();
        paymentHistory = new javax.swing.JPanel();
        archivesBtn = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        dashBoardPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        totalTenantLabel1 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        totalTenantLabel = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        registerPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        code = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        firstname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        middlename = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lastname = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        gRoom = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        uName = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        pword = new javax.swing.JPasswordField();
        showPassword1 = new javax.swing.JCheckBox();
        jLabel41 = new javax.swing.JLabel();
        confirmPword = new javax.swing.JPasswordField();
        showPassword = new javax.swing.JCheckBox();
        registerBtn = new javax.swing.JButton();
        tenantPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        searchBy = new javax.swing.JComboBox<>();
        deleteBtn = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tenantTable = new javax.swing.JTable();
        paymentPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        confirmBtn = new javax.swing.JButton();
        room = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        code1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        payHistoryPanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        search2 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        searchBy2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        payHistoryTable = new javax.swing.JTable();
        archivePanel = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        search1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        searchBy1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        archiveTable = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        noticePanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        noticeDeleteBtn = new javax.swing.JButton();
        adminSendField = new javax.swing.JTextField();
        adminSendBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        adminReceive = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 200));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setForeground(new java.awt.Color(51, 153, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bh_management/system/USERLOGO.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dashBtn.setBackground(new java.awt.Color(51, 102, 255));

        dashBoardBtn.setBackground(new java.awt.Color(51, 102, 255));
        dashBoardBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dashBoardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashBoardBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dashBoardBtn.setText("DASHBOARD");
        dashBoardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashBoardBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashBoardBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashBoardBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashBoardBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout dashBtnLayout = new javax.swing.GroupLayout(dashBtn);
        dashBtn.setLayout(dashBtnLayout);
        dashBtnLayout.setHorizontalGroup(
            dashBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashBoardBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dashBtnLayout.setVerticalGroup(
            dashBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashBoardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        roomBtn.setBackground(new java.awt.Color(51, 102, 255));

        tenantBtn.setBackground(new java.awt.Color(51, 102, 255));
        tenantBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tenantBtn.setForeground(new java.awt.Color(255, 255, 255));
        tenantBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenantBtn.setText("TENANTS");
        tenantBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tenantBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenantBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tenantBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tenantBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout roomBtnLayout = new javax.swing.GroupLayout(roomBtn);
        roomBtn.setLayout(roomBtnLayout);
        roomBtnLayout.setHorizontalGroup(
            roomBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tenantBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        roomBtnLayout.setVerticalGroup(
            roomBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tenantBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        tenantsBtn.setBackground(new java.awt.Color(51, 102, 255));

        paymentBtn.setBackground(new java.awt.Color(51, 102, 255));
        paymentBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        paymentBtn.setForeground(new java.awt.Color(255, 255, 255));
        paymentBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paymentBtn.setText("PAYMENTS");
        paymentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        paymentBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paymentBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paymentBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout tenantsBtnLayout = new javax.swing.GroupLayout(tenantsBtn);
        tenantsBtn.setLayout(tenantsBtnLayout);
        tenantsBtnLayout.setHorizontalGroup(
            tenantsBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        tenantsBtnLayout.setVerticalGroup(
            tenantsBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        paymentsBtn.setBackground(new java.awt.Color(51, 102, 255));

        payHistoryBtn.setBackground(new java.awt.Color(51, 102, 255));
        payHistoryBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        payHistoryBtn.setForeground(new java.awt.Color(255, 255, 255));
        payHistoryBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        payHistoryBtn.setText("PAYMENT HISTORY");
        payHistoryBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        payHistoryBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payHistoryBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                payHistoryBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                payHistoryBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout paymentsBtnLayout = new javax.swing.GroupLayout(paymentsBtn);
        paymentsBtn.setLayout(paymentsBtnLayout);
        paymentsBtnLayout.setHorizontalGroup(
            paymentsBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(payHistoryBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        paymentsBtnLayout.setVerticalGroup(
            paymentsBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(payHistoryBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        archiveBtn.setBackground(new java.awt.Color(51, 102, 255));

        noticesBtn.setBackground(new java.awt.Color(51, 102, 255));
        noticesBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        noticesBtn.setForeground(new java.awt.Color(255, 255, 255));
        noticesBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noticesBtn.setText("NOTICES");
        noticesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        noticesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noticesBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noticesBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                noticesBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout archiveBtnLayout = new javax.swing.GroupLayout(archiveBtn);
        archiveBtn.setLayout(archiveBtnLayout);
        archiveBtnLayout.setHorizontalGroup(
            archiveBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noticesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        archiveBtnLayout.setVerticalGroup(
            archiveBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noticesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        noticeBtn.setBackground(new java.awt.Color(51, 102, 255));

        roomsBtn.setBackground(new java.awt.Color(51, 102, 255));
        roomsBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        roomsBtn.setForeground(new java.awt.Color(255, 255, 255));
        roomsBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roomsBtn.setText("REGISTER");
        roomsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roomsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomsBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                roomsBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                roomsBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout noticeBtnLayout = new javax.swing.GroupLayout(noticeBtn);
        noticeBtn.setLayout(noticeBtnLayout);
        noticeBtnLayout.setHorizontalGroup(
            noticeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roomsBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        noticeBtnLayout.setVerticalGroup(
            noticeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roomsBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        paymentHistory.setBackground(new java.awt.Color(51, 102, 255));

        archivesBtn.setBackground(new java.awt.Color(51, 102, 255));
        archivesBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        archivesBtn.setForeground(new java.awt.Color(255, 255, 255));
        archivesBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        archivesBtn.setText("ARCHIVE");
        archivesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archivesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archivesBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                archivesBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                archivesBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout paymentHistoryLayout = new javax.swing.GroupLayout(paymentHistory);
        paymentHistory.setLayout(paymentHistoryLayout);
        paymentHistoryLayout.setHorizontalGroup(
            paymentHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(archivesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        paymentHistoryLayout.setVerticalGroup(
            paymentHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(archivesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dashBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roomBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tenantsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(paymentsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(archiveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(noticeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(paymentHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dashBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(roomBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tenantsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(paymentsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(paymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(archiveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(noticeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(51, 102, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome!");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Felisilda, Arnold James");

        logoutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(68, 87, 251));
        logoutBtn.setText("LOG OUT");
        logoutBtn.setBorder(null);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        dashBoardPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(74, 140, 217));

        jPanel6.setBackground(new java.awt.Color(0, 51, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Room");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        totalTenantLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        totalTenantLabel1.setForeground(new java.awt.Color(0, 0, 0));
        totalTenantLabel1.setText("6");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Total Number of Room");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(totalTenantLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalTenantLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(0, 42, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(74, 140, 217));

        jPanel11.setBackground(new java.awt.Color(0, 51, 255));
        jPanel11.setForeground(new java.awt.Color(51, 51, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tenants");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        totalTenantLabel.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        totalTenantLabel.setForeground(new java.awt.Color(0, 0, 0));
        totalTenantLabel.setText("0");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Total Number of Tenant");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(totalTenantLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalTenantLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(0, 42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dashBoardPanelLayout = new javax.swing.GroupLayout(dashBoardPanel);
        dashBoardPanel.setLayout(dashBoardPanelLayout);
        dashBoardPanelLayout.setHorizontalGroup(
            dashBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashBoardPanelLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        dashBoardPanelLayout.setVerticalGroup(
            dashBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashBoardPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(dashBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 358, Short.MAX_VALUE))
        );

        registerPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ROOMS");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("REGISTER");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Code:");

        code.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Firstname:");

        firstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Middlename:");

        middlename.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Lastname:");

        lastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Address:");

        address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Gender:");

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Male", "Female" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Mobile No.:");

        number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Room:");

        gRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "(Male Category)", "Room 1", "Room 2", "Room 3", "(Female Category)", "Room 4", "Room 5", "Room 6" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Username:");

        uName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Password:");

        pword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pword.setEchoChar('\u0000');

        showPassword1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        showPassword1.setForeground(new java.awt.Color(0, 0, 0));
        showPassword1.setText("Show Password");
        showPassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassword1ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Confirm Password:");

        confirmPword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        confirmPword.setEchoChar('\u0000');

        showPassword.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        showPassword.setForeground(new java.awt.Color(0, 0, 0));
        showPassword.setText("Show Password");
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });

        registerBtn.setBackground(new java.awt.Color(68, 87, 251));
        registerBtn.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("REGISTER");
        registerBtn.setBorder(null);
        registerBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(gRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registerPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(133, 133, 133))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPanelLayout.createSequentialGroup()
                                        .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pword, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(showPassword1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(showPassword)
                            .addComponent(confirmPword, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(225, 225, 225))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(uName, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(46, 46, 46))
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, 0)
                        .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, 0)
                        .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(gRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPassword1))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmPword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPassword)))
                .addGap(53, 53, 53)
                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        tenantPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ROOMS");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("TENANTS");

        search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Search By:");

        searchBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Code", "Firstname", "Lastname", "Middlename", "Address", "Number", "Room" }));
        searchBy.setBorder(null);

        deleteBtn.setBackground(new java.awt.Color(51, 102, 255));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("DELETE");
        deleteBtn.setBorder(null);
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Dropbox\\PC\\Downloads\\search_txt-removebg-preview.png")); // NOI18N

        jPanel7.setBackground(new java.awt.Color(51, 153, 255));

        tenantTable.setBackground(new java.awt.Color(255, 255, 255));
        tenantTable.setForeground(new java.awt.Color(0, 0, 0));
        tenantTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Firstname", "Middlename", "Lastname", "Address", "Gender", "Number", "Room", "Username"
            }
        ));
        tenantTable.setGridColor(new java.awt.Color(0, 51, 255));
        tenantTable.setShowGrid(true);
        tenantTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenantTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tenantTable);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tenantPanelLayout = new javax.swing.GroupLayout(tenantPanel);
        tenantPanel.setLayout(tenantPanelLayout);
        tenantPanelLayout.setHorizontalGroup(
            tenantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tenantPanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(tenantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tenantPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tenantPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        tenantPanelLayout.setVerticalGroup(
            tenantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tenantPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(tenantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGroup(tenantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tenantPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(tenantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)))
                    .addGroup(tenantPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(search))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tenantPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        paymentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("ROOMS");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("PAYMENTS");

        lname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Lastname:");

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Amount:");

        amount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        confirmBtn.setBackground(new java.awt.Color(51, 102, 255));
        confirmBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        confirmBtn.setForeground(new java.awt.Color(255, 255, 255));
        confirmBtn.setText("CONFIRM");
        confirmBtn.setBorder(null);
        confirmBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        room.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "(Male Category)", "RM 1", "RM 2", "RM 3", "(Female Category)", "RM 4", "RM 5", "RM 6" }));
        room.setBorder(null);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Room:");

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Code:");

        code1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Firstname");

        fname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel35)
                    .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(565, 565, 565))
        );
        paymentPanelLayout.setVerticalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addGap(45, 45, 45)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        payHistoryPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("ROOMS");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("HISTORY");

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Search:");

        search2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        search2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search2KeyReleased(evt);
            }
        });

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Search By:");

        searchBy2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Code", "Firstname ", "Lastname", "Date" }));
        searchBy2.setBorder(null);

        payHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Firstname", "Lastname", "Date", "Amount", "Room"
            }
        ));
        jScrollPane2.setViewportView(payHistoryTable);

        javax.swing.GroupLayout payHistoryPanelLayout = new javax.swing.GroupLayout(payHistoryPanel);
        payHistoryPanel.setLayout(payHistoryPanelLayout);
        payHistoryPanelLayout.setHorizontalGroup(
            payHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payHistoryPanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(payHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(payHistoryPanelLayout.createSequentialGroup()
                        .addGroup(payHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(payHistoryPanelLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search2))
                            .addGroup(payHistoryPanelLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBy2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        payHistoryPanelLayout.setVerticalGroup(
            payHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payHistoryPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(payHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addGap(29, 29, 29)
                .addGroup(payHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(search2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(searchBy2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        archivePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("ROOMS");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("ARCHIVE");

        search1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search1KeyReleased(evt);
            }
        });

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Search By:");

        searchBy1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Code", "Firstname", "Lastname", "Middlename", "Address", "Number" }));
        searchBy1.setBorder(null);

        archiveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Firstname", "Lastname", "Middlename", "Address", "Gender", "Number", "Room", "Username"
            }
        ));
        jScrollPane3.setViewportView(archiveTable);

        jLabel20.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Dropbox\\PC\\Downloads\\search_txt-removebg-preview.png")); // NOI18N

        javax.swing.GroupLayout archivePanelLayout = new javax.swing.GroupLayout(archivePanel);
        archivePanel.setLayout(archivePanelLayout);
        archivePanelLayout.setHorizontalGroup(
            archivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, archivePanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(archivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(archivePanelLayout.createSequentialGroup()
                        .addGroup(archivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(archivePanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search1))
                            .addGroup(archivePanelLayout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBy1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        archivePanelLayout.setVerticalGroup(
            archivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(archivePanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(archivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(29, 29, 29)
                .addGroup(archivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(archivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36)
                        .addComponent(searchBy1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        noticePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("ROOMS");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("NOTICES");

        noticeDeleteBtn.setBackground(new java.awt.Color(51, 102, 255));
        noticeDeleteBtn.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        noticeDeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        noticeDeleteBtn.setText("DELETE");
        noticeDeleteBtn.setBorder(null);
        noticeDeleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        noticeDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noticeDeleteBtnActionPerformed(evt);
            }
        });

        adminSendField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        adminSendBtn.setBackground(new java.awt.Color(51, 102, 255));
        adminSendBtn.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        adminSendBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminSendBtn.setText("SEND");
        adminSendBtn.setBorder(null);
        adminSendBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminSendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminSendBtnActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(102, 153, 255));

        adminReceive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adminReceive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminReceiveMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(adminReceive);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout noticePanelLayout = new javax.swing.GroupLayout(noticePanel);
        noticePanel.setLayout(noticePanelLayout);
        noticePanelLayout.setHorizontalGroup(
            noticePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, noticePanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(noticePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminSendBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminSendField, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, noticePanelLayout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(noticeDeleteBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        noticePanelLayout.setVerticalGroup(
            noticePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noticePanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(noticePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(noticeDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(adminSendField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(adminSendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dashBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(registerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(tenantPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(paymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(payHistoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(archivePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(noticePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dashBoardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(registerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(tenantPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(paymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(payHistoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(archivePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(noticePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void roomsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomsBtnMouseClicked
        dashBoardPanel.setVisible(false);
        registerPanel.setVisible(true);
        tenantPanel.setVisible(false);
        paymentPanel.setVisible(false);
        payHistoryPanel.setVisible(false);
        archivePanel.setVisible(false);
        noticePanel.setVisible(false);
        
        
    }//GEN-LAST:event_roomsBtnMouseClicked

    private void tenantBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenantBtnMouseClicked
        dashBoardPanel.setVisible(false);
        registerPanel.setVisible(false);
        tenantPanel.setVisible(true);
        paymentPanel.setVisible(false);
        payHistoryPanel.setVisible(false);
        archivePanel.setVisible(false);
        noticePanel.setVisible(false);
        
        
    }//GEN-LAST:event_tenantBtnMouseClicked

    private void paymentBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentBtnMouseClicked
        dashBoardPanel.setVisible(false);
        registerPanel.setVisible(false);
        tenantPanel.setVisible(false);
        paymentPanel.setVisible(true);
        payHistoryPanel.setVisible(false);
        archivePanel.setVisible(false);
        noticePanel.setVisible(false);
        
        
    }//GEN-LAST:event_paymentBtnMouseClicked

    private void payHistoryBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payHistoryBtnMouseClicked
        dashBoardPanel.setVisible(false);
        registerPanel.setVisible(false);
        tenantPanel.setVisible(false);
        paymentPanel.setVisible(false);
        payHistoryPanel.setVisible(true);
        archivePanel.setVisible(false);
        noticePanel.setVisible(false);
        
       
    }//GEN-LAST:event_payHistoryBtnMouseClicked

    private void archivesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivesBtnMouseClicked
        dashBoardPanel.setVisible(false);
        registerPanel.setVisible(false);
        tenantPanel.setVisible(false);
        paymentPanel.setVisible(false);
        payHistoryPanel.setVisible(false);
        archivePanel.setVisible(true);
        noticePanel.setVisible(false);
        
        
    }//GEN-LAST:event_archivesBtnMouseClicked

    private void noticesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noticesBtnMouseClicked
        dashBoardPanel.setVisible(false);
        registerPanel.setVisible(false);
        tenantPanel.setVisible(false);
        paymentPanel.setVisible(false);
        payHistoryPanel.setVisible(false);
        archivePanel.setVisible(false);
        noticePanel.setVisible(true);
        
        
    }//GEN-LAST:event_noticesBtnMouseClicked

    private void dashBoardBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashBoardBtnMouseClicked
        dashBoardPanel.setVisible(true);
        registerPanel.setVisible(false);
        tenantPanel.setVisible(false);
        paymentPanel.setVisible(false);
        payHistoryPanel.setVisible(false);
        archivePanel.setVisible(false);
        noticePanel.setVisible(false);
        
    }//GEN-LAST:event_dashBoardBtnMouseClicked

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
    DefaultTableModel df = (DefaultTableModel) tenantTable.getModel();
    int i=tenantTable.getSelectedRow();
        try 
        { 
            String uCode = df.getValueAt(i, 0).toString();

            // Retrieve the tenant data before deleting from the register table
            pst = con.prepareStatement("SELECT * FROM `register` WHERE Recode = ?");
            pst.setString(1, uCode);
            rs = pst.executeQuery();

            if (rs.next()) 
            {
                // Store the tenant data in the archive table
                pst = con.prepareStatement("INSERT INTO `archive` (Recode, Firstname, Middlename, Lastname, Address, Gender, Number, Room, Username) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, rs.getString("Recode"));
                pst.setString(2, rs.getString("Firstname"));
                pst.setString(3, rs.getString("Middlename"));
                pst.setString(4, rs.getString("Lastname"));
                pst.setString(5, rs.getString("Address"));
                pst.setString(6, rs.getString("Gender"));
                pst.setString(7, rs.getString("Number"));
                pst.setString(8, rs.getString("Room"));
                pst.setString(9, rs.getString("Username"));
                pst.executeUpdate();
            }

            // Delete the tenant data from the register table
           
            pst = con.prepareStatement("DELETE FROM `register` WHERE Recode = ?");
            pst.setString(1, uCode);

            int k = pst.executeUpdate();

            if (k == 1) 
            {
                int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) 
                {
                    LoadDataTenant();
                    LoadDataArchive();
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Error Deleting data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        try {
            String search_txt = search.getText() + "%";
            int q;
            int search = searchBy.getSelectedIndex();
            
            switch(search)
            {
                case 0:
                    pst = con.prepareStatement("SELECT * FROM `register` WHERE Recode LIKE '"+search_txt+"'");
                    break;
                case 1:
                    pst = con.prepareStatement("SELECT * FROM `register` WHERE Firstname LIKE '"+search_txt+"'");
                    break;
                case 2:
                    pst = con.prepareStatement("SELECT * FROM `register` WHERE Lastname LIKE '"+search_txt+"'");
                    break;
                case 3:
                    pst = con.prepareStatement("SELECT * FROM `register` WHERE Middlename LIKE '"+search_txt+"'");
                    break;
                case 4:
                    pst = con.prepareStatement("SELECT * FROM `register` WHERE Address LIKE '"+search_txt+"'");
                    break;
                case 5:
                    pst = con.prepareStatement("SELECT * FROM `register` WHERE Code LIKE '"+search_txt+"'");
                    break;
                case 6:
                    pst = con.prepareStatement("SELECT * FROM `register` WHERE Room LIKE '"+search_txt+"'");
                    break;
                    
            }
            
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)tenantTable.getModel();
            df.setRowCount(0);
            
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("Recode"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Middlename"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Address"));
                    vec.add(rs.getString("Gender"));
                    vec.add(rs.getString("Number"));
                    vec.add(rs.getString("Room"));
                    vec.add(rs.getString("Username"));                    
                }
                 df.addRow(vec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchKeyReleased

    private void search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search1KeyReleased
        try {
            String search_txt = search1.getText() + "%";
            int q;
            int search = searchBy1.getSelectedIndex();
            
            switch(search)
            {
                case 0:
                    pst = con.prepareStatement("SELECT * FROM `archive` WHERE Recode LIKE '"+search_txt+"'");
                    break;
                case 1:
                    pst = con.prepareStatement("SELECT * FROM `archive` WHERE Firstname LIKE '"+search_txt+"'");
                    break;
                case 2:
                    pst = con.prepareStatement("SELECT * FROM `archive` WHERE Lastname LIKE '"+search_txt+"'");
                    break;
                case 3:
                    pst = con.prepareStatement("SELECT * FROM `archive` WHERE Middlename LIKE '"+search_txt+"'");
                    break;
                case 4:
                    pst = con.prepareStatement("SELECT * FROM `archive` WHERE Address LIKE '"+search_txt+"'");
                    break;
                case 5:
                    pst = con.prepareStatement("SELECT * FROM `archive` WHERE Code LIKE '"+search_txt+"'");
                    break;
            }
            
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)archiveTable.getModel();
            df.setRowCount(0);
            
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("Recode"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Middlename"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Address"));
                    vec.add(rs.getString("Gender"));
                    vec.add(rs.getString("Number"));
                    vec.add(rs.getString("Room"));
                    vec.add(rs.getString("Username"));                    
                }
                 df.addRow(vec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search1KeyReleased

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        
        pcode = code1.getText();
        String pfname = fname.getText();
        String plname = lname.getText();
        String pdate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pamount = amount.getText();
        String proom = room.getSelectedItem().toString();

        if (pcode.isEmpty() || pfname.isEmpty() || plname.isEmpty() || /*pdate.isEmpty() ||*/ pamount.isEmpty() || proom.equals("Select") || proom.equals("(Male Category)") || proom.equals("(Female Category)")) 
        {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
        } 
        else if (!pamount.matches("\\d+")) 
        {
            JOptionPane.showMessageDialog(null, "Please recheck you amount");
        } 
        else 
        {
            try {
                pst = con.prepareStatement("SELECT Recode FROM `register` WHERE Recode = ?");
                pst.setString(1, pcode);
                rs = pst.executeQuery();

                if (!rs.next()) 
                {
                    JOptionPane.showMessageDialog(null, "The code you entered does not exist. Please try again.");
                } 
                else 
                {
                    pst = con.prepareStatement("INSERT INTO `payment`(`Code`, `Firstname`, `Lastname`, `Date`, `Amount`, `Room`) VALUES (?,?,?,?,?,?)");
                    pst.setString(1, pcode);
                    pst.setString(2, pfname);
                    pst.setString(3, plname);
                    pst.setString(4, pdate);
                    pst.setString(5, pamount);
                    pst.setString(6, proom);

                    int k = pst.executeUpdate();

                    if (k == 1) 
                    {
                        JOptionPane.showMessageDialog(null, "Data Saved");
                        code1.setText("");
                        fname.setText("");
                        lname.setText("");
                        amount.setText("");
                        room.setSelectedItem("Select");

                        LoadDataPayHistory();
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Error Data Saving");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search2KeyReleased
        try {
            String search_txt = search2.getText() + "%";
            int q;
            int search = searchBy2.getSelectedIndex();
            
            switch(search)
            {
                case 0:
                    pst = con.prepareStatement("SELECT * FROM `payment` WHERE Code LIKE '"+search_txt+"'");
                    break;
                case 1:
                    pst = con.prepareStatement("SELECT * FROM `payment` WHERE Firstname LIKE '"+search_txt+"'");
                    break;
                case 2:
                    pst = con.prepareStatement("SELECT * FROM `payment` WHERE Lastname LIKE '"+search_txt+"'");
                    break;
                case 3:
                    pst = con.prepareStatement("SELECT * FROM `payment` WHERE Date LIKE '"+search_txt+"'");
                    break;
            }
            
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)payHistoryTable.getModel();
            df.setRowCount(0);
            
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("Code"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Date"));
                    vec.add(rs.getString("Amount"));
                    vec.add(rs.getString("Room"));
                }
                 df.addRow(vec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_search2KeyReleased

    private void showPassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassword1ActionPerformed

        if(showPassword1.isSelected())
        {
            pword.setEchoChar((char)0);
        }
        else
        {
            pword.setEchoChar('*');
        }
    }//GEN-LAST:event_showPassword1ActionPerformed

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed

        if(showPassword.isSelected())
        {
            confirmPword.setEchoChar((char)0);
        }
        else
        {
            confirmPword.setEchoChar('*');
        }
    }//GEN-LAST:event_showPasswordActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed

       HomePanel homepanel = new HomePanel();
        
        
        recode = code.getText();
        String fname = firstname.getText();
        String mname = middlename.getText();
        String lname = lastname.getText();
        String address1 = address.getText();
        String genderValue = gender.getSelectedItem().toString();
        String num = number.getText();
        roomValue = gRoom.getSelectedItem().toString();
        String uname = uName.getText();
        String pword1 = new String(pword.getPassword());
        String confirmPassword = new String(confirmPword.getPassword());

        if(recode.isEmpty() || fname.isEmpty() || lname.isEmpty() || mname.isEmpty() || address1.isEmpty() || num.isEmpty() || genderValue.equals("Select") || roomValue.equals("Select") || roomValue.equals("(Male Category)") || roomValue.equals("(Female Category)") ||uname.isEmpty() || pword1.isEmpty() || confirmPassword.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please, fill all the field");
        }
        else if(num.length() > 11)
        {
            JOptionPane.showMessageDialog(null, "Phone Number exceed!");
        }
        else if(recode.length() != 4 || !recode.matches("\\d+"))
        {
            JOptionPane.showMessageDialog(null, "Personal code must be a 4-digit number.");
        }
        /*else if(!fname.matches("([a-zA-Z])") || !mname.matches("([a-zA-Z])") || !lname.matches("([a-zA-Z])") || !address1.matches("([a-zA-Z])"))
        {
            JOptionPane.showMessageDialog(null, "Please make sure you inputted only letters in your Fullname and Address!", "Error", HEIGHT);
        }*/
        else if(!(num.matches("\\d+")))
        {
            JOptionPane.showMessageDialog(null, "Please review your phone number. It should only contain numbers.", "Error", HEIGHT);
        }
        else if(!uname.matches("^([a-zA-Z0-9 +!@#$%^&*?-]){8,15}+"))
        {
            JOptionPane.showMessageDialog(null, "Username must be 8-15 characters long and contain letters, numbers, and symbols.", "Error", HEIGHT);
        }
        else if(!pword1.matches("^(?=.*?[0-9])(?=.*?[#@$!%^*?&-]).{8,15}$"))
        {
            JOptionPane.showMessageDialog(null, "Password must be 8-15 characters long and contain at least one number and one symbol.", "Error", HEIGHT);
        }
        else if(!pword1.equals(confirmPassword))
        {
            JOptionPane.showMessageDialog(null, "Password not match");
        }
        else
        {
            try
            {
                pst = con.prepareStatement("SELECT * FROM `register` WHERE Recode = ?");
                pst.setString(1, recode);
                rs = pst.executeQuery();
                
                pst1 = con.prepareStatement("SELECT * FROM `register` WHERE Username = ?");
                pst1.setString(1, uname);
                rs1 = pst1.executeQuery();
                
                pst1 = con.prepareStatement("SELECT * FROM `register` WHERE Room = 'Room 1'");
                //pst1.setString(1, roomValue);
                pst2 = con.prepareStatement("SELECT * FROM `register` WHERE Room = 'Room 2'");
                //pst2.setString(1, roomValue);
                pst3 = con.prepareStatement("SELECT * FROM `register` WHERE Room = 'Room 3'");
                //pst3.setString(1, roomValue);
                pst4 = con.prepareStatement("SELECT * FROM `register` WHERE Room = 'Room 4'");
                //pst4.setString(1, roomValue);
                pst5 = con.prepareStatement("SELECT * FROM `register` WHERE Room = 'Room 5'");
                //pst5.setString(1, roomValue);
                pst6 = con.prepareStatement("SELECT * FROM `register` WHERE Room = 'Room 6'");
                //pst6.setString(1, roomValue);
                
                
                
                if(rs.next())
                {
                    roomAvail--;
                    homepanel.room1.setText(String.valueOf(roomAvail));
                    homepanel.room2.setText(String.valueOf(roomAvail));
                    homepanel.room3.setText(String.valueOf(roomAvail));
                    homepanel.room4.setText(String.valueOf(roomAvail));
                    homepanel.room5.setText(String.valueOf(roomAvail));
                    homepanel.room6.setText(String.valueOf(roomAvail));
                }
                

                if (rs.next())
                {
                    JOptionPane.showMessageDialog(null, "This code is already in use. Please choose another one.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(rs1.next())
                {
                    JOptionPane.showMessageDialog(null, "This username is already in use. Please choose another one.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    pst = con.prepareStatement("INSERT INTO `register`(`Recode`,`Firstname`, `Middlename`, `Lastname`, `Address`, `Gender`, `Number`, `Room`,`Username`, `Password`) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, recode);
                    pst.setString(2, fname);
                    pst.setString(3, mname);
                    pst.setString(4, lname);
                    pst.setString(5, address1);
                    pst.setString(6, genderValue);
                    pst.setString(7, num);
                    pst.setString(8, roomValue);
                    pst.setString(9, uname);
                    pst.setString(10, pword1);

                    totalTenant++;

                    int k = pst.executeUpdate();

                    if (k == 1)
                    {
                        JOptionPane.showMessageDialog(null, "Data saved.");
                        code.setText("");
                        firstname.setText("");
                        middlename.setText("");
                        lastname.setText("");
                        address.setText("");
                        gender.setSelectedItem("Select");
                        number.setText("");
                        gRoom.setSelectedItem("Select");
                        uName.setText("");
                        pword.setText("");
                        confirmPword.setText("");
                        
                        totalTenantLabel.setText(String.valueOf(totalTenant));
                        
                        LoadDataTenant();
                        
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error saving data.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error saving data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_registerBtnActionPerformed

    private void tenantTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenantTableMouseClicked
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_tenantTableMouseClicked

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed

        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(response == JOptionPane.YES_OPTION)
        {
            //System.exit(0);
            new LoginForm().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void adminSendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminSendBtnActionPerformed
      try {
    sendTextField = adminSendField.getText();
    
    if (sendTextField.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please write something!");
    } else {
        pst = con.prepareStatement("INSERT INTO `message`(`message`, `date`) VALUES (?,?)");
        pst.setString(1, sendTextField);
        pst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        
        int k = pst.executeUpdate();
        
        if (k == 1) {
            JOptionPane.showMessageDialog(null, "Data sent.");
            adminSendField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Error sending.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} catch (SQLException ex) {
    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
}

        
    }//GEN-LAST:event_adminSendBtnActionPerformed

    private void adminReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminReceiveMouseClicked

    }//GEN-LAST:event_adminReceiveMouseClicked

    private void noticeDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noticeDeleteBtnActionPerformed
        try {
    int selectedRow = adminReceive.getSelectedIndex();
    if (selectedRow != -1) {
        DefaultListModel<String> listModel = (DefaultListModel<String>) adminReceive.getModel();
        String uid = listModel.getElementAt(selectedRow);
        
        pst = con.prepareStatement("DELETE FROM `messageuser` WHERE uid = ?");
        pst.setString(1, uid);
        int rowsDeleted = pst.executeUpdate();
        
        if (rowsDeleted >= 0) {
            JOptionPane.showMessageDialog(null, "Item deleted successfully.");
            
            // Remove the item from the list model
            listModel.remove(selectedRow);

        } else {
            JOptionPane.showMessageDialog(null, "Item not found.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "No item selected.");
    }
} catch (SQLException ex) {
    Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
}
      



    }//GEN-LAST:event_noticeDeleteBtnActionPerformed

    private void dashBoardBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashBoardBtnMouseEntered
       dashBoardBtn.setForeground(black);
    }//GEN-LAST:event_dashBoardBtnMouseEntered

    private void dashBoardBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashBoardBtnMouseExited
        dashBoardBtn.setForeground(white);
    }//GEN-LAST:event_dashBoardBtnMouseExited

    private void tenantBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenantBtnMouseEntered
         tenantBtn.setForeground(black);
    }//GEN-LAST:event_tenantBtnMouseEntered

    private void tenantBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenantBtnMouseExited
         tenantBtn.setForeground(white);
    }//GEN-LAST:event_tenantBtnMouseExited

    private void paymentBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentBtnMouseEntered
         paymentBtn.setForeground(black);
    }//GEN-LAST:event_paymentBtnMouseEntered

    private void paymentBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentBtnMouseExited
         paymentBtn.setForeground(white);
    }//GEN-LAST:event_paymentBtnMouseExited

    private void payHistoryBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payHistoryBtnMouseEntered
        payHistoryBtn.setForeground(black);
    }//GEN-LAST:event_payHistoryBtnMouseEntered

    private void payHistoryBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payHistoryBtnMouseExited
         payHistoryBtn.setForeground(white);
    }//GEN-LAST:event_payHistoryBtnMouseExited

    private void archivesBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivesBtnMouseEntered
         archivesBtn.setForeground(black);
    }//GEN-LAST:event_archivesBtnMouseEntered

    private void archivesBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivesBtnMouseExited
        archivesBtn.setForeground(white);
    }//GEN-LAST:event_archivesBtnMouseExited

    private void noticesBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noticesBtnMouseEntered
        noticesBtn.setForeground(black);
    }//GEN-LAST:event_noticesBtnMouseEntered

    private void noticesBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noticesBtnMouseExited
        noticesBtn.setForeground(white);
    }//GEN-LAST:event_noticesBtnMouseExited

    private void roomsBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomsBtnMouseEntered
        roomsBtn.setForeground(black); 
    }//GEN-LAST:event_roomsBtnMouseEntered

    private void roomsBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomsBtnMouseExited
         roomsBtn.setForeground(white);
    }//GEN-LAST:event_roomsBtnMouseExited

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
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JList<String> adminReceive;
    private javax.swing.JButton adminSendBtn;
    public javax.swing.JTextField adminSendField;
    private javax.swing.JTextField amount;
    private javax.swing.JPanel archiveBtn;
    private javax.swing.JPanel archivePanel;
    private javax.swing.JTable archiveTable;
    private javax.swing.JLabel archivesBtn;
    public javax.swing.JTextField code;
    public javax.swing.JTextField code1;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JPasswordField confirmPword;
    private javax.swing.JLabel dashBoardBtn;
    private javax.swing.JPanel dashBoardPanel;
    private javax.swing.JPanel dashBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField firstname;
    private javax.swing.JTextField fname;
    public javax.swing.JComboBox<String> gRoom;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JTextField lastname;
    private javax.swing.JTextField lname;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTextField middlename;
    private javax.swing.JPanel noticeBtn;
    private javax.swing.JButton noticeDeleteBtn;
    private javax.swing.JPanel noticePanel;
    private javax.swing.JLabel noticesBtn;
    private javax.swing.JTextField number;
    private javax.swing.JLabel payHistoryBtn;
    private javax.swing.JPanel payHistoryPanel;
    private javax.swing.JTable payHistoryTable;
    private javax.swing.JLabel paymentBtn;
    private javax.swing.JPanel paymentHistory;
    private javax.swing.JPanel paymentPanel;
    private javax.swing.JPanel paymentsBtn;
    private javax.swing.JPasswordField pword;
    private javax.swing.JButton registerBtn;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JComboBox<String> room;
    private javax.swing.JPanel roomBtn;
    private javax.swing.JLabel roomsBtn;
    private javax.swing.JTextField search;
    private javax.swing.JTextField search1;
    private javax.swing.JTextField search2;
    private javax.swing.JComboBox<String> searchBy;
    private javax.swing.JComboBox<String> searchBy1;
    private javax.swing.JComboBox<String> searchBy2;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JCheckBox showPassword1;
    private javax.swing.JLabel tenantBtn;
    private javax.swing.JPanel tenantPanel;
    private static javax.swing.JTable tenantTable;
    private javax.swing.JPanel tenantsBtn;
    private javax.swing.JLabel totalTenantLabel;
    private javax.swing.JLabel totalTenantLabel1;
    private javax.swing.JTextField uName;
    // End of variables declaration//GEN-END:variables




    
}
