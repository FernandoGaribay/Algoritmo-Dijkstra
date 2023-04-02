package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Mapa extends JPanel {

    private static int numCasilla = 30;
    private JPanel casillas[][];

    public Mapa(int numCasilla) {
        this.numCasilla = numCasilla;
        
        casillas = new JPanel[numCasilla][numCasilla];
        crearMapa();
        numCasilla = 100;
    }

    private void crearMapa() {
        setLayout(null);
        int tamCasilla = 750 / numCasilla;

        for (int fila = 0; fila < numCasilla; fila++) {
            for (int columna = 0; columna < numCasilla; columna++) {
                casillas[fila][columna] = new JPanel();
                casillas[fila][columna].setBackground(Color.white);
                casillas[fila][columna].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                casillas[fila][columna].setName(fila + "," + columna);
                casillas[fila][columna].setBounds(columna * tamCasilla, fila * tamCasilla, tamCasilla, tamCasilla);
                add(casillas[fila][columna]);
                eventosBotones(fila, columna);
            }
        }
    }

    public void eventosBotones(int fila, int columna) {
        casillas[fila][columna].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    int ancho = ((JPanel) e.getSource()).getWidth();
                    int x = (e.getX() + casillas[fila][columna].getX()) / ancho;
                    int y = (e.getY() + casillas[fila][columna].getY()) / ancho;
                    casillas[y][x].setBackground(Color.red);
                } catch (Exception ex) {}
            }
        });

        casillas[fila][columna].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int coordenadas[] = obtenerCoordenadas((JPanel) e.getSource());
                System.out.println("Casilla: " + coordenadas[0] + "," + coordenadas[1]);
                casillas[coordenadas[0]][coordenadas[1]].setBackground(Color.red);
            }
        });
    }

    private int[] obtenerCoordenadas(JPanel boton) {
        int coordenadas[] = new int[2];
        for (int i = 0; i < 2; i++) {
            coordenadas[i] = Integer.parseInt(boton.getName().split(",")[i]);
        }
        return coordenadas;
    }

}
