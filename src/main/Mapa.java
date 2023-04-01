package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Mapa extends JPanel {

    private static final int numCasilla = 20;
    private JButton casillas[][];

    public Mapa() {
        casillas = new JButton[numCasilla][numCasilla];
        crearMapa();

//        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int cellX = e.getX() / tamanoCasilla;
//                int cellY = e.getY() / tamanoCasilla;
//                {
//                    System.out.println(e.getX() / tamanoCasilla);
//                    System.out.println("Clicked on cell: " + cellX + ", " + cellY);
//                }
//            }
//        });
//        this.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                int cellX = e.getX() / tamanoCasilla;
//                int cellY = e.getY() / tamanoCasilla;
//                {
//                    System.out.println(e.getX() / tamanoCasilla);
//                    System.out.println("Clicked on cell: " + cellX + ", " + cellY);
//                }
//
//            }
//        });
    }

//    @Override
//    public void paintComponent(Graphics g) {	//REPAINT
//        super.paintComponent(g);
//        for (int x = 0; x < numCasilla; x++) {	//PAINT EACH NODE IN THE GRID
//            for (int y = 0; y < numCasilla; y++) {
//                g.setColor(Color.WHITE);
//                g.fillRect(x * tamanoCasilla, y * tamanoCasilla, tamanoCasilla, tamanoCasilla);
//                g.setColor(Color.BLACK);
//                g.drawRect(x * tamanoCasilla, y * tamanoCasilla, tamanoCasilla, tamanoCasilla);
//            }
//        }
//    }
    private void crearMapa() {
        setLayout(new GridLayout(numCasilla, numCasilla));

        for (int fila = 0; fila < numCasilla; fila++) {
            for (int columna = 0; columna < numCasilla; columna++) {
                casillas[fila][columna] = new JButton();
                casillas[fila][columna].setName(fila + "," + columna);
                add(casillas[fila][columna]);

                casillas[fila][columna].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        btnClickIzquierdo((JButton) e.getSource());
                    }
                });
            }
        }
    }
    
    private void btnClickIzquierdo(JButton e) {
        int coordenadas[] = obtenerCoordenadas(e);
        casillas[coordenadas[0]][coordenadas[1]].setText("a");
        System.out.println("coordenada: " + coordenadas[0] + "," + coordenadas[1]);
    }

    private int[] obtenerCoordenadas(JButton boton) {
        int coordenadas[] = new int[2];
        for (int i = 0; i < 2; i++) {
            coordenadas[i] = Integer.parseInt(boton.getName().split(",")[i]);
        }
        return coordenadas;
    }

}
