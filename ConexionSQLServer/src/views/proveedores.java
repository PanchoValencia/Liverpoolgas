/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.storedProcedures;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import conexionsqlserver.Login;
import conexionsqlserver.Validations;
import conexionsqlserver.temporalVariables;
import java.awt.Frame;
import javax.swing.JFrame;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Derian Pinto
 */
public class proveedores extends javax.swing.JFrame {

   private static int    flag      = 0;
    private static int    codigoProv = 0;
    private static int    flagAdd   = 0;


    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int aFlag) {
        flag = aFlag;
    }
    
    public static int getCodigoProv() {
        return codigoProv;
    }
    
    public static void setCodigoProv(int aCodigoProv) {
        codigoProv = aCodigoProv;
    }
    
    public static int getFlagAdd() {
        return flagAdd;
    }
    public static void setFlagAdd(int aFladAdd) {
        flagAdd = aFladAdd;
    }

    /**
     * Creates new form addCli
     */
    
    static ResultSet res;

    
    
    int idProv    = 0,
        idDom     = 0,
        idColonia = 0,
        idEdo     = 0,
        idCiudad  = 0,
        idTel     = 0,
        idCorreo  = 0;
    
    public proveedores() {
        initComponents();
    }
    
    public void showProv(){
        DefaultTableModel proveedor = (DefaultTableModel) showPersonalData.getModel();
        proveedor.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query("select * from proveedor where activo="+1);
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        apmProv = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        appProv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameProv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        descEmailProv = new javax.swing.JTextField();
        emailProv = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAddProv = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        descDomProv = new javax.swing.JTextField();
        calleProv = new javax.swing.JTextField();
        colProv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cdProv = new javax.swing.JTextField();
        cpProv = new javax.swing.JTextField();
        numExtProv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        numIntProv = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        estadoProv = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        descTelProv = new javax.swing.JTextField();
        telProv = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        saveProv = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        showPersonalData = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        showTel = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        showEmail = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        showDom = new javax.swing.JTable();
        btnAddTelProv = new javax.swing.JButton();
        BtnAddEmailProv = new javax.swing.JButton();
        BtnAddDomProv = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        apmProv.setBackground(new java.awt.Color(240, 240, 240));
        apmProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        apmProv.setForeground(new java.awt.Color(51, 51, 51));
        apmProv.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Nombre(s)");

        appProv.setBackground(new java.awt.Color(240, 240, 240));
        appProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        appProv.setForeground(new java.awt.Color(51, 51, 51));
        appProv.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Apellido Paterno");

        nameProv.setBackground(new java.awt.Color(240, 240, 240));
        nameProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        nameProv.setForeground(new java.awt.Color(51, 51, 51));
        nameProv.setEnabled(false);
        nameProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameProvActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Apellido Materno");

        jLayeredPane1.setLayer(apmProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(appProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(nameProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(apmProv, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(appProv)
                    .addComponent(nameProv, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {apmProv, appProv, nameProv});

        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(appProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(apmProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {apmProv, appProv, jLabel1, jLabel2, jLabel3, nameProv});

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "E-mail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        descEmailProv.setBackground(new java.awt.Color(240, 240, 240));
        descEmailProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descEmailProv.setForeground(new java.awt.Color(51, 51, 51));
        descEmailProv.setEnabled(false);

        emailProv.setBackground(new java.awt.Color(240, 240, 240));
        emailProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        emailProv.setForeground(new java.awt.Color(51, 51, 51));
        emailProv.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("E-mail");

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Descripcion");

        jLayeredPane4.setLayer(descEmailProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(emailProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailProv, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(descEmailProv))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(descEmailProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnAgregar.setBackground(new java.awt.Color(0, 102, 204));
        btnAgregar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(240, 240, 240));
        btnAgregar.setText("Añadir");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
        btnEliminar.setText("Eliminar");
        btnEliminar.setActionCommand("");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAddProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddProv.setForeground(java.awt.Color.gray);
        btnAddProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1485408397_Add-Male-User.png"))); // NOI18N
        btnAddProv.setText("Nuevo");
        btnAddProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProvActionPerformed(evt);
            }
        });

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domicilio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        descDomProv.setBackground(new java.awt.Color(240, 240, 240));
        descDomProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descDomProv.setForeground(new java.awt.Color(51, 51, 51));
        descDomProv.setEnabled(false);

        calleProv.setBackground(new java.awt.Color(240, 240, 240));
        calleProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        calleProv.setForeground(new java.awt.Color(51, 51, 51));
        calleProv.setEnabled(false);

        colProv.setBackground(new java.awt.Color(240, 240, 240));
        colProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        colProv.setForeground(new java.awt.Color(51, 51, 51));
        colProv.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Ciudad");

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("CP");

        cdProv.setBackground(new java.awt.Color(240, 240, 240));
        cdProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        cdProv.setForeground(new java.awt.Color(51, 51, 51));
        cdProv.setEnabled(false);

        cpProv.setBackground(new java.awt.Color(240, 240, 240));
        cpProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        cpProv.setForeground(new java.awt.Color(51, 51, 51));
        cpProv.setEnabled(false);
        cpProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpProvActionPerformed(evt);
            }
        });

        numExtProv.setBackground(new java.awt.Color(240, 240, 240));
        numExtProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        numExtProv.setForeground(new java.awt.Color(51, 51, 51));
        numExtProv.setEnabled(false);
        numExtProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numExtProvActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Estado");

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Calle");

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Colonia");

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Num. Ext");

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Descripcion");

        numIntProv.setBackground(new java.awt.Color(240, 240, 240));
        numIntProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        numIntProv.setForeground(new java.awt.Color(51, 51, 51));
        numIntProv.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Num. Int");

        estadoProv.setBackground(new java.awt.Color(240, 240, 240));
        estadoProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        estadoProv.setForeground(new java.awt.Color(51, 51, 51));
        estadoProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un estado..", "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Coahuila ", "Colima", "Durango", "Edo. México", "Guanajuato", "Guerrero ", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));
        estadoProv.setEnabled(false);
        estadoProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoProvActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(descDomProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(calleProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(colProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cdProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cpProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(numExtProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(numIntProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(estadoProv, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calleProv, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numIntProv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colProv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numExtProv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cpProv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descDomProv, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cdProv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estadoProv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {calleProv, cdProv, colProv, cpProv, descDomProv, estadoProv, numExtProv, numIntProv});

        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(calleProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(colProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(numExtProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(numIntProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cpProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descDomProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(estadoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cdProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnActualizar.setBackground(new java.awt.Color(0, 102, 204));
        btnActualizar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(240, 240, 240));
        btnActualizar.setText("Actualizar");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teléfono", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        descTelProv.setBackground(new java.awt.Color(240, 240, 240));
        descTelProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descTelProv.setForeground(new java.awt.Color(51, 51, 51));
        descTelProv.setEnabled(false);

        telProv.setBackground(new java.awt.Color(240, 240, 240));
        telProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        telProv.setForeground(new java.awt.Color(51, 51, 51));
        telProv.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Telefono");

        jLabel14.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Descripcion");

        jLayeredPane3.setLayer(descTelProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(telProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(telProv, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(descTelProv))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(telProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descTelProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        saveProv.setBackground(new java.awt.Color(0, 153, 0));
        saveProv.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        saveProv.setForeground(new java.awt.Color(240, 240, 240));
        saveProv.setText("GUARDAR");
        saveProv.setActionCommand("Agregar");
        saveProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveProv.setEnabled(false);
        saveProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProvActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnCancel.setForeground(java.awt.Color.gray);
        btnCancel.setText("Cancelar");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLayeredPane5.setLayer(jLayeredPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnAgregar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnEliminar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnAddProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnActualizar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(saveProv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnCancel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane5Layout.createSequentialGroup()
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saveProv)
                            .addComponent(btnActualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jLayeredPane5Layout.createSequentialGroup()
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addComponent(btnAddProv, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        jLayeredPane5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActualizar, btnAgregar, btnEliminar, saveProv});

        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProv, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel))
                .addGap(10, 10, 10)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveProv)
                    .addComponent(btnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)))
        );

        jLayeredPane5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnActualizar, btnAgregar, btnEliminar, saveProv});

        showPersonalData.setBackground(new java.awt.Color(240, 240, 240));
        showPersonalData.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showPersonalData.setForeground(new java.awt.Color(102, 102, 102));
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
        showPersonalData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showPersonalData.setGridColor(new java.awt.Color(0, 153, 204));
        showPersonalData.setRowHeight(35);
        showPersonalData.setRowMargin(5);
        showPersonalData.setShowVerticalLines(false);
        showPersonalData.getTableHeader().setReorderingAllowed(false);
        showPersonalData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPersonalDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(showPersonalData);

        showTel.setBackground(new java.awt.Color(240, 240, 240));
        showTel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showTel.setForeground(new java.awt.Color(102, 102, 102));
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
        showTel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showTel.setGridColor(new java.awt.Color(0, 153, 204));
        showTel.setRowHeight(35);
        showTel.setRowMargin(5);
        showTel.setShowVerticalLines(false);
        showTel.getTableHeader().setReorderingAllowed(false);
        showTel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showTelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(showTel);
        if (showTel.getColumnModel().getColumnCount() > 0) {
            showTel.getColumnModel().getColumn(0).setMinWidth(0);
            showTel.getColumnModel().getColumn(0).setPreferredWidth(0);
            showTel.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        showEmail.setBackground(new java.awt.Color(240, 240, 240));
        showEmail.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showEmail.setForeground(new java.awt.Color(102, 102, 102));
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
        showEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showEmail.setGridColor(new java.awt.Color(0, 153, 204));
        showEmail.setRowHeight(35);
        showEmail.setRowMargin(5);
        showEmail.setShowVerticalLines(false);
        showEmail.getTableHeader().setReorderingAllowed(false);
        showEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showEmailMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(showEmail);
        if (showEmail.getColumnModel().getColumnCount() > 0) {
            showEmail.getColumnModel().getColumn(0).setMinWidth(0);
            showEmail.getColumnModel().getColumn(0).setPreferredWidth(0);
            showEmail.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        showDom.setBackground(new java.awt.Color(240, 240, 240));
        showDom.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showDom.setForeground(new java.awt.Color(102, 102, 102));
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
        showDom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showDom.setGridColor(new java.awt.Color(0, 153, 204));
        showDom.setRowHeight(35);
        showDom.setRowMargin(5);
        showDom.setShowVerticalLines(false);
        showDom.getTableHeader().setReorderingAllowed(false);
        showDom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showDomMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(showDom);
        if (showDom.getColumnModel().getColumnCount() > 0) {
            showDom.getColumnModel().getColumn(0).setMinWidth(0);
            showDom.getColumnModel().getColumn(0).setPreferredWidth(0);
            showDom.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnAddTelProv.setBackground(new java.awt.Color(0, 102, 204));
        btnAddTelProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddTelProv.setForeground(new java.awt.Color(240, 240, 240));
        btnAddTelProv.setText("Añadir");
        btnAddTelProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddTelProv.setEnabled(false);
        btnAddTelProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTelProvActionPerformed(evt);
            }
        });

        BtnAddEmailProv.setBackground(new java.awt.Color(0, 102, 204));
        BtnAddEmailProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        BtnAddEmailProv.setForeground(new java.awt.Color(240, 240, 240));
        BtnAddEmailProv.setText("Añadir");
        BtnAddEmailProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAddEmailProv.setEnabled(false);
        BtnAddEmailProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddEmailProvActionPerformed(evt);
            }
        });

        BtnAddDomProv.setBackground(new java.awt.Color(0, 102, 204));
        BtnAddDomProv.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        BtnAddDomProv.setForeground(new java.awt.Color(240, 240, 240));
        BtnAddDomProv.setText("Añadir");
        BtnAddDomProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAddDomProv.setEnabled(false);
        BtnAddDomProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddDomProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddTelProv)
                            .addComponent(BtnAddEmailProv)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BtnAddDomProv)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(171, 171, 171)
                                        .addComponent(btnAddTelProv)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(BtnAddEmailProv))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(BtnAddDomProv))
                    .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProvActionPerformed
        // TODO add your handling code here:
        nameProv.setEnabled(true);
        nameProv.setText("");
        nameProv.requestFocus();
        appProv.setEnabled(true);
        appProv.setText("");
        apmProv.setEnabled(true);
        apmProv.setText("");
        
          /*Domicilio */ 
        this.calleProv.setEnabled(true);
        calleProv.setText("");
        this.colProv.setEnabled(true);
        colProv.setText("");
        this.numExtProv.setEnabled(true);
        numExtProv.setText("");
        this.numIntProv.setEnabled(true);
        numIntProv.setText("");
        this.cpProv.setEnabled(true);
        cpProv.setText("");
        this.descDomProv.setEnabled(true);
        descDomProv.setText("");
        this.estadoProv.setEnabled(true);
        this.estadoProv.setSelectedIndex(0);
        this.cdProv.setEnabled(true);
        this.cdProv.setText("");
        /*  Telefono */ 
        this.telProv.setEnabled(true);
        telProv.setText("");
        this.descTelProv.setEnabled(true);
        descTelProv.setText("");
        /*Email */      
        this.emailProv.setEnabled(true);
        emailProv.setText("");
        this.descEmailProv.setEnabled(true);
        descEmailProv.setText("");
        
        this.saveProv.setEnabled(true);
        this.btnEliminar.setEnabled(false);
        this.btnActualizar.setEnabled(false);
        this.btnAgregar.setEnabled(false);
        this.btnCancel.setEnabled(true);
        
        this.btnAddTelProv.setEnabled(false);
        this.BtnAddEmailProv.setEnabled(false);
        this.BtnAddDomProv.setEnabled(false);
        
        /*tables*/
        showPersonalData.clearSelection();
        showDom.clearSelection();
        showTel.clearSelection();
        showEmail.clearSelection();
        
        cleanAddress();
        cleanTel();
        cleanEmail();
    }//GEN-LAST:event_btnAddProvActionPerformed

    private void cpProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpProvActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if(getFlagAdd() == 1){//si vale 1 añade un nuevo telefono
            if( telProv.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Aún no has llenado éste campo!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
                telProv.requestFocus();
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo teléfono?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addTelefono(telProv.getText(), descTelProv.getText());
                        int codTel = storedProcedures.getID("cod_telefono", "telefono" );
                        JOptionPane.showMessageDialog(null, "Teléfono agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        storedProcedures.addTel_Prov(codTel, getCodigoProv());
                        showTel(getCodigoProv());
                        telProv.setText("");telProv.setEnabled(false);
                        descTelProv.setText("");descTelProv.setEnabled(false);
                        this.btnAgregar.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    telProv.setText("");telProv.setEnabled(false);
                    descTelProv.setText("");descTelProv.setEnabled(false);
                    this.btnAgregar.setEnabled(false);
                }
            }
        }
        
        else if( getFlagAdd() == 2 ){//si vale 2 añade un nuevo correo
            if( emailProv.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Aún no has llenado éste campo!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
                emailProv.requestFocus();
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo correo?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addEmail(emailProv.getText(), descEmailProv.getText());
                        int codEmail = storedProcedures.getID("cod_correo", "correo" );
                        JOptionPane.showMessageDialog(null, "Correo agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        storedProcedures.addEmail_Prov(codEmail, getCodigoProv());
                        showEmail(getCodigoProv());
                        emailProv.setText("");emailProv.setEnabled(false);
                        descEmailProv.setText("");descEmailProv.setEnabled(false);
                        this.btnAgregar.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    emailProv.setText("");emailProv.setEnabled(false);
                    descEmailProv.setText("");descEmailProv.setEnabled(false);
                    this.btnAgregar.setEnabled(false);
                }
            }
        }
        
        else if( getFlagAdd() == 3 ){//si vale 2 añade un nuevo domicilio
            if( calleProv.getText().isEmpty() || colProv.getText().isEmpty() || numExtProv.getText().isEmpty() || cpProv.getText().isEmpty() || estadoProv.getSelectedIndex() == 0 || cdProv.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo domicilio?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addDomicilio(calleProv.getText(), numIntProv.getText(), Integer.parseInt(numExtProv.getText()), descDomProv.getText());
                        storedProcedures.addColonia(colProv.getText(), Integer.parseInt(cpProv.getText()));
                        storedProcedures.addEstado((String)estadoProv.getSelectedItem());
                        storedProcedures.addCiudad(cdProv.getText());
                        int cod = storedProcedures.getID("cod_domicilio", "domicilio" );
                        storedProcedures.addDomicilio_Prov(cod, getCodigoProv());
                        storedProcedures.addColonia_Domicilio(cod, cod);
                        storedProcedures.addEstado_Domicilio(cod, cod);
                        storedProcedures.addCiudad_Domicilio(cod, cod);
                        JOptionPane.showMessageDialog(null, "Domicilio agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        showAddress(getCodigoProv());
                        
                        calleProv.setText("");calleProv.setEnabled(false);
                        colProv.setText("");colProv.setEnabled(false);
                        numIntProv.setText("");numIntProv.setEnabled(false);
                        numExtProv.setText("");numExtProv.setEnabled(false);
                        cpProv.setText("");cpProv.setEnabled(false);
                        descDomProv.setText("");descDomProv.setEnabled(false);
                        estadoProv.setSelectedIndex(0);estadoProv.setEnabled(false);
                        cdProv.setText("");cdProv.setEnabled(false);
                        this.btnAgregar.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    calleProv.setText("");calleProv.setEnabled(false);
                    colProv.setText("");colProv.setEnabled(false);
                    numIntProv.setText("");numIntProv.setEnabled(false);
                    numExtProv.setText("");numExtProv.setEnabled(false);
                    cpProv.setText("");cpProv.setEnabled(false);
                    descDomProv.setText("");descDomProv.setEnabled(false);
                    estadoProv.setSelectedIndex(0);estadoProv.setEnabled(false);
                    cdProv.setText("");cdProv.setEnabled(false);
                    this.btnAgregar.setEnabled(false);
                }
            }
        }                                            
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(this.showPersonalData.getSelectedRows().length > 0){//elimina logicamente un cliente
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste proveedor?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteProv(getCodigoProv());
                JOptionPane.showMessageDialog(null, "Proveedor eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                showProv();
                cleanAddress();
                cleanTel();
                cleanEmail();
                nameProv.setText("");nameProv.setEnabled(false);
                appProv.setText("");appProv.setEnabled(false);
                apmProv.setText("");apmProv.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
        
        else if(this.showTel.getSelectedRows().length > 0){//elimina un registro de telefono de un cliente
            int row = showTel.getSelectedRow();
            int cod = Integer.parseInt(showTel.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste telefono?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteTel_Prov(cod);
                storedProcedures.deleteTel(cod);
                JOptionPane.showMessageDialog(null, "Telefono eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                this.showTel(getCodigoProv());
                showPersonalData.clearSelection();
                telProv.setText("");telProv.setEnabled(false);
                descTelProv.setText("");descTelProv.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
        
        else if(this.showEmail.getSelectedRows().length > 0){//elimina un registro de correo de un cliente
            int row = showEmail.getSelectedRow();
            int cod = Integer.parseInt(showEmail.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste correo?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteEmail_Prov(cod);
                storedProcedures.deleteEmail(cod);
                JOptionPane.showMessageDialog(null, "Correo eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                this.showEmail(getCodigoProv());
                showPersonalData.clearSelection();
                emailProv.setText("");emailProv.setEnabled(false);
                descEmailProv.setText("");descEmailProv.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
        
        else if(this.showDom.getSelectedRows().length > 0){//elimina un registro de domicilio de un cliente
            int row = showDom.getSelectedRow();
            int cod = Integer.parseInt(showDom.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste domicilio?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteDom(cod);
                JOptionPane.showMessageDialog(null, "Domicilio eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                this.showAddress(getCodigoProv());
                showDom.clearSelection();
                calleProv.setText("");calleProv.setEnabled(false);
                colProv.setText("");colProv.setEnabled(false);
                numIntProv.setText("");numIntProv.setEnabled(false);
                numExtProv.setText("");numExtProv.setEnabled(false);
                cpProv.setText("");cpProv.setEnabled(false);
                descDomProv.setText("");descDomProv.setEnabled(false);
                estadoProv.setSelectedIndex(0);estadoProv.setEnabled(false);
                cdProv.setText("");cdProv.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void BtnAddEmailProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddEmailProvActionPerformed
        // TODO add your handling code here:
         this.btnAgregar.setEnabled(true);
        this.btnEliminar.setEnabled(false);
        this.btnActualizar.setEnabled(false);
        this.saveProv.setEnabled(false);
        
        this.nameProv.setEnabled(false);
        this.appProv.setEnabled(false);
        this.apmProv.setEnabled(false);
        
        this.calleProv.setText("");this.calleProv.setEnabled(false);
        this.colProv.setText("");this.colProv.setEnabled(false);
        this.cpProv.setText("");this.cpProv.setEnabled(false);
        this.descDomProv.setText("");this.descDomProv.setEnabled(false);
        this.estadoProv.setSelectedIndex(0);this.estadoProv.setEnabled(false);
        this.cdProv.setText("");this.cdProv.setEnabled(false);
        
        this.telProv.setText("");this.telProv.setEnabled(false);this.telProv.requestFocus();
        this.descTelProv.setText("");this.descTelProv.setEnabled(false);
        
        this.emailProv.setText("");this.emailProv.setEnabled(true);
        this.descEmailProv.setText("");this.descEmailProv.setEnabled(true);
        setFlagAdd(2);
    }//GEN-LAST:event_BtnAddEmailProvActionPerformed

    private void btnAddTelProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTelProvActionPerformed
        // TODO add your handling code here:
        this.btnAgregar.setEnabled(true);
        this.btnEliminar.setEnabled(false);
        this.btnActualizar.setEnabled(false);
        this.saveProv.setEnabled(false);
        
        this.nameProv.setEnabled(false);
        this.appProv.setEnabled(false);
        this.apmProv.setEnabled(false);
        
        this.calleProv.setText("");this.calleProv.setEnabled(false);
        this.colProv.setText("");this.colProv.setEnabled(false);
        this.cpProv.setText("");this.cpProv.setEnabled(false);
        this.descDomProv.setText("");this.descDomProv.setEnabled(false);
        this.estadoProv.setSelectedIndex(0);this.estadoProv.setEnabled(false);
        this.cdProv.setText("");this.cdProv.setEnabled(false);
        
        this.telProv.setText("");this.telProv.setEnabled(true);this.telProv.requestFocus();
        this.descTelProv.setText("");this.descTelProv.setEnabled(true);
        
        this.emailProv.setText("");this.emailProv.setEnabled(false);
        this.descEmailProv.setText("");this.descEmailProv.setEnabled(false);
        setFlagAdd(1);
    }//GEN-LAST:event_btnAddTelProvActionPerformed
 
    private void saveProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProvActionPerformed
       
        String nombres   = this.nameProv.getText();
        String app       = this.appProv.getText();
        String apm       = this.apmProv.getText();
        String calle     = this.calleProv.getText();
        String colonia   = this.colProv.getText();
        String numExt    = this.numExtProv.getText();
        String numInt    = this.numIntProv.getText();
        String cp        = this.cpProv.getText();
        String descrip   = this.descDomProv.getText();
        String estado    = (String)estadoProv.getSelectedItem();
        String ciudad    = this.cdProv.getText();
        String tel       = this.telProv.getText();
        String descTel   = this.descTelProv.getText();
        String email     = this.emailProv.getText();
        String descEmail = this.descEmailProv.getText();
        
        if( nombres.isEmpty() || app.isEmpty() || apm.isEmpty() || calle.isEmpty() ||
            colonia.isEmpty() ||tel.isEmpty() || numExt.isEmpty() || cp.isEmpty() || ciudad.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
        }
        else{
            if( Validations.validarNombres(nombres) || Validations.validarNombres(app) || Validations.validarNombres(apm) || Validations.validarNumeros(numExt) || Validations.validarNumeros(cp) ||
                Validations.validarNumInt(numInt) || Validations.validarTel(tel) ||  Validations.validarNombres(ciudad)){
                JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else if( Validations.lenght(nombres, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                nameProv.requestFocus();
            }
            else if( Validations.lenght(app, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                appProv.requestFocus();
            }
            else if( Validations.lenght(apm, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                apmProv.requestFocus();
            }
            else if( Validations.lenght(calle, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                calleProv.requestFocus();
            }
            else if( Validations.lenght(colonia, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                colProv.requestFocus();
            }
            else if( Validations.lenght(numExt, 5) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 5 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                numExtProv.requestFocus();
            }
            else if( Validations.lenght(numInt, 4) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 4 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                numIntProv.requestFocus();
            }
            else if( Validations.ValidarCp(cp) ){
                JOptionPane.showMessageDialog(null, "Ingresa 5 números en el código postal!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                cpProv.requestFocus();
            }
            else if( Validations.lenght(descrip, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descDomProv.requestFocus();
            }
            else if( Validations.lenght(tel, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 25 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                telProv.requestFocus();
            }
            else if( Validations.lenght(descTel, 30) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 30 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descTelProv.requestFocus();
            }
            else if( Validations.lenght(email, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo incluyendo @example.com!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                emailProv.requestFocus();
            }
            else if( Validations.lenght(descEmail, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descEmailProv.requestFocus();
            }
            else if( estado.equals("Selecciona un estado..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún estado.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                estadoProv.requestFocus();
            }
            else if( Validations.lenght(ciudad, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                cdProv.requestFocus();
            }
            /*else if( !email.isEmpty() ){
                if( Validations.validarEmail(email)){
                    JOptionPane.showMessageDialog(null, "Introduce una dirección e correo válida!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    emailCte.requestFocus();
                }
            }*/
            else{
                try{
                    int numE = new Integer(numExt);
                    int codP = new Integer(cp);
                    String emailValid = null;
                    
                    
                    storedProcedures.addProveedor(nombres, app, apm);
                    idProv = storedProcedures.getID("cod_proveedor", "proveedor");
                    
                    storedProcedures.addDomicilio(calle, numInt, numE, descrip);
                    idDom = storedProcedures.getID("cod_domicilio", "domicilio");
                    storedProcedures.addDomicilio_Prov(idDom, idProv);
                    
                    storedProcedures.addColonia(colonia, codP);
                    idColonia = storedProcedures.getID("cod_colonia", "colonia");
                    
                    storedProcedures.addColonia_Domicilio(idDom, idColonia);
                    
                    storedProcedures.addEstado(estado);
                    idEdo = storedProcedures.getID("cod_estado", "estado");
                    
                    storedProcedures.addEstado_Domicilio(idDom, idEdo);
                    
                    storedProcedures.addCiudad(ciudad);
                    idCiudad = storedProcedures.getID("cod_ciudad", "ciudad");
                    
                    storedProcedures.addCiudad_Domicilio(idDom, idCiudad);
                    
                    if( !tel.isEmpty()){
                        storedProcedures.addTelefono(tel, descTel);
                        idTel = storedProcedures.getID("cod_telefono", "telefono");
                        storedProcedures.addTel_Prov(idTel, idProv);
                    }
                    
                    if( !email.isEmpty()){
                        storedProcedures.addEmail(email, descEmail);
                        idCorreo = storedProcedures.getID("cod_correo", "correo");
                        storedProcedures.addEmail_Prov(idCorreo, idProv);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    nameProv.setText("");
                    appProv.setText("");
                    apmProv.setText("");
                    calleProv.setText("");
                    colProv.setText("");
                    numExtProv.setText("");
                    numIntProv.setText("");
                    cpProv.setText("");
                    descDomProv.setText("");
                    estadoProv.setSelectedIndex(0);
                    cdProv.setText("");
                    telProv.setText("");
                    descTelProv.setText("");
                    emailProv.setText("");
                    descEmailProv.setText("");
                    showProv();
                }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(this, "Hubo un error al almacenar el proveedor, intenta de nuevo", ":( FATAL!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    
            
                    
    }//GEN-LAST:event_saveProvActionPerformed

    private void nameProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameProvActionPerformed

    private void numExtProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numExtProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numExtProvActionPerformed

    private void estadoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoProvActionPerformed

    private void showPersonalDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPersonalDataMouseClicked
        // TODO add your handling code here:
        this.saveProv.setEnabled(false);
        this.btnAgregar.setEnabled(false);
        this.btnEliminar.setEnabled(true);
        this.btnActualizar.setEnabled(true);
        
        this.btnAddTelProv.setEnabled(true);
        this.BtnAddEmailProv.setEnabled(true);
        this.BtnAddDomProv.setEnabled(true);
        this.btnCancel.setEnabled(true);
        
        showDom.clearSelection();
        showTel.clearSelection();
        showEmail.clearSelection();
        
        calleProv.setText("");
        calleProv.setEnabled(false);
        colProv.setText("");
        colProv.setEnabled(false);
        numExtProv.setText("");
        numExtProv.setEnabled(false);
        numIntProv.setText("");
        numIntProv.setEnabled(false);
        cpProv.setText("");
        cpProv.setEnabled(false);
        descDomProv.setText("");
        descDomProv.setEnabled(false);
        estadoProv.setSelectedIndex(0);
        estadoProv.setEnabled(false);
        cdProv.setEnabled(false);
        cdProv.setText("");
        telProv.setText("");
        telProv.setEnabled(false);
        descTelProv.setText("");
        descTelProv.setEnabled(false);
        emailProv.setText("");
        emailProv.setEnabled(false);
        descEmailProv.setText("");
        descEmailProv.setEnabled(false);
        
        try{
            int row = showPersonalData.getSelectedRow();
            setCodigoProv(Integer.parseInt(showPersonalData.getValueAt(row, 0).toString()));
            res = conexionsqlserver.ConnectionDB.Query("select * from proveedor where cod_proveedor="+getCodigoProv());
            while(res.next()){
                nameProv.setText(res.getString("nombre_proveedor"));
                nameProv.setEnabled(true);
                appProv.setText(res.getString("apellidop_proveedor"));
                appProv.setEnabled(true);
                apmProv.setText(res.getString("apellidom_proveedor"));
                apmProv.setEnabled(true);    
            }
            
            showAddress(getCodigoProv());
            showTel(getCodigoProv());
            this.showEmail(getCodigoProv());
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }                  
    }//GEN-LAST:event_showPersonalDataMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        cleanTel();
        cleanEmail();
        cleanAddress();
        showPersonalData.clearSelection();
        
        this.btnAgregar.setEnabled(false);
        this.btnEliminar.setEnabled(false);
        this.saveProv.setEnabled(false);
        this.btnActualizar.setEnabled(false);
        this.BtnAddDomProv.setEnabled(false);
        this.BtnAddEmailProv.setEnabled(false);
        this.btnAddTelProv.setEnabled(false);
        btnCancel.setEnabled(false);
        
        nameProv.setEnabled(false);
        nameProv.setText("");
        nameProv.requestFocus();
        this.appProv.setEnabled(false);
        appProv.setText("");
        this.apmProv.setEnabled(false);
        apmProv.setText("");
        /*Domicilio */ 
        this.calleProv.setEnabled(false);
        calleProv.setText("");
        this.colProv.setEnabled(false);
        colProv.setText("");
        this.numExtProv.setEnabled(false);
        numExtProv.setText("");
        this.numIntProv.setEnabled(false);
        numIntProv.setText("");
        this.cpProv.setEnabled(false);
        cpProv.setText("");
        this.descDomProv.setEnabled(false);
        descDomProv.setText("");
        this.estadoProv.setEnabled(false);
        this.estadoProv.setSelectedIndex(0);
        this.cdProv.setEnabled(false);
        this.cdProv.setText("");
        /*  Telefono */ 
        this.telProv.setEnabled(false);
        telProv.setText("");
        this.descTelProv.setEnabled(false);
        descTelProv.setText("");
        /*Email */      
        this.emailProv.setEnabled(false);
        emailProv.setText("");
        this.descEmailProv.setEnabled(false);
        descEmailProv.setText("");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void showTelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showTelMouseClicked
        // TODO add your handling code here:
        this.saveProv.setEnabled(false);
        this.btnAgregar.setEnabled(false);
        this.btnEliminar.setEnabled(true);
        this.btnActualizar.setEnabled(true);
        
        showPersonalData.clearSelection();
        showEmail.clearSelection();
        showDom.clearSelection();
        
        nameProv.setEnabled(false);
        appProv.setEnabled(false);
        apmProv.setEnabled(false);
        calleProv.setText("");
        calleProv.setEnabled(false);
        colProv.setText("");
        colProv.setEnabled(false);
        numExtProv.setText("");
        numExtProv.setEnabled(false);
        numIntProv.setText("");
        numIntProv.setEnabled(false);
        cpProv.setText("");
        cpProv.setEnabled(false);
        descDomProv.setText("");
        descDomProv.setEnabled(false);
        estadoProv.setSelectedIndex(0);
        estadoProv.setEnabled(false);
        cdProv.setEnabled(false);
        cdProv.setText("");
        emailProv.setText("");
        emailProv.setEnabled(false);
        descEmailProv.setText("");
        descEmailProv.setEnabled(false);
        
        try{
            int row = showTel.getSelectedRow();
            int codTel = Integer.parseInt(showTel.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from telefono where cod_telefono='"+codTel+"'");
            while(res.next()){
                telProv.setText(res.getString("numero_telefono"));
                telProv.setEnabled(true);
                descTelProv.setText(res.getString("descripcion_telefono"));
                descTelProv.setEnabled(true);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showTelMouseClicked

    private void showEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showEmailMouseClicked
        // TODO add your handling code here:
        this.saveProv.setEnabled(false);
        this.btnAgregar.setEnabled(false);
        this.btnEliminar.setEnabled(true);
        this.btnActualizar.setEnabled(true);
        
        showPersonalData.clearSelection();
        showTel.clearSelection();
        showDom.clearSelection();
        
        nameProv.setEnabled(false);
        appProv.setEnabled(false);
        apmProv.setEnabled(false);
        calleProv.setText("");
        calleProv.setEnabled(false);
        colProv.setText("");
        colProv.setEnabled(false);
        numExtProv.setText("");
        numExtProv.setEnabled(false);
        numIntProv.setText("");
        numIntProv.setEnabled(false);
        cpProv.setText("");
        cpProv.setEnabled(false);
        descDomProv.setText("");
        descDomProv.setEnabled(false);
        estadoProv.setSelectedIndex(0);
        estadoProv.setEnabled(false);
        cdProv.setEnabled(false);
        cdProv.setText("");
        emailProv.setText("");
        emailProv.setEnabled(false);
        descEmailProv.setText("");
        descEmailProv.setEnabled(false);
          try{
            int row = showEmail.getSelectedRow();
            int codCorreo = Integer.parseInt(showEmail.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from correo where cod_correo="+codCorreo);
            while(res.next()){
                emailProv.setText(res.getString("nombre_correo"));
                emailProv.setEnabled(true);
                descEmailProv.setText(res.getString("descripcion_correo"));
                descEmailProv.setEnabled(true);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showEmailMouseClicked

    private void showDomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showDomMouseClicked
        // TODO add your handling code here:
        this.saveProv.setEnabled(false);
        this.btnAgregar.setEnabled(false);
        this.btnEliminar.setEnabled(true);
        this.btnActualizar.setEnabled(true);
        
        showPersonalData.clearSelection();
        showEmail.clearSelection();
        showTel.clearSelection();
        
        nameProv.setEnabled(false);
        appProv.setEnabled(false);
        apmProv.setEnabled(false);
        calleProv.setText("");
        calleProv.setEnabled(false);
        colProv.setText("");
        colProv.setEnabled(false);
        numExtProv.setText("");
        numExtProv.setEnabled(false);
        numIntProv.setText("");
        numIntProv.setEnabled(false);
        cpProv.setText("");
        cpProv.setEnabled(false);
        descDomProv.setText("");
        descDomProv.setEnabled(false);
        estadoProv.setSelectedIndex(0);
        estadoProv.setEnabled(false);
        cdProv.setEnabled(false);
        cdProv.setText("");
        emailProv.setText("");
        emailProv.setEnabled(false);
        descEmailProv.setText("");
        descEmailProv.setEnabled(false);
        try{
            int row = showDom.getSelectedRow();
            int codD = Integer.parseInt(showDom.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from domicilio, colonia, estado, ciudad where cod_domicilio="+codD+" and cod_colonia="+codD+" and cod_estado="+codD+" and cod_ciudad="+codD);
            while(res.next()){
                calleProv.setText(res.getString("calle"));
                calleProv.setEnabled(true);
                colProv.setText(res.getString("nombre_colonia"));
                colProv.setEnabled(true);
                numExtProv.setText(res.getString("num_ext"));
                numExtProv.setEnabled(true);
                numIntProv.setText(res.getString("num_int"));
                numIntProv.setEnabled(true);
                cpProv.setText(res.getString("cp"));
                cpProv.setEnabled(true);
                descDomProv.setText(res.getString("descripcion_domicilio"));
                descDomProv.setEnabled(true);
                estadoProv.setEnabled(true);
                cdProv.setText(res.getString("nombre_ciudad"));
                cdProv.setEnabled(true);
                
                String estado = res.getString("nombre_estado");
                switch(estado){
                    case "Aguascalientes":
                        estadoProv.setSelectedIndex(1);
                        break;
                    case "Baja California":
                         estadoProv.setSelectedIndex(2);
                        break;
                    case " Baja California Sur":
                         estadoProv.setSelectedIndex(3);
                        break;
                    case "Campeche":
                        estadoProv.setSelectedIndex(4);
                        break;
                    case "Chiapas":
                        estadoProv.setSelectedIndex(5);
                        break;
                    case "Chihuahua":
                        estadoProv.setSelectedIndex(6);
                        break;
                    case "Coahuila":
                        estadoProv.setSelectedIndex(7);
                        break;
                    case "Colima":
                        estadoProv.setSelectedIndex(8);
                        break;
                    case "Durango":
                        estadoProv.setSelectedIndex(9);
                        break;
                    case "Edo. México":
                        estadoProv.setSelectedIndex(10);
                        break;
                    case "Guanajuato":
                        estadoProv.setSelectedIndex(11);
                        break;
                    case "Guerrero":
                        estadoProv.setSelectedIndex(12);
                        break;
                    case "Hidalgo":
                        estadoProv.setSelectedIndex(13);
                        break;
                    case "Jalisco":
                        estadoProv.setSelectedIndex(14);
                        break;
                    case "Michoacán":
                        estadoProv.setSelectedIndex(15);
                        break;
                    case "Morelos":
                        estadoProv.setSelectedIndex(16);
                        break;
                    case "Nayarit":
                        estadoProv.setSelectedIndex(17);
                        break;
                    case "Nuevo León":
                        estadoProv.setSelectedIndex(18);
                        break;
                    case "Oaxaca":
                        estadoProv.setSelectedIndex(19);
                        break;
                    case "Puebla":
                        estadoProv.setSelectedIndex(20);
                        break;
                    case "Querétaro":
                        estadoProv.setSelectedIndex(21);
                        break;
                    case "Quintana Roo":
                        estadoProv.setSelectedIndex(22);
                        break;
                    case "San Luis Potosí":
                        estadoProv.setSelectedIndex(23);
                        break;
                    case "Sinaloa":
                        estadoProv.setSelectedIndex(24);
                        break;
                    case "Sonora":
                        estadoProv.setSelectedIndex(25);
                        break;
                    case "Tabasco":
                        estadoProv.setSelectedIndex(26);
                        break;
                    case "Tamaulipas":
                        estadoProv.setSelectedIndex(27);
                        break;
                    case "Tlaxcala":
                        estadoProv.setSelectedIndex(28);
                        break;
                    case "Veracruz":
                        estadoProv.setSelectedIndex(29);
                        break;
                    case "Yucatán":
                        estadoProv.setSelectedIndex(30);
                        break;
                    case "Zacatecas":
                        estadoProv.setSelectedIndex(31);
                        break;
                }
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showDomMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        String nombres   = this.nameProv.getText();
        String app       = this.appProv.getText();
        String apm       = this.apmProv.getText();
        String calle     = this.calleProv.getText();
        String colonia   = this.colProv.getText();
        String numExt    = this.numExtProv.getText();
        String numInt    = this.numIntProv.getText();
        String cp        = this.cpProv.getText();
        String descrip   = this.descDomProv.getText();
        String estado    = (String)estadoProv.getSelectedItem();
        String ciudad    = this.cdProv.getText();
        String tel       = this.telProv.getText();
        String descTel   = this.descTelProv.getText();
        String email     = this.emailProv.getText();
        String descEmail = this.descEmailProv.getText();
        
        if(this.showPersonalData.getSelectedRows().length > 0){//actualiza cliente
            if( nombres.isEmpty() || app.isEmpty() || apm.isEmpty()  ){
            JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else{
                if( Validations.validarNombres(nombres) || Validations.validarNombres(app) || Validations.validarNombres(apm) ){
                    JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                }
                else if( Validations.lenght(nombres, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    nameProv.requestFocus();
                }
                else if( Validations.lenght(app, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    appProv.requestFocus();
                }
                else if( Validations.lenght(apm, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    apmProv.requestFocus();
                }
                else{
                    int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar la información del cliente?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                    if(req == JOptionPane.YES_OPTION){
                        try{
                        storedProcedures.updateProv(nameProv.getText(), appProv.getText(), apmProv.getText(), getCodigoProv());
                        JOptionPane.showMessageDialog(null, "Proveedor actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        showProv();
                        nameProv.setText("");nameProv.setEnabled(false);
                        appProv.setText("");appProv.setEnabled(false);
                        apmProv.setText("");apmProv.setEnabled(false);
                        this.btnActualizar.setEnabled(false);
                        this.btnEliminar.setEnabled(false);
                        this.saveProv.setEnabled(false);
                        this.btnAgregar.setEnabled(false);
                        }
                        catch(SQLException e){}
                    }
                }
            }    
        }
        
        else if(this.showTel.getSelectedRows().length > 0){//actualiza telefono
            if( Validations.validarTel(tel) ){
                JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else if( Validations.lenght(tel, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 25 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                telProv.requestFocus();
            }
            else if( Validations.lenght(descTel, 30) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 30 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descTelProv.requestFocus();
            }
            else{
             int row = showTel.getSelectedRow();
                int cod = Integer.parseInt(showTel.getValueAt(row, 0).toString());
                int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste teléfono?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                    storedProcedures.updateTel(telProv.getText(), descTelProv.getText(), cod);
                    JOptionPane.showMessageDialog(null, "Telefono actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    this.showTel(getCodigoProv());
                    this.telProv.setText("");this.telProv.setEnabled(false);
                    this.descTelProv.setText("");this.descTelProv.setEnabled(false);
                    this.btnActualizar.setEnabled(false);
                    this.btnEliminar.setEnabled(false);
                    this.saveProv.setEnabled(false);
                    this.btnAgregar.setEnabled(false);
                    }
                    catch(SQLException e){}
                }   
            }
        }
        
        else if(this.showEmail.getSelectedRows().length > 0){//actualiza email
            if( Validations.lenght(email, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo incluyendo @example.com!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                emailProv.requestFocus();
            }
            else if( Validations.lenght(descEmail, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descEmailProv.requestFocus();
            }
            else{
                int row = showEmail.getSelectedRow();
                int cod = Integer.parseInt(showEmail.getValueAt(row, 0).toString());
                int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste correo?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                    storedProcedures.updateEmail(emailProv.getText(), descEmailProv.getText(), cod);
                    JOptionPane.showMessageDialog(null, "Correo actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    this.showEmail(getCodigoProv());
                    this.emailProv.setText("");this.emailProv.setEnabled(false);
                    this.descEmailProv.setText("");this.descEmailProv.setEnabled(false);
                    this.btnActualizar.setEnabled(false);
                    this.btnEliminar.setEnabled(false);
                    this.saveProv.setEnabled(false);
                    this.btnAgregar.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
            }
        }
        
        else if(this.showDom.getSelectedRows().length > 0){//actualiza domicilios
            if( calle.isEmpty() || colonia.isEmpty() || numExt.isEmpty() || cp.isEmpty() || ciudad.isEmpty() ){
                JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else{
                if( Validations.validarNumeros(numExt) || Validations.validarNumeros(cp) ||
                    Validations.validarNumInt(numInt) || Validations.validarNombres(ciudad)){
                    JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                }
                else if( Validations.lenght(calle, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    calleProv.requestFocus();
                }
                else if( Validations.lenght(colonia, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    colProv.requestFocus();
                }
                else if( Validations.lenght(numExt, 5) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 5 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    numExtProv.requestFocus();
                }
                else if( Validations.lenght(numInt, 4) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 4 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    numIntProv.requestFocus();
                }
                else if( Validations.ValidarCp(cp) ){
                    JOptionPane.showMessageDialog(null, "Ingresa 5 números en el código postal!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    cpProv.requestFocus();
                }
                else if( Validations.lenght(descrip, 40) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    descDomProv.requestFocus();
                }
                else if( estado.equals("Selecciona un estado..") ){
                    JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún estado.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    estadoProv.requestFocus();
                }
                else{
                    int row = showDom.getSelectedRow();
                    int cod = Integer.parseInt(showDom.getValueAt(row, 0).toString());
                    int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste domicilio?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                    if(req == JOptionPane.YES_OPTION){
                        try{
                            storedProcedures.updateDom(cod, calleProv.getText(), colProv.getText(), numIntProv.getText(), Integer.parseInt(numExtProv.getText()), Integer.parseInt(cpProv.getText()), descDomProv.getText(), (String)estadoProv.getSelectedItem(), cdProv.getText());
                            JOptionPane.showMessageDialog(null, "Domicilio actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                            this.showAddress(getCodigoProv());

                            calleProv.setText("");calleProv.setEnabled(false);
                            colProv.setText("");colProv.setEnabled(false);
                            numIntProv.setText("");numIntProv.setEnabled(false);
                            numExtProv.setText("");numExtProv.setEnabled(false);
                            cpProv.setText("");cpProv.setEnabled(false);
                            descDomProv.setText("");descDomProv.setEnabled(false);
                            estadoProv.setSelectedIndex(0);estadoProv.setEnabled(false);
                            cdProv.setText("");cdProv.setEnabled(false);

                            this.btnActualizar.setEnabled(false);
                            this.btnEliminar.setEnabled(false);
                            this.saveProv.setEnabled(false);
                            this.btnAgregar.setEnabled(false);
                        }
                        catch(SQLException e){}
                    }
                }
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void BtnAddDomProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddDomProvActionPerformed
        // TODO add your handling code here:
         this.btnAgregar.setEnabled(true);
        this.btnEliminar.setEnabled(false);
        this.btnActualizar.setEnabled(false);
        this.saveProv.setEnabled(false);
        
        this.nameProv.setEnabled(false);
        this.appProv.setEnabled(false);
        this.apmProv.setEnabled(false);
        
        this.calleProv.setText("");this.calleProv.setEnabled(true);
        this.colProv.setText("");this.colProv.setEnabled(true);
        this.cpProv.setText("");this.cpProv.setEnabled(true);
        this.numExtProv.setText("");this.numExtProv.setEnabled(true);
        this.numIntProv.setText("");this.numIntProv.setEnabled(true);
        this.descDomProv.setText("");this.descDomProv.setEnabled(true);
        this.estadoProv.setSelectedIndex(0);this.estadoProv.setEnabled(true);
        this.cdProv.setText("");this.cdProv.setEnabled(true);
        
        this.telProv.setText("");this.telProv.setEnabled(false);this.telProv.requestFocus();
        this.descTelProv.setText("");this.descTelProv.setEnabled(false);
        
        this.emailProv.setText("");this.emailProv.setEnabled(false);
        this.descEmailProv.setText("");this.descEmailProv.setEnabled(false);
        setFlagAdd(3);
        
    }//GEN-LAST:event_BtnAddDomProvActionPerformed

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
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAddDomProv;
    private javax.swing.JButton BtnAddEmailProv;
    private javax.swing.JTextField apmProv;
    private javax.swing.JTextField appProv;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAddProv;
    private javax.swing.JButton btnAddTelProv;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JTextField calleProv;
    private javax.swing.JTextField cdProv;
    private javax.swing.JTextField colProv;
    private javax.swing.JTextField cpProv;
    private javax.swing.JTextField descDomProv;
    private javax.swing.JTextField descEmailProv;
    private javax.swing.JTextField descTelProv;
    private javax.swing.JTextField emailProv;
    private javax.swing.JComboBox<String> estadoProv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField nameProv;
    private javax.swing.JTextField numExtProv;
    private javax.swing.JTextField numIntProv;
    private javax.swing.JButton saveProv;
    private javax.swing.JTable showDom;
    private javax.swing.JTable showEmail;
    private javax.swing.JTable showPersonalData;
    private javax.swing.JTable showTel;
    private javax.swing.JTextField telProv;
    // End of variables declaration//GEN-END:variables
}
