
package Controller;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    //esto verifica que el usuario exista
    public ResultSet ValidarUsuario(String usuario, String clave) throws SQLException{
        Conexion con = new Conexion();
        Connection conexion = con.getConexion();
        
        if(conexion == null){
            return null;
        }
        
        String Sql = "SELECT * FROM LoginEmpleados WHERE Rut = ? AND Contrasena = ?";
        
        PreparedStatement consultaLogin = conexion.prepareStatement(Sql);
        consultaLogin.setString(1, usuario);
        consultaLogin.setString(2, clave);
        return consultaLogin.executeQuery();
            
    }
}
