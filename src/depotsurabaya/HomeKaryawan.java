/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package depotsurabaya;

import static depotsurabaya.Login.txt_pass;
import static depotsurabaya.Login.txt_user;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class HomeKaryawan extends javax.swing.JFrame  implements Runnable {

    Thread T;
    boolean kanan = true;
    boolean kiri = false;
    boolean berjalan = true;
    int x, y;
    
    static String Username;
    public HomeKaryawan(String username) {
        initComponents();
        this.Username = username;
        setNama(username);
         this.setTitle("Welcome to Depot Surabaya");
        this.setLocationRelativeTo(this);
        x = jLabel3.getX();
        y = jLabel3.getY();
        
        T = new Thread(this);
        T.start();
    }

    @Override
    public void run(){
      while (true){
          if(berjalan){
              if(x >= jPanel2.getWidth()-0){
                  kiri = true;
                  kanan = false;
              }
          if(kiri){
              x -- ;
              jLabel3.setLocation(x,y);
          }
          if(x<=-550) {
              kanan = true;
              kiri = false;
          }
          if(kanan){
              x ++ ;
              jLabel3.setLocation(x,y);
          }
    }
    try{
        Thread.sleep(10);
    }catch (InterruptedException ex){
        Logger.getLogger(HomeKaryawan.class.getName()).log(Level.SEVERE,null, ex);
    }
    repaint();
    }
}
    
    public void setNama(String user){
        namahomeuser.setText(user);
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
        jLabel2 = new javax.swing.JLabel();
        btn_jual_kry = new javax.swing.JButton();
        btn_pesan_kry = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        namahomeuser = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setText("Karyawan,");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, 40));

        btn_jual_kry.setBorder(null);
        btn_jual_kry.setContentAreaFilled(false);
        btn_jual_kry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jual_kryActionPerformed(evt);
            }
        });
        jPanel1.add(btn_jual_kry, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 100, 30));

        btn_pesan_kry.setBorder(null);
        btn_pesan_kry.setContentAreaFilled(false);
        btn_pesan_kry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesan_kryActionPerformed(evt);
            }
        });
        jPanel1.add(btn_pesan_kry, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 90, 30));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 100, 30));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Jl.Raya Pemogan no 24, Denpasar Selatan, Denpasar, bali");
        jPanel2.add(jLabel3);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 417, 360, 30));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 18, 40, 40));

        namahomeuser.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jPanel1.add(namahomeuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 390, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\DASHBOARD KARYAWAN (2).png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_jual_kryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jual_kryActionPerformed
        // TODO add your handling code here:
        this.setVisible (false); 
	new Transaksi_jual_kry(Username).setVisible(true);
    }//GEN-LAST:event_btn_jual_kryActionPerformed

    private void btn_pesan_kryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesan_kryActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new PESANAN_KRY(Username).setVisible(true);
    }//GEN-LAST:event_btn_pesan_kryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible (false); 
	new HomeKaryawan(Username).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int respons = JOptionPane.showConfirmDialog(this, "Yakin Ingin Keluar ?", "Keluar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(respons==JOptionPane.YES_OPTION) {
        this.setVisible(false);
        new HalamanUtama().setVisible(true);
        }
        txt_user.setText(null);
        txt_pass.setText(null);
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(HomeKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new HomeKaryawan(Username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_jual_kry;
    private javax.swing.JButton btn_pesan_kry;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel namahomeuser;
    // End of variables declaration//GEN-END:variables
}
