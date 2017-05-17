/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionsqlserver;
import views.FormLogin;
import java.sql.Connection;
import views.Promociones;

/**
 *
 * @author JoséFrancisco
 */
public class ConnectionSQLServer {
    
    public static void main(String[] args) {
        Connection miConexion;
        miConexion = ConnectionDB.GetConnection();
        
        
        if(miConexion != null){
            //JOptionPane.showMessageDialog(null, "Conexión realizada correctamente");
            
            FormLogin login = new FormLogin();
        
            login.setLocationRelativeTo(null);//Posicionar al centro la interfaz de  login
            login.setVisible(true);//Iniciar el programa por el login
            
            Promociones promo = new Promociones();
            promo.bajaXFechaSistema();
            
        }
    }
    
}
