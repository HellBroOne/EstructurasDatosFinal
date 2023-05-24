package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ColaEstatica;

public class PruebaColaEstatica {
    public static void main(String[] args) {
        ColaEstatica cola = new ColaEstatica(5);
        cola.poner("A");
        cola.poner("B");
        cola.poner("C");
        cola.poner("D");
        cola.poner("E");
        cola.poner("F");
        cola.imprimir();
        SalidaPorDefecto.terminal("\nVeamos el primero:"+cola.verInicio()+".\n");
        SalidaPorDefecto.terminal("\nVeamos el ultimo:"+cola.verTope()+".\n");
        SalidaPorDefecto.terminal("\nDebe de estar llena:"+cola.lleno()+".\n");

        SalidaPorDefecto.terminal("\nEliminando: "+cola.quitar()+".\n");
        SalidaPorDefecto.terminal("\nAhora no debe de estar llena:"+cola.lleno()+".\n");
        cola.imprimir();
        SalidaPorDefecto.terminal("\nEliminando: "+cola.quitar()+".\n");

        cola.imprimir();
        cola.poner("Z");
        SalidaPorDefecto.terminal("\n");
        cola.imprimir();
        cola.quitar();
        cola.quitar();
        cola.quitar();
        cola.quitar();
        SalidaPorDefecto.terminal("\nAhora se supone que esta vacia:"+cola.vacio()+".\n");
        cola.poner("X");
        SalidaPorDefecto.terminal("\nAhora no se supone que esta vacia:"+cola.vacio()+".\n");
    }
}
