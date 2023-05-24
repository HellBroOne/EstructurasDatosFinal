package utilerias.comunes;

/**
 * Clase enumerada para el tipo de orden que se llevara.
 */
public enum TipoOrden {
    INC(1),
    DEC(2);

    private int numeroOrden;
    private TipoOrden(int numeroOrden){
        this.numeroOrden = numeroOrden;
    }

    public int getNumeroOrden(){
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
}
