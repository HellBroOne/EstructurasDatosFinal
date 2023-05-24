package pruebas;

import estructurasLineales.ListaEstatica;
import entradasalida.SalidaPorDefecto;

public class PruebaListaEstatica {
    public static void main(String[] argumentosValidosQueLePaso) {
        ListaEstatica lista = new ListaEstatica(5);

        lista.agregar("A");
        lista.agregar("G");
        lista.agregar("D");
        lista.agregar("S");
        lista.agregar("R");
        lista.agregar("J");
        lista.imprimir();

        SalidaPorDefecto.terminal("\n");

        SalidaPorDefecto.terminal("Buscando a G: "+lista.buscar("G")+"\n");
        SalidaPorDefecto.terminal("Buscando a Nemo: "+lista.buscar("Nemo")+"\n");
        SalidaPorDefecto.terminal("Eliminando: "+lista.eliminar("D")+"\n");
        SalidaPorDefecto.terminal("Eliminando: "+lista.eliminar("Nemo")+"\n");

        lista.imprimir();
        SalidaPorDefecto.terminal("\n");

        //Comparamos con el metodo esigual (FUNCIONA!)
        ListaEstatica lista1 = new ListaEstatica(4);
        ListaEstatica lista2 = new ListaEstatica(4);

        lista1.agregar("A");
        lista1.agregar("B");
        lista1.agregar("C");
        lista1.agregar("D");

        lista2.agregar("A");
        lista2.agregar("B");
        lista2.agregar("C");
        lista2.agregar("D");
        SalidaPorDefecto.terminal("Lista 1: \n");
        lista1.imprimir();
        SalidaPorDefecto.terminal("Lista 2: \n");
        lista2.imprimir();
        SalidaPorDefecto.terminal("Si ambas listas son iguales devolvera 'true'.\n" +
                "Si ambas listas son diferentes devuelve 'false'.\n" +
                "Resultado: " + lista1.esIgual(lista2) + "\n");

        //Prueba para obtener en VectorLista (FUNCIONA!)
        //la lista estatica lista tiene un elemento eliminado asi que tiene un tope en 3
        lista.imprimir();
        SalidaPorDefecto.terminal(""+lista.obtener(3)+"\n");
        SalidaPorDefecto.terminal(""+lista.obtener(0)+"\n");

        //para la prueba de cambiar (FUNCIONA!)
        ListaEstatica lista3 = new ListaEstatica(4);
        lista3.agregar("A");
        lista3.agregar("A");
        lista3.agregar("A");
        lista3.agregar("B");
        SalidaPorDefecto.terminal("\n");
        lista3.imprimir();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+lista3.cambiar("A", "C", 2)+"\n");
        lista3.imprimir();
        SalidaPorDefecto.terminal("\n");

        SalidaPorDefecto.terminal(""+lista3.cambiar(3, "D")+"\n");
        lista3.imprimir();
        SalidaPorDefecto.terminal("\n");

        //pureba buscarValores (FUNCIONA!)
        SalidaPorDefecto.terminal("Lista Estatica generada de busqueda: "+"\n");
        lista3.buscarValores("C").imprimir(); //"C" si esta en la lista
        SalidaPorDefecto.terminal("Lista Estatica generada de busqueda (elemento no esta en el arreglo): "+"\n");
        lista3.buscarValores("B").imprimir(); //"B" NO esta en la lista
        SalidaPorDefecto.terminal("\n");

        ListaEstatica lista4 = new ListaEstatica(3);
        lista4.agregar("A");
        lista4.agregar("B");
        lista4.agregar("C");
        lista4.imprimir();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+lista4.eliminar(2)+"\n");
        SalidaPorDefecto.terminal("Lista nueva: \n");
        lista4.imprimir();
        int pos = (int) lista4.buscar("A");
        SalidaPorDefecto.terminal("Buscando la posicion del A: "+lista4.buscar("A")+". \n");
        SalidaPorDefecto.terminal("Obteniendo el de la posicion 1: "+lista4.obtener(1)+". \n");
        SalidaPorDefecto.terminal("Obteniendo el de la posicion 3: "+lista4.obtener(3)+". \n");
        SalidaPorDefecto.terminal("Obteniendo el de la posicion de A: "+lista4.obtener(pos)+". \n");
        SalidaPorDefecto.terminal("\n");

        ListaEstatica lista5 = new ListaEstatica(3);
        lista5.agregar("A");
        lista5.agregar("B");
        lista5.agregar("C");
        lista5.imprimir();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+lista5.eliminar()+"\n");
        SalidaPorDefecto.terminal("Lista nueva: \n");
        lista5.imprimir();
        SalidaPorDefecto.terminal("\nLista normal: \n");
        lista.imprimir();
        lista.invertir();
        SalidaPorDefecto.terminal("\nLista invertida: \n");
        lista.imprimir();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+lista3.contar("C"));
    }
}
