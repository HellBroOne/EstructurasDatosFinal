package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.GrafoEstatico;
import utilerias.comunes.TipoOrden;

public class PruebaGrafoRuta {
    public static void main(String[] args) {
        GrafoEstatico grafoRuta = new GrafoEstatico(8, TipoOrden.DEC);
        grafoRuta.agregarVertice("A");
        grafoRuta.agregarVertice("B");
        grafoRuta.agregarVertice("C");
        grafoRuta.agregarVertice("D");
        grafoRuta.agregarVertice("E");
        grafoRuta.agregarVertice("F");
        grafoRuta.agregarVertice("H");
        grafoRuta.agregarVertice("M");

        grafoRuta.agregarArista("A", "B", 3);
        grafoRuta.agregarArista("B", "A", 3);

        grafoRuta.agregarArista("A", "C", 5.0);
        grafoRuta.agregarArista("C", "A", 5.0);

        grafoRuta.agregarArista("C", "E", 8.0);
        grafoRuta.agregarArista("E", "C", 8.0);

        grafoRuta.agregarArista("C", "D", 6.0);
        grafoRuta.agregarArista("D", "C", 6.0);

        grafoRuta.agregarArista("B", "E", 1.0);
        grafoRuta.agregarArista("E", "B", 1.0);

        grafoRuta.agregarArista("B", "H", 4.0);
        grafoRuta.agregarArista("H", "B", 4.0);

        grafoRuta.agregarArista("E", "F", 7.0);
        grafoRuta.agregarArista("F", "E", 7.0);

        grafoRuta.agregarArista("F", "H", 6.0);
        grafoRuta.agregarArista("H", "F", 6.0);

        grafoRuta.agregarArista("F", "D", 2.0);
        grafoRuta.agregarArista("D", "F", 2.0);

        grafoRuta.agregarArista("D", "M", 6.0);
        grafoRuta.agregarArista("M", "D", 6.0);

        grafoRuta.agregarArista("H", "M", 9.0);
        grafoRuta.agregarArista("M", "H", 9.0);

        grafoRuta.imprimir();
        SalidaPorDefecto.terminal("\n");
        grafoRuta.rutaMasCortaDijkstra("A").imprimir();
        SalidaPorDefecto.terminal("\nMetrica de A a M es: "+grafoRuta.metricaRutaOptima("A", "M"));
        SalidaPorDefecto.terminal("\nLa ruta de A a M es: ");
        grafoRuta.rutaOptima("A", "M").imprimir();
    }
}
