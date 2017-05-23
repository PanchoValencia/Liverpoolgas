/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionsqlserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JoséFrancisco
 */
public class Validations {
    
    static ResultSet res;
    
    public static final String expReg = "[-\\w\\.]+@[\\.\\w]+\\.\\w+";
    
    public static boolean validarNombres(String palabra){
        String cadena[] = palabra.split("");
        for(int i = 0 ; i < cadena.length ; i ++){
            if( cadena[i].equals("1") || cadena[i].equals("2") || cadena[i].equals("3") ||
                cadena[i].equals("4") || cadena[i].equals("5") || cadena[i].equals("6") ||
                cadena[i].equals("7") || cadena[i].equals("8") || cadena[i].equals("9") ||
                cadena[i].equals(",") || cadena[i].equals("-") || cadena[i].equals("_") ||
                cadena[i].equals("<") || cadena[i].equals(">") || cadena[i].equals("=") ||
                cadena[i].equals("+") || cadena[i].equals("*") || cadena[i].equals("º") ||
                cadena[i].equals("#") || cadena[i].equals("'") || cadena[i].equals("!") ||
                cadena[i].equals("$") || cadena[i].equals("&") || cadena[i].equals(")") ||
                cadena[i].equals("%") || cadena[i].equals("/") || cadena[i].equals("(") ||
                cadena[i].equals("?") || cadena[i].equals("¿") || cadena[i].equals("`") ||
                cadena[i].equals("@") || cadena[i].equals("{") || cadena[i].equals("}") ||
                cadena[i].equals("[") || cadena[i].equals("]") || cadena[i].equals(";") ||
                cadena[i].equals(":") || cadena[i].equals("ª") || cadena[i].equals("|") ||
                cadena[i].equals("~") || cadena[i].equals("€") || cadena[i].equals("¬") ||
                cadena[i].equals("0")
            ){
                return true;
            }
        }
        return false;
    }
    
    public static boolean validarNumeros(String num){
        String cad[] = num.split("");
        for(int i = 0 ; i < cad.length ; i ++){
            if( !(cad[i].equals("1") || cad[i].equals("2") || cad[i].equals("3") ||
                  cad[i].equals("4") || cad[i].equals("5") || cad[i].equals("6") ||
                  cad[i].equals("7") || cad[i].equals("8") || cad[i].equals("9") ||
                  cad[i].equals("0")) ){
                  return true;
            }
        }
        return false;
    }
    
     public static boolean validarNumInt(String num){
        if(num.length() == 0){
            return false;
        } 
        else{
            String cad[] = num.split("");
            for(int i = 0 ; i < cad.length ; i ++){
                if( !(cad[i].equals("1") || cad[i].equals("2") || cad[i].equals("3") ||
                  cad[i].equals("4") || cad[i].equals("5") || cad[i].equals("6") ||
                  cad[i].equals("7") || cad[i].equals("8") || cad[i].equals("9") ||
                  cad[i].equals("0") || cad[i].equals("a") || cad[i].equals("b") ||
                  cad[i].equals("c") || cad[i].equals("d") || cad[i].equals("e") ||
                  cad[i].equals("f") || cad[i].equals("g") || cad[i].equals("h") ||
                  cad[i].equals("i") || cad[i].equals("j") || cad[i].equals("k") ||
                  cad[i].equals("l") || cad[i].equals("m") || cad[i].equals("n") ||
                  cad[i].equals("ñ") || cad[i].equals("o") || cad[i].equals("p") ||
                  cad[i].equals("q") || cad[i].equals("r") || cad[i].equals("s") ||
                  cad[i].equals("t") || cad[i].equals("u") || cad[i].equals("v") ||
                  cad[i].equals("w") || cad[i].equals("x") || cad[i].equals("y") ||
                  cad[i].equals("z") || cad[i].equals("A") || cad[i].equals("B") ||
                  cad[i].equals("C") || cad[i].equals("D") || cad[i].equals("E") ||
                  cad[i].equals("F") || cad[i].equals("G") || cad[i].equals("H") ||
                  cad[i].equals("I") || cad[i].equals("J") || cad[i].equals("K") ||
                  cad[i].equals("L") || cad[i].equals("M") || cad[i].equals("N") ||
                  cad[i].equals("Ñ") || cad[i].equals("O") || cad[i].equals("P") ||
                  cad[i].equals("Q") || cad[i].equals("R") || cad[i].equals("S") ||
                  cad[i].equals("T") || cad[i].equals("U") || cad[i].equals("V") ||
                  cad[i].equals("W") || cad[i].equals("X") || cad[i].equals("Y") || cad[i].equals("Z")) ){
                    return true;
                }
            }
            return false;
        }
    }
    
    public static boolean lenght(String palabra , int longitud){
        if(palabra.length() > longitud)
            return true;
        
        else
            return false;
        
    }
    
    public static boolean ValidarCp(String palabra){
        if(palabra.length() != 5)
            return true;
        
        else
            return false;
        
    }
     
    public static boolean validarTel(String tel){
        if(tel.length() == 0){
            return false;
        }
        else{
            String num[] = tel.split("");
            for(int i = 0 ; i < num.length ; i ++){
                if( !(num[i].equals("0") || num[i].equals("1") || num[i].equals("2") || 
                    num[i].equals("3") || num[i].equals("4") || num[i].equals("5") ||
                    num[i].equals("6") || num[i].equals("7") || num[i].equals("8") ||
                    num[i].equals("9") || num[i].equals("(") || num[i].equals(")") ||
                    num[i].equals("-") || num[i].equals(" ")) ){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean validarEmail(String email){
        Pattern pattern = Pattern.compile(expReg);
 
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static boolean validarFloat(String num){
        String cad[] = num.split("");
        
        for( int i = 0 ; i < cad.length ; i ++ ){
            if( !(cad[i].equals("0") || cad[i].equals("1") || cad[i].equals("2") || 
                  cad[i].equals("3") || cad[i].equals("4") || cad[i].equals("5") ||
                  cad[i].equals("6") || cad[i].equals("7") || cad[i].equals("8") ||
                  cad[i].equals("9") || cad[i].equals(".")) 
            ){
                return true;
            }
        }
        return false;
    }
    
    public static boolean existMarcaActive( String marca ){
        try{
            res = conexionsqlserver.ConnectionDB.Query("select nombre_marca from marca where activo="+1);
            while(res.next()){
                if( marca.equalsIgnoreCase(res.getString("nombre_marca")) ){
                    return true;
                }
            }
        }
        catch(SQLException e){}
        return false;
    }
    
    public static boolean existMarcaInactive( String marca ){
        try{
            res = conexionsqlserver.ConnectionDB.Query("select nombre_marca from marca where activo="+0);
            while(res.next()){
                if( marca.equalsIgnoreCase(res.getString("nombre_marca")) ){
                    return true;
                }
            }
        }
        catch(SQLException e){}
        return false;
    }
    
    public static boolean validarQuotes( String palabra )
    {
        String cad[] = palabra.split("");
        
        for( int i = 0 ; i < cad.length ; i ++ )
        {
            if( cad[i].equals("'") || cad[i].equals("`") || cad[i].equals("´") )
            {
                return true;
            }
        }
        
        return false;
    }
}

