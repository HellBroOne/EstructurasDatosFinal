package utilerias.comunes;

/**
 * Clase enumerada para el tipo de columna que se llevara.
 */
public enum TipoColumna {
    IZQUIERDA(1),
    DERECHA(2);

    private int numeroColumna;
    private TipoColumna(int numeroOrden){
        this.numeroColumna = numeroOrden;
    }

    public int getNumeroColumna(){
        return numeroColumna;
    }

    public void setNumeroColumna(int numeroOrden) {
        this.numeroColumna = numeroOrden;
    }
}
