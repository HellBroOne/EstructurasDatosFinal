package estructurasnolineales.auxiliares;

import estructurasnolineales.Matriz2Numerica;

public class VerticeProbabilidad {
    protected int indice;
    protected Object info;
    protected Matriz2Numerica probabilidad;

    public VerticeProbabilidad(){
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

    public Matriz2Numerica getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Matriz2Numerica probabilidad) {
        this.probabilidad = probabilidad;
    }

    public String toString(){
        return info.toString();
    }
}
