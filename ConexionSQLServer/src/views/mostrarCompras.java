/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Derian Pinto
 */
public class mostrarCompras extends javax.swing.JInternalFrame {
    static ResultSet res;
    /**
     * Creates new form mostrasCompras
     */
    public mostrarCompras() {
        initComponents();
    }
    public void showVentasFolioASC(){
        DefaultTableModel com = (DefaultTableModel) tableCompras.getModel();
        com.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query("SELECT COUNT(folio_compra), folio_compra FROM compra GROUP BY folio_compra order by folio_compra ASC");
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString("folio_venta"));
                com.addRow(v);
                tableCompras.setModel(com);
            }
        }
        catch(SQLException e){
        }
    }
    
    public float configurarSubtotal(){
        float subt = 0;
        for( int i = 0 ; i < tablaReporte.getRowCount() ; i ++ )
        {
            subt += Float.parseFloat(tablaReporte.getValueAt(i, 6).toString());
        }
        return subt;
    }
    
    public float configurarIVA(){
        float percent = (configurarSubtotal() / 100 ) * 16;
        return percent;
    }
    
    public float configurarTotal(){
        float total = configurarSubtotal() + configurarIVA();
        return total;
    }
    
    public void showVentasFolioDESC(){
        DefaultTableModel vta = (DefaultTableModel) tableCompras.getModel();
        vta.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query("SELECT COUNT(folio_compra), folio_compra FROM compra GROUP BY folio_compra order by folio_compra DESC");
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString("folio_compra"));
                vta.addRow(v);
                tableCompras.setModel(vta);
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

        jLayeredPane3 = new javax.swing.JLayeredPane();
        Total = new javax.swing.JTextField();
        proveedor = new javax.swing.JTextField();
        fecha = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        folio = new javax.swing.JTextField();
        f = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        f1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaReporte = new javax.swing.JTable();
        iva = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCompras = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        orden = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 102, 153), null));

        Total.setEditable(false);
        Total.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalActionPerformed(evt);
            }
        });

        proveedor.setEditable(false);
        proveedor.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        proveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorActionPerformed(evt);
            }
        });

        fecha.setEditable(false);
        fecha.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Total $");

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Reporte de venta");

        folio.setEditable(false);
        folio.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        folio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        folio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folioActionPerformed(evt);
            }
        });

        f.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        f.setForeground(new java.awt.Color(102, 102, 102));
        f.setText("Fecha");

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Proveedor");

        f1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        f1.setForeground(new java.awt.Color(102, 102, 102));
        f1.setText("Folio");

        tablaReporte.setBackground(new java.awt.Color(240, 240, 240));
        tablaReporte.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tablaReporte.setForeground(new java.awt.Color(102, 102, 102));
        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Marca", "Modelo", "Precio", "Cantidad", "codProducto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaReporte.setGridColor(new java.awt.Color(255, 255, 255));
        tablaReporte.setRowHeight(30);
        tablaReporte.setRowMargin(3);
        tablaReporte.setShowHorizontalLines(false);
        tablaReporte.setShowVerticalLines(false);
        tablaReporte.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaReporte);

        iva.setEditable(false);
        iva.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        iva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ivaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("IVA(16%) = $");

        subtotal.setEditable(false);
        subtotal.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        subtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtotalActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Subtotal $");

        jLayeredPane3.setLayer(Total, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(proveedor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(fecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jSeparator3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(folio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(f, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(f1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(iva, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(subtotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                        .addComponent(f)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(f1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(folio, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(folio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tableCompras.setBackground(new java.awt.Color(240, 240, 240));
        tableCompras.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableCompras.setForeground(new java.awt.Color(102, 102, 102));
        tableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "count", "Folio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableCompras.setGridColor(new java.awt.Color(0, 153, 204));
        tableCompras.setRowHeight(35);
        tableCompras.setRowMargin(5);
        tableCompras.setShowVerticalLines(false);
        tableCompras.getTableHeader().setReorderingAllowed(false);
        tableCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableComprasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCompras);
        if (tableCompras.getColumnModel().getColumnCount() > 0) {
            tableCompras.getColumnModel().getColumn(0).setMinWidth(0);
            tableCompras.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableCompras.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Orden de folio:");

        orden.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        orden.setForeground(new java.awt.Color(51, 51, 51));
        orden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        orden.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        orden.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ordenItemStateChanged(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(51, 51, 51));
        btnActualizar.setText("Actualizar");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLayeredPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalActionPerformed

    private void proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed

    private void folioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folioActionPerformed

    }//GEN-LAST:event_folioActionPerformed

    private void ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ivaActionPerformed

    private void subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalActionPerformed

    private void tableComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableComprasMouseClicked
        DefaultTableModel registro = (DefaultTableModel) tablaReporte.getModel();
        registro.setRowCount(0);

        int filaSelecTV = tableCompras.getSelectedRow(); //fila seleccionada de tabla venta
        String folioCompra = tableCompras.getValueAt(filaSelecTV, 1).toString(); // se asigna valor de folio de la fila

        String query =
        "SELECT * FROM compra"
        + " inner join producto on compra.cod_producto=producto.cod_producto"
        + " inner join marca_producto on producto.cod_producto=marca_producto.cod_producto"
        + " inner join marca on marca_producto.cod_marca=marca.cod_marca"
        + " inner join modelo_producto on producto.cod_producto=modelo_producto.cod_producto"
        + " inner join modelo on modelo_producto.cod_modelo=modelo.cod_modelo"
        + " inner join proveedor on compra.cod_proveedor=proveedor.cod_proveedor"
        + " where venta.folio_compra='"+folioCompra+"'";

        res = conexionsqlserver.ConnectionDB.Query(query);//ejecutar consulta

        try
        {
            while( res.next() )
            {
                int CODPROD     = res.getInt("cod_producto");
                String PRODUCTO = res.getString("nombre_producto");
                String MARCA    = res.getString("nombre_marca");
                String MODELO   = res.getString("nombre_modelo");
                String PRECIO   = res.getString("precio_producto");
                String CANTIDAD = res.getString("cantidad_compra");
                String FECHA    = res.getString("fecha_compra");

                Object fila[] =
                {
                    PRODUCTO ,
                    MARCA ,
                    MODELO ,
                    PRECIO ,
                    "" ,//promocion
                    CANTIDAD ,
                    "", //importe
                    CODPROD
                };

                registro.addRow(fila);
                tablaReporte.setModel(registro);
                fecha.setText(FECHA);
                folio.setText(folioCompra);
            }
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog(null, "Error al insertar filas de compra");
        }


        res = conexionsqlserver.ConnectionDB.Query("select * from proveedor inner join proveedor on proveedor.cod_proveedor=compra.cod_proveedor and compra.folio_compra='"+folioCompra+"'");
        try
        {
            while( res.next() )
            {
                String nombreProv = res.getString("nombre_proveedor");
                String appProv    = res.getString("apellidop_proveedor");
                String apmProv    = res.getString("apellidom_proveedor");

                proveedor.setText(nombreProv + " " + appProv + " " + apmProv);
            }
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog(null, "Error al insertar proveedor");
        }
        subtotal.setText( String.valueOf(configurarSubtotal()) );
        iva.setText( String.valueOf(configurarIVA()) );
        Total.setText(String.valueOf(configurarTotal()));
    }//GEN-LAST:event_tableComprasMouseClicked

    private void ordenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ordenItemStateChanged
        if( orden.getSelectedIndex() == 0 ){
            this.showVentasFolioASC();
        }
        else if( orden.getSelectedIndex() == 1 ){
            this.showVentasFolioDESC();
        }

        tableCompras.clearSelection();

        folio.setText("");
        fecha.setText("");
        proveedor.setText("");
        subtotal.setText("");
        iva.setText("");
        Total.setText("");

        DefaultTableModel tab = (DefaultTableModel) tablaReporte.getModel();
        tab.setRowCount(0);
    }//GEN-LAST:event_ordenItemStateChanged

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this.showVentasFolioASC();
        orden.setSelectedIndex(0);
        tableCompras.clearSelection();

        folio.setText("");
        fecha.setText("");
        proveedor.setText("");
        subtotal.setText("");
        iva.setText("");
        Total.setText("");

        DefaultTableModel rep = (DefaultTableModel) tablaReporte.getModel();
        rep.setRowCount(0);
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Total;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JLabel f;
    private javax.swing.JLabel f1;
    private javax.swing.JTextField fecha;
    private javax.swing.JTextField folio;
    private javax.swing.JTextField iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> orden;
    private javax.swing.JTextField proveedor;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tablaReporte;
    private javax.swing.JTable tableCompras;
    // End of variables declaration//GEN-END:variables
}
