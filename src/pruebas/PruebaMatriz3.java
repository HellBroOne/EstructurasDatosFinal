package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstatica;
import estructurasnolineales.Matriz2;
import estructurasnolineales.Matriz3;

public class PruebaMatriz3 {
    public static void main(String[] args) {
        Matriz3 matriz = new Matriz3(4, 3, 5, 0);
        matriz.imprimirPorColumnas();

        matriz.cambiar(2, 1, 3, 9);
        matriz.cambiar(1, 2, 0, 8);
        matriz.cambiar(0, 0, 0, 7);
        matriz.cambiar(3, 1, 4, 4);

        SalidaPorDefecto.terminal("otra vez\n");
        matriz.imprimirPorColumnas();
        SalidaPorDefecto.terminal("listas2: \n");
        imprimirMatrices(matriz.aMatrices2());
    }

    public static void imprimirMatrices(ListaEstatica matrices){
        for (int cadaMatriz=0;cadaMatriz<matrices.cantidad();cadaMatriz++){
            Matriz2 matriz2Temporal = (Matriz2) matrices.obtener(cadaMatriz);
            matriz2Temporal.imprimirPorColumnas();
            SalidaPorDefecto.terminal("\n");
        }
    }
}
