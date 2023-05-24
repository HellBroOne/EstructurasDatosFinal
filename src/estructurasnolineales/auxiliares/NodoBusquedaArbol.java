package estructurasnolineales.auxiliares;

public class NodoBusquedaArbol {
    protected long indice; //valor de un campo
    protected long direccionMemoria;

    public NodoBusquedaArbol(){
        indice = 0;
        direccionMemoria = 0;
    }

    public long getIndice() {
        return indice;
    }

    public void setIndice(long indice) {
        this.indice = indice;
    }

    public long getDireccionMemoria() {
        return direccionMemoria;
    }

    public void setDireccionMemoria(long direccionMemoria) {
        this.direccionMemoria = direccionMemoria;
    }

    @Override
    public String toString() {
        return ""+indice;
    }
}
