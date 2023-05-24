package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ColaPorPrioridad;
import estructurasLineales.ListaEstaticaOrdenada;
import utilerias.comunes.TipoOrden;

public class PruebaColaPorPrioridad {
    public static void main(String[] args) {
        ListaEstaticaOrdenada prioridades = new ListaEstaticaOrdenada(2, TipoOrden.INC);
        prioridades.agregar(4);
        prioridades.agregar(7);
        ColaPorPrioridad colaP = new ColaPorPrioridad(3, prioridades);
        SalidaPorDefecto.terminal("Probando el metodo agregar: "+colaP.agregarACola(4, "D")+".\n");
        SalidaPorDefecto.terminal("Probando el metodo agregar en una posicion invalida: "+colaP.agregarACola(20, "D")+".\n");
        colaP.agregarACola(4, "C");
        colaP.agregarACola(4, "B");
        colaP.agregarACola(7, "A");
        SalidaPorDefecto.terminal("\nProbando la impresion:\n");
        colaP.imprimir();
        SalidaPorDefecto.terminal("\nProbando la eliminacion del de menor prior: "+colaP.quitarPrioridad()+"\n");
        SalidaPorDefecto.terminal("Probando la eliminacion del de menor prior: "+colaP.quitarPrioridad()+"\n\n");
        colaP.imprimir();

    }
}
