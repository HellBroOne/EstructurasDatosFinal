package estructurasnolineales;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.*;
import estructurasLineales.auxiliares.Nodo;
import estructurasLineales.auxiliares.NodoClave;
import estructurasLineales.auxiliares.NodoDoble;
import utilerias.comunes.Comparadores;

public class ArbolBinario {
    protected NodoDoble raiz;
    public ArbolBinario(){
        raiz = null; //inicializar la raiz en nula
    }

    public boolean crearArbol(){
        SalidaPorDefecto.terminal("Dame la raiz: ");
        String info = EntradaPorDefecto.consolaCadenas();
        NodoDoble nuevoNodo = new NodoDoble(info);
        if (nuevoNodo != null){
            raiz = nuevoNodo;
            crearArbol(raiz);
            return true;
        }else{
            return false;
        }
    }

    protected void crearArbol(NodoDoble subRaiz){
        //  --------- PREGUNTAR POR EL HIJO IZQUIERDO --------
        SalidaPorDefecto.terminal("¿El nodo "+subRaiz.getInfo()+" tiene hijo izquierdo? [S/N] ");
        String respuestaI = EntradaPorDefecto.consolaCadenas();
        if (respuestaI.equalsIgnoreCase("s")){
            SalidaPorDefecto.terminal("Dame el contenido del hijo izquierdo de "+subRaiz.getInfo()+": ");
            String infoIzq = EntradaPorDefecto.consolaCadenas();
            NodoDoble nuevoNodoIzq = new NodoDoble(infoIzq);
            if (nuevoNodoIzq != null){
                subRaiz.setLigaIzq(nuevoNodoIzq);
                crearArbol(nuevoNodoIzq);
            }
        }
        //  --------- PREGUNTAR POR EL HIJO DERECHO ---------
        SalidaPorDefecto.terminal("¿El nodo "+subRaiz.getInfo()+" tiene hijo derecho? [S/N] ");
        String respuestaD = EntradaPorDefecto.consolaCadenas();
        if (respuestaD.equalsIgnoreCase("s")){
            SalidaPorDefecto.terminal("Dame el contenido del hijo derecho de "+subRaiz.getInfo()+": ");
            String infoDer = EntradaPorDefecto.consolaCadenas();
            NodoDoble nuevoNodoDer = new NodoDoble(infoDer);
            if (nuevoNodoDer != null){
                subRaiz.setLigaDer(nuevoNodoDer);
                crearArbol(nuevoNodoDer);
            }
        }

    }

    //INVOCACIONES A METODOS DE RECORRIDO DESDE METODOS PUBLICOS (METODOS PUENTE)
    public void innorden(){
        innorden(raiz);
    }
    public void preorden(){
        preorden(raiz);
    }
    public void postorden(){
        postorden(raiz);
    }

    //metodos de recorrido ( o_o")
    private void innorden(NodoDoble subRaiz){ //(IRD)
        if (subRaiz != null){
            innorden(subRaiz.getLigaIzq()); //obtnener la liga izquierda.
            SalidaPorDefecto.terminal(subRaiz.getInfo()+" "); //imprimir la raiz
            innorden(subRaiz.getLigaDer()); //obtnener la liga derecha.
        } //si la raiz es nula, no va a hacer nada
    }
    private void preorden(NodoDoble subRaiz){ //(RID)
        if (subRaiz != null){
            SalidaPorDefecto.terminal(subRaiz.getInfo()+" "); //imprimir la raiz
            preorden(subRaiz.getLigaIzq()); //obtnener la liga izquierda.
            preorden(subRaiz.getLigaDer()); //obtnener la liga derecha.
        } //si la raiz es nula, no va a hacer nada
    }
    private void postorden(NodoDoble subRaiz){ //(IDR)
        if (subRaiz != null){
            postorden(subRaiz.getLigaIzq()); //obtnener la liga izquierda.
            postorden(subRaiz.getLigaDer()); //obtnener la liga derecha.
            SalidaPorDefecto.terminal(subRaiz.getInfo()+" "); //imprimir la raiz
        } //si la raiz es nula, no va a hacer nada
    }

    /**
     * Metodo que inicializa al metodo de<br/>
     * la altura del arbol con ciertas <b>variables<b/>.
     * @return Regresa el valor de la altura.
     */
    public int alturaArbol(){
        return alturaArbol(raiz, 0);
    }

    /**
     * Metodo privado que hace la recursividad<br/>
     * para obtener la altura.
     * @param subraiz Nodo subraiz a verificar.
     * @param contador Contador de la altura.
     * @return Regresa el valor de la altura del arbol.
     */
    private int alturaArbol(NodoDoble subraiz, int contador){
        if (subraiz != null){
            int alturaDerecha = alturaArbol(subraiz.getLigaDer(), contador+1);
            int alturaIzquierda = alturaArbol(subraiz.getLigaIzq(), contador+1);
            if (alturaIzquierda > alturaDerecha){
                return alturaIzquierda;
            } else {
                return alturaDerecha;
            }
        }
        return contador;
    }

    /**
     * Metodo que manda a llamar al metodo recursivo<br/>
     * que devuelve la altura del nodo solicitado.
     * @param info Info a buscar.
     * @return Regresa la altura donde esta.
     */
    public int buscarAltura(Object info){
        return buscarAltura(raiz, info, 1);
    }

    /**
     * Metodo que busca la altura de un nodo de manera recursiva.
     * @param subraiz Subraiz actual a buscar.
     * @param info Informacion a buscar.
     * @param contador Indicador de la altura.
     * @return Regresa el numero de la altura.
     */
    private int buscarAltura(NodoDoble subraiz, Object info, int contador){
        if (subraiz != null){
            if (subraiz.getInfo().toString().equalsIgnoreCase(info.toString()) == false){
                int resultadoDer = buscarAltura(subraiz.getLigaDer(), info, contador+1);
                int resultadoIzq = buscarAltura(subraiz.getLigaIzq(), info, contador+1);
                if (resultadoDer != -1){
                    return resultadoDer;
                } else if (resultadoIzq != -1) {
                    return resultadoIzq;
                } else {
                    return -1;
                }
            } else {
                return contador;
            }
        } else {
            return -1;
        }
    }

    /**
     * Metodo que imprime cuantos nodos hay en todos los niveles.
     */
    public void elementosPorNivel(){
        ListaDinamicaClave nodos = elementosPorNivelEnLista();
        int nivel = 1;
        while (nivel <= alturaArbol()){
            int cantidadNodos = (int) nodos.obtener(nivel);
            if (cantidadNodos == 1){
                SalidaPorDefecto.terminal("\nNivel "+nivel+": "+cantidadNodos+" elemento.");
            }else{
                SalidaPorDefecto.terminal("\nNivel "+nivel+": "+cantidadNodos+" elementos.");
            }
            nivel++;
        }
    }

    /**
     * Metodo que obtiene la cantidad de nodos<br/>
     * y los va a guardar en una lista Clave.<br/>
     * En claves para buscar de manera (nivel): numerodeNodos.
     * @return Regresa la lista de los nodos.
     */
    private ListaDinamicaClave elementosPorNivelEnLista(){
        ListaDinamicaClave elementos = new ListaDinamicaClave();
        int nivel = 1;
        int resultado = buscarElementosNivel(raiz, nivel+1, 0, elementos);
        elementos.agregar(nivel, resultado);
        return elementos;
    }

    /**
     * Metodo recursivo que va a guardar la cantidad de <br/>
     * nodos por subniveles en la lista anterior.
     * @param subraiz Subraiz Actual.
     * @param nivel Nivel actual del arboles.
     * @param cantidad Cantidad de nodos actuales.
     * @param nodos Lista de los nodos.
     * @return Regresa la cantidad del nodo si hay.
     */
    private int buscarElementosNivel(NodoDoble subraiz, int nivel, int cantidad, ListaDinamicaClave nodos){
        if (subraiz != null){
            cantidad++;
            int ramaIzq = buscarElementosNivel(subraiz.getLigaIzq(), nivel+1, 0, nodos);
            int ramaDer = buscarElementosNivel(subraiz.getLigaDer(), nivel+1, 0, nodos);
            int total = ramaIzq + ramaDer;
            if (total != 0){
                Object numeroElementos = nodos.buscar(nivel);
                if (numeroElementos == null){
                    nodos.agregar(nivel, total);
                } else {
                    nodos.cambiar(nivel, total+(int)numeroElementos);
                }
            }
        }
        return cantidad;
    }

    /**
     * Metodo que manda a llamar al metodo<br/>
     * que verifica que tipo de nodo es.
     * @param info Informacion del nodo a buscar.
     */
    public void tipoNodo(Object info){
        queEs(info, raiz, raiz);
    }

    /**
     * Metodo que verifica a un nodo y devuelve que es.<br/>
     * Ademas de decir cual es el padre. Si no se encuentra,<br/>
     * no imprime nada.
     * @param info Informacion a buscar.
     * @param subraiz Subraiz a verificar.
     * @param anterior Nodo anterior al actual.
     */
    private void queEs(Object info, NodoDoble subraiz, NodoDoble anterior){
        if (subraiz != null){
            if (subraiz.getInfo().toString().equalsIgnoreCase(info.toString())){ // se encontro
                if (subraiz.getLigaDer() == null && subraiz.getLigaIzq() == null){
                    SalidaPorDefecto.terminal("El nodo "+info+" es hoja. El nodo "+anterior.getInfo()+" es el nodo padre.");
                }else if (subraiz == raiz){
                    SalidaPorDefecto.terminal("El nodo "+info+" es la raiz. No tiene padre.");
                } else if (subraiz.getLigaDer() != null || subraiz.getLigaIzq() != null){
                    SalidaPorDefecto.terminal("El nodo "+info+" es intermedio. El nodo "+anterior.getInfo()+" es el nodo padre.");
                }
            } else {
                queEs(info, subraiz.getLigaIzq(), subraiz);
                queEs(info, subraiz.getLigaDer(), subraiz);
            }
        }
    }

    /**
     * Metodo que verifica si el nodo actual tiene un hermano en el arbol.
     * @param info Informacion a buscar del nodo.
     */
    public void tieneHermano(Object info){
        tieneHermano(info, raiz, raiz, "Raiz");
    }

    /**
     * Metodo que verifica si el nodo tiene hermano.
     * @param info Info a buscar.
     * @param subRaiz Nodo actual.
     * @param padre Nodo anterior o padre.
     * @param queLiga Identificador que dice si la<br/>
     *                llamada actual fue a la derecha o izquerda.
     */
    private void tieneHermano(Object info, NodoDoble subRaiz, NodoDoble padre, String queLiga){
        if (subRaiz != null){
            if (subRaiz.getInfo().toString().equalsIgnoreCase(info.toString())){
                if (queLiga.equalsIgnoreCase("Derecha") == true){
                    NodoDoble hermano = padre.getLigaIzq();
                    if (hermano != null){
                        SalidaPorDefecto.terminal("El hermano de "+subRaiz.getInfo()+" es "+hermano.getInfo());
                    } else {
                        SalidaPorDefecto.terminal("El Nodo "+subRaiz.getInfo()+" no tiene hermano.");
                    }
                } else if (queLiga.equalsIgnoreCase("Izquierda") == true){
                    NodoDoble hermano = padre.getLigaDer();
                    if (hermano != null){
                        SalidaPorDefecto.terminal("El hermano de "+subRaiz.getInfo()+" es "+hermano.getInfo());
                    } else {
                        SalidaPorDefecto.terminal("El Nodo "+subRaiz.getInfo()+" no tiene hermano.");
                    }
                } else { //es raiz
                    SalidaPorDefecto.terminal("El Nodo "+subRaiz.getInfo()+" es la raiz.");
                }
            }
            tieneHermano(info, subRaiz.getLigaDer(), subRaiz, "Derecha");
            tieneHermano(info, subRaiz.getLigaIzq(), subRaiz, "Izquierda");
        }
    }

    /**
     * Metodo que devuelve el nodo de la raiz del arbol.
     * @return Regresa la raiz.
     */
    public NodoDoble inicializarNodo() {
        NodoDoble iterador = raiz;
        return iterador;
    }

    /**
     * Metodo que invoca al postorden pero ciclico.
     */
    public void postordenCiclico(){
        PilaDinamica salida = postordenIterativo(raiz);
        while (salida.vacio() == false){
            NodoDoble actual = (NodoDoble) salida.quitar();
            SalidaPorDefecto.terminal(actual+" ");
        }
    }

    /**
     * Tecnicamente es el mismo metodo de recorrido por ancho,<br/>
     * pero devuelve el resultado en una pila. La cual tendra<br/>
     * a los elementos ya "ordenados".
     * @param subraiz Subraiz actual a agregar.
     * @return Regresa la pila en orden posterior.
     */
    private PilaDinamica postordenIterativo(NodoDoble subraiz){
        PilaDinamica pila = new PilaDinamica();
        PilaDinamica salida = new PilaDinamica();
        pila.poner(subraiz);
        while (pila.vacio() == false){
            NodoDoble actual = (NodoDoble) pila.quitar();
            salida.poner(actual);
            if (actual.getLigaIzq()!= null){
                pila.poner(actual.getLigaIzq());
            }
            if (actual.getLigaDer()!= null){
                pila.poner(actual.getLigaDer());
            }
        }
        return salida;
    }


    /**
     * Metodo que manda a llamar al recorrido por anchura.
     */
    public void amplitud(){
       amplitud(raiz);
    }

    /**
     * Metodo que recibe la raiz como amplitud, no es recursivo.
     * @param subraiz Subraiz del nodo actual.
     */
    private void amplitud(NodoDoble subraiz){
        ColaDinamica cola = new ColaDinamica();
        cola.poner(subraiz);
        while(cola.vacio() == false){
            NodoDoble actual = (NodoDoble) cola.quitar();
            SalidaPorDefecto.terminal(actual+" ");
            if (actual.getLigaIzq()!= null){
                cola.poner(actual.getLigaIzq());
            }
            if (actual.getLigaDer()!= null){
                cola.poner(actual.getLigaDer());
            }
        }
    }

    /**
     * Metodo que manda a llamar al recorrido por anchura,<br/>
     * pero hey! ahora con pilas!
     */
    public void amplitudPilesca(){
        amplitudPilesca(raiz);
    }

    /**
     * Metodo que recibe la raiz como amplitud, no es recursivo.<br/>
     * Y ahora con pilas!!!
     * @param subraiz Subraiz del nodo actual.
     */
    private void amplitudPilesca(NodoDoble subraiz){
        PilaDinamica pila = new PilaDinamica();
        pila.poner(subraiz);
        while(pila.vacio() == false){
            NodoDoble actual = (NodoDoble) pila.quitar();
            SalidaPorDefecto.terminal(actual+" ");
            if (actual.getLigaIzq()!= null){
                pila.poner(actual.getLigaIzq());
            }
            if (actual.getLigaDer()!= null){
                pila.poner(actual.getLigaDer());
            }
        }
    }

}