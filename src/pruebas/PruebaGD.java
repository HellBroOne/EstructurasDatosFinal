package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.GrafoDinamico;

public class PruebaGD {
    public static void main(String[] args) {
        GrafoDinamico grafo = new GrafoDinamico();
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");

        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");

        grafo.agregarArista("B", "A");
        grafo.agregarArista("B", "C");

        grafo.agregarArista("C", "B");
        grafo.agregarArista("C", "E");

        grafo.agregarArista("D", "A");
        grafo.agregarArista("D", "C");

        grafo.agregarArista("E", "A");
        grafo.agregarArista("E", "C");
        grafo.agregarArista("E", "D");

        grafo.imprimir();
        SalidaPorDefecto.terminal("\nRecorrido por Profundidad: \n");
        grafo.recorridoProfundidad("A").imprimir();
    }
}
