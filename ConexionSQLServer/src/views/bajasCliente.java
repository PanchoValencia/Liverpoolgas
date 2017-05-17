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
 * @author JoséFrancisco
 */
public class bajasCliente extends javax.swing.JFrame {
    static ResultSet res;
    
    private static int cod = 0;

    public static int getCod() {
        return cod;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    /**
     * Creates new form bajasCliente
     */
    public bajasCliente() {
        initComponents();
    }
    
    public void showClient(){
        DefaultTableModel cliente = (DefaultTableModel) tableShowPersonalData.getModel();
        cliente.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query("select * from cliente where activo="+0);
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                cliente.addRow(v);
                tableShowPersonalData.setModel(cliente);
            }
        }
        catch(SQLException e){
        }
    }
    public void showTel(int codCte){
        DefaultTableModel tel = (DefaultTableModel) showTelCte.getModel();
        tel.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from telefono"
                        + " inner join telefono_cliente on telefono.cod_telefono=telefono_cliente.cod_telefono"
                        + " and telefono_cliente.cod_cliente="+codCte+" order by numero_telefono"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("numero_telefono"));
                v.add(res.getString("descripcion_telefono"));
                tel.addRow(v);
                showTelCte.setModel(tel);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void cleanTel(){
        DefaultTableModel tel = (DefaultTableModel) showTelCte.getModel();
        tel.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select numero_telefono , descripcion_telefono from telefono"
                        + " inner join telefono_cliente on telefono.cod_telefono=telefono_cliente.cod_telefono"
                        + " and telefono_cliente.cod_cliente="+0
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("numero_telefono"));
                v.add(res.getString("descripcion_telefono"));
                tel.addRow(v);
                showTelCte.setModel(tel);
            }
        }
        catch(SQLException e){
        }
    }
    
    
    public void showEmail(int codCte){
        DefaultTableModel email = (DefaultTableModel) showEmailCte.getModel();
        email.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from correo"
                        + " inner join correo_cliente on correo.cod_correo=correo_cliente.cod_correo"
                        + " and correo_cliente.cod_cliente="+codCte+" order by nombre_correo"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("nombre_correo"));
                v.add(res.getString("descripcion_correo"));
                email.addRow(v);
                showEmailCte.setModel(email);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void cleanEmail(){
        DefaultTableModel email = (DefaultTableModel) showEmailCte.getModel();
        email.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select nombre_correo , descripcion_correo from correo"
                        + " inner join correo_cliente on correo.cod_correo=correo_cliente.cod_correo"
                        + " and correo_cliente.cod_cliente="+0
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("nombre_correo"));
                v.add(res.getString("descripcion_correo"));
                email.addRow(v);
                showEmailCte.setModel(email);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showAddress(int codC){
        DefaultTableModel address = (DefaultTableModel) this.tableDomCte.getModel();
        address.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM domicilio"
          + " INNER JOIN dom_cliente ON domicilio.cod_domicilio=dom_cliente.cod_domicilio AND"
          + " dom_cliente.cod_cliente="+codC
          + " INNER JOIN colonia_domicilio ON dom_cliente.cod_domicilio=colonia_domicilio.cod_domicilio"
          + " INNER JOIN colonia ON colonia_domicilio.cod_colonia=colonia.cod_colonia"
          + " INNER JOIN estado_domicilio ON dom_cliente.cod_domicilio=estado_domicilio.cod_domicilio"
          + " INNER JOIN estado ON estado_domicilio.cod_estado=estado.cod_estado"
          + " INNER JOIN ciudad_domicilio ON dom_cliente.cod_domicilio=ciudad_domicilio.cod_domicilio"
          + " INNER JOIN ciudad ON ciudad_domicilio.cod_ciudad=ciudad.cod_ciudad order by calle"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("calle"));
                v.add(res.getString("nombre_colonia"));
                v.add(res.getInt("num_ext"));
                v.add(res.getString("num_int"));
                v.add(res.getInt("cp"));
                v.add(res.getString("descripcion_domicilio"));
                v.add(res.getString("nombre_estado"));
                v.add(res.getString("nombre_ciudad"));
                address.addRow(v);
                this.tableDomCte.setModel(address);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void cleanAddress(){
        DefaultTableModel address = (DefaultTableModel) this.tableDomCte.getModel();
        address.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT calle , nombre_colonia , num_ext , num_int , cp , descripcion_domicilio , nombre_estado , nombre_ciudad"
          + " FROM domicilio"
          + " INNER JOIN dom_cliente ON domicilio.cod_domicilio=dom_cliente.cod_domicilio AND"
          + " dom_cliente.cod_cliente="+0
          + " INNER JOIN colonia_domicilio ON dom_cliente.cod_domicilio=colonia_domicilio.cod_domicilio"
          + " INNER JOIN colonia ON colonia_domicilio.cod_colonia=colonia.cod_colonia"
          + " INNER JOIN estado_domicilio ON dom_cliente.cod_domicilio=estado_domicilio.cod_domicilio"
          + " INNER JOIN estado ON estado_domicilio.cod_estado=estado.cod_estado"
          + " INNER JOIN ciudad_domicilio ON dom_cliente.cod_domicilio=ciudad_domicilio.cod_domicilio"
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
                this.tableDomCte.setModel(address);
            }
        }
        catch(SQLException e){
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        showEmailCte = new javax.swing.JTable();
        btnHabilitar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableShowPersonalData = new javax.swing.JTable();
        a = new javax.swing.JScrollPane();
        tableDomCte = new javax.swing.JTable();
        refresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        showTelCte = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bajas administrativas de clientes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes dados de baja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 14), new java.awt.Color(255, 51, 51))); // NOI18N

        showEmailCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showEmailCte.setForeground(new java.awt.Color(102, 102, 102));
        showEmailCte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Email", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showEmailCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showEmailCte.setGridColor(new java.awt.Color(0, 153, 204));
        showEmailCte.setRowHeight(35);
        showEmailCte.setRowMargin(5);
        showEmailCte.setShowVerticalLines(false);
        showEmailCte.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(showEmailCte);

        btnHabilitar.setBackground(new java.awt.Color(251, 51, 51));
        btnHabilitar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnHabilitar.setForeground(new java.awt.Color(240, 240, 240));
        btnHabilitar.setText("Habilitar");
        btnHabilitar.setEnabled(false);
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });

        tableShowPersonalData.setBackground(new java.awt.Color(240, 240, 240));
        tableShowPersonalData.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableShowPersonalData.setForeground(new java.awt.Color(102, 102, 102));
        tableShowPersonalData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre(s)", "Apellido paterno", "Apellido Materno", "sexo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableShowPersonalData.setToolTipText("");
        tableShowPersonalData.setAutoscrolls(false);
        tableShowPersonalData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableShowPersonalData.setGridColor(new java.awt.Color(0, 153, 204));
        tableShowPersonalData.setMaximumSize(new java.awt.Dimension(250, 0));
        tableShowPersonalData.setRowHeight(35);
        tableShowPersonalData.setRowMargin(5);
        tableShowPersonalData.setShowVerticalLines(false);
        tableShowPersonalData.getTableHeader().setReorderingAllowed(false);
        tableShowPersonalData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableShowPersonalDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableShowPersonalData);

        a.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N

        tableDomCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableDomCte.setForeground(new java.awt.Color(102, 102, 102));
        tableDomCte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Calle", "Colonia", "No.Ext", "No.Int", "CP", "Descripción", "Estado", "Ciudad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDomCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableDomCte.setGridColor(new java.awt.Color(0, 153, 204));
        tableDomCte.setMaximumSize(new java.awt.Dimension(600, 0));
        tableDomCte.setMinimumSize(new java.awt.Dimension(600, 0));
        tableDomCte.setRowHeight(35);
        tableDomCte.setRowMargin(5);
        tableDomCte.setShowVerticalLines(false);
        tableDomCte.getTableHeader().setReorderingAllowed(false);
        a.setViewportView(tableDomCte);

        refresh.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1490756189_User_Interface-43.png"))); // NOI18N
        refresh.setText("Actualizar");
        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        showTelCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showTelCte.setForeground(new java.awt.Color(102, 102, 102));
        showTelCte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teléfono", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showTelCte.setRowHeight(35);
        showTelCte.setRowMargin(5);
        showTelCte.setShowVerticalLines(false);
        showTelCte.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(showTelCte);

        jLayeredPane1.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnHabilitar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(a, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(refresh, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                        .addComponent(refresh)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarActionPerformed

        int opt = JOptionPane.showConfirmDialog(this, "¿Estás seguro de habilitar éste cliente?" , "¿Confirmar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if( opt == JOptionPane.NO_OPTION ){
            JOptionPane.showMessageDialog(null, "Proceso cancelado!!" , "Información", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            try{
                storedProcedures.enableCte(getCod());
                JOptionPane.showMessageDialog(null, "Cliente habilitado exitosamente!!" , "Genial", JOptionPane.INFORMATION_MESSAGE);
                this.showClient();
                this.cleanAddress();
                this.cleanEmail();
                this.cleanTel();
                btnHabilitar.setEnabled(false);
            }
            catch( SQLException e ){}
        }
    }//GEN-LAST:event_btnHabilitarActionPerformed

    private void tableShowPersonalDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShowPersonalDataMouseClicked
        int row = tableShowPersonalData.getSelectedRow();
        setCod(Integer.parseInt(tableShowPersonalData.getValueAt(row, 0).toString()));
        showAddress(getCod());
        showEmail(getCod());
        showTel(getCod());
        btnHabilitar.setEnabled(true);
    }//GEN-LAST:event_tableShowPersonalDataMouseClicked

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        this.tableDomCte.clearSelection();
        this.showEmailCte.clearSelection();
        this.showTelCte.clearSelection();
        this.showClient();
        this.btnHabilitar.setEnabled(false);
        this.cleanAddress();
        this.cleanEmail();
        this.cleanTel();
    }//GEN-LAST:event_refreshActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
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
            java.util.logging.Logger.getLogger(bajasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bajasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bajasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bajasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bajasCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane a;
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton refresh;
    private javax.swing.JTable showEmailCte;
    private javax.swing.JTable showTelCte;
    private javax.swing.JTable tableDomCte;
    private javax.swing.JTable tableShowPersonalData;
    // End of variables declaration//GEN-END:variables
}
