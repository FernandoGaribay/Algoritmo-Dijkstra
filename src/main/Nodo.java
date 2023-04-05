package main;

class Nodo {

    private Estado estado;
    private int saltos;
    private int x;
    private int y;
    private double distanciaFinal = 0;

    public Nodo(int x, int y) {
        estado = Estado.VACIO;
        this.x = x;
        this.y = y;
        saltos = -1;
    }

    public double calcularDistancia(int finX, int finY) {
        int xdif = Math.abs(getX() - finX);
        int ydif = Math.abs(getY() - finY);
        setDistanciaFinal(Math.sqrt((xdif * xdif) + (ydif * ydif)));
        return getDistanciaFinal();
    }
    
    private void setEstado(Estado nuevoEstado) {
        estado = nuevoEstado;
    }

    public void setInicio() {
        setEstado(Estado.INICIO);
    }

    public void setFinal() {
        setEstado(Estado.FINAL);
    }

    public void setMuro() {
        setEstado(Estado.MURO);
    }

    public void setVacio() {
        setEstado(Estado.VACIO);
    }

    public void setVisitado() {
        setEstado(Estado.VISITADO);
    }

    public void setCaminoFinal() {
        setEstado(Estado.CAMINO_FINAL);
    }
            
    private enum Estado{
        INICIO,
        FINAL,
        MURO,
        VACIO,
        VISITADO,
        CAMINO_FINAL
    }

    public Estado getEstado() {
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
}
