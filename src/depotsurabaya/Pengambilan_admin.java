/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depotsurabaya;

import static depotsurabaya.Transaksi_Pesanan_Adm.username;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Pengambilan_admin extends javax.swing.JFrame {

    static String username;
    public Pengambilan_admin(String username) {
        initComponents();
        tampilnota();
        this.username =username;
        kosong();
    }
     public void tampilnota() {
        
        nota_tnt.removeAllItems();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
        nota_tnt.addItem("- Pilih -");

        try {
            
            String sql = "SELECT nota_pemesanan from pemesanan ";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                nota_tnt.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
             
        }
    }

private void kembalian(){
         
    int gtotal = Integer.parseInt(bayar.getText());
    int bayar = Integer.parseInt(txt_bayar.getText());
   
    int kembali  = bayar - gtotal;
    txt_kembali.setText(String.valueOf(kembali));
    }

private void kosong() {
    nama.setText(null);
    alamat.setText(null);
    no.setText(null);
    tgl.setText(null);
    tgl1.setText(null);
    status.setText(null);
    total.setText(null);
    bayar.setText(null);
    kurang.setText(null);
    txt_bayar.setText(null);
    txt_kembali.setText(null);
    sudah.setText("sudah diambil");
    nota_tnt.addItem("- Pilih -");
 
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nota_tnt = new javax.swing.JComboBox<>();
        sudah = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        bayar = new javax.swing.JTextField();
        no = new javax.swing.JTextField();
        tgl = new javax.swing.JTextField();
        tgl1 = new javax.swing.JTextField();
        status = new javax.swing.JTextField();
        kurang = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txt_bayar = new javax.swing.JTextField();
        txt_kembali = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nota_tnt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        nota_tnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nota_tntActionPerformed(evt);
            }
        });
        nota_tnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nota_tntKeyReleased(evt);
            }
        });
        getContentPane().add(nota_tnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 20, 158, 30));

        sudah.setText("sudah diambil");
        sudah.setBorder(null);
        sudah.setContentAreaFilled(false);
        getContentPane().add(sudah, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 120, 20));

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane1.setViewportView(alamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 150, 60));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        nama.setBorder(null);
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 150, 26));

        total.setBorder(null);
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 307, 150, 26));

        bayar.setBorder(null);
        getContentPane().add(bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 350, 153, 28));

        no.setBorder(null);
        getContentPane().add(no, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 198, 150, 26));

        tgl.setBorder(null);
        getContentPane().add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 80, 150, 26));

        tgl1.setBorder(null);
        getContentPane().add(tgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 142, 150, 26));

        status.setBorder(null);
        getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 198, 150, 26));

        kurang.setBorder(null);
        getContentPane().add(kurang, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 394, 154, 28));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 487, 120, 29));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 487, 120, 29));

        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 40, 40));

        txt_bayar.setBorder(null);
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 153, 28));

        txt_kembali.setBorder(null);
        txt_kembali.setOpaque(false);
        getContentPane().add(txt_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 394, 154, 29));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\PENGAMBILAN PESANAN (4).png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nota_tntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nota_tntActionPerformed
        // TODO add your handling code here:
       try
            {
            String querySelect ="select nama_pembeli, alamat_pembeli, no_telp, gtotal, bayar, kembalian, tgl_pemesanan, tgl_pengambilan_pesanan, keterangan, status_pengambilan from pemesanan join pembeli on pembeli.id_pembeli = pemesanan.id_pembeli where nota_pemesanan='"+nota_tnt.getSelectedItem()+"'";
            java.sql.Connection con = (Connection) DepotSurabaya.configDB();
            java.sql.PreparedStatement prp = con.prepareStatement(querySelect);
            java.sql.ResultSet rs = prp.executeQuery();
            

                while(rs.next())
                {if(rs.getString("status_pengambilan").equals("sudah diambil")){
                        JOptionPane.showMessageDialog(null,"maaf pesanan anda sudah diambil");
                        kosong();
                    }else if(rs.getString("status_pengambilan").equals("belum")){
                    nama.setText(rs.getString(1));
                    alamat.setText(rs.getString(2));
                    no.setText(rs.getString(3));
                    total.setText(rs.getString(4));
                    bayar.setText(rs.getString(5));
                    kurang.setText(rs.getString(6));
                    tgl.setText(rs.getString(7));
                    tgl1.setText(rs.getString(8));
                    status.setText(rs.getString(9));

                }
                }
  
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"GAGAL" + e);
            }
    }//GEN-LAST:event_nota_tntActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String stat = null;
        if (sudah.isSelected()){
            stat = "sudah diambil";
        }
            try {
                String sql = "UPDATE pemesanan "
                        
                        +"set gtotal = '"+total.getText()
                        +"', tgl_pemesanan = '"+tgl.getText()
                        +"', tgl_pengambilan_pesanan = '"+tgl1.getText()
                        +"', bayar = '"+txt_bayar.getText()
                        +"', kembalian = '"+txt_kembali.getText()
                        +"', status_pengambilan = '"+stat
                        +"', keterangan = '"+status.getText()
                        
                        +"' "+"WHERE nota_pemesanan = '"
                        + nota_tnt.getSelectedItem()+"'";
                
                java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
                JOptionPane.showMessageDialog(null, "Pesanan Berhasil diambil");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Transaksi_pemesanan_admin(username).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        kembalian();
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void nota_tntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nota_tntKeyReleased
        // TODO add your handling code here:
     
    }//GEN-LAST:event_nota_tntKeyReleased

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
            java.util.logging.Logger.getLogger(Pengambilan_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengambilan_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengambilan_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengambilan_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengambilan_admin(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JTextField bayar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kurang;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField no;
    private javax.swing.JComboBox<String> nota_tnt;
    private javax.swing.JTextField status;
    private javax.swing.JRadioButton sudah;
    private javax.swing.JTextField tgl;
    private javax.swing.JTextField tgl1;
    private javax.swing.JTextField total;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_kembali;
    // End of variables declaration//GEN-END:variables
}
