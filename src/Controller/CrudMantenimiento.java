package Controller;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Controller.ResultadoMantenimiento;
import Generics.Componente;
import javax.swing.table.DefaultTableModel;

public class CrudMantenimiento implements ICrud<ResultadoMantenimiento>{

    @Override
    public void guardar(String idEquipo, String json, java.sql.Date fecha) {
        // 1. Verificar si existe el registro para ese ID y esa FECHA específica
        String checkSql = "SELECT COUNT(*) FROM MantenimientoEquipos WHERE idEquipo = ? AND fecha = ?";

        try (Connection con = new Conexion().getConexion(); PreparedStatement check = con.prepareStatement(checkSql)) {

            check.setString(1, idEquipo);
            check.setDate(2, fecha);
            ResultSet rs = check.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // SI EXISTE EN ESA FECHA -> ACTUALIZAR
                String updateSql = "UPDATE MantenimientoEquipos SET detalles = ? WHERE idEquipo = ? AND fecha = ?";
                try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                    psUpdate.setString(1, json);
                    psUpdate.setString(2, idEquipo);
                    psUpdate.setDate(3, fecha);
                    psUpdate.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Mantenimiento actualizado para la fecha: " + fecha);
                }
            } else {
                // NO EXISTE EN ESA FECHA -> INSERTAR NUEVO
                String insertSql = "INSERT INTO MantenimientoEquipos(idEquipo, detalles, fecha) VALUES (?, ?, ?)";
                try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                    psInsert.setString(1, idEquipo);
                    psInsert.setString(2, json);
                    psInsert.setDate(3, fecha);
                    psInsert.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Nuevo registro creado para la fecha: " + fecha);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al procesar el mantenimiento con fecha");
            e.printStackTrace();
        }
    }

    @Override
    public ResultadoMantenimiento buscar(String idEquipo, java.sql.Date fecha) {
        String sql = "SELECT detalles, fecha, hora FROM MantenimientoEquipos WHERE idEquipo = ? AND fecha = ?";
        ResultadoMantenimiento resultado = null;

        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idEquipo);
            ps.setDate(2, fecha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultado = new ResultadoMantenimiento();
                resultado.json = rs.getString("detalles");
                resultado.fecha = rs.getDate("fecha");
                resultado.hora = rs.getTime("hora");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar mantenimiento");
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public void actualizar(String idEquipo, String json, java.sql.Date fecha) {
        String sql = "UPDATE MantenimientoEquipos SET detalles = ? WHERE idEquipo = ? AND fecha = ?";

        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, json);
            ps.setString(2, idEquipo);
            ps.setDate(3, fecha);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Mantenimiento actualizado para la fecha " + fecha);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el equipo o la fecha");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar");
            e.printStackTrace();
        }
    }

    @Override
    public void eiliminar(String id, java.sql.Date fecha) {
        String sql = "DELETE FROM MantenimientoEquipos WHERE idEquipo = ? AND fecha = ?";

        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setDate(2, fecha);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Mantenimiento eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "No existe el equipo");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar");
            e.printStackTrace();
        }
    }
    

//Guardar en inventario

    public void guardarInv(String id, String tipo, String cap, int cant, String est) {
        String sql = "INSERT INTO Inventario VALUES (?, ?, ?, ?, ?)";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, tipo);
            ps.setString(3, cap);
            ps.setInt(4, cant);
            ps.setString(5, est);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Componente Guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

//Actualizar inventario
    public void actualizarInv(String id, String tipo, String cap, int cant, String est) {
        String sql = "UPDATE Inventario SET tipoComponente=?, capacidad=?, cantidad=?, estado=? WHERE idComponente=?";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipo);
            ps.setString(2, cap);
            ps.setInt(3, cant);
            ps.setString(4, est);
            ps.setString(5, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inventario Actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Cargar la tabla
    public javax.swing.table.DefaultTableModel cargarTablaInv() {
        String[] col = {"ID", "Tipo", "Capacidad", "Cant", "Estado"};

        DefaultTableModel mod = new DefaultTableModel(null, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try (
            Connection con = new Conexion().getConexion();
            ResultSet rs = con.prepareStatement("SELECT * FROM Inventario").executeQuery()
        ) {

            while (rs.next()) {

                mod.addRow(new Object[]{
                    rs.getString("idComponente"),
                    rs.getString("tipoComponente"),
                    rs.getString("capacidad"),
                    rs.getInt("cantidad"),
                    rs.getString("estado")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            return mod;
        
    }


    public Componente buscarComponente(String id) {
        String sql = "SELECT * FROM Inventario WHERE idComponente = ?";
        
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);
            consultaSQL.setString(1, id);
            ResultSet rs = consultaSQL.executeQuery();
            if (rs.next()) {
                Componente c = new Componente();
                
                c.setIdComponente(rs.getString("idComponente"));
                c.setTipoComponente(rs.getString("tipoComponente"));
                c.setCapacidad(rs.getString("capacidad"));
                c.setCantidad(rs.getInt("cantidad"));
                c.setEstado(rs.getString("estado"));
                
                return c;
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + error.getMessage());
            return null;
        }
        return null;
    } 
    

}
