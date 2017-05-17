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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class classDate {
    Calendar fecha = Calendar.getInstance();
    
    
    String dia = Integer.toString(fecha.get(Calendar.DATE));
    String d = Integer.toString(fecha.get(Calendar.DATE)+1);
    String mes = Integer.toString(fecha.get(Calendar.MONTH)+1);
    
    String año = Integer.toString(fecha.get(Calendar.YEAR));
    
    String segundo = Integer.toString(fecha.get(Calendar.SECOND));
    String minuto  = Integer.toString(fecha.get(Calendar.MINUTE));
    String hora    = Integer.toString(fecha.get(Calendar.HOUR));
    
    String anio[] = año.split("");
    String year = anio[anio.length-2] += anio[anio.length-1];
    
    String fol = year+mes+dia+hora+minuto+segundo;
    
    
    String actual = null;
    
    public void setearFecha(){
        if(dia.length() == 1 && mes.length() == 1){
            actual = año+"-"+"0"+mes+"-"+"0"+dia;
        }
        else if(dia.length() == 1 && mes.length() == 2){
            actual = año+"-"+mes+"-"+"0"+dia;
        }
        else if(dia.length() == 2 && mes.length() == 1){
            actual = año+"-"+"0"+mes+"-"+dia;
        }
        else{
            actual = año+"-"+mes+"-"+dia;
        }
        temporalVariables.setFechaActual(actual);
    }
    
    public void setearFol(){
        temporalVariables.setFol(fol);
    }
    
    public Date getFechaSistema(){
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String fi = null;
        if(dia.length() == 1 && mes.length() == 1){
            fi = año+"-"+"0"+mes+"-"+"0"+dia;
        }
        else if(dia.length() == 1 && mes.length() == 2){
            fi = año+"-"+mes+"-"+"0"+dia;
        }
        else if(dia.length() == 2 && mes.length() == 1){
            fi = año+"-"+"0"+mes+"-"+dia;
        }
        else{
            fi = año+"-"+mes+"-"+dia;
        }
        
        Date fe = null;
        try {
            fe = fecha.parse(fi);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "no se parseo la fecha");
        }
        
        fecha.format(fe);
        
        return fe;
    }
    
    public Date getMayorFechaSistema(){
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String fi = null;
        if(d.length() == 1 && mes.length() == 1){
            fi = año+"-"+"0"+mes+"-"+"0"+d;
        }
        else if(d.length() == 1 && mes.length() == 2){
            fi = año+"-"+mes+"-"+"0"+d;
        }
        else if(d.length() == 2 && mes.length() == 1){
            fi = año+"-"+"0"+mes+"-"+d;
        }
        else{
            fi = año+"-"+mes+"-"+d;
        }
        
        Date fe = null;
        try {
            fe = fecha.parse(fi);
        } catch (ParseException ex) {
            Logger.getLogger(classDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fecha.format(fe);
        
        return fe;
    }
}
