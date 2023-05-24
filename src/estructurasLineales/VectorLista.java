package estructurasLineales;

/**
 * Esta clase contiene los métodos de un TDA Vector Lista.
 * @version 1.0
 * @author Gerardo
 */
public interface VectorLista extends Lista{
    /**
     * Regresa true si esta llena, false si no lo esta.
     * @return Regresa true si esta completamente llena.
     */
    public boolean llena();

    /**
     * Obtiene un elemento de un indice especificado.
     * @param indice Indice de la posicion del elemento que se obtendra.
     * @return Se regresa el Objeto del indice anterior.
     */
    public Object obtener(int indice);

    /**
     * Cambia un objeto de la array en la posicion del parametro indice
     * por un objeto info.
     * @param indice Se ingresa un entero como valor indice del arreglo.
     * @param info Se ingresa el objeto el cual va a ser cambiado.
     * @return Se regresa true si se encontro el objeto y se modifico,
     * y false si el indice salio fuera de los rangos o no se encontro.
     */
    public boolean cambiar(int indice, Object info);

    /**
     * Se cambian los elementos de una lista estatica con los elementos de otra lista.
     * @param indicesBusqueda Se agrega una lista de la cual se obtendran los indices.
     * @param infosNuevos Se aniade una Lista que especifica cuales elementos se agregaran.
     * @return Se regresa True si se han cambiado exitosamente, o false si no se cambiaron.
     */
    public boolean cambiarListaEstatica(ListaEstatica indicesBusqueda, ListaEstatica infosNuevos);

    /**
     * Se elimina un Objeto de un indice especifico del arreglo.
     * @param indice Se agrega el indice entero de la posicion que se desea eliminar.
     * @return Se regresa el objeto eliminado.
     */
    public Object eliminar(int indice);
}
