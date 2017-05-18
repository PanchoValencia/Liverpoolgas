/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.Render;
import conexionsqlserver.Validations;
import conexionsqlserver.classDate;
import conexionsqlserver.storedProcedures;
import conexionsqlserver.temporalVariables;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JoséFrancisco
 */
public class adminEstadoCuenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form adminEstadoCuenta
     */
    static ResultSet res;
    
    public void showCte(String buscar){
        DefaultTableModel cte = (DefaultTableModel) tablaCte.getModel();
        cte.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM cliente WHERE nombres_cliente LIKE '%"+buscar+"%' AND cliente.activo="+1+" ORDER BY nombres_cliente"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt("cod_cliente"));
                v.add(res.getString("nombres_cliente"));
                v.add(res.getString("apellidop_cliente"));
                v.add(res.getString("apellidom_cliente"));
                cte.addRow(v);
                tablaCte.setModel(cte);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showCreditos(int codCte){
        DefaultTableModel cred = (DefaultTableModel) tablaCreditos.getModel();
        cred.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT COUNT(folio_venta), folio_venta FROM estado_cuenta WHERE cod_cliente="+codCte+" GROUP BY folio_venta ORDER BY folio_venta"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("folio_venta"));
                //v.add(res.getString("fecha_limite_ec"));
                //v.add(res.getString("total_ec"));
                cred.addRow(v);
                tablaCreditos.setModel(cred);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showCreditosComplementos(){
        DefaultTableModel comp = (DefaultTableModel) tablaComplemento.getModel();
        DefaultTableModel otraT = (DefaultTableModel) tablaCreditos.getModel();
        comp.setRowCount(0);
        
        int filasTablaCred = tablaCreditos.getRowCount();
        for( int i = 0 ; i < filasTablaCred ; i ++)
        {
            String fol = tablaCreditos.getValueAt(i, 0).toString();
            res = conexionsqlserver.ConnectionDB.Query(
                "SELECT TOP(1) folio_venta, fecha_limite_ec , total_ec FROM estado_cuenta WHERE folio_venta='"+fol+"' ORDER BY cod_ec DESC"
            );

            try{
                while(res.next()){
                    Vector v = new Vector();
                    v.add(res.getString("fecha_limite_ec"));
                    v.add(res.getString("total_ec"));
                    comp.addRow(v);
                    tablaComplemento.setModel(comp);
                    int numRow = tablaComplemento.getRowCount()-1;
                    
                    if( Float.parseFloat(tablaComplemento.getValueAt(numRow, 1).toString()) == 0 )
                    {
                        comp.removeRow(numRow);
                        otraT.removeRow(numRow);
                    }
                }
            }
            catch(SQLException e){
            }
        }
    }
    
    public void setearStatus(){
        int cantFilas = tablaComplemento.getRowCount();
        
        for( int i = 0 ; i < cantFilas ; i ++ )
        {
            classDate dat = new classDate();
            DateFormat fec = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaSis =  dat.getFechaSistema();
            Date fechaVer = null;
            String fechaV = tablaComplemento.getValueAt(i, 0).toString();

            try 
            {
                fechaVer = fec.parse(fechaV);
            } 
            catch (ParseException ex) {
                Logger.getLogger(adminEstadoCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }

            if( fechaSis.compareTo(fechaVer) < 0 )
            {
                tablaComplemento.setValueAt("Activo", i, 2);
            }
            else
            {
                tablaComplemento.setValueAt("Vencido", i, 2);
            }
        }
    }
    
    public adminEstadoCuenta() {
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel13 = new javax.swing.JLabel();
        buscarCte = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCte = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCreditos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaComplemento = new javax.swing.JTable();
        btnHistorial = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        btnAbonar = new javax.swing.JButton();
        fechaAbono = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        abono = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();

        setClosable(true);
        setTitle("Administrar estados de cuenta");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1491881015_search.png"))); // NOI18N
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buscarCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        buscarCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarCteActionPerformed(evt);
            }
        });
        buscarCte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarCteKeyReleased(evt);
            }
        });

        tablaCte.setBackground(new java.awt.Color(240, 240, 240));
        tablaCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tablaCte.setForeground(new java.awt.Color(0, 102, 153));
        tablaCte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "Nombre(s)", "Apellido paterno", "Apellido Materno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaCte.setRowHeight(25);
        tablaCte.setRowMargin(3);
        tablaCte.setShowVerticalLines(false);
        tablaCte.getTableHeader().setReorderingAllowed(false);
        tablaCte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCte);
        if (tablaCte.getColumnModel().getColumnCount() > 0) {
            tablaCte.getColumnModel().getColumn(0).setMinWidth(40);
            tablaCte.getColumnModel().getColumn(0).setPreferredWidth(70);
            tablaCte.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Cliente");

        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(buscarCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarCte, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel13)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Créditos del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        tablaCreditos.setBackground(new java.awt.Color(240, 240, 240));
        tablaCreditos.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tablaCreditos.setForeground(new java.awt.Color(0, 102, 153));
        tablaCreditos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCreditos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaCreditos.setIntercellSpacing(new java.awt.Dimension(1, 3));
        tablaCreditos.setRowHeight(25);
        tablaCreditos.setShowVerticalLines(false);
        tablaCreditos.getTableHeader().setReorderingAllowed(false);
        tablaCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCreditosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCreditos);

        tablaComplemento.setBackground(new java.awt.Color(240, 240, 240));
        tablaComplemento.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tablaComplemento.setForeground(new java.awt.Color(0, 102, 153));
        tablaComplemento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha límite", "Saldo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaComplemento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaComplemento.setIntercellSpacing(new java.awt.Dimension(1, 3));
        tablaComplemento.setRowHeight(25);
        tablaComplemento.setShowVerticalLines(false);
        tablaComplemento.getTableHeader().setReorderingAllowed(false);
        tablaComplemento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaComplementoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaComplemento);

        btnHistorial.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(40, 40, 40));
        btnHistorial.setText("ver historial");
        btnHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistorial.setEnabled(false);
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnHistorial, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnHistorial)))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHistorial)
                .addContainerGap())
        );

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Abono a  la cuenta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Fecha");

        btnAbonar.setBackground(new java.awt.Color(0, 102, 153));
        btnAbonar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        btnAbonar.setForeground(new java.awt.Color(240, 240, 240));
        btnAbonar.setText("ABONAR");
        btnAbonar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAbonar.setEnabled(false);
        btnAbonar.setFocusTraversalPolicyProvider(true);
        btnAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarActionPerformed(evt);
            }
        });

        fechaAbono.setEditable(false);
        fechaAbono.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        fechaAbono.setForeground(new java.awt.Color(51, 51, 51));
        fechaAbono.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Abono");

        abono.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        abono.setForeground(new java.awt.Color(51, 51, 51));
        abono.setEnabled(false);
        abono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                abonoKeyReleased(evt);
            }
        });

        jLayeredPane3.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(btnAbonar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(fechaAbono, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(abono, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAbonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(abono, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fechaAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(abono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAbonar)
                .addContainerGap())
        );

        btnReset.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(20, 20, 20));
        btnReset.setText("Reestablecer");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(322, 322, 322))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane2)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarCteActionPerformed

    private void buscarCteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarCteKeyReleased
        showCte(buscarCte.getText());
        
        if( buscarCte.getText().isEmpty() )
        {
            DefaultTableModel tabCte = (DefaultTableModel) tablaCte.getModel();
            tabCte.setRowCount(0);
        }
        
        DefaultTableModel tabCred = (DefaultTableModel) tablaCreditos.getModel();
        tabCred.setRowCount(0);
        DefaultTableModel tabComp = (DefaultTableModel) tablaComplemento.getModel();
        tabComp.setRowCount(0);
        fechaAbono.setText("");
        abono.setText("");
        abono.setEnabled(false);
        btnAbonar.setEnabled(false);
        btnHistorial.setEnabled(false);
    }//GEN-LAST:event_buscarCteKeyReleased

    private void tablaCteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCteMouseClicked
        int row = tablaCte.getSelectedRow();
        int codCte = Integer.parseInt(tablaCte.getValueAt(row, 0).toString());
        showCreditos(codCte);
        showCreditosComplementos();
        setearStatus();
        btnHistorial.setEnabled(false);
    }//GEN-LAST:event_tablaCteMouseClicked

    private void tablaCreditosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCreditosMouseClicked
        classDate date = new classDate();
        date.setearFecha();
        fechaAbono.setText((String)temporalVariables.getFechaActual());
        
        abono.setText("");
        abono.setEnabled(true);
        btnAbonar.setEnabled(false);
        tablaComplemento.clearSelection();
        int numFila = tablaCreditos.getSelectedRow();
        tablaComplemento.setRowSelectionInterval(numFila, numFila);
        btnHistorial.setEnabled(true);
    }//GEN-LAST:event_tablaCreditosMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        buscarCte.setText("");
        DefaultTableModel tabCte = (DefaultTableModel) tablaCte.getModel();
        tabCte.setRowCount(0);
        DefaultTableModel tabCre = (DefaultTableModel) tablaCreditos.getModel();
        tabCre.setRowCount(0);
        DefaultTableModel tabComp = (DefaultTableModel) tablaComplemento.getModel();
        tabComp.setRowCount(0);
        
        fechaAbono.setText("");
        abono.setText("");
        abono.setEnabled(false);
        btnAbonar.setEnabled(false);
        btnHistorial.setEnabled(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void abonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abonoKeyReleased
        
        if( Validations.validarFloat(abono.getText()) )
        {
            if( abono.getText().isEmpty() )
            {
                //
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sólo números, admite punto decimal!!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                abono.setText("");
                btnAbonar.setEnabled(false);
            }
        }
        else if( Validations.lenght(abono.getText(), 53) )
        {
            JOptionPane.showMessageDialog(null, "Sólo admiten 53 caracteres de lonigtud", "Advertencia", JOptionPane.WARNING_MESSAGE);
            abono.setText("");
            btnAbonar.setEnabled(false);
        }
        else
        {
            int rowCred = tablaCreditos.getSelectedRow();
            double cantDeuda = Double.parseDouble(tablaComplemento.getValueAt(rowCred, 1).toString());
            double cantAbono = Double.parseDouble(abono.getText());
            
            if( cantAbono > cantDeuda )
            {
                JOptionPane.showMessageDialog(null, "El abono sobrepasa la deuda, inserta otra cantidad", "Advertencia", JOptionPane.WARNING_MESSAGE);
                abono.setText("");
                btnAbonar.setEnabled(false);
            }
            else
            {
                btnAbonar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_abonoKeyReleased

    private void btnAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarActionPerformed
        int opt = JOptionPane.showConfirmDialog(this, "¿Está seguro de abonar dicha cantidad?", "Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if( opt == JOptionPane.NO_OPTION )
        {
            // do_nothing
        }
        else{
            int rowCte = tablaCte.getSelectedRow();
            int rowCre = tablaCreditos.getSelectedRow();
            
            int codCte        = Integer.parseInt(tablaCte.getValueAt(rowCte, 0).toString());
            String folioVenta = tablaCreditos.getValueAt(rowCre, 0).toString();
            String fechaLim   = tablaComplemento.getValueAt(rowCre, 0).toString();
            float Abono       = Float.parseFloat(abono.getText());
            float tot         = Float.parseFloat(tablaComplemento.getValueAt(rowCre, 1).toString());
            float Total       = tot - Abono;
            String fechaAb    = fechaAbono.getText();
            
            try
            {
                storedProcedures.abonoEC(codCte, folioVenta, fechaLim, Abono, Total, fechaAb);
                JOptionPane.showMessageDialog(null, "Abono creado correctamente!!", "Genial" , JOptionPane.INFORMATION_MESSAGE);
                
                //tablaCte.clearSelection();
                //tablaComplemento.clearSelection();
                //tablaCreditos.clearSelection();
                fechaAbono.setText("");
                abono.setText("");
                abono.setEnabled(false);
                btnAbonar.setEnabled(false);
                btnHistorial.setEnabled(false);
                showCreditos(codCte);
                showCreditosComplementos();
            }
            catch( SQLException e )
            {
                JOptionPane.showMessageDialog(null, "Error, no se realizó el abono");
            }
        }
    }//GEN-LAST:event_btnAbonarActionPerformed

    private void tablaComplementoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaComplementoMouseClicked
        classDate date = new classDate();
        date.setearFecha();
        fechaAbono.setText((String)temporalVariables.getFechaActual());
        
        abono.setText("");
        abono.setEnabled(true);
        btnAbonar.setEnabled(false);
        tablaCreditos.clearSelection();
        int numFila = tablaComplemento.getSelectedRow();
        tablaCreditos.setRowSelectionInterval(numFila, numFila);
        btnHistorial.setEnabled(true);
    }//GEN-LAST:event_tablaComplementoMouseClicked

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        int filaTCli = tablaCte.getSelectedRow();
        int filaTCre = tablaCreditos.getSelectedRow();
        int filaTCom = tablaComplemento.getSelectedRow();
        
        int codCte = Integer.parseInt(tablaCte.getValueAt(filaTCli, 0).toString());
        String nombresCli = null ,
               appCli     = null , 
               apmCli     = null ,
               nCompleto  = null;
        
        res = conexionsqlserver.ConnectionDB.Query("SELECT * FROM cliente where cod_cliente="+codCte);
        try
        {
            while( res.next() )
            {
                nombresCli = res.getString("nombres_cliente");
                appCli     = res.getString("apellidop_cliente");
                apmCli     = res.getString("apellidom_cliente");
                
                nCompleto = nombresCli + " " + appCli + " " + apmCli;
            }
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog(null, "No se obtuvo el cliente");
        }
        
        String fol = tablaCreditos.getValueAt(filaTCre, 0).toString();
        String saldIn = null ,
               fechaI = null , 
               fechaF = null ;
        
        res = conexionsqlserver.ConnectionDB.Query(
                "SELECT TOP(1) fecha_limite_ec , fecha_venta , total_ec FROM estado_cuenta"
              + " INNER JOIN venta ON estado_cuenta.folio_venta=venta.folio_venta"
              + " AND estado_cuenta.folio_venta='"+fol+"' ORDER BY cod_ec ASC"
        );
        try
        {
            while( res.next() )
            {
                
                fechaI = res.getString("fecha_venta");
                fechaF = res.getString("fecha_limite_ec");
                saldIn = res.getString("total_ec");
            }
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog(null, "No se obtuvo el primer estado de cuenta");
        }
        
        historialEC hec = new historialEC();
        DefaultTableModel tHis = (DefaultTableModel) hec.tablaHIstorial.getModel();
        tHis.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "SELECT * FROM estado_cuenta WHERE folio_venta='"+fol+"' AND abono_ec IS NOT NULL"
        );
        try
        {
            while( res.next() )
            {
                String fechaAb = res.getString("fecha_abono");
                String abon    = res.getString("abono_ec");
                String resto   = res.getString("total_ec");
                
                Object row[] = {
                  fechaAb,
                  "$"+abon,
                  "$"+resto
                };
                
                tHis.addRow(row);
                hec.tablaHIstorial.setModel(tHis);
            }
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog(null, "No se mostraron los estados de cuenta!!");
        }
        
        //Abrir historial y setear
        
        hec.setVisible(true);
        hec.setLocationRelativeTo(null);
        
        hec.cliente.setText(nCompleto);
        hec.folio.setText(fol);
        hec.fInicio.setText(fechaI);
        hec.fVencimiento.setText(fechaF);
        hec.saldoI.setText("$"+saldIn);
    }//GEN-LAST:event_btnHistorialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abono;
    private javax.swing.JButton btnAbonar;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnReset;
    private javax.swing.JTextField buscarCte;
    private javax.swing.JTextField fechaAbono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTable tablaComplemento;
    public javax.swing.JTable tablaCreditos;
    public javax.swing.JTable tablaCte;
    // End of variables declaration//GEN-END:variables
}
