package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.ListaDobleNoLinear;

public class PruebaListaDobleNoLinear {
    public static void main(String[] args) {
        ListaDobleNoLinear ldNoLin = new ListaDobleNoLinear();
        //Agregar
        SalidaPorDefecto.terminal("Agregando a la posicion inicial: "+ldNoLin.agregar("A")+"\n");
        SalidaPorDefecto.terminal("Agregando a la posicion segunda: "+ldNoLin.agregar("B")+"\n");
        SalidaPorDefecto.terminal("Agregando a la posicion tercera: "+ldNoLin.agregar("C")+"\n");
        SalidaPorDefecto.terminal("Agregando a la posicion cuarta: "+ldNoLin.agregar("D")+"\n");
        SalidaPorDefecto.terminal("Agregando a la posicion quinta: "+ldNoLin.agregar("E")+"\n");
        SalidaPorDefecto.terminal("Agregando a la posicion sexta: "+ldNoLin.agregar("F")+"\n");
        //Impresion
        ldNoLin.imprimir();
        //Eliminacion
        SalidaPorDefecto.terminal("Eliminando final: "+ldNoLin.eliminar()+"\n");
        ldNoLin.imprimir();
        SalidaPorDefecto.terminal("\nEliminando final: "+ldNoLin.eliminar()+"\n");
        ldNoLin.imprimir();
        //Busqueda
        SalidaPorDefecto.terminal("\nBuscando E: "+ldNoLin.buscar("E")+"\n");
        SalidaPorDefecto.terminal("Buscando A: "+ldNoLin.buscar("A")+"\n");
        SalidaPorDefecto.terminal("Buscando D: "+ldNoLin.buscar("D")+"\n");
        SalidaPorDefecto.terminal("Buscando X: "+ldNoLin.buscar("X")+"\n");
    }
}
