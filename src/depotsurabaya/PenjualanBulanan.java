/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depotsurabaya;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lenovo
 */
public class PenjualanBulanan extends javax.swing.JFrame {

    static String username;
    public PenjualanBulanan(String username) {
        initComponents();
        this.username = username;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exit = new javax.swing.JButton();
        txt_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_total = new javax.swing.JTextField();
        txt_tahun = new com.toedter.calendar.JYearChooser();
        txt_bulan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 40, 40));

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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 110, 30));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 130));

        txt_total.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 200, 40));
        getContentPane().add(txt_tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 220, 110, 30));

        txt_bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        getContentPane().add(txt_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\RIWAYAT PENJUALAN BULANAN (2).png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        this.setVisible (false); 
	new RiwayatJual(username).setVisible(true);
    }//GEN-LAST:event_exitActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
    model.addColumn("No");
    model.addColumn("Nota Penjualan");
    model.addColumn("Nama Menu");
    model.addColumn("Jumlah");
    model.addColumn("Harga");
    model.addColumn("Sub Total");
    }//GEN-LAST:event_jTable1MouseClicked
 
    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
 int totalGrandTotal = 0;
 
        DefaultTableModel model = new DefaultTableModel();
    model.addColumn("No");
    model.addColumn("Nota Penjualan");
    model.addColumn("Nama Menu");
    model.addColumn("Jumlah");
    model.addColumn("Harga");
    model.addColumn("Sub Total");
    

 
        try {
    int no = 1;
        String sql = "select penjualan.nota_penjualan, menu.nama_menu, detail_penjualan.jumlah, menu.harga, detail_penjualan.sub_total "
                + "from penjualan join detail_penjualan on penjualan.nota_penjualan = detail_penjualan.nota_penjualan "
                + " join menu on menu.id_menu=detail_penjualan.id_menu "
                + " where month(tgl_penjualan)=' "+txt_bulan.getSelectedItem()+"'and year(tgl_penjualan)='"+txt_tahun.getYear()+"'" + "order by                            penjualan.nota_penjualan";
       java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet res = stm.executeQuery(sql);
        while (res.next()){
            model.addRow(new Object[]{no++, res.getString(1), 
                res.getString(2), res.getString(3),res.getString(4),
                res.getString(5)});
           totalGrandTotal += Integer.parseInt(res.getString(5));
        }
        System.out.print("Total grand = " + totalGrandTotal);
        txt_total.setText(GetkonversiRupiah(String.valueOf(totalGrandTotal)));
        jTable1.setModel(model);
    } catch (SQLException e){
      JOptionPane.showMessageDialog(this, e);   
    }
       
           
    }//GEN-LAST:event_txt_cariActionPerformed
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
    private void total(){

        try {
            String sql = "SELECT SUM(sub_total) FROM penjualan join detail_penjualan "
                    + "on penjualan.nota_penjualan=detail_penjualan.nota_penjualan WHERE tgl_penjualan='"+txt_bulan.            getSelectedItem()+"'and year(tgl_penjualan)='"+txt_tahun.getYear()  +"'";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            
            if(rs.next()){
                txt_total.setText(rs.getString(1));
                
            }
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
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
            java.util.logging.Logger.getLogger(PenjualanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenjualanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenjualanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenjualanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenjualanBulanan(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> txt_bulan;
    private javax.swing.JButton txt_cari;
    private com.toedter.calendar.JYearChooser txt_tahun;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}