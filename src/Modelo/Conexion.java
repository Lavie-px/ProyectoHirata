package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    //Reemplazar por las credenciales de su base de datos, los detalles de las tablas se encuentran en la carpeta de docuemtnacion
    public static final String URL = "";
    public static final String USER = "";
    public static final String CLAVE = "";

    private static Connection con = null;

    public Connection getConexion() {

        try {
            
            // Cargar driver UNA sola vez
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            /* esta parte es solo un debugger para saber de donde se esta llamando el metodo
            no es algo que el usuario veria al final
            Exception x = new Exception();
            StackTraceElement[] trace = x.getStackTrace();
            System.out.println("getConexion() llamado desde: " + trace[1]);
            */
            
            // Si la conexión NO existe o está cerrada la crea si no la reutiliza
            if (con == null || con.isClosed() || !con.isValid(2)) {
                con = DriverManager.getConnection(URL, USER, CLAVE);
                System.out.println("Conexion creada");
            } else {
                System.out.println("Conexion reutilizada");
            }

        } catch (Exception e) {
            System.err.println("Error conexion: " + e.getMessage());
        }

        return con;
    }

    public void throwcon() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexion cerrada");
            }
        } catch (Exception e) {
            System.err.println("Error cerrando conexión: " + e.getMessage());
        }
    }
}
