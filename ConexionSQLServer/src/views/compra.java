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
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Derian Pinto
 */
public class compra extends javax.swing.JInternalFrame {
    static ResultSet res;
public void showProv()
    {
        res = conexionsqlserver.ConnectionDB.Query("SELECT * FROM proveedor WHERE proveedor.activo="+1);
        
        try
        {  
            while( res.next() )
            {
                String codigo   = res.getString("cod_proveedor")       ,
                       nombre   = res.getString("nombre_proveedor")   ,
                       app      = res.getString("apellidop_proveedor") ,
                       apm      = res.getString("apellidom_proveedor") ,
                       empleado = codigo + " - " + nombre + " " + app + " " + apm ;
                
                comboProveedor.addItem(empleado);      
            }
        }
        catch( SQLException e )
        {        
        }  
    }
    
    
    public float getSubTotal(){
        int numFilas = tableCompra.getRowCount();
        
        float importe  = 0;
        float subtotal = 0;
        
        if( numFilas > 0 ){
            for( int i = 0 ; i < numFilas ; i ++ ){
                
                if( tableCompra.getValueAt(i, 7).toString().isEmpty() ){
                    importe = 0;
                }
                else{
                    importe = Float.parseFloat(tableCompra.getValueAt(i, 7).toString())*Float.parseFloat(tableCompra.getValueAt(i, 6).toString());
                }
                tableCompra.setValueAt(importe, i, 8);
                subtotal = subtotal + importe;
            }
        }
        
        //JOptionPane.showMessageDialog(null, "renglones="+numFilas+" ::: Columnas="+numColum);
        
        return subtotal;
    }
    
    public float getIva(){
        Float iva = getSubTotal() / 100 * 16;
        
        return iva;
    }
    
    public float getTotal(){
        float tot = getSubTotal() + getIva();
        
        return tot;
    }
    
    public boolean faltaCantidad(){
        int numFilas = tableCompra.getRowCount();
        String cantidad = null;
        
        if( numFilas > 0 ){
            for( int i = 0 ; i < numFilas ; i ++ ){
                cantidad = tableCompra.getValueAt(i, 8).toString();
                
                if( cantidad.equals("")){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Creates new form 
     */
    public compra() {
        initComponents();
        showProv();  
        //setear classDate
        classDate date = new classDate();
        date.setearFecha();
        fecha.setText((String)temporalVariables.getFechaActual());
    }
    
    
    public void showProd(String buscar){
        DefaultTableModel prod = (DefaultTableModel) tableProductos.getModel();
        prod.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM producto"
          + " inner join producto_departamento on producto.cod_producto=producto_departamento.cod_producto"
          + " inner join departamento on producto_departamento.cod_departamento=departamento.cod_departamento"
          + " inner join marca_producto on producto.cod_producto=marca_producto.cod_producto"
          + " inner join marca on marca_producto.cod_marca=marca.cod_marca"
          + " inner join modelo_producto on producto.cod_producto=modelo_producto.cod_producto"
          + " inner join modelo on modelo_producto.cod_modelo=modelo.cod_modelo"
          + " inner join color_producto on producto.cod_producto=color_producto.cod_producto"
          + " inner join color on color_producto.cod_color=color.cod_color"
          + " where nombre_producto like '%"+buscar+"%' and producto.activo="+1+" order by nombre_producto"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt("cod_producto"));
                v.add(res.getString("nombre_producto"));
                v.add(res.getString("nombre_marca"));
                v.add(res.getString("nombre_modelo"));
                v.add(res.getString("nombre_color"));
                v.add(res.getFloat("precio_producto"));
                v.add(res.getString("descripcion_producto"));
                prod.addRow(v);
                tableProductos.setModel(prod);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void cleanProd(){
        DefaultTableModel prod = (DefaultTableModel) tableProductos.getModel();
        prod.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM producto"
          + " inner join producto_departamento on producto.cod_producto=producto_departamento.cod_producto"
          + " inner join departamento on producto_departamento.cod_departamento=departamento.cod_departamento"
          + " inner join marca_producto on producto.cod_producto=marca_producto.cod_producto"
          + " inner join marca on marca_producto.cod_marca=marca.cod_marca"
          + " inner join modelo_producto on producto.cod_producto=modelo_producto.cod_producto"
          + " inner join modelo on modelo_producto.cod_modelo=modelo.cod_modelo"
          + " inner join color_producto on producto.cod_producto=color_producto.cod_producto"
          + " inner join color on color_producto.cod_color=color.cod_color"
          + " where producto.activo="+2+" order by nombre_producto"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt("cod_producto"));
                v.add(res.getString("nombre_producto"));
                v.add(res.getString("nombre_marca"));
                v.add(res.getString("nombre_modelo"));
                v.add(res.getString("nombre_color"));
                v.add(res.getFloat("precio_producto"));
                v.add(res.getString("descripcion_producto"));
                prod.addRow(v);
                tableProductos.setModel(prod);
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

        jLabel1 = new javax.swing.JLabel();
        folio = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        iva = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCompra = new javax.swing.JTable();
        subtotal = new javax.swing.JTextField();
        btnPagarContado = new javax.swing.JButton();
        inputCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnCantidad = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        txtBuscarProd = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Folio");

        folio.setEditable(false);
        folio.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        folio.setForeground(new java.awt.Color(102, 102, 102));
        folio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resúmen de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Subtotal $");

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("IVA $");

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Total $");

        total.setEditable(false);
        total.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        total.setForeground(new java.awt.Color(102, 102, 102));
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        iva.setEditable(false);
        iva.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        iva.setForeground(new java.awt.Color(102, 102, 102));
        iva.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tableCompra.setBackground(new java.awt.Color(240, 240, 240));
        tableCompra.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tableCompra.setForeground(new java.awt.Color(102, 102, 102));
        tableCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "Nombre", "Modelo", "Marca", "Color", "Descripción", "Precio", "Cantidad", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableCompra.setGridColor(new java.awt.Color(0, 153, 204));
        tableCompra.setRowHeight(30);
        tableCompra.setRowMargin(3);
        tableCompra.getTableHeader().setReorderingAllowed(false);
        tableCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCompraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCompra);

        subtotal.setEditable(false);
        subtotal.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        subtotal.setForeground(new java.awt.Color(102, 102, 102));
        subtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnPagarContado.setBackground(new java.awt.Color(0, 102, 240));
        btnPagarContado.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnPagarContado.setForeground(new java.awt.Color(240, 240, 240));
        btnPagarContado.setText("PAGO DE CONTADO");
        btnPagarContado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPagarContado.setEnabled(false);
        btnPagarContado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarContadoActionPerformed(evt);
            }
        });

        inputCantidad.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        inputCantidad.setEnabled(false);
        inputCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputCantidadKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Cantidad");

        btnCantidad.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnCantidad.setText("Ok");
        btnCantidad.setEnabled(false);
        btnCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCantidadActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(total, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(iva, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(subtotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnPagarContado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(inputCantidad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnCantidad, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnCantidad))
                            .addComponent(btnPagarContado, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(total)
                                    .addComponent(iva, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPagarContado)
                .addGap(51, 51, 51))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Fecha");

        fecha.setEditable(false);
        fecha.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        fecha.setForeground(new java.awt.Color(102, 102, 102));
        fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Proveedor");

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selección de productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        tableProductos.setBackground(new java.awt.Color(240, 240, 240));
        tableProductos.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tableProductos.setForeground(new java.awt.Color(102, 102, 102));
        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Marca", "Modelo", "Color", "Precio", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableProductos.setGridColor(new java.awt.Color(0, 153, 204));
        tableProductos.setRowHeight(30);
        tableProductos.setRowMargin(3);
        tableProductos.setShowVerticalLines(false);
        tableProductos.getTableHeader().setReorderingAllowed(false);
        tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableProductos);

        txtBuscarProd.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        txtBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProdActionPerformed(evt);
            }
        });
        txtBuscarProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProdKeyReleased(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1491881015_search.png"))); // NOI18N
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Buscar");

        jLayeredPane3.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtBuscarProd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        comboProveedor.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        comboProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un proveedor" }));
        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(40, 40, 40));
        btnReset.setText("Reestablecer");
        btnReset.setToolTipText("");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReset.setEnabled(false);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(folio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReset))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(folio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3)
                    .addComponent(jLayeredPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompraMouseClicked
        inputCantidad.setEnabled(true);
        inputCantidad.requestFocus();
        btnCantidad.setEnabled(true);
        int row = tableCompra.getSelectedRow();
        String cantidad = tableCompra.getValueAt(row, 7).toString();
        inputCantidad.setText(cantidad);

        //clickear botón para eliminar fila
        int column = tableCompra.getColumnModel().getColumnIndexAtX(evt.getX());
        int fila = evt.getY()/tableCompra.getRowHeight();
        DefaultTableModel vta = (DefaultTableModel) tableCompra.getModel();
        if( fila < tableCompra.getRowCount() && fila >= 0 && column < tableCompra.getColumnCount() && column >= 0 ){
            Object value = tableCompra.getValueAt(fila, column);

            if( value instanceof JButton ){
                ((JButton)value).doClick();
                JButton btn = (JButton) value;
                vta.removeRow(tableCompra.getSelectedRow());
                inputCantidad.setText("");
                inputCantidad.setEnabled(false);
                btnCantidad.setEnabled(false);

                //setear subtotal,iva,desc,total,tablaProd,btnPagar
                subtotal.setText(String.valueOf(getSubTotal()));
                iva.setText(String.valueOf(getIva()));
                total.setText(String.valueOf(getTotal()));
                if( tableCompra.getRowCount() == 0 ){
                    subtotal.setText("");
                    iva.setText("");
                    total.setText("");
                    tableProductos.clearSelection();

                    btnPagarContado.setEnabled(false);
                    
                }
            }
        }
    }//GEN-LAST:event_tableCompraMouseClicked

    private void btnPagarContadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarContadoActionPerformed
        if( faltaCantidad() )
        {
            JOptionPane.showMessageDialog(null, "Ingresa la cantidad de compra de cada producto" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
        }

        else if( comboProveedor.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog(null, "Selecciona el proveedor que realiza la venta" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            comboProveedor.requestFocus();
        }

        else
        {

            int op = JOptionPane.showConfirmDialog(this, "¿Seguro de realizar el pago?", "Confirmar" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if( op == JOptionPane.YES_OPTION )
            {
                String codpro   = comboProveedor.getSelectedItem().toString();
                String[] codP   = codpro.split("");
                int codProveedor = Integer.parseInt(codP[0]);


                int numFilas = tableCompra.getRowCount();
                for( int i = 0 ; i < numFilas ; i ++ )
                {
                    int codProducto = Integer.parseInt(tableCompra.getValueAt(i, 0).toString());
                    String Folio    = folio.getText();
                    String Fecha    = fecha.getText();
                    Float precio    = Float.parseFloat(tableCompra.getValueAt(i, 6).toString());
                    int cantidad    = Integer.parseInt(tableCompra.getValueAt(i, 7).toString());

                    int cantExistente = 0;
                    res = conexionsqlserver.ConnectionDB.Query("select * from producto where cod_producto="+codProducto);
                    try
                    {
                        while( res.next() )
                        {
                            cantExistente = res.getInt("cantidad_producto");
                        }
                    }
                    catch(SQLException e ){}

                    int cantRestante = cantExistente + cantidad;

                    try
                    {
                        storedProcedures.nuevaCompra(codProveedor, codProducto, Folio, Fecha, precio, cantidad);
                        storedProcedures.sumPxC(codProducto, cantRestante);
                    }
                    catch( SQLException e ){
                        JOptionPane.showMessageDialog(null, "No se realizó, :(" , "Error" , JOptionPane.ERROR_MESSAGE);
                    }
                }

                JOptionPane.showMessageDialog(null, "Compra de contado creada correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);

                folio.setText("");
                comboProveedor.setSelectedIndex(0);
                inputCantidad.setText("");
                inputCantidad.setEnabled(false);
                DefaultTableModel tv = (DefaultTableModel) tableCompra.getModel();
                int nfilas = tableCompra.getRowCount();
                for( int i = nfilas-1 ; i >= 0  ; i -- ){
                    tv.removeRow(i);
                }
                subtotal.setText("");
                iva.setText("");
                total.setText("");
                btnPagarContado.setEnabled(false);
                txtBuscarProd.setText("");
                cleanProd();
            }
        }
    }//GEN-LAST:event_btnPagarContadoActionPerformed

    private void inputCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCantidadKeyReleased
        String cant = inputCantidad.getText();
        if( Validations.validarNumeros(cant) ){
            if( cant.equals("") )
            {
                //
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sólo se aceptan números enteros","Error",JOptionPane.ERROR_MESSAGE);
                inputCantidad.setText("");
            }
        }
        if( Validations.lenght(cant, 10) ){
            JOptionPane.showMessageDialog(null, "Máximo 10 caracteres","Error",JOptionPane.ERROR_MESSAGE);
            inputCantidad.setText("");
        }
    }//GEN-LAST:event_inputCantidadKeyReleased

    private void btnCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCantidadActionPerformed
        int rowTV = tableCompra.getSelectedRow();
        int codProducto = Integer.parseInt(tableCompra.getValueAt(rowTV, 0).toString());
        int cantPedida  = Integer.parseInt(inputCantidad.getText());
        int cantExistente = 0;
        res = conexionsqlserver.ConnectionDB.Query("select * from producto where cod_producto="+codProducto);
        try
        {
            while( res.next() )
            {
                cantExistente = res.getInt("cantidad_producto");
            }
        }
        catch(SQLException e ){}

        if( inputCantidad.getText().isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "Introduce una cantidad","Error",JOptionPane.INFORMATION_MESSAGE);
            inputCantidad.requestFocus();
        }
        else
        {
            tableCompra.setValueAt(inputCantidad.getText(), rowTV, 7);
            inputCantidad.setText("");
            inputCantidad.setEnabled(false);
            btnCantidad.setEnabled(false);
            tableCompra.clearSelection();

            subtotal.setText( String.valueOf(getSubTotal()) );
            iva.setText(String.valueOf(getIva()));
            total.setText(String.valueOf(getTotal()));

            //habilitar pagar
            btnPagarContado.setEnabled(true);

            btnReset.setEnabled(true);
        }
    }//GEN-LAST:event_btnCantidadActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked

        int row = tableProductos.getSelectedRow();
        int codProd  = Integer.parseInt( tableProductos.getValueAt(row, 0).toString() );
        String prod  = tableProductos.getValueAt(row, 1).toString();
        String marca = tableProductos.getValueAt(row, 2).toString();
        String model = tableProductos.getValueAt(row, 3).toString();
        String color = tableProductos.getValueAt(row, 4).toString();
        String price = tableProductos.getValueAt(row, 5).toString();
        String desc  = tableProductos.getValueAt(row, 6).toString();

        if( tableCompra.getRowCount() > 0 )
        {
            for( int i = 0 ; i < tableCompra.getRowCount() ; i ++)
            {
                String cod = tableCompra.getValueAt(i, 0).toString();

                if( String.valueOf(codProd).equals(cod) )
                {
                    JOptionPane.showMessageDialog(null, "Ya agregaste éste producto, añade uno diferente", "Advertencia",JOptionPane.WARNING_MESSAGE);
                    tableProductos.clearSelection();
                    break;
                }
                else if( i == tableCompra.getRowCount()-1 )
                {
                    DefaultTableModel vta = (DefaultTableModel) tableCompra.getModel();

                    tableCompra.setDefaultRenderer(Object.class, new Render());

                    JButton btn = new JButton("Quitar");

                    //colocar promoción si es que lo tiene
                   
                    //setear classDate
                    classDate date = new classDate();
                    date.setearFecha();

                    int rowProd = tableProductos.getSelectedRow();
                    int codProducto = Integer.parseInt(tableProductos.getValueAt(rowProd, 0).toString());
                   
              

                    Object fila[] = { String.valueOf(codProd) , prod , model , marca , color , desc, price, "" ,"", btn };

                    vta.addRow(fila);
                    tableCompra.setModel(vta);
                    break;
                }
            }
        }
        else{
            DefaultTableModel vta = (DefaultTableModel) tableCompra.getModel();
            tableCompra.setDefaultRenderer(Object.class, new Render());

            JButton btn = new JButton("Quitar");

            //colocar promoción si es que lo tien
            //setear classDate
            classDate date = new classDate();
            date.setearFecha();
            int rowProd = tableProductos.getSelectedRow();
            int codProducto = Integer.parseInt(tableProductos.getValueAt(rowProd, 0).toString());

            Object fila[] = { String.valueOf(codProd) , prod , model , marca , color , desc , price , "" , "",btn};

            vta.addRow(fila);
            tableCompra.setModel(vta);
        }
    }//GEN-LAST:event_tableProductosMouseClicked

    private void txtBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProdActionPerformed

    private void txtBuscarProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProdKeyReleased
        if( Validations.validarQuotes(txtBuscarProd.getText()) )
        {
        
        }
        else
        {
            showProd(txtBuscarProd.getText());
            if( txtBuscarProd.getText().isEmpty() ){
                cleanProd();
            }
        }
    }//GEN-LAST:event_txtBuscarProdKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        folio.setText("");
        fecha.setText("");
        comboProveedor.setSelectedIndex(0);
        inputCantidad.setText("");
        inputCantidad.setEnabled(false);
        btnCantidad.setEnabled(false);
        txtBuscarProd.setText("");

        DefaultTableModel tp = (DefaultTableModel) tableProductos.getModel();
        DefaultTableModel tv = (DefaultTableModel) tableCompra.getModel();

        tp.setRowCount(0);
        tv.setRowCount(0);
        subtotal.setText("");
        iva.setText("");
        total.setText("");
        btnPagarContado.setEnabled(false);
        btnReset.setEnabled(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        // TODO add your handling code here:
        if( comboProveedor.getSelectedIndex() > 0 )
        {
            classDate fec = new classDate();
            fec.setearFol(); 

            String item = comboProveedor.getSelectedItem().toString();
            String array[] = item.split("");
            int cod = Integer.parseInt( array[0] );

            folio.setText(temporalVariables.getFol() + cod);
        }
        else{
            folio.setText("");
        }
    }//GEN-LAST:event_comboProveedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCantidad;
    private javax.swing.JButton btnPagarContado;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> comboProveedor;
    private javax.swing.JTextField fecha;
    private javax.swing.JTextField folio;
    private javax.swing.JTextField inputCantidad;
    private javax.swing.JTextField iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tableCompra;
    private javax.swing.JTable tableProductos;
    public javax.swing.JTextField total;
    private javax.swing.JTextField txtBuscarProd;
    // End of variables declaration//GEN-END:variables
}
