/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visual;

import Controller.CrudRutas;
import Controller.OpenRouteService;
import Controller.Sesion;
import Generics.DetalleRecorrido;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.sql.ResultSet;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author jose9
 */
public class FrmMonitoreo extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmMonitoreo.class.getName());

    Sesion ObjSesion = new Sesion();
    String tipoUsuario = ObjSesion.getTipoUsuario();
    CrudRutas RutasCrud = new CrudRutas();
    private double distanciaCalculada = 0;

    public FrmMonitoreo(String tipoUsuario) {
        this.setUndecorated(true);
        initComponents();

        if (ObjSesion.getIdCamion() == null || ObjSesion.getIdCamion().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuario sin camion asignado");
            dispose();
            return;
        }

        Image icon = new ImageIcon(getClass().getResource("/Assets/Pollo (2).jpeg")).getImage();
        setIconImage(icon);
        try {
            java.awt.Taskbar.getTaskbar().setIconImage(icon);
        } catch (Exception e) {
        }
        setSize(650, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "salir");
        getRootPane().getActionMap().put("salir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FrmPrincipal ObjPrincipal = new FrmPrincipal(tipoUsuario);
                ObjPrincipal.setVisible(true);
            }
        });

        CmbSucursalPartida.setVisible(false);
        CmbSucursalLlegada.setVisible(false);
        TxtLocalidadPartida.setVisible(false);
        TxtLocalidadLlegada.setVisible(false);
        LblErrorPartida.setVisible(false);
        LblErrorLlegada.setVisible(false);

        cargarSucursales();

        LblPatente.setText("Patente: " + ObjSesion.getIdCamion());

    }

    private FrmMonitoreo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private double generarTemperaturaMotor() {
        return Math.round((85 + (Math.random() * 8)) * 10.0) / 10.0;
    }

    private double generarTemperaturaCarga() {
        return Math.round((4 + (Math.random() * 4)) * 10.0) / 10.0;
    }

    private String generarJsonRecorrido(String origen, String destino, double distanciaKm, String tiempoEstimado, int kilometrajeInicial, int kilometrajeFinal) {
        try {
            DetalleRecorrido detalle = new DetalleRecorrido();

            detalle.origen = origen;
            detalle.destino = destino;

            detalle.distanciaKm = distanciaKm;
            detalle.tiempoEstimado = tiempoEstimado;

            detalle.kilometrajeInicial = kilometrajeInicial;
            detalle.kilometrajeFinal = kilometrajeFinal;

            detalle.temperaturaMotor = generarTemperaturaMotor();
            detalle.temperaturaCarga = generarTemperaturaCarga();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(detalle);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String obtenerDireccionPartida() {

        if (RdoSucursalPartida.isSelected()) {
            return CmbSucursalPartida.getSelectedItem().toString();
        }

        return TxtLocalidadPartida.getText();
    }

    private String obtenerDireccionLlegada() {
        if (RdoSucursalLlegada.isSelected()) {
            return CmbSucursalLlegada.getSelectedItem().toString();
        }

        return TxtLocalidadLlegada.getText();
    }

    public void cargarSucursales() {

        CmbSucursalPartida.removeAllItems();
        CmbSucursalLlegada.removeAllItems();

        try {

            ResultSet resultado = RutasCrud.listarSucursales();

            while (resultado != null && resultado.next()) {

                String direccion
                        = resultado.getString("Direccion");

                CmbSucursalPartida.addItem(direccion);

                CmbSucursalLlegada.addItem(direccion);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al cargar sucursales");

        }
    }

    private String formatearTiempo(
            double horasDecimal) {

        int horas = (int) horasDecimal;

        int minutos = (int) ((horasDecimal - horas) * 60);

        if (horas <= 0) {
            return minutos + " min";

        }

        return horas + "h " + minutos + "min";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtngInicio = new javax.swing.ButtonGroup();
        BtngLlegada = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        PnoInicio = new javax.swing.JPanel();
        RdoSucursalPartida = new javax.swing.JRadioButton();
        RdoLocaldiadPartida = new javax.swing.JRadioButton();
        CmbSucursalPartida = new javax.swing.JComboBox<>();
        TxtLocalidadPartida = new javax.swing.JTextField();
        LblErrorPartida = new javax.swing.JLabel();
        PnoLlegada = new javax.swing.JPanel();
        RdoSucursalLlegada = new javax.swing.JRadioButton();
        RdoLocalidadLlegada = new javax.swing.JRadioButton();
        CmbSucursalLlegada = new javax.swing.JComboBox<>();
        TxtLocalidadLlegada = new javax.swing.JTextField();
        LblErrorLlegada = new javax.swing.JLabel();
        BtnIniciar = new javax.swing.JButton();
        BtnFinalizar = new javax.swing.JButton();
        BtnVolver = new javax.swing.JButton();
        LblDistancia = new javax.swing.JLabel();
        LblTiempo = new javax.swing.JLabel();
        LblPatente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PnoInicio.setBorder(javax.swing.BorderFactory.createTitledBorder("Inicio"));

        BtngInicio.add(RdoSucursalPartida);
        RdoSucursalPartida.setText("Sucursal");
        RdoSucursalPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdoSucursalPartidaActionPerformed(evt);
            }
        });

        BtngInicio.add(RdoLocaldiadPartida);
        RdoLocaldiadPartida.setText("Localidad particular");
        RdoLocaldiadPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdoLocaldiadPartidaActionPerformed(evt);
            }
        });

        TxtLocalidadPartida.setToolTipText("[Calle + número], [Comuna], [Ciudad/Región], [País]");

        LblErrorPartida.setText("Ubicacion no encontrada");

        javax.swing.GroupLayout PnoInicioLayout = new javax.swing.GroupLayout(PnoInicio);
        PnoInicio.setLayout(PnoInicioLayout);
        PnoInicioLayout.setHorizontalGroup(
            PnoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnoInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtLocalidadPartida)
                    .addGroup(PnoInicioLayout.createSequentialGroup()
                        .addGroup(PnoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblErrorPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PnoInicioLayout.createSequentialGroup()
                                .addComponent(RdoSucursalPartida)
                                .addGap(33, 33, 33)
                                .addComponent(RdoLocaldiadPartida)))
                        .addGap(0, 137, Short.MAX_VALUE))
                    .addComponent(CmbSucursalPartida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PnoInicioLayout.setVerticalGroup(
            PnoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnoInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RdoSucursalPartida)
                    .addComponent(RdoLocaldiadPartida))
                .addGap(18, 18, 18)
                .addComponent(CmbSucursalPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtLocalidadPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblErrorPartida)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        PnoLlegada.setBorder(javax.swing.BorderFactory.createTitledBorder("Llegada"));

        BtngLlegada.add(RdoSucursalLlegada);
        RdoSucursalLlegada.setText("Sucursal");
        RdoSucursalLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdoSucursalLlegadaActionPerformed(evt);
            }
        });

        BtngLlegada.add(RdoLocalidadLlegada);
        RdoLocalidadLlegada.setText("Localidad particular");
        RdoLocalidadLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdoLocalidadLlegadaActionPerformed(evt);
            }
        });

        TxtLocalidadLlegada.setToolTipText("[Calle + número], [Comuna], [Ciudad/Región], [País]");

        LblErrorLlegada.setText("Ubicacion no encontrada");

        javax.swing.GroupLayout PnoLlegadaLayout = new javax.swing.GroupLayout(PnoLlegada);
        PnoLlegada.setLayout(PnoLlegadaLayout);
        PnoLlegadaLayout.setHorizontalGroup(
            PnoLlegadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnoLlegadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnoLlegadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtLocalidadLlegada, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnoLlegadaLayout.createSequentialGroup()
                        .addGroup(PnoLlegadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnoLlegadaLayout.createSequentialGroup()
                                .addComponent(RdoSucursalLlegada)
                                .addGap(18, 18, 18)
                                .addComponent(RdoLocalidadLlegada))
                            .addComponent(LblErrorLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CmbSucursalLlegada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PnoLlegadaLayout.setVerticalGroup(
            PnoLlegadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnoLlegadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnoLlegadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RdoSucursalLlegada)
                    .addComponent(RdoLocalidadLlegada))
                .addGap(18, 18, 18)
                .addComponent(CmbSucursalLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtLocalidadLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblErrorLlegada)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        BtnIniciar.setText("Iniciar recorrido");
        BtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIniciarActionPerformed(evt);
            }
        });

        BtnFinalizar.setText("Finalizar recorrido");
        BtnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFinalizarActionPerformed(evt);
            }
        });

        BtnVolver.setText("Volver");
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });

        LblDistancia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblDistancia.setText("Distancia: ????");

        LblTiempo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblTiempo.setText("Tiempo estimado: ????");

        LblPatente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblPatente.setText("Patente: ????");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PnoLlegada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BtnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(LblDistancia)
                    .addComponent(LblTiempo)
                    .addComponent(LblPatente))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblPatente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblDistancia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblTiempo)
                        .addGap(18, 18, 18)
                        .addComponent(BtnIniciar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnoLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnFinalizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnVolver)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RdoSucursalPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdoSucursalPartidaActionPerformed
        CmbSucursalPartida.setVisible(true);
        TxtLocalidadPartida.setVisible(false);
        PnoInicio.revalidate();
        PnoInicio.repaint();
    }//GEN-LAST:event_RdoSucursalPartidaActionPerformed

    private void RdoLocaldiadPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdoLocaldiadPartidaActionPerformed
        CmbSucursalPartida.setVisible(false);
        TxtLocalidadPartida.setVisible(true);
        PnoInicio.revalidate();
        PnoInicio.repaint();
    }//GEN-LAST:event_RdoLocaldiadPartidaActionPerformed

    private void RdoSucursalLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdoSucursalLlegadaActionPerformed
        CmbSucursalLlegada.setVisible(true);
        TxtLocalidadLlegada.setVisible(false);
        PnoLlegada.revalidate();
        PnoLlegada.repaint();
    }//GEN-LAST:event_RdoSucursalLlegadaActionPerformed

    private void RdoLocalidadLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdoLocalidadLlegadaActionPerformed
        CmbSucursalLlegada.setVisible(false);
        TxtLocalidadLlegada.setVisible(true);
        PnoLlegada.revalidate();
        PnoLlegada.repaint();
    }//GEN-LAST:event_RdoLocalidadLlegadaActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
        dispose();
        FrmPrincipal ObjPrincipal = new FrmPrincipal(tipoUsuario);
        ObjPrincipal.setVisible(true);
    }//GEN-LAST:event_BtnVolverActionPerformed


    private void BtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIniciarActionPerformed
        String origen = obtenerDireccionPartida();

        String destino = obtenerDireccionLlegada();

        OpenRouteService ors = new OpenRouteService();

        double distancia = ors.calcularDistancia(origen, destino);

        if (distancia < 0) {
            JOptionPane.showMessageDialog(this, "No se pudo calcular la ruta");

            return;
        }
        double tiempo = distancia / 80.0;
        distanciaCalculada = distancia;
        LblDistancia.setText("Distancia: " + String.format("%.2f", distancia) + " KM");
        LblTiempo.setText("Tiempo estimado: " + String.valueOf(formatearTiempo(tiempo)));
    }//GEN-LAST:event_BtnIniciarActionPerformed

    private void BtnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFinalizarActionPerformed
        String idCamion = ObjSesion.getIdCamion();

        if (distanciaCalculada <= 0) {
            JOptionPane.showMessageDialog(this, "No hay recorrido calculado");
            return;
        }

        int distanciaEntera = (int) Math.round(distanciaCalculada);

        int kilometrajeActual = RutasCrud.obtenerKilometrajeActual(idCamion);

        if (kilometrajeActual < 0) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el kilometraje");
            return;
        }

        int nuevoKilometraje = kilometrajeActual + distanciaEntera;

        boolean actualizado = RutasCrud.actualizarKilometraje(idCamion, nuevoKilometraje);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Recorrido finalizado\n" + "Nuevo kilometraje: " + nuevoKilometraje + " KM");

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar");
        }

        String origen = obtenerDireccionPartida();

        String destino = obtenerDireccionLlegada();

        String tiempoEstimado = LblTiempo.getText().replace("Tiempo estimado: ", "");

        String json = generarJsonRecorrido(origen, destino, distanciaCalculada, tiempoEstimado, kilometrajeActual, nuevoKilometraje);

        boolean historialGuardado = RutasCrud.guardarHistorialRecorrido(idCamion, json);
        
        if (actualizado && historialGuardado) {

            JOptionPane.showMessageDialog(this,"Recorrido finalizado\n"+ "Nuevo kilometraje: "+ nuevoKilometraje+ " KM");

        } else {

            JOptionPane.showMessageDialog(this,"Error al guardar historial");
        }

    }//GEN-LAST:event_BtnFinalizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmMonitoreo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnFinalizar;
    private javax.swing.JButton BtnIniciar;
    private javax.swing.JButton BtnVolver;
    private javax.swing.ButtonGroup BtngInicio;
    private javax.swing.ButtonGroup BtngLlegada;
    private javax.swing.JComboBox<String> CmbSucursalLlegada;
    private javax.swing.JComboBox<String> CmbSucursalPartida;
    private javax.swing.JLabel LblDistancia;
    private javax.swing.JLabel LblErrorLlegada;
    private javax.swing.JLabel LblErrorPartida;
    private javax.swing.JLabel LblPatente;
    private javax.swing.JLabel LblTiempo;
    private javax.swing.JPanel PnoInicio;
    private javax.swing.JPanel PnoLlegada;
    private javax.swing.JRadioButton RdoLocaldiadPartida;
    private javax.swing.JRadioButton RdoLocalidadLlegada;
    private javax.swing.JRadioButton RdoSucursalLlegada;
    private javax.swing.JRadioButton RdoSucursalPartida;
    private javax.swing.JTextField TxtLocalidadLlegada;
    private javax.swing.JTextField TxtLocalidadPartida;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
