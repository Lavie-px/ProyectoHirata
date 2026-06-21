
package Controller;

public class Sesion {

    private static String tipoUsuario;
    private static String nombreReal;
    private static String apellidoReal;

    public static String getTipoUsuario() { 
        return tipoUsuario;
    }

    public static void setTipoUsuario(String tipoUsuario) {
        Sesion.tipoUsuario = tipoUsuario;
    }

    public static String getNombreReal() {
        return nombreReal;
    }

    public static void setNombreReal(String nombreReal) {
        Sesion.nombreReal = nombreReal;
    }

    public static String getApellidoReal() {
        return apellidoReal;
    }

    public static void setApellidoReal(String apellidoReal) {
        Sesion.apellidoReal = apellidoReal;
    }

    public static String NombreCompleto(String Nombre, String Apellido){
        return Nombre+""+Apellido;
    }
}

