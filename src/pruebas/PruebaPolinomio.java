package pruebas;

import entradasalida.SalidaPorDefecto;
import utilerias.matematicas.polinomios.OperacionesPolinomio;

public class PruebaPolinomio {
    public static void main(String[] args) {
        OperacionesPolinomio polin = new OperacionesPolinomio();
        polin.agregarExpresion();
        SalidaPorDefecto.terminal("Imprimiendo el polinomio: ");
        polin.imprimirPolinomio();
        SalidaPorDefecto.terminal("\nBuscando el monomio x^3: "+polin.obtenerMonomio("x^3"));
        SalidaPorDefecto.terminal("\nBuscando el monomio x^1: "+polin.obtenerMonomio("x^1"));
        SalidaPorDefecto.terminal("\nBinario: "+polin.aBinario());
        SalidaPorDefecto.terminal("\nResultado: "+polin.resolverPolinomio()+"\n");
    }
}
