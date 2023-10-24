package depotsurabaya;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RiwayatJual extends javax.swing.JFrame {
    static String username;
    public RiwayatJual(String username) {
        initComponents();
        this.username = username;
    }

    public void getTotalHari() {
        String sqlx = "SELECT * FROM  penjualan WHERE nota_penjualan";
    }

    static String getPosisiTerakhirDataBarang() {
        ArrayList<String> data = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM penjualan ORDER BY nota_penjualan";
            java.sql.Connection conn = (Connection) DepotSurabaya.configDB();
            java.sql.PreparedStatement prs = conn.prepareStatement(sqlQuery);
            java.sql.ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                data.add(rs.getString("nota_penjualan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.get(0);
    }
    int nPosisi;
    int totalHargaJual;
    float hari;

    public void mendapatHargaBeliTotal() {
        String gethargabeli = "SELECT SUM(grand_total) AS totalharian FROM penjualan WHERE DAY(tgl_penjualan) = DAY(now())";
        try {
            java.sql.Connection conn = (Connection) DepotSurabaya.configDB();
            java.sql.PreparedStatement prs = conn.prepareStatement(gethargabeli);
            java.sql.ResultSet rs = prs.executeQuery(gethargabeli);
            while (rs.next()) {
                System.out.println(rs.getString("totalHarian"));
                hari = Float.parseFloat(rs.getString("totalharian"));
                    
                totalHargaJual = (int) hari;
                jTextField1.setText(GetkonversiRupiah(String.valueOf(totalHargaJual)));

            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Maaf belum ada transaksi hari ini");   
        }
    }
    float bulan;

    public void mendapatkanHargaBeliTotalBulan() {
        String queryGetHargaBeli = "SELECT SUM(grand_total) AS totalPemasukanBulanan FROM `penjualan` WHERE MONTH(tgl_penjualan) = MONTH(now()) ";
        try {
            java.sql.Connection conn = (Connection) DepotSurabaya.configDB();
            java.sql.PreparedStatement prs = conn.prepareStatement(queryGetHargaBeli);
            java.sql.ResultSet rs = prs.executeQuery(queryGetHargaBeli);
            while (rs.next()) {
                bulan = Float.parseFloat(rs.getString("totalPemasukanBulanan"));
                totalHargaJual = (int) bulan;
                jTextField2.setText(GetkonversiRupiah(String.valueOf(totalHargaJual)));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Maaf belum ada transaksi hari ini");
        }
    }

    public String GetkonversiRupiah(String harga) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        String idr = kursIndonesia.format(Integer.parseInt(harga));
        //System.out.println(idr);
        //set text jLabel.setText(idr)
        return idr;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        perhari = new javax.swing.JButton();
        perbulan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBorder(null);
        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 190, 100));

        perhari.setOpaque(false);
        perhari.setContentAreaFilled(false);
        perhari.setBorderPainted(false);
        perhari.setBorder(null);
        perhari.setContentAreaFilled(false);
        perhari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perhariActionPerformed(evt);
            }
        });
        getContentPane().add(perhari, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 120, 40));

        perbulan.setOpaque(false);
        perbulan.setContentAreaFilled(false);
        perbulan.setBorderPainted(false);
        perbulan.setBorder(null);
        perbulan.setContentAreaFilled(false);
        perbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perbulanActionPerformed(evt);
            }
        });
        getContentPane().add(perbulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 120, 40));

        jButton3.setOpaque(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setBorderPainted(false);
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, 270, 40));

        jButton4.setOpaque(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setBorderPainted(false);
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 270, 40));

        jTextField2.setBorder(null);
        jTextField2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 208, 180, 100));

        exit .setOpaque(false);
        exit .setContentAreaFilled(false);
        exit .setBorderPainted(false);
        exit.setBorder(null);
        exit.setContentAreaFilled(false);
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 23, 39, 39));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\HALAMAN LAPORAN.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void perbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perbulanActionPerformed
        // TODO add your handling code here:
        mendapatkanHargaBeliTotalBulan();
    }//GEN-LAST:event_perbulanActionPerformed

    private void perhariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perhariActionPerformed
        // TODO add your handling code here:
        mendapatHargaBeliTotal();
    }//GEN-LAST:event_perhariActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible (false); 
	new PenjualanHarian(username).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         this.setVisible (false); 
	new PenjualanBulanan(username).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        this.setVisible (false); 
	new Laporan(username).setVisible(true); 
    }//GEN-LAST:event_exitActionPerformed

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
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiwayatJual(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton perbulan;
    private javax.swing.JButton perhari;
    // End of variables declaration//GEN-END:variables
}
