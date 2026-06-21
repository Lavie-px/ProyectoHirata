/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

/**
 *
 * @author jose9
 */
public interface ICrud<T> {
    default void actualizar(T obj){};
    default void grabar(T obj){};

   default T buscar(String id){
        return null;
    };
    default T buscar(String id, java.sql.Date fecha){
        return null;
    }
    default void eiliminar(String id){};

    default void guardar(String id, String json, java.sql.Date fecha) {}
    default void actualizar(String id, String json, java.sql.Date fecha) {}
    default void eiliminar(String id, java.sql.Date fecha){};
    
    

}
