package pruebas;

import estructurasnolineales.GrafoEstatico;

public class PruebaOT {
    public static void main(String[] args) {
        GrafoEstatico grafo = new GrafoEstatico(7);
        grafo.agregarVertice("P1");
        grafo.agregarVertice("P2");
        grafo.agregarVertice("P3");
        grafo.agregarVertice("P4");
        grafo.agregarVertice("P5");
        grafo.agregarVertice("P6");
        grafo.agregarVertice("P7");

        grafo.agregarArista("P1", "P3");
        grafo.agregarArista("P2", "P3");
        grafo.agregarArista("P3", "P4");
        grafo.agregarArista("P3", "P5");
        grafo.agregarArista("P4", "P5");
        grafo.agregarArista("P4", "P6");
        grafo.agregarArista("P5", "P6");
        grafo.agregarArista("P5", "P7");
        grafo.agregarArista("P6", "P7");

        grafo.ordenacionTopologica().imprimir();
    }
}
