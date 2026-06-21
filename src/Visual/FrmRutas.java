/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visual;

import Controller.CrudRutas;
import Controller.Sesion;
import Generics.DetalleRecorrido;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author jose9
 */
public class FrmRutas extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmRutas.class.getName());

    Sesion ObjSesion = new Sesion();
    String tipoUsuario = ObjSesion.getTipoUsuario();
    CrudRutas ObjCrudRutas = new CrudRutas();

    public FrmRutas(String tipoUsuario) {
        this.setUndecorated(true);
        initComponents();

        Image icon = new ImageIcon(getClass().getResource("/Assets/Pollo (2).jpeg")).getImage();
        setIconImage(icon);
        try {
            java.awt.Taskbar.getTaskbar().setIconImage(icon);
        } catch (Exception e) {
        }
        setSize(937, 515);
        setLocationRelativeTo(null);
        setResizable(false);
        TxtAreaDetalle.setEnabled(false);

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "salir");
        getRootPane().getActionMap().put("salir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FrmPrincipal ObjPrincipal = new FrmPrincipal(tipoUsuario);
                ObjPrincipal.setVisible(true);
            }
        });

        MostrarDatosTabla();

    }

    private FrmRutas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String FormatearRecorrido(DetalleRecorrido d, String fecha) {

        StringBuilder sb = new StringBuilder();

        sb.append("=== RECORRIDO ===\n\n");
        sb.append("Fecha: ").append(fecha).append("\n\n");
        sb.append("Origen: ").append(d.origen != null ? d.origen : "No especificado").append("\n");
        sb.append("Destino: ").append(d.destino != null ? d.destino : "No especificado").append("\n");
        sb.append("Distancia planificada: ").append(d.distanciaKm).append(" km\n");
        sb.append("Kilometraje inicial: ").append(d.kilometrajeInicial).append(" km\n");
        sb.append("Kilometraje final: ").append(d.kilometrajeFinal).append(" km\n");
        sb.append("Distancia recorrida: ").append(d.kilometrajeFinal - d.kilometrajeInicial).append(" km\n\n");
        sb.append("Temperatura del motor: ").append(d.temperaturaMotor).append(" °C\n");
        sb.append("Temperatura de la carga: ").append(d.temperaturaCarga).append(" °C\n");

        return sb.toString();
    }

    public void BuscarRecorridos() {
        DefaultTableModel ObjModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ObjModel.addColumn("ID");
        ObjModel.addColumn("Patente");
        ObjModel.addColumn("Fecha");
        ObjModel.addColumn("Detalle");

        TblDatos.setModel(ObjModel);

        ResultSet resultado = null;

        try {

            resultado = ObjCrudRutas.Buscar(TxtPatente.getText(), TxtFecha.getText());
            int cantidad = 0;

            while (resultado != null && resultado.next()) {

                ObjModel.addRow(new Object[]{
                    resultado.getInt("IdRecorrido"),
                    resultado.getString("IdCamion"),
                    resultado.getDate("Fecha"),
                    resultado.getString("DetalleRecorrido")
                });

                cantidad++;
            }

            LblRegistros.setText("Registros encontrados: " + cantidad);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "No se pudieron cargar los registros", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void MostrarDatosTabla() {

        DefaultTableModel ObjModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        ObjModel.addColumn("ID");
        ObjModel.addColumn("Patente");
        ObjModel.addColumn("Fecha");
        ObjModel.addColumn("Detalle");

        TblDatos.setModel(ObjModel);

        ResultSet resultado = null;
        Connection con = null;

        try {

            resultado = ObjCrudRutas.listarTodos();

            if (resultado != null) {
                con = resultado.getStatement().getConnection();
            }

            int cantRegistros = 0;

            while (resultado != null && resultado.next()) {

                ObjModel.addRow(new Object[]{
                    resultado.getInt("IdRecorrido"),
                    resultado.getString("IdCamion"),
                    resultado.getDate("Fecha"),
                    resultado.getString("DetalleRecorrido")
                });

                cantRegistros++;

            }

            LblRegistros.setText("Total registros: " + cantRegistros);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los datos");
            System.out.println(e);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LblPatente = new javax.swing.JLabel();
        TxtPatente = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        TxtFecha = new javax.swing.JTextField();
        LblFechaMantenimiento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtAreaDetalle = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblDatos = new javax.swing.JTable();
        BtnVolver = new javax.swing.JButton();
        LblRegistros = new javax.swing.JLabel();
        LblDetalle = new javax.swing.JLabel();
        LblInstrucciones = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LblTitulo.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Historial de Rutas");

        LblPatente.setText("Patente");

        TxtPatente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPatenteActionPerformed(evt);
            }
        });
        TxtPatente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtPatenteKeyTyped(evt);
            }
        });

        BtnBuscar.setText("Buscar");
        BtnBuscar.setToolTipText("requiere id y fecha para buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        TxtFecha.setToolTipText("Formato: yyyy-mm-dd");
        TxtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFechaActionPerformed(evt);
            }
        });
        TxtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFechaKeyTyped(evt);
            }
        });

        LblFechaMantenimiento.setText("Fecha");

        TxtAreaDetalle.setColumns(20);
        TxtAreaDetalle.setRows(5);
        jScrollPane1.setViewportView(TxtAreaDetalle);

        TblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblDatosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblDatos);

        BtnVolver.setText("Volver");
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });

        LblRegistros.setText("Registros");

        LblDetalle.setText("Detalle Registro");

        LblInstrucciones.setText("Presione ESC o aqui para volver");
        LblInstrucciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblInstruccionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LblInstruccionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LblInstruccionesMouseExited(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/refresh-page-option.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LblTituloLayout = new javax.swing.GroupLayout(LblTitulo);
        LblTitulo.setLayout(LblTituloLayout);
        LblTituloLayout.setHorizontalGroup(
            LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LblTituloLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(LblTituloLayout.createSequentialGroup()
                            .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(LblTituloLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(LblRegistros)
                                    .addGap(349, 349, 349)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LblDetalle)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LblTituloLayout.createSequentialGroup()
                            .addGap(372, 372, 372)
                            .addComponent(LblInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(328, 328, 328)))
                    .addComponent(jLabel1)
                    .addGroup(LblTituloLayout.createSequentialGroup()
                        .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblFechaMantenimiento)
                            .addGroup(LblTituloLayout.createSequentialGroup()
                                .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnVolver)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        LblTituloLayout.setVerticalGroup(
            LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LblTituloLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LblTituloLayout.createSequentialGroup()
                        .addComponent(LblPatente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtPatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnBuscar)
                            .addComponent(BtnVolver)
                            .addComponent(jButton1)))
                    .addGroup(LblTituloLayout.createSequentialGroup()
                        .addComponent(LblFechaMantenimiento)
                        .addGap(34, 34, 34)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblRegistros)
                    .addComponent(LblDetalle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LblTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblInstrucciones)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtPatenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPatenteActionPerformed

    }//GEN-LAST:event_TxtPatenteActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        if (TxtPatente.getText().isBlank() || TxtFecha.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Ingrese datos de busqueda validos");
            return;
        }
        BuscarRecorridos();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void TxtFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFechaKeyTyped
        char c = evt.getKeyChar();
        String texto = TxtFecha.getText();

        // 1. Solo permitir números
        if (!Character.isDigit(c)) {
            evt.consume();
            return;
        }

        // 2. Limitar a 10 caracteres (yyyy-mm-dd)
        if (texto.length() >= 10) {
            evt.consume();
            return;
        }

        // 3. Insertar guiones automáticamente
        // Nota: El evento KeyTyped ocurre antes de que el caracter se añada al texto
        if (texto.length() == 4 || texto.length() == 7) {
            TxtFecha.setText(texto + "-");
        }
    }//GEN-LAST:event_TxtFechaKeyTyped

    private void TxtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFechaActionPerformed

    private void TblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDatosMouseClicked
        int filaSeleccionada = TblDatos.getSelectedRow();

        int id = (int) TblDatos.getValueAt(filaSeleccionada, 0);
        String patente = TblDatos.getValueAt(filaSeleccionada, 1).toString();
        String fecha = TblDatos.getValueAt(filaSeleccionada, 2).toString();

        String json = ObjCrudRutas.ObtenerDetalle(id, patente, fecha);

        try {
            ObjectMapper mapper = new ObjectMapper();

            DetalleRecorrido detalle = mapper.readValue(json, DetalleRecorrido.class);

            TxtAreaDetalle.setText(FormatearRecorrido(detalle, fecha));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al interpretar el detalle del recorrido:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_TblDatosMouseClicked

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
        dispose();
        FrmPrincipal ObjPrincipal = new FrmPrincipal(ObjSesion.getTipoUsuario());
        ObjPrincipal.setVisible(true);
    }//GEN-LAST:event_BtnVolverActionPerformed

    private void LblInstruccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblInstruccionesMouseClicked
        dispose();
        FrmPrincipal ObjPrincipal = new FrmPrincipal(tipoUsuario);
        ObjPrincipal.setVisible(true);

    }//GEN-LAST:event_LblInstruccionesMouseClicked

    private void LblInstruccionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblInstruccionesMouseEntered
        LblInstrucciones.setForeground(Color.red);
    }//GEN-LAST:event_LblInstruccionesMouseEntered

    private void LblInstruccionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblInstruccionesMouseExited
        LblInstrucciones.setForeground(Color.black);
    }//GEN-LAST:event_LblInstruccionesMouseExited

    private void TxtPatenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPatenteKeyTyped
        char c = evt.getKeyChar();

        // Convertir a mayúscula automáticamente
        evt.setKeyChar(Character.toUpperCase(c));

        // Permitir solo letras, números y guion
        if (!Character.isLetterOrDigit(c) && c != '-') {
            evt.consume(); // bloquea el caracter
        }
    }//GEN-LAST:event_TxtPatenteKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TxtPatente.setText(null);
        TxtFecha.setText(null);
        TxtAreaDetalle.setText(null);
        MostrarDatosTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FrmRutas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnVolver;
    private javax.swing.JLabel LblDetalle;
    private javax.swing.JLabel LblFechaMantenimiento;
    private javax.swing.JLabel LblInstrucciones;
    private javax.swing.JLabel LblPatente;
    private javax.swing.JLabel LblRegistros;
    private javax.swing.JPanel LblTitulo;
    private javax.swing.JTable TblDatos;
    private javax.swing.JTextArea TxtAreaDetalle;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JTextField TxtPatente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
