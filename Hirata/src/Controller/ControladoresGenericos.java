
package Controller;

public class ControladoresGenericos {
   
    public static void manejarRut(javax.swing.JTextField txt, java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();

        // Bloquear todo lo que no sea número o K/k
        if (!Character.isDigit(c) && c != 'k' && c != 'K') {
            evt.consume();
            return;
        }

        // Convertir k → K
        if (c == 'k') {
            c = 'K';
        }

        // Texto actual SIN formato
        String actual = txt.getText().replaceAll("[^0-9K]", "");

        // Simular nuevo texto
        String nuevo = actual + c;

        // Limitar a 9 caracteres (8 números + DV)
        if (nuevo.length() > 9) {
            evt.consume();
            return;
        }

        // Formatear
        String formateado = formatearRUT(nuevo);

        txt.setText(formateado);

        // Cancelar el evento original
        evt.consume();

        // Validar
        if (validarRUT(nuevo)) {
            txt.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GREEN, 2));
        } else {
            txt.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED, 2));
        }
    }

    public static String formatearRUT(String rut) {
        StringBuilder sb = new StringBuilder(rut);
        int largo = sb.length();

        if (largo > 1) sb.insert(largo - 1, "-");
        if (largo > 4) sb.insert(largo - 4, ".");
        if (largo > 7) sb.insert(largo - 7, ".");

        return sb.toString();
    }

    public static boolean validarRUT(String rut) {
        try {
            int suma = 0;
            int multiplo = 2;

            for (int i = rut.length() - 2; i >= 0; i--) {
                suma += Character.getNumericValue(rut.charAt(i)) * multiplo;
                multiplo = (multiplo == 7) ? 2 : multiplo + 1;
            }

            int dvEsperado = 11 - (suma % 11);
            char dvReal = rut.charAt(rut.length() - 1);

            if (dvEsperado == 11) return dvReal == '0';
            if (dvEsperado == 10) return dvReal == 'K';

            return dvReal == Character.forDigit(dvEsperado, 10);

        } catch (Exception e) {
            return false;
        }
    }  
    
    public static void validarYColorearRut(javax.swing.JTextField txt) {

        String limpio = txt.getText().replaceAll("[^0-9K]", "");

        if (validarRUT(limpio)) {
            txt.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GREEN, 2));
        } else {
            txt.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED, 2));
        }
    }
}
