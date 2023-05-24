package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.PilaDinamica;

public class PruebaPilaDinamica {
    public static void main(String[] args) {
        PilaDinamica pilaDyna = new PilaDinamica();
        SalidaPorDefecto.terminal("Probemos el metodo de poner: "+pilaDyna.poner("Hola")+".\n");
        SalidaPorDefecto.terminal("Probemos el metodo de poner: "+pilaDyna.poner("Como")+".\n");
        SalidaPorDefecto.terminal("Probemos el metodo de poner: "+pilaDyna.poner("Estan")+".\n(De paso el de imprimir)\n");
        pilaDyna.imprimir();
        SalidaPorDefecto.terminal("\nProbemos el metodo de quitar,\nquitando 'Estan': "+pilaDyna.quitar()+".\n");
        pilaDyna.imprimir();
        SalidaPorDefecto.terminal("\nQue tal si vemos el ultimo? Ultimo: "+pilaDyna.verTope()+".\n");
        pilaDyna.imprimir();
        SalidaPorDefecto.terminal("\nNo deberia estar vacia. Vacia?: "+pilaDyna.vacio()+".\n");
        pilaDyna.quitar();
        pilaDyna.quitar();
        SalidaPorDefecto.terminal("[Alguien quito dos veces]\nY ahora?: "+pilaDyna.vacio()+".\n");
        pilaDyna.imprimir(); //imprime un null por defecto
    }
}
