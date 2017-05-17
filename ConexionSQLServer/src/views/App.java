/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.Login;
import conexionsqlserver.temporalVariables;
import java.beans.PropertyVetoException;
import javax.swing.JFrame;

/**
 *
 * @author JoséFrancisco
 */
public class App extends javax.swing.JFrame {

    /**
     * Creates new form App
     */
    public App() {
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

        dpnEscritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        backHome = new javax.swing.JMenu();
        archivo = new javax.swing.JMenu();
        vta = new javax.swing.JMenu();
        ventaNueva = new javax.swing.JMenuItem();
        verVentas = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        adminProducto = new javax.swing.JMenu();
        adminProductos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        adminMarcas = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        irPromociones = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        adminProv = new javax.swing.JMenuItem();
        bajaProv = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        adminEmpleados = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        empleadosBajas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LIVERPOOLGAS");

        dpnEscritorio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout dpnEscritorioLayout = new javax.swing.GroupLayout(dpnEscritorio);
        dpnEscritorio.setLayout(dpnEscritorioLayout);
        dpnEscritorioLayout.setHorizontalGroup(
            dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
        );
        dpnEscritorioLayout.setVerticalGroup(
            dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        backHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489031663_Grid.png"))); // NOI18N
        backHome.setText("Home");
        backHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backHomeMouseClicked(evt);
            }
        });
        jMenuBar1.add(backHome);

        archivo.setBackground(new java.awt.Color(153, 153, 153));
        archivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488961501_document.png"))); // NOI18N
        archivo.setText("Archivo");
        archivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        vta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488968007_for_sale.png"))); // NOI18N
        vta.setText("Ventas");
        vta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ventaNueva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489001366_1.png"))); // NOI18N
        ventaNueva.setText("Venta nueva");
        ventaNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventaNuevaActionPerformed(evt);
            }
        });
        vta.add(ventaNueva);

        verVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488968007_for_sale.png"))); // NOI18N
        verVentas.setText("Mostrar Ventas");
        verVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verVentasActionPerformed(evt);
            }
        });
        vta.add(verVentas);

        archivo.add(vta);
        archivo.add(jSeparator2);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488968272_buy_16.png"))); // NOI18N
        jMenu2.setText("Compras");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488968582_add-buy-plus-shopping-cart.png"))); // NOI18N
        jMenuItem4.setText("Nueva");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1488968643_meanicons_57.png"))); // NOI18N
        jMenuItem5.setText("Modificar / Eliminar");
        jMenuItem5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.add(jMenuItem5);

        archivo.add(jMenu2);
        archivo.add(jSeparator3);

        adminProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489001176_benchmarking.png"))); // NOI18N
        adminProducto.setText("Productos");
        adminProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        adminProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490411964_sliders.png"))); // NOI18N
        adminProductos.setText("Administrar Productos");
        adminProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminProductosActionPerformed(evt);
            }
        });
        adminProducto.add(adminProductos);
        adminProducto.add(jSeparator1);

        adminMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490411964_sliders.png"))); // NOI18N
        adminMarcas.setText("Administrar Marcas");
        adminMarcas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminMarcasActionPerformed(evt);
            }
        });
        adminProducto.add(adminMarcas);

        archivo.add(adminProducto);
        archivo.add(jSeparator4);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489002846_interact.png"))); // NOI18N
        jMenuItem8.setText("Cambios");
        jMenuItem8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archivo.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489002930_go-back.png"))); // NOI18N
        jMenuItem9.setText("Devoluciones");
        jMenuItem9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archivo.add(jMenuItem9);
        archivo.add(jSeparator5);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489003165_documents-01.png"))); // NOI18N
        jMenuItem10.setText("Estado de Cuenta");
        jMenuItem10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archivo.add(jMenuItem10);
        archivo.add(jSeparator6);

        irPromociones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489003225_Technology_Mix_-_Final-17.png"))); // NOI18N
        irPromociones.setText("Promociones");
        irPromociones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irPromociones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irPromocionesActionPerformed(evt);
            }
        });
        archivo.add(irPromociones);

        jMenuBar1.add(archivo);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489002535_provider.png"))); // NOI18N
        jMenu5.setText("Admin. Proveedor");
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        adminProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490411964_sliders.png"))); // NOI18N
        adminProv.setText("Administrar proveedores");
        adminProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminProvActionPerformed(evt);
            }
        });
        jMenu5.add(adminProv);

        bajaProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490752835_icon_5_thumb_down.png"))); // NOI18N
        bajaProv.setText("Proveedores dados de baja");
        bajaProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaProvActionPerformed(evt);
            }
        });
        jMenu5.add(bajaProv);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1489002609_Profile.png"))); // NOI18N
        jMenu6.setText("Empleados");
        jMenu6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        adminEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490411964_sliders.png"))); // NOI18N
        adminEmpleados.setText("Administrar empleados");
        adminEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEmpleadosActionPerformed(evt);
            }
        });
        jMenu6.add(adminEmpleados);
        jMenu6.add(jSeparator7);

        empleadosBajas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490752835_icon_5_thumb_down.png"))); // NOI18N
        empleadosBajas.setText("Empleados dados de baja");
        empleadosBajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosBajasActionPerformed(evt);
            }
        });
        jMenu6.add(empleadosBajas);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpnEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpnEscritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backHomeMouseClicked
        this.dispose();
        
        chooseApartments choose = new chooseApartments();
        
        choose.setVisible(true);
        choose.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        Login log = new Login();
        String usuario = temporalVariables.getUsuario();
        String password = temporalVariables.getPassword();
        
        if( !( usuario.equals(log.user) || password.equals(log.password) ) ){
            choose.adminUsuarios.setVisible(false);
        }
        
    }//GEN-LAST:event_backHomeMouseClicked

    private void adminProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminProductosActionPerformed
        adminProductos prod = new adminProductos();
        dpnEscritorio.add(prod);
        prod.show();
        try {
            prod.setMaximum(true);
        } catch (PropertyVetoException ex) {
        }
    }//GEN-LAST:event_adminProductosActionPerformed

    private void adminMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminMarcasActionPerformed
        adminMarcas marca = new adminMarcas();
        dpnEscritorio.add(marca);
        marca.show();
    }//GEN-LAST:event_adminMarcasActionPerformed

    private void adminEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEmpleadosActionPerformed
        adminEmpleado emp = new adminEmpleado();
        this.dispose();
        emp.setVisible(true);
        emp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        emp.showEmpleado();
    }//GEN-LAST:event_adminEmpleadosActionPerformed

    private void empleadosBajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosBajasActionPerformed
        bajasEmpleados bEmp = new bajasEmpleados();
        this.dispose();
        bEmp.setVisible(true);
        bEmp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        bEmp.showEmpBajas();
    }//GEN-LAST:event_empleadosBajasActionPerformed

    private void ventaNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventaNuevaActionPerformed
        nuevaVenta vta = new nuevaVenta();
        
        dpnEscritorio.add(vta);
        vta.show();
        try {
            vta.setMaximum(true);
        } 
        catch (PropertyVetoException ex) {
        }
    }//GEN-LAST:event_ventaNuevaActionPerformed

    private void verVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verVentasActionPerformed
        mostrarVentas vta = new mostrarVentas();
        
        dpnEscritorio.add(vta);
        vta.show();
        vta.showVentasFolioASC();
        try {
            vta.setMaximum(true);
        } 
        catch (PropertyVetoException ex) {
        }
    }//GEN-LAST:event_verVentasActionPerformed

    private void adminProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminProvActionPerformed
        proveedores pro = new proveedores();
        this.dispose();
        pro.setVisible(true);
        pro.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pro.showProv();
    }//GEN-LAST:event_adminProvActionPerformed

    private void bajaProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaProvActionPerformed
        bajasProveedor bp = new bajasProveedor();
        this.dispose();
        bp.setVisible(true);
        bp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        bp.showProv();
    }//GEN-LAST:event_bajaProvActionPerformed

    private void irPromocionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irPromocionesActionPerformed
        Promociones promo = new Promociones();
        
        dpnEscritorio.add(promo);
        promo.show();
        try {
            promo.setMaximum(true);
            promo.bajaXFechaSistema();
        } 
        catch (PropertyVetoException ex) {
        }
    }//GEN-LAST:event_irPromocionesActionPerformed

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem adminEmpleados;
    private javax.swing.JMenuItem adminMarcas;
    private javax.swing.JMenu adminProducto;
    private javax.swing.JMenuItem adminProductos;
    private javax.swing.JMenuItem adminProv;
    private javax.swing.JMenu archivo;
    public javax.swing.JMenu backHome;
    private javax.swing.JMenuItem bajaProv;
    private javax.swing.JDesktopPane dpnEscritorio;
    private javax.swing.JMenuItem empleadosBajas;
    private javax.swing.JMenuItem irPromociones;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JMenuItem ventaNueva;
    private javax.swing.JMenuItem verVentas;
    private javax.swing.JMenu vta;
    // End of variables declaration//GEN-END:variables
}