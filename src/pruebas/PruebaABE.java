package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.ArbolExpresiones;

public class PruebaABE {
    public static void main(String[] args) {
        ArbolExpresiones arbolEx = new ArbolExpresiones();
        arbolEx.crearArbolPostfija("ab+53*42^/-");
        SalidaPorDefecto.terminal("\nRecorridos:\n\n");
        SalidaPorDefecto.terminal("Innorden:\n");
        arbolEx.innorden();
        SalidaPorDefecto.terminal("\nPreorden:\n");
        arbolEx.preorden();
        SalidaPorDefecto.terminal("\nPostorden:\n");
        arbolEx.postorden();
        //de derecha a izquierda y el operando cambia cuando es una u otra
        arbolEx.crearArbolPrefija("-+ab/*53^42");
        SalidaPorDefecto.terminal("\n\nRecorridos:\n\n");
        SalidaPorDefecto.terminal("Innorden:\n");
        arbolEx.innorden();
        SalidaPorDefecto.terminal("\nPreorden:\n");
        arbolEx.preorden();
        SalidaPorDefecto.terminal("\nPostorden:\n");
        arbolEx.postorden();
    }
}
