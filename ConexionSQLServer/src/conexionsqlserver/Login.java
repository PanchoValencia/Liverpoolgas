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
public class Login {
    static ResultSet res;
    public String user     = "admin";//Nombre del Super-Usuario
    public String password = "admin";//Contraseña del Super-Usuario
    
    public boolean loginRoot(String user, String password){//Método para verificar el login de usuario, recibe dos parámetros, el primero el nombre de usuario de tipo String, y el segundo la contraseña de tipo String
        if(user.equals(this.user) && password.equals(this.password)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean loginUsers(String user, String password) throws SQLException{
        
        res = conexionsqlserver.ConnectionDB.Query("select * from usuario where username='"+user+"' and pasword='"+password+"'");
        while(res.next()){
            if(res.getString("username").equals(user) && res.getString("pasword").equals(password)){
            return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    
}
