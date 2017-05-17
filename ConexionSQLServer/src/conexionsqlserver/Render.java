package conexionsqlserver;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author JoséFrancisco
 */
public class Render extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if( value instanceof JButton ){
            JButton btn = (JButton) value;
            return btn;
        }
        
        if( value instanceof JTextField ){
            JTextField txt = (JTextField) value;
            return txt;
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }   
    
}
