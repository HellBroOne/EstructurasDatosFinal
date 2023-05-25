package estructurasnolineales.auxiliares;

import estructurasnolineales.Matriz2;

public class Vertice {
    protected int indice;
    protected Object info;
    protected Matriz2 probabilidad;

    public Vertice(){
        this.probabilidad = null;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Matriz2 getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Matriz2 probabilidad) {
        this.probabilidad = probabilidad;
    }

    public String toString(){
        return info.toString();
    }
}
