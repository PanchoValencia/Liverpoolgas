/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.storedProcedures;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JoséFrancisco
 */
public class Devoluciones extends javax.swing.JInternalFrame {

    /**
     * Creates new form Devoluciones
     */
    static ResultSet res;
    
    int cc = 0;

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }
    
    public void mostrarReporte()
    {
        DefaultTableModel td = (DefaultTableModel) tablaDevolucion.getModel();
        td.setRowCount(0);

        //setear cliente y fecha
        int row = tablaFolios.getSelectedRow();
        String fol = tablaFolios.getValueAt(row, 0).toString();
        String nom = null , app = null , apm = null , completo = null;
        int codCte = 0 , codProd = 0;
        
        res = conexionsqlserver.ConnectionDB.Query(
                "SELECT TOP(1) cod_cliente,fecha_venta FROM venta where folio_venta='"+fol+"' order by cod_venta ASC"
        );
        
        try
        {
            while( res.next() )
            {
                codCte = res.getInt("cod_cliente");
                fechaVenta.setText(res.getString("fecha_venta"));
            }
        }
        catch( SQLException e ){}
        
        setCc(codCte);
        
        res = conexionsqlserver.ConnectionDB.Query("SELECT * FROM cliente WHERE cod_cliente="+codCte);
        try
        {
            while( res.next() )
            {
                nom = res.getString("nombres_cliente");
                app = res.getString("apellidop_cliente");
                apm = res.getString("apellidom_cliente");
                completo = nom + " " + app + " " + apm;
                cliente.setText(completo);
            }
        }
        catch( SQLException e ){}
        
        //configurar la tablaDevolucion
        res = conexionsqlserver.ConnectionDB.Query(
                "SELECT * FROM venta"
                + " inner join producto on venta.cod_producto=producto.cod_producto"
                + " inner join marca_producto on producto.cod_producto=marca_producto.cod_producto"
                + " inner join marca on marca_producto.cod_marca=marca.cod_marca"
                + " inner join modelo_producto on producto.cod_producto=modelo_producto.cod_producto"
                + " inner join modelo on modelo_producto.cod_modelo=modelo.cod_modelo"
                + " where venta.folio_venta='"+fol+"'"
        );
        int cant = 0;
        String prod = null,
               marca = null,
               modelo = null,
               promocion = null;
        float precio  = 0, 
              importe = 0;
        try
        {
            while( res.next() )
            {
                cant = res.getInt("cantidad_venta");
                prod = res.getString("nombre_producto");
                marca = res.getString("nombre_marca");
                modelo = res.getString("nombre_modelo");
                precio = res.getFloat("precio_producto");
                codProd = res.getInt("cod_producto");
                
                Object fila[] = {
                    prod,
                    marca,
                    modelo,
                    precio,
                    "",//promocion
                    cant,
                    "",//importe
                    codProd
                }; 
                
                td.addRow(fila);
            }
        }
        catch( SQLException e ){}
        
        for( int i = 0 ; i < tablaDevolucion.getRowCount() ; i ++ )
        {
            
            res = conexionsqlserver.ConnectionDB.Query("select * from venta"
                    + " inner join promo_venta on venta.folio_venta=promo_venta.folio_venta"
                    + " inner join promocion on promo_venta.cod_producto=promocion.cod_producto"
                    + " and promo_venta.folio_venta='"+fol
                    + "' and promo_venta.cod_producto="+codProd);
            try
            {
                while( res.next() )
                {
                    promocion = res.getString("descripcion_promocion");
                }
            }
            catch( SQLException e )
            {
                JOptionPane.showMessageDialog(null, "Error al mostrar promociones");
            }
            
            tablaDevolucion.setValueAt(promocion, i, 4);
        }
        configurarImporte();
        subtotal.setText( String.valueOf(configurarSubtotal()) );
        iva.setText( String.valueOf(configurarIVA()) );
        Total.setText(String.valueOf(configurarTotal()));
        
        btnCrear.setEnabled(false);
    }
    
    public void configurarImporte(){
        for( int i = 0 ; i < tablaDevolucion.getRowCount() ; i ++ )
        {
            float precio     = Float.parseFloat(tablaDevolucion.getValueAt(i, 3).toString());
            int cantidad     = Integer.parseInt(tablaDevolucion.getValueAt(i, 5).toString());
            float subt = 0 , importe = 0;
            
            if( tablaDevolucion.getValueAt(i, 4) == null ){
                importe = precio * cantidad ;
                tablaDevolucion.setValueAt(importe, i, 6);
            }
            else
            {
                String promocion = tablaDevolucion.getValueAt(i, 4).toString();

                switch( promocion )
                {
                    case "10% de descuento":
                        subt = ( ( precio * cantidad ) / 100 ) * (10);
                        importe = ( precio * cantidad ) - (subt);
                        tablaDevolucion.setValueAt(importe, i, 6);
                        break;

                    case "20% de descuento":
                        subt = ( ( precio * cantidad ) / 100 ) * (20);
                        importe = ( precio * cantidad ) - (subt);
                        tablaDevolucion.setValueAt(importe, i, 6);
                        break;

                    case "30% de descuento":
                        subt = ( ( precio * cantidad ) / 100 ) * (30);
                        importe = ( precio * cantidad ) - (subt);
                        tablaDevolucion.setValueAt(importe, i, 6);
                        break;
                    case "40% de descuento":
                        subt = ( ( precio * cantidad ) / 100 ) * (40);
                        importe = ( precio * cantidad ) - (subt);
                        tablaDevolucion.setValueAt(importe, i, 6);
                        break;

                    case "50% de descuento":
                        subt = ( ( precio * cantidad ) / 100 ) * (50);
                        importe = ( precio * cantidad ) - (subt);
                        tablaDevolucion.setValueAt(importe, i, 6);
                        break;

                    default:
                        importe = precio * cantidad ;
                        tablaDevolucion.setValueAt(importe, i, 6);
                        break;
                }
            }
        }
    }
    
    public float configurarSubtotal(){
        float subt = 0;
        for( int i = 0 ; i < tablaDevolucion.getRowCount() ; i ++ )
        {
            subt += Float.parseFloat(tablaDevolucion.getValueAt(i, 6).toString());
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
    public void mostrarFolios()
    {
        DefaultTableModel tf = (DefaultTableModel) tablaFolios.getModel();
        tf.setRowCount(0);
        try
        {
            res = conexionsqlserver.ConnectionDB.Query("SELECT COUNT(folio_venta),folio_venta FROM venta group by folio_venta");
            while( res.next() )
            {
                Vector v = new Vector();
                v.add(res.getString("folio_venta"));
                tf.addRow(v);
                tablaFolios.setModel(tf);
            }
        }
        catch( SQLException e )
        {}
    }
    
    public Devoluciones() {
        initComponents();
        mostrarFolios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFolios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fechaVenta = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDevolucion = new javax.swing.JTable();
        Total = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        iva = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.blue);
        setTitle("Devoluciones");

        tablaFolios.setBackground(new java.awt.Color(240, 240, 240));
        tablaFolios.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tablaFolios.setForeground(new java.awt.Color(0, 102, 204));
        tablaFolios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio de Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFolios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaFolios.setIntercellSpacing(new java.awt.Dimension(1, 3));
        tablaFolios.setRowHeight(25);
        tablaFolios.setShowVerticalLines(false);
        tablaFolios.getTableHeader().setReorderingAllowed(false);
        tablaFolios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFoliosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFolios);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Cliente");

        cliente.setEditable(false);
        cliente.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        cliente.setForeground(new java.awt.Color(51, 51, 51));
        cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Fecha de venta");

        fechaVenta.setEditable(false);
        fechaVenta.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        fechaVenta.setForeground(new java.awt.Color(51, 51, 51));
        fechaVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tablaDevolucion.setBackground(new java.awt.Color(240, 240, 240));
        tablaDevolucion.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tablaDevolucion.setForeground(new java.awt.Color(102, 102, 102));
        tablaDevolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Marca", "Modelo", "Precio", "Promoción", "Cantidad", "Importe", "codProducto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDevolucion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaDevolucion.setGridColor(new java.awt.Color(255, 255, 255));
        tablaDevolucion.setRowHeight(30);
        tablaDevolucion.setRowMargin(3);
        tablaDevolucion.setShowHorizontalLines(false);
        tablaDevolucion.setShowVerticalLines(false);
        tablaDevolucion.getTableHeader().setReorderingAllowed(false);
        tablaDevolucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDevolucionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDevolucion);
        if (tablaDevolucion.getColumnModel().getColumnCount() > 0) {
            tablaDevolucion.getColumnModel().getColumn(7).setMinWidth(0);
            tablaDevolucion.getColumnModel().getColumn(7).setPreferredWidth(0);
            tablaDevolucion.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        Total.setEditable(false);
        Total.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Total $");

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

        iva.setEditable(false);
        iva.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        iva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ivaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Subtotal $");

        btnCrear.setBackground(new java.awt.Color(0, 102, 204));
        btnCrear.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(240, 240, 240));
        btnCrear.setText("CREAR DEVOLUCIÓN");
        btnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrear.setEnabled(false);
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(40, 40, 40));
        jButton1.setText("Reestablecer valores");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrear)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaFoliosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFoliosMouseClicked
        mostrarReporte();
    }//GEN-LAST:event_tablaFoliosMouseClicked

    private void TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalActionPerformed

    private void subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalActionPerformed

    private void ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ivaActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // generar con los procedimientos
        
        int opt = JOptionPane.showConfirmDialog(this, "¿Seguro de hacer la devolución?,", "Responder" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if( opt == JOptionPane.YES_OPTION )
        {
            int selected = tablaDevolucion.getSelectedRow();
            
            int codProd = Integer.parseInt(tablaDevolucion.getValueAt(selected, 7).toString());
            int codCte  = getCc();
            String fech = fechaVenta.getText();
            String folio = tablaFolios.getValueAt(tablaFolios.getSelectedRow(), 0).toString();
            float monto = Float.parseFloat(tablaDevolucion.getValueAt(selected, 6).toString());

            int cantExistente = 0;
            int cantDevuelta = Integer.parseInt(tablaDevolucion.getValueAt(selected, 5).toString());
            int nuevaCant = 0;
            res = conexionsqlserver.ConnectionDB.Query("select * from producto where cod_producto="+codProd);

            try
            {
                while( res.next() )
                {
                    cantExistente = res.getInt("cantidad_producto");
                }
                
                nuevaCant = cantExistente + cantDevuelta;
                storedProcedures.newDevolucion(codProd, codCte, fech, folio, monto);
                storedProcedures.sumPxC(codProd, nuevaCant);
                
                JOptionPane.showMessageDialog(null, "Producto devuelto exitosamente!!", "Genial", JOptionPane.INFORMATION_MESSAGE);
            }
            catch( SQLException e ){}
            mostrarReporte();
            if( tablaDevolucion.getRowCount() == 0 ){
                mostrarFolios();
            }
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void tablaDevolucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDevolucionMouseClicked
        btnCrear.setEnabled(true);
    }//GEN-LAST:event_tablaDevolucionMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarFolios();
        DefaultTableModel td = (DefaultTableModel) tablaDevolucion.getModel();
        td.setRowCount(0);
        cliente.setText("");
        fechaVenta.setText("");
        subtotal.setText("");
        iva.setText("");
        Total.setText("");
        btnCrear.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Total;
    private javax.swing.JButton btnCrear;
    private javax.swing.JTextField cliente;
    private javax.swing.JTextField fechaVenta;
    private javax.swing.JTextField iva;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tablaDevolucion;
    private javax.swing.JTable tablaFolios;
    // End of variables declaration//GEN-END:variables
}
