package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasLineales.ListaDinamicaDoble;

public class PruebaListaDinamicaDoble {
    public static void main(String[] args) {
        ListaDinamicaDoble listaDoble = new ListaDinamicaDoble();
        SalidaPorDefecto.terminal("Probando el vacio: "+listaDoble.vacia()+"\n");
        SalidaPorDefecto.terminal("Probando el agregar: "+listaDoble.agregar("A")+"\n");
        listaDoble.agregar("B");
        listaDoble.agregar("C");
        listaDoble.agregar("D");
        listaDoble.agregar("E");
        listaDoble.imprimir();
        SalidaPorDefecto.terminal("\nEliminando el ultimo: "+listaDoble.eliminar()+"\n");
        listaDoble.imprimir();
    }
}
