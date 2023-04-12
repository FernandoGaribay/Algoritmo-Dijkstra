package main;

import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

public class main extends javax.swing.JFrame {

    private static int numeroCasillas = 30;
    private static int milisegundos = 15;

    private Mapa tablero;
    private Dijkstra objDijkstra;
    
    public main() {
        initComponents();
        this.inicializarMapa();
    }
    
    public void inicializarMapa(){
        pnlMapa.removeAll();
        
        tablero = new Mapa(numeroCasillas);
        pnlMapa.add(tablero);
        tablero.setBounds(0, 0, pnlMapa.getWidth(), pnlMapa.getHeight());
        
        pnlMapa.revalidate();
        pnlMapa.repaint();
    }
    
    public void actualizarUI() {
        tablero.pintarMapa(objDijkstra.getNodos());
        pnlMapa.revalidate();
        pnlMapa.repaint();
    }
    
    public void imprimirNodos(Nodo nodos[][]){
        this.lblComprobaciones.setText("Misma vtn");
        for (int fila = 0; fila < nodos.length; fila++) {
            for (int columna = 0; columna < nodos.length; columna++) {
                if(nodos[fila][columna].getEstado() == Estados.INICIO){
                    System.out.print("I");
                } else if (tablero.getNodos()[fila][columna].getEstado() == Estados.FINAL){
                    System.out.print("F");
                } else if (tablero.getNodos()[fila][columna].getEstado() == Estados.MURO) {
                    System.out.print("M");
                } else if (tablero.getNodos()[fila][columna].getEstado() == Estados.VACIO) {
                    System.out.print(".");
                } else if (tablero.getNodos()[fila][columna].getEstado() == Estados.VISITADO) {
                    System.out.print("*");
                } else if (tablero.getNodos()[fila][columna].getEstado() == Estados.CAMINO_FINAL) {
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        pnlMapa = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        lblLongCamino = new javax.swing.JLabel();
        lblComprobaciones = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuHerramientas = new javax.swing.JMenu();
        btnReiniciar = new javax.swing.JMenuItem();
        btnSiguiente = new javax.swing.JMenuItem();
        btnAnterior = new javax.swing.JMenuItem();
        menuConfiguracion = new javax.swing.JMenu();
        btnNumCasillas = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frame"); // NOI18N

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMapa.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout pnlMapaLayout = new javax.swing.GroupLayout(pnlMapa);
        pnlMapa.setLayout(pnlMapaLayout);
        pnlMapaLayout.setHorizontalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        pnlMapaLayout.setVerticalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        pnlContenedor.add(pnlMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 750, 750));

        btnIniciar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnIniciar.setText("Iniciar busqueda");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        pnlContenedor.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 180, 60));

        lblLongCamino.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblLongCamino.setText("Longitud del camino:");
        pnlContenedor.add(lblLongCamino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 30));

        lblComprobaciones.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblComprobaciones.setText("Comprobaciones:");
        pnlContenedor.add(lblComprobaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 310, 30));

        menuHerramientas.setText("Herramientas");

        btnReiniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        btnReiniciar.setText("Reiniciar mapa");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });
        menuHerramientas.add(btnReiniciar);

        btnSiguiente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.ALT_DOWN_MASK));
        btnSiguiente.setText("Paso Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        menuHerramientas.add(btnSiguiente);

        btnAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT, java.awt.event.InputEvent.ALT_DOWN_MASK));
        btnAnterior.setText("Paso Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        menuHerramientas.add(btnAnterior);

        jMenuBar1.add(menuHerramientas);

        menuConfiguracion.setText("Configuración");

        btnNumCasillas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_DOWN_MASK));
        btnNumCasillas.setText("Numero de casillas");
        btnNumCasillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNumCasillasActionPerformed(evt);
            }
        });
        menuConfiguracion.add(btnNumCasillas);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem1.setText("Milisegundos de Delay");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuConfiguracion.add(jMenuItem1);

        jMenuBar1.add(menuConfiguracion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNumCasillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNumCasillasActionPerformed
        this.numeroCasillas = Integer.parseInt(JOptionPane.showInputDialog("Dijite el numero de casillas"));
        inicializarMapa();
    }//GEN-LAST:event_btnNumCasillasActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        this.tablero.reiniciarMapa();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        objDijkstra = new Dijkstra(this, tablero);
        objDijkstra.start(); 
        
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        System.out.println("Paso siguiente *");
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        System.out.println("Paso anterior *");
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int milisegundos = Integer.parseInt(JOptionPane.showInputDialog("Dijite el numero de delay (milisegundos):"));
        this.milisegundos = milisegundos;
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    public int getMilisegundos() {
        return milisegundos;
    }

    public void setMilisegundos(int aMilisegundos) {
        milisegundos = aMilisegundos;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnAnterior;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JMenuItem btnNumCasillas;
    private javax.swing.JMenuItem btnReiniciar;
    private javax.swing.JMenuItem btnSiguiente;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JLabel lblComprobaciones;
    private javax.swing.JLabel lblLongCamino;
    private javax.swing.JMenu menuConfiguracion;
    private javax.swing.JMenu menuHerramientas;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlMapa;
    // End of variables declaration//GEN-END:variables
}
