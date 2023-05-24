package estructurasLineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.auxiliares.Nodo;
import estructurasnolineales.Matriz2;
import utilerias.comunes.Comparadores;
import utilerias.comunes.TipoOrden;

/**
 * Clase que implementa un TDA Lista Dinamica Ordenada
 */
public class ListaDinamicaOrdenada extends ListaDinamica{
    protected TipoOrden orden;
    public ListaDinamicaOrdenada(TipoOrden orden){
        super();
        this.orden = orden;
    }

    /**
     * Metodo que verifica si la lista esta vacia.
     * @return True si esta vacia, false si no.
     */
    @Override
    public boolean vacia() {
        return super.vacia();
    }

    /**
     * Metodo que busca un valor en el rango de primero < info < ultimo
     * @param info Valor a buscar.
     * @return Regresa una lista con la info del nodo si se encuentra, si no,
     * se regresa una lista donde deberia de estar.
     */
    public Object buscar(Object info){
        if (vacia() == false){ //siempre y cuando haya que buscar
            Nodo recorrido = primero;
            Nodo anterior = null;
            int posicion = 0;
            while ( (recorrido != null)&&(info.toString().equalsIgnoreCase(recorrido.toString()) == false) ) { //mientras el recorrido no sea nulo
                if (orden.getNumeroOrden() == 1) { //Si es incremental
                    if (Comparadores.comparar(info, recorrido.getInfo()) < 0) { //si la info del actual es menor, entonces se sabe que el anterior era este
                        ListaEstatica nodos = new ListaEstatica(2);
                        if (anterior != null) { //si el anterior es diferente de null
                            nodos.agregar(anterior.getInfo());
                        } else {
                            nodos.agregar(null);
                        }
                        nodos.agregar(posicion * (-1));
                        return nodos;
                    } else {
                        anterior = recorrido;
                        recorrido = recorrido.getLigaDer();
                        posicion++;
                    }
                } else { //Si es decremental
                    if (Comparadores.comparar(info, recorrido.getInfo()) > 0) { //si la info del actual es mayor, entonces se sabe que el anterior era este
                        ListaEstatica nodos = new ListaEstatica(2);
                        if (anterior != null) { //si el anterior es diferente de null
                            nodos.agregar(anterior.getInfo());
                        } else {
                            nodos.agregar(null);
                        }
                        nodos.agregar(posicion * (-1));
                        return nodos;
                    } else {
                        anterior = recorrido;
                        recorrido = recorrido.getLigaDer();
                        posicion++;
                    }

                }
            }
            if (recorrido == null){ //si llego al final (null)
                return null;
            } else { //lo encontro!
                ListaEstatica nodos = new ListaEstatica(2);
                nodos.agregar(recorrido.getInfo());
                nodos.agregar(posicion);
                return nodos;
            }
        } else { //si esta vacia
            return null;
        }
    }

    /**
     * Metodo que agrega un objeto de forma ordenada.
     * @param info Es el nuevo valor a añadir.
     * @return regresa el metodo a agregar
     */
    @Override
    public int agregar(Object info) {
        if (vacia() == true){ //si no hay ningun nodo
               return super.agregarInicio(info);
        } else if (primero == ultimo){ //si solo hay uno
            if (orden.getNumeroOrden() == 1){ //si son incrementales
                if (Comparadores.comparar(info, primero.getInfo()) > 0){ //si es mayor se agrega al final
                    return super.agregar(info);
                } else { //si es menor, al inicio
                    return super.agregarInicio(info);
                }
            } else { // si es decremental
                if (Comparadores.comparar(info, primero.getInfo()) < 0){ //si es mayor se agrega al final
                    return super.agregar(info);
                } else { //si es menor, al inicio
                    return super.agregarInicio(info);
                }
            }
        } else { //si hay elementos en medio
            Nodo actual = primero;
            Nodo anterior = null;
            while (actual != null){
                if (orden.getNumeroOrden() == 1){
                    if (Comparadores.comparar(info, actual.getInfo()) < 0){ //si es menor al actual
                        if (anterior != null){
                            Nodo nuevoNodo = new Nodo(info);
                            anterior.setLigaDer(nuevoNodo);
                            nuevoNodo.setLigaDer(actual);
                            //ultimo = nuevoNodo; la neta hacer esto de cambiar los ultimos fue una tonteria ._.
                        } else {
                            super.agregarInicio(info);
                        }
                        return 1;
                    }
                }else{ // SI ES DECREMENTAL
                    if (Comparadores.comparar(info, actual.getInfo()) > 0){ //si es mayor al actual
                        if (anterior != null){
                            Nodo nuevoNodo = new Nodo(info);
                            anterior.setLigaDer(nuevoNodo);
                            nuevoNodo.setLigaDer(actual);
                        } else {
                            super.agregarInicio(info);
                        }
                        return 1;
                    }
                }
                anterior = actual;
                actual = actual.getLigaDer();
            }
            //if (Comparadores.comparar(info, anterior.getInfo()) != 0){
            super.agregar(info); //em la verdad es que creo que no era necesario comparar el ultimo con el que se va a insertar en esta linea
            //}
            return -1;
        }
    }

    /**
     * Metodo que elimina el inicio, mejor dicho, el ultimo.
     * @return Regresa al objeto eliminado
     */
    @Override
    public Object eliminarInicio() {
        return super.eliminarInicio();
    }


    /**
     * Metodo que agrega un elemento, pero donde deba ir
     * @param info Elemento a agregar
     * @return Regresa positivo si se pudo agregar.
     */
    @Override
    public int agregarInicio(Object info) {
        return agregar(info);
    }

    /**
     * Metodo que agrega a un elemento, pero igualmente, donde deba ir
     * @param info Elemento a agregar
     * @return Regresa positivo si se pudo agregar.
     */
    public int agregarFinal(Object info){
        return agregar(info);
    }

    /**
     * Metodo que agrega una Lista Estatica a la lista actual.
     * @param lista2 Ingresar un Objeto tipo lista, si no devolvera null.
     * @return regresa true si se pudo agregar, false si no.
     */
    @Override
    public boolean agregarLista(Object lista2) {
        if (lista2 instanceof ListaEstatica){ //funciona con estaticas
            if (((ListaEstatica) lista2).vacia() == false){
                for (int cadaInfo = 0; cadaInfo< ((ListaEstatica) lista2).cantidad(); cadaInfo++){
                    agregar( ((ListaEstatica) lista2).obtener(cadaInfo) );
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina a los elementos de una lista.
     * @param lista2 Se agrega un objeto de tipo lista.
     * @return regresa true si se pudo eliminar, false si no.
     */
    @Override
    public boolean eliminarLista(Object lista2) {
        if (lista2 instanceof ListaEstatica){ //funciona con estaticas
            if (((ListaEstatica) lista2).vacia() == false){
                for (int cadaInfo = 0; cadaInfo< ((ListaEstatica) lista2).cantidad(); cadaInfo++){
                    eliminar( ((ListaEstatica) lista2).obtener(cadaInfo) );
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina los datos de una matriz 2D pasada como argumento
     * @param tabla Matriz a eliminar
     * @return Regresa True si se pudo eliminar, falso si la matriz esta vacia.
     */
    public boolean eliminarMatriz2D(Matriz2 tabla){
        if (tabla.esVacia() == false) { //si la matriz de tabla no esta vacia
            for (int cadaRenglon = 0; cadaRenglon < tabla.obtenerRenglones(); cadaRenglon++) {
                for (int cadaColumna = 0; cadaColumna < tabla.obtenerColumnas(); cadaColumna++) {
                    Object infoAgregar = tabla.obtener(cadaRenglon, cadaColumna);
                    if (infoAgregar != null) {
                        eliminar(infoAgregar);
                    }
                }
            }
            return true;
        } else{
            return false;
        }
    }

    /**
     * Metodo que cambia un elemento por otro.
     * @param indice Numero del nodo en la lista.
     * @param info Info a cambiar.
     * @return Regresa tru si se cambio correctamente.
     */
    @Override
    public boolean cambiar(int indice, Object info) {
        Object obtenido = obtener(indice);
        eliminar(obtenido);
        agregar(info);
        return true;
    }

    /**
     * Metodo que cambia un objeto de la lista un cierto numero de veces
     * @param infoViejo Se recibe un Elemento para buscar, el cual sera modificado.
     * @param infoNuevo Se modifica un Elemento viejo para modificar.
     * @param numVeces Numero de veces a eliminar.
     * @return regresa true si se pudo cambiar al menos uno.
     */
    @Override
    public boolean cambiar(Object infoViejo, Object infoNuevo, int numVeces) {
        int contador_veces = 0;
        for (int vez = 0; vez<numVeces; vez++){
            Object resultado = eliminar(infoViejo);
            if (resultado != null){
                agregar(infoNuevo);
                contador_veces++;
            }
        }
        if (contador_veces == 0){
            return false;
        } else {
            return true;
        }
    }


}
