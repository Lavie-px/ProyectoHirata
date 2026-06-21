
package Controller;

public class Detalles {
    public boolean ram;
    public boolean procesador;
    public boolean fuente;
    public boolean placaBase;
    public boolean disipador;

    public Almacenamiento almacenamiento;
    public Perifericos perifericos;
    
    public String tipoMantenimiento; 

    public String descripcion;

    public static class Almacenamiento {
        public boolean ssd;
        public boolean hdd;
    }

    public static class Perifericos {
        public boolean monitor;
        public boolean mouse;
        public boolean teclado;
    }
    
    
    
}
