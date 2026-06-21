
package Controller;
import Modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Generics.Usuario;

public class CrudUsuarios implements ICrud<Usuario> {
    
    public ResultSet listatTodos(){
        String sql = "SELECT * FROM LoginEmpleados";
        
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();
            
            Statement SqlSentence = ActiveCon.createStatement();
            return SqlSentence.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error al listar");
            return null;
        }
    }
    
    
    public ResultSet listarCamiones() {
        String sql = "SELECT idCamion FROM Camiones";

        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();
            
            Statement SqlSentence = ActiveCon.createStatement();
            return SqlSentence.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error al listar");
            return null;
        }
    }
    
    @Override
    public void grabar(Usuario u){
        
        String sql = "INSERT INTO LoginEmpleados(Rut,Contrasena, Nombre, Apellido, idCamion, cargo)"+"VALUES (?,?,?,?,?,?)";
        
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();
            
            PreparedStatement SqlSentence = ActiveCon.prepareStatement(sql);
            SqlSentence.setString(1, u.getRut());
            SqlSentence.setString(2, u.getClave());
            SqlSentence.setString(3, u.getNombre());
            SqlSentence.setString(4, u.getApellido());
             
            if (u.getIdCamion().equals("Sin camion asignado")) {
                SqlSentence.setNull(5, java.sql.Types.VARCHAR);
            } else {
                SqlSentence.setString(5, u.getIdCamion());
            }

            SqlSentence.setString(6, u.getTipoUsuario());

            SqlSentence.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados con exito","Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"error al grabar","ERROR", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    
    @Override
    public Usuario buscar(String rutBuscado) {
        String sql = "SELECT * FROM LoginEmpleados WHERE Rut = ?";
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);
            consultaSQL.setString(1, rutBuscado);
            ResultSet rs = consultaSQL.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();

                u.setRut(rs.getString("Rut"));
                u.setNombre(rs.getString("Nombre"));
                u.setApellido(rs.getString("Apellido"));
                u.setClave(rs.getString("Contrasena"));
                u.setTipoUsuario(rs.getString("Cargo"));
                u.setIdCamion(rs.getString("idCamion"));

                return u;
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + error.getMessage());
            return null;
        }
        return null;
    }
    
    @Override
    public void actualizar(Usuario u){
        String sql = "UPDATE LoginEmpleados SET Nombre=?, Apellido=?, Contrasena=?, Cargo=?, idCamion=? WHERE Rut=?";

        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);

            consultaSQL.setString(1, u.getNombre());
            consultaSQL.setString(2, u.getApellido());
            consultaSQL.setString(3, u.getClave());
            consultaSQL.setString(4, u.getTipoUsuario());

            // manejar NULL del combo
            if (u.getIdCamion().equals("Sin camion asignado")) {
                consultaSQL.setNull(5, java.sql.Types.VARCHAR);
            } else {
                consultaSQL.setString(5, u.getIdCamion());
            }

            consultaSQL.setString(6, u.getRut());

            consultaSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void eiliminar(String id) {
        String sql = "DELETE FROM LoginEmpleados WHERE Rut=?";
        
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();
            
            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);
            consultaSQL.setString(1,id);
            
            int filas = consultaSQL.executeUpdate();
            
            if(filas > 0){
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);  
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el rut", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
           
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al eliminar "+error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }


}


