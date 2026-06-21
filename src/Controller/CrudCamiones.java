
package Controller;
import Modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Generics.Camion;

public class CrudCamiones implements ICrud<Camion>{
    
    public ResultSet listatTodos(){
        String sql = "SELECT * FROM Camiones";
        
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
    public void grabar(Camion c){
        
        String sql = "INSERT INTO Camiones(idCamion, Marca, Modelo, Anio, kilometrajeActual, estadoMantenimiento)"+"VALUES (?,?,?,?,?,?)";

        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();
            
            PreparedStatement SqlSentence = ActiveCon.prepareStatement(sql);
            SqlSentence.setString(1, c.getIdCamion());
            SqlSentence.setString(2, c.getMarca());
            SqlSentence.setString(3, c.getModelo());
            SqlSentence.setInt(4, c.getAnio());
            SqlSentence.setInt(5, c.getKilometrajeActual());
            SqlSentence.setString(6, c.getEstadoMantenimiento());

            SqlSentence.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados con exito","Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"error al grabar","ERROR", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    
    @Override
    public Camion buscar(String id) {
        String sql = "SELECT * FROM Camiones WHERE idCamion = ?";
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);
            consultaSQL.setString(1, id); 
            ResultSet rs = consultaSQL.executeQuery();
            if (rs.next()) {
                Camion c = new Camion();

                c.setIdCamion(rs.getString("idCamion"));
                c.setMarca(rs.getString("Marca"));
                c.setModelo(rs.getString("Modelo"));
                c.setAnio(rs.getInt("Anio"));
                c.setKilometrajeActual(rs.getInt("kilometrajeActual"));
                c.setEstadoMantenimiento(rs.getString("estadoMantenimiento"));

                return c;
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + error.getMessage());
            return null;
        }
        return null;
    }
    
    @Override
    public void actualizar(Camion c) {
        String sql = "UPDATE Camiones SET marca=?, modelo=?, anio=?, kilometrajeActual=?, estadoMantenimiento=? WHERE idCamion=?";

        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);

            consultaSQL.setString(1, c.getMarca());
            consultaSQL.setString(2, c.getModelo());
            consultaSQL.setInt(3, c.getAnio());
            consultaSQL.setInt(4, c.getKilometrajeActual());
            consultaSQL.setString(5, c.getEstadoMantenimiento());
            consultaSQL.setString(6, c.getIdCamion());

            consultaSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eiliminar(String id) {
        String sql = "DELETE FROM Camiones WHERE idCamion=?";
        
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();
            
            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);
            consultaSQL.setString(1,id);
            
            int filas = consultaSQL.executeUpdate();
            
            if(filas > 0){
                JOptionPane.showMessageDialog(null, "Camion eliminado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);  
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el Camion", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
           
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al eliminar "+error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}


