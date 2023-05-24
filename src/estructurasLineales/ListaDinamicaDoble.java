package estructurasLineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.auxiliares.Nodo;
import estructurasLineales.auxiliares.NodoDoble;

public class ListaDinamicaDoble implements Lista{
    protected NodoDoble primero;
    protected NodoDoble ultimo;

    @Override
    public boolean vacia() {
        if (primero == null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int agregar(Object info) {
        NodoDoble nuevoNodo = new NodoDoble(info);
        if (nuevoNodo != null){
            if (vacia() == true){
                primero = nuevoNodo;
                ultimo = nuevoNodo;
            }else{
                ultimo.setLigaDer(nuevoNodo);
                nuevoNodo.setLigaIzq(ultimo);
                ultimo = nuevoNodo;
            }
            return 1;
        }else{//si la memoria se llena y ya no hay direccion de memoria
            return -1;
        }
    }

    @Override
    public void imprimir() {
        if (vacia() == false){
            SalidaPorDefecto.terminal("null <- ");
            NodoDoble nodoActual = primero;
            while (nodoActual.getLigaDer() != null){
                SalidaPorDefecto.terminal(nodoActual.getInfo()+" <-> ");
                nodoActual = nodoActual.getLigaDer();
            }
            SalidaPorDefecto.terminal(nodoActual.getInfo()+" -> ");
        }
        SalidaPorDefecto.terminal("null");
    }

    @Override
    public void imprimirOI() {

    }

    @Override
    public Object buscar(Object info) {
        return null;
    }

    @Override
    public Object eliminar(Object info) {
        return null;
    }

    @Override
    public boolean esIgual(Object lista2) {
        return false;
    }

    @Override
    public boolean cambiar(Object infoViejo, Object infoNuevo, int numVeces) {
        return false;
    }

    @Override
    public ListaEstatica buscarValores(Object info) {
        return null;
    }

    @Override
    public Object eliminar() {
        if (vacia() == false){
            Object respaldo = ultimo.getInfo();
            if (primero == ultimo){
                primero = null;
                ultimo = null;
            } else {
                ultimo = ultimo.getLigaIzq();
                ultimo.setLigaDer(null);
            }
            return respaldo;
        }else{
            return null;
        }
    }

    @Override
    public void vaciar() {

    }

    @Override
    public boolean agregarLista(Object lista2) {
        return false;
    }

    @Override
    public void invertir() {

    }

    @Override
    public int contar(Object info) {
        return 0;
    }

    @Override
    public boolean eliminarLista(Object lista2) {
        return false;
    }

    @Override
    public void rellenar(Object info, int cantidad) {

    }

    @Override
    public Lista clonar() {
        return null;
    }

    @Override
    public Lista subLista(int indiceInicial, int indiceFinal) {
        return null;
    }

    @Override
    public boolean redimensionar(int maximo) {
        return false;
    }

    @Override
    public int maximo() {
        return 0;
    }

    @Override
    public int cantidad() {
        return 0;
    }

    @Override
    public Object verUltimo() {
        return null;
    }

    @Override
    public Object eliminarInicio() {
        return null;
    }
}
