package estructurasLineales;

/**
 * Esta clase contiene los métodos de un TDA Lista.
 * @version 1.0
 * @author Gerardo
 */
public interface Lista {
    /**
     * Este método regresa un indicador de si la Lista esta vacia.
     *
     * @return Regresa <b>true</b> si esta vacia. <b>False</b> en caso contrario.
     */
    public boolean vacia();

    /**
     * Este metodo añade un elemento al final de la Lista.
     *
     * @param info Es el nuevo valor a añadir.
     * @return Regresa a posicion en donde lo agrega o -1 en caso contrario.
     */
    public int agregar(Object info);

    /**
     * Este metodo imprime la Lista que mande llamarlo.
     * De orden mediante el cual se fueron agregando a la
     */
    public void imprimir();

    /**
     * Metodo util que sirve al momento de imprimir una cadena al reves.
     */
    public void imprimirOI();

    /**
     * Buscar entra en la lista y mediante el parametro info busca un valor
     * que coincida con el solicitado y si lo encuentra, devuelve la posicion del
     * valor encontrado.
     * @param info Valor a buscar.
     * @return Devualve la posicion del valor solicitado, si no lo encuentra, devuelve -1.
     */
    public Object buscar(Object info);

    /**
     * Se elimina un elemento de la Lista, siempre cuando exista y se recorren
     * @param info Objeto que se desea eliminar.
     * @return Devuelve el objeto Eliminado
     */
    public Object eliminar(Object info);

    /**
     * Indica si la lista es igual la lista2, y a su vez valida que lista2 sea una ListaEstatica.
     *
     * @param lista2 recibe un parametro de objeto que debe ser un tipo ListaEstatica.
     * @return regresa true si lista2 es igual a la otra lista, de lo contrario devolvera false.
     */
    public boolean esIgual(Object lista2);

    /**
     * Este metodo modifica un elemento viejo por el elemento nuevo haciendo una busqueda en si,
     *
     * @param infoViejo Se recibe un Elemento para buscar, el cual sera modificado.
     * @param infoNuevo Se modifica un Elemento viejo para modificar.
     * @param numVeces
     * @return Regresa verdadero si se ha hecho alguna modificacion
     */
    public boolean cambiar(Object infoViejo, Object infoNuevo, int numVeces);

    /**
     * Busca dentro de un arreglo los elementos indicados por el parametro info.
     * @param info elementos a buscar en info
     * @return Devuelve un arreglo que devuelve las posiciones de todas  las
     * ocurrencias del elemento indicado.
     */
    public ListaEstatica buscarValores(Object info);

    /**
     * Se regresa el objeto de la ultima posicion del arreglo
     * @return Regresa el ultimo objeto de una Lista.
     */
    public Object eliminar();

    /**
     * Vacia todos los elemento de un arreglo.
     */
    public void vaciar();

    /**
     * Metodo que agrega una lista dada en los parametros al
     * final de la que se invoca
     * @param lista2 Ingresar un Objeto tipo lista, si no devolvera null.
     * @return Regresa True si la adicion fue exitosa, de lo contrario regresa False.
     */
    public boolean agregarLista(Object lista2);

    /**
     * Metodo que invierte la lista.
     */
    public void invertir();

    /**
     * Cuenta cuantas instancias u ocurrencias de el objeto que se le pasa en los parametros
     * hay en el arreglo que lo invoca.
     * @param info Se pasa el objeto que se desea buscar
     * @return Se regresa el numero de las instancias que se contaron.
     */
    public int contar(Object info);

    /**
     * Se eliminan objetos que esten en la lista2 pasada por parametros
     * y que coincidan en la lista que mando a llamar al metodo.
     * @param lista2 Se agrega un objeto de tipo lista.
     * @return Se regresa true, si la eliminacion fue correcta, de lo contrario, se regresa false
     * si no fue exitosa o si el parametro lista2 no es lista.
     */
    public boolean eliminarLista(Object lista2);

    /**
     * Rellena una lista con un objeto, la cantidad de veces que se especifique.
     * @param info Objeto que se desea llenar en la array.
     * @param cantidad Cantidad de veces que se desea llenar en la array.
     */
    public void rellenar(Object info, int cantidad);

    /**
     * Clona una lista.
     * @return Se regresa una lista clonada.
     */
    public Lista clonar();

    /**
     * Obten una lista nueva desde el parametro de indice Inicial hasta el
     * parametro de indice Final.
     * @param indiceInicial Indice desde donde se empieza la lista.
     * @param indiceFinal Indice desde donde se finaliza la lista.
     * @return Se regresa una Lista nueva.
     */
    public Lista subLista(int indiceInicial, int indiceFinal);

    /**
     * Redimensiona una lista ya existente, a un tamanio nuevo.
     * @param maximo Tamanio nuevo de la lista.
     * @return Regresa True si si se dimensiona, y False si no se pudo.
     */
    public boolean redimensionar(int maximo);

    /**
     * Regresa el numero maximo de la lista que lo invoca.
     * @return Regresa el numero del tamanio de la lista.
     */
    public int maximo();

    /**
     * Regresa la cantidad de elementos en una array
     * @return Numero de elementos en la array.
     */
    public int cantidad();

    /**
     * Se regresa el ultimo elemento de una lista.
     * @return Elemento agregado al final.
     */
    public Object verUltimo();

    /**
     * Elimina el primer elemento de la lista
     * @return Elemento eliminado del inicio.
     */
    public Object eliminarInicio();
}
