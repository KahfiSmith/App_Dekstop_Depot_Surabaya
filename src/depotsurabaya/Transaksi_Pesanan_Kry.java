/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package depotsurabaya;

import static depotsurabaya.HomeAdmin.username;
import static depotsurabaya.Login.txt_user;
import static depotsurabaya.transaksi_jual_admin.listObj;
import static depotsurabaya.transaksi_jual_admin.username;
import java.awt.*;
import java.sql.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */


public class Transaksi_Pesanan_Kry extends javax.swing.JFrame {

   static String Username;
    public Transaksi_Pesanan_Kry(String username) {
        initComponents();
         this.setResizable(false);
        this.Username = username;
     
         Transaksi_Pesanan_Kry.cariProduk objProduk = new Transaksi_Pesanan_Kry.cariProduk();
        objProduk.TampilData();
        auto_code();
        nama_kasir();
       gkedit();
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
        String sql = "SELECT MAX(nota_pemesanan) as nota FROM pemesanan";
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
    public class cariProduk extends javax.swing.JFrame {
    DefaultTableModel tabModel;
    ResultSet RsProduk = null;
    
     void TampilData() {
         try {
            Object[] judul_kolom = {"Id Menu"};
            tabModel = new DefaultTableModel(null,judul_kolom);
            jTable1.setModel(tabModel);
            Connection conn = (Connection)DepotSurabaya.configDB();
            Statement stt = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            tabModel.addColumn("Nama Menu");
            jTable1.setModel(tabModel);
            Statement stt1 = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            tabModel.addColumn("Harga");
            jTable1.setModel(tabModel);
            Statement stt11 = conn.createStatement();
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
            jTable1.setModel(tabModel);      
        if(key != ""){ 
            Connection conn=(Connection)DepotSurabaya.configDB();
            Statement stt=conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            String query = "SELECT  * from menu WHERE nama_menu LIKE '%"+key+"%'";
            RsProduk=stt.executeQuery(query);  
            while(RsProduk.next()){
                Object[] data={   
                RsProduk.getString("id_menu"),
                RsProduk.getString("nama_menu"),
                RsProduk.getString("harga"),};
               tabModel.addRow(data);
               jTable1.setModel(tabModel);   
            }
            } else{
         Connection conn=(Connection)DepotSurabaya.configDB();
            Statement stt=conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            String query = "SELECT  * from menu WHERE nama_menu LIKE '%"+key+"%'";
            RsProduk=stt.executeQuery(query);  
                Object[] data={
                    "kosong"
                    };
            tabModel.addRow(data);
            jTable1.setModel(tabModel);   
        }                    
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
}
    private void total(){
     try {
            String sql = "SELECT SUM(sub_total) FROM detail_pemesanan WHERE nota_pemesanan='"
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
    
    private void hitung(){
    int harga = Integer.parseInt(txt_harga.getText());
    int qty = Integer.parseInt(txt_jumlah.getText());
    int subTtl  = harga * qty;
    txt_subTtl.setText(String.valueOf(subTtl));
    
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
            Logger.getLogger(Transaksi_jual_kry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_kasir = new javax.swing.JTextField();
        txt_tgl = new com.toedter.calendar.JDateChooser();
        txt_tgl1 = new com.toedter.calendar.JDateChooser();
        namamenu = new javax.swing.JTextField();
        txt_cari = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_subTtl = new javax.swing.JTextField();
        idmenu = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        gtotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btn_clear = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_byr = new javax.swing.JButton();
        btn_tmbh2 = new javax.swing.JButton();
        btn_tmbh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_kasir.setBorder(null);
        jPanel1.add(txt_kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 140, 20));

        txt_nota.setBorder(null);
        jPanel1.add(txt_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 22, 150, 20));
        jPanel1.add(txt_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 160, 30));
        jPanel1.add(txt_tgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 160, 30));

        namamenu.setBorder(null);
        namamenu.setBackground(new java.awt.Color(147, 238, 167));
        namamenu.setBorder(null);
        jPanel1.add(namamenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 47, 170, 20));

        txt_cari.setBorder(null);
        txt_cari.setBackground(new java.awt.Color(80, 181, 109));
        txt_cari.setBorder(null);
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        jPanel1.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 220, 24));

        txt_harga.setBorder(null);
        txt_harga.setBackground(new java.awt.Color(147, 238, 167));
        txt_harga.setBorder(null);
        jPanel1.add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 72, 170, 20));

        txt_subTtl.setBorder(null);
        txt_subTtl.setBackground(new java.awt.Color(147, 238, 167));
        txt_subTtl.setBorder(null);
        jPanel1.add(txt_subTtl, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 124, 170, 20));

        idmenu.setBorder(null);
        idmenu.setBackground(new java.awt.Color(147, 238, 167));
        idmenu.setBorder(null);
        jPanel1.add(idmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 21, 170, 20));

        txt_jumlah.setBorder(null);
        txt_jumlah.setBackground(new java.awt.Color(147, 238, 167));
        txt_jumlah.setBorder(null);
        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyReleased(evt);
            }
        });
        jPanel1.add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 98, 170, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 360, 200));

        gtotal.setBorder(null);
        jPanel1.add(gtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 437, 270, 30));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Menu", "Nama Menu", "Harga", "Jumlah", "Sub Taotall"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 350, 240));

        btn_clear.setBorder(null);
        btn_clear.setContentAreaFilled(false);
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        jPanel1.add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 483, 76, 20));

        btn_hapus.setBorder(null);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(617, 482, 75, 22));

        btn_edit.setBorder(null);
        btn_edit.setContentAreaFilled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(796, 481, 74, 21));

        btn_byr.setBorder(null);
        btn_byr.setContentAreaFilled(false);
        btn_byr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_byrActionPerformed(evt);
            }
        });
        jPanel1.add(btn_byr, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 482, 75, 21));

        btn_tmbh2.setBorder(null);
        btn_tmbh2.setContentAreaFilled(false);
        btn_tmbh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tmbh2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tmbh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 23, 23, 19));

        btn_tmbh.setBorder(null);
        btn_tmbh.setContentAreaFilled(false);
        btn_tmbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tmbhActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tmbh, new org.netbeans.lib.awtextra.AbsoluteConstraints(933, 123, 26, 20));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 150, 30));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 150, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\TRANSAKSI PEMESANAN  KARYAWAN.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tmbh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tmbh2ActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO pemesanan (nota_pemesanan) VALUES ('"+txt_nota.getText()+"')";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_tmbh2ActionPerformed

    private void btn_tmbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tmbhActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        if(idmenu.getText().trim().equals("") || txt_jumlah.getText().trim().equals("") 
                || namamenu.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Data Yang Dimasukkan Belum Lengkap");
        }else{
        model.addRow(new Object[]{idmenu.getText(),namamenu.getText(),txt_harga.getText(),
        txt_jumlah.getText(),txt_subTtl.getText()});
        }
        try {
            String sql = "INSERT INTO detail_pemesanan VALUES ('"+txt_nota.getText()+"','"
                    +idmenu.getText()+"',"+txt_subTtl.getText()+","+txt_jumlah.getText()+")";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosong();
        total();
    
    }//GEN-LAST:event_btn_tmbhActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          this.setVisible (false); 
	new HomeKaryawan(Username).setVisible(true);                                  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          this.setVisible (false); 
	new Transaksi_jual_kry(Username).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here: 
        int baris = jTable1.rowAtPoint(evt.getPoint());
        if(jTable1.getValueAt(baris, 1) == null){
            namamenu.setText("");
        } else{
            namamenu.setText(jTable1.getValueAt(baris, 1).toString());
        }   
        if(jTable1.getValueAt(baris, 2) == null){
            txt_harga.setText("");
        } else{
            txt_harga.setText(jTable1.getValueAt(baris, 2).toString());
        }   
        if(jTable1.getValueAt(baris, 0) == null){
            idmenu.setText("");
        } else{
            idmenu.setText(jTable1.getValueAt(baris, 0).toString());
        } 
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        idmenu.setText(model.getValueAt(jTable2.getSelectedRow(),0).toString());
        namamenu.setText(model.getValueAt(jTable2.getSelectedRow(),1).toString());
        txt_harga.setText(model.getValueAt(jTable2.getSelectedRow(),2).toString());
        txt_jumlah.setText(model.getValueAt(jTable2.getSelectedRow(),3).toString());
        txt_subTtl.setText(model.getValueAt(jTable2.getSelectedRow(),4).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        String key =txt_cari.getText();
    System.out.println(key);
        cariProduk objProd = new cariProduk();
    if (key!="") {
        objProd.cariData(key);
    } else {       
    }
    }//GEN-LAST:event_txt_cariKeyReleased

    private void txt_jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyReleased
        // TODO add your handling code here:
        hitung();
    }//GEN-LAST:event_txt_jumlahKeyReleased

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        if (jTable2.getSelectedRow() == -1){
            if (jTable2.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Tidak Ada Data Dalam Tabel");
            }else{
            JOptionPane.showMessageDialog(null, "Pilih Data Yang Akan Dihapus");
            }
        }else{
        model.removeRow(jTable2.getSelectedRow());
        }
        try {
            String sql = "Delete from detail_pemesanan WHERE id_menu = '"+idmenu.getText()+"'AND nota_pemesanan='"+txt_nota.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
             JOptionPane.showMessageDialog(null, "data berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosong();
        total();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        if (jTable2.getSelectedRow() == -1){
            if (jTable2.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Tidak Ada Data Dalam Tabel");
            }else{
            JOptionPane.showMessageDialog(null, "Pilih Data Yang Akan Diubah");
            }
        }else{
        model.setValueAt(idmenu.getText(), jTable2.getSelectedRow(),0);
        model.setValueAt(namamenu.getText(), jTable2.getSelectedRow(),1);
        model.setValueAt(txt_jumlah.getText(), jTable2.getSelectedRow(),3);
        model.setValueAt(txt_harga.getText(),jTable2.getSelectedRow(),2);
        model.setValueAt(txt_subTtl.getText(), jTable2.getSelectedRow(),4);
        }
        try {
            String sql = "UPDATE detail_pemesanan SET jumlah= "+txt_jumlah.getText()+", "
                    + "sub_total ="+txt_subTtl.getText()+" WHERE nota_pemesanan = '"+txt_nota.getText()
                    +"' AND id_menu = '"+idmenu.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosong();
        total();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_byrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_byrActionPerformed
        // TODO add your handling code here:
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(txt_tgl.getDate()));
        String tgla = String.valueOf(fm.format(txt_tgl1.getDate()));
        try {
            String sql = "UPDATE pemesanan set username = '"+txt_user.getText()+"', gtotal = '" 
                    +gtotal.getText()+"', tgl_pemesanan = '"+tgl+"', tgl_pengambilan_pesanan = '"+tgla+"' "+"WHERE nota_pemesanan = '"
                    + txt_nota.getText()+"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.setVisible(false);
        new Pembayaran_pemesanan_Kry(Username).setVisible(true);
        auto_code();
    }//GEN-LAST:event_btn_byrActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi_Pesanan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi_Pesanan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi_Pesanan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi_Pesanan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi_Pesanan_Kry(Username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_byr;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tmbh;
    private javax.swing.JButton btn_tmbh2;
    private javax.swing.JTextField gtotal;
    private javax.swing.JTextField idmenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField namamenu;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kasir;
    public static final javax.swing.JTextField txt_nota = new javax.swing.JTextField();
    private javax.swing.JTextField txt_subTtl;
    private com.toedter.calendar.JDateChooser txt_tgl;
    private com.toedter.calendar.JDateChooser txt_tgl1;
    // End of variables declaration//GEN-END:variables
}
