package estructurasLineales;

import entradasalida.SalidaPorDefecto;
import utilerias.comunes.TipoOrden;

/**
 * Clase que implementa un TDA Cola por Prioridad
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */
public class ColaPorPrioridad{
    protected int MAXIMO_Listas; //El tamanio maximo de la lista de prioridades.
    protected int MAXIMO_Cola; //tamanio de cada cola que tengamos en la lista de colas.
    protected ListaEstaticaOrdenada prioridades; //lista de prioridades.
    protected ListaEstatica colas; //lista con colas.

    /**
     * Constructor de la clase de colas por prioridad
     * @param tamanioColas Se asigna un tamanio a las colas que tendremos en cada prioridad.
     * @param prioridades Una lista estatica ordenada con las prioridades.
     */
    public ColaPorPrioridad(int tamanioColas, ListaEstaticaOrdenada prioridades){
        this.prioridades = prioridades;
        MAXIMO_Listas = prioridades.maximo();
        colas = new ListaEstatica(MAXIMO_Listas);
        MAXIMO_Cola = tamanioColas;
        llenarDeColas();
    }

    /**
     * llenamos la listaestatica que tenga colas
     */
    protected void llenarDeColas(){
        for (int cadaCola = 0; cadaCola<MAXIMO_Listas; cadaCola++){
            ColaEstatica colaTemporal = new ColaEstatica(MAXIMO_Cola);
            colas.agregar(colaTemporal);
        }
    }

    /**
     * Metodo que agrega un elemento a la cola de prioridad
     * @param prioridad Número de Prioridad a la cual agregar,<br/>
     *                  si la prioridad no existe en la lista de prioridades,<br/>
     *                  se regresa un valor negativo.
     * @param info Elemento a agregar en la cola de la prioridad deseada.
     * @return Regresa 1 si agrego correctmente.<br/>
     *         O de lo contrario, regresa un numero negativo<br/>
     *         si no se pudo agregar (no encontro la prioridad o<br/>)
     *         esta llena.
     */
    public int agregarACola(int prioridad, Object info){
        int posicionDePrioridad = (int) prioridades.buscar(prioridad);
        if ( posicionDePrioridad > 0){ //si si se encontro
            int posicionEnColas = posicionDePrioridad-1;
            if (((ColaEstatica) colas.obtener(posicionEnColas)).lleno() == false){
                ((ColaEstatica) colas.obtener(posicionEnColas)).poner(info);
                return 1;
            } else {
                return  -1;
            }
        } else {
            return posicionDePrioridad;
        }
    }

    /**
     * Metodo que imprime los valores por orden de prioridad mayor
     */
    public void imprimir(){
        for (int prioridad = (MAXIMO_Listas-1); prioridad>=0; prioridad--){
            SalidaPorDefecto.terminal("Prioridad "+(prioridad+1)+": ");
            ((ColaEstatica) colas.obtener(prioridad)).imprimir();
            SalidaPorDefecto.terminal("\n");
        }
    }

    /**
     * Metodo que elimina un elemento de las colas,<br/>
     * de acuerdo a su prioridad.
     * @return Regresa el objeto eliminado o <br/>
     * null si las colas están vacias.
     */
    public Object quitarPrioridad(){
        for (int cadaCola = 0; cadaCola<colas.maximo(); cadaCola++){
            if ( ((ColaEstatica) colas.obtener(cadaCola)).vacio() == false ){
                Object respaldo = ((ColaEstatica) colas.obtener(cadaCola)).quitar(); //quitamos un elemento de ese lugar
                return respaldo;
            }
        }
        return null;
    }

}
