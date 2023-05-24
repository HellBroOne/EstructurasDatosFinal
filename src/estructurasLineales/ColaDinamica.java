package estructurasLineales;

/**
 * Clase que implementa un TDA de Cola Dinamica.
 */
public class ColaDinamica implements Lote{

    protected ListaDinamica cola; /** Atributo Lista dinamica que es una cola*/

    /**
     * Constructor que inicia a la Cola Dinamica,<br/>
     * iniciando su arreglo interno.
     */
    public ColaDinamica(){
        cola = new ListaDinamica();
    }

    /**
     * Metodo que verifica si la cola esta llena.
     * @return Regresa true si no esta llena, false si esta llena.
     */
    @Override
    public boolean lleno() {
        Object comprobacion = new Object();
        if (comprobacion != null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que verifica si la cola esta vacia.
     * @return Regresa true si esta vacia, false si no.
     */
    @Override
    public boolean vacio() {
        return cola.vacia();
    }

    /**
     * Metodo que pone un elemento a la cola.
     * @param info Elemento a poner.
     * @return Regresa True si se pudo agregar, false si no.
     */
    @Override
    public boolean poner(Object info) {
        int resultado = cola.agregar(info);
        if (resultado == 1){ // si se pudo agregar
            return true;
        } else { //no se pudo agregar
            return false;
        }
    }

    /**
     * Metodo que quita y devuelve el primer elemento de la cola.
     * @return Regresa el primer elemento (FIFO).
     */
    @Override
    public Object quitar() {
        return cola.eliminarInicio();
    }

    /**
     * Metodo que imprime a la cola.
     */
    @Override
    public void imprimir() {
        cola.imprimir();
    }

    /**
     * Metodo que permite obtener el ultimo valor de la cola.
     * @return
     */
    @Override
    public Object verTope() {
        return cola.verUltimo();
    }

    /**
     * Metodo que permite observar el primer nodo
     * @return regresa el primer elemento
     */
    public Object verInicio(){
        return cola.primero.getInfo();
    }
}
