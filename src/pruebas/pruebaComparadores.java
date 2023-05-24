package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstatica;
import utilerias.comunes.Comparadores;

public class pruebaComparadores {
    public static void main(String[] args) {
        SalidaPorDefecto.terminal("Si es menor regresa 1, si no regresa -1 o 0 si son iguales\nPrueba en Doubles:\n");
        SalidaPorDefecto.terminal((Comparadores.comparar(2.0, 4.0))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar(5.0, 1.0))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar(7.0, 7.0))+"\n" );
        SalidaPorDefecto.terminal("Ahora con cadenas: \n");
        SalidaPorDefecto.terminal((Comparadores.comparar("Manuel", "Jose Enrique"))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar("a", "A"))+"\n" ); // iguales tecnicamente
        SalidaPorDefecto.terminal((Comparadores.comparar("1", "2"))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar("B", "C"))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar("Z", "E"))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar("Z", null))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar(1, null))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar(null, 2))+"\n" );
        SalidaPorDefecto.terminal((Comparadores.comparar(null , null))+"\n" );


        ListaEstatica lista1 = new ListaEstatica(2);
        ListaEstatica lista2 = new ListaEstatica(2);

        SalidaPorDefecto.terminal((Comparadores.comparar(lista1, lista2)+"\n"));

        ListaEstatica lista3 = new ListaEstatica(5);
        SalidaPorDefecto.terminal("Prueba en lista: "+(Comparadores.comparar(lista2, lista3)+"\n"));
    }
}
