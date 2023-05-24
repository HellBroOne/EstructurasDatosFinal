package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.ArbolBinario;
import utilerias.matematicas.ArbolExpresionReemplazo;

public class PruebaAEReemplazo {
    public static void main(String[] args) {
        ArbolExpresionReemplazo arbolEx = new ArbolExpresionReemplazo();
        arbolEx.crearArbol();
        SalidaPorDefecto.terminal("\n");
        arbolEx.inorden();
        SalidaPorDefecto.terminal("\n\nNuevo: ");
        ArbolBinario arbolReemplazo = arbolEx.generarArbol();
        arbolReemplazo.innorden();
        arbolReemplazo.postorden();
        arbolReemplazo.preorden();
        SalidaPorDefecto.terminal("\nOriginal: ");
        arbolEx.inorden();
    }
}
