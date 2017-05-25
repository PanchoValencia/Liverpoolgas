/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.Validations;
import conexionsqlserver.classDate;
import conexionsqlserver.storedProcedures;
import conexionsqlserver.temporalVariables;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JoséFrancisco
 */
public class Cambios extends javax.swing.JInternalFrame {

    /**
     * Creates new form Cambios
     */
    static ResultSet res;
    
    int cc = 0;
    int filaTD = 0;
    int filaTP = 0;

    public int getFilaTD() {
        return filaTD;
    }

    public void setFilaTD(int filaTD) {
        this.filaTD = filaTD;
    }

    public int getFilaTP() {
        return filaTP;
    }

    public void setFilaTP(int filaTP) {
        this.filaTP = filaTP;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
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
        {
            JOptionPane.showMessageDialog(null, "error en mostrar folios");
        }
    }
    public Cambios() {
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
        cliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDevolucion = new javax.swing.JTable();
        fechaVenta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        iva = new javax.swing.JTextField();
        Total = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        txtBuscarProd = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnCambio = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cambio de producto");

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

        cliente.setEditable(false);
        cliente.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        cliente.setForeground(new java.awt.Color(51, 51, 51));
        cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Cliente");

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

        fechaVenta.setEditable(false);
        fechaVenta.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        fechaVenta.setForeground(new java.awt.Color(51, 51, 51));
        fechaVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Fecha de venta");

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

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Subtotal $");

        btnReset.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(40, 40, 40));
        btnReset.setText("Reestablecer valores");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selección de productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        tableProductos.setBackground(new java.awt.Color(240, 240, 240));
        tableProductos.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tableProductos.setForeground(new java.awt.Color(102, 102, 102));
        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CódigoProd", "Nombre", "Marca", "Modelo", "Color", "Precio", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        if (tableProductos.getColumnModel().getColumnCount() > 0) {
            tableProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

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

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1491881015_search.png"))); // NOI18N
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Buscar");

        jLayeredPane3.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtBuscarProd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
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
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnCambio.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        btnCambio.setForeground(new java.awt.Color(240, 240, 240));
        btnCambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1495588542_exchange.png"))); // NOI18N
        btnCambio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioActionPerformed(evt);
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
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCambio)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaFoliosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFoliosMouseClicked
        mostrarReporte();
    }//GEN-LAST:event_tablaFoliosMouseClicked

    private void tablaDevolucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDevolucionMouseClicked
        setFilaTD(1);
    }//GEN-LAST:event_tablaDevolucionMouseClicked

    private void subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalActionPerformed

    private void ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ivaActionPerformed

    private void TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        mostrarFolios();
        DefaultTableModel td = (DefaultTableModel) tablaDevolucion.getModel();
        td.setRowCount(0);
        this.setFilaTD(0);
        cliente.setText("");
        fechaVenta.setText("");
        subtotal.setText("");
        iva.setText("");
        Total.setText("");
        txtBuscarProd.setText("");
        DefaultTableModel tp = (DefaultTableModel) tableProductos.getModel();
        tp.setRowCount(0);
        setFilaTP(0);
    }//GEN-LAST:event_btnResetActionPerformed

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

    private void btnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioActionPerformed
        String nuevaCantidad = null;
        if( getFilaTD() == 0 )
        {
            JOptionPane.showMessageDialog(null, "Selecciona el producto a cambiar", "Advertencia",JOptionPane.WARNING_MESSAGE);
            
        }
        else if ( getFilaTP() == 0 )
        {
            JOptionPane.showMessageDialog(null, "Selecciona el nuevo producto", "Advertencia",JOptionPane.WARNING_MESSAGE);
        }
        else if( getFilaTD() == 1 && getFilaTP() == 1 )
        {
            nuevaCantidad = JOptionPane.showInputDialog("Cantidad del nuevo producto");
            if( nuevaCantidad == null )
            {
            }
            else
            {
                if( nuevaCantidad.isEmpty() )
                {
                    JOptionPane.showMessageDialog(null, "Ingresa la cantidad del nuevo producto", "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    nuevaCantidad = JOptionPane.showInputDialog("Cantidad del nuevo producto");
                }
                else if( Validations.validarNumeros(nuevaCantidad) )
                {
                    JOptionPane.showMessageDialog(null, "Sólo acepta números enteros", "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    nuevaCantidad = JOptionPane.showInputDialog("Cantidad del nuevo producto");
                }
                else if( Validations.lenght(nuevaCantidad, 10) )
                {
                    JOptionPane.showMessageDialog(null, "Máximo 10 números como longitud", "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    nuevaCantidad = JOptionPane.showInputDialog("Cantidad del nuevo producto");
                }
                else
                {
                    int filaTP = tableProductos.getSelectedRow();
                    int filaTF = tablaFolios.getSelectedRow();
                    String fol = tablaFolios.getValueAt(filaTF, 0).toString();
                    int codProd = Integer.parseInt(tableProductos.getValueAt(filaTP, 0).toString());
                    int newCantidad = Integer.parseInt(nuevaCantidad);
                    int cantExistente = 0;
                    
                    res = conexionsqlserver.ConnectionDB.Query("SELECT * FROM producto WHERE cod_producto="+codProd);
                    try
                    {
                        while( res.next() )
                        {
                            cantExistente = res.getInt("cantidad_producto");
                        }
                    }
                    catch( SQLException e )
                    {
                        JOptionPane.showMessageDialog(null, "error 1");
                    }
                    if( newCantidad > cantExistente )
                    {
                        JOptionPane.showMessageDialog(null, "Producto insuficiente, quedan "+cantExistente, "Advertencia" , JOptionPane.WARNING_MESSAGE);
                        nuevaCantidad = JOptionPane.showInputDialog("Cantidad del nuevo producto");
                    }
                    else
                    {
                        int codCte = 0;
                        int rowTP = tableProductos.getSelectedRow();
                        int rowTD = tablaDevolucion.getSelectedRow();
                        int codProdNuevo = Integer.parseInt(tableProductos.getValueAt(rowTP, 0).toString());
                        float precio = Float.parseFloat(tableProductos.getValueAt(rowTD, 5).toString());
                        int codProdViejo = Integer.parseInt(tablaDevolucion.getValueAt(rowTD, 7).toString());
                        
                        res = conexionsqlserver.ConnectionDB.Query(
                                "SELECT * FROM venta"
                              + " INNER JOIN cliente ON venta.cod_cliente=cliente.cod_cliente"
                              + " WHERE venta.folio_venta='"+fol+"'"
                        );
                        try
                        {
                            while( res.next() )
                            {
                                codCte = res.getInt("cod_cliente");
                            }
                        }
                        catch( SQLException e )
                        {
                            JOptionPane.showMessageDialog(null, "error 2");
                        }
                        
                        classDate date = new classDate();
                        date.setearFecha();
                        String fechaCambio = (String)temporalVariables.getFechaActual();
                        
                        String marca = tableProductos.getValueAt(rowTP, 2).toString();
                        String producto = tableProductos.getValueAt(rowTP, 1).toString();
                        String modelo = tableProductos.getValueAt(rowTP, 3).toString();
                        
                        String nuevoProd = marca + " " + producto + " " + modelo;
                        
                        int cantRegresada = Integer.parseInt(tablaDevolucion.getValueAt(rowTD, 5).toString());
                        int cantSumada = cantExistente + cantRegresada;
                        
                        float importeDev = Float.parseFloat(tablaDevolucion.getValueAt(rowTD, 6).toString());
                        float importe = 0 , desc = 0 ;
                        float price = 0;
                        //hacer diferencia =>
                        int codPromo = 0;
                        String promo = null;
                        Date fechaPromo = null;
                        int rowProd = tableProductos.getSelectedRow();
                        int codProducto = Integer.parseInt(tableProductos.getValueAt(rowProd, 0).toString());
                        res = conexionsqlserver.ConnectionDB.Query(
                                "SELECT * FROM promocion"
                                        + " INNER JOIN producto ON promocion.cod_producto=producto.cod_producto"
                                        + " WHERE promocion.cod_producto="+codProducto+" AND promocion.activo="+1);
                        try
                        {
                            while( res.next() )
                            {
                                codPromo = res.getInt("cod_promocion");
                                promo = res.getString("descripcion_promocion");
                                fechaPromo = res.getDate("fechai_promocion");
                                price = res.getFloat("precio_producto");
                                if( fechaPromo.compareTo(date.getFechaSistema()) > 0 )
                                {
                                    promo = null;
                                }
                            }
                        }
                        catch(SQLException e)
                        {
                            JOptionPane.showMessageDialog(null, "error 3");
                        }
                        if( promo != null )
                        {
                            switch( promo )
                            {
                                case "10% de descuento":
                                    desc = ((price * newCantidad ) / 100) * (10) ;
                                    importe = (price * newCantidad) - desc ;
                                    break;
                                case "20% de descuento":
                                    desc = ((price * newCantidad ) / 100) * (20) ;
                                    importe = (price * newCantidad) - desc ;
                                    break;
                                case "30% de descuento":
                                    desc = ((price * newCantidad ) / 100) * (30) ;
                                    importe = (price * newCantidad) - desc ;
                                    break;
                                case "40% de descuento":
                                    desc = ((price * newCantidad ) / 100) * (40) ;
                                    importe = (price * newCantidad) - desc ;
                                    break;
                                case "50% de descuento":
                                    desc = ((price * newCantidad ) / 100) * (50) ;
                                    importe = (price * newCantidad) - desc ;
                                    break;
                                default:
                                    importe = price * newCantidad;
                                    break;
                            }
                        }
                        else
                        {
                            importe = price * newCantidad;
                        }
                        
                        
                        float dif = importe - importeDev;
                        res = conexionsqlserver.ConnectionDB.Query("");
                        
                        try
                        {
                            date.setearFol();
                            storedProcedures.newCambio(codProdNuevo, codCte, temporalVariables.getFol(), dif, nuevoProd, fechaCambio);
                            storedProcedures.updateVenta(codProdViejo, codProdNuevo, fol, precio, newCantidad);
                            storedProcedures.sumPxC(codProdViejo, cantSumada);
                            
                            JOptionPane.showMessageDialog(null, "Cambio creado correctamente", "Genial" , JOptionPane.INFORMATION_MESSAGE);
                            
                            mostrarFolios();
                            cleanProd();
                            DefaultTableModel td = (DefaultTableModel) tablaDevolucion.getModel();
                            td.setRowCount(0);
                            cliente.setText("");
                            fechaVenta.setText("");
                            subtotal.setText("");
                            iva.setText("");
                            Total.setText("");
                            //btnCambio.setEnabled(false);
                            txtBuscarProd.setText("");
                        }
                        catch( SQLException e )
                        {
                            JOptionPane.showMessageDialog(null, "error 4");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCambioActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
        setFilaTP(1);
    }//GEN-LAST:event_tableProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Total;
    private javax.swing.JButton btnCambio;
    private javax.swing.JButton btnReset;
    private javax.swing.JTextField cliente;
    private javax.swing.JTextField fechaVenta;
    private javax.swing.JTextField iva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tablaDevolucion;
    private javax.swing.JTable tablaFolios;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTextField txtBuscarProd;
    // End of variables declaration//GEN-END:variables
}
