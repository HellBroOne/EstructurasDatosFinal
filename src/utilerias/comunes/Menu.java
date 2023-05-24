package utilerias.comunes;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;

public class Menu {
    /**
     * Metodo que muestra un menu sencillo de 3 opciones
     * @param opcion1 Opcion 1 a mostrar.
     * @param opcion2 Opcion 2 a mostrar.
     * @param opcion3 Opcion 3 a mostrar.
     * @return Regresa el numero de la opcion seleccionada.
     */
    public static int muestraMenuCuatroOpciones(String opcion1, String opcion2, String opcion3){
        SalidaPorDefecto.terminal("Bienvenido, que desea hacer?: ");
        SalidaPorDefecto.terminal("\n1."+opcion1);
        SalidaPorDefecto.terminal("\n2."+opcion2);
        SalidaPorDefecto.terminal("\n3."+opcion3);
        SalidaPorDefecto.terminal("\n0. Salir");
        SalidaPorDefecto.terminal("\nElija un numero: ");
        int respuesta = EntradaPorDefecto.consolaDouble().intValue();
        return respuesta;
    }
}
