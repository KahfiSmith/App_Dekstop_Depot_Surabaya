/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depotsurabaya;

import static depotsurabaya.HomeKaryawan.Username;
import static depotsurabaya.Transaksi_jual_kry.txt_nota;
import static depotsurabaya.Transaksi_jual_kry.txt_tgl;
import static depotsurabaya.Transaksi_jual_kry.gtotal;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
public class Pembayaran_penjualan_Kry extends javax.swing.JFrame {
    static String Username;
    public Pembayaran_penjualan_Kry(String Username) {
        initComponents();
        this.Username = Username;
        input();
        noedit();
        pnj();
    }    
    public final void input(){
    txt_nota2.setText(txt_nota.getText());
   
    }
     private void noedit(){
    txt_nota2.setEditable(false);
    txt_tgl2.setEditable(false);
    txt_total2.setEditable(false);
    } 
     private void kembalian(){    
    int gtotal = Integer.parseInt(txt_total2.getText());
    int bayar = Integer.parseInt(txt_bayar.getText());
   
    int kembali  = bayar - gtotal;
    txt_kembali.setText(String.valueOf(kembali));
    }
     
      private void pnj(){
    try{
        String sql = "SELECT sum(detail_penjualan.sub_total), penjualan.tgl_penjualan "
                + " FROM penjualan join detail_penjualan "
                + " on penjualan.nota_penjualan = detail_penjualan.nota_penjualan"
                + " WHERE penjualan.nota_penjualan='"+txt_nota2.getText() +"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()){
            txt_total2.setText(rs.getString(1));
            txt_tgl2.setText(rs.getString(2));
            }else {
                JOptionPane.showMessageDialog(null, sql);
            }
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_nota2 = new javax.swing.JTextField();
        txt_tgl2 = new javax.swing.JTextField();
        txt_total2 = new javax.swing.JTextField();
        txt_bayar = new javax.swing.JTextField();
        txt_kembali = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txt_nota2.setBorder(null);
        txt_nota2.setOpaque(false);
        getContentPane().add(txt_nota2, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 122, 166, 26));

        txt_tgl2.setBorder(null);
        txt_tgl2.setOpaque(false);
        getContentPane().add(txt_tgl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 185, 166, 26));

        txt_total2.setBorder(null);
        txt_total2.setOpaque(false);
        getContentPane().add(txt_total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 249, 166, 25));

        txt_bayar.setBorder(null);
        txt_bayar.setOpaque(false);
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 304, 166, 25));

        txt_kembali.setBorder(null);
        txt_kembali.setOpaque(false);
        getContentPane().add(txt_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 359, 167, 24));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 420, 122, 27));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 20, 50, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\PEMBAYARAN PENJUALAN.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        kembalian();
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         int kembali = Integer.parseInt(txt_kembali.getText());
        
        if(kembali<0){
                JOptionPane.showMessageDialog(null, "Maaf Uang Pembayaran Kurang!");
                txt_bayar.setText("");
                txt_kembali.setText("");
            }else{
        try {
            String sql = "UPDATE penjualan SET Bayar ="+txt_bayar.getText()+ ", "
                    + "Kembalian= "+txt_kembali.getText()+ " WHERE nota_penjualan ='"+txt_nota2.getText()+"'";
            java.sql.Connection conn =(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            //Statement stm = conn.createStatement();          
            try {                
               String NamaFile = "C:\\Users\\LENOVO\\Documents\\NetBeansProjects"
                      + "\\DepotSurabaya\\src\\depotsurabaya\\reportnota.jasper";

            HashMap parameter = new HashMap () ;
            parameter.put ("nota_penjualan", txt_nota2.getText()) ;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost/house_food","root","");
            File file = new File (NamaFile) ;

            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter,con);

            JasperViewer.viewReport( jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                        "Data Tidak Dapat Dicetak"
            +"\n"+e.getMessage(), "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Transaksi_jual_kry(Username).setVisible(true);
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
            java.util.logging.Logger.getLogger(Pembayaran_penjualan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembayaran_penjualan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembayaran_penjualan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembayaran_penjualan_Kry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembayaran_penjualan_Kry(Username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_kembali;
    private javax.swing.JTextField txt_nota2;
    private javax.swing.JTextField txt_tgl2;
    private javax.swing.JTextField txt_total2;
    // End of variables declaration//GEN-END:variables
}
