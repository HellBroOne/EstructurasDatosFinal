package utilerias.comunes;

public enum TipoTabla {
    COLUMNA(0),
    RENGLON(1);
    private int numeroTabla;
    private TipoTabla(int numeroTabla){this.numeroTabla = numeroTabla;}

    public int getNumeroTabla() {
        return numeroTabla;
    }
}
