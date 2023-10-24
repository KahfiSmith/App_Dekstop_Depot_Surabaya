/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depotsurabaya;

import static depotsurabaya.DATA_PEMESAN_KRY.id_pembeli;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class DATA_PEMESAN_KRY extends javax.swing.JFrame {

    static String Username;
    public DATA_PEMESAN_KRY(String username) {
        initComponents();
         auto_code();
         kosong();
        this.Username = username;
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
                DATA_PEMESAN_KRY.id_pembeli.setText(b);
                DATA_PEMESAN_KRY.id_pembeli.setEditable(false);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        txt_nama = new javax.swing.JTextField();
        txt_notlp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Btn_simpan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 170, 90));

        id_pembeli.setBorder(null);
        id_pembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_pembeliActionPerformed(evt);
            }
        });
        jPanel1.add(id_pembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 154, 30));

        txt_nama.setBorder(null);
        jPanel1.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 155, 30));

        txt_notlp.setBorder(null);
        jPanel1.add(txt_notlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 373, 154, 30));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 437, 70, 29));

        Btn_simpan.setBorder(null);
        Btn_simpan.setContentAreaFilled(false);
        Btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 437, 70, 29));

        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(843, 20, 40, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\DATA PEMESAN (1).png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_simpanActionPerformed
        // TODO add your handling code here:
        try {
            String sql =  "insert into pembeli values ('" + id_pembeli.getText()+"','"+txt_nama.getText()+"',"
               + "'"+txt_alamat.getText()+"',"
               + "'"+txt_notlp.getText()+"')"; 
            java.sql.Connection conn=(Connection)DepotSurabaya.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil menambahakan data pemesan!");
            this.setVisible(false);
            new Transaksi_Pesanan_Kry(Username).setVisible(true);        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_Btn_simpanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new PESANAN_KRY(Username).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       kosong();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void id_pembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_pembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_pembeliActionPerformed

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
            java.util.logging.Logger.getLogger(DATA_PEMESAN_KRY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DATA_PEMESAN_KRY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DATA_PEMESAN_KRY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DATA_PEMESAN_KRY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DATA_PEMESAN_KRY(Username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_simpan;
    public static final javax.swing.JTextField id_pembeli = new javax.swing.JTextField();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_notlp;
    // End of variables declaration//GEN-END:variables
}
