/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package depotsurabaya;

import static depotsurabaya.HomeAdmin.username;
import static depotsurabaya.Login.txt_user;
import static depotsurabaya.Menu.username;
import java.awt.*;
import java.sql.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class dataModelPenjualan{
    private int idPenjualan;
    private String idbarang;
    private String harga;
    private String jumlah;

    public dataModelPenjualan(int idPenjualan, String idbarang, String harga, String jumlah) {
        this.idPenjualan = idPenjualan;
        this.idbarang = idbarang;
        this.harga = harga;
        this.jumlah = jumlah;
    }
    
    public int getIdPenjualan() {
        return idPenjualan;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public String getHarga() {
        return harga;
    }

    public String getJumlah() {
        return jumlah;
    }
}



public class transaksi_jual_admin extends javax.swing.JFrame {

   static String username;
    public transaksi_jual_admin(String username) {
        initComponents();
         this.setResizable(false);
        this.username = username;
     
         cariProduk objProduk = new cariProduk();
        objProduk.TampilData();
        auto_code();
        nama_kasir();
       gkedit();
       jTable1.setModel(model);
    }
    
    private void nama_kasir() {
    try{
        String sql = "SELECT nama_user FROM data_user WHERE username='"+txt_user.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()){
            txt_kasir.setText(rs.getString(1));
            }else {
                JOptionPane.showMessageDialog(null, sql);
            }
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    txt_kasir.setEditable(false);
    }

    public void auto_code(){
    try{
        String sql = "SELECT MAX(nota_penjualan) as nota FROM penjualan ";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while(rs.next()){
                Object[ ] obj = new Object[1];
                obj[0] = rs.getString("nota");
                if(obj[0] == null){
                    obj[0] = "P0000";
                }
                String str_nt = (String) obj[0];
                String nt = str_nt.substring(1,5);//P0000
                int int_nota = Integer.parseInt(nt);
                int_nota++;
                String a = String.format("%04d", int_nota);
                String b = "P" + a;
                txt_nota.setText(b);
                txt_nota.setEditable(false);
            }
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    
    }
    
    private void hitung(){
    int harga = Integer.parseInt(txt_harga.getText());
    int qty = Integer.parseInt(txt_jumlah.getText());
    int subTtl  = harga * qty;
    txt_subTtl.setText(String.valueOf(subTtl));
    
    }
    
    private void total(){
     try {
            String sql = "SELECT SUM(sub_total) FROM detail_penjualan WHERE nota_penjualan='"
                    +txt_nota.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            
            if(rs.next()){
                gtotal.setText(rs.getString(1));
                
            }
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }




     public class cariProduk extends javax.swing.JFrame {
    DefaultTableModel tabModel;
    ResultSet RsProduk = null;
    
     private void TampilData() {
         try {
            Object[] judul_kolom = {"Id Menu"};
            tabModel = new DefaultTableModel(null,judul_kolom);
            table.setModel(tabModel);
            Connection conn = (Connection)DepotSurabaya.configDB();
            Statement stt = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            tabModel.addColumn("Nama Menu");
            table.setModel(tabModel);
            Statement stt1 = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            tabModel.addColumn("Harga");
            table.setModel(tabModel);
            Statement stt11 = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
             tabModel.addColumn("Stok");
            table.setModel(tabModel);
            Statement stt111 = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            RsProduk=stt.executeQuery("SELECT  * from menu");  
            while(RsProduk.next()){
                Object[] data={
                    RsProduk.getString("namamenu"),
                    RsProduk.getString("harga"),
                    
                    };
               tabModel.addRow(data);
            }                
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
         }
     private void cariData(String key){ 
    try{
            tabModel=new DefaultTableModel();
            tabModel.addColumn("Id Menu");
            tabModel.addColumn("Nama Menu");
            tabModel.addColumn("Harga");
            tabModel.addColumn("Stok");
            table.setModel(tabModel);      
        if(key != ""){ 
            Connection conn=(Connection)DepotSurabaya.configDB();
            Statement stt=conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            String query = "SELECT  * from menu WHERE id_menu ='"+cari.getText() +"' OR nama_menu LIKE '%"+key+"%'";
            RsProduk=stt.executeQuery(query);  
            while(RsProduk.next()){
                Object[] data={   
                RsProduk.getString("id_menu"),
                RsProduk.getString("nama_menu"),
                RsProduk.getString("harga"),
                RsProduk.getString("stok")};
               tabModel.addRow(data);
               table.setModel(tabModel);   
            }
            } else{
         Connection conn=(Connection)DepotSurabaya.configDB();
            Statement stt=conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            String query = "SELECT  * from menu WHERE id_menu ='"+cari.getText() +"' OR nama_menu LIKE '%"+key+"%'";
            RsProduk=stt.executeQuery(query);  
                Object[] data={
                    "kosong"
                    };
            tabModel.addRow(data);
            table.setModel(tabModel);   
        }                    
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
}
   
private void gkedit(){
    idmenu.setEditable(false);
    namamenu.setEditable(false);
    txt_subTtl.setEditable(false);
    
    gtotal.setEditable(false);
    }


private void kosong() {
    idmenu.setText(null);
    namamenu.setText(null);
    txt_harga.setText(null);
    txt_jumlah.setText(null);
    txt_subTtl.setText(null);
    listObj.clear();
    
}
static int total = 0;
static ArrayList<dataModelPenjualan>  listObj = new ArrayList<>();
static int nomer = 1;
public int getJumlahStock(String idBarang){
    String querySelect = "SELECT * FROM id_menu WHERE id_menu = '" + idmenu +"'";
        try {
            java.sql.Connection con = (Connection) DepotSurabaya.configDB();
            java.sql.PreparedStatement prp = con.prepareStatement(querySelect);
            java.sql.ResultSet rs = prp.executeQuery();
            while(rs.next()){
                return Integer.parseInt(rs.getString("stok"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(transaksi_jual_admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_kasir = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        Btn_edit = new javax.swing.JButton();
        btn_bayar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cari = new javax.swing.JTextField();
        idmenu = new javax.swing.JTextField();
        namamenu = new javax.swing.JTextField();
        txt_subTtl = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        txt_harga = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nota.setBorder(null);
        txt_nota.setOpaque(false);
        jPanel1.add(txt_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 26, 153, 29));

        txt_kasir.setBorder(null);
        txt_kasir.setOpaque(false);
        jPanel1.add(txt_kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 115, 153, 29));

        btn_tambah.setBorder(null);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 30, 30));

        gtotal.setBorder(null);
        gtotal.setOpaque(false);
        jPanel1.add(gtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 443, 282, 36));

        btn_hapus.setBorder(null);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 493, 74, 20));

        btn_clear.setBorder(null);
        btn_clear.setContentAreaFilled(false);
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        jPanel1.add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 492, 76, 23));

        Btn_edit.setBorder(null);
        Btn_edit.setContentAreaFilled(false);
        Btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 490, 75, 23));

        btn_bayar.setBorder(null);
        btn_bayar.setContentAreaFilled(false);
        btn_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bayarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 492, 75, 22));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Menu", "Nama Menu", "Harga", "Jumlah", "Sub Total"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 360, 240));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 350, 220));

        cari.setOpaque(false);
        cari.setBackground(new java.awt.Color(80, 181, 109));
        cari.setBorder(null);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });
        jPanel1.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 160, 20));

        idmenu.setOpaque(false);
        idmenu.setBackground(new java.awt.Color(147, 238, 167));
        idmenu.setBorder(null);
        jPanel1.add(idmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 32, 170, -1));

        namamenu.setOpaque(false);
        namamenu.setBackground(new java.awt.Color(147, 238, 167));
        namamenu.setBorder(null);
        jPanel1.add(namamenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 58, 170, -1));

        txt_subTtl.setOpaque(false);
        txt_subTtl.setBackground(new java.awt.Color(147, 238, 167));
        txt_subTtl.setBorder(null);
        txt_subTtl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_subTtlActionPerformed(evt);
            }
        });
        txt_subTtl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_subTtlKeyReleased(evt);
            }
        });
        jPanel1.add(txt_subTtl, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 136, 170, -1));

        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 110, 30));

        txt_harga.setOpaque(false);
        txt_harga.setBackground(new java.awt.Color(147, 238, 167));
        txt_harga.setBorder(null);
        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });
        jPanel1.add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 84, 170, -1));

        txt_jumlah.setOpaque(false);
        txt_jumlah.setBackground(new java.awt.Color(147, 238, 167));
        txt_jumlah.setBorder(null);
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyReleased(evt);
            }
        });
        jPanel1.add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 170, -1));

        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 30, 30, 25));
        jPanel1.add(txt_tglpenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 160, 30));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 100, 20));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 93, 60, 30));

        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 213, 100, 30));

        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 100, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\TRANSAKSI JUAL OWNER (3).png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
                try {
            String sql = "Delete from penjualan WHERE nota_penjualan='"+txt_nota.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, e.getMessage());
        }

          this.setVisible (false); 
	new HomeAdmin(username).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void txt_subTtlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_subTtlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_subTtlActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int baris = table.rowAtPoint(evt.getPoint());
        if(table.getValueAt(baris, 1) == null){
            namamenu.setText("");
        } else{
            namamenu.setText(table.getValueAt(baris, 1).toString());
        }   
        if(table.getValueAt(baris, 2) == null){
            txt_harga.setText("");
        } else{
            txt_harga.setText(table.getValueAt(baris, 2).toString());
        }   
        if(table.getValueAt(baris, 0) == null){
            idmenu.setText("");
        } else{
            idmenu.setText(table.getValueAt(baris, 0).toString());
        } 
        
    }//GEN-LAST:event_tableMouseClicked

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
        // TODO add your handling code here:
         String key = cari.getText();
    System.out.println(key);
        cariProduk objProd = new cariProduk();
    if (key!="") {
        objProd.cariData(key);
    } else {       
    }
    }//GEN-LAST:event_cariKeyReleased

    private void txt_subTtlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_subTtlKeyReleased
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txt_subTtlKeyReleased

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
       DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if(idmenu.getText().trim().equals("") || txt_jumlah.getText().trim().equals("") 
                || namamenu.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Data Yang Dimasukkan Belum Lengkap");
        }else{
        model.addRow(new Object[]{idmenu.getText(),namamenu.getText(),txt_harga.getText(),
        txt_jumlah.getText(),txt_subTtl.getText()});
        }
       
        try {
            String sql = "INSERT INTO detail_penjualan VALUES ('"+txt_nota.getText()+"','"
                    +idmenu.getText()+"',"+txt_subTtl.getText()+","+txt_jumlah.getText()+")";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosong();
        total();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jTable1.getModel();
        
        idmenu.setText(model.getValueAt(jTable1.getSelectedRow(),0).toString());
        namamenu.setText(model.getValueAt(jTable1.getSelectedRow(),1).toString());
        txt_harga.setText(model.getValueAt(jTable1.getSelectedRow(),2).toString());
        txt_jumlah.setText(model.getValueAt(jTable1.getSelectedRow(),3).toString());
        txt_subTtl.setText(model.getValueAt(jTable1.getSelectedRow(),4).toString());
                      
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO penjualan (nota_penjualan) VALUES ('"+txt_nota.getText()+"')";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
       
                       

    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyReleased
        // TODO add your handling code here:
         int qty = Integer.parseInt(txt_jumlah.getText());
        try {
            String sql = "SELECT stok FROM menu WHERE id_menu='"+idmenu.getText() +"' OR nama_menu LIKE '%"+idmenu.getText() +"%'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            
            if(rs.next()){
                int stok = rs.getInt(1);
                if(stok<qty){
                JOptionPane.showMessageDialog(null, "Maaf Stok Tidak Mencukupi");
                kosong();
                }
            }
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        hitung();
    }//GEN-LAST:event_txt_jumlahKeyReleased

    private void Btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_editActionPerformed
        // TODO add your handling code here:
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getSelectedRow() == -1){
            if (jTable1.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Tidak Ada Data Dalam Tabel");
            }else{
            JOptionPane.showMessageDialog(null, "Pilih Data Yang Akan Diubah");
            }
        }else{
        model.setValueAt(idmenu.getText(), jTable1.getSelectedRow(),0);
        model.setValueAt(namamenu.getText(), jTable1.getSelectedRow(),1);
        model.setValueAt(txt_jumlah.getText(), jTable1.getSelectedRow(),3);
        model.setValueAt(txt_harga.getText(),jTable1.getSelectedRow(),2);
        model.setValueAt(txt_subTtl.getText(), jTable1.getSelectedRow(),4);
        }
        try {
            String sql = "UPDATE detail_penjualan SET jumlah= "+txt_jumlah.getText()+", "
                    + "sub_total ="+txt_subTtl.getText()+" WHERE nota_penjualan = '"+txt_nota.getText()
                    +"' AND id_menu = '"+idmenu.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosong();
        total();
    }//GEN-LAST:event_Btn_editActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
       kosong();

    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getSelectedRow() == -1){
            if (jTable1.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Tidak Ada Data Dalam Tabel");
            }else{
            JOptionPane.showMessageDialog(null, "Pilih Data Yang Akan Dihapus");
            }
        }else{
        model.removeRow(jTable1.getSelectedRow());
        }
        try {
            String sql = "Delete from detail_penjualan WHERE id_menu = '"+idmenu.getText()+"'AND nota_penjualan='"+txt_nota.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosong();
        total();

    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bayarActionPerformed
        // TODO add your handling code here:
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(txt_tglpenjualan.getDate()));
        try {
            String sql = "UPDATE penjualan set username = '"+txt_user.getText()+"', grand_total =" 
                    +gtotal.getText()+", tgl_penjualan = '"+tgl+"'"+"WHERE nota_penjualan ='"
                    + txt_nota.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.setVisible (false); 
	new Pembayaran_penjualan(username).setVisible(true);
        auto_code();

    }//GEN-LAST:event_btn_bayarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         this.setVisible (false); 
	new Menu(username).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Menu(username).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Transaksi_pemesanan_admin(username).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Laporan(username).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

 
     
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
            java.util.logging.Logger.getLogger(transaksi_jual_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi_jual_admin(username).setVisible(true);
            }
        });
    }
        int baris = 0;
        static Object kolom[]={"id menu","Nama Menu","Harga","Jumlah","Sub Total"};
        DefaultTableModel model = new DefaultTableModel(kolom,baris);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_edit;
    private javax.swing.JButton btn_bayar;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JTextField cari;
    public static final javax.swing.JTextField gtotal = new javax.swing.JTextField();
    private javax.swing.JTextField idmenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField namamenu;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kasir;
    public static final javax.swing.JTextField txt_nota = new javax.swing.JTextField();
    private javax.swing.JTextField txt_subTtl;
    public static final com.toedter.calendar.JDateChooser txt_tglpenjualan = new com.toedter.calendar.JDateChooser();
    // End of variables declaration//GEN-END:variables
}
