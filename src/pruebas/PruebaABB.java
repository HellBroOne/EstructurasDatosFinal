package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.ArbolBinarioBusqueda;

public class PruebaABB {
    public static void main(String[] args) {
        ArbolBinarioBusqueda arbolBusqueda = new ArbolBinarioBusqueda();
        arbolBusqueda.agregar(5);
        arbolBusqueda.agregar(3);
        arbolBusqueda.agregar(9);
        arbolBusqueda.agregar(7);
        arbolBusqueda.agregar(4.5);
        arbolBusqueda.agregar(12);
        arbolBusqueda.agregar(4);
        arbolBusqueda.agregar(1);
        arbolBusqueda.innorden();
        SalidaPorDefecto.terminal("\n\nBuscando a el 7: "+arbolBusqueda.buscar(7)+".\n");
        SalidaPorDefecto.terminal("Buscando a el 4.5: "+arbolBusqueda.buscar(4.5)+".\n");
        SalidaPorDefecto.terminal("Buscando a Nemo: "+arbolBusqueda.buscar("Nemo")+".\n");

        SalidaPorDefecto.terminal("Eliminando a 5:\n");
        arbolBusqueda.eliminar(5);
        arbolBusqueda.innorden();
        SalidaPorDefecto.terminal("\nEliminando a 4.5:\n");
        arbolBusqueda.eliminar(4.5);
        arbolBusqueda.innorden();
        SalidaPorDefecto.terminal("\nEliminando a 3:\n");
        arbolBusqueda.eliminar(3);
        arbolBusqueda.innorden();
        SalidaPorDefecto.terminal("\nEliminando a 666:\n");
        arbolBusqueda.eliminar(666);
        arbolBusqueda.innorden();
    }
}
