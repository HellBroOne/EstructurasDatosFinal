package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamicaOrdenada;
import estructurasLineales.ListaEstatica;
import estructurasLineales.ListaEstaticaOrdenada;
import estructurasnolineales.Matriz2;
import utilerias.comunes.TipoOrden;
import utilerias.comunes.TipoTabla;

public class PruebaListaDinamicaOrdenada {
    public static void main(String[] args) {
        ListaDinamicaOrdenada listaOrd = new ListaDinamicaOrdenada(TipoOrden.INC);
        listaOrd.agregar(2);
        listaOrd.agregar(6);
        listaOrd.agregar(4);
        listaOrd.agregar(3);
        listaOrd.agregar(1);
        listaOrd.imprimir();
        listaOrd.invertir();
        listaOrd.imprimir();
        SalidaPorDefecto.terminal("\nBuscando el 5:\n");
        ListaEstatica retorno = ((ListaEstatica) listaOrd.buscar(5));
        retorno.imprimir();
        SalidaPorDefecto.terminal("\nBuscando el 3:\n");
        ListaEstatica retorno2 = ((ListaEstatica) listaOrd.buscar(3));
        retorno2.imprimir();
        SalidaPorDefecto.terminal("Tamanio: "+retorno.maximo());
        ListaDinamicaOrdenada nuevalistaOrd = new ListaDinamicaOrdenada(TipoOrden.DEC);
        SalidaPorDefecto.terminal("\nLista Ordenada nueva:\n");
        nuevalistaOrd.agregar(2);
        nuevalistaOrd.agregar(6);
        nuevalistaOrd.agregar(4);
        nuevalistaOrd.agregar(3);
        nuevalistaOrd.agregar(1);
        nuevalistaOrd.agregar(-30);
        nuevalistaOrd.agregar(-88);
        nuevalistaOrd.agregar(-100);
        nuevalistaOrd.agregar(210);
        nuevalistaOrd.imprimir();
        Object tres = 3;
        nuevalistaOrd.eliminar(tres);
        SalidaPorDefecto.terminal("\n");
        nuevalistaOrd.imprimir();
        SalidaPorDefecto.terminal("\n");
        ListaDinamicaOrdenada listaAlfa = new ListaDinamicaOrdenada(TipoOrden.INC);
        listaAlfa.agregar("Tortilla");
        listaAlfa.agregar("Estructura de Datos");
        listaAlfa.agregar("Ya quiero salir de vacaciones ._.");
        listaAlfa.agregar("Pan");
        listaAlfa.agregar("Xeno Gear");
        SalidaPorDefecto.terminal("\nLista Ordenada Alfas:\n");
        listaAlfa.imprimir();
        SalidaPorDefecto.terminal("\n");
        //listaAlfa.eliminar("Pan");
        listaAlfa.imprimirOI();
        listaAlfa.eliminarInicio(); //<-- Debe mandar llamar a eliminar unicamente
        SalidaPorDefecto.terminal("\n");
        listaAlfa.imprimir();
        SalidaPorDefecto.terminal("\n");
        ListaEstatica uno = new ListaEstatica(4);
        uno.agregar("Verde");
        uno.agregar("Cyber");
        uno.agregar("Neon");
        SalidaPorDefecto.terminal("\n");
        listaAlfa.imprimir();
        SalidaPorDefecto.terminal("\nAgregando lista:"+listaAlfa.agregarLista(uno)+"\n");
        listaAlfa.imprimir();
        SalidaPorDefecto.terminal("\nUltimo:"+listaAlfa.verUltimo()+"\n");
        SalidaPorDefecto.terminal("\n\n");
        Matriz2 matrix = new Matriz2(2, 2);
        matrix.cambiar(0, 0, "Anders");
        matrix.cambiar(0, 1, "Broma");
        matrix.cambiar(1, 0, "Falacia");
        matrix.cambiar(1, 1, "Qwerty");
        SalidaPorDefecto.terminal("\nUltimo:"+listaAlfa.verUltimo()+"\n");
        SalidaPorDefecto.terminal("\nAgregando lista:"+listaAlfa.agregarMatriz2D(matrix, TipoTabla.COLUMNA)+"\n");
        listaAlfa.imprimir();
        SalidaPorDefecto.terminal("\nEliminando lista:"+listaAlfa.eliminarLista(uno)+"\n");
        SalidaPorDefecto.terminal("\nUltimo:"+listaAlfa.verUltimo()+"\n");
        listaAlfa.imprimir();
        SalidaPorDefecto.terminal("\nEliminando matriz:"+listaAlfa.eliminarMatriz2D(matrix)+"\n");
        listaAlfa.imprimir();
        listaAlfa.cambiar(3, "Si sale padrino?");
        SalidaPorDefecto.terminal("\n\n");
        listaAlfa.imprimir();
        SalidaPorDefecto.terminal("\nCambiando Tortilla 4 veces:"+listaAlfa.cambiar("Tortilla", "Queso", 4)+"\n");
        listaAlfa.imprimir();
    }
}
