package estructurasLineales;

/**
 *
 */
public class PilaEstatica implements Lote{

    protected ListaEstatica pila;

    public PilaEstatica (int tamanio){//Constructor para la clase Lote
        pila = new ListaEstatica(tamanio);

    }

    @Override
    public boolean lleno() {
        return pila.llena();
    }

    @Override
    public boolean vacio() {
        return pila.vacia();
    }

    @Override
    public boolean poner(Object info) {
        int retorno = pila.agregar(info);
        if (retorno >= 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Object quitar() {
        return pila.eliminar();
    }

    @Override
    public void imprimir() {
        pila.imprimir();
    }

    @Override
    public Object verTope() {
        return pila.verUltimo();
    }
}
