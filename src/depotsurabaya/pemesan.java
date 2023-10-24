/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depotsurabaya;


import java.sql.Connection;
import javax.swing.JOptionPane;


public class pemesan extends javax.swing.JFrame {

    static String username;
    public pemesan(String username) {
        initComponents();
        auto_code();
        this.username = username;
    }
public void auto_code(){
    try{
        String sql = "SELECT MAX(id_pembeli) as id FROM pembeli";
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while(rs.next()){
                Object[ ] obj = new Object[1];
                obj[0] = rs.getString("id");
                if(obj[0] == null){
                    obj[0] = "M0000";
                }
                String str_nt = (String) obj[0];
                String nt = str_nt.substring(1,5);//P0000
                int int_nota = Integer.parseInt(nt);
                int_nota++;
                String a = String.format("%04d", int_nota);
                String b = "R" + a;
                id_pembeli.setText(b);
                id_pembeli.setEditable(false);
            }
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
   private void kosong() {
    txt_nama.setText(null);
    txt_alamat.setText(null);
    txt_notlp.setText(null);

}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nama = new javax.swing.JTextField();
        txt_notlp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_pembeli.setBorder(null);
        getContentPane().add(id_pembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 147, 153, 28));

        txt_nama.setBorder(null);
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 206, 154, 28));

        txt_notlp.setBorder(null);
        getContentPane().add(txt_notlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 369, 153, 28));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 17, 38, 38));

        clear.setBorder(null);
        clear.setContentAreaFilled(false);
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        getContentPane().add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, 70, 30));

        simpan.setBorder(null);
        simpan.setContentAreaFilled(false);
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 430, 70, 30));

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 170, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\DATA PEMESAN (1).png")); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_clearActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        try {                    
       String sql = "insert into pembeli values ('" + id_pembeli.getText()+"','"+txt_nama.getText()+"',"
               + "'"+txt_alamat.getText()+"',"
               + "'"+txt_notlp.getText()+"')"; 
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);            
           pst.execute();          
           JOptionPane.showMessageDialog(null, "Berhasil menambahakan data pemesan !");
            this.setVisible(false);
            new Transaksi_Pesanan_Adm(username).setVisible(true);        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
       
    }//GEN-LAST:event_simpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Transaksi_pemesanan_admin(username).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(pemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pemesan(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    public static final javax.swing.JTextField id_pembeli = new javax.swing.JTextField();
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton simpan;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_notlp;
    // End of variables declaration//GEN-END:variables
}
