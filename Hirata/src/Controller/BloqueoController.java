package Controller;

import Modelo.Conexion;
import java.net.NetworkInterface;
import java.sql.*;
import java.time.*;
import java.util.Enumeration;

public class BloqueoController {

    // Obtiene la dirección MAC única del equipo
    public String obtenerDireccionMAC() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : mac) {
                        sb.append(String.format("%02X:", b));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener MAC: " + e.getMessage());
        }
        return "00:00:00:00:00:00"; // Valor por defecto
    }

    // Verifica si el equipo está bloqueado y si debe desbloquearse
    public boolean verificarBloqueo() {
        String mac = obtenerDireccionMAC();
        Conexion con = new Conexion();
        Connection conexion = con.getConexion();

        try {
            // Verificar si el equipo existe
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT * FROM equipos WHERE mac_equipo = ?");
            ps.setString(1, mac);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                // Si no existe el registro, lo creamos
                PreparedStatement insert = conexion.prepareStatement(
                        "INSERT INTO equipos (mac_equipo, estado, fechaBan, horaBan) VALUES (?, false, NULL, NULL)");
                insert.setString(1, mac);
                insert.executeUpdate();
                return false;
            }

            boolean estado = rs.getBoolean("estado");
            Date fechaBanSQL = rs.getDate("fechaBan");
            Time horaBanSQL = rs.getTime("horaBan");

            if (!estado) return false; // No bloqueado

            // Si está bloqueado pero tiene fecha/hora
            if (fechaBanSQL != null && horaBanSQL != null) {
                LocalDate fechaBan = fechaBanSQL.toLocalDate();
                LocalTime horaBan = horaBanSQL.toLocalTime();
                LocalDateTime fechaHoraBan = LocalDateTime.of(fechaBan, horaBan);
                LocalDateTime ahora = LocalDateTime.now();

                Duration dif = Duration.between(fechaHoraBan, ahora);

                if (dif.toMinutes() >= 5) {
                    // Desbloquear si ya pasaron 5 minutos
                    PreparedStatement desbloquear = conexion.prepareStatement(
                            "UPDATE equipos SET estado = false, fechaBan = NULL, horaBan = NULL WHERE mac_equipo = ?");
                    desbloquear.setString(1, mac);
                    desbloquear.executeUpdate();
                    return false;
                } else {
                    long minutosRestantes = 5 - dif.toMinutes();
                    System.out.println("Bloqueado aún por " + minutosRestantes + " minuto(s).");
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println("Error al verificar bloqueo: " + e.getMessage());
        }
        return false;
    }

    // Activa el bloqueo (registra fecha y hora actuales)
public void activarBloqueo() {
        String mac = obtenerDireccionMAC();
        Conexion con = new Conexion();
        Connection conexion = con.getConexion();

        try {
            PreparedStatement update = conexion.prepareStatement(
                    "UPDATE equipos SET estado = true, fechaBan = CURDATE(), horaBan = CURTIME() WHERE mac_equipo = ?");
            update.setString(1, mac);
            update.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al activar bloqueo: " + e.getMessage());
        }
    }
}


