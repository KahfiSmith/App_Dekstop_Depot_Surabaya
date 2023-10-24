/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depotsurabaya;

import static depotsurabaya.Transaksi_Pesanan_Adm.txt_nota;
import static depotsurabaya.pemesan.id_pembeli;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
public class Pembayaran_pemesanan_Admin extends javax.swing.JFrame {

    static String username;
    public Pembayaran_pemesanan_Admin(String username) {
        initComponents();
        muncul();
        noedit();
        pnj();
        nama_pembeli();
        this.username = username;
    }

     private void nama_pembeli() {
         try{
         String sql = "SELECT nama_pembeli FROM pembeli WHERE id_pembeli='"+id_pembeli.getText()+"'";
     java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()){
            txt_namaPembeli.setText(rs.getString(1));
            }else {
                JOptionPane.showMessageDialog(null, sql);
            }
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    txt_namaPembeli.setEditable(false);
    }
     
     
     private void muncul(){
    txt_nota1.setText(txt_nota.getText());
    
    }
     
     private void kembalian(){
         
    int gtotal = Integer.parseInt(txt_total.getText());
    int bayar = Integer.parseInt(txt_bayar.getText());
   
    int kembali  = bayar - gtotal;
    txt_kembali.setText(String.valueOf(kembali));
    }
     
    private void noedit(){
    txt_nota1.setEditable(false);
    tgl_pesan.setEditable(false);
    tgl_ambil.setEditable(false);
    txt_total.setEditable(false);    
    }
    
    private void pnj(){
    try{
        String sql = "SELECT sum(detail_pemesanan.sub_total), pemesanan.tgl_pemesanan,"
                + " pemesanan.tgl_pengambilan_pesanan "
                + " FROM pemesanan join detail_pemesanan "
                + " on pemesanan.nota_pemesanan = detail_pemesanan.nota_pemesanan"
                + " WHERE pemesanan.nota_pemesanan='"+txt_nota1.getText() +"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()){
            txt_total.setText(rs.getString(1));
            tgl_pesan.setText(rs.getString(2));
            tgl_ambil.setText(rs.getString(3));
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
        txt_nota1 = new javax.swing.JTextField();
        txt_namaPembeli = new javax.swing.JTextField();
        tgl_pesan = new javax.swing.JTextField();
        tgl_ambil = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        txt_bayar = new javax.swing.JTextField();
        txt_kembali = new javax.swing.JTextField();
        lunas = new javax.swing.JRadioButton();
        blm_lunas = new javax.swing.JRadioButton();
        Btn_simpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txt_nota1.setBorder(null);
        txt_nota1.setOpaque(false);
        getContentPane().add(txt_nota1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 88, 160, 24));

        txt_namaPembeli.setBorder(null);
        txt_namaPembeli.setOpaque(false);
        getContentPane().add(txt_namaPembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 137, 160, 23));

        tgl_pesan.setBorder(null);
        tgl_pesan.setOpaque(false);
        getContentPane().add(tgl_pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 160, 23));

        tgl_ambil.setBorder(null);
        tgl_ambil.setOpaque(false);
        getContentPane().add(tgl_ambil, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 238, 160, 24));

        txt_total.setBorder(null);
        txt_total.setOpaque(false);
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 286, 160, 24));

        txt_bayar.setBorder(null);
        txt_bayar.setOpaque(false);
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 160, 24));

        txt_kembali.setBorder(null);
        txt_kembali.setOpaque(false);
        getContentPane().add(txt_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 438, 160, 23));

        lunas.setText("LUNAS");
        lunas.setBorder(null);
        lunas.setContentAreaFilled(false);
        getContentPane().add(lunas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, -1, -1));

        blm_lunas.setText("BELUM LUNAS");
        blm_lunas.setBorder(null);
        blm_lunas.setContentAreaFilled(false);
        getContentPane().add(blm_lunas, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 400, -1, -1));

        Btn_simpan.setBorder(null);
        Btn_simpan.setContentAreaFilled(false);
        Btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(Btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 485, 115, 24));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 18, 39, 38));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\PEMBAYARAN PEMESANAN.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        kembalian();
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void Btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_simpanActionPerformed
        // TODO add your handling code here:
        String statuso = null;
        if (lunas.isSelected()){
            statuso = "lunas";
        }else if(blm_lunas.isSelected()){
            statuso = "belum lunas";
        }   
        try {
            String sql = "UPDATE pemesanan "
                        
                        +"set id_pembeli = '"+id_pembeli.getText()
                        +"', bayar = '"+txt_bayar.getText()
                        +"', kembalian = '"+txt_kembali.getText()
                        +"', keterangan = '"+statuso
                        +"' "+"WHERE nota_pemesanan = '"
                        + txt_nota1.getText()+"'";
            java.sql.Connection conn =(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            //Statement stm = conn.createStatement();          
        try {
              String NamaFile = "C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\DepotSurabaya\\src\\depotsurabaya\\NotaPemesanan.jasper";
                      

            HashMap parameter = new HashMap () ;
            parameter.put ("nota_pesanan", txt_nota1.getText()) ;

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

    }//GEN-LAST:event_Btn_simpanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Transaksi_pemesanan_admin(username).setVisible(true);
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
            java.util.logging.Logger.getLogger(Pembayaran_pemesanan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembayaran_pemesanan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembayaran_pemesanan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembayaran_pemesanan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembayaran_pemesanan_Admin(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_simpan;
    private javax.swing.JRadioButton blm_lunas;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton lunas;
    private javax.swing.JTextField tgl_ambil;
    private javax.swing.JTextField tgl_pesan;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_kembali;
    private javax.swing.JTextField txt_namaPembeli;
    private javax.swing.JTextField txt_nota1;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
