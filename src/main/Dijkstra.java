package main;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Dijkstra extends Thread{

    private Nodo nodos[][];
    private int numCasillas;
    private int inicioX, inicioY;
    private int milisegundos;
    private int validaciones;
    private int longitud;
    boolean ciclo;

    private final main main;
    private final Mapa mapa;

    List<Nodo[][]> historial = new ArrayList<>();

    public List<Nodo[][]> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Nodo[][]> historial) {
        this.historial = historial;
    }

    public Dijkstra(main main, Mapa mapa) {

        this.nodos = mapa.getNodos();
        this.numCasillas = mapa.getNumCasilla();
        this.inicioX = mapa.getInicioX();
        this.inicioY = mapa.getInicioY();
        this.milisegundos = main.getMilisegundos();

        this.validaciones = 0;
        this.longitud = 0;
        this.ciclo = true;

        this.main = main;
        this.mapa = mapa;
    }
    
    @Override
    public void run() {
        if (!mapa.validacion()) {
            JOptionPane.showMessageDialog(main, "Favor de ingresar los nodos de inicio y de final.");
            return;
        }

        ArrayList<Nodo> prioridad = new ArrayList<Nodo>();
        prioridad.add(nodos[inicioX][inicioY]);
        while (ciclo) {
            int saltos = prioridad.get(0).getSaltos() + 1;
            ArrayList<Nodo> explorados = obtenerNodosVecinos(prioridad.get(0), saltos);

            if (!explorados.isEmpty()) {
                prioridad.remove(0);
                prioridad.addAll(explorados);

                main.actualizarUI();
                delay();
            } else {
                prioridad.remove(0);
            }
            añadirHistorial();
        }
    }

    public void algDijkstra() {
        if (!mapa.validacion()) {
            JOptionPane.showMessageDialog(main, "Favor de ingresar los nodos de inicio y de final.");
            return;
        }

        ArrayList<Nodo> prioridad = new ArrayList<Nodo>();
        prioridad.add(nodos[inicioX][inicioY]);
      
            int saltos = prioridad.get(0).getSaltos() + 1;
            ArrayList<Nodo> explorados = obtenerNodosVecinos(prioridad.get(0), saltos);

            if (!explorados.isEmpty()) {
                prioridad.remove(0);
                prioridad.addAll(explorados);

                main.actualizarUI();
            } else {
                prioridad.remove(0);
            }
            añadirHistorial();
        
    }

    public ArrayList<Nodo> obtenerNodosVecinos(Nodo actual, int hops) {
        ArrayList<Nodo> explorados = new ArrayList<>();

        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                int tempX = actual.getX() + a;
                int tempY = actual.getY() + b;
                if ((tempX > -1 && tempX < numCasillas) && (tempY > -1 && tempY < numCasillas)) {
                    Nodo nodoVecino = nodos[tempX][tempY];

                    boolean nodoValido = (nodoVecino.getSaltos() == -1 || nodoVecino.getSaltos() > hops);
                    boolean noEsMuro = (nodoVecino.getEstado() != Estados.MURO);
                    if (nodoValido && noEsMuro) {
                        explorar(nodoVecino, actual.getX(), actual.getY(), hops);
                        explorados.add(nodoVecino);
                    }
                }
            }
        }
        return explorados;
    }

    public void explorar(Nodo actual, int ulX, int ulY, int saltos) {
        if (actual.getEstado() != Estados.INICIO && actual.getEstado() != Estados.FINAL) {
            actual.setVisitado();
        }
        actual.setNodoAnterior(ulX, ulY);
        actual.setSaltos(saltos);
        validaciones++;

        if (actual.getEstado() == Estados.FINAL) {
            caminoFinal(actual.getAnteriorX(), actual.getAnteriorY(), saltos);
        }
    }

    public void caminoFinal(int ulX, int ulY, int saltos) {
        longitud = saltos;
        while (saltos > 1) {
            Nodo current = nodos[ulX][ulY];
            current.setCaminoFinal();
            ulX = current.getAnteriorX();
            ulY = current.getAnteriorY();

            añadirHistorial();
            main.actualizarUI();

            saltos--;
        }
        main.actualizarVariables();
        ciclo = false;
    }
    
    public void añadirHistorial() {
        Nodo[][] nodosCopia = new Nodo[numCasillas][numCasillas];

        for (int i = 0; i < numCasillas; i++) {
            for (int j = 0; j < numCasillas; j++) {
                nodosCopia[i][j] = new Nodo(nodos[i][j].getEstado(), i, j);
            }
        }

        if (!historial.isEmpty()) {
            Nodo[][] ultimaMatriz = historial.get(historial.size() - 1);
            boolean iguales = true;

            for (int i = 0; i < numCasillas; i++) {
                for (int j = 0; j < numCasillas; j++) {
                    if (nodosCopia[i][j].getEstado() != ultimaMatriz[i][j].getEstado()) {
                        iguales = false;
                        break;
                    }
                }
                if (!iguales) {
                    break;
                }
            }

            if (iguales) {
                System.out.println("Matriz igual");
            } else {
                historial.add(nodosCopia);
            }
        } else {
            historial.add(nodosCopia);
        }
    }

    private void delay() {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Nodo[][] getNodos() {
        return nodos;
    }

    public void setNodos(Nodo[][] nodos) {
        this.nodos = nodos;
    }

    public int getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(int validaciones) {
        this.validaciones = validaciones;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
