package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.PilaEstatica;

public class pruebaPila {
    public static void main(String[] argumentos) {
        PilaEstatica pila = new PilaEstatica(5);
        pila.poner("A");
        pila.poner("S");
        pila.poner("B");
        pila.poner("R");

        pila.imprimir();
        SalidaPorDefecto.terminal("El tope es: " + pila.verTope() + ".\n");
        SalidaPorDefecto.terminal("Eliminando: " + pila.quitar() + ".\n");
        SalidaPorDefecto.terminal("El nuevo tope es: " + pila.verTope() + ".\n");
        pila.imprimir();
    }
}
