package estructurasnolineales;

import estructurasLineales.ColaDinamica;
import estructurasLineales.PilaDinamica;
import estructurasLineales.auxiliares.NodoDoble;
import utilerias.comunes.Comparadores;
import utilerias.comunes.TipoOrden;

/**
 * Clase que implementa un TDA Monticulo/Heap.
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */
public class Monticulo extends ArbolBinario{
    protected TipoOrden orden;

    /**
     * Constructor del TDA Monticulo, solo recibe el tipo de orden.
     * @param orden Tipo Enumerado Orden que indica el orden del Monticulo.
     */
    public Monticulo(TipoOrden orden){
        super();
        this.orden = orden;
    }

    /**
     * Metodo que permite agregar un nodo al actual arbol.
     * @param info Info a agregar.
     */
    public void agregar(Object info){
        NodoDoble nuevoNodo = new NodoDoble(info);
        if (nuevoNodo != null) { //hay memoria
            if (raiz == null){
                raiz = nuevoNodo;
            }else{
                agregaNodo(nuevoNodo, raiz);
            }
        }
    }

    /**
     * Metodo privado que hace la agregacion del nodo en donde le corresponde.<br/>
     * Tecnicamente hace un recorrido por Amplitud.
     * @param nuevoNodo Nuevo nodo a insertar.
     * @param subRaiz Subraiz actual.
     */
    private void agregaNodo(NodoDoble nuevoNodo, NodoDoble subRaiz){ //postorden?? no, por anchura.
        ColaDinamica cola = new ColaDinamica();
        PilaDinamica pila = new PilaDinamica(); //pila de nodos ya visitados (todos)
        cola.poner(subRaiz);
        while (cola.vacio() == false){
            NodoDoble actual = (NodoDoble) cola.quitar();
            pila.poner(actual);
            if (actual.getLigaIzq() != null){
                cola.poner(actual.getLigaIzq());
            } else {
                actual.setLigaIzq(nuevoNodo);
                organizarNodo(nuevoNodo);
                break;
            }
            if (actual.getLigaDer() != null){
                cola.poner(actual.getLigaDer());
            } else {
                actual.setLigaDer(nuevoNodo);
                organizarNodo(nuevoNodo);
                break;
            }
        }
    }

    /**
     * Metodo privado que va ordenando al nodo para<br/>
     * darle estructura de Monticulo.
     * @param actual Nodo actual a ordenar.
     */
    private void organizarNodo(NodoDoble actual){ //usar una cola? no, hacer metodo obtener padre
        NodoDoble nodoPadre = obtenerPadre(actual, raiz);
        if (nodoPadre != null){
            if (orden == TipoOrden.INC && Comparadores.comparar(actual.getInfo(), nodoPadre.getInfo()) > 0){ //se hace el intercambio
                Object auxiliar = nodoPadre.getInfo();
                nodoPadre.setInfo(actual.getInfo());
                actual.setInfo(auxiliar);
                organizarNodo(nodoPadre);
            }
            if (orden == TipoOrden.DEC && Comparadores.comparar(actual.getInfo(), nodoPadre.getInfo()) < 0){ //se hace el intercambio
                Object auxiliar = nodoPadre.getInfo();
                nodoPadre.setInfo(actual.getInfo());
                actual.setInfo(auxiliar);
                organizarNodo(nodoPadre);
            }
        }
    }

    /**
     * Metodo privado que busca al padre del nodo pasado como argumento.
     * @param actual Nodo actual a buscar
     * @param subRaiz Nodo que se va a ir iterando hasta encontrar al padre.
     * @return Regresa un nodo padre o el nulo, si no tiene padre (Casi siempre con la raiz)
     */
    private NodoDoble obtenerPadre(NodoDoble actual, NodoDoble subRaiz){
        NodoDoble nodoIzquierda = null;
        NodoDoble nodoDerecha = null;
        if (actual == raiz || subRaiz == null){
            return null;
        } else {
            if (subRaiz.getLigaIzq() == actual || subRaiz.getLigaDer() == actual){
                return subRaiz;
            } else {
                nodoIzquierda = obtenerPadre(actual, subRaiz.getLigaIzq());
                nodoDerecha = obtenerPadre(actual, subRaiz.getLigaDer());
            }
            if (nodoIzquierda != null){
                return nodoIzquierda;
            }
            return nodoDerecha;
        }
    }

    /**
     * Metodo que permite obtener el info del ultimo nodo.
     * @return Regresa la info del ultimo nodo.
     */
    public Object obtenerUltimoInfo(){
       NodoDoble ultimo = obtenerUltimoNodo(raiz);
       return ultimo.getInfo();
    }

    /**
     * Metodo que recorre por amplitud y devuelve en NodoDoble<br/>
     * al ultimo nodo en el arbol.
     * @param subRaiz Subraiz a procesar (Siempre la raiz, por eso es<br/>
     *                privado).
     * @return Regresa el Nodo insertado al ultimo.
     */
    private NodoDoble obtenerUltimoNodo(NodoDoble subRaiz){
        ColaDinamica cola = new ColaDinamica();
        cola.poner(subRaiz);
        NodoDoble actual = null;
        while(cola.vacio() == false){
            actual = (NodoDoble) cola.quitar();
            if (actual.getLigaIzq()!= null){
                cola.poner(actual.getLigaIzq());
            }
            if (actual.getLigaDer()!= null){
                cola.poner(actual.getLigaDer());
            }
        }
        return actual;
    }

    /**
     * Metodo que permite ir eliminando a la raiz.
     * @return Regresa el elemento de la raiz como respaldo.
     */
    public Object eliminar(){
        //1.- Se reemplaza la raíz con el elemento que ocupa la última posición del montículo.
        NodoDoble ultimo = obtenerUltimoNodo(raiz);
        Object respaldo = null;
        if (ultimo != raiz){ //el nodo a reemplazar es diferente a la raiz
            NodoDoble padre = obtenerPadre(ultimo, raiz);
            respaldo = raiz.getInfo();
            this.raiz.setInfo(ultimo.getInfo());
            if (padre.getLigaIzq() == ultimo){
                padre.setLigaIzq(null);
            } else {
                padre.setLigaDer(null);
            }
            NodoDoble hijomayor = obtenerHijoMayor(raiz);//ver el hijo el actual nodo.
            reemplazar(raiz, hijomayor);
            return respaldo; //si devuelve bien la raiz
        } else { //eliminar la raiz.
            raiz = null;
        }
        /* 2.- Se verifica si el valor de la raíz es menor que el valor más grande de sus hijos. (ciclo)
           Si se cumple la condición se efectúa el intercambio, si no se cumple, entonces el algoritmo se
           detiene y el elemento queda ubicado en su posición correcta en el montículo. */
        return respaldo;
    }

    /**
     * Metodo que reemplaza a los nodos dependiendo de como sean ordenados.
     * @param actual Nodo actual, por lo general el padre.
     * @param hijoMayor Hijo mayor del nodo actual.
     */
    private void reemplazar(NodoDoble actual, NodoDoble hijoMayor){
        if (hijoMayor != null){ //si no tiene hijos
            if (orden == TipoOrden.INC && Comparadores.comparar(actual.getInfo(), hijoMayor.getInfo()) < 0){ //si el actual es menor al hijo mayor, hacer el intercambio
                Object auxiliar = hijoMayor.getInfo();
                hijoMayor.setInfo(actual.getInfo());
                actual.setInfo(auxiliar);
                NodoDoble nuevoMayor = obtenerHijoMayor(hijoMayor);
                reemplazar(hijoMayor, nuevoMayor);
            }
            if (orden == TipoOrden.DEC && Comparadores.comparar(actual.getInfo(), hijoMayor.getInfo()) > 0){ //si el actual es menor al hijo mayor, hacer el intercambio
                Object auxiliar = hijoMayor.getInfo();
                hijoMayor.setInfo(actual.getInfo());
                actual.setInfo(auxiliar);
                NodoDoble nuevoMayor = obtenerHijoMayor(hijoMayor);
                reemplazar(hijoMayor, nuevoMayor);
            }
        }
    }

    /**
     * Metodo que simplemente obtiene el hijo mayor de un nodo.
     * @param subraiz Nodo actual a cual comparar
     * @return Regresa el hijo mayor.
     */
    private NodoDoble obtenerHijoMayor(NodoDoble subraiz){
        if (subraiz.getLigaDer() != null && subraiz.getLigaIzq() != null){
            if (Comparadores.comparar(subraiz.getLigaIzq().getInfo(), subraiz.getLigaDer().getInfo()) > 0){ //significa que el de la izquierda es mayor
                return subraiz.getLigaIzq();
            } else { //significa que el de la derecha fue el mayor
                return subraiz.getLigaDer();
            }
        } else {
            return null; //ni hijos tiene
        }
    }

    //Las notas para este metodo son las siguientes:
    //1. Hay que corregir que se envie el respaldo como regreso. Por que siempre manda un nulo.
    //2. Si el nodo padre es la raiz ps ahi queda... creo.
    //3. De hecho, el arbol actual queda hecho un cochinero 🤨, posiblemente se deba hacerse
    //   un intercambio, pero sin modificar al ultimo mas que una vez,
}
