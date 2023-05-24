package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstaticaOrdenada;
import utilerias.comunes.TipoOrden;
import estructurasLineales.ColaPorPrioridad;

public class SimulacionProcesos {
    public static void main(String[] args) {
        //Creamos una nueva lista ordenada de las prioridades.
        ListaEstaticaOrdenada prioridades = new ListaEstaticaOrdenada(3, TipoOrden.INC);
        prioridades.agregar(3); //procesos de SO (importancia mayor)
        prioridades.agregar(2); //descargas (importancia media)
        prioridades.agregar(1); //impresiones (importancia menor)
        ColaPorPrioridad colaPrior = new ColaPorPrioridad(5, prioridades);
        SalidaPorDefecto.terminal("Agregando proceso de Impresion: "+colaPrior.agregarACola(1, "Impresion desde Salida por Defecto")+".\n");
        SalidaPorDefecto.terminal("Agregando proceso de Descarga: "+colaPrior.agregarACola(2, "Descargando Overwatch 2.")+".\n");
        SalidaPorDefecto.terminal("Agregando proceso de Impresion: "+colaPrior.agregarACola(1, "Impresion de Archivo: 'Word.docx'.")+".\n");
        SalidaPorDefecto.terminal("Agregando proceso de SO: "+colaPrior.agregarACola(3, "Actualizando software del sistema.")+".\n");
        SalidaPorDefecto.terminal("Agregando proceso de SO: "+colaPrior.agregarACola(3, "Reiniciando la pc...")+".\n");
        SalidaPorDefecto.terminal("Agregando proceso de Descarga: "+colaPrior.agregarACola(2, "Descargando zip 'ProyectoEDyLab.zip'.")+".\n");
        SalidaPorDefecto.terminal("Agregando proceso de Reproducir Cumbias: "+colaPrior.agregarACola(5, "Reproduciendo Cumbia...")+".\n"); //Si no esta en las prioridades, no se agrega
        SalidaPorDefecto.terminal("\n");
        colaPrior.imprimir(); //imprimimos todas las colas por Prior más alta a mas baja
        SalidaPorDefecto.terminal("\n");
        //Simulamos que se finalizan todos los procesos
        SalidaPorDefecto.terminal("Proceso finalizado: "+colaPrior.quitarPrioridad()+".\n");
        SalidaPorDefecto.terminal("Proceso finalizado: "+colaPrior.quitarPrioridad()+".\n");
        SalidaPorDefecto.terminal("Proceso finalizado: "+colaPrior.quitarPrioridad()+".\n");
        SalidaPorDefecto.terminal("Proceso finalizado: "+colaPrior.quitarPrioridad()+".\n");
        SalidaPorDefecto.terminal("Proceso finalizado: "+colaPrior.quitarPrioridad()+".\n");
        SalidaPorDefecto.terminal("Proceso finalizado: "+colaPrior.quitarPrioridad()+".\n");
        //Se imprime "null" si ya no hay procesos.
        SalidaPorDefecto.terminal("Proceso finalizado: "+colaPrior.quitarPrioridad()+".\n");
    }
}
