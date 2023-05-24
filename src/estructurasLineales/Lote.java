package estructurasLineales;

/**
 * Interfaz correspondiente al Lote.
 * @version 1.0
 * @author Gerardo Rivas Delgado
 */
public interface Lote {
    public boolean lleno();
    public boolean vacio();
    public boolean poner(Object info);
    public Object quitar();
    public void imprimir();
    public Object verTope();
}
