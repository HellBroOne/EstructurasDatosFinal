package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.ArbolBinario;

public class PruebaAB {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        // PARA ESTE EJEMPLO USE UN ARBOL DE A B D C F G
        arbol.crearArbol();
        SalidaPorDefecto.terminal("\nPostorden Recursivo: ");
        arbol.postorden();
        SalidaPorDefecto.terminal("\nPostorden Iterativo: ");
        arbol.postordenCiclico();
        SalidaPorDefecto.terminal("\n");
        arbol.elementosPorNivel();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("\n\nLa altura del arbol es de: "+arbol.alturaArbol()+"\n");
        SalidaPorDefecto.terminal("La altura del nodo C es de: "+arbol.buscarAltura("C")+"\n");
        SalidaPorDefecto.terminal("La altura del nodo B es de: "+arbol.buscarAltura("B")+"\n");
        SalidaPorDefecto.terminal("La altura del nodo A es de: "+arbol.buscarAltura("A")+"\n");

        SalidaPorDefecto.terminal("\nVerificando que es A: ");
        arbol.tipoNodo("A");
        SalidaPorDefecto.terminal("\nVerificando que es B: ");
        arbol.tipoNodo("B");
        SalidaPorDefecto.terminal("\nVerificando que es F: ");
        arbol.tipoNodo("F");
        SalidaPorDefecto.terminal("\nVerificando que es X (Inexistente): ");
        arbol.tipoNodo("X");

        SalidaPorDefecto.terminal("\n");
        arbol.tieneHermano("A"); //nodo raiz
        SalidaPorDefecto.terminal("\n");
        arbol.tieneHermano("B"); //si tiene hermano
        SalidaPorDefecto.terminal("\n");
        arbol.tieneHermano("G"); //no tiene hermano
        SalidaPorDefecto.terminal("\n");

        arbol.preorden();
        SalidaPorDefecto.terminal("\n");
        arbol.innorden();
        SalidaPorDefecto.terminal("\n");
        arbol.postorden();

        //########################## PRUEBAS DE LA PRACTICA 18 ###########################
        SalidaPorDefecto.terminal("\nPor anchura: ");
        arbol.amplitud();
        SalidaPorDefecto.terminal("\nPor anchura Pilosa: ");
        arbol.amplitudPilesca();
    }
}
