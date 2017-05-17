/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import conexionsqlserver.Login;
import conexionsqlserver.storedProcedures;
import conexionsqlserver.temporalVariables;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Derian Pinto
 */
public class bajasProveedor extends javax.swing.JFrame {
 static ResultSet res;
    
    private static int cod = 0;

    public static int getCod() {
        return cod;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }
    /** Creates new form bajasProveedor */
    public bajasProveedor() {
        initComponents();
    }
     public void showProv(){
        DefaultTableModel proveedor = (DefaultTableModel) showPersonalData.getModel();
        proveedor.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query("select * from proveedor where activo="+0);
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                proveedor.addRow(v);
                showPersonalData.setModel(proveedor);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showTel(int codProv){
        DefaultTableModel tel = (DefaultTableModel) showTel.getModel();
        tel.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from telefono"
                        + " inner join telefono_proveedor on telefono.cod_telefono=telefono_proveedor.cod_telefono"
                        + " and telefono_proveedor.cod_proveedor="+codProv
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("cod_telefono"));
                v.add(res.getString("numero_telefono"));
                v.add(res.getString("descripcion_telefono"));
                tel.addRow(v);
                showTel.setModel(tel);
            }
        }
        catch(SQLException e){
        }
    }
    public void cleanTel(){
        DefaultTableModel tel = (DefaultTableModel) showTel.getModel();
        tel.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select numero_telefono , descripcion_telefono from telefono"
                        + " inner join telefono_proveedor on telefono.cod_telefono=telefono_proveedor.cod_telefono"
                        + " and telefono_proveedor.cod_proveedor="+0
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("numero_telefono"));
                v.add(res.getString("descripcion_telefono"));
                tel.addRow(v);
                showTel.setModel(tel);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showEmail(int codProv){
        DefaultTableModel email = (DefaultTableModel) showEmail.getModel();
        email.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from correo"
                        + " inner join correo_proveedor on correo.cod_correo=correo_proveedor.cod_correo"
                        + " and correo_proveedor.cod_proveedor="+codProv
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("cod_correo"));
                v.add(res.getString("nombre_correo"));
                v.add(res.getString("descripcion_correo"));
                email.addRow(v);
                showEmail.setModel(email);
            }
        }
        catch(SQLException e){
        }
    }
    public void cleanEmail(){
        DefaultTableModel email = (DefaultTableModel) showEmail.getModel();
        email.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select nombre_correo , descripcion_correo from correo"
                        + " inner join correo_proveedor on correo.cod_correo=correo_proveedor.cod_correo"
                        + " and correo_proveedor.cod_proveedor="+0
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("nombre_correo"));
                v.add(res.getString("descripcion_correo"));
                email.addRow(v);
                showEmail.setModel(email);
            }
        }
        catch(SQLException e){
        }
    }
    
    
    public void showAddress(int codP){
        DefaultTableModel address = (DefaultTableModel) this.showDom.getModel();
        address.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM domicilio"
          + " INNER JOIN dom_proveedor ON domicilio.cod_domicilio=dom_proveedor.cod_domicilio AND"
          + " dom_proveedor.cod_proveedor="+codP
          + " INNER JOIN colonia_domicilio ON dom_proveedor.cod_domicilio=colonia_domicilio.cod_domicilio"
          + " INNER JOIN colonia ON colonia_domicilio.cod_colonia=colonia.cod_colonia"
          + " INNER JOIN estado_domicilio ON dom_proveedor.cod_domicilio=estado_domicilio.cod_domicilio"
          + " INNER JOIN estado ON estado_domicilio.cod_estado=estado.cod_estado"
          + " INNER JOIN ciudad_domicilio ON dom_proveedor.cod_domicilio=ciudad_domicilio.cod_domicilio"
          + " INNER JOIN ciudad ON ciudad_domicilio.cod_ciudad=ciudad.cod_ciudad"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt("cod_domicilio"));
                v.add(res.getString("calle"));
                v.add(res.getString("nombre_colonia"));
                v.add(res.getInt("num_ext"));
                v.add(res.getString("num_int"));
                v.add(res.getInt("cp"));
                v.add(res.getString("descripcion_domicilio"));
                v.add(res.getString("nombre_estado"));
                v.add(res.getString("nombre_ciudad"));
                address.addRow(v);
                this.showDom.setModel(address);
            }
        }
        catch(SQLException e){
        }
    }
    public void cleanAddress(){
        DefaultTableModel address = (DefaultTableModel) this.showDom.getModel();
        address.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT calle , nombre_colonia , num_ext , num_int , cp , descripcion_domicilio , nombre_estado , nombre_ciudad"
          + " FROM domicilio"
          + " INNER JOIN dom_proveedor ON domicilio.cod_domicilio=dom_proveedor.cod_domicilio AND"
          + " dom_proveedor.cod_proveedor="+0
          + " INNER JOIN colonia_domicilio ON dom_proveedor.cod_domicilio=colonia_domicilio.cod_domicilio"
          + " INNER JOIN colonia ON colonia_domicilio.cod_colonia=colonia.cod_colonia"
          + " INNER JOIN estado_domicilio ON dom_proveedor.cod_domicilio=estado_domicilio.cod_domicilio"
          + " INNER JOIN estado ON estado_domicilio.cod_estado=estado.cod_estado"
          + " INNER JOIN ciudad_domicilio ON dom_proveedor.cod_domicilio=ciudad_domicilio.cod_domicilio"
          + " INNER JOIN ciudad ON ciudad_domicilio.cod_ciudad=ciudad.cod_ciudad"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString(1));
                v.add(res.getString(2));
                v.add(res.getInt(3));
                v.add(res.getString(4));
                v.add(res.getInt(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                address.addRow(v);
                this.showDom.setModel(address);
            }
        }
        catch(SQLException e){
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        showPersonalData = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        showTel = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        showEmail = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        showDom = new javax.swing.JTable();
        btnHabilitarP = new javax.swing.JButton();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        showPersonalData.setBackground(new java.awt.Color(204, 204, 204));
        showPersonalData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Apellido paterno", "Apellido materno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showPersonalData.getTableHeader().setReorderingAllowed(false);
        showPersonalData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPersonalDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(showPersonalData);

        showTel.setBackground(new java.awt.Color(204, 204, 204));
        showTel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Telefono", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showTel.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(showTel);

        showEmail.setBackground(new java.awt.Color(204, 204, 204));
        showEmail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "E-mail", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showEmail.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(showEmail);

        showDom.setBackground(new java.awt.Color(204, 204, 204));
        showDom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Calle", "Colonia", "No.Ext", "No.Int", "CP", "Descripcion", "Estado", "Ciudad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showDom.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(showDom);

        btnHabilitarP.setBackground(new java.awt.Color(251, 51, 51));
        btnHabilitarP.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnHabilitarP.setForeground(new java.awt.Color(240, 240, 240));
        btnHabilitarP.setText("Habilitar");
        btnHabilitarP.setEnabled(false);
        btnHabilitarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarPActionPerformed(evt);
            }
        });

        refresh.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490756189_User_Interface-43.png"))); // NOI18N
        refresh.setText("Actualizar");
        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHabilitarP, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHabilitarP, jScrollPane1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(refresh)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHabilitarP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showPersonalDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPersonalDataMouseClicked
        // TODO add your handling code here:
        int row = showPersonalData.getSelectedRow();
        setCod(Integer.parseInt(showPersonalData.getValueAt(row, 0).toString()));
        showAddress(getCod());
        showEmail(getCod());
        showTel(getCod());
        btnHabilitarP.setEnabled(true);
    }//GEN-LAST:event_showPersonalDataMouseClicked

    private void btnHabilitarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarPActionPerformed

        int opt = JOptionPane.showConfirmDialog(this, "¿Estás seguro de habilitar éste cliente?" , "¿Confirmar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if( opt == JOptionPane.NO_OPTION ){
            JOptionPane.showMessageDialog(null, "Proceso cancelado!!" , "Información", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            try{
                storedProcedures.enableProv(getCod());
                JOptionPane.showMessageDialog(null, "Proveedor habilitado exitosamente!!" , "Genial", JOptionPane.INFORMATION_MESSAGE);
                this.showProv();
                this.cleanAddress();
                this.cleanEmail();
                this.cleanTel();
                btnHabilitarP.setEnabled(false);
            }
            catch( SQLException e ){}
        }
    }//GEN-LAST:event_btnHabilitarPActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
         this.showDom.clearSelection();
        this.showEmail.clearSelection();
        this.showTel.clearSelection();
        this.showProv();
        this.btnHabilitarP.setEnabled(false);
        this.cleanAddress();
        this.cleanEmail();
        this.cleanTel();
    }//GEN-LAST:event_refreshActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
        App choose = new App();
        choose.setVisible(true);
        choose.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(bajasProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bajasProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bajasProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bajasProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bajasProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHabilitarP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton refresh;
    private javax.swing.JTable showDom;
    private javax.swing.JTable showEmail;
    private javax.swing.JTable showPersonalData;
    private javax.swing.JTable showTel;
    // End of variables declaration//GEN-END:variables

}
