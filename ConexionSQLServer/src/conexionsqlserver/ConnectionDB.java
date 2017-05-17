/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionsqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author JoséFrancisco
 */
public class ConnectionDB {
    
    public static Connection GetConnection(){
        Connection conexion = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             String url = "jdbc:sqlserver://FRANCISCOVALENC:1433;databaseName=Liverpoolgas";             
             conexion= DriverManager.getConnection(url, "sa", "sa");         
        }         
        catch(ClassNotFoundException ex)         
        {             
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);             
            conexion=null;         
        }         
        catch(SQLException ex)         
        {             
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);             
            conexion=null;         
        }         
        catch(Exception ex)         
        {             
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);             
            conexion=null;         
        }         
        finally         
        {             
            return conexion;         
        }
    }
    
    public static ResultSet Query(String consulta){
        Connection con = GetConnection();
        Statement declara;
        
        try{
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
