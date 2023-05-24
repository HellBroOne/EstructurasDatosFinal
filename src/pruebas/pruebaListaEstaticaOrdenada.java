package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstaticaOrdenada;
import utilerias.comunes.TipoOrden;

public class pruebaListaEstaticaOrdenada {
    public static void main(String[] args) {


        ListaEstaticaOrdenada listaOrd = new ListaEstaticaOrdenada(7, TipoOrden.INC);
        SalidaPorDefecto.terminal(listaOrd.agregar(2)+"\n");
        SalidaPorDefecto.terminal(listaOrd.agregar(1)+"\n");
        SalidaPorDefecto.terminal(listaOrd.agregar(3)+"\n");
        SalidaPorDefecto.terminal(listaOrd.agregar(6)+"\n");
        SalidaPorDefecto.terminal(listaOrd.agregar(5)+"\n");
        SalidaPorDefecto.terminal(listaOrd.agregar(4)+"\n");
        SalidaPorDefecto.terminal(listaOrd.agregar(7)+"\n");
        SalidaPorDefecto.terminal("La lista Es:\n");
        listaOrd.imprimir();
        SalidaPorDefecto.terminal("\nLa lista ha acabado\n");
        //el metodo agregar me agrega todos los elementos en la posicion 0, algo debe de tener que no entra en la variable

        SalidaPorDefecto.terminal(listaOrd.buscar(7)+"\n");
        SalidaPorDefecto.terminal(listaOrd.buscar(1)+"\n");
        SalidaPorDefecto.terminal(listaOrd.buscar(2)+"\n");
        SalidaPorDefecto.terminal(listaOrd.buscar(9)+"\n");

        SalidaPorDefecto.terminal("Salida, lista inversa\n");
        listaOrd.invertir();
        listaOrd.imprimir();

        SalidaPorDefecto.terminal("Salida, eliminados\n");
        Integer uno = 1;
        Integer dos = 2;
        SalidaPorDefecto.terminal(listaOrd.eliminar(listaOrd.buscar(1))+"\n");
        SalidaPorDefecto.terminal(listaOrd.eliminar(listaOrd.buscar(9))+"\n");
        listaOrd.imprimir();

        ListaEstaticaOrdenada lista2 = new ListaEstaticaOrdenada(2, TipoOrden.INC);
        lista2.agregar(8);
        lista2.agregar(9);

        listaOrd.arregloDesordenado().imprimir();

        listaOrd.cambiar(6, 8);
        listaOrd.imprimir();
    }
}
