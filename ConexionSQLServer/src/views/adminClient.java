/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexionsqlserver.Login;
import conexionsqlserver.Validations;
import conexionsqlserver.storedProcedures;
import conexionsqlserver.temporalVariables;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author JoséFrancisco
 */
public class adminClient extends javax.swing.JFrame {
    
    public static  String sexo      = "";
    private static int    flag      = 0;
    private static int    codigoCte = 0;
    private static int    flagAdd   = 0;

    public static String getSexo() {
        return sexo;
    }
    public static void setSexo(String aSexo) {
        sexo = aSexo;
    }

    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int aFlag) {
        flag = aFlag;
    }
    
    public static int getCodigoCte() {
        return codigoCte;
    }
    
    public static void setCodigoCte(int aCodigoCte) {
        codigoCte = aCodigoCte;
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

    
    
    int idCte     = 0,
        idDom     = 0,
        idColonia = 0,
        idEdo     = 0,
        idCiudad  = 0,
        idTel     = 0,
        idCorreo  = 0;
    
    public adminClient() {
        initComponents();
    }
    
    public void showClient(){
        DefaultTableModel cliente = (DefaultTableModel) tableShowPersonalData.getModel();
        cliente.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query("select * from cliente where activo="+1+" order by nombres_cliente");
        
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
                v.add(res.getString("cod_telefono"));
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
                v.add(res.getString("cod_correo"));
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

        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel9 = new javax.swing.JLabel();
        numExtCte = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        coloniaCte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        descDomCte = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        calleCte = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        numIntCte = new javax.swing.JTextField();
        cpCte = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        estadoCte = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        ciudadCte = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        sexoFemCte = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        appCte = new javax.swing.JTextField();
        apmCte = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        nameCte = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        sexoMascCte = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        btnSaveCte = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        telCte = new javax.swing.JTextField();
        descTelCte = new javax.swing.JTextField();
        btnUpdateCte = new javax.swing.JButton();
        btnDeleteCte = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel11 = new javax.swing.JLabel();
        descEmailCte = new javax.swing.JTextField();
        emailCte = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnAddCte = new javax.swing.JButton();
        btnAñadirCampos = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableShowPersonalData = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        showEmailCte = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        showTelCte = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        a = new javax.swing.JScrollPane();
        tableDomCte = new javax.swing.JTable();
        btnAddTelCte = new javax.swing.JButton();
        btnAddEmailCte = new javax.swing.JButton();
        btnAddAddressCte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        jLayeredPane5.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio"));
        jLayeredPane2.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLayeredPane2.setPreferredSize(new java.awt.Dimension(342, 263));

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Descripción");

        numExtCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        numExtCte.setEnabled(false);
        numExtCte.setPreferredSize(new java.awt.Dimension(200, 20));
        numExtCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numExtCteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Num. Int");

        coloniaCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        coloniaCte.setEnabled(false);
        coloniaCte.setPreferredSize(new java.awt.Dimension(200, 20));
        coloniaCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coloniaCteActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Colonia");

        descDomCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descDomCte.setEnabled(false);
        descDomCte.setPreferredSize(new java.awt.Dimension(200, 20));
        descDomCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descDomCteActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("CP");

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Calle");

        calleCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        calleCte.setEnabled(false);
        calleCte.setMinimumSize(new java.awt.Dimension(200, 20));
        calleCte.setPreferredSize(new java.awt.Dimension(200, 20));
        calleCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calleCteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Num. Ext");

        numIntCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        numIntCte.setEnabled(false);
        numIntCte.setPreferredSize(new java.awt.Dimension(200, 20));
        numIntCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numIntCteActionPerformed(evt);
            }
        });

        cpCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        cpCte.setEnabled(false);
        cpCte.setPreferredSize(new java.awt.Dimension(200, 20));
        cpCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpCteActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Estado");

        estadoCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        estadoCte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un estado..", "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Coahuila ", "Colima", "Durango", "Edo. México", "Guanajuato", "Guerrero ", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));
        estadoCte.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Ciudad");

        ciudadCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        ciudadCte.setEnabled(false);
        ciudadCte.setPreferredSize(new java.awt.Dimension(200, 20));
        ciudadCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadCteActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(numExtCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(coloniaCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(descDomCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(calleCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(numIntCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cpCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(estadoCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(ciudadCte, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cpCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(numExtCte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numIntCte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(descDomCte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(coloniaCte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calleCte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(estadoCte, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ciudadCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calleCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(coloniaCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(numExtCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(numIntCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descDomCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estadoCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudadCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personales"));
        jLayeredPane1.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        sexoFemCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        sexoFemCte.setForeground(new java.awt.Color(51, 51, 51));
        sexoFemCte.setText("Femenino");
        sexoFemCte.setEnabled(false);
        sexoFemCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoFemCteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Apellido Materno");

        appCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        appCte.setEnabled(false);
        appCte.setPreferredSize(new java.awt.Dimension(200, 20));
        appCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appCteActionPerformed(evt);
            }
        });

        apmCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        apmCte.setEnabled(false);
        apmCte.setPreferredSize(new java.awt.Dimension(200, 20));
        apmCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apmCteActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Sexo");

        nameCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        nameCte.setEnabled(false);
        nameCte.setPreferredSize(new java.awt.Dimension(200, 20));
        nameCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameCteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Nombre(s)");

        sexoMascCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        sexoMascCte.setForeground(new java.awt.Color(51, 51, 51));
        sexoMascCte.setText("Masculino");
        sexoMascCte.setEnabled(false);
        sexoMascCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoMascCteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Apellido Paterno");

        jLayeredPane1.setLayer(sexoFemCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(appCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(apmCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(nameCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(sexoMascCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nameCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(apmCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(sexoMascCte)
                        .addGap(10, 10, 10)
                        .addComponent(sexoFemCte))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {apmCte, appCte, nameCte});

        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(nameCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apmCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(sexoMascCte)
                    .addComponent(sexoFemCte))
                .addContainerGap())
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {apmCte, appCte, nameCte});

        btnSaveCte.setBackground(new java.awt.Color(0, 153, 0));
        btnSaveCte.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        btnSaveCte.setForeground(new java.awt.Color(250, 250, 250));
        btnSaveCte.setText("Guardar");
        btnSaveCte.setActionCommand("Agregar");
        btnSaveCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSaveCte.setEnabled(false);
        btnSaveCte.setPreferredSize(new java.awt.Dimension(150, 35));
        btnSaveCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCteActionPerformed(evt);
            }
        });

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfono"));
        jLayeredPane4.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLayeredPane4.setPreferredSize(new java.awt.Dimension(342, 91));

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Teléfono");

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Descripción");

        telCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        telCte.setEnabled(false);
        telCte.setPreferredSize(new java.awt.Dimension(200, 20));

        descTelCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descTelCte.setEnabled(false);
        descTelCte.setPreferredSize(new java.awt.Dimension(200, 20));
        descTelCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descTelCteActionPerformed(evt);
            }
        });

        jLayeredPane4.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(telCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(descTelCte, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(4, 4, 4)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(telCte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descTelCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(telCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(descTelCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnUpdateCte.setBackground(new java.awt.Color(0, 51, 102));
        btnUpdateCte.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        btnUpdateCte.setForeground(new java.awt.Color(250, 250, 250));
        btnUpdateCte.setText("Actualizar");
        btnUpdateCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateCte.setEnabled(false);
        btnUpdateCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCteActionPerformed(evt);
            }
        });

        btnDeleteCte.setBackground(new java.awt.Color(251, 51, 51));
        btnDeleteCte.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        btnDeleteCte.setForeground(new java.awt.Color(250, 250, 250));
        btnDeleteCte.setText("Eliminar");
        btnDeleteCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCte.setEnabled(false);
        btnDeleteCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCteActionPerformed(evt);
            }
        });

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("E-mail"));
        jLayeredPane3.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLayeredPane3.setPreferredSize(new java.awt.Dimension(342, 91));

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Descripción");

        descEmailCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descEmailCte.setEnabled(false);
        descEmailCte.setPreferredSize(new java.awt.Dimension(200, 20));
        descEmailCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descEmailCteActionPerformed(evt);
            }
        });

        emailCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        emailCte.setEnabled(false);
        emailCte.setPreferredSize(new java.awt.Dimension(200, 20));
        emailCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailCteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("E-mail");

        jLayeredPane3.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(descEmailCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(emailCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(4, 4, 4)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailCte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descEmailCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descEmailCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddCte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1485408397_Add-Male-User.png"))); // NOI18N
        btnAddCte.setText("Nuevo");
        btnAddCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCteActionPerformed(evt);
            }
        });

        btnAñadirCampos.setBackground(new java.awt.Color(51, 102, 255));
        btnAñadirCampos.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        btnAñadirCampos.setForeground(new java.awt.Color(250, 250, 250));
        btnAñadirCampos.setText("Añadir");
        btnAñadirCampos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAñadirCampos.setEnabled(false);
        btnAñadirCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirCamposActionPerformed(evt);
            }
        });

        reset.setBackground(new java.awt.Color(51, 51, 51));
        reset.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        reset.setForeground(new java.awt.Color(250, 250, 250));
        reset.setText("Cancelar");
        reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jLayeredPane5.setLayer(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnSaveCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnUpdateCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnDeleteCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnAddCte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnAñadirCampos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(reset, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .addComponent(jLayeredPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane5Layout.createSequentialGroup()
                            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnSaveCte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdateCte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDeleteCte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAñadirCampos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddCte)
                .addContainerGap())
        );

        jLayeredPane5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLayeredPane1, jLayeredPane2});

        jLayeredPane5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAñadirCampos, btnDeleteCte, btnSaveCte, btnUpdateCte});

        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCte, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveCte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAñadirCampos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateCte)
                    .addComponent(btnDeleteCte))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLayeredPane5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAñadirCampos, btnDeleteCte, btnSaveCte, btnUpdateCte});

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
        if (tableShowPersonalData.getColumnModel().getColumnCount() > 0) {
            tableShowPersonalData.getColumnModel().getColumn(0).setMinWidth(0);
            tableShowPersonalData.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableShowPersonalData.getColumnModel().getColumn(0).setMaxWidth(0);
            tableShowPersonalData.getColumnModel().getColumn(1).setMinWidth(120);
            tableShowPersonalData.getColumnModel().getColumn(1).setPreferredWidth(120);
            tableShowPersonalData.getColumnModel().getColumn(1).setMaxWidth(190);
            tableShowPersonalData.getColumnModel().getColumn(2).setMinWidth(80);
            tableShowPersonalData.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableShowPersonalData.getColumnModel().getColumn(2).setMaxWidth(120);
            tableShowPersonalData.getColumnModel().getColumn(3).setMinWidth(80);
            tableShowPersonalData.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableShowPersonalData.getColumnModel().getColumn(3).setMaxWidth(120);
            tableShowPersonalData.getColumnModel().getColumn(4).setMinWidth(70);
            tableShowPersonalData.getColumnModel().getColumn(4).setPreferredWidth(70);
            tableShowPersonalData.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        showEmailCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showEmailCte.setForeground(new java.awt.Color(102, 102, 102));
        showEmailCte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "Email", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        showEmailCte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showEmailCteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(showEmailCte);
        if (showEmailCte.getColumnModel().getColumnCount() > 0) {
            showEmailCte.getColumnModel().getColumn(0).setMinWidth(0);
            showEmailCte.getColumnModel().getColumn(0).setPreferredWidth(0);
            showEmailCte.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        showTelCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        showTelCte.setForeground(new java.awt.Color(102, 102, 102));
        showTelCte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "Teléfono", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showTelCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showTelCte.setGridColor(new java.awt.Color(0, 153, 204));
        showTelCte.setRowHeight(35);
        showTelCte.setRowMargin(5);
        showTelCte.getTableHeader().setReorderingAllowed(false);
        showTelCte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showTelCteMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(showTelCte);
        if (showTelCte.getColumnModel().getColumnCount() > 0) {
            showTelCte.getColumnModel().getColumn(0).setMinWidth(0);
            showTelCte.getColumnModel().getColumn(0).setPreferredWidth(0);
            showTelCte.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLabel17.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Selecciona un Cliente para (ver datos, actualizar u eliminar)");

        a.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N

        tableDomCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableDomCte.setForeground(new java.awt.Color(102, 102, 102));
        tableDomCte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "Calle", "Colonia", "No.Ext", "No.Int", "CP", "Descripción", "Estado", "Ciudad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        tableDomCte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDomCteMouseClicked(evt);
            }
        });
        a.setViewportView(tableDomCte);
        if (tableDomCte.getColumnModel().getColumnCount() > 0) {
            tableDomCte.getColumnModel().getColumn(0).setMinWidth(0);
            tableDomCte.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableDomCte.getColumnModel().getColumn(0).setMaxWidth(0);
            tableDomCte.getColumnModel().getColumn(3).setMinWidth(50);
            tableDomCte.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableDomCte.getColumnModel().getColumn(3).setMaxWidth(70);
            tableDomCte.getColumnModel().getColumn(4).setMinWidth(50);
            tableDomCte.getColumnModel().getColumn(4).setPreferredWidth(50);
            tableDomCte.getColumnModel().getColumn(4).setMaxWidth(70);
            tableDomCte.getColumnModel().getColumn(5).setMinWidth(60);
            tableDomCte.getColumnModel().getColumn(5).setPreferredWidth(60);
            tableDomCte.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        btnAddTelCte.setBackground(new java.awt.Color(51, 102, 255));
        btnAddTelCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddTelCte.setForeground(new java.awt.Color(255, 250, 250));
        btnAddTelCte.setText("Añadir");
        btnAddTelCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddTelCte.setEnabled(false);
        btnAddTelCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTelCteActionPerformed(evt);
            }
        });

        btnAddEmailCte.setBackground(new java.awt.Color(51, 102, 255));
        btnAddEmailCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddEmailCte.setForeground(new java.awt.Color(255, 250, 250));
        btnAddEmailCte.setText("Añadir");
        btnAddEmailCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddEmailCte.setEnabled(false);
        btnAddEmailCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmailCteActionPerformed(evt);
            }
        });

        btnAddAddressCte.setBackground(new java.awt.Color(51, 102, 255));
        btnAddAddressCte.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddAddressCte.setForeground(new java.awt.Color(255, 250, 250));
        btnAddAddressCte.setText("Añadir");
        btnAddAddressCte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddAddressCte.setEnabled(false);
        btnAddAddressCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAddressCteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddTelCte)
                                    .addComponent(btnAddEmailCte)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                            .addComponent(btnAddAddressCte)
                            .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnAddTelCte)
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnAddEmailCte)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnAddAddressCte)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cpCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpCteActionPerformed

    private void numIntCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numIntCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numIntCteActionPerformed

    private void calleCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calleCteActionPerformed

    private void descDomCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descDomCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descDomCteActionPerformed

    private void coloniaCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coloniaCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coloniaCteActionPerformed

    private void numExtCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numExtCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numExtCteActionPerformed

    private void descTelCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descTelCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descTelCteActionPerformed

    private void sexoMascCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoMascCteActionPerformed
        this.sexoFemCte.setSelected(false);
        setFlag(1);
        setSexo("Masculino");
    }//GEN-LAST:event_sexoMascCteActionPerformed

    private void nameCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameCteActionPerformed

    private void apmCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apmCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apmCteActionPerformed

    private void appCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appCteActionPerformed

    private void emailCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailCteActionPerformed

    private void descEmailCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descEmailCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descEmailCteActionPerformed

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

    private void btnAddCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCteActionPerformed
        /*Datos personales */ 
        nameCte.setEnabled(true);
        nameCte.setText("");
        nameCte.requestFocus();
        this.appCte.setEnabled(true);
        appCte.setText("");
        this.apmCte.setEnabled(true);
        apmCte.setText("");
        this.sexoMascCte.setEnabled(true);
        this.sexoMascCte.setSelected(false);
        this.sexoFemCte.setEnabled(true);
        this.sexoFemCte.setSelected(false);
        /*Domicilio */ 
        this.calleCte.setEnabled(true);
        calleCte.setText("");
        this.coloniaCte.setEnabled(true);
        coloniaCte.setText("");
        this.numExtCte.setEnabled(true);
        numExtCte.setText("");
        this.numIntCte.setEnabled(true);
        numIntCte.setText("");
        this.cpCte.setEnabled(true);
        cpCte.setText("");
        this.descDomCte.setEnabled(true);
        descDomCte.setText("");
        this.estadoCte.setEnabled(true);
        this.estadoCte.setSelectedIndex(0);
        this.ciudadCte.setEnabled(true);
        this.ciudadCte.setText("");
        /*  Telefono */ 
        this.telCte.setEnabled(true);
        telCte.setText("");
        this.descTelCte.setEnabled(true);
        descTelCte.setText("");
        /*Email */      
        this.emailCte.setEnabled(true);
        emailCte.setText("");
        this.descEmailCte.setEnabled(true);
        descEmailCte.setText("");
        
        this.btnSaveCte.setEnabled(true);
        this.btnDeleteCte.setEnabled(false);
        this.btnUpdateCte.setEnabled(false);
        this.btnAñadirCampos.setEnabled(false);
        
        this.btnAddTelCte.setEnabled(false);
        this.btnAddEmailCte.setEnabled(false);
        this.btnAddAddressCte.setEnabled(false);
        
        /*tables*/
        tableShowPersonalData.clearSelection();
        tableDomCte.clearSelection();
        showTelCte.clearSelection();
        showEmailCte.clearSelection();
        
        cleanAddress();
        cleanTel();
        cleanEmail();
    }//GEN-LAST:event_btnAddCteActionPerformed

    private void btnSaveCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCteActionPerformed
        
        String nombres   = this.nameCte.getText();
        String app       = this.appCte.getText();
        String apm       = this.apmCte.getText();
        String sex       = getSexo();
        int sexoValid    = getFlag();
        String calle     = this.calleCte.getText();
        String colonia   = this.coloniaCte.getText();
        String numExt    = this.numExtCte.getText();
        String numInt    = this.numIntCte.getText();
        String cp        = this.cpCte.getText();
        String descrip   = this.descDomCte.getText();
        String estado    = (String)estadoCte.getSelectedItem();
        String ciudad    = this.ciudadCte.getText();
        String tel       = this.telCte.getText();
        String descTel   = this.descTelCte.getText();
        String email     = this.emailCte.getText();
        String descEmail = this.descEmailCte.getText();
        
        if( nombres.isEmpty() || app.isEmpty() || apm.isEmpty() || sexoValid == 0 || calle.isEmpty() ||
            colonia.isEmpty() || numExt.isEmpty() || cp.isEmpty() || ciudad.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
        }
        else{
            if( Validations.validarNombres(nombres) || Validations.validarNombres(app) || Validations.validarNombres(apm) || Validations.validarNumeros(numExt) || Validations.validarNumeros(cp) ||
                Validations.validarNumInt(numInt) || Validations.validarTel(tel) ||  Validations.validarNombres(ciudad)){
                JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else if( Validations.lenght(nombres, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                nameCte.requestFocus();
            }
            else if( Validations.lenght(app, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                appCte.requestFocus();
            }
            else if( Validations.lenght(apm, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                apmCte.requestFocus();
            }
            else if( Validations.lenght(calle, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                calleCte.requestFocus();
            }
            else if( Validations.lenght(colonia, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                coloniaCte.requestFocus();
            }
            else if( Validations.lenght(numExt, 5) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 5 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                numExtCte.requestFocus();
            }
            else if( Validations.lenght(numInt, 4) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 4 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                numIntCte.requestFocus();
            }
            else if( Validations.ValidarCp(cp) ){
                JOptionPane.showMessageDialog(null, "Ingresa 5 números en el código postal!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                cpCte.requestFocus();
            }
            else if( Validations.lenght(descrip, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descDomCte.requestFocus();
            }
            else if( Validations.lenght(tel, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 25 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                telCte.requestFocus();
            }
            else if( Validations.lenght(descTel, 30) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 30 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descTelCte.requestFocus();
            }
            else if( Validations.lenght(email, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo incluyendo @example.com!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                emailCte.requestFocus();
            }
            else if( Validations.lenght(descEmail, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descEmailCte.requestFocus();
            }
            else if( estado.equals("Selecciona un estado..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún estado.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                estadoCte.requestFocus();
            }
            else if( Validations.lenght(ciudad, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                ciudadCte.requestFocus();
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
                    
                    storedProcedures.addCliente(nombres, app, apm, sex);
                    idCte = storedProcedures.getID("cod_cliente", "cliente");
                    
                    storedProcedures.addDomicilio(calle, numInt, numE, descrip);
                    idDom = storedProcedures.getID("cod_domicilio", "domicilio");
                    storedProcedures.addDomicilio_Cte(idDom, idCte);
                    
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
                        storedProcedures.addTel_Cte(idTel, idCte);
                    }
                    
                    if( !email.isEmpty()){
                        storedProcedures.addEmail(email, descEmail);
                        idCorreo = storedProcedures.getID("cod_correo", "correo");
                        storedProcedures.addEmail_Cte(idCorreo, idCte);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Cliente agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    nameCte.setText("");
                    appCte.setText("");
                    apmCte.setText("");
                    sexoMascCte.setSelected(false);
                    sexoFemCte.setSelected(false);
                    calleCte.setText("");
                    coloniaCte.setText("");
                    numExtCte.setText("");
                    numIntCte.setText("");
                    cpCte.setText("");
                    descDomCte.setText("");
                    estadoCte.setSelectedIndex(0);
                    ciudadCte.setText("");
                    telCte.setText("");
                    descTelCte.setText("");
                    emailCte.setText("");
                    descEmailCte.setText("");
                    showClient();
                }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(this, "Hubo un error al almacenar el cliente, intenta de nuevo", ":( FATAL!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnSaveCteActionPerformed

    private void sexoFemCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoFemCteActionPerformed
        this.sexoMascCte.setSelected(false);
        setFlag(1);
        setSexo("Femenino");
    }//GEN-LAST:event_sexoFemCteActionPerformed

    private void tableShowPersonalDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShowPersonalDataMouseClicked
        this.btnSaveCte.setEnabled(false);
        this.btnAñadirCampos.setEnabled(false);
        this.btnDeleteCte.setEnabled(true);
        this.btnUpdateCte.setEnabled(true);
        
        this.btnAddTelCte.setEnabled(true);
        this.btnAddEmailCte.setEnabled(true);
        this.btnAddAddressCte.setEnabled(true);
        
        tableDomCte.clearSelection();
        showTelCte.clearSelection();
        showEmailCte.clearSelection();
        
        calleCte.setText("");
        calleCte.setEnabled(false);
        coloniaCte.setText("");
        coloniaCte.setEnabled(false);
        numExtCte.setText("");
        numExtCte.setEnabled(false);
        numIntCte.setText("");
        numIntCte.setEnabled(false);
        cpCte.setText("");
        cpCte.setEnabled(false);
        descDomCte.setText("");
        descDomCte.setEnabled(false);
        estadoCte.setSelectedIndex(0);
        estadoCte.setEnabled(false);
        ciudadCte.setEnabled(false);
        ciudadCte.setText("");
        telCte.setText("");
        telCte.setEnabled(false);
        descTelCte.setText("");
        descTelCte.setEnabled(false);
        emailCte.setText("");
        emailCte.setEnabled(false);
        descEmailCte.setText("");
        descEmailCte.setEnabled(false);
        
        try{
            int row = tableShowPersonalData.getSelectedRow();
            setCodigoCte(Integer.parseInt(tableShowPersonalData.getValueAt(row, 0).toString()));
            res = conexionsqlserver.ConnectionDB.Query("select * from cliente where cod_cliente='"+getCodigoCte()+"'");
            while(res.next()){
                nameCte.setText(res.getString("nombres_cliente"));
                nameCte.setEnabled(true);
                appCte.setText(res.getString("apellidop_cliente"));
                appCte.setEnabled(true);
                apmCte.setText(res.getString("apellidom_cliente"));
                apmCte.setEnabled(true);
                sexoMascCte.setEnabled(true);
                sexoFemCte.setEnabled(true);

                String getSex = res.getString("sexo_cliente");
                if( getSex.equals("Masculino") ){
                    sexoMascCte.setSelected(true);
                    sexoFemCte.setSelected(false);
                    setFlag(1);
                    setSexo("Masculino");
                }
                else{
                    sexoMascCte.setSelected(false);
                    sexoFemCte.setSelected(true);
                    setFlag(2);
                    setSexo("Femenino");
                }
            }
            
            showAddress(getCodigoCte());
            showTel(getCodigoCte());
            this.showEmail(getCodigoCte());
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableShowPersonalDataMouseClicked

    private void ciudadCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ciudadCteActionPerformed

    private void btnAddAddressCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAddressCteActionPerformed
        this.btnAñadirCampos.setEnabled(true);
        this.btnDeleteCte.setEnabled(false);
        this.btnUpdateCte.setEnabled(false);
        this.btnSaveCte.setEnabled(false);
        
        this.nameCte.setEnabled(false);
        this.appCte.setEnabled(false);
        this.apmCte.setEnabled(false);
        this.sexoMascCte.setEnabled(false);
        this.sexoFemCte.setEnabled(false);
        
        this.calleCte.setText("");this.calleCte.setEnabled(true);this.calleCte.requestFocus();
        this.coloniaCte.setText("");this.coloniaCte.setEnabled(true);
        this.numExtCte.setText("");this.numExtCte.setEnabled(true);
        this.numIntCte.setText("");this.numIntCte.setEnabled(true);
        this.cpCte.setText("");this.cpCte.setEnabled(true);
        this.descDomCte.setText("");this.descDomCte.setEnabled(true);
        this.estadoCte.setSelectedIndex(0);this.estadoCte.setEnabled(true);
        this.ciudadCte.setText("");this.ciudadCte.setEnabled(true);
        
        this.telCte.setText("");this.telCte.setEnabled(false);
        this.descTelCte.setText("");this.descTelCte.setEnabled(false);
        this.emailCte.setText("");this.emailCte.setEnabled(false);
        this.descEmailCte.setText("");this.descEmailCte.setEnabled(false);
        
        setFlagAdd(3);
    }//GEN-LAST:event_btnAddAddressCteActionPerformed

    private void btnAddEmailCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmailCteActionPerformed
        this.btnAñadirCampos.setEnabled(true);
        this.btnDeleteCte.setEnabled(false);
        this.btnUpdateCte.setEnabled(false);
        this.btnSaveCte.setEnabled(false);
        
        this.nameCte.setEnabled(false);
        this.appCte.setEnabled(false);
        this.apmCte.setEnabled(false);
        this.sexoMascCte.setEnabled(false);
        this.sexoFemCte.setEnabled(false);
        
        this.calleCte.setText("");this.calleCte.setEnabled(false);
        this.coloniaCte.setText("");this.coloniaCte.setEnabled(false);
        this.numExtCte.setText("");this.numExtCte.setEnabled(false);
        this.numIntCte.setText("");this.numIntCte.setEnabled(false);
        this.cpCte.setText("");this.cpCte.setEnabled(false);
        this.descDomCte.setText("");this.descDomCte.setEnabled(false);
        this.estadoCte.setSelectedIndex(0);this.estadoCte.setEnabled(false);
        this.ciudadCte.setText("");this.ciudadCte.setEnabled(false);
        
        this.telCte.setText("");this.telCte.setEnabled(false);
        this.descTelCte.setText("");this.descTelCte.setEnabled(false);
        
        this.emailCte.setText("");this.emailCte.setEnabled(true);this.emailCte.requestFocus();
        this.descEmailCte.setText("");this.descEmailCte.setEnabled(true);
        setFlagAdd(2);
    }//GEN-LAST:event_btnAddEmailCteActionPerformed

    private void btnAddTelCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTelCteActionPerformed
        this.btnAñadirCampos.setEnabled(true);
        this.btnDeleteCte.setEnabled(false);
        this.btnUpdateCte.setEnabled(false);
        this.btnSaveCte.setEnabled(false);
        
        this.nameCte.setEnabled(false);
        this.appCte.setEnabled(false);
        this.apmCte.setEnabled(false);
        this.sexoMascCte.setEnabled(false);
        this.sexoFemCte.setEnabled(false);
        
        this.calleCte.setText("");this.calleCte.setEnabled(false);
        this.coloniaCte.setText("");this.coloniaCte.setEnabled(false);
        this.numExtCte.setText("");this.numExtCte.setEnabled(false);
        this.numIntCte.setText("");this.numIntCte.setEnabled(false);
        this.cpCte.setText("");this.cpCte.setEnabled(false);
        this.descDomCte.setText("");this.descDomCte.setEnabled(false);
        this.estadoCte.setSelectedIndex(0);this.estadoCte.setEnabled(false);
        this.ciudadCte.setText("");this.ciudadCte.setEnabled(false);
        
        this.telCte.setText("");this.telCte.setEnabled(true);this.telCte.requestFocus();
        this.descTelCte.setText("");this.descTelCte.setEnabled(true);
        
        this.emailCte.setText("");this.emailCte.setEnabled(false);
        this.descEmailCte.setText("");this.descEmailCte.setEnabled(false);
        setFlagAdd(1);
    }//GEN-LAST:event_btnAddTelCteActionPerformed

    private void btnDeleteCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCteActionPerformed
        if(this.tableShowPersonalData.getSelectedRows().length > 0){//elimina logicamente un cliente
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste cliente?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteCte(getCodigoCte());
                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                showClient();
                cleanAddress();
                cleanTel();
                cleanEmail();
                nameCte.setText("");nameCte.setEnabled(false);
                appCte.setText("");appCte.setEnabled(false);
                apmCte.setText("");apmCte.setEnabled(false);
                sexoMascCte.setSelected(false);sexoMascCte.setEnabled(false);
                sexoFemCte.setSelected(false);sexoFemCte.setEnabled(false);
                this.btnDeleteCte.setEnabled(false);
                this.btnUpdateCte.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
        
        else if(this.showTelCte.getSelectedRows().length > 0){//elimina un registro de telefono de un cliente
            int row = showTelCte.getSelectedRow();
            int cod = Integer.parseInt(showTelCte.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste telefono?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteTel_Cte(cod);
                storedProcedures.deleteTel(cod);
                JOptionPane.showMessageDialog(null, "Telefono eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                this.showTel(getCodigoCte());
                tableShowPersonalData.clearSelection();
                telCte.setText("");telCte.setEnabled(false);
                descTelCte.setText("");descTelCte.setEnabled(false);
                this.btnDeleteCte.setEnabled(false);
                this.btnUpdateCte.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
        
        else if(this.showEmailCte.getSelectedRows().length > 0){//elimina un registro de correo de un cliente
            int row = showEmailCte.getSelectedRow();
            int cod = Integer.parseInt(showEmailCte.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste correo?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteEmail_Cte(cod);
                storedProcedures.deleteEmail(cod);
                JOptionPane.showMessageDialog(null, "Correo eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                this.showEmail(getCodigoCte());
                tableShowPersonalData.clearSelection();
                emailCte.setText("");emailCte.setEnabled(false);
                descEmailCte.setText("");descEmailCte.setEnabled(false);
                this.btnDeleteCte.setEnabled(false);
                this.btnUpdateCte.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
        
        else if(this.tableDomCte.getSelectedRows().length > 0){//elimina un registro de domicilio de un cliente
            int row = tableDomCte.getSelectedRow();
            int cod = Integer.parseInt(tableDomCte.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste domicilio?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                storedProcedures.deleteDom(cod);
                JOptionPane.showMessageDialog(null, "Domicilio eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                this.showAddress(getCodigoCte());
                tableDomCte.clearSelection();
                calleCte.setText("");calleCte.setEnabled(false);
                coloniaCte.setText("");coloniaCte.setEnabled(false);
                numIntCte.setText("");numIntCte.setEnabled(false);
                numExtCte.setText("");numExtCte.setEnabled(false);
                cpCte.setText("");cpCte.setEnabled(false);
                descDomCte.setText("");descDomCte.setEnabled(false);
                estadoCte.setSelectedIndex(0);estadoCte.setEnabled(false);
                ciudadCte.setText("");ciudadCte.setEnabled(false);
                this.btnDeleteCte.setEnabled(false);
                this.btnUpdateCte.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
    }//GEN-LAST:event_btnDeleteCteActionPerformed

    private void btnUpdateCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCteActionPerformed
        String nombres   = this.nameCte.getText();
        String app       = this.appCte.getText();
        String apm       = this.apmCte.getText();
        String sex       = getSexo();
        int sexoValid    = getFlag();
        String calle     = this.calleCte.getText();
        String colonia   = this.coloniaCte.getText();
        String numExt    = this.numExtCte.getText();
        String numInt    = this.numIntCte.getText();
        String cp        = this.cpCte.getText();
        String descrip   = this.descDomCte.getText();
        String estado    = (String)estadoCte.getSelectedItem();
        String ciudad    = this.ciudadCte.getText();
        String tel       = this.telCte.getText();
        String descTel   = this.descTelCte.getText();
        String email     = this.emailCte.getText();
        String descEmail = this.descEmailCte.getText();
        
        if(this.tableShowPersonalData.getSelectedRows().length > 0){//actualiza cliente
            if( nombres.isEmpty() || app.isEmpty() || apm.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else{
                if( Validations.validarNombres(nombres) || Validations.validarNombres(app) || Validations.validarNombres(apm) ){
                    JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                }
                else if( Validations.lenght(nombres, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    nameCte.requestFocus();
                }
                else if( Validations.lenght(app, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    appCte.requestFocus();
                }
                else if( Validations.lenght(apm, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    apmCte.requestFocus();
                }
                else{
                    int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar la información del cliente?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                    if(req == JOptionPane.YES_OPTION){
                        try{
                        storedProcedures.updateCte(nameCte.getText(), appCte.getText(), apmCte.getText(), getSexo(), getCodigoCte());
                        JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        showClient();
                        nameCte.setText("");nameCte.setEnabled(false);
                        appCte.setText("");appCte.setEnabled(false);
                        apmCte.setText("");apmCte.setEnabled(false);
                        sexoFemCte.setSelected(false);sexoFemCte.setEnabled(false);
                        sexoMascCte.setSelected(false);sexoMascCte.setEnabled(false);
                        this.btnUpdateCte.setEnabled(false);
                        this.btnDeleteCte.setEnabled(false);
                        this.btnSaveCte.setEnabled(false);
                        this.btnAñadirCampos.setEnabled(false);
                        }
                        catch(SQLException e){}
                    }
                }
            }    
        }
        
        else if(this.showTelCte.getSelectedRows().length > 0){//actualiza telefono
            if( Validations.validarTel(tel) ){
                JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else if( Validations.lenght(tel, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 25 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                telCte.requestFocus();
            }
            else if( Validations.lenght(descTel, 30) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 30 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descTelCte.requestFocus();
            }
            else{
             int row = showTelCte.getSelectedRow();
                int cod = Integer.parseInt(showTelCte.getValueAt(row, 0).toString());
                int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste teléfono?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                    storedProcedures.updateTel(telCte.getText(), descTelCte.getText(), cod);
                    JOptionPane.showMessageDialog(null, "Telefono actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    this.showTel(getCodigoCte());
                    this.telCte.setText("");this.telCte.setEnabled(false);
                    this.descTelCte.setText("");this.descTelCte.setEnabled(false);
                    this.btnUpdateCte.setEnabled(false);
                    this.btnDeleteCte.setEnabled(false);
                    this.btnSaveCte.setEnabled(false);
                    this.btnAñadirCampos.setEnabled(false);
                    }
                    catch(SQLException e){}
                }   
            }
        }
        
        else if(this.showEmailCte.getSelectedRows().length > 0){//actualiza email
            if( Validations.lenght(email, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo incluyendo @example.com!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                emailCte.requestFocus();
            }
            else if( Validations.lenght(descEmail, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descEmailCte.requestFocus();
            }
            else{
                int row = showEmailCte.getSelectedRow();
                int cod = Integer.parseInt(showEmailCte.getValueAt(row, 0).toString());
                int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste correo?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                    storedProcedures.updateEmail(emailCte.getText(), descEmailCte.getText(), cod);
                    JOptionPane.showMessageDialog(null, "Correo actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    this.showEmail(getCodigoCte());
                    this.emailCte.setText("");this.emailCte.setEnabled(false);
                    this.descEmailCte.setText("");this.descEmailCte.setEnabled(false);
                    this.btnUpdateCte.setEnabled(false);
                    this.btnDeleteCte.setEnabled(false);
                    this.btnSaveCte.setEnabled(false);
                    this.btnAñadirCampos.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
            }
        }
        
        else if(this.tableDomCte.getSelectedRows().length > 0){//actualiza domicilios
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
                    calleCte.requestFocus();
                }
                else if( Validations.lenght(colonia, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    coloniaCte.requestFocus();
                }
                else if( Validations.lenght(numExt, 5) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 5 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    numExtCte.requestFocus();
                }
                else if( Validations.lenght(numInt, 4) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 4 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    numIntCte.requestFocus();
                }
                else if( Validations.ValidarCp(cp) ){
                    JOptionPane.showMessageDialog(null, "Ingresa 5 números en el código postal!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    cpCte.requestFocus();
                }
                else if( Validations.lenght(descrip, 40) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    descDomCte.requestFocus();
                }
                else if( estado.equals("Selecciona un estado..") ){
                    JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún estado.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    estadoCte.requestFocus();
                }
                else{
                    int row = tableDomCte.getSelectedRow();
                    int cod = Integer.parseInt(tableDomCte.getValueAt(row, 0).toString());
                    int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste domicilio?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                    if(req == JOptionPane.YES_OPTION){
                        try{
                            storedProcedures.updateDom(cod, calleCte.getText(), coloniaCte.getText(), numIntCte.getText(), Integer.parseInt(numExtCte.getText()), Integer.parseInt(cpCte.getText()), descDomCte.getText(), (String)estadoCte.getSelectedItem(), ciudadCte.getText());
                            JOptionPane.showMessageDialog(null, "Domicilio actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                            this.showAddress(getCodigoCte());

                            calleCte.setText("");calleCte.setEnabled(false);
                            coloniaCte.setText("");coloniaCte.setEnabled(false);
                            numIntCte.setText("");numIntCte.setEnabled(false);
                            numExtCte.setText("");numExtCte.setEnabled(false);
                            cpCte.setText("");cpCte.setEnabled(false);
                            descDomCte.setText("");descDomCte.setEnabled(false);
                            estadoCte.setSelectedIndex(0);estadoCte.setEnabled(false);
                            ciudadCte.setText("");ciudadCte.setEnabled(false);

                            this.btnUpdateCte.setEnabled(false);
                            this.btnDeleteCte.setEnabled(false);
                            this.btnSaveCte.setEnabled(false);
                            this.btnAñadirCampos.setEnabled(false);
                        }
                        catch(SQLException e){}
                    }
                }
            }
        }
    }//GEN-LAST:event_btnUpdateCteActionPerformed

    private void showTelCteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showTelCteMouseClicked
        this.btnSaveCte.setEnabled(false);
        this.btnAñadirCampos.setEnabled(false);
        this.btnDeleteCte.setEnabled(true);
        this.btnUpdateCte.setEnabled(true);
        
        tableShowPersonalData.clearSelection();
        showEmailCte.clearSelection();
        tableDomCte.clearSelection();
        
        nameCte.setEnabled(false);
        appCte.setEnabled(false);
        apmCte.setEnabled(false);
        sexoMascCte.setEnabled(false);
        sexoFemCte.setEnabled(false);
        calleCte.setText("");
        calleCte.setEnabled(false);
        coloniaCte.setText("");
        coloniaCte.setEnabled(false);
        numExtCte.setText("");
        numExtCte.setEnabled(false);
        numIntCte.setText("");
        numIntCte.setEnabled(false);
        cpCte.setText("");
        cpCte.setEnabled(false);
        descDomCte.setText("");
        descDomCte.setEnabled(false);
        estadoCte.setSelectedIndex(0);
        estadoCte.setEnabled(false);
        ciudadCte.setEnabled(false);
        ciudadCte.setText("");
        emailCte.setText("");
        emailCte.setEnabled(false);
        descEmailCte.setText("");
        descEmailCte.setEnabled(false);
        
        try{
            int row = showTelCte.getSelectedRow();
            int codTel = Integer.parseInt(showTelCte.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from telefono where cod_telefono='"+codTel+"'");
            while(res.next()){
                telCte.setText(res.getString("numero_telefono"));
                telCte.setEnabled(true);
                descTelCte.setText(res.getString("descripcion_telefono"));
                descTelCte.setEnabled(true);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showTelCteMouseClicked

    private void showEmailCteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showEmailCteMouseClicked
        this.btnSaveCte.setEnabled(false);
        this.btnAñadirCampos.setEnabled(false);
        this.btnDeleteCte.setEnabled(true);
        this.btnUpdateCte.setEnabled(true);
        
        tableShowPersonalData.clearSelection();
        showTelCte.clearSelection();
        tableDomCte.clearSelection();
        
        nameCte.setEnabled(false);
        appCte.setEnabled(false);
        apmCte.setEnabled(false);
        sexoMascCte.setEnabled(false);
        sexoFemCte.setEnabled(false);
        calleCte.setText("");
        calleCte.setEnabled(false);
        coloniaCte.setText("");
        coloniaCte.setEnabled(false);
        numExtCte.setText("");
        numExtCte.setEnabled(false);
        numIntCte.setText("");
        numIntCte.setEnabled(false);
        cpCte.setText("");
        cpCte.setEnabled(false);
        descDomCte.setText("");
        descDomCte.setEnabled(false);
        estadoCte.setSelectedIndex(0);
        estadoCte.setEnabled(false);
        ciudadCte.setEnabled(false);
        ciudadCte.setText("");
        telCte.setText("");
        telCte.setEnabled(false);
        descTelCte.setText("");
        descTelCte.setEnabled(false);
        
        
        
        try{
            int row = showEmailCte.getSelectedRow();
            int codCorreo = Integer.parseInt(showEmailCte.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from correo where cod_correo="+codCorreo);
            while(res.next()){
                emailCte.setText(res.getString("nombre_correo"));
                emailCte.setEnabled(true);
                descEmailCte.setText(res.getString("descripcion_correo"));
                descEmailCte.setEnabled(true);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showEmailCteMouseClicked

    private void btnAñadirCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirCamposActionPerformed
        if(getFlagAdd() == 1){//si vale 1 añade un nuevo telefono
            if( telCte.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Aún no has llenado éste campo!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
                telCte.requestFocus();
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo teléfono?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addTelefono(telCte.getText(), descTelCte.getText());
                        int codTel = storedProcedures.getID("cod_telefono", "telefono" );
                        JOptionPane.showMessageDialog(null, "Teléfono agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        storedProcedures.addTel_Cte(codTel, getCodigoCte());
                        showTel(getCodigoCte());
                        telCte.setText("");telCte.setEnabled(false);
                        descTelCte.setText("");descTelCte.setEnabled(false);
                        this.btnAñadirCampos.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    telCte.setText("");telCte.setEnabled(false);
                    descTelCte.setText("");descTelCte.setEnabled(false);
                    this.btnAñadirCampos.setEnabled(false);
                }
            }
        }
        
        else if( getFlagAdd() == 2 ){//si vale 2 añade un nuevo correo
            if( emailCte.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Aún no has llenado éste campo!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
                emailCte.requestFocus();
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo correo?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addEmail(emailCte.getText(), descEmailCte.getText());
                        int codEmail = storedProcedures.getID("cod_correo", "correo" );
                        JOptionPane.showMessageDialog(null, "Correo agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        storedProcedures.addEmail_Cte(codEmail, getCodigoCte());
                        showEmail(getCodigoCte());
                        emailCte.setText("");emailCte.setEnabled(false);
                        descEmailCte.setText("");descEmailCte.setEnabled(false);
                        this.btnAñadirCampos.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    emailCte.setText("");emailCte.setEnabled(false);
                    descEmailCte.setText("");descEmailCte.setEnabled(false);
                    this.btnAñadirCampos.setEnabled(false);
                }
            }
        }
        
        else if( getFlagAdd() == 3 ){//si vale 2 añade un nuevo domicilio
            if( calleCte.getText().isEmpty() || coloniaCte.getText().isEmpty() || numExtCte.getText().isEmpty() || cpCte.getText().isEmpty() || estadoCte.getSelectedIndex() == 0 || ciudadCte.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo domicilio?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addDomicilio(calleCte.getText(), numIntCte.getText(), Integer.parseInt(numExtCte.getText()), descDomCte.getText());
                        storedProcedures.addColonia(coloniaCte.getText(), Integer.parseInt(cpCte.getText()));
                        storedProcedures.addEstado((String)estadoCte.getSelectedItem());
                        storedProcedures.addCiudad(ciudadCte.getText());
                        int cod = storedProcedures.getID("cod_domicilio", "domicilio" );
                        storedProcedures.addDomicilio_Cte(cod, getCodigoCte());
                        storedProcedures.addColonia_Domicilio(cod, cod);
                        storedProcedures.addEstado_Domicilio(cod, cod);
                        storedProcedures.addCiudad_Domicilio(cod, cod);
                        JOptionPane.showMessageDialog(null, "Domicilio agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        showAddress(getCodigoCte());
                        
                        calleCte.setText("");calleCte.setEnabled(false);
                        coloniaCte.setText("");coloniaCte.setEnabled(false);
                        numIntCte.setText("");numIntCte.setEnabled(false);
                        numExtCte.setText("");numExtCte.setEnabled(false);
                        cpCte.setText("");cpCte.setEnabled(false);
                        descDomCte.setText("");descDomCte.setEnabled(false);
                        estadoCte.setSelectedIndex(0);estadoCte.setEnabled(false);
                        ciudadCte.setText("");ciudadCte.setEnabled(false);
                        this.btnAñadirCampos.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    calleCte.setText("");calleCte.setEnabled(false);
                    coloniaCte.setText("");coloniaCte.setEnabled(false);
                    numIntCte.setText("");numIntCte.setEnabled(false);
                    numExtCte.setText("");numExtCte.setEnabled(false);
                    cpCte.setText("");cpCte.setEnabled(false);
                    descDomCte.setText("");descDomCte.setEnabled(false);
                    estadoCte.setSelectedIndex(0);estadoCte.setEnabled(false);
                    ciudadCte.setText("");ciudadCte.setEnabled(false);
                    this.btnAñadirCampos.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_btnAñadirCamposActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        cleanTel();
        cleanEmail();
        cleanAddress();
        tableShowPersonalData.clearSelection();
        
        this.btnAñadirCampos.setEnabled(false);
        this.btnDeleteCte.setEnabled(false);
        this.btnSaveCte.setEnabled(false);
        this.btnUpdateCte.setEnabled(false);
        this.btnAddAddressCte.setEnabled(false);
        this.btnAddEmailCte.setEnabled(false);
        this.btnAddTelCte.setEnabled(false);
        
        nameCte.setEnabled(false);
        nameCte.setText("");
        nameCte.requestFocus();
        this.appCte.setEnabled(false);
        appCte.setText("");
        this.apmCte.setEnabled(false);
        apmCte.setText("");
        this.sexoMascCte.setEnabled(false);
        this.sexoMascCte.setSelected(false);
        this.sexoFemCte.setEnabled(false);
        this.sexoFemCte.setSelected(false);
        /*Domicilio */ 
        this.calleCte.setEnabled(false);
        calleCte.setText("");
        this.coloniaCte.setEnabled(false);
        coloniaCte.setText("");
        this.numExtCte.setEnabled(false);
        numExtCte.setText("");
        this.numIntCte.setEnabled(false);
        numIntCte.setText("");
        this.cpCte.setEnabled(false);
        cpCte.setText("");
        this.descDomCte.setEnabled(false);
        descDomCte.setText("");
        this.estadoCte.setEnabled(false);
        this.estadoCte.setSelectedIndex(0);
        this.ciudadCte.setEnabled(false);
        this.ciudadCte.setText("");
        /*  Telefono */ 
        this.telCte.setEnabled(false);
        telCte.setText("");
        this.descTelCte.setEnabled(false);
        descTelCte.setText("");
        /*Email */      
        this.emailCte.setEnabled(false);
        emailCte.setText("");
        this.descEmailCte.setEnabled(false);
        descEmailCte.setText("");
    }//GEN-LAST:event_resetActionPerformed

    private void tableDomCteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDomCteMouseClicked
        this.btnSaveCte.setEnabled(false);
        this.btnAñadirCampos.setEnabled(false);
        this.btnDeleteCte.setEnabled(true);
        this.btnUpdateCte.setEnabled(true);
        
        tableShowPersonalData.clearSelection();
        showTelCte.clearSelection();
        showEmailCte.clearSelection();
        
        nameCte.setEnabled(false);
        appCte.setEnabled(false);
        apmCte.setEnabled(false);
        sexoMascCte.setEnabled(false);
        sexoFemCte.setEnabled(false);
        telCte.setText("");
        telCte.setEnabled(false);
        descTelCte.setText("");
        descTelCte.setEnabled(false);
        emailCte.setText("");
        emailCte.setEnabled(false);
        descEmailCte.setText("");
        descEmailCte.setEnabled(false);
        
        try{
            int row = tableDomCte.getSelectedRow();
            int codD = Integer.parseInt(tableDomCte.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from domicilio, colonia, estado, ciudad where cod_domicilio="+codD+" and cod_colonia="+codD+" and cod_estado="+codD+" and cod_ciudad="+codD);
            while(res.next()){
                calleCte.setText(res.getString("calle"));
                calleCte.setEnabled(true);
                coloniaCte.setText(res.getString("nombre_colonia"));
                coloniaCte.setEnabled(true);
                numExtCte.setText(res.getString("num_ext"));
                numExtCte.setEnabled(true);
                numIntCte.setText(res.getString("num_int"));
                numIntCte.setEnabled(true);
                cpCte.setText(res.getString("cp"));
                cpCte.setEnabled(true);
                descDomCte.setText(res.getString("descripcion_domicilio"));
                descDomCte.setEnabled(true);
                estadoCte.setEnabled(true);
                ciudadCte.setText(res.getString("nombre_ciudad"));
                ciudadCte.setEnabled(true);
                
                String estado = res.getString("nombre_estado");
                switch(estado){
                    case "Aguascalientes":
                        estadoCte.setSelectedIndex(1);
                        break;
                    case "Baja California":
                        estadoCte.setSelectedIndex(2);
                        break;
                    case " Baja California Sur":
                        estadoCte.setSelectedIndex(3);
                        break;
                    case "Campeche":
                        estadoCte.setSelectedIndex(4);
                        break;
                    case "Chiapas":
                        estadoCte.setSelectedIndex(5);
                        break;
                    case "Chihuahua":
                        estadoCte.setSelectedIndex(6);
                        break;
                    case "Coahuila":
                        estadoCte.setSelectedIndex(7);
                        break;
                    case "Colima":
                        estadoCte.setSelectedIndex(8);
                        break;
                    case "Durango":
                        estadoCte.setSelectedIndex(9);
                        break;
                    case "Edo. México":
                        estadoCte.setSelectedIndex(10);
                        break;
                    case "Guanajuato":
                        estadoCte.setSelectedIndex(11);
                        break;
                    case "Guerrero":
                        estadoCte.setSelectedIndex(12);
                        break;
                    case "Hidalgo":
                        estadoCte.setSelectedIndex(13);
                        break;
                    case "Jalisco":
                        estadoCte.setSelectedIndex(14);
                        break;
                    case "Michoacán":
                        estadoCte.setSelectedIndex(15);
                        break;
                    case "Morelos":
                        estadoCte.setSelectedIndex(16);
                        break;
                    case "Nayarit":
                        estadoCte.setSelectedIndex(17);
                        break;
                    case "Nuevo León":
                        estadoCte.setSelectedIndex(18);
                        break;
                    case "Oaxaca":
                        estadoCte.setSelectedIndex(19);
                        break;
                    case "Puebla":
                        estadoCte.setSelectedIndex(20);
                        break;
                    case "Querétaro":
                        estadoCte.setSelectedIndex(21);
                        break;
                    case "Quintana Roo":
                        estadoCte.setSelectedIndex(22);
                        break;
                    case "San Luis Potosí":
                        estadoCte.setSelectedIndex(23);
                        break;
                    case "Sinaloa":
                        estadoCte.setSelectedIndex(24);
                        break;
                    case "Sonora":
                        estadoCte.setSelectedIndex(25);
                        break;
                    case "Tabasco":
                        estadoCte.setSelectedIndex(26);
                        break;
                    case "Tamaulipas":
                        estadoCte.setSelectedIndex(27);
                        break;
                    case "Tlaxcala":
                        estadoCte.setSelectedIndex(28);
                        break;
                    case "Veracruz":
                        estadoCte.setSelectedIndex(29);
                        break;
                    case "Yucatán":
                        estadoCte.setSelectedIndex(30);
                        break;
                    case "Zacatecas":
                        estadoCte.setSelectedIndex(31);
                        break;
                }
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableDomCteMouseClicked

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
            java.util.logging.Logger.getLogger(adminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane a;
    private javax.swing.JTextField apmCte;
    private javax.swing.JTextField appCte;
    private javax.swing.JButton btnAddAddressCte;
    private javax.swing.JButton btnAddCte;
    private javax.swing.JButton btnAddEmailCte;
    private javax.swing.JButton btnAddTelCte;
    private javax.swing.JButton btnAñadirCampos;
    private javax.swing.JButton btnDeleteCte;
    private javax.swing.JButton btnSaveCte;
    private javax.swing.JButton btnUpdateCte;
    private javax.swing.JTextField calleCte;
    private javax.swing.JTextField ciudadCte;
    private javax.swing.JTextField coloniaCte;
    private javax.swing.JTextField cpCte;
    private javax.swing.JTextField descDomCte;
    private javax.swing.JTextField descEmailCte;
    private javax.swing.JTextField descTelCte;
    private javax.swing.JTextField emailCte;
    private javax.swing.JComboBox<String> estadoCte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField nameCte;
    private javax.swing.JTextField numExtCte;
    private javax.swing.JTextField numIntCte;
    private javax.swing.JButton reset;
    private javax.swing.JRadioButton sexoFemCte;
    private javax.swing.JRadioButton sexoMascCte;
    private javax.swing.JTable showEmailCte;
    private javax.swing.JTable showTelCte;
    private javax.swing.JTable tableDomCte;
    private javax.swing.JTable tableShowPersonalData;
    private javax.swing.JTextField telCte;
    // End of variables declaration//GEN-END:variables
}
