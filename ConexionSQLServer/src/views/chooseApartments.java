/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.storedProcedures;
import conexionsqlserver.temporalVariables;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author JoséFrancisco
 */
public class chooseApartments extends javax.swing.JFrame {
    static ResultSet res;
    /**
     * Creates new form FormApartments
     */
    public chooseApartments() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        irZapateria = new javax.swing.JButton();
        irRopaAdultos = new javax.swing.JButton();
        irMuebleria = new javax.swing.JButton();
        irCosmeticos = new javax.swing.JButton();
        irBebes = new javax.swing.JButton();
        irDulceria = new javax.swing.JButton();
        irJugueteria = new javax.swing.JButton();
        irElectronica = new javax.swing.JButton();
        irLineaBlanca = new javax.swing.JButton();
        irAutos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        adminUsuarios = new javax.swing.JMenu();
        addUser = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        showUsers = new javax.swing.JMenuItem();
        adminCliente = new javax.swing.JMenu();
        btnAddCliente = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        bajaCte = new javax.swing.JMenuItem();
        closeSesion = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LIVERPOOLGAS");
        setExtendedState(1);
        setMinimumSize(new java.awt.Dimension(545, 370));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Selecciona el departamento");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(549, 40, 270, 30);

        irZapateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922306_shoes.png"))); // NOI18N
        irZapateria.setText("Zapatería");
        irZapateria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irZapateria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irZapateria);
        irZapateria.setBounds(625, 90, 140, 41);

        irRopaAdultos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922418_clothing_accesories_clothes_fabric-11.png"))); // NOI18N
        irRopaAdultos.setText("Ropa Adultos");
        irRopaAdultos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irRopaAdultos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irRopaAdultos);
        irRopaAdultos.setBounds(625, 150, 140, 41);

        irMuebleria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922682_bedroom.png"))); // NOI18N
        irMuebleria.setText("Mueblería");
        irMuebleria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irMuebleria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irMuebleria);
        irMuebleria.setBounds(625, 210, 140, 41);

        irCosmeticos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488921940_bag_icons-17.png"))); // NOI18N
        irCosmeticos.setText("Cosméticos");
        irCosmeticos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irCosmeticos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irCosmeticos);
        irCosmeticos.setBounds(625, 270, 140, 41);

        irBebes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922077_baby-boy.png"))); // NOI18N
        irBebes.setText("Niños y Bebés");
        irBebes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irBebes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irBebes);
        irBebes.setBounds(812, 90, 140, 41);

        irDulceria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922549_Candy.png"))); // NOI18N
        irDulceria.setText("Dulcería");
        irDulceria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irDulceria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        irDulceria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irDulceriaActionPerformed(evt);
            }
        });
        getContentPane().add(irDulceria);
        irDulceria.setBounds(812, 150, 140, 41);

        irJugueteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922613_package_toys.png"))); // NOI18N
        irJugueteria.setText("Juguetería");
        irJugueteria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irJugueteria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irJugueteria);
        irJugueteria.setBounds(812, 210, 140, 41);

        irElectronica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922207_kmixdocked_error.png"))); // NOI18N
        irElectronica.setText("Electrónica");
        irElectronica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irElectronica.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irElectronica);
        irElectronica.setBounds(438, 90, 140, 41);

        irLineaBlanca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922793_blender.png"))); // NOI18N
        irLineaBlanca.setText("Línea Blanca");
        irLineaBlanca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irLineaBlanca.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irLineaBlanca);
        irLineaBlanca.setBounds(438, 150, 140, 41);

        irAutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488922463_CarGrey.png"))); // NOI18N
        irAutos.setText("Autos");
        irAutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irAutos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(irAutos);
        irAutos.setBounds(438, 210, 140, 41);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screen Shot 2017-03-08 at 09.53.11.png"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1366, 734);

        adminUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488961304_kdmconfig.png"))); // NOI18N
        adminUsuarios.setText("Administracion de Usuarios");
        adminUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        addUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1485408397_Add-Male-User.png"))); // NOI18N
        addUser.setText("Agregar usuario");
        addUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserActionPerformed(evt);
            }
        });
        adminUsuarios.add(addUser);

        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminUsuarios.add(jSeparator1);

        showUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1485404206_user_male2.png"))); // NOI18N
        showUsers.setText("Mostrar usuarios");
        showUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showUsersActionPerformed(evt);
            }
        });
        adminUsuarios.add(showUsers);

        jMenuBar1.add(adminUsuarios);

        adminCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489002466_Client_Male_Light.png"))); // NOI18N
        adminCliente.setText("Clientes");
        adminCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490411964_sliders.png"))); // NOI18N
        btnAddCliente.setText("Administrar clientes");
        btnAddCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClienteActionPerformed(evt);
            }
        });
        adminCliente.add(btnAddCliente);
        adminCliente.add(jSeparator2);

        bajaCte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490752835_icon_5_thumb_down.png"))); // NOI18N
        bajaCte.setText("Clientes dados de baja");
        bajaCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaCteActionPerformed(evt);
            }
        });
        adminCliente.add(bajaCte);

        jMenuBar1.add(adminCliente);

        closeSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488961025_Exit_Arrow_Door_Signout_Out_Close.png"))); // NOI18N
        closeSesion.setText("Cerrar sesión");
        closeSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeSesion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        closeSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeSesionMouseClicked(evt);
            }
        });
        jMenuBar1.add(closeSesion);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void irDulceriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irDulceriaActionPerformed
        // TODO add your handling code here:
        this.dispose();
        App principal = new App();
        
        principal.setVisible(true);
        principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        temporalVariables.setDepartamento("Dulcería");
        
        int aux = 0;
        
        res = conexionsqlserver.ConnectionDB.Query("select * from departamento where nombre_departamento='"+temporalVariables.getDepartamento()+"'");
        try{
            
            while( res.next()){
                aux ++;
            }
            if( aux == 0 ){
                storedProcedures.addDepa(temporalVariables.getDepartamento());
                aux = 0;
            }
        }catch(SQLException e){}
        
    }//GEN-LAST:event_irDulceriaActionPerformed

    private void addUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserActionPerformed
        FormAddUser addUser = new FormAddUser();
        addUser.setVisible(true);
        addUser.setLocationRelativeTo(null);
    }//GEN-LAST:event_addUserActionPerformed

    private void showUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showUsersActionPerformed
        tableUsers tabla = new tableUsers();
        tabla.setVisible(true);
        tabla.setLocationRelativeTo(null);
        tabla.refresUsers();
    }//GEN-LAST:event_showUsersActionPerformed

    private void closeSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeSesionMouseClicked
        this.dispose();
        FormLogin login = new FormLogin();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }//GEN-LAST:event_closeSesionMouseClicked

    private void btnAddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClienteActionPerformed
        adminClient addCte = new adminClient();
        this.dispose();
        addCte.setVisible(true);
        addCte.setExtendedState(JFrame.MAXIMIZED_BOTH);
        addCte.showClient();
    }//GEN-LAST:event_btnAddClienteActionPerformed

    private void bajaCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaCteActionPerformed
        bajasCliente bCte = new bajasCliente();
        this.dispose();
        bCte.setVisible(true);
        bCte.setExtendedState(JFrame.MAXIMIZED_BOTH);
        bCte.showClient();
    }//GEN-LAST:event_bajaCteActionPerformed

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
            java.util.logging.Logger.getLogger(chooseApartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chooseApartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chooseApartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chooseApartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chooseApartments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addUser;
    private javax.swing.JMenu adminCliente;
    public javax.swing.JMenu adminUsuarios;
    private javax.swing.JMenuItem bajaCte;
    private javax.swing.JMenuItem btnAddCliente;
    private javax.swing.JMenu closeSesion;
    private javax.swing.JButton irAutos;
    private javax.swing.JButton irBebes;
    private javax.swing.JButton irCosmeticos;
    private javax.swing.JButton irDulceria;
    private javax.swing.JButton irElectronica;
    private javax.swing.JButton irJugueteria;
    private javax.swing.JButton irLineaBlanca;
    private javax.swing.JButton irMuebleria;
    private javax.swing.JButton irRopaAdultos;
    private javax.swing.JButton irZapateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JMenuItem showUsers;
    // End of variables declaration//GEN-END:variables
}
