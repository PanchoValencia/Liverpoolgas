/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author JoséFrancisco
 */
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

public class adminEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form adminEmpleado
     */
    public adminEmpleado() {
        initComponents();
        departamento.setText(temporalVariables.getDepartamento());
    }
    
    public  static String sexo      = "";
    private static int    flag      = 0;
    private static int    codigoEmp = 0;
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
    
    public static int getCodigoEmp() {
        return codigoEmp;
    }
    
    public static void setCodigoEmp(int aCodigoEmp) {
        codigoEmp = aCodigoEmp;
    }
    
    public static int getFlagAdd() {
        return flagAdd;
    }
    public static void setFlagAdd(int aFladAdd) {
        flagAdd = aFladAdd;
    }
    
    static ResultSet res;

    
    
    int idEmp     = 0,
        idDom     = 0,
        idColonia = 0,
        idEdo     = 0,
        idCiudad  = 0,
        idTel     = 0,
        idDepa    = 0,
        idCorreo  = 0,
        idDia     = 0,
        idHorario = 0;
    
    public void showEmpleado(){
        DefaultTableModel emp = (DefaultTableModel) tableShowPersonalData.getModel();
        emp.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query("select * from empleado where activo="+1+" order by nombres_empleado");
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                emp.addRow(v);
                tableShowPersonalData.setModel(emp);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showTel(int codEmp){
        DefaultTableModel tel = (DefaultTableModel) tableShowTel.getModel();
        tel.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from telefono"
                        + " inner join telefono_empleado on telefono.cod_telefono=telefono_empleado.cod_telefono"
                        + " and telefono_empleado.cod_empleado="+codEmp+" order by numero_telefono"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("cod_telefono"));
                v.add(res.getString("numero_telefono"));
                v.add(res.getString("descripcion_telefono"));
                tel.addRow(v);
                tableShowTel.setModel(tel);
            }
        }
        catch(SQLException e){
        }
    }
    public void cleanTel(){
        DefaultTableModel tel = (DefaultTableModel) tableShowTel.getModel();
        tel.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select numero_telefono , descripcion_telefono from telefono"
                        + " inner join telefono_empleado on telefono.cod_telefono=telefono_empleado.cod_telefono"
                        + " and telefono_empleado.cod_empleado="+0
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("numero_telefono"));
                v.add(res.getString("descripcion_telefono"));
                tel.addRow(v);
                tableShowTel.setModel(tel);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showHorario(int codEmp){
        DefaultTableModel horario = (DefaultTableModel) tableHorario.getModel();
        horario.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from horario"
               +" inner join horario_empleado on horario.cod_horario=horario_empleado.cod_horario"
               +" and horario_empleado.cod_empleado="+codEmp
               +" inner join dia_horario on horario.cod_horario=dia_horario.cod_horario"
               +" inner join dia on dia_horario.cod_dia=dia.cod_dia order by nombre_dia"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("cod_dia"));
                v.add(res.getString("nombre_dia"));
                v.add(res.getString("hora_entrada"));
                v.add(res.getString("hora_salida"));
                horario.addRow(v);
                tableHorario.setModel(horario);
            }
        }
        catch(SQLException e){
        }
    }
    public void cleanHorario(){
        DefaultTableModel horario = (DefaultTableModel) tableHorario.getModel();
        horario.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from horario"
               +" inner join horario_empleado on horario.cod_horario=horario_empleado.cod_horario"
               +" and horario_empleado.cod_empleado="+0
               +" inner join dia_horario on horario.cod_horario=dia_horario.cod_horario"
               +" inner join dia on dia_horario.cod_dia=dia.cod_dia"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("cod_dia"));
                v.add(res.getString("nombre_dia"));
                v.add(res.getString("hora_entrada"));
                v.add(res.getString("hora_salida"));
                horario.addRow(v);
                tableHorario.setModel(horario);
            }
        }
        catch(SQLException e){
        }
    }
    
    public void showEmail(int codEmp){
        DefaultTableModel email = (DefaultTableModel) tableShowEmail.getModel();
        email.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select * from correo"
                        + " inner join correo_empleado on correo.cod_correo=correo_empleado.cod_correo"
                        + " and correo_empleado.cod_empleado="+codEmp+" order by nombre_correo"
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("cod_correo"));
                v.add(res.getString("nombre_correo"));
                v.add(res.getString("descripcion_correo"));
                email.addRow(v);
                tableShowEmail.setModel(email);
            }
        }
        catch(SQLException e){
        }
    }
    public void cleanEmail(){
        DefaultTableModel email = (DefaultTableModel) tableShowEmail.getModel();
        email.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
                "select nombre_correo , descripcion_correo from correo"
                        + " inner join correo_empleado on correo.cod_correo=correo_empleado.cod_correo"
                        + " and correo_empleado.cod_empleado="+0
        );
        
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString("nombre_correo"));
                v.add(res.getString("descripcion_correo"));
                email.addRow(v);
                tableShowEmail.setModel(email);
            }
        }
        catch(SQLException e){
        }
    }
    
    
    public void showAddress(int codEmp){
        DefaultTableModel address = (DefaultTableModel) this.tableDomicilios.getModel();
        address.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT * FROM domicilio"
          + " INNER JOIN dom_empleado ON domicilio.cod_domicilio=dom_empleado.cod_domicilio AND"
          + " dom_empleado.cod_empleado="+codEmp
          + " INNER JOIN colonia_domicilio ON dom_empleado.cod_domicilio=colonia_domicilio.cod_domicilio"
          + " INNER JOIN colonia ON colonia_domicilio.cod_colonia=colonia.cod_colonia"
          + " INNER JOIN estado_domicilio ON dom_empleado.cod_domicilio=estado_domicilio.cod_domicilio"
          + " INNER JOIN estado ON estado_domicilio.cod_estado=estado.cod_estado"
          + " INNER JOIN ciudad_domicilio ON dom_empleado.cod_domicilio=ciudad_domicilio.cod_domicilio"
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
                this.tableDomicilios.setModel(address);
            }
        }
        catch(SQLException e){
        }
    }
    public void cleanAddress(){
        DefaultTableModel address = (DefaultTableModel) this.tableDomicilios.getModel();
        address.setRowCount(0);
        res = conexionsqlserver.ConnectionDB.Query(
            "SELECT calle , nombre_colonia , num_ext , num_int , cp , descripcion_domicilio , nombre_estado , nombre_ciudad"
          + " FROM domicilio"
          + " INNER JOIN dom_empleado ON domicilio.cod_domicilio=dom_empleado.cod_domicilio AND"
          + " dom_empleado.cod_empleado="+0
          + " INNER JOIN colonia_domicilio ON dom_empleado.cod_domicilio=colonia_domicilio.cod_domicilio"
          + " INNER JOIN colonia ON colonia_domicilio.cod_colonia=colonia.cod_colonia"
          + " INNER JOIN estado_domicilio ON dom_empleado.cod_domicilio=estado_domicilio.cod_domicilio"
          + " INNER JOIN estado ON estado_domicilio.cod_estado=estado.cod_estado"
          + " INNER JOIN ciudad_domicilio ON dom_empleado.cod_domicilio=ciudad_domicilio.cod_domicilio"
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
                this.tableDomicilios.setModel(address);
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
        numExtEmp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        coloniaEmp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        descDomEmp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        calleEmp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        numIntEmp = new javax.swing.JTextField();
        cpEmp = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        estadoEmp = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        ciudadEmp = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        femenino = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        appEmp = new javax.swing.JTextField();
        apmEmp = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        nombresEmp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        masculino = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        departamento = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        telEmp = new javax.swing.JTextField();
        descTelEmp = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel10 = new javax.swing.JLabel();
        diaEmp = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        hSalidaEmp = new javax.swing.JComboBox<>();
        hEntradaEmp = new javax.swing.JComboBox<>();
        btnNew = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLabel18 = new javax.swing.JLabel();
        descEmailEmp = new javax.swing.JTextField();
        emailEmp = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableShowPersonalData = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableShowTel = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        a = new javax.swing.JScrollPane();
        tableDomicilios = new javax.swing.JTable();
        btnAddTel = new javax.swing.JButton();
        btnAddEmail = new javax.swing.JButton();
        btnAddAddress = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableShowEmail = new javax.swing.JTable();
        btnAddHorario = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableHorario = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));
        jLayeredPane5.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio"));
        jLayeredPane2.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLayeredPane2.setPreferredSize(new java.awt.Dimension(342, 263));

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Descripción");

        numExtEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        numExtEmp.setEnabled(false);
        numExtEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        numExtEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numExtEmpActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Num. Int");

        coloniaEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        coloniaEmp.setEnabled(false);
        coloniaEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        coloniaEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coloniaEmpActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Colonia");

        descDomEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descDomEmp.setEnabled(false);
        descDomEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        descDomEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descDomEmpActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("CP");

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Calle");

        calleEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        calleEmp.setEnabled(false);
        calleEmp.setMinimumSize(new java.awt.Dimension(200, 20));
        calleEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        calleEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calleEmpActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Num. Ext");

        numIntEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        numIntEmp.setEnabled(false);
        numIntEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        numIntEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numIntEmpActionPerformed(evt);
            }
        });

        cpEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        cpEmp.setEnabled(false);
        cpEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        cpEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpEmpActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Estado");

        estadoEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        estadoEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un estado..", "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Coahuila ", "Colima", "Durango", "Edo. México", "Guanajuato", "Guerrero ", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));
        estadoEmp.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Ciudad");

        ciudadEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        ciudadEmp.setEnabled(false);
        ciudadEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        ciudadEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadEmpActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(numExtEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(coloniaEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(descDomEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(calleEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(numIntEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cpEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(estadoEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(ciudadEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cpEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(descDomEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(estadoEmp, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ciudadEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(calleEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(coloniaEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numExtEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numIntEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calleEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coloniaEmp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numExtEmp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numIntEmp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descDomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estadoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudadEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personales"));
        jLayeredPane1.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        femenino.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        femenino.setForeground(new java.awt.Color(51, 51, 51));
        femenino.setText("Femenino");
        femenino.setEnabled(false);
        femenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femeninoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Apellido Materno");

        appEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        appEmp.setEnabled(false);
        appEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        appEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appEmpActionPerformed(evt);
            }
        });

        apmEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        apmEmp.setEnabled(false);
        apmEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        apmEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apmEmpActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Sexo:");

        nombresEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        nombresEmp.setEnabled(false);
        nombresEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        nombresEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombresEmpActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Nombre(s)");

        masculino.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        masculino.setForeground(new java.awt.Color(51, 51, 51));
        masculino.setText("Masculino");
        masculino.setEnabled(false);
        masculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masculinoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Apellido Paterno");

        jLabel21.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Departamento");

        departamento.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        departamento.setEnabled(false);
        departamento.setMinimumSize(new java.awt.Dimension(200, 20));
        departamento.setPreferredSize(new java.awt.Dimension(200, 20));
        departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departamentoActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(femenino, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(appEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(apmEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(nombresEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(masculino, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(departamento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(departamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombresEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(apmEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(masculino)
                            .addComponent(femenino))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(59, 59, 59))))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(masculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(femenino))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(nombresEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(appEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apmEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSave.setBackground(new java.awt.Color(0, 153, 0));
        btnSave.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(250, 250, 250));
        btnSave.setText("Guardar");
        btnSave.setActionCommand("Agregar");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setEnabled(false);
        btnSave.setPreferredSize(new java.awt.Dimension(150, 35));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfono"));
        jLayeredPane4.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLayeredPane4.setPreferredSize(new java.awt.Dimension(342, 91));

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Número");

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Descripción");

        telEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        telEmp.setEnabled(false);
        telEmp.setPreferredSize(new java.awt.Dimension(200, 20));

        descTelEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descTelEmp.setEnabled(false);
        descTelEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        descTelEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descTelEmpActionPerformed(evt);
            }
        });

        jLayeredPane4.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(telEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(descTelEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addComponent(descTelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(telEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13)
                    .addComponent(descTelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnUpdate.setBackground(new java.awt.Color(0, 51, 102));
        btnUpdate.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(250, 250, 250));
        btnUpdate.setText("Actualizar");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(251, 51, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(250, 250, 250));
        btnDelete.setText("Eliminar");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Horario"));
        jLayeredPane3.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLayeredPane3.setPreferredSize(new java.awt.Dimension(342, 91));

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Día");

        diaEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        diaEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un día..", "Todos los días", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" }));
        diaEmp.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Salida");

        jLabel20.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Entrada");

        hSalidaEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        hSalidaEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un horario..", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        hSalidaEmp.setEnabled(false);
        hSalidaEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hSalidaEmpActionPerformed(evt);
            }
        });

        hEntradaEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        hEntradaEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un horario..", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00" }));
        hEntradaEmp.setEnabled(false);

        jLayeredPane3.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(diaEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(hSalidaEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(hEntradaEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel20))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(diaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hSalidaEmp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hEntradaEmp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(diaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(hEntradaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(hSalidaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNew.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1485408397_Add-Male-User.png"))); // NOI18N
        btnNew.setText("Nuevo");
        btnNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnAñadir.setBackground(new java.awt.Color(51, 102, 255));
        btnAñadir.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnAñadir.setForeground(new java.awt.Color(250, 250, 250));
        btnAñadir.setText("Añadir");
        btnAñadir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAñadir.setEnabled(false);
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
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

        jLayeredPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("E-mail"));
        jLayeredPane6.setForeground(new java.awt.Color(51, 51, 51));
        jLayeredPane6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLayeredPane6.setPreferredSize(new java.awt.Dimension(342, 91));

        jLabel18.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Descripción");

        descEmailEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        descEmailEmp.setEnabled(false);
        descEmailEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        descEmailEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descEmailEmpActionPerformed(evt);
            }
        });

        emailEmp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        emailEmp.setEnabled(false);
        emailEmp.setPreferredSize(new java.awt.Dimension(200, 20));
        emailEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailEmpActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Correo");

        jLayeredPane6.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(descEmailEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(emailEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(emailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(6, 6, 6)
                .addComponent(descEmailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(descEmailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(emailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane5.setLayer(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnSave, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnUpdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnDelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnNew, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnAñadir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(reset, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew))
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLayeredPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                            .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                            .addComponent(jLayeredPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

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
        }

        tableShowTel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableShowTel.setForeground(new java.awt.Color(102, 102, 102));
        tableShowTel.setModel(new javax.swing.table.DefaultTableModel(
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
        tableShowTel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableShowTel.setGridColor(new java.awt.Color(0, 153, 204));
        tableShowTel.setRowHeight(35);
        tableShowTel.setRowMargin(5);
        tableShowTel.getTableHeader().setReorderingAllowed(false);
        tableShowTel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableShowTelMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableShowTel);
        if (tableShowTel.getColumnModel().getColumnCount() > 0) {
            tableShowTel.getColumnModel().getColumn(0).setMinWidth(0);
            tableShowTel.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableShowTel.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLabel17.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Selecciona un Empleado para (ver datos, actualizar u eliminar)");

        a.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N

        tableDomicilios.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableDomicilios.setForeground(new java.awt.Color(102, 102, 102));
        tableDomicilios.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDomicilios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableDomicilios.setGridColor(new java.awt.Color(0, 153, 204));
        tableDomicilios.setMaximumSize(new java.awt.Dimension(600, 0));
        tableDomicilios.setMinimumSize(new java.awt.Dimension(600, 0));
        tableDomicilios.setRowHeight(35);
        tableDomicilios.setRowMargin(5);
        tableDomicilios.setShowVerticalLines(false);
        tableDomicilios.getTableHeader().setReorderingAllowed(false);
        tableDomicilios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDomiciliosMouseClicked(evt);
            }
        });
        a.setViewportView(tableDomicilios);
        if (tableDomicilios.getColumnModel().getColumnCount() > 0) {
            tableDomicilios.getColumnModel().getColumn(0).setMinWidth(0);
            tableDomicilios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableDomicilios.getColumnModel().getColumn(0).setMaxWidth(0);
            tableDomicilios.getColumnModel().getColumn(3).setMinWidth(50);
            tableDomicilios.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableDomicilios.getColumnModel().getColumn(3).setMaxWidth(50);
            tableDomicilios.getColumnModel().getColumn(4).setMinWidth(50);
            tableDomicilios.getColumnModel().getColumn(4).setPreferredWidth(50);
            tableDomicilios.getColumnModel().getColumn(4).setMaxWidth(50);
            tableDomicilios.getColumnModel().getColumn(5).setMinWidth(70);
            tableDomicilios.getColumnModel().getColumn(5).setPreferredWidth(70);
            tableDomicilios.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        btnAddTel.setBackground(new java.awt.Color(51, 102, 255));
        btnAddTel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddTel.setForeground(new java.awt.Color(255, 250, 250));
        btnAddTel.setText("Añadir");
        btnAddTel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddTel.setEnabled(false);
        btnAddTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTelActionPerformed(evt);
            }
        });

        btnAddEmail.setBackground(new java.awt.Color(51, 102, 255));
        btnAddEmail.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddEmail.setForeground(new java.awt.Color(255, 250, 250));
        btnAddEmail.setText("Añadir");
        btnAddEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddEmail.setEnabled(false);
        btnAddEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmailActionPerformed(evt);
            }
        });

        btnAddAddress.setBackground(new java.awt.Color(51, 102, 255));
        btnAddAddress.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddAddress.setForeground(new java.awt.Color(255, 250, 250));
        btnAddAddress.setText("Añadir");
        btnAddAddress.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddAddress.setEnabled(false);
        btnAddAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAddressActionPerformed(evt);
            }
        });

        tableShowEmail.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableShowEmail.setForeground(new java.awt.Color(102, 102, 102));
        tableShowEmail.setModel(new javax.swing.table.DefaultTableModel(
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
        tableShowEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableShowEmail.setGridColor(new java.awt.Color(0, 153, 204));
        tableShowEmail.setRowHeight(35);
        tableShowEmail.setRowMargin(5);
        tableShowEmail.setShowVerticalLines(false);
        tableShowEmail.getTableHeader().setReorderingAllowed(false);
        tableShowEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableShowEmailMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableShowEmail);
        if (tableShowEmail.getColumnModel().getColumnCount() > 0) {
            tableShowEmail.getColumnModel().getColumn(0).setMinWidth(0);
            tableShowEmail.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableShowEmail.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnAddHorario.setBackground(new java.awt.Color(51, 102, 255));
        btnAddHorario.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        btnAddHorario.setForeground(new java.awt.Color(255, 250, 250));
        btnAddHorario.setText("Añadir");
        btnAddHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddHorario.setEnabled(false);
        btnAddHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHorarioActionPerformed(evt);
            }
        });

        tableHorario.setFont(new java.awt.Font("Segoe UI Emoji", 0, 16)); // NOI18N
        tableHorario.setForeground(new java.awt.Color(102, 102, 102));
        tableHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "Día", "Entrada", "Salida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableHorario.setGridColor(new java.awt.Color(0, 153, 204));
        tableHorario.setRowHeight(35);
        tableHorario.setRowMargin(5);
        tableHorario.setShowVerticalLines(false);
        tableHorario.getTableHeader().setReorderingAllowed(false);
        tableHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHorarioMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tableHorario);
        if (tableHorario.getColumnModel().getColumnCount() > 0) {
            tableHorario.getColumnModel().getColumn(0).setMinWidth(0);
            tableHorario.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableHorario.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(btnAddAddress)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAddTel)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addComponent(btnAddEmail)
                                .addComponent(btnAddHorario)))
                        .addComponent(a, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnAddTel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnAddEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(btnAddHorario))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnAddAddress)
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numExtEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numExtEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numExtEmpActionPerformed

    private void coloniaEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coloniaEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coloniaEmpActionPerformed

    private void descDomEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descDomEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descDomEmpActionPerformed

    private void calleEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calleEmpActionPerformed

    private void numIntEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numIntEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numIntEmpActionPerformed

    private void cpEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpEmpActionPerformed

    private void ciudadEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ciudadEmpActionPerformed

    private void femeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femeninoActionPerformed
        this.masculino.setSelected(false);
        setFlag(1);
        setSexo("Femenino");
    }//GEN-LAST:event_femeninoActionPerformed

    private void appEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appEmpActionPerformed

    private void apmEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apmEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apmEmpActionPerformed

    private void nombresEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombresEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombresEmpActionPerformed

    private void masculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masculinoActionPerformed
        this.femenino.setSelected(false);
        setFlag(1);
        setSexo("Masculino");
    }//GEN-LAST:event_masculinoActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        //String depa      = this.departamento.getText();
        String nombres   = this.nombresEmp.getText();
        String app       = this.appEmp.getText();
        String apm       = this.apmEmp.getText();
        String sex       = getSexo();
        int sexoValid    = getFlag();
        String calle     = this.calleEmp.getText();
        String colonia   = this.coloniaEmp.getText();
        String numExt    = this.numExtEmp.getText();
        String numInt    = this.numIntEmp.getText();
        String cp        = this.cpEmp.getText();
        String descrip   = this.descDomEmp.getText();
        String estado    = (String)estadoEmp.getSelectedItem();
        String ciudad    = this.ciudadEmp.getText();
        String tel       = this.telEmp.getText();
        String descTel   = this.descTelEmp.getText();
        String email     = this.emailEmp.getText();
        String descEmail = this.descEmailEmp.getText();
        String dia       = (String)diaEmp.getSelectedItem();
        String hEnt      = (String)hEntradaEmp.getSelectedItem();
        String hSal      = (String)hSalidaEmp.getSelectedItem();

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
                nombresEmp.requestFocus();
            }
            else if( Validations.lenght(app, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                appEmp.requestFocus();
            }
            else if( Validations.lenght(apm, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                apmEmp.requestFocus();
            }
            else if( Validations.lenght(calle, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                calleEmp.requestFocus();
            }
            else if( Validations.lenght(colonia, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                coloniaEmp.requestFocus();
            }
            else if( Validations.lenght(numExt, 5) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 5 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                numExtEmp.requestFocus();
            }
            else if( Validations.lenght(numInt, 4) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 4 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                numIntEmp.requestFocus();
            }
            else if( Validations.ValidarCp(cp) ){
                JOptionPane.showMessageDialog(null, "Ingresa 5 números en el código postal!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                cpEmp.requestFocus();
            }
            else if( Validations.lenght(descrip, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descDomEmp.requestFocus();
            }
            else if( Validations.lenght(tel, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 25 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                telEmp.requestFocus();
            }
            else if( Validations.lenght(descTel, 30) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 30 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descTelEmp.requestFocus();
            }
            else if( Validations.lenght(email, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo incluyendo @example.com!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                emailEmp.requestFocus();
            }
            else if( Validations.lenght(descEmail, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descEmailEmp.requestFocus();
            }
            else if( estado.equals("Selecciona un estado..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún estado.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                estadoEmp.requestFocus();
            }
            else if( Validations.lenght(ciudad, 20) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                ciudadEmp.requestFocus();
            }
            else if( dia.equals("Selecciona un día..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún día.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                diaEmp.requestFocus();
            }
            else if( hEnt.equals("Selecciona un horario..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún horario de entrada.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                hEntradaEmp.requestFocus();
            }
            else if( hSal.equals("Selecciona un horario..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún horario de salida.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                hSalidaEmp.requestFocus();
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
                    
                    res = conexionsqlserver.ConnectionDB.Query("select * from departamento where nombre_departamento='"+temporalVariables.getDepartamento()+"'");
                    while( res.next() ){
                        idDepa = res.getInt("cod_departamento");
                    }

                    storedProcedures.addEmpleado(nombres, app, apm, sex);
                    idEmp = storedProcedures.getID("cod_empleado", "empleado");
                    storedProcedures.addEmpleado_Depa(idEmp, idDepa);

                    storedProcedures.addDomicilio(calle, numInt, numE, descrip);
                    idDom = storedProcedures.getID("cod_domicilio", "domicilio");
                    storedProcedures.addDomicilio_Empleado(idDom, idEmp);

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
                        storedProcedures.addTel_Empleado(idTel, idEmp);
                    }

                    if( !email.isEmpty()){
                        storedProcedures.addEmail(email, descEmail);
                        idCorreo = storedProcedures.getID("cod_correo", "correo");
                        storedProcedures.addEmail_Empleado(idCorreo, idEmp);
                    }
                    
                    storedProcedures.addDia(dia);
                    idDia = storedProcedures.getID("cod_dia", "dia");
                    
                    storedProcedures.addHorario(hEnt, hSal);
                    idHorario = storedProcedures.getID("cod_horario", "horario");
                    
                    storedProcedures.addDia_horario(idDia, idHorario);
                    storedProcedures.addHorario_empleado(idHorario, idEmp);

                    JOptionPane.showMessageDialog(null, "Empleado agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    nombresEmp.setText("");
                    appEmp.setText("");
                    apmEmp.setText("");
                    masculino.setSelected(false);
                    femenino.setSelected(false);
                    calleEmp.setText("");
                    coloniaEmp.setText("");
                    numExtEmp.setText("");
                    numIntEmp.setText("");
                    cpEmp.setText("");
                    descDomEmp.setText("");
                    estadoEmp.setSelectedIndex(0);
                    ciudadEmp.setText("");
                    telEmp.setText("");
                    descTelEmp.setText("");
                    emailEmp.setText("");
                    descEmailEmp.setText("");
                    diaEmp.setSelectedIndex(0);
                    hEntradaEmp.setSelectedIndex(0);
                    hSalidaEmp.setSelectedIndex(0);
                    showEmpleado();
                }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(this, "Hubo un error al almacenar el empleado, intenta de nuevo", ":( FATAL!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void descTelEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descTelEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descTelEmpActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String nombres   = this.nombresEmp.getText();
        String app       = this.appEmp.getText();
        String apm       = this.apmEmp.getText();
        String sex       = getSexo();
        int sexoValid    = getFlag();
        String calle     = this.calleEmp.getText();
        String colonia   = this.coloniaEmp.getText();
        String numExt    = this.numExtEmp.getText();
        String numInt    = this.numIntEmp.getText();
        String cp        = this.cpEmp.getText();
        String descrip   = this.descDomEmp.getText();
        String estado    = (String)estadoEmp.getSelectedItem();
        String ciudad    = this.ciudadEmp.getText();
        String tel       = this.telEmp.getText();
        String descTel   = this.descTelEmp.getText();
        String email     = this.emailEmp.getText();
        String descEmail = this.descEmailEmp.getText();

        if(this.tableShowPersonalData.getSelectedRows().length > 0){//actualiza empleado
            if( nombres.isEmpty() || app.isEmpty() || apm.isEmpty() ){
                JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else{
                if( Validations.validarNombres(nombres) || Validations.validarNombres(app) || Validations.validarNombres(apm) ){
                    JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                }
                else if( Validations.lenght(nombres, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    nombresEmp.requestFocus();
                }
                else if( Validations.lenght(app, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    appEmp.requestFocus();
                }
                else if( Validations.lenght(apm, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    apmEmp.requestFocus();
                }
                else{
                    int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar la información del empleado?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                    if(req == JOptionPane.YES_OPTION){
                        try{
                            storedProcedures.updateEmpleado(nombresEmp.getText(), appEmp.getText(), apmEmp.getText(), getSexo(), getCodigoEmp());
                            JOptionPane.showMessageDialog(null, "Empleado actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                            showEmpleado();
                            nombresEmp.setText("");nombresEmp.setEnabled(false);
                            appEmp.setText("");appEmp.setEnabled(false);
                            apmEmp.setText("");apmEmp.setEnabled(false);
                            femenino.setSelected(false);femenino.setEnabled(false);
                            masculino.setSelected(false);masculino.setEnabled(false);
                            this.btnUpdate.setEnabled(false);
                            this.btnDelete.setEnabled(false);
                            this.btnSave.setEnabled(false);
                            this.btnAñadir.setEnabled(false);
                        }
                        catch(SQLException e){}
                    }
                }
            }
        }

        else if(this.tableShowTel.getSelectedRows().length > 0){//actualiza telefono
            if( Validations.validarTel(tel) ){
                JOptionPane.showMessageDialog(null, "Introduce datos válidos!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
            }
            else if( Validations.lenght(tel, 25) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 25 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                telEmp.requestFocus();
            }
            else if( Validations.lenght(descTel, 30) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 30 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descTelEmp.requestFocus();
            }
            else{
                int row = tableShowTel.getSelectedRow();
                int cod = Integer.parseInt(tableShowTel.getValueAt(row, 0).toString());
                int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste teléfono?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.updateTel(telEmp.getText(), descTelEmp.getText(), cod);
                        JOptionPane.showMessageDialog(null, "Telefono actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        this.showTel(getCodigoEmp());
                        this.telEmp.setText("");this.telEmp.setEnabled(false);
                        this.descTelEmp.setText("");this.descTelEmp.setEnabled(false);
                        this.btnUpdate.setEnabled(false);
                        this.btnDelete.setEnabled(false);
                        this.btnSave.setEnabled(false);
                        this.btnAñadir.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
            }
        }

        else if(this.tableShowEmail.getSelectedRows().length > 0){//actualiza email
            if( Validations.lenght(email, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo incluyendo @example.com!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                emailEmp.requestFocus();
            }
            else if( Validations.lenght(descEmail, 40) ){
                JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                descEmailEmp.requestFocus();
            }
            else{
                int row = tableShowEmail.getSelectedRow();
                int cod = Integer.parseInt(tableShowEmail.getValueAt(row, 0).toString());
                int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste correo?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.updateEmail(emailEmp.getText(), descEmailEmp.getText(), cod);
                        JOptionPane.showMessageDialog(null, "Correo actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        this.showEmail(getCodigoEmp());
                        this.emailEmp.setText("");this.emailEmp.setEnabled(false);
                        this.descEmailEmp.setText("");this.descEmailEmp.setEnabled(false);
                        this.btnUpdate.setEnabled(false);
                        this.btnDelete.setEnabled(false);
                        this.btnSave.setEnabled(false);
                        this.btnAñadir.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
            }
        }
        
        else if( this.tableHorario.getSelectedRows().length > 0 ){ //actualiza horario
            String dia  = (String)diaEmp.getSelectedItem(),
                   hEnt = (String)hEntradaEmp.getSelectedItem(),
                   hSal = (String)hSalidaEmp.getSelectedItem();
            
            if( dia.equals("Selecciona un día..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún día.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                diaEmp.requestFocus();
            }
            else if( hEnt.equals("Selecciona un horario..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún horario de entrada.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                hEntradaEmp.requestFocus();
            }
            else if( hSal.equals("Selecciona un horario..") ){
                JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún horario de salida.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                hSalidaEmp.requestFocus();
            }
            else{
                int row = tableHorario.getSelectedRow();
                int cod = Integer.parseInt(tableHorario.getValueAt(row, 0).toString());
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas actualizar éste horario?","Confirmar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if( req == JOptionPane.YES_OPTION ){
                    try{
                        storedProcedures.updateDia(dia, cod);
                        storedProcedures.updateHorario(hEnt, hSal, cod);
                        JOptionPane.showMessageDialog(null, "Horario actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        this.showHorario(getCodigoEmp());
                        diaEmp.setSelectedIndex(0);diaEmp.setEnabled(false);
                        hEntradaEmp.setSelectedIndex(0);hEntradaEmp.setEnabled(false);
                        hSalidaEmp.setSelectedIndex(0);hSalidaEmp.setEnabled(false);
                        btnDelete.setEnabled(false);
                        btnUpdate.setEnabled(false);
                        this.btnSave.setEnabled(false);
                        this.btnAñadir.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
            }
        }

        else if(this.tableDomicilios.getSelectedRows().length > 0){//actualiza domicilios
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
                    calleEmp.requestFocus();
                }
                else if( Validations.lenght(colonia, 20) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 20 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    coloniaEmp.requestFocus();
                }
                else if( Validations.lenght(numExt, 5) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 5 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    numExtEmp.requestFocus();
                }
                else if( Validations.lenght(numInt, 4) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 4 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    numIntEmp.requestFocus();
                }
                else if( Validations.ValidarCp(cp) ){
                    JOptionPane.showMessageDialog(null, "Ingresa 5 números en el código postal!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    cpEmp.requestFocus();
                }
                else if( Validations.lenght(descrip, 40) ){
                    JOptionPane.showMessageDialog(null, "Sólo se permiten 40 caracteres como máximo!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    descDomEmp.requestFocus();
                }
                else if( estado.equals("Selecciona un estado..") ){
                    JOptionPane.showMessageDialog(null, "Aún no has seleccionado ningún estado.." , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    estadoEmp.requestFocus();
                }
                else{
                    int row = tableDomicilios.getSelectedRow();
                    int cod = Integer.parseInt(tableDomicilios.getValueAt(row, 0).toString());
                    int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea actualizar éste domicilio?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                    if(req == JOptionPane.YES_OPTION){
                        try{
                            storedProcedures.updateDom(cod, calleEmp.getText(), coloniaEmp.getText(), numIntEmp.getText(), Integer.parseInt(numExtEmp.getText()), Integer.parseInt(cpEmp.getText()), descDomEmp.getText(), (String)estadoEmp.getSelectedItem(), ciudadEmp.getText());
                            JOptionPane.showMessageDialog(null, "Domicilio actualizado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                            this.showAddress(getCodigoEmp());

                            calleEmp.setText("");calleEmp.setEnabled(false);
                            coloniaEmp.setText("");coloniaEmp.setEnabled(false);
                            numIntEmp.setText("");numIntEmp.setEnabled(false);
                            numExtEmp.setText("");numExtEmp.setEnabled(false);
                            cpEmp.setText("");cpEmp.setEnabled(false);
                            descDomEmp.setText("");descDomEmp.setEnabled(false);
                            estadoEmp.setSelectedIndex(0);estadoEmp.setEnabled(false);
                            ciudadEmp.setText("");ciudadEmp.setEnabled(false);

                            this.btnUpdate.setEnabled(false);
                            this.btnDelete.setEnabled(false);
                            this.btnSave.setEnabled(false);
                            this.btnAñadir.setEnabled(false);
                        }
                        catch(SQLException e){}
                    }
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(this.tableShowPersonalData.getSelectedRows().length > 0){//elimina logicamente un empleado
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste empleado?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                    storedProcedures.deleteEmpleado(getCodigoEmp());
                    JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    showEmpleado();
                    cleanAddress();
                    cleanTel();
                    cleanEmail();
                    cleanHorario();
                    nombresEmp.setText("");nombresEmp.setEnabled(false);
                    appEmp.setText("");appEmp.setEnabled(false);
                    apmEmp.setText("");apmEmp.setEnabled(false);
                    masculino.setSelected(false);masculino.setEnabled(false);
                    femenino.setSelected(false);femenino.setEnabled(false);
                    this.btnDelete.setEnabled(false);
                    this.btnUpdate.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }

        else if(this.tableShowTel.getSelectedRows().length > 0){//elimina un registro de telefono de un empleado
            int row = tableShowTel.getSelectedRow();
            int cod = Integer.parseInt(tableShowTel.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste telefono?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                    storedProcedures.deleteTel_Empleado(cod);
                    storedProcedures.deleteTel(cod);
                    JOptionPane.showMessageDialog(null, "Telefono eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    this.showTel(getCodigoEmp());
                    tableShowPersonalData.clearSelection();
                    telEmp.setText("");telEmp.setEnabled(false);
                    descTelEmp.setText("");descTelEmp.setEnabled(false);
                    this.btnDelete.setEnabled(false);
                    this.btnUpdate.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }

        else if(this.tableShowEmail.getSelectedRows().length > 0){//elimina un registro de correo de un empleado
            int row = tableShowEmail.getSelectedRow();
            int cod = Integer.parseInt(tableShowEmail.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste correo?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                    storedProcedures.deleteEmail_Empleado(cod);
                    storedProcedures.deleteEmail(cod);
                    JOptionPane.showMessageDialog(null, "Correo eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    this.showEmail(getCodigoEmp());
                    tableShowPersonalData.clearSelection();
                    emailEmp.setText("");emailEmp.setEnabled(false);
                    descEmailEmp.setText("");descEmailEmp.setEnabled(false);
                    this.btnDelete.setEnabled(false);
                    this.btnUpdate.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }

        else if(this.tableDomicilios.getSelectedRows().length > 0){//elimina un registro de domicilio de un empleado
            int row = tableDomicilios.getSelectedRow();
            int cod = Integer.parseInt(tableDomicilios.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro que desea eliminar éste domicilio?" , "Confirmar" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
            if(req == JOptionPane.YES_OPTION){
                try{
                    storedProcedures.deleteDomEmpleado(cod);
                    JOptionPane.showMessageDialog(null, "Domicilio eliminado exitosamente" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                    this.showAddress(getCodigoEmp());
                    tableDomicilios.clearSelection();
                    calleEmp.setText("");calleEmp.setEnabled(false);
                    coloniaEmp.setText("");coloniaEmp.setEnabled(false);
                    numIntEmp.setText("");numIntEmp.setEnabled(false);
                    numExtEmp.setText("");numExtEmp.setEnabled(false);
                    cpEmp.setText("");cpEmp.setEnabled(false);
                    descDomEmp.setText("");descDomEmp.setEnabled(false);
                    estadoEmp.setSelectedIndex(0);estadoEmp.setEnabled(false);
                    ciudadEmp.setText("");ciudadEmp.setEnabled(false);
                    this.btnDelete.setEnabled(false);
                    this.btnUpdate.setEnabled(false);
                }
                catch(SQLException e){}
            }
        }
        
        else if( tableHorario.getSelectedRows().length > 0 ){ // elimina un registro de horario de un cliente
            int row = tableHorario.getSelectedRow();
            int cod = Integer.parseInt(tableHorario.getValueAt(row, 0).toString());
            int req = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar éste registro de horario?", "Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if( req == JOptionPane.YES_OPTION ){
                try{
                    storedProcedures.deleteDia_Horario(cod);
                    storedProcedures.deleteHorario_Empleado(cod);
                    storedProcedures.deleteDia(cod);
                    storedProcedures.deleteHorario(cod);
                    JOptionPane.showMessageDialog(null, "Horario eliminado exitosamente!!","Genial",JOptionPane.INFORMATION_MESSAGE);
                    this.showHorario(getCodigoEmp());
                    diaEmp.setSelectedIndex(0);diaEmp.setEnabled(false);
                    hEntradaEmp.setSelectedIndex(0);hEntradaEmp.setEnabled(false);
                    hSalidaEmp.setSelectedIndex(0);hSalidaEmp.setEnabled(false);
                    this.btnDelete.setEnabled(false);
                    this.btnUpdate.setEnabled(false);
                }
                catch(SQLException e){
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Proceso cancelado","Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        /*Datos personales */
        nombresEmp.setEnabled(true);
        nombresEmp.setText("");
        nombresEmp.requestFocus();
        this.appEmp.setEnabled(true);
        appEmp.setText("");
        this.apmEmp.setEnabled(true);
        apmEmp.setText("");
        this.masculino.setEnabled(true);
        this.masculino.setSelected(false);
        this.femenino.setEnabled(true);
        this.femenino.setSelected(false);
        /*Domicilio */
        this.calleEmp.setEnabled(true);
        calleEmp.setText("");
        this.coloniaEmp.setEnabled(true);
        coloniaEmp.setText("");
        this.numExtEmp.setEnabled(true);
        numExtEmp.setText("");
        this.numIntEmp.setEnabled(true);
        numIntEmp.setText("");
        this.cpEmp.setEnabled(true);
        cpEmp.setText("");
        this.descDomEmp.setEnabled(true);
        descDomEmp.setText("");
        this.estadoEmp.setEnabled(true);
        this.estadoEmp.setSelectedIndex(0);
        this.ciudadEmp.setEnabled(true);
        this.ciudadEmp.setText("");
        /*  Telefono */
        this.telEmp.setEnabled(true);
        telEmp.setText("");
        this.descTelEmp.setEnabled(true);
        descTelEmp.setText("");
        /*Email */
        this.emailEmp.setEnabled(true);
        emailEmp.setText("");
        this.descEmailEmp.setEnabled(true);
        descEmailEmp.setText("");
        diaEmp.setEnabled(true);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(true);
        hEntradaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(true);
        hSalidaEmp.setSelectedIndex(0);

        this.btnSave.setEnabled(true);
        this.btnDelete.setEnabled(false);
        this.btnUpdate.setEnabled(false);
        this.btnAñadir.setEnabled(false);

        this.btnAddTel.setEnabled(false);
        this.btnAddEmail.setEnabled(false);
        this.btnAddAddress.setEnabled(false);
        this.btnAddHorario.setEnabled(false);

        /*tables*/
        tableShowPersonalData.clearSelection();
        tableDomicilios.clearSelection();
        tableShowTel.clearSelection();
        tableShowEmail.clearSelection();

        cleanAddress();
        cleanTel();
        cleanEmail();
        cleanHorario();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        if(getFlagAdd() == 1){//si vale 1 añade un nuevo telefono
            if( telEmp.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Aún no has llenado éste campo!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
                telEmp.requestFocus();
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo teléfono?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addTelefono(telEmp.getText(), descTelEmp.getText());
                        int codTel = storedProcedures.getID("cod_telefono", "telefono" );
                        JOptionPane.showMessageDialog(null, "Teléfono agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        storedProcedures.addTel_Empleado(codTel, getCodigoEmp());
                        showTel(getCodigoEmp());
                        telEmp.setText("");telEmp.setEnabled(false);
                        descTelEmp.setText("");descTelEmp.setEnabled(false);
                        this.btnAñadir.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    telEmp.setText("");telEmp.setEnabled(false);
                    descTelEmp.setText("");descTelEmp.setEnabled(false);
                    this.btnAñadir.setEnabled(false);
                }
            }
        }

        else if( getFlagAdd() == 2 ){//si vale 2 añade un nuevo correo
            if( emailEmp.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Aún no has llenado éste campo!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
                emailEmp.requestFocus();
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo correo?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addEmail(emailEmp.getText(), descEmailEmp.getText());
                        int codEmail = storedProcedures.getID("cod_correo", "correo" );
                        JOptionPane.showMessageDialog(null, "Correo agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        storedProcedures.addEmail_Empleado(codEmail, getCodigoEmp());
                        showEmail(getCodigoEmp());
                        emailEmp.setText("");emailEmp.setEnabled(false);
                        descEmailEmp.setText("");descEmailEmp.setEnabled(false);
                        this.btnAñadir.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    emailEmp.setText("");emailEmp.setEnabled(false);
                    descEmailEmp.setText("");descEmailEmp.setEnabled(false);
                    this.btnAñadir.setEnabled(false);
                }
            }
        }

        else if( getFlagAdd() == 3 ){//si vale 2 añade un nuevo domicilio
            if( calleEmp.getText().isEmpty() || coloniaEmp.getText().isEmpty() || numExtEmp.getText().isEmpty() || cpEmp.getText().isEmpty() || estadoEmp.getSelectedIndex() == 0 || ciudadEmp.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Completa los campos obligatorios!!", "DVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
            else{
                int req = JOptionPane.showConfirmDialog(this, "¿Seguro que desea agregar nuevo domicilio?","Responder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(req == JOptionPane.YES_OPTION){
                    try{
                        storedProcedures.addDomicilio(calleEmp.getText(), numIntEmp.getText(), Integer.parseInt(numExtEmp.getText()), descDomEmp.getText());
                        storedProcedures.addColonia(coloniaEmp.getText(), Integer.parseInt(cpEmp.getText()));
                        storedProcedures.addEstado((String)estadoEmp.getSelectedItem());
                        storedProcedures.addCiudad(ciudadEmp.getText());
                        int cod = storedProcedures.getID("cod_domicilio", "domicilio" );
                        storedProcedures.addDomicilio_Empleado(cod, getCodigoEmp());
                        storedProcedures.addColonia_Domicilio(cod, cod);
                        storedProcedures.addEstado_Domicilio(cod, cod);
                        storedProcedures.addCiudad_Domicilio(cod, cod);
                        JOptionPane.showMessageDialog(null, "Domicilio agregado correctamente!!" , "Genial" , JOptionPane.INFORMATION_MESSAGE);
                        showAddress(getCodigoEmp());

                        calleEmp.setText("");calleEmp.setEnabled(false);
                        coloniaEmp.setText("");coloniaEmp.setEnabled(false);
                        numIntEmp.setText("");numIntEmp.setEnabled(false);
                        numExtEmp.setText("");numExtEmp.setEnabled(false);
                        cpEmp.setText("");cpEmp.setEnabled(false);
                        descDomEmp.setText("");descDomEmp.setEnabled(false);
                        estadoEmp.setSelectedIndex(0);estadoEmp.setEnabled(false);
                        ciudadEmp.setText("");ciudadEmp.setEnabled(false);
                        this.btnAñadir.setEnabled(false);
                    }
                    catch(SQLException e){}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operación cancelada!!" , "Advertencia" , JOptionPane.WARNING_MESSAGE);
                    calleEmp.setText("");calleEmp.setEnabled(false);
                    coloniaEmp.setText("");coloniaEmp.setEnabled(false);
                    numIntEmp.setText("");numIntEmp.setEnabled(false);
                    numExtEmp.setText("");numExtEmp.setEnabled(false);
                    cpEmp.setText("");cpEmp.setEnabled(false);
                    descDomEmp.setText("");descDomEmp.setEnabled(false);
                    estadoEmp.setSelectedIndex(0);estadoEmp.setEnabled(false);
                    ciudadEmp.setText("");ciudadEmp.setEnabled(false);
                    this.btnAñadir.setEnabled(false);
                }
            }
        }
        
        else if( getFlagAdd() == 4 ){
            if( diaEmp.getSelectedIndex() == 0 ){
                JOptionPane.showMessageDialog(null, "Selecciona una opción de los días!!" , "Advertencia!!", JOptionPane.INFORMATION_MESSAGE);
                diaEmp.requestFocus();
            }
            else if( hEntradaEmp.getSelectedIndex() == 0 ){
                JOptionPane.showMessageDialog(null, "Selecciona una opción de horario de entrada!!" , "Advertencia!!", JOptionPane.INFORMATION_MESSAGE);
                hEntradaEmp.requestFocus();
            }
            else if( hSalidaEmp.getSelectedIndex() == 0 ){
                JOptionPane.showMessageDialog(null, "Selecciona una opción de horario de salida!!" , "Advertencia!!", JOptionPane.INFORMATION_MESSAGE);
                hSalidaEmp.requestFocus();
            }
            else{
                int op = JOptionPane.showConfirmDialog(this, "¿Seguro que desea añadir un nuevo registro de horario?", "Confirmar", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
                if( op == JOptionPane.NO_OPTION ){
                    JOptionPane.showMessageDialog(null, "Proceso cancelado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    diaEmp.setSelectedIndex(0);
                    diaEmp.setEnabled(false);
                    hEntradaEmp.setSelectedIndex(0);
                    hEntradaEmp.setEnabled(false);
                    hSalidaEmp.setSelectedIndex(0);
                    hSalidaEmp.setEnabled(false);
                    this.btnAñadir.setEnabled(false);
                }
                else{
                    try{
                        storedProcedures.addDia((String)diaEmp.getSelectedItem());
                        int codDia = storedProcedures.getID("cod_dia", "dia");
                        
                        storedProcedures.addHorario((String)hEntradaEmp.getSelectedItem(), (String)hSalidaEmp.getSelectedItem());
                        int codHorario = storedProcedures.getID("cod_horario", "horario");
                        
                        storedProcedures.addDia_horario(codDia, codHorario);
                        storedProcedures.addHorario_empleado(codHorario, getCodigoEmp());
                        
                        JOptionPane.showMessageDialog(null, "Nuevo horario almacenado correctamente","Genial!!", JOptionPane.INFORMATION_MESSAGE);
                        showHorario(getCodigoEmp());
                    }
                    catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Error al almacenarlo, intenta de nuevo", "Error!!" , JOptionPane.ERROR_MESSAGE);
                    }
                }  
            }
        }
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        cleanTel();
        cleanEmail();
        cleanAddress();
        cleanHorario();
        tableShowPersonalData.clearSelection();

        this.btnAñadir.setEnabled(false);
        this.btnDelete.setEnabled(false);
        this.btnSave.setEnabled(false);
        this.btnUpdate.setEnabled(false);
        this.btnAddAddress.setEnabled(false);
        this.btnAddEmail.setEnabled(false);
        this.btnAddTel.setEnabled(false);
        this.btnAddHorario.setEnabled(false);

        nombresEmp.setEnabled(false);
        nombresEmp.setText("");
        nombresEmp.requestFocus();
        this.appEmp.setEnabled(false);
        appEmp.setText("");
        this.apmEmp.setEnabled(false);
        apmEmp.setText("");
        this.masculino.setEnabled(false);
        this.masculino.setSelected(false);
        this.femenino.setEnabled(false);
        this.femenino.setSelected(false);
        /*Domicilio */
        this.calleEmp.setEnabled(false);
        calleEmp.setText("");
        this.coloniaEmp.setEnabled(false);
        coloniaEmp.setText("");
        this.numExtEmp.setEnabled(false);
        numExtEmp.setText("");
        this.numIntEmp.setEnabled(false);
        numIntEmp.setText("");
        this.cpEmp.setEnabled(false);
        cpEmp.setText("");
        this.descDomEmp.setEnabled(false);
        descDomEmp.setText("");
        this.estadoEmp.setEnabled(false);
        this.estadoEmp.setSelectedIndex(0);
        this.ciudadEmp.setEnabled(false);
        this.ciudadEmp.setText("");
        /*  Telefono */
        this.telEmp.setEnabled(false);
        telEmp.setText("");
        this.descTelEmp.setEnabled(false);
        descTelEmp.setText("");
        /*Email */
        this.emailEmp.setEnabled(false);
        emailEmp.setText("");
        this.descEmailEmp.setEnabled(false);
        descEmailEmp.setText("");
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hEntradaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
    }//GEN-LAST:event_resetActionPerformed

    private void tableShowPersonalDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShowPersonalDataMouseClicked
        this.btnSave.setEnabled(false);
        this.btnAñadir.setEnabled(false);
        this.btnDelete.setEnabled(true);
        this.btnUpdate.setEnabled(true);

        this.btnAddTel.setEnabled(true);
        this.btnAddEmail.setEnabled(true);
        this.btnAddAddress.setEnabled(true);
        this.btnAddHorario.setEnabled(true);

        tableDomicilios.clearSelection();
        tableShowTel.clearSelection();
        tableShowEmail.clearSelection();
        tableHorario.clearSelection();

        calleEmp.setText("");
        calleEmp.setEnabled(false);
        coloniaEmp.setText("");
        coloniaEmp.setEnabled(false);
        numExtEmp.setText("");
        numExtEmp.setEnabled(false);
        numIntEmp.setText("");
        numIntEmp.setEnabled(false);
        cpEmp.setText("");
        cpEmp.setEnabled(false);
        descDomEmp.setText("");
        descDomEmp.setEnabled(false);
        estadoEmp.setSelectedIndex(0);
        estadoEmp.setEnabled(false);
        ciudadEmp.setEnabled(false);
        ciudadEmp.setText("");
        telEmp.setText("");
        telEmp.setEnabled(false);
        descTelEmp.setText("");
        descTelEmp.setEnabled(false);
        emailEmp.setText("");
        emailEmp.setEnabled(false);
        descEmailEmp.setText("");
        descEmailEmp.setEnabled(false);
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);

        try{
            int row = tableShowPersonalData.getSelectedRow();
            setCodigoEmp(Integer.parseInt(tableShowPersonalData.getValueAt(row, 0).toString()));
            res = conexionsqlserver.ConnectionDB.Query("select * from empleado where cod_empleado="+getCodigoEmp());
            while(res.next()){
                nombresEmp.setText(res.getString("nombres_empleado"));
                nombresEmp.setEnabled(true);
                appEmp.setText(res.getString("apellidop_empleado"));
                appEmp.setEnabled(true);
                apmEmp.setText(res.getString("apellidom_empleado"));
                apmEmp.setEnabled(true);
                masculino.setEnabled(true);
                femenino.setEnabled(true);

                String getSex = res.getString("sexo_empleado");
                if( getSex.equals("Masculino") ){
                    masculino.setSelected(true);
                    femenino.setSelected(false);
                    setFlag(1);
                    setSexo("Masculino");
                }
                else{
                    masculino.setSelected(false);
                    femenino.setSelected(true);
                    setFlag(2);
                    setSexo("Femenino");
                }
            }

            showAddress(getCodigoEmp());
            showTel(getCodigoEmp());
            showHorario(getCodigoEmp());
            showEmail(getCodigoEmp());

        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableShowPersonalDataMouseClicked

    private void tableShowTelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShowTelMouseClicked
        this.btnSave.setEnabled(false);
        this.btnAñadir.setEnabled(false);
        this.btnDelete.setEnabled(true);
        this.btnUpdate.setEnabled(true);

        tableShowPersonalData.clearSelection();
        tableShowEmail.clearSelection();
        tableDomicilios.clearSelection();
        tableHorario.clearSelection();

        nombresEmp.setEnabled(false);
        appEmp.setEnabled(false);
        apmEmp.setEnabled(false);
        masculino.setEnabled(false);
        femenino.setEnabled(false);
        calleEmp.setText("");
        calleEmp.setEnabled(false);
        coloniaEmp.setText("");
        coloniaEmp.setEnabled(false);
        numExtEmp.setText("");
        numExtEmp.setEnabled(false);
        numIntEmp.setText("");
        numIntEmp.setEnabled(false);
        cpEmp.setText("");
        cpEmp.setEnabled(false);
        descDomEmp.setText("");
        descDomEmp.setEnabled(false);
        estadoEmp.setSelectedIndex(0);
        estadoEmp.setEnabled(false);
        ciudadEmp.setEnabled(false);
        ciudadEmp.setText("");
        emailEmp.setText("");
        emailEmp.setEnabled(false);
        descEmailEmp.setText("");
        descEmailEmp.setEnabled(false);
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);

        try{
            int row = tableShowTel.getSelectedRow();
            int codTel = Integer.parseInt(tableShowTel.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from telefono where cod_telefono='"+codTel+"'");
            while(res.next()){
                telEmp.setText(res.getString("numero_telefono"));
                telEmp.setEnabled(true);
                descTelEmp.setText(res.getString("descripcion_telefono"));
                descTelEmp.setEnabled(true);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableShowTelMouseClicked

    private void tableDomiciliosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDomiciliosMouseClicked
        this.btnSave.setEnabled(false);
        this.btnAñadir.setEnabled(false);
        this.btnDelete.setEnabled(true);
        this.btnUpdate.setEnabled(true);

        tableShowPersonalData.clearSelection();
        tableShowTel.clearSelection();
        tableShowEmail.clearSelection();
        tableHorario.clearSelection();

        nombresEmp.setEnabled(false);
        appEmp.setEnabled(false);
        apmEmp.setEnabled(false);
        masculino.setEnabled(false);
        femenino.setEnabled(false);
        telEmp.setText("");
        telEmp.setEnabled(false);
        descTelEmp.setText("");
        descTelEmp.setEnabled(false);
        emailEmp.setText("");
        emailEmp.setEnabled(false);
        descEmailEmp.setText("");
        descEmailEmp.setEnabled(false);
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);

        try{
            int row = tableDomicilios.getSelectedRow();
            int codD = Integer.parseInt(tableDomicilios.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from domicilio, colonia, estado, ciudad where cod_domicilio="+codD+" and cod_colonia="+codD+" and cod_estado="+codD+" and cod_ciudad="+codD);
            while(res.next()){
                calleEmp.setText(res.getString("calle"));
                calleEmp.setEnabled(true);
                coloniaEmp.setText(res.getString("nombre_colonia"));
                coloniaEmp.setEnabled(true);
                numExtEmp.setText(res.getString("num_ext"));
                numExtEmp.setEnabled(true);
                numIntEmp.setText(res.getString("num_int"));
                numIntEmp.setEnabled(true);
                cpEmp.setText(res.getString("cp"));
                cpEmp.setEnabled(true);
                descDomEmp.setText(res.getString("descripcion_domicilio"));
                descDomEmp.setEnabled(true);
                estadoEmp.setEnabled(true);
                ciudadEmp.setText(res.getString("nombre_ciudad"));
                ciudadEmp.setEnabled(true);

                String estado = res.getString("nombre_estado");
                switch(estado){
                    case "Aguascalientes":
                    estadoEmp.setSelectedIndex(1);
                    break;
                    case "Baja California":
                    estadoEmp.setSelectedIndex(2);
                    break;
                    case " Baja California Sur":
                    estadoEmp.setSelectedIndex(3);
                    break;
                    case "Campeche":
                    estadoEmp.setSelectedIndex(4);
                    break;
                    case "Chiapas":
                    estadoEmp.setSelectedIndex(5);
                    break;
                    case "Chihuahua":
                    estadoEmp.setSelectedIndex(6);
                    break;
                    case "Coahuila":
                    estadoEmp.setSelectedIndex(7);
                    break;
                    case "Colima":
                    estadoEmp.setSelectedIndex(8);
                    break;
                    case "Durango":
                    estadoEmp.setSelectedIndex(9);
                    break;
                    case "Edo. México":
                    estadoEmp.setSelectedIndex(10);
                    break;
                    case "Guanajuato":
                    estadoEmp.setSelectedIndex(11);
                    break;
                    case "Guerrero":
                    estadoEmp.setSelectedIndex(12);
                    break;
                    case "Hidalgo":
                    estadoEmp.setSelectedIndex(13);
                    break;
                    case "Jalisco":
                    estadoEmp.setSelectedIndex(14);
                    break;
                    case "Michoacán":
                    estadoEmp.setSelectedIndex(15);
                    break;
                    case "Morelos":
                    estadoEmp.setSelectedIndex(16);
                    break;
                    case "Nayarit":
                    estadoEmp.setSelectedIndex(17);
                    break;
                    case "Nuevo León":
                    estadoEmp.setSelectedIndex(18);
                    break;
                    case "Oaxaca":
                    estadoEmp.setSelectedIndex(19);
                    break;
                    case "Puebla":
                    estadoEmp.setSelectedIndex(20);
                    break;
                    case "Querétaro":
                    estadoEmp.setSelectedIndex(21);
                    break;
                    case "Quintana Roo":
                    estadoEmp.setSelectedIndex(22);
                    break;
                    case "San Luis Potosí":
                    estadoEmp.setSelectedIndex(23);
                    break;
                    case "Sinaloa":
                    estadoEmp.setSelectedIndex(24);
                    break;
                    case "Sonora":
                    estadoEmp.setSelectedIndex(25);
                    break;
                    case "Tabasco":
                    estadoEmp.setSelectedIndex(26);
                    break;
                    case "Tamaulipas":
                    estadoEmp.setSelectedIndex(27);
                    break;
                    case "Tlaxcala":
                    estadoEmp.setSelectedIndex(28);
                    break;
                    case "Veracruz":
                    estadoEmp.setSelectedIndex(29);
                    break;
                    case "Yucatán":
                    estadoEmp.setSelectedIndex(30);
                    break;
                    case "Zacatecas":
                    estadoEmp.setSelectedIndex(31);
                    break;
                }
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableDomiciliosMouseClicked

    private void btnAddTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTelActionPerformed
        this.btnAñadir.setEnabled(true);
        this.btnDelete.setEnabled(false);
        this.btnUpdate.setEnabled(false);
        this.btnSave.setEnabled(false);

        this.nombresEmp.setEnabled(false);
        this.appEmp.setEnabled(false);
        this.apmEmp.setEnabled(false);
        this.masculino.setEnabled(false);
        this.femenino.setEnabled(false);

        this.calleEmp.setText("");this.calleEmp.setEnabled(false);
        this.coloniaEmp.setText("");this.coloniaEmp.setEnabled(false);
        this.numExtEmp.setText("");this.numExtEmp.setEnabled(false);
        this.numIntEmp.setText("");this.numIntEmp.setEnabled(false);
        this.cpEmp.setText("");this.cpEmp.setEnabled(false);
        this.descDomEmp.setText("");this.descDomEmp.setEnabled(false);
        this.estadoEmp.setSelectedIndex(0);this.estadoEmp.setEnabled(false);
        this.ciudadEmp.setText("");this.ciudadEmp.setEnabled(false);

        this.telEmp.setText("");this.telEmp.setEnabled(true);this.telEmp.requestFocus();
        this.descTelEmp.setText("");this.descTelEmp.setEnabled(true);

        this.emailEmp.setText("");this.emailEmp.setEnabled(false);
        this.descEmailEmp.setText("");this.descEmailEmp.setEnabled(false);
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hEntradaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
        setFlagAdd(1);
        
        tableShowPersonalData.clearSelection();
        tableShowTel.clearSelection();
        tableShowEmail.clearSelection();
        tableDomicilios.clearSelection();
        tableHorario.clearSelection();
    }//GEN-LAST:event_btnAddTelActionPerformed

    private void btnAddEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmailActionPerformed
        this.btnAñadir.setEnabled(true);
        this.btnDelete.setEnabled(false);
        this.btnUpdate.setEnabled(false);
        this.btnSave.setEnabled(false);

        this.nombresEmp.setEnabled(false);
        this.appEmp.setEnabled(false);
        this.apmEmp.setEnabled(false);
        this.masculino.setEnabled(false);
        this.femenino.setEnabled(false);

        this.calleEmp.setText("");this.calleEmp.setEnabled(false);
        this.coloniaEmp.setText("");this.coloniaEmp.setEnabled(false);
        this.numExtEmp.setText("");this.numExtEmp.setEnabled(false);
        this.numIntEmp.setText("");this.numIntEmp.setEnabled(false);
        this.cpEmp.setText("");this.cpEmp.setEnabled(false);
        this.descDomEmp.setText("");this.descDomEmp.setEnabled(false);
        this.estadoEmp.setSelectedIndex(0);this.estadoEmp.setEnabled(false);
        this.ciudadEmp.setText("");this.ciudadEmp.setEnabled(false);

        this.telEmp.setText("");this.telEmp.setEnabled(false);
        this.descTelEmp.setText("");this.descTelEmp.setEnabled(false);

        this.emailEmp.setText("");this.emailEmp.setEnabled(true);this.emailEmp.requestFocus();
        this.descEmailEmp.setText("");this.descEmailEmp.setEnabled(true);
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hEntradaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
        setFlagAdd(2);
        
        tableShowPersonalData.clearSelection();
        tableShowTel.clearSelection();
        tableShowEmail.clearSelection();
        tableDomicilios.clearSelection();
        tableHorario.clearSelection();
    }//GEN-LAST:event_btnAddEmailActionPerformed

    private void btnAddAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAddressActionPerformed
        this.btnAñadir.setEnabled(true);
        this.btnDelete.setEnabled(false);
        this.btnUpdate.setEnabled(false);
        this.btnSave.setEnabled(false);

        this.nombresEmp.setEnabled(false);
        this.appEmp.setEnabled(false);
        this.apmEmp.setEnabled(false);
        this.masculino.setEnabled(false);
        this.femenino.setEnabled(false);

        this.calleEmp.setText("");this.calleEmp.setEnabled(true);this.calleEmp.requestFocus();
        this.coloniaEmp.setText("");this.coloniaEmp.setEnabled(true);
        this.numExtEmp.setText("");this.numExtEmp.setEnabled(true);
        this.numIntEmp.setText("");this.numIntEmp.setEnabled(true);
        this.cpEmp.setText("");this.cpEmp.setEnabled(true);
        this.descDomEmp.setText("");this.descDomEmp.setEnabled(true);
        this.estadoEmp.setSelectedIndex(0);this.estadoEmp.setEnabled(true);
        this.ciudadEmp.setText("");this.ciudadEmp.setEnabled(true);

        this.telEmp.setText("");this.telEmp.setEnabled(false);
        this.descTelEmp.setText("");this.descTelEmp.setEnabled(false);
        this.emailEmp.setText("");this.emailEmp.setEnabled(false);
        this.descEmailEmp.setText("");this.descEmailEmp.setEnabled(false);
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hEntradaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
        setFlagAdd(3);
        tableShowPersonalData.clearSelection();
        tableShowTel.clearSelection();
        tableShowEmail.clearSelection();
        tableDomicilios.clearSelection();
        tableHorario.clearSelection();
    }//GEN-LAST:event_btnAddAddressActionPerformed

    private void tableShowEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShowEmailMouseClicked
        this.btnSave.setEnabled(false);
        this.btnAñadir.setEnabled(false);
        this.btnDelete.setEnabled(true);
        this.btnUpdate.setEnabled(true);

        this.btnAddTel.setEnabled(true);
        this.btnAddEmail.setEnabled(true);
        this.btnAddAddress.setEnabled(true);
        this.btnAddHorario.setEnabled(true);

        tableDomicilios.clearSelection();
        tableShowTel.clearSelection();
        tableShowPersonalData.clearSelection();
        tableHorario.clearSelection();
        
        nombresEmp.setText("");
        nombresEmp.setEnabled(false);
        appEmp.setText("");
        appEmp.setEnabled(false);
        apmEmp.setText("");
        apmEmp.setEnabled(false);
        masculino.setEnabled(false);
        femenino.setEnabled(false);
        calleEmp.setText("");
        calleEmp.setEnabled(false);
        coloniaEmp.setText("");
        coloniaEmp.setEnabled(false);
        numExtEmp.setText("");
        numExtEmp.setEnabled(false);
        numIntEmp.setText("");
        numIntEmp.setEnabled(false);
        cpEmp.setText("");
        cpEmp.setEnabled(false);
        descDomEmp.setText("");
        descDomEmp.setEnabled(false);
        estadoEmp.setSelectedIndex(0);
        estadoEmp.setEnabled(false);
        ciudadEmp.setEnabled(false);
        ciudadEmp.setText("");
        telEmp.setText("");
        telEmp.setEnabled(false);
        descTelEmp.setText("");
        descTelEmp.setEnabled(false);
        diaEmp.setEnabled(false);
        diaEmp.setSelectedIndex(0);
        hEntradaEmp.setSelectedIndex(0);
        hEntradaEmp.setEnabled(false);
        hSalidaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(false);

        try{
            int row = tableShowEmail.getSelectedRow();
            int codEmail = Integer.parseInt(tableShowEmail.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query("select * from correo where cod_correo="+codEmail);
            while(res.next()){
                emailEmp.setText(res.getString("nombre_correo"));
                emailEmp.setEnabled(true);
                descEmailEmp.setText(res.getString("descripcion_correo"));
                descEmailEmp.setEnabled(true);
            }

        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableShowEmailMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.dispose();
        App app = new App();
        app.setVisible(true);
        app.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowClosed

    private void descEmailEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descEmailEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descEmailEmpActionPerformed

    private void emailEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailEmpActionPerformed

    private void btnAddHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHorarioActionPerformed
        this.btnAñadir.setEnabled(true);
        this.btnDelete.setEnabled(false);
        this.btnUpdate.setEnabled(false);
        this.btnSave.setEnabled(false);

        this.nombresEmp.setEnabled(false);
        this.appEmp.setEnabled(false);
        this.apmEmp.setEnabled(false);
        this.masculino.setEnabled(false);
        this.femenino.setEnabled(false);

        this.calleEmp.setText("");this.calleEmp.setEnabled(false);
        this.coloniaEmp.setText("");this.coloniaEmp.setEnabled(false);
        this.numExtEmp.setText("");this.numExtEmp.setEnabled(false);
        this.numIntEmp.setText("");this.numIntEmp.setEnabled(false);
        this.cpEmp.setText("");this.cpEmp.setEnabled(false);
        this.descDomEmp.setText("");this.descDomEmp.setEnabled(false);
        this.estadoEmp.setSelectedIndex(0);this.estadoEmp.setEnabled(false);
        this.ciudadEmp.setText("");this.ciudadEmp.setEnabled(false);

        this.telEmp.setText("");this.telEmp.setEnabled(false);
        this.descTelEmp.setText("");this.descTelEmp.setEnabled(false);
        this.emailEmp.setText("");this.emailEmp.setEnabled(false);
        this.descEmailEmp.setText("");this.descEmailEmp.setEnabled(false);
        diaEmp.setEnabled(true);
        diaEmp.setSelectedIndex(0);diaEmp.requestFocus();
        hEntradaEmp.setEnabled(true);
        hEntradaEmp.setSelectedIndex(0);
        hSalidaEmp.setEnabled(true);
        hSalidaEmp.setSelectedIndex(0);
        setFlagAdd(4);
        tableShowPersonalData.clearSelection();
        tableShowTel.clearSelection();
        tableShowEmail.clearSelection();
        tableDomicilios.clearSelection();
        tableHorario.clearSelection();
    }//GEN-LAST:event_btnAddHorarioActionPerformed

    private void tableHorarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHorarioMouseClicked
        this.btnSave.setEnabled(false);
        this.btnAñadir.setEnabled(false);
        this.btnDelete.setEnabled(true);
        this.btnUpdate.setEnabled(true);

        this.btnAddTel.setEnabled(true);
        this.btnAddEmail.setEnabled(true);
        this.btnAddAddress.setEnabled(true);
        this.btnAddHorario.setEnabled(true);

        tableDomicilios.clearSelection();
        tableShowTel.clearSelection();
        tableShowPersonalData.clearSelection();
        tableShowEmail.clearSelection();
        
        nombresEmp.setText("");
        nombresEmp.setEnabled(false);
        appEmp.setText("");
        appEmp.setEnabled(false);
        apmEmp.setText("");
        apmEmp.setEnabled(false);
        masculino.setEnabled(false);
        femenino.setEnabled(false);
        calleEmp.setText("");
        calleEmp.setEnabled(false);
        coloniaEmp.setText("");
        coloniaEmp.setEnabled(false);
        numExtEmp.setText("");
        numExtEmp.setEnabled(false);
        numIntEmp.setText("");
        numIntEmp.setEnabled(false);
        cpEmp.setText("");
        cpEmp.setEnabled(false);
        descDomEmp.setText("");
        descDomEmp.setEnabled(false);
        estadoEmp.setSelectedIndex(0);
        estadoEmp.setEnabled(false);
        ciudadEmp.setEnabled(false);
        ciudadEmp.setText("");
        telEmp.setText("");
        telEmp.setEnabled(false);
        descTelEmp.setText("");
        descTelEmp.setEnabled(false);
        emailEmp.setText("");
        emailEmp.setEnabled(false);
        descEmailEmp.setText("");
        descEmailEmp.setEnabled(false);
        diaEmp.setEnabled(true);
        hEntradaEmp.setEnabled(true);
        hSalidaEmp.setEnabled(true);

        try{
            int row = tableHorario.getSelectedRow();
            int codHor = Integer.parseInt(tableHorario.getValueAt(row, 0).toString());
            res = conexionsqlserver.ConnectionDB.Query(
                    "select * from horario"
                  + " inner join dia_horario on horario.cod_horario=dia_horario.cod_horario"
                  + " and dia_horario.cod_dia="+codHor
                  + " inner join dia on dia_horario.cod_dia=dia.cod_dia");
            while(res.next()){
                String dia = res.getString("nombre_dia");
                String in  = res.getString("hora_entrada");
                String out = res.getString("hora_salida");
                switch(dia){
                    case "Todos los días":
                        diaEmp.setSelectedIndex(1);
                        break;
                    case "Lunes":
                        diaEmp.setSelectedIndex(2);
                        break;
                    case "Martes":
                        diaEmp.setSelectedIndex(3);
                        break;
                    case "Miércoles":
                        diaEmp.setSelectedIndex(4);
                        break;
                    case "Jueves":
                        diaEmp.setSelectedIndex(5);
                        break;
                    case "Viernes":
                        diaEmp.setSelectedIndex(6);
                        break;
                    case "Sábado":
                        diaEmp.setSelectedIndex(7);
                        break;
                    case "Domingo":
                        diaEmp.setSelectedIndex(8);
                        break;
                }
                switch(in){
                    case "9:00":
                        hEntradaEmp.setSelectedIndex(1);
                        break;
                    case "10:00":
                        hEntradaEmp.setSelectedIndex(2);
                        break;
                    case "11:00":
                        hEntradaEmp.setSelectedIndex(3);
                        break;
                    case "12:00":
                        hEntradaEmp.setSelectedIndex(4);
                        break;
                    case "13:00":
                        hEntradaEmp.setSelectedIndex(5);
                        break;
                    case "14:00":
                        hEntradaEmp.setSelectedIndex(6);
                        break;
                    case "15:00":
                        hEntradaEmp.setSelectedIndex(7);
                        break;
                    case "16:00":
                        hEntradaEmp.setSelectedIndex(8);
                        break;
                    case "17:00":
                        hEntradaEmp.setSelectedIndex(9);
                        break;
                    case "18:00":
                        hEntradaEmp.setSelectedIndex(10);
                        break;
                    case "19:00":
                        hEntradaEmp.setSelectedIndex(11);
                        break;
                }
                switch(out){
                    case "10:00":
                        hSalidaEmp.setSelectedIndex(1);
                        break;
                    case "11:00":
                        hSalidaEmp.setSelectedIndex(2);
                        break;
                    case "12:00":
                        hSalidaEmp.setSelectedIndex(3);
                        break;
                    case "13:00":
                        hSalidaEmp.setSelectedIndex(4);
                        break;
                    case "14:00":
                        hSalidaEmp.setSelectedIndex(5);
                        break;
                    case "15:00":
                        hSalidaEmp.setSelectedIndex(6);
                        break;
                    case "16:00":
                        hSalidaEmp.setSelectedIndex(7);
                        break;
                    case "17:00":
                        hSalidaEmp.setSelectedIndex(8);
                        break;
                    case "18:00":
                        hSalidaEmp.setSelectedIndex(9);
                        break;
                    case "19:00":
                        hSalidaEmp.setSelectedIndex(10);
                        break;
                    case "20:00":
                        hSalidaEmp.setSelectedIndex(11);
                        break;
                }
            }

        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se mostraron", "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableHorarioMouseClicked

    private void hSalidaEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hSalidaEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hSalidaEmpActionPerformed

    private void departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departamentoActionPerformed

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
            java.util.logging.Logger.getLogger(adminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane a;
    private javax.swing.JTextField apmEmp;
    private javax.swing.JTextField appEmp;
    private javax.swing.JButton btnAddAddress;
    private javax.swing.JButton btnAddEmail;
    private javax.swing.JButton btnAddHorario;
    private javax.swing.JButton btnAddTel;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField calleEmp;
    private javax.swing.JTextField ciudadEmp;
    private javax.swing.JTextField coloniaEmp;
    private javax.swing.JTextField cpEmp;
    private javax.swing.JTextField departamento;
    private javax.swing.JTextField descDomEmp;
    private javax.swing.JTextField descEmailEmp;
    private javax.swing.JTextField descTelEmp;
    private javax.swing.JComboBox<String> diaEmp;
    private javax.swing.JTextField emailEmp;
    private javax.swing.JComboBox<String> estadoEmp;
    private javax.swing.JRadioButton femenino;
    private javax.swing.JComboBox<String> hEntradaEmp;
    private javax.swing.JComboBox<String> hSalidaEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JTextField nombresEmp;
    private javax.swing.JTextField numExtEmp;
    private javax.swing.JTextField numIntEmp;
    private javax.swing.JButton reset;
    private javax.swing.JTable tableDomicilios;
    private javax.swing.JTable tableHorario;
    private javax.swing.JTable tableShowEmail;
    private javax.swing.JTable tableShowPersonalData;
    private javax.swing.JTable tableShowTel;
    private javax.swing.JTextField telEmp;
    // End of variables declaration//GEN-END:variables
}
