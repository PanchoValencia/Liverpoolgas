/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionsqlserver;

/**
 *
 * @author JoséFrancisco
 */
public class NewUser {
    String user ;
    String password ; 
    
    public NewUser(){}
    
    public NewUser(String user, String password){
        this.user           = user ;
        this.password       = password ;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean addUser(String user, String password, String confirmPassword){
         if(password.equals(confirmPassword)){
             return true;
         }
         else{
             
             return false;
         }
    }
}
