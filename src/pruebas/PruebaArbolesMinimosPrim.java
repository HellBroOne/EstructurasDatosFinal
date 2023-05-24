package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.GrafoDinamico;

public class PruebaArbolesMinimosPrim {
    public static void main(String[] args) {
        GrafoDinamico grafo = new GrafoDinamico();
        //agregar vertices
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        //agregar aristas
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 4);

        grafo.agregarArista(2, 1, 1);
        grafo.agregarArista(2, 3, 3);
        grafo.agregarArista(2, 4, 6);

        grafo.agregarArista(3, 1, 4);
        grafo.agregarArista(3, 2, 3);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(3, 4, 4);

        grafo.agregarArista(4, 2, 6);
        grafo.agregarArista(4, 3, 4);
        grafo.agregarArista(4, 5, 5);

        grafo.agregarArista(5, 3, 2);
        grafo.agregarArista(5, 4, 5);

        grafo.imprimir();
        //SalidaPorDefecto.terminal("\nRecorrido en profundidad desde 1: ");
        //grafo.recorridoProfundidad(1).imprimir();
        SalidaPorDefecto.terminal("\nAlgoritmo de Prim: \n");
        grafo.algoritmoPrim().imprimir();
        SalidaPorDefecto.terminal("\nLista de Costo minimo: \n");
        grafo.imprimirCostoMinimo();
    }
}
