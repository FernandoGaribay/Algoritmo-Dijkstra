package main;

class Nodo {

    private Estados estado;
    private int saltos;
    private int x;
    private int y;
    private double distanciaFinal = 0;

    private int anteriorX;
    private int anteriorY;

    public Nodo(int x, int y) {
        this.estado = Estados.VACIO;
        this.x = x;
        this.y = y;
        this.saltos = -1;
    }

    private void setEstado(Estados nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setInicio() {
        setEstado(Estados.INICIO);
    }

    public void setFinal() {
        setEstado(Estados.FINAL);
    }

    public void setMuro() {
        setEstado(Estados.MURO);
    }

    public void setVacio() {
        setEstado(Estados.VACIO);
    }

    public void setVisitado() {
        setEstado(Estados.VISITADO);
    }

    public void setAbierto() {
        setEstado(Estados.ABIERTO);
    }

    public void setCaminoFinal() {
        setEstado(Estados.CAMINO_FINAL);
    }

    public Estados getEstado() {
        return estado;
    }

    public int getSaltos() {
        return saltos;
    }

    public void setSaltos(int saltos) {
        this.saltos = saltos;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDistanciaFinal() {
        return distanciaFinal;
    }

    public void setDistanciaFinal(double distanciaFinal) {
        this.distanciaFinal = distanciaFinal;
    }

    public int getAnteriorX() {
        return anteriorX;
    }

    public int getAnteriorY() {
        return anteriorY;
    }

    public void setNodoAnterior(int x, int y) {
        anteriorX = x;
        anteriorY = y;
    }
}
