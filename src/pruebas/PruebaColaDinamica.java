package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ColaDinamica;

public class PruebaColaDinamica {
    public static void main(String[] args) {
        ColaDinamica colaDyna = new ColaDinamica();
        SalidaPorDefecto.terminal("Probemos el metodo de poner: "+colaDyna.poner("Hola")+".\n");
        SalidaPorDefecto.terminal("Probemos el metodo de poner: "+colaDyna.poner("Como")+".\n");
        SalidaPorDefecto.terminal("Probemos el metodo de poner: "+colaDyna.poner("Estan")+".\n(De paso el de imprimir)\n");
        colaDyna.imprimir();
        SalidaPorDefecto.terminal("\nVeamos el primero: "+colaDyna.verInicio()+".\n");
        colaDyna.imprimir();
        SalidaPorDefecto.terminal("\nProbemos el metodo de quitar,\nquitando 'Hola': "+colaDyna.quitar()+".\n");
        colaDyna.imprimir();
        SalidaPorDefecto.terminal("\nQue tal si vemos el ultimo? Ultimo: "+colaDyna.verTope()+".\n");
        colaDyna.imprimir();
        SalidaPorDefecto.terminal("\nNo deberia estar vacia. Vacia?: "+colaDyna.vacio()+".\n");
        colaDyna.quitar();
        colaDyna.quitar();
        SalidaPorDefecto.terminal("[Alguien quito dos veces]\nY ahora?: "+colaDyna.vacio()+".\n");
        colaDyna.imprimir(); //imprime un null por defecto
    }
}
