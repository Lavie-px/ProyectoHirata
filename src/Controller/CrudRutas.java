package Controller;

import Modelo.Conexion;
import Modelo.Conexion;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jose9
 */
public class CrudRutas {

    public ResultSet listarSucursales() {

        String sql = "SELECT Direccion FROM Sucursales";

        try {

            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            Statement SqlSentence = ActiveCon.createStatement();

            return SqlSentence.executeQuery(sql);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar sucursales"
            );

            return null;
        }
    }

    public int obtenerKilometrajeActual(String idCamion) {

        String sql = "SELECT kilometrajeActual FROM Camiones WHERE idCamion = ?";

        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();
            PreparedStatement ps = ActiveCon.prepareStatement(sql);

            ps.setString(1, idCamion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("kilometrajeActual");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al obtener kilometraje");
        }

        return -1;
    }

    public boolean actualizarKilometraje(String idCamion, int nuevoKilometraje) {
        String sql = "UPDATE Camiones SET kilometrajeActual = ? WHERE idCamion = ?";

        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement ps = ActiveCon.prepareStatement(sql);

            ps.setInt(1, nuevoKilometraje);

            ps.setString(2, idCamion);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al actualizar kilometraje");

            return false;
        }
    }

    //desde aca es solo para FrmRutas y guardarHistorialRecorrido es para FrmRegistro
    public boolean guardarHistorialRecorrido(String idCamion, String detalleJson) {
        try {
            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            String sql = "INSERT INTO HistorialRecorridos (IdCamion, DetalleRecorrido) VALUES (?, ?)";

            PreparedStatement ps = ActiveCon.prepareStatement(sql);

            ps.setString(1, idCamion);
            ps.setString(2, detalleJson);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }

    public ResultSet listarTodos() {

        String sql = "SELECT IdRecorrido, IdCamion, Fecha, DetalleRecorrido FROM HistorialRecorridos ";
        try {

            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            Statement SqlSentence = ActiveCon.createStatement();

            return SqlSentence.executeQuery(sql);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al listar rutas");
            System.out.println(e);
            return null;

        }
    }

    public String ObtenerDetalle(int Id, String patente, String fecha) {

        String sql = "SELECT DetalleRecorrido FROM HistorialRecorridos WHERE IdRecorrido = ? AND IdCamion = ? AND Fecha = ?";

        try {

            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement ps = ActiveCon.prepareStatement(sql);
            ps.setInt(1, Id);
            ps.setString(2, patente);
            ps.setString(3, fecha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("DetalleRecorrido");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener detalle");
        }

        return "";
    }

    public ResultSet Buscar(String patente, String fecha) {

        String sql = "SELECT IdRecorrido, IdCamion, Fecha, DetalleRecorrido FROM HistorialRecorridos WHERE IdCamion = ? AND Fecha = ?";

        try {

            Conexion con = new Conexion();
            Connection ActiveCon = con.getConexion();

            PreparedStatement ps = ActiveCon.prepareStatement(sql);

            ps.setString(1, patente);
            ps.setString(2, fecha);

            return ps.executeQuery();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al buscar recorridos");
            System.out.println(e);
            return null;
        }
    }
}
