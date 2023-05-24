package toneladasCampesinos;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;

public class PruebaProgramaCampesinos {
    public static void main(String[] args) {

        SalidaPorDefecto.terminal("Ingrese un anio para continuar: ");
        int numeroAnioPartida = Integer.parseInt(EntradaPorDefecto.consolaCadenas());
        Anio anio1 = new Anio(numeroAnioPartida);
        Anio anio2 = new Anio(numeroAnioPartida-1);
        Anio anio3 = new Anio(numeroAnioPartida-2);
        Anio anio4 = new Anio(numeroAnioPartida-3);
        menuControlador menu = new menuControlador(anio1, anio2, anio3, anio4);
        menu.mostrarMenu();
    }
}
