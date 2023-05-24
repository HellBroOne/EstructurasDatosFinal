package registros.comunes;

public class EtiquetaGrafo {
    protected double metricaAcumulada;
    protected int verticeAnterior;
    protected int iteracion;

    public double getMetricaAcumulada() {
        return metricaAcumulada;
    }

    public void setMetricaAcumulada(double metricaAcumulada) {
        this.metricaAcumulada = metricaAcumulada;
    }

    public int getVerticeAnterior() {
        return verticeAnterior;
    }

    public void setVerticeAnterior(int verticeAnterior) {
        this.verticeAnterior = verticeAnterior;
    }

    public int getIteracion() {
        return iteracion;
    }

    public void setIteracion(int iteracion) {
        this.iteracion = iteracion;
    }

    public String toString(){
        return "["+metricaAcumulada+", "+verticeAnterior+"] "+iteracion;
    }
}
