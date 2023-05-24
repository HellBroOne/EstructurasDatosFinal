package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.GrafoEstatico;

public class PruebaGrafoEstatico {
    public static void main(String[] args) {
        GrafoEstatico grafoEs = new GrafoEstatico(4, 0.0);
        grafoEs.agregarVertice("A");
        grafoEs.agregarVertice("B");
        grafoEs.agregarVertice("C");
        grafoEs.agregarVertice("D");

        grafoEs.agregarArista("A", "B");
        grafoEs.agregarArista("A", "C");
        grafoEs.agregarArista("B", "A");
        grafoEs.agregarArista("B", "C");
        grafoEs.agregarArista("B", "D");
        grafoEs.agregarArista("D", "B");
        grafoEs.agregarArista("D", "C");

        GrafoEstatico grafo2 = new GrafoEstatico(5, 0.0);
        grafo2.agregarVertice("A");
        grafo2.agregarVertice("B");
        grafo2.agregarVertice("C");
        grafo2.agregarVertice("D");
        grafo2.agregarVertice("E");

        grafo2.agregarArista("A", "B");
        grafo2.agregarArista("B", "C");
        grafo2.agregarArista("B", "D");
        grafo2.agregarArista("C", "A");
        grafo2.agregarArista("D", "A");
        grafo2.agregarArista("D", "C");
        grafo2.agregarArista("D", "E");
        grafo2.agregarArista("E", "C");

        grafo2.imprimir();
        SalidaPorDefecto.terminal("\nRecorrido a Profundidad:\n");
        grafo2.recorridoProfundidad("A").imprimir();
        SalidaPorDefecto.terminal("\nRecorrido a Anchura:\n");
        grafo2.recorridoAnchura("A").imprimir();
    }
}
