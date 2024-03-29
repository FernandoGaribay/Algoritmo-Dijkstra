package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import static main.Estados.CAMINO_FINAL;
import static main.Estados.FINAL;
import static main.Estados.INICIO;
import static main.Estados.MURO;
import static main.Estados.VACIO;
import static main.Estados.VISITADO;

public class Mapa extends JPanel {

    private int numCasilla;
    private JPanel casillas[][];
    private Nodo nodos[][];
    private int inicioX, inicioY;
    private int finX, finY;
   

    public Mapa(int numCasilla) {
        this.numCasilla = numCasilla;
        
        casillas = new JPanel[numCasilla][numCasilla];
        nodos = reiniciarNodos();
        inicioX = -1;
        inicioY = -1;
        finX = -1;
        finY = -1;

        crearMapa();
    }
    
    public Nodo[][] reiniciarNodos() {
        Nodo[][] initNodos = new Nodo[numCasilla][numCasilla];
        
        for (int fila = 0; fila < numCasilla; fila++) {
            for (int columna = 0; columna < numCasilla; columna++) {
                initNodos[fila][columna] = new Nodo(fila,columna);
            }
        }
        
        return initNodos;
    }
    
    public void crearMapa() {
        
        this.removeAll();
        setLayout(null);
        int tamCasilla = 750 / numCasilla;

        for (int fila = 0; fila < numCasilla; fila++) {
            for (int columna = 0; columna < numCasilla; columna++) {
                casillas[fila][columna] = new JPanel();
                casillas[fila][columna].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                casillas[fila][columna].setName(fila + "," + columna);
                casillas[fila][columna].setBounds(columna * tamCasilla, fila * tamCasilla, tamCasilla, tamCasilla);  
                add(casillas[fila][columna]);       
  
                eventosBotones(fila, columna);
            }
        }
        pintarMapa(nodos);
    }
    
    public void pintarMapa(Nodo[][] nodos) {
       
        for (int fila = 0; fila < numCasilla; fila++) {
            for (int columna = 0; columna < numCasilla; columna++) {
                switch (nodos[fila][columna].getEstado()) {
                    case INICIO ->
                        casillas[fila][columna].setBackground(new Color(0, 255, 0));
                    case FINAL ->
                        casillas[fila][columna].setBackground(new Color(255, 0, 0));
                    case MURO ->
                        casillas[fila][columna].setBackground(Color.gray);
                    case VACIO ->
                        casillas[fila][columna].setBackground(Color.white);
                    case VISITADO ->
                        casillas[fila][columna].setBackground(new Color(175, 175, 255));
                    case CAMINO_FINAL ->
                        casillas[fila][columna].setBackground(new Color(255, 240, 0));
                }
            }
        }
        this.revalidate();
        this.repaint();
    }

    private void eventosBotones(int fila, int columna) {
        casillas[fila][columna].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    int ancho = ((JPanel) e.getSource()).getWidth();
                    int x = (e.getX() + casillas[fila][columna].getX()) / ancho;
                    int y = (e.getY() + casillas[fila][columna].getY()) / ancho;

                    if(nodos[y][x].getEstado() != VACIO && nodos[y][x].getEstado() != MURO){
                        return;
                    }
                    
                    if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) {
                        nodos[y][x].setMuro();
                        casillas[y][x].setBackground(Color.gray);
                        
                    } else if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == MouseEvent.BUTTON3_DOWN_MASK) {
                        nodos[y][x].setVacio();
                        casillas[y][x].setBackground(Color.white);
                        
                    }
                } catch (Exception ex) {}
            }
        });

        casillas[fila][columna].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                if (e.getButton() == MouseEvent.BUTTON1) {
                    clickIzquierdo(panel);
                } else if (e.getButton() == MouseEvent.BUTTON3){
                    clickDerecho(panel);
                }
            }
        });
    }
    
    private void clickIzquierdo(JPanel e){
        int coordenadas[] = obtenerCoordenadas(e);

        if (inicioX > -1 && inicioY > -1) {
            System.out.print("\nNodo antiguo: " + nodos[inicioX][inicioY].getX() + "," + nodos[inicioX][inicioY].getY() + "; Tipo: " + nodos[inicioX][inicioY].getEstado());
            nodos[inicioX][inicioY].setVacio();
            nodos[inicioX][inicioY].setSaltos(-1);
            casillas[inicioX][inicioY].setBackground(Color.white);
            System.out.print(" -> " + nodos[inicioX][inicioY].getEstado()+ "\n");
        }
        inicioX = coordenadas[0];
        inicioY = coordenadas[1];
        
        nodos[inicioX][inicioY].setSaltos(0);
        nodos[inicioX][inicioY].setInicio();	//SET THE NODE CLICKED TO BE START
        casillas[inicioX][inicioY].setBackground(Color.green);
        
        System.out.println("Casilla: " + coordenadas[0] + "," + coordenadas[1]);
        System.out.println("Nodo nuevo: " + nodos[coordenadas[0]][coordenadas[1]].getX() + "," + nodos[coordenadas[0]][coordenadas[1]].getY() + "; Tipo: " + nodos[coordenadas[0]][coordenadas[1]].getEstado());
        repaint();
    }
    
    private void clickDerecho(JPanel e){ 
        int coordenadas[] = obtenerCoordenadas(e);
        
        if (finX > -1 && finY > -1) {
            System.out.print("\nNodo antiguo: " + nodos[finX][finY].getX() + "," + nodos[finX][finY].getY() + "; Tipo: " + nodos[finX][finY].getEstado());
            nodos[finX][finY].setVacio();
            casillas[finX][finY].setBackground(Color.white);
            System.out.print(" -> " + nodos[finX][finY].getEstado()+ "\n");
        }
        finX = coordenadas[0];
        finY = coordenadas[1];
        
        nodos[finX][finY].setFinal();
        casillas[finX][finY].setBackground(Color.red);
        
        System.out.println("Casilla: " + coordenadas[0] + "," + coordenadas[1]);
        System.out.println("Nodo nuevo: " + nodos[coordenadas[0]][coordenadas[1]].getX() + "," + nodos[coordenadas[0]][coordenadas[1]].getY() + "; Tipo: " + nodos[coordenadas[0]][coordenadas[1]].getEstado());
        repaint();
    }

    public void reiniciarMapa(){
        
        inicioX= -1;
        inicioY=-1;
        finX=-1;
        finY=-1;
        
        for (int fila = 0; fila < numCasilla; fila++) {
            for (int columna = 0; columna < numCasilla; columna++) {
                casillas[fila][columna].setBackground(Color.white); 
            }
        }
        nodos = reiniciarNodos();
        repaint();
    }
    
    private int[] obtenerCoordenadas(JPanel boton) {
        int coordenadas[] = new int[2];
        for (int i = 0; i < 2; i++) {
            coordenadas[i] = Integer.parseInt(boton.getName().split(",")[i]);
        }
        return coordenadas;
    }
    
    public boolean validacion(){
        if(inicioX < 0 && inicioY < 0){
            return false;
        }
        if(finX < 0 && finY < 0){
            return false;
        }
        return true;
    }

    public int getNumCasilla() {
        return numCasilla;
    }

    public void setNumCasilla(int numCasilla) {
        this.numCasilla = numCasilla;
    }

    public JPanel[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(JPanel[][] casillas) {
        this.casillas = casillas;
    }

    public Nodo[][] getNodos() {
        return nodos;
    }

    public void setNodos(Nodo[][] nodos) {
        this.nodos = nodos;
    }

    public int getInicioX() {
        return inicioX;
    }

    public void setInicioX(int inicioX) {
        this.inicioX = inicioX;
    }

    public int getInicioY() {
        return inicioY;
    }

    public void setInicioY(int inicioY) {
        this.inicioY = inicioY;
    }

    public int getFinX() {
        return finX;
    }

    public void setFinX(int finX) {
        this.finX = finX;
    }

    public int getFinY() {
        return finY;
    }

    public void setFinY(int finY) {
        this.finY = finY;
    }

}
