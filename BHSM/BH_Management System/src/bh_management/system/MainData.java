/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Data;

import java.sql.*;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainData extends javax.swing.JFrame {
   
    public MainData() {
        initComponents();
        connect();
        LoadData();  
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel df = new DefaultTableModel();   
    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/felisilda_bd_system?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            
            //To disabled 
            fnametext.setEnabled(false);
            lnametext.setEnabled(false);
            phone.setEnabled(false);
            date.setEnabled(false);
            bloodType.setEditable(false);
            address.setEnabled(false);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadData()
    {
        try {
            int q;
            pst = con.prepareStatement("SELECT * FROM `donors_details` ");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("id"));
                    vec.add(rs.getString("PhoneNo"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Date"));
                    vec.add(rs.getString("BloodType"));
                    vec.add(rs.getString("Address"));
                }
                //add row
                
                df.addRow(vec);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fnametext = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lnametext = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bloodType = new javax.swing.JComboBox<>();
        search = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        searchBy = new javax.swing.JComboBox<>();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 119, 182));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Phone No.");

        jLabel5.setText("Date:");

        saveBtn.setText("ADD");
        saveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveBtnMouseClicked(evt);
            }
        });
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        jTable1.setBorder(null);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "Phone No.", "Firstname", "Lastname", "Date", "Blood Type", "Address"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        jLabel6.setText("BLOOD DONATION SYSTEM");

        jLabel7.setText("Blood Type:");

        jLabel8.setText("Address:");

        jLabel1.setText("(Sample: 11-18-23)");

        bloodType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Type B+", "Type B-", "Type AB+", "Type AB-", "Type O", "Type O+" }));

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Dropbox\\PC\\Downloads\\search_txt-removebg-preview.png")); // NOI18N

        jLabel9.setText("Search By:");

        searchBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Patient ID", "Phone No.", "Firstname", "Lastname", "Date", "Blood Type", "Address" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fnametext, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(lnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(searchBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(bloodType, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 17, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(searchBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        try {
            String uid = df.getValueAt(jTable1.getSelectedRow(), 0).toString();
            pst = con.prepareStatement("DELETE FROM `donors_details` WHERE id = ?");
            pst.setString(1, uid);
            
            int k = pst.executeUpdate();
            
            
            if(k == 1)
            {
                JOptionPane.showMessageDialog(null, "DATA DELETED");
                LoadData();
                
                saveBtn.setText("SAVE");
                saveBtn.setEnabled(true);
                editBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "ERROR DELETING DATA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if(editBtn.getText().equals("EDIT"))
        {
            phone.setText(df.getValueAt(jTable1.getSelectedRow(), 1).toString());
            fnametext.setText(df.getValueAt(jTable1.getSelectedRow(), 2).toString());
            lnametext.setText(df.getValueAt(jTable1.getSelectedRow(), 3).toString());       
            date.setText(df.getValueAt(jTable1.getSelectedRow(), 4).toString());
            address.setText(df.getValueAt(jTable1.getSelectedRow(), 6).toString());
            
            //TRANSFER DATA TO TEXTFIELD
            
            editBtn.setText("UPDATE");
            jButton3.setEnabled(false);
            saveBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }
        else
        {
            toUpdate();
            editBtn.setText("EDIT");
            jButton3.setEnabled(true);
            saveBtn.setEnabled(true);
            editBtn.setEnabled(false);
            jTable1.setEnabled(true);
            
            fnametext.setText("");
            lnametext.setText("");
            phone.setText("");
            date.setText("");
            bloodType.setSelectedItem("Select");
            address.setText("");
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    public void toUpdate()
    {
        try {
            String updatefname = fnametext.getText();
            String updatelname = lnametext.getText();
            String updatephone = phone.getText();
            String updatedate = date.getText();
            String value = bloodType.getSelectedItem().toString();
            String updateaddress = address.getText();
            
            String uid = df.getValueAt(jTable1.getSelectedRow(), 0).toString();
            
            pst = con.prepareStatement("UPDATE `donors_details` SET `PhoneNo`= ?,`Firstname`= ?,`Lastname`= ?,`Date`= ?,`BloodType`= ?, `Address`= ? WHERE `id` = ?");
            pst.setString(1, updatephone);
            pst.setString(2, updatefname);
            pst.setString(3, updatelname);
            pst.setString(4, updatedate);
            pst.setString(5, value);
            pst.setString(6, updateaddress);
            pst.setString(7, uid);
            
            int k = pst.executeUpdate();
            if(k == 1)
            {
                JOptionPane.showMessageDialog(null, "DATA UPDATED");
                LoadData();
                saveBtn.setText("SAVE");
                saveBtn.setEnabled(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "ERROR UPDATING");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        editBtn.setEnabled(true);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
        
        fnametext.setEnabled(true);
        lnametext.setEnabled(true);
        phone.setEnabled(true);
        date.setEnabled(true);
        bloodType.setEditable(true);
        address.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
       
        if(saveBtn.getText().equals("ADD"))
        {
            saveBtn.setText("SAVE");
            
            fnametext.setEnabled(true);
            lnametext.setEnabled(true);
            phone.setEnabled(true);
            date.setEnabled(true);
            bloodType.setEditable(true);
            address.setEnabled(true);
        }
        else if(saveBtn.getText().equals("SAVE"))
        {
            toSave();
            fnametext.setEnabled(true);
            lnametext.setEnabled(true);
            phone.setEnabled(true);
            date.setEnabled(true);
            bloodType.setEditable(true);
            address.setEnabled(true);
        }
    }//GEN-LAST:event_saveBtnActionPerformed
    public void toSave()
    {
        if(con != null)
        {
            try {     
                
                String savefname = fnametext.getText();
                String savelname = lnametext.getText();
                String savephone = phone.getText();
                String savedate = date.getText();
                String value = bloodType.getSelectedItem().toString();
                String saveaddress = address.getText();
                
                pst = con.prepareStatement("INSERT INTO `donors_details`(`PhoneNo`, `Firstname`, `Lastname`, `Date`, `BloodType`, `Address`) VALUES (?,?,?,?,?,?)");
                pst.setString(1, savephone);
                pst.setString(2, savefname);
                pst.setString(3, savelname);
                pst.setString(4, savedate);
                pst.setString(5, value);
                pst.setString(6, saveaddress);
                
                
                
                int k = pst.executeUpdate();
                
                if(k == 1)
                {
                    JOptionPane.showMessageDialog(null, "Data Saved");
                    fnametext.setText("");
                    lnametext.setText("");
                    phone.setText("");
                    date.setText("");
                    bloodType.setSelectedItem("Select");
                    address.setText("");
                    
                    saveBtn.setText("SAVE");
                    LoadData();
                 
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error Data Saving");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void saveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBtnMouseClicked

    }//GEN-LAST:event_saveBtnMouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        try {
            String search_txt = search.getText() + "%";
            String search_num = search.getText() + "%";
            int q;
            int search = searchBy.getSelectedIndex();
            
            switch (search) 
            {
                case 0:
                    pst = con.prepareStatement("SELECT * FROM donors_details WHERE ID LIKE '"+search_txt+"'");
                    break;
                case 1:
                    pst = con.prepareStatement("SELECT * FROM donors_details WHERE PhoneNo LIKE '"+search_num+"'");
                    break;
                case 2:
                    pst = con.prepareStatement("SELECT * FROM donors_details WHERE Firstname LIKE '"+search_txt+"'");
                    break;
                case 3:
                    pst = con.prepareStatement("SELECT * FROM donors_details WHERE Lastname LIKE '"+search_txt+"'");
                    break; 
                case 4:
                    pst = con.prepareStatement("SELECT * FROM donors_details WHERE Date LIKE '"+search_txt+"'");
                    break;
                case 5:
                    pst = con.prepareStatement("SELECT * FROM donors_details WHERE BloodType LIKE '"+search_txt+"'");
                    break;
                case 6:
                    pst = con.prepareStatement("SELECT * FROM donors_details WHERE Address LIKE '"+search_txt+"'");
                    break;
                default: 
                    break;
            }
            
            //pst = con.prepareStatement("SELECT * FROM donors_details WHERE Lastname LIKE '"+search_txt+"'");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            while(rs.next())
            {
                Vector vec = new Vector();
                for(int i = 0; i <= q; i++)
                {
                    vec.add(rs.getString("id"));
                    vec.add(rs.getString("PhoneNo"));
                    vec.add(rs.getString("Firstname"));
                    vec.add(rs.getString("Lastname"));
                    vec.add(rs.getString("Date"));
                    vec.add(rs.getString("BloodType"));
                    vec.add(rs.getString("Address"));
                }
                //add row
                
                df.addRow(vec);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchKeyReleased

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
            java.util.logging.Logger.getLogger(MainData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JComboBox<String> bloodType;
    private javax.swing.JTextField date;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField fnametext;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lnametext;
    private javax.swing.JTextField phone;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField search;
    private javax.swing.JComboBox<String> searchBy;
    // End of variables declaration//GEN-END:variables
}
