package main;

class Nodo {

    // 0 = start, 1 = finish, 2 = wall, 3 = empty, 4 = checked, 5 = finalpath
    private int cellType = 0;
    private int hops;
    private int x;
    private int y;

    public Nodo(int type, int x, int y) {
        cellType = type;
        this.x = x;
        this.y = y;
        hops = -1;
    }
}
