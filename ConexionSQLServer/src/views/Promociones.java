/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

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
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JoséFrancisco
 */
public class Promociones extends javax.swing.JInternalFrame {
    static ResultSet res;
    /**
     * Creates new form Promociones
     */
    
    public void showProd(){
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
          + " where producto.activo="+1+" order by nombre_producto"
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
    
    public void showVigentes(){
        DefaultTableModel vig = (DefaultTableModel) tableVigente.getModel();
        vig.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM promocion inner join producto on promocion.cod_producto=producto.cod_producto and promocion.activo="+1+" order by fechai_promocion"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt("cod_producto"));
                v.add(res.getString("fechai_promocion"));
                v.add(res.getString("fechaf_promocion"));
                v.add(res.getString("descripcion_promocion"));
                v.add(res.getInt("cod_promocion"));
                vig.addRow(v);
                tableVigente.setModel(vig);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showVencidas(){
        DefaultTableModel ven = (DefaultTableModel) tableVencida.getModel();
        ven.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM promocion inner join producto on promocion.cod_producto=producto.cod_producto and promocion.activo="+0+" order by fechai_promocion"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt("cod_producto"));
                v.add(res.getString("fechai_promocion"));
                v.add(res.getString("fechaf_promocion"));
                v.add(res.getString("descripcion_promocion"));
                v.add(res.getInt("cod_promocion"));
                ven.addRow(v);
                tableVencida.setModel(ven);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void bajaXFechaSistema(){
        
        classDate f = new classDate();

        for( int i = 0 ; i < tableVigente.getRowCount() ; i ++ )
        {
            int codPromo = Integer.parseInt(tableVigente.getValueAt(i, 4).toString());
            String fechaVencimiento = tableVigente.getValueAt(i, 2).toString();
            DateFormat fec = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaVencer = null;
            try 
            {
                fechaVencer = fec.parse(fechaVencimiento);
            } 
            catch(ParseException ex) {
                Logger.getLogger(Promociones.class.getName()).log(Level.SEVERE, null, ex);
            }

            try
            {
                if( fechaVencer.compareTo(f.getFechaSistema()) == 0 || fechaVencer.compareTo(f.getFechaSistema()) < 0 ){
                    storedProcedures.disabledPromo(codPromo);
                }
            }
            catch( SQLException e ){}
        }
    }
    
    public Promociones() {
        initComponents();
        showProd();
        showVigentes();
        showVencidas();
        bajaXFechaSistema();
        
        //minima classDate para seleccionar en classDate inicial y maxima final
        classDate fe = new classDate();
        fechaInicial.setMinSelectableDate(fe.getFechaSistema());
        fechaFinal.setMinSelectableDate(fe.getMayorFechaSistema());
        ((JTextField)this.fechaInicial.getDateEditor()).setEditable(false);
        ((JTextField)this.fechaFinal.getDateEditor()).setEditable(false);
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
        jScrollPane6 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        fechaFinal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fechaInicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        descripcion = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableVigente = new javax.swing.JTable();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableVencida = new javax.swing.JTable();

        setClosable(true);
        setTitle("Administrar promociones");

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selección de productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        tableProductos.setBackground(new java.awt.Color(240, 240, 240));
        tableProductos.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
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
        tableProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableProductos.setEnabled(false);
        tableProductos.setGridColor(new java.awt.Color(0, 153, 204));
        tableProductos.setRowHeight(25);
        tableProductos.setRowMargin(2);
        tableProductos.setShowVerticalLines(false);
        tableProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tableProductos);
        if (tableProductos.getColumnModel().getColumnCount() > 0) {
            tableProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLayeredPane3.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Promoción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        fechaFinal.setForeground(new java.awt.Color(102, 102, 102));
        fechaFinal.setDateFormatString("yyyy-MM-dd");
        fechaFinal.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Descripción:");

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Fecha inicial");

        fechaInicial.setForeground(new java.awt.Color(102, 102, 102));
        fechaInicial.setDateFormatString("yyyy-MM-dd");
        fechaInicial.setEnabled(false);
        fechaInicial.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Vigencia");

        btnModificar.setBackground(new java.awt.Color(0, 102, 204));
        btnModificar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(240, 240, 240));
        btnModificar.setText("Actualizar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 102, 204));
        btnGuardar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1493687494_discount.png"))); // NOI18N

        descripcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una promoción...", "10% de descuento", "20% de descuento", "30% de descuento", "40% de descuento", "50% de descuento" }));
        descripcion.setEnabled(false);

        btnCancelar.setBackground(new java.awt.Color(220, 220, 220));
        btnCancelar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(51, 51, 51));
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(220, 220, 220));
        btnNuevo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(51, 51, 51));
        btnNuevo.setText("Nueva");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(fechaFinal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(fechaInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnModificar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnGuardar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(descripcion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnNuevo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator1)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                    .addGap(42, 42, 42)
                                    .addComponent(jLabel4)))
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fechaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addComponent(fechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnNuevo))
                .addGap(40, 40, 40)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addGap(45, 45, 45))
        );

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Promociones Vigentes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        tableVigente.setBackground(new java.awt.Color(240, 240, 240));
        tableVigente.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        tableVigente.setForeground(new java.awt.Color(102, 102, 102));
        tableVigente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codProducto", "Fecha de inicio", "Fecha final", "Descripción", "codPromo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVigente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableVigente.setGridColor(new java.awt.Color(0, 153, 204));
        tableVigente.setRowHeight(25);
        tableVigente.setRowMargin(2);
        tableVigente.setShowVerticalLines(false);
        tableVigente.getTableHeader().setReorderingAllowed(false);
        tableVigente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVigenteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableVigente);
        if (tableVigente.getColumnModel().getColumnCount() > 0) {
            tableVigente.getColumnModel().getColumn(0).setMinWidth(0);
            tableVigente.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableVigente.getColumnModel().getColumn(0).setMaxWidth(0);
            tableVigente.getColumnModel().getColumn(1).setMinWidth(50);
            tableVigente.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableVigente.getColumnModel().getColumn(1).setMaxWidth(130);
            tableVigente.getColumnModel().getColumn(2).setMinWidth(50);
            tableVigente.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableVigente.getColumnModel().getColumn(2).setMaxWidth(130);
            tableVigente.getColumnModel().getColumn(3).setMinWidth(100);
            tableVigente.getColumnModel().getColumn(3).setPreferredWidth(220);
            tableVigente.getColumnModel().getColumn(3).setMaxWidth(250);
            tableVigente.getColumnModel().getColumn(4).setMinWidth(0);
            tableVigente.getColumnModel().getColumn(4).setPreferredWidth(0);
            tableVigente.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jLayeredPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Promociones vencidas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        tableVencida.setBackground(new java.awt.Color(240, 240, 240));
        tableVencida.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        tableVencida.setForeground(new java.awt.Color(102, 102, 102));
        tableVencida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codProducto", "Fecha de inicio", "Fecha final", "Descripción", "codPromo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVencida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableVencida.setGridColor(new java.awt.Color(0, 153, 204));
        tableVencida.setRowHeight(25);
        tableVencida.setRowMargin(2);
        tableVencida.setShowVerticalLines(false);
        tableVencida.getTableHeader().setReorderingAllowed(false);
        tableVencida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVencidaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableVencida);
        if (tableVencida.getColumnModel().getColumnCount() > 0) {
            tableVencida.getColumnModel().getColumn(0).setMinWidth(0);
            tableVencida.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableVencida.getColumnModel().getColumn(0).setMaxWidth(0);
            tableVencida.getColumnModel().getColumn(1).setMinWidth(50);
            tableVencida.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableVencida.getColumnModel().getColumn(1).setMaxWidth(130);
            tableVencida.getColumnModel().getColumn(2).setMinWidth(50);
            tableVencida.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableVencida.getColumnModel().getColumn(2).setMaxWidth(130);
            tableVencida.getColumnModel().getColumn(3).setMinWidth(100);
            tableVencida.getColumnModel().getColumn(3).setPreferredWidth(220);
            tableVencida.getColumnModel().getColumn(3).setMaxWidth(250);
            tableVencida.getColumnModel().getColumn(4).setMinWidth(0);
            tableVencida.getColumnModel().getColumn(4).setPreferredWidth(0);
            tableVencida.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jLayeredPane4.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane4))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLayeredPane3)
                            .addComponent(jLayeredPane1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        btnCancelar.setEnabled(true);
        fechaInicial.setEnabled(true);
        fechaInicial.setCalendar(null);
        fechaFinal.setEnabled(true);
        fechaFinal.setCalendar(null);
        descripcion.setEnabled(true);
        descripcion.setSelectedIndex(0);
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        tableProductos.clearSelection();
        tableProductos.setEnabled(true);
        tableVigente.clearSelection();
        tableVencida.clearSelection();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        btnCancelar.setEnabled(false);
        fechaInicial.setEnabled(false);
        fechaInicial.setCalendar(null);
        fechaFinal.setEnabled(false);
        fechaFinal.setCalendar(null);
        descripcion.setEnabled(false);
        descripcion.setSelectedIndex(0);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        tableProductos.clearSelection();
        tableVigente.clearSelection();
        tableVencida.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        int codDepa = 0;
        res = conexionsqlserver.ConnectionDB.Query("select * from departamento where nombre_departamento='"+temporalVariables.getDepartamento()+"'");
        try
        {
            while(res.next())
            {
                codDepa = res.getInt("cod_departamento");
            }
        }
        catch(SQLException e){}
        
        if( tableProductos.getSelectedRow() == -1 )
        {
            JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla para aplicar promoción!!", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else if( fechaInicial.getDate().toString().equals("") )
        {
            JOptionPane.showMessageDialog(null, "Define la fecha de inicio de la promoción", "Advertencia", JOptionPane.WARNING_MESSAGE);
            fechaInicial.requestFocus();
        }
        else if( fechaFinal.getDate().toString().equals("") )
        {
            JOptionPane.showMessageDialog(null, "Define la fecha de final de la promoción", "Advertencia", JOptionPane.WARNING_MESSAGE);
            fechaFinal.requestFocus();
        }
        else if( descripcion.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog(null, "Define la promoción a aplicar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            descripcion.requestFocus();
        }
        else
        {
            if( tableVigente.getRowCount() == 0 )
            {
                //DateFormat classDate = new SimpleDateFormat("yyyy-MM-dd");
                Date fei = fechaInicial.getDate();
                Date fef = fechaFinal.getDate();
                
                if( fef.compareTo(fei) < 0 || fef.compareTo(fei) == 0 )
                {
                    JOptionPane.showMessageDialog(null, "La fecha de vencimiento debe ser mayor a la de inicio", "Advertencia",JOptionPane.WARNING_MESSAGE);
                    fechaFinal.requestFocus();
                }
                else
                {
                    int rowP    = tableProductos.getSelectedRow();
                    int codProd = Integer.parseInt(tableProductos.getValueAt(rowP, 0).toString());

                    Date dfi = fechaInicial.getDate();
                    Date dff = fechaFinal.getDate();

                    DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
                    String fi = fecha.format(dfi);
                    String ff = fecha.format(dff);

                    String desc = descripcion.getSelectedItem().toString();

                    try
                    {
                        storedProcedures.nuevaPromo(codProd, codDepa, fi, ff, desc);
                        JOptionPane.showMessageDialog(null, "Promoción creada correctamente!!","Genial",JOptionPane.INFORMATION_MESSAGE);
                        tableProductos.clearSelection();
                        fechaInicial.setEnabled(true);
                        fechaInicial.setCalendar(null);
                        fechaFinal.setEnabled(true);
                        fechaFinal.setCalendar(null);
                        descripcion.setEnabled(true);
                        descripcion.setSelectedIndex(0);
                        btnGuardar.setEnabled(true);
                        btnModificar.setEnabled(false);
                        showVigentes();
                    }
                    catch( SQLException e ){
                        JOptionPane.showMessageDialog(null, "No se pudo insertar, intente de nuevo", "Fatal", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else
            {
                Date fei = fechaInicial.getDate();
                Date fef = fechaFinal.getDate();
                
                if( fef.compareTo(fei) < 0 || fef.compareTo(fei) == 0 )
                {
                    JOptionPane.showMessageDialog(null, "La fecha de vencimiento debe ser mayor a la de inicio", "Advertencia",JOptionPane.WARNING_MESSAGE);
                    fechaFinal.requestFocus();
                }
                else
                {
                    int rowPS = tableProductos.getSelectedRow();
                    int codProdSeleccionado = Integer.parseInt(tableProductos.getValueAt(rowPS, 0).toString());
                    for( int i = 0 ; i < tableVigente.getRowCount() ; i ++ )
                    {
                        int codProdExistente = Integer.parseInt(tableVigente.getValueAt(i, 0).toString());

                        if( codProdSeleccionado == codProdExistente )
                        {
                            JOptionPane.showMessageDialog(null, "Éste producto ya tiene promoción, elije otro", "Advertencia",JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        else
                        {
                            int rowP    = tableProductos.getSelectedRow();
                            int codProd = Integer.parseInt(tableProductos.getValueAt(rowP, 0).toString());

                            Date dfi = fechaInicial.getDate();
                            Date dff = fechaFinal.getDate();

                            DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
                            String fi = fecha.format(dfi);
                            String ff = fecha.format(dff);

                            String desc = descripcion.getSelectedItem().toString();

                            try
                            {
                                storedProcedures.nuevaPromo(codProd, codDepa, fi, ff, desc);
                                JOptionPane.showMessageDialog(null, "Promoción creada correctamente!!","Genial",JOptionPane.INFORMATION_MESSAGE);
                                tableProductos.clearSelection();
                                fechaInicial.setEnabled(true);
                                fechaInicial.setCalendar(null);
                                fechaFinal.setEnabled(true);
                                fechaFinal.setCalendar(null);
                                descripcion.setEnabled(true);
                                descripcion.setSelectedIndex(0);
                                btnGuardar.setEnabled(true);
                                btnModificar.setEnabled(false);
                                showVigentes();
                            }
                            catch( SQLException e ){
                                JOptionPane.showMessageDialog(null, "No se pudo insertar, intente de nuevo", "Fatal", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tableVigenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVigenteMouseClicked
        
        tableProductos.clearSelection();
        tableVencida.clearSelection();
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(true);
        fechaInicial.setEnabled(true);
        fechaFinal.setEnabled(true);
        descripcion.setEnabled(true);
        tableProductos.setEnabled(false);
        
        int rowVig  = tableVigente.getSelectedRow();
        int cantFilasPro  = tableProductos.getRowCount();
        int codProd = Integer.parseInt(tableVigente.getValueAt(rowVig, 0).toString());
        String fi   = tableVigente.getValueAt(rowVig, 1).toString();
        String ff   = tableVigente.getValueAt(rowVig, 2).toString();
        String desc = tableVigente.getValueAt(rowVig, 3).toString();
        
        for( int i = 0 ; i < cantFilasPro ; i ++ )
        {
            if( codProd == Integer.parseInt(tableProductos.getValueAt(i, 0).toString()) )
            {
                tableProductos.setRowSelectionInterval(i, i);
                break;
            }
        }
        
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        try 
        {
            Date feI = fecha.parse(fi);
            fechaInicial.setDate(feI);
            Date feF = fecha.parse(ff);
            fechaFinal.setDate(feF);
        } 
        catch (ParseException ex) 
        {
            Logger.getLogger(Promociones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        switch( desc ){
            case "10% de descuento":
                descripcion.setSelectedIndex(1);
                break;
                
            case "20% de descuento":
                descripcion.setSelectedIndex(2);
                break;
                
            case "30% de descuento":
                descripcion.setSelectedIndex(3);
                break;
                
            case "40% de descuento":
                descripcion.setSelectedIndex(4);
                break;
                
            case "50% de descuento":
                descripcion.setSelectedIndex(5);
                break;
        }
        
    }//GEN-LAST:event_tableVigenteMouseClicked

    private void tableVencidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVencidaMouseClicked
        
        tableProductos.clearSelection();
        tableVigente.clearSelection();
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        fechaInicial.setEnabled(true);
        fechaFinal.setEnabled(true);
        descripcion.setEnabled(true);
        tableProductos.setEnabled(false);
        
        int rowVig  = tableVencida.getSelectedRow();
        int cantFilasPro  = tableProductos.getRowCount();
        int codProd = Integer.parseInt(tableVencida.getValueAt(rowVig, 0).toString());
        String fi   = tableVencida.getValueAt(rowVig, 1).toString();
        String ff   = tableVencida.getValueAt(rowVig, 2).toString();
        String desc = tableVencida.getValueAt(rowVig, 3).toString();
        
        for( int i = 0 ; i < cantFilasPro ; i ++ )
        {
            if( codProd == Integer.parseInt(tableProductos.getValueAt(i, 0).toString()) )
            {
                tableProductos.setRowSelectionInterval(i, i);
                break;
            }
        }
        
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        try 
        {
            Date feI = fecha.parse(fi);
            fechaInicial.setDate(feI);
            Date feF = fecha.parse(ff);
            fechaFinal.setDate(feF);
        } 
        catch (ParseException ex) 
        {
            Logger.getLogger(Promociones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        switch( desc ){
            case "10% de descuento":
                descripcion.setSelectedIndex(1);
                break;
                
            case "20% de descuento":
                descripcion.setSelectedIndex(2);
                break;
                
            case "30% de descuento":
                descripcion.setSelectedIndex(3);
                break;
                
            case "40% de descuento":
                descripcion.setSelectedIndex(4);
                break;
                
            case "50% de descuento":
                descripcion.setSelectedIndex(5);
                break;
        }
    }//GEN-LAST:event_tableVencidaMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int codDepa = 0;
        res = conexionsqlserver.ConnectionDB.Query("select * from departamento where nombre_departamento='"+temporalVariables.getDepartamento()+"'");
        try
        {
            while(res.next())
            {
                codDepa = res.getInt("cod_departamento");
            }
        }
        catch(SQLException e){}
        
        
        
        if( tableProductos.getSelectedRow() == -1 )
        {
            JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla para aplicar promoción!!", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else if( fechaInicial.getDate().toString().equals("") )
        {
            JOptionPane.showMessageDialog(null, "Define la fecha de inicio de la promoción", "Advertencia", JOptionPane.WARNING_MESSAGE);
            fechaInicial.requestFocus();
        }
        else if( fechaFinal.getDate().toString().equals("") )
        {
            JOptionPane.showMessageDialog(null, "Define la fecha de final de la promoción", "Advertencia", JOptionPane.WARNING_MESSAGE);
            fechaFinal.requestFocus();
        }
        else if( descripcion.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog(null, "Define la promoción a aplicar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            descripcion.requestFocus();
        }
        else
        {
            int rowP     = tableProductos.getSelectedRow();
            int rowPromo = tableVigente.getSelectedRow();
            int codProd  = Integer.parseInt(tableProductos.getValueAt(rowP, 0).toString());
            int codPromo = Integer.parseInt(tableVigente.getValueAt(rowPromo, 4).toString());
        
            Date dfi = fechaInicial.getDate();
            Date dff = fechaFinal.getDate();

            DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            String fi = fecha.format(dfi);
            String ff = fecha.format(dff);

            String desc = descripcion.getSelectedItem().toString();
            
            try
            {
                storedProcedures.updatePromo(codPromo , codProd , codDepa , fi , ff , desc);
                JOptionPane.showMessageDialog(null, "Promoción actualizada correctamente!!","Genial",JOptionPane.INFORMATION_MESSAGE);
                tableProductos.clearSelection();
                fechaInicial.setEnabled(false);
                fechaInicial.setCalendar(null);
                fechaFinal.setEnabled(false);
                fechaFinal.setCalendar(null);
                descripcion.setEnabled(false);
                descripcion.setSelectedIndex(0);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnCancelar.setEnabled(false);
                showVigentes();
                showVencidas();
            }
            catch( SQLException e ){
                JOptionPane.showMessageDialog(null, "No se pudo actualizar, intente de nuevo", "Fatal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> descripcion;
    private com.toedter.calendar.JDateChooser fechaFinal;
    private com.toedter.calendar.JDateChooser fechaInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTable tableVencida;
    private javax.swing.JTable tableVigente;
    // End of variables declaration//GEN-END:variables
}
