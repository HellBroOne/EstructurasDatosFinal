package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.auxiliares.Nodo;
import estructurasLineales.auxiliares.NodoDoble;

import javax.naming.event.ObjectChangeListener;

/**
 * Clase que implementa un TDA de una Lista Dinamica y Doble <br/>
 * a parte que esta lista es no lineal.
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */
public class ListaDobleNoLinear {
    protected NodoDoble inicio; //Nodo del inicio
    protected NodoDoble penultimo;
    protected NodoDoble fin; //Nodo del final
    protected int posicionActual;

    /**
     * Constructor de la clase, por defecto<br/>
     * inicializa los nodos de la lista en nulo.
     */
    public ListaDobleNoLinear(){
        this.inicio = null;
        this.penultimo = null;
        this.fin = null;
        this.posicionActual = 1;
    }

    /**
     * Metodo que verifica si la lista esta vacia.
     * @return
     */
    public boolean vacia(){
        if (inicio == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que inserta un nodo con una info al final.
     * @param info Info a agregar.
     * @return Regresa la posicion del nodo agregado
     */
    public int agregar(Object info){
        NodoDoble nuevoNodo = new NodoDoble(info);
        if (nuevoNodo != null){
            if (vacia() == true){ //si la lista esta vacia
                inicio = nuevoNodo;
                penultimo = nuevoNodo;
                fin = nuevoNodo;
                posicionActual++;
            } else {
                if (posicionActual%2 == 0) { //si el nuevo nodo es par
                    if (posicionActual == 2){
                        nuevoNodo.setLigaIzq(inicio);
                        fin.setLigaIzq(nuevoNodo); // la liga izq es como si fuera la liga de abajo
                        penultimo = fin;
                        fin = nuevoNodo;
                        posicionActual++; //ahora la posicion vale +1
                    } else {
                        penultimo.setLigaDer(nuevoNodo); //se enlaza la liga del par al siguiente
                        fin.setLigaIzq(nuevoNodo); //se enlaza la liga del ultimo (impar) a la del ultimo
                        nuevoNodo.setLigaIzq(fin);
                        penultimo = fin;
                        fin = nuevoNodo;
                        posicionActual++; //ahora la posicion vale +1
                    }
                }else{ //si el nuevo nodo es impar
                    penultimo.setLigaDer(nuevoNodo);
                    penultimo = penultimo.getLigaIzq(); //el de abajo es el nuevo penultimo
                    fin = nuevoNodo; //el nuevo nodo es el nuevo final
                    posicionActual++; //ahora la posicion vale +1
                }
            }
            return posicionActual-1;
        }else{
            return -1;
        }
    }

    /**
     * Metodo que elimina al final de la lista.
     * @return Regresa al objeto obtenido.
     */
    public Object eliminar(){
        if (vacia() == false){ //hay que algo eliminar
            Object respaldo = fin.getInfo();
            if ((inicio == fin)&&(inicio == penultimo)){ //solo hay un nodo en la lista
                inicio = null;
                fin = null;
                penultimo = null;
            } else { //si esta al final de otro nodo.
                fin = penultimo;
                penultimo = buscarAnteriorAPenultimo();
                if ((posicionActual-1)%2 == 0){ //si el ultimo nodo fue par
                     //ojo aqui
                    fin.setLigaIzq(null);
                    penultimo.setLigaDer(null);
                } else { //si el ultimo fue impar
                    penultimo.setLigaDer(null);
                }
            }
            posicionActual--;
            return respaldo;
        }else{
            return null;
        }
    }

    /**
     * Metodo que busca al elemento detras del penultimo.
     * @return Regresa el nodo completo detras del penultimo.
     */
    private NodoDoble buscarAnteriorAPenultimo(){
        NodoDoble anterior = inicio;
        NodoDoble temporal = inicio;
        int actual = 1;
        while ((temporal != null) ){
            if (temporal == penultimo){
                return anterior;
            }
            if (actual%2 == 0){
                NodoDoble auxiliar = temporal;
                temporal = anterior.getLigaDer();
                anterior = auxiliar;
                actual++;
            } else {
                anterior = temporal;
                temporal = temporal.getLigaIzq();
                actual++;
            }

        }
        return null;
    }

    /**
     * Metodo que busca a un elemento y lo devuelve.
     * @param info Info solicitada a buscar.
     * @return Regresa la info del
     */
    public Object buscar(Object info){
        NodoDoble anterior = inicio;
        NodoDoble temporal = inicio;
        int posicion = 1;
        while (temporal != null){
            if (temporal.getInfo().equals(info) == true){
                return temporal.getInfo();
            }
            if (posicion%2 == 0){ //si el nodo actual es par, invocar a la liga derecha del anterior
                NodoDoble auxiliar = temporal;
                temporal = anterior.getLigaDer();
                anterior = auxiliar;
                posicion++;
            }else{ //si el nodo actual es impar, invocar a la liga de abajo (liga izq)
                anterior = temporal;
                temporal = temporal.getLigaIzq();
                posicion++;
            }
        }
        return null;
    }

    /**
     * Metodo que permite realizar la impresion de la Lista Doble.
     */
    public void imprimir(){
        NodoDoble temporal = inicio;
        while (temporal != null){ //imprimir impares
            SalidaPorDefecto.terminal(temporal.getInfo()+" -> ");
            temporal = temporal.getLigaDer();
        }
        SalidaPorDefecto.terminal("null\n");
        if (posicionActual > 1){ //hay mas de un nodo
            temporal = inicio.getLigaIzq();
            while (temporal != null){ //imprimir impares
                SalidaPorDefecto.terminal(temporal.getInfo()+" -> ");
                temporal = temporal.getLigaDer();
            }
        }
        SalidaPorDefecto.terminal("null");
    }

    /**
     * Metodo de prueba
     */
    private void imprimirDatos(){
        SalidaPorDefecto.terminal("\nInicio: "+inicio+"\nFin: "+fin+"\nPenultimo: "+penultimo+"\n");
    }

}
