package pruebas;

import RedesBayesianas.RedBayesiana;
import entradasalida.SalidaPorDefecto;

public class PruebaRedBayesiana {
    public static void main(String[] args) {
        RedBayesiana red = new RedBayesiana(7);
        red.agregarVariable("Nublado");
        red.agregarVariable("Llueve");
        red.agregarVariable("Se va la Luz");
        red.agregarVariable("Me enfermo");
        red.agregarVariable("No hago la tarea");
        red.agregarVariable("No voy a la escuela");
        red.agregarVariable("Repruebo");
        red.imprimir();

        red.agregarArista("Nublado", "llueve");
        red.agregarArista("llueve", "se va la luz");
        red.agregarArista("llueve", "me enfermo");
        red.agregarArista("se va la luz", "No hago la tarea");
        red.agregarArista("Me enfermo", "no voy a la escuela");
        red.agregarArista("No hago la tarea", "repruebo");
        red.agregarArista("No voy a la escuela", "repruebo");
        red.imprimir();
        SalidaPorDefecto.terminal("\n\n");
        SalidaPorDefecto.terminal("Vamos a ponerle probabilidades a Nublado:\n");
        //red.agregarProbabilidades("Nublado");
        SalidaPorDefecto.terminal("Vamos a ponerle probabilidades a Llueve:\n");
        red.agregarProbabilidades("Repruebo");
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("Obtenido: "+red.calcularProbabilidad("Repruebo"));
    }
}
