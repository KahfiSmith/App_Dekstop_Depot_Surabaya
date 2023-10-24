package depotsurabaya;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class RiwayatPesanan extends javax.swing.JFrame {
    static String username;
    public RiwayatPesanan(String username) {
        initComponents();
        getDataComboBox();
        this.username=username;
    }

    public void getDataComboBox() {
        DefaultComboBoxModel modelComboBOx = new DefaultComboBoxModel();

        String queryEtalaseItem = "SELECT  * FROM `detail_pemesanan`";
        try {
            java.sql.Connection conn = (Connection) DepotSurabaya.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(queryEtalaseItem);
            java.sql.ResultSet rs = pst.executeQuery(queryEtalaseItem);
            while (rs.next()) {
                modelComboBOx.addElement(rs.getString("nota_pemesanan"));
            }
        } catch (Exception e) {
        }
        this.jComboBox1.setModel(modelComboBOx);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txt_cari = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBorder(null);
        jTextField1.setBorder(null);
        jTextField1.setOpaque(false);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 199, 177, 32));

        jTextField2.setBorder(null);
        jTextField2.setBorder(null);
        jTextField2.setOpaque(false);
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 259, 177, 33));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 580, 190));

        txt_cari.setOpaque(false);
        txt_cari.setContentAreaFilled(false);
        txt_cari.setBorderPainted(false);
        txt_cari.setBorder(null);
        txt_cari.setContentAreaFilled(false);
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 110, 30));

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
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 40, 40));

        jComboBox1.setBorder(null);
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 190, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\RIWAYAT PESANAN (3).png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Laporan(username).setVisible(true);
    }//GEN-LAST:event_exitActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jComboBox1ActionPerformed
int no = 1;

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
   
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:]'wc
        System.out.println(String.valueOf(evt.getItem()));
                 System.out.println("hello");
        try {
            // TODO add your handling code here:
        String query = "SELECT * FROM pemesanan JOIN detail_pemesanan ON pemesanan.nota_pemesanan = detail_pemesanan.nota_pemesanan JOIN menu ON detail_pemesanan.id_menu = menu.id_menu WHERE pemesanan.nota_pemesanan = '"+ 
                    String.valueOf(evt.getItem()) + "';";
            System.out.println(query);
            DefaultTableModel tdm = new DefaultTableModel();
            tdm.addColumn("No");
            tdm.addColumn("Nota Pemesanan");
            tdm.addColumn("Nama Menu");
            tdm.addColumn("Jumlah");
            tdm.addColumn("Harga");
            tdm.addColumn("Sub Total");
            tdm.addColumn("Keterangan");
            tdm.addColumn("Status Pengambilan");
        //tdm.addColumn("Stock");
        
            java.sql.Connection connection = (Connection) DepotSurabaya.configDB();
            java.sql.PreparedStatement prs = connection.prepareStatement(query);
            java.sql.ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                System.out.println("sub total" + rs.getString("sub_total"));
                System.out.println("nota pemesanan" + rs.getString("nota_pemesanan"));
                this.jTextField1.setText(rs.getString("tgl_pemesanan"));
                this.jTextField2.setText(rs.getString("tgl_pengambilan_pesanan"));
                
                tdm.addRow(new Object[]{
                    no++,
                    rs.getString("nota_pemesanan"),
                    rs.getString("nama_menu"),
                    rs.getString("qty"),
                    rs.getString("harga"),
                    rs.getString("sub_total"),
                    rs.getString("keterangan"),
                    rs.getString("status_pengambilan")
                });
            }
            this.jTable2.setModel(tdm);
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

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
            java.util.logging.Logger.getLogger(RiwayatPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiwayatPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiwayatPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiwayatPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiwayatPesanan(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton txt_cari;
    // End of variables declaration//GEN-END:variables
}
