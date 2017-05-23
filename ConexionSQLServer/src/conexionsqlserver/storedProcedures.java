/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionsqlserver;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JoséFrancisco
 */
public class storedProcedures {
    static ResultSet res;
    
    public static void agregarUsuario(String name , String password)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addUser(?,?)}");
        
        add.setString(1, name);
        add.setString(2, password);
        add.execute();
    }
    
    public static void eliminarUsuario(int cod_user)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteUser(?)}");
        
        delete.setInt(1, cod_user);
        delete.execute();
    }
    
    public static void searchUsuario(String name , String password)throws SQLException{
        CallableStatement search = ConnectionDB.GetConnection().prepareCall("{call searchUser(?,?)}");
        
        search.setString(1, name);
        search.setString(2, password);
        search.execute();
    }
    
    public static void addCliente(String name , String app , String apm , String sexo )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addClient(?,?,?,?)}");
        
        add.setString(1, name);
        add.setString(2, app);
        add.setString(3, apm);
        add.setString(4, sexo);
        add.execute();
    }
    
    public static void addEmpleado(String name , String app , String apm , String sexo )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addEmpleado(?,?,?,?)}");
        
        add.setString(1, name);
        add.setString(2, app);
        add.setString(3, apm);
        add.setString(4, sexo);
        add.execute();
    }
    
    public static void addDomicilio(String calle , String nInt , int nExt , String desc)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDom(?,?,?,?)}");
        
        add.setString(1, calle);
        add.setString(2, nInt);
        add.setInt(3, nExt);
        add.setString(4, desc);
        add.execute();
    }
    
    public static void addDomicilio_Cte(int dom , int cte)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDomCte(?,?)}");
        
        add.setInt(1, dom);
        add.setInt(2, cte);
        add.execute();
    }
    
    public static void addDomicilio_Empleado(int dom , int emp)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDomEmpleado(?,?)}");
        
        add.setInt(1, dom);
        add.setInt(2, emp);
        add.execute();
    }
    
    public static void addColonia(String colonia , int cp)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addColonia(?,?)}");
        
        add.setString(1, colonia);
        add.setInt(2, cp);
        add.execute();
    }
    
    public static void addColonia_Domicilio(int dom , int col)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDomColonia(?,?)}");
        
        add.setInt(1, dom);
        add.setInt(2, col);
        add.execute();
    }
    
    public static void addEstado(String estado )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addEstado(?)}");
        
        add.setString(1, estado);
        add.execute();
    }
    
    public static void addEstado_Domicilio(int dom , int est)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDomEstado(?,?)}");
        
        add.setInt(1, dom);
        add.setInt(2, est);
        add.execute();
    }
    
    public static void addCiudad(String ciudad )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addCiudad(?)}");
        
        add.setString(1, ciudad);
        add.execute();
    }
    
    public static void addCiudad_Domicilio(int dom , int ciudad)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDomCiudad(?,?)}");
        
        add.setInt(1, dom);
        add.setInt(2, ciudad);
        add.execute();
    }
    
    public static void addTelefono(String tel , String desc)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addTel(?,?)}");
        
        add.setString(1, tel);
        add.setString(2, desc);
        add.execute();
    }
    
    public static void addTel_Cte(int tel , int cte)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addTelCte(?,?)}");
        
        add.setInt(1, tel);
        add.setInt(2, cte);
        add.execute();
    }
    
    public static void addTel_Empleado(int tel , int emp)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addTelEmp(?,?)}");
        
        add.setInt(1, tel);
        add.setInt(2, emp);
        add.execute();
    }
    
    public static void addEmail(String email , String desc)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addEmail(?,?)}");
        
        add.setString(1, email);
        add.setString(2, desc);
        add.execute();
    }
    
    public static void addEmail_Cte(int codCorreo , int codCte)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addEmailCte(?,?)}");
        
        add.setInt(1, codCorreo);
        add.setInt(2, codCte);
        add.execute();
    }
    
    public static void addEmail_Empleado(int codCorreo , int codEmp)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addEmailEmp(?,?)}");
        
        add.setInt(1, codCorreo);
        add.setInt(2, codEmp);
        add.execute();
    }
    
    public static int getID(String nomCod , String tabla){
        int id = 0;
        String SQL = "SELECT MAX("+nomCod+") AS "+nomCod+" FROM "+tabla;
        try{
            res = conexionsqlserver.ConnectionDB.Query(SQL);
            if(res.next()){
                id = res.getInt(1);
            }
        }catch(SQLException e){}
        return id;
    }
    
    public static void deleteCte(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteCte(?)}");
        
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteEmpleado(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteEmpleado(?)}");
        
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void updateCte(String nom , String app , String apm , String sexo , int cod)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateCte(?,?,?,?,?)}");
        
        update.setString(1, nom);
        update.setString(2, app);
        update.setString(3, apm);
        update.setString(4, sexo);
        update.setInt(5, cod);
        update.execute();
    }
    
    public static void updateEmpleado(String nom , String app , String apm , String sexo , int cod)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateEmpleado(?,?,?,?,?)}");
        
        update.setString(1, nom);
        update.setString(2, app);
        update.setString(3, apm);
        update.setString(4, sexo);
        update.setInt(5, cod);
        update.execute();
    }
    
    public static void deleteTel_Cte(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteTel_Cte(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteTel_Empleado(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteTel_Empleado(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteTel(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteTel(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void updateTel(String num , String desc , int cod)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateTel(?,?,?)}");
        
        update.setString(1, num);
        update.setString(2, desc);
        update.setInt(3, cod);
        update.execute();
    }
    
    public static void deleteEmail_Cte(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteEmail_Cte(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteEmail_Empleado(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteEmail_Empleado(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteDia_Horario(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteDiaHorario(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteDia(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteDia(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void updateDia(String dia , int cod)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateDia(?,?)}");
        
        update.setString(1, dia);
        update.setInt(2, cod);
        update.execute();
    }
    
    public static void deleteHorario(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteHorario(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void updateHorario(String entrada , String salida , int cod)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateHorario(?,?,?)}");
        
        update.setString(1, entrada);
        update.setString(2, salida);
        update.setInt(3, cod);
        update.execute();
    }
    
    public static void deleteHorario_Empleado(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteHorarioEmpleado(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteEmail(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteEmail(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void updateEmail(String nom , String desc , int cod)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateEmail(?,?,?)}");
        
        update.setString(1, nom);
        update.setString(2, desc);
        update.setInt(3, cod);
        update.execute();
    }
    
    public static void updateDom(int cod , String calle , String colonia , String numInt , int numExt , int cp , String desc , String estado , String ciudad)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateDom(?,?,?,?,?,?,?,?,?)}");
        
        update.setInt(1, cod);
        update.setString(2, calle);
        update.setString(3, colonia);
        update.setString(4, numInt);
        update.setInt(5, numExt);
        update.setInt(6, cp);
        update.setString(7, desc);
        update.setString(8, estado);
        update.setString(9, ciudad);
        update.execute();
    }
    
    public static void deleteDom( int cod )throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteDom(?)}");
        
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteDomEmpleado( int cod )throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteDomEmpleado(?)}");
        
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void addDepa( String depa )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDepa(?)}");
        
        add.setString(1, depa);
        add.execute();
    }
    
    public static void addProducto( String nombre , float precio , int cantidad , String desc )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addProd(?,?,?,?)}");
        
        add.setString(1, nombre);
        add.setFloat(2, precio);
        add.setInt(3, cantidad);
        add.setString(4, desc);
        add.execute();
    }
    
    public static void addMarca( String nombre  )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addMarca(?)}");
        
        add.setString(1, nombre);
        add.execute();
    }
    
    public static void addModelo( String nombre  )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addModelo(?)}");
        
        add.setString(1, nombre);
        add.execute();
    }
    
    public static void addColor( String nombre  )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addColor(?)}");
        
        add.setString(1, nombre);
        add.execute();
    }
    
    public static void addProd_Depa( int codProd , int codDepa )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addProducto_departamento(?,?)}");
        
        add.setInt(1, codProd);
        add.setInt(2, codDepa);
        add.execute();
    }
    
    public static void addEmpleado_Depa( int codEmp , int codDepa )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addEmpleado_departamento(?,?)}");
        
        add.setInt(1, codEmp);
        add.setInt(2, codDepa);
        add.execute();
    }
    
    public static void addMarca_Producto( int codMarca ,  int codProd )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addMarca_producto(?,?)}");
        
        add.setInt(1, codMarca);
        add.setInt(2, codProd);
        add.execute();
    }
    
    public static void addModelo_Producto( int codModelo ,  int codProd )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addModelo_producto(?,?)}");
        
        add.setInt(1, codModelo);
        add.setInt(2, codProd);
        add.execute();
    }
    public static void addColor_Producto( int codColor ,  int codProd )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addColor_producto(?,?)}");
        
        add.setInt(1, codColor);
        add.setInt(2, codProd);
        add.execute();
    }
    
    public static void updateProd( int cod , String nombre , float precio , int cantidad , String desc , String modelo , String color)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateProd(?,?,?,?,?,?,?)}");
        
        update.setInt(1, cod);
        update.setString(2, nombre);
        update.setFloat(3, precio);
        update.setInt(4, cantidad);
        update.setString(5, desc);
        update.setString(6, modelo);
        update.setString(7, color);
        update.execute();
    }
    
    public static void deleteProducto( int cod )throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteProd(?)}");
        
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void deleteMarca( int cod )throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteMarca(?)}");
        
        delete.setInt(1, cod);
        delete.execute();
    }
    
    public static void enableMarca( int cod )throws SQLException{
        CallableStatement enable = ConnectionDB.GetConnection().prepareCall("{call enableMarca(?)}");
        
        enable.setInt(1, cod);
        enable.execute();
    }
    
    public static void enableProd( int cod )throws SQLException{
        CallableStatement enable = ConnectionDB.GetConnection().prepareCall("{call enableProd(?)}");
        
        enable.setInt(1, cod);
        enable.execute();
    }
    
    public static void enableCte( int cod )throws SQLException{
        CallableStatement enable = ConnectionDB.GetConnection().prepareCall("{call enableCliente(?)}");
        
        enable.setInt(1, cod);
        enable.execute();
    }
    
    public static void enableEmpleado( int cod )throws SQLException{
        CallableStatement enable = ConnectionDB.GetConnection().prepareCall("{call enableEmpleado(?)}");
        
        enable.setInt(1, cod);
        enable.execute();
    }
    
    public static void updateMarca( int cod , String marca )throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateMarca(?,?)}");
        
        update.setInt(1, cod);
        update.setString(2, marca);
        update.execute();
    }
    
    public static int getIDMarca(String marca){
        int cod = 0;
        try{
            res = conexionsqlserver.ConnectionDB.Query("select cod_marca from marca where nombre_marca='"+marca+"'");
            if(res.next()){
                cod = res.getInt(1);
            }
        }
        catch(SQLException e){}
        return cod;
    }
    
    public static void updateMarca_Prod( int codmarca , int codprod )throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateMarcaProd(?,?)}");
        
        update.setInt(1, codmarca);
        update.setInt(2, codprod);
        update.execute();
    }
    
    public static void addDia( String dia )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDia(?)}");
        
        add.setString(1, dia);
        add.execute();
    }
    
    public static void addHorario( String entrada , String salida )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addHorario(?,?)}");
        
        add.setString(1, entrada);
        add.setString(2, salida);
        add.execute();
    }
    
    public static void addDia_horario( int codDia , int codHorario )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDiaHorario(?,?)}");
        
        add.setInt(1, codDia);
        add.setInt(2, codHorario);
        add.execute();
    }
   
    public static void addHorario_empleado( int codHorario , int codEmp )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addHorarioEmpleado(?,?)}");
        
        add.setInt(1, codHorario);
        add.setInt(2, codEmp);
        add.execute();
    }
    
    //prov =>
    public static void addProveedor(String name , String app , String apm)throws SQLException{
     CallableStatement add= ConnectionDB.GetConnection().prepareCall("{call addProv(?,?,?)}");
     
        add.setString(1, name);
        add.setString(2, app);
        add.setString(3, apm);
        add.execute();
    }

     public static void addDomicilio_Prov(int dom , int prov)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call addDomProv(?,?)}");
        
        add.setInt(1, dom);
        add.setInt(2, prov);
        add.execute();
    }
    public static void addTel_Prov(int tel , int prov)throws SQLException{
        CallableStatement add= ConnectionDB.GetConnection().prepareCall("{call addTelProv(?,?)}");
        add.setInt(1, tel);
        add.setInt(2, prov);
        add.execute();
    }
    public static void addEmail_Prov(int codCorreo , int codProv)throws SQLException{
        CallableStatement add= ConnectionDB.GetConnection().prepareCall("{call addEmailprov(?,?)}");
        add.setInt(1, codCorreo);
        add.setInt(2, codProv);
        add.execute();
    }
    public static void deleteProv(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteProv(?)}");
        
        delete.setInt(1, cod);
        delete.execute();
    }
    public static void updateProv(String nom , String app , String apm , int cod)throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call updateProv(?,?,?,?)}");
        
        update.setString(1, nom);
        update.setString(2, app);
        update.setString(3, apm);
        update.setInt(4, cod);
        update.execute();
    }
    
    public static void deleteTel_Prov(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteTel_Prov(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    public static void deleteEmail_Prov(int cod)throws SQLException{
        CallableStatement delete = ConnectionDB.GetConnection().prepareCall("{call deleteEmail_Prov(?)}");
       
        delete.setInt(1, cod);
        delete.execute();
    }
    public static void enableProv( int cod )throws SQLException{
        CallableStatement enable = ConnectionDB.GetConnection().prepareCall("{call enableProveedor(?)}");
        
        enable.setInt(1, cod);
        enable.execute();
    }
    
    public static void nuevaPromo( int codProd , int codDepa ,String fechai , String fechaf , String desc )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call nuevaPromo(?,?,?,?,?)}");
        
        add.setInt(1, codProd);
        add.setInt(2, codDepa);
        add.setString(3, fechai);
        add.setString(4, fechaf);
        add.setString(5, desc);
        add.execute();
    }
    
    public static void disabledPromo( int cod)throws SQLException{
        CallableStatement dis = ConnectionDB.GetConnection().prepareCall("{call disabledPromo(?)}");
        
        dis.setInt(1, cod);
        dis.execute();
    }
    
    public static void enabledPromo( int codPromo , int codProd , int codDepa ,String fechai , String fechaf , String desc )throws SQLException{
        CallableStatement upd = ConnectionDB.GetConnection().prepareCall("{call enabledPromo(?,?,?,?,?,?)}");
        
        upd.setInt(1, codPromo);
        upd.setInt(2, codProd);
        upd.setInt(3, codDepa);
        upd.setString(4, fechai);
        upd.setString(5, fechaf);
        upd.setString(6, desc);
        upd.execute();
    }
    
    public static void updatePromo( int codPromo , int codProd , int codDepa ,String fechai , String fechaf , String desc )throws SQLException{
        CallableStatement upd = ConnectionDB.GetConnection().prepareCall("{call updatePromo(?,?,?,?,?,?)}");
        
        upd.setInt(1, codPromo);
        upd.setInt(2, codProd);
        upd.setInt(3, codDepa);
        upd.setString(4, fechai);
        upd.setString(5, fechaf);
        upd.setString(6, desc);
        upd.execute();
    }
    
    public static void addPromo_venta( int cod_prod , String folio )throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call addPromo_venta(?,?)}");
        
        update.setInt(1, cod_prod);
        update.setString(2, folio);
        update.execute();
    }
    
    public static void nuevaVenta( int codEmp , int codCte , int codProd , String folio , String fecha , float precio , int cantidad )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call nuevaVenta(?,?,?,?,?,?,?)}");
        
        add.setInt(1, codEmp);
        add.setInt(2, codCte);
        add.setInt(3, codProd);
        add.setString(4, folio);
        add.setString(5, fecha);
        add.setFloat(6, precio);
        add.setInt(7, cantidad);
        add.execute();
    }

    public static void restPxV( int cod , int cant )throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call restPxV(?,?)}");
        
        update.setInt(1, cod);
        update.setInt(2, cant);
        update.execute();
    }
    
    //ESTADO DE CUENTA
    public static void newEC( int codCte , String folio , String fechaLim , float total)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call newEC(?,?,?,?)}");
        
        add.setInt(1, codCte);
        add.setString(2, folio);
        add.setString(3, fechaLim);
        add.setFloat(4, total);
        add.execute();
    }
    
    public static void abonoEC( int codCte , String folio , String fechaLim , float abono , float total , String fechaAbono)throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call abonoEC(?,?,?,?,?,?)}");
        
        add.setInt(1, codCte);
        add.setString(2, folio);
        add.setString(3, fechaLim);
        add.setFloat(4, abono);
        add.setFloat(5, total);
        add.setString(6, fechaAbono);
        add.execute();
    }
    
    
    //:::::COMPRAS::::
    public static void nuevaCompra( int codProv, int codProd , String folio , String fecha , float precio , int cantidad )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call nuevaCompra(?,?,?,?,?,?)}");
        
        add.setInt(1, codProv);
        add.setInt(2, codProd);
        add.setString(3, folio);
        add.setString(4, fecha);
        add.setFloat(5, precio);
        add.setInt(6, cantidad);
        add.execute();
    }

    public static void sumPxC( int cod , int cant )throws SQLException{
        CallableStatement update = ConnectionDB.GetConnection().prepareCall("{call sumPxC(?,?)}");
        
        update.setInt(1, cod);
        update.setInt(2, cant);
        update.execute();
    }
    
    //:::::DEVOLUCIONES:::::
    public static void newDevolucion( int codProd, int codCte , String fecha , String folio , float monto )throws SQLException{
        CallableStatement add = ConnectionDB.GetConnection().prepareCall("{call newDevolucion(?,?,?,?,?)}");
        
        add.setInt(1, codProd);
        add.setInt(2, codCte);
        add.setString(3, fecha);
        add.setString(4, folio);
        add.setFloat(5, monto);
        add.execute();
    }

}
