package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.*;
import estructurasnolineales.auxiliares.Vertice;

/**
 * Clase que implementa un TDA Grafo Dinamico
 */
public class GrafoDinamico {
    protected ListaDinamica listaAdyacencia;

    public GrafoDinamico(){
        listaAdyacencia = new ListaDinamica();
    }

    private ListaDinamica buscarVerticeSublista(Object verticeBuscado){
        listaAdyacencia.inicializarIterador();
        while (listaAdyacencia.hayNodo() != false){
            ListaDinamica sublista = (ListaDinamica) listaAdyacencia.obtenerNodo();
            Vertice primerVertice = (Vertice) sublista.verPrimero();
            if (primerVertice!=null && primerVertice.toString().equalsIgnoreCase(verticeBuscado.toString()) == true){
                return sublista;
            }
        }
        return null;
    }

    public boolean agregarVertice(Object info){
        ListaDinamica sublista = buscarVerticeSublista(info);
        if (sublista != null){ //ya existe
            return false;
        } else { //no existe
            Vertice verticeNuevo = new Vertice();
            verticeNuevo.setInfo(info);
            ListaDinamica sublistaNueva = new ListaDinamica();
            int retornoSublista = sublistaNueva.agregar(verticeNuevo);
            int retorno = listaAdyacencia.agregar(sublistaNueva);
            if (retornoSublista == -1){
                return false;
            }
            if (retorno == -1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean agregarArista(Object origen, Object destino){
        return agregarArista(origen, destino, 1);
    }

    public boolean agregarArista(Object origen, Object destino, int peso){
        ListaDinamica sublistaOrigen = buscarVerticeSublista(origen);
        ListaDinamica sublistaDestino = buscarVerticeSublista(destino);
        if (sublistaOrigen != null && sublistaDestino != null){
            Vertice nuevaArista = new Vertice();
            Vertice verticeDestino = (Vertice) sublistaDestino.verPrimero();
            nuevaArista.setInfo(verticeDestino.getInfo());
            nuevaArista.setIndice(peso);
            int retorno = sublistaOrigen.agregar(nuevaArista);
            if (retorno == -1){
                return false;
            }else{
                return true;
            }
        } else {
            return false;
        }
    }

    public void imprimir(){
        listaAdyacencia.inicializarIterador();
        while (listaAdyacencia.hayNodo() == true){
            ListaDinamica subLista = (ListaDinamica) listaAdyacencia.obtenerNodo();
            subLista.imprimir();
            SalidaPorDefecto.terminal("\n");
        }
    }

    public ListaDinamica recorridoProfundidad(Object origen){
        PilaDinamica pila = new PilaDinamica();
        ListaDinamica marcados = new ListaDinamica();
        ListaDinamica recorridoSalida = new ListaDinamica();
        //1. Partir de un nodo origen, marcarlo y ponerlo en la pila
        ListaDinamica subListaOrigen = buscarVerticeSublista(origen);
        if (subListaOrigen==null){
            return null;
        }
        Vertice vetriceOrigen = (Vertice) subListaOrigen.verPrimero();
        pila.poner(vetriceOrigen);
        marcados.agregar(vetriceOrigen);
        //2. Mientras haya elementos en la pila, sacar uno
        while (pila.vacio() != true) {
            Vertice verticeActual = (Vertice) pila.quitar();
            recorridoSalida.agregar(verticeActual.getInfo());
            //3. Los vertices adyacentes al vertice sacado, mientras no esten marcados,
            // ponerlos en la pila y marcarlos
            ListaDinamica subListaVerticeActual = buscarVerticeSublista(verticeActual);
            if (subListaVerticeActual != null){
                marcarYEnpilarVerticesAdyacentes(subListaVerticeActual, marcados, pila);
            }
        }
        return recorridoSalida;
    }

    private void marcarYEnpilarVerticesAdyacentes(ListaDinamica subListaOrigen, ListaDinamica marcados, PilaDinamica pila){
        subListaOrigen.inicializarIterador();
        subListaOrigen.obtenerNodo(); //deshacerse del primero
        while (subListaOrigen.hayNodo() == true){
            Vertice verticeDestino = (Vertice) subListaOrigen.obtenerNodo();
            if (marcados.buscar(verticeDestino) == null){
                pila.poner(verticeDestino);
                marcados.agregar(verticeDestino);
            }
        }
    }

    /*
    1. U es un lugar donde se van guardando los vertices actuales
    (de donde vamos a ir guardando).
    2. u y v indica vertices a los cuales se va a ir.
       u es el vertice de origen en el conjunto U.
       y v es el vertice de destino en el conjunto V.
    3. L es un
     */

    /**
     * Metodo que permite obtener el arbol generador de Costo minimo.<br/>
     * En este caso, se devuelve la lista 'L' donde se tiene una Lista<br/>
     * Dinamica con Listas Estaticas que adentro tienen al vertice [u, v]<br/>
     * Por ejemplo se tiene la lista [ (u, v) ] siendo la lista de u, v<br/>
     * una lista estatica de tamanio de 2. Y la lista de corchetes es<br/>
     * una lista dinamica con todos las vertices actuales.
     * @return Regresa una lista dinamica con listas estaticas donde se guardan las aristas minimas.
     */
    public ListaDinamica algoritmoPrim(){
        //-2. Inicializar V con valores de los vertices de la lista de Adyacencia
        ListaDinamica vertices = (ListaDinamica) listaAdyacencia.clonar();
        //-1. Inicializar U con un vertice.
        ListaDinamica marcados = new ListaDinamica();
        marcados.agregar(vertices.verPrimero());
        marcados.inicializarIterador();
        //0. PruebaArbolesMinimosPrim
        ListaDinamica aristas = new ListaDinamica();
        //1. Mientras V (listaAdyacencia) sea diferente de U (marcados)
        while (vertices.esIgual(marcados) == false){
            //1.1 Elegir una arista (u, v) perteneciente al grafo
            //    tal que su costo sea minimo.
            ListaDinamica VmenosU = restarConjunto(marcados, vertices);
            ListaEstatica aristaMenor = obtenerVerticeMenor(marcados, VmenosU);
            //1.2 Agregar arista (u, v) a L
            aristas.agregar(aristaMenor);
            //1.3 Agregar el nodo v a U
            Vertice verticeV = (Vertice) aristaMenor.obtener(1);
            ListaDinamica listaVertice = buscarVerticeSublista(verticeV);
            marcados.agregar(listaVertice);
        }
        return aristas;
    }

    /**
     * Este metodo ni se usa jajaja.
     * El punto era solo agregar los puros vertices a una lista nueva.
     * @param listaVertices Lista de vertices a los que se van a
     *                      agregar los vertices actuales.
     */
    private void agregarTodosNodosALista(ListaDinamica listaVertices){
        listaAdyacencia.inicializarIterador();
        while (listaAdyacencia.hayNodo() != false){
            ListaDinamica sublista = (ListaDinamica) listaAdyacencia.obtenerNodo();
            Vertice verticeActual = (Vertice) sublista.verPrimero();
            listaVertices.agregar(verticeActual);
        }
    }

    /**
     * Metodo que, a partir de dos conjuntos, permite deducir la arista de menor costo.
     * @param conjuntoU Conjunto U (es decir el conjunto de vertices marcadas).
     * @param conjuntoV Conjunto V (es decir el conjunto de vertices de destino).
     * @return Regresa una lista Estatica tamanio 2 con [u, v] en ella misma.
     */
    private ListaEstatica obtenerVerticeMenor(ListaDinamica conjuntoU, ListaDinamica conjuntoV){
        // La idea es obtener cada vertice del conjuntoU y ver a cual arista se relaciona mejor del conjunto V.
        conjuntoU.inicializarIterador();
        int menor = Integer.MAX_VALUE;
        Vertice menorOrigen = null;
        Vertice menorDestino = null;

        Vertice actualOrigen = null;
        Vertice actualDestino = null;
        while (conjuntoU.hayNodo() != false){
            ListaDinamica sublista = (ListaDinamica) conjuntoU.obtenerNodo();
            actualOrigen = (Vertice) sublista.verPrimero();
            sublista.inicializarIterador();
            sublista.obtenerNodo(); //evitamos el primer nodo
            while (sublista.hayNodo() != false){
                actualDestino = (Vertice) sublista.obtenerNodo();
                if (verificarSiYaestaMarcado(conjuntoU, actualDestino)){
                    if (actualDestino.getIndice() < menor){
                        menor = actualDestino.getIndice();
                        menorOrigen = actualOrigen;
                        menorDestino = actualDestino;
                    }
                }
            }
        }
        if (menorOrigen != null && menorDestino != null){
            ListaEstatica arista = new ListaEstatica(2);
            arista.agregar(menorOrigen);
            arista.agregar(menorDestino);
            return arista;
        } else {
            return null;
        }
    }

    /**
     * Metodo que permite obtener V - U. Parte del paso 1.1
     * @param conjuntoU Conjunto U (marcados).
     * @param conjuntoV Conunto V (Todos los vertices).
     * @return Regresa una nueva Lista Dinamica con los vertices eliminados de U en V.
     */
    private ListaDinamica restarConjunto(ListaDinamica conjuntoU, ListaDinamica conjuntoV){
        ListaDinamica retorno = (ListaDinamica) conjuntoV.clonar();
        retorno.eliminarLista(conjuntoU);
        return retorno;
    }

    /**
     * Metodo que verifica si un vertice ya ha sido marcado. (Evita bucles)
     * @param marcados Vertices ya marcados o ya visitados.
     * @param destino Vertice de destino a verificar.
     * @return Regresa true si ya ha sido visitado, false si no.
     */
    private boolean verificarSiYaestaMarcado(ListaDinamica marcados, Vertice destino){
        ListaDinamica obtenidaDestino = buscarVerticeSublista(destino);
        if (marcados.buscar(obtenidaDestino) == null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que permite imprimir el costo minimo.<br/>
     * Simplemente imprime la lista generada por el algoritmo de prim.
     */
    public void imprimirCostoMinimo(){
        ListaDinamica prim = algoritmoPrim();
        if (prim != null){
            prim.inicializarIterador();
            SalidaPorDefecto.terminal("[ ");
            while (prim.hayNodo() == true){
                ListaEstatica aristaActual = (ListaEstatica) prim.obtenerNodo();
                SalidaPorDefecto.terminal("("+aristaActual.obtener(0)+", "+aristaActual.obtener(1)+")");
                if (aristaActual != prim.verUltimo()){
                    SalidaPorDefecto.terminal(", ");
                }
            }
            SalidaPorDefecto.terminal(" ]");
        }
    }
}
