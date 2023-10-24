/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package depotsurabaya;

import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.IBarcode;
import static depotsurabaya.HomeAdmin.username;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Menu extends javax.swing.JFrame {

   static String username;
    public Menu(String username) {
        initComponents();
      load_table();
        this.username = username;
        this.txt_idMenu1.setText(getrandomString(13));
        
    }
    
    
    
    private void ResetField (){
    
        txt_idMenu1.setText("");
        txt_namamenu.setText("");
        txt_jumlah.setText("");
        txt_harga.setText("");
        barcode.setIcon(null);
        btn_makanan.isSelected();
        btn_minuman.isSelected();
        this.txt_idMenu1.setText(getrandomString(13));
   }
    
    static String getrandomString(int panjangKarakter){
        List<Character> charTemp = new ArrayList<>();
        for(int i = 0; i < panjangKarakter; i++){
            charTemp.add((char)ThreadLocalRandom.current().nextInt(48,57));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < charTemp.size(); i++){
            sb.append(charTemp.get(i));
    }
        return sb.toString();
    }
    
    private void load_table() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("No");
    model.addColumn("Id Menu");
    model.addColumn("Nama Menu");
    model.addColumn("Harga");
    model.addColumn("Stok");
    model.addColumn("Jenis Menu");
    model.addColumn("Barcode");
    
  
    
    try {
        int no=1;
        String sql ="select * from menu join detail_menu on menu.id_jenis = detail_menu.id_jenis ";
         java.sql.Connection conn =(Connection)DepotSurabaya.configDB();
         java.sql.Statement stm=conn.createStatement();
         java.sql.ResultSet res=stm.executeQuery(sql);
         while (res.next()){
             model.addRow(new Object[] {no++,res.getString(1),
             res.getString(2),res.getString(3),res.getString(4),
             res.getString(8),res.getString(6)});
         }
         jTable1.setModel(model);
    }catch (Exception e){
    }
    
    }
    
    public void encodeBarcode(){
 
       EAN13 objEan = new EAN13();
       objEan.setData(this.txt_idMenu1.getText().toString());
       objEan.setUom(IBarcode.UOM_PIXEL);
       objEan.setX(3f);
       objEan.setY(175f);
       objEan.setTextFont(new Font("Arial", 0, 20));
       objEan.setLeftMargin(0f);
       objEan.setRightMargin(0f);
       objEan.setTopMargin(0f);
       objEan.setBottomMargin(0f);
       objEan.setResolution(72);
       objEan.setShowText(true);
       objEan.setTextMargin(6);
       objEan.setRotate(IBarcode.ROTATE_0);
        try {
            objEan.drawBarcode("C://drivers//" + this.txt_namamenu.getText().toString() + ".png");
            JOptionPane.showMessageDialog(this, "BARCODE BERHASIL.....");
            
        } catch (Exception ex) {
        }   
    }
   
  public void setUkuranBarCode(EAN13 mBarcode,float panjangBarcode , float tinggiBarcode){
            mBarcode.setX(panjangBarcode);
            mBarcode.setY(tinggiBarcode);
            
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_namamenu = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        btn_makanan = new javax.swing.JRadioButton();
        btn_minuman = new javax.swing.JRadioButton();
        barcode = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idMenu1.setBorder(null);
        txt_idMenu1.setOpaque(false);
        jPanel1.add(txt_idMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 31, 153, 30));

        txt_namamenu.setBorder(null);
        txt_namamenu.setOpaque(false);
        txt_namamenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namamenuActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namamenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 89, 153, 30));

        txt_jumlah.setBorder(null);
        txt_jumlah.setOpaque(false);
        jPanel1.add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 203, 153, 30));

        txt_harga.setBorder(null);
        txt_harga.setOpaque(false);
        jPanel1.add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 35, 154, 29));

        btn_makanan.setText("Makanan");
        btn_makanan.setBorder(null);
        btn_makanan.setOpaque(false);
        btn_makanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_makananActionPerformed(evt);
            }
        });
        jPanel1.add(btn_makanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, -1, -1));

        btn_minuman.setText("Minuman");
        btn_minuman.setBorder(null);
        btn_minuman.setOpaque(false);
        jPanel1.add(btn_minuman, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));
        jPanel1.add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 250, 130));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 264, 98, 29));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 263, 100, 29));

        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 261, 100, 30));

        jButton5.setBorder(null);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 261, 100, 30));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 670, 180));

        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 100, 30));

        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 60, 30));

        jButton8.setBorder(null);
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 100, 40));

        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 80, 30));

        jButton10.setBorder(null);
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 80, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\HALAMAN MENU (6).png")); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namamenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namamenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namamenuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String id_jenis = null;
        if (btn_makanan.isSelected()){
            id_jenis = "MK01";
        }
        else if (btn_minuman.isSelected()){
        id_jenis = "MN01";
    }      
        
        String hs = "C://drivers//" + this.txt_namamenu.getText().toString() + ".png";
        try {                    
       String sql = "insert into menu values ('" + txt_idMenu1.getText()+"','"+txt_namamenu.getText()+"',"
               + "'"+txt_harga.getText()+"','"+txt_jumlah.getText()+"','"+id_jenis+"','" + hs+"')";
       System.out.println(sql);
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);            
           pst.execute();          
           JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan !");
           encodeBarcode();
                 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        ResetField();
        load_table();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int baris = jTable1.rowAtPoint(evt.getPoint());
        if(jTable1.getValueAt(baris, 1) == null){
            txt_idMenu1.setText("");
        } else{
            txt_idMenu1.setText(jTable1.getValueAt(baris, 1).toString());
        }   
        if(jTable1.getValueAt(baris, 2) == null){
            txt_namamenu.setText("");
        } else{
            txt_namamenu.setText(jTable1.getValueAt(baris, 2).toString());
        }   
        if(jTable1.getValueAt(baris, 3) == null){
            txt_harga.setText("");
        } else{
            txt_harga.setText(jTable1.getValueAt(baris, 3).toString());
        } 
        if(jTable1.getValueAt(baris, 4) == null){
            txt_jumlah.setText("");
        } else{
            txt_jumlah.setText(jTable1.getValueAt(baris, 4).toString());
        } 
        if(jTable1.getValueAt(baris, 6)==null){
        
        } else {
        File fps = new File(jTable1.getValueAt(baris, 6).toString());
        try{
        Image mImage = ImageIO.read(fps);       
        ImageIcon mImageIc = new ImageIcon(mImage);
        mImage = mImageIc.getImage().getScaledInstance(barcode.getWidth(), barcode.getHeight(), 
                Image.SCALE_SMOOTH);
        this.barcode.setIcon(new ImageIcon(mImage));
        }
        catch(Exception e){
                
                }
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ResetField();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         this.setVisible (false); 
	new HomeAdmin(username).setVisible(true);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
          this.setVisible (false);
          new transaksi_jual_admin(username).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.setVisible (false);
        new Menu(username).setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int respons = JOptionPane.showConfirmDialog(this, "Yakin Ingin Menghapus ?", "Hapus Barang", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(respons==JOptionPane.YES_OPTION) {
        try {
           String sql= "DELETE FROM menu where id_menu ='"+txt_idMenu1.getText()+"'";
             java.sql.Connection conn =(Connection)DepotSurabaya.configDB();
             java.sql.PreparedStatement pst=conn.prepareStatement (sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di hapus");
       }catch (Exception e){
           JOptionPane.showMessageDialog(this, e.getMessage());
    }
        }
     load_table();
        ResetField();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
        String sql= "UPDATE menu SET "
             +" nama_menu='"+txt_namamenu.getText()
             +"',stok='"+txt_jumlah.getText()+"',harga='"+txt_harga.getText()
             +"' WHERE id_menu='"+ txt_idMenu1.getText()+"'";
             java.sql.Connection conn =(Connection)DepotSurabaya.configDB();
             java.sql.PreparedStatement pst=conn.prepareStatement (sql);
            pst.execute();          
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, "perubahan data gagal" 
                   +e.getMessage());
       }load_table();
        ResetField();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_makananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_makananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_makananActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Transaksi_pemesanan_admin(username).setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Laporan(username).setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcode;
    private javax.swing.JRadioButton btn_makanan;
    private javax.swing.JRadioButton btn_minuman;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_harga;
    public static final javax.swing.JTextField txt_idMenu1 = new javax.swing.JTextField();
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_namamenu;
    // End of variables declaration//GEN-END:variables
}
