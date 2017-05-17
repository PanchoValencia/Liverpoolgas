
package conexionsqlserver;

/**
 *
 * @author JoséFrancisco
 */
public class temporalVariables {
    
    private static String usuario      = "";
    private static String password     = "";
    protected static String departamento = "";
    protected static String fechaActual = "";
    protected static String fol = "";
    
    public static String getFol() {
        return fol;
    }

    public static void setFol(String fol) {
        temporalVariables.fol = fol;
    }
    
    public static String getFechaActual() {
        return fechaActual;
    }

    public static void setFechaActual(String fechaActual) {
        temporalVariables.fechaActual = fechaActual;
    }

    public static String getDepartamento() {
        return departamento;
    }

    public static void setDepartamento(String departamento) {
        temporalVariables.departamento = departamento;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String aUsuario) {
        usuario = aUsuario;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String aPassword) {
        password = aPassword;
    }
    
    
}
