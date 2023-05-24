package estructurasLineales;

/**
 * Clase que implementa un TDA Pila Dinamica
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */
public class PilaDinamica implements Lote{

    protected ListaDinamica pila; /** Lista dinamica que simula una pila. */

    /**
     * Constructor de la pila dinamica que inicializa el arrelgo interno<br/>
     * dinamico de la pila.
     */
    public PilaDinamica(){
        pila = new ListaDinamica();
    }

    /**
     * Metodo que verifica si una pila dinamica esta llena creando<br/>
     * un objeto. (Otra manera de saber que no hay memoria es con un<br/>
     * Stack Overflow o un OutOfMemoryError).
     * @return True si está llena (no hay espacio en memoria)<br/>
     * False si aún hay espacio en la memoria.
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
     * Metodo que verifica si la pila dinamica esta vacia.
     * @return Regresa True si esta vacia, false si no.
     */
    @Override
    public boolean vacio() { //VACIO FUNCIONA
        return pila.vacia();
    }

    /**
     * Metodo que permite agregar un elemento al final de la pila.
     * @param info Informacion a agregar.
     * @return Regresa true si se pudo agregar o false
     */
    @Override
    public boolean poner(Object info) { //PONER FUNCIONA
        int resultado = pila.agregar(info); //quitamos el elemento y el resultado va a compararse
        if (resultado == 1){ // si se pudo agregar
            return true;
        } else { //no se pudo agregar
            return false;
        }
    }

    /**
     * Metodo que quita el del inicio y lo devuelve.
     * @return Regresa el objeto del final (LIFO).
     */
    @Override
    public Object quitar() { //QUITAR FUNCIONA
        return pila.eliminar();
    }

    /**
     * Metodo que imprime los elementos de la pila.
     */
    @Override
    public void imprimir() { //IMPRIMIR OK
        pila.imprimir();
    }

    /**
     * Metodo que regresa el ultimo nodo en la pila.
     * @return Regresa la info del ultimo nodo.
     */
    @Override
    public Object verTope() { //VER ULTIMO FUNCIONA
        return pila.verUltimo();
    }
}
