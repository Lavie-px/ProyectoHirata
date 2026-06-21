package Generics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jose9
 */
public class Usuario {
    String rut;
    String clave;
    String nombre;
    String apellido;
    String tipoUsuario;
    String idCamion;

    public String getRut() {
        return rut;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getIdCamion() {
        return idCamion;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setIdCamion(String idCamion) {
        this.idCamion = idCamion;
    }


}
