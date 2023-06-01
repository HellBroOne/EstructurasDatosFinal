package ModelosOcultosMarkov.prueba;

import ModelosOcultosMarkov.codigo.ModeloOcultoMarkov;
import ModelosOcultosMarkov.visual.InterfazUsuarioMOM;
import entradasalida.SalidaPorDefecto;

public class PruebaMOM {
    public static void main(String[] args) {
        ModeloOcultoMarkov mom = new ModeloOcultoMarkov();
        mom.imprimirModelo();
        SalidaPorDefecto.terminal("\n##### PROBABILIDADES DE INICIOS #####\n");
        SalidaPorDefecto.terminal("Probabilidad que inicie Lluvia: "+(mom.probabilidadInicio("Lluvia")*100)+"%");
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("Probabilidad que inicie lluvia: "+mom.probabilidadInicio("lluvia"));
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("Probabilidad que inicie lodoso: "+mom.probabilidadInicio("lodoso"));
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("Probabilidad que inicie humedo: "+mom.probabilidadInicio("humedo"));
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("\n##### PROBABILIDADES DE ACTIVIDADES #####\n");
        SalidaPorDefecto.terminal("Probabilidad que se camine si es humedo: "+mom.probabilidadDeHacerActividad("humedo", "Caminar")+"\n");
        SalidaPorDefecto.terminal("Probabilidad que se duerma si es tormenta: "+mom.probabilidadDeHacerActividad("Tormenta", "Dormir")+"\n");
        SalidaPorDefecto.terminal("Probabilidad que se baile si es Seco: "+mom.probabilidadDeHacerActividad("Seco", "Bailar")+"\n");
        SalidaPorDefecto.terminal("\n##### PROBABILIDADES DE TRANSICION #####\n");
        SalidaPorDefecto.terminal("Probabilidad que el dia humedo cambie a lluvia: "+mom.obtenerEstadoTransicion("humedo", "Lluvia")+"\n");
        SalidaPorDefecto.terminal("Probabilidad que el dia Tormenta cambie a tormenta: "+mom.obtenerEstadoTransicion("tormenta", "Tormenta")+"\n");
        SalidaPorDefecto.terminal("Probabilidad que el dia Seco cambie a Tormenta: "+mom.obtenerEstadoTransicion("Seco", "Tormenta")+"\n");
        SalidaPorDefecto.terminal("Probabilidad que el dia soleado cambie a huracan 3000 con probabilidades de caos y destruccion del mundo: "+mom.obtenerEstadoTransicion("Soleado", "Huracan 3000 con probabilidades de caos y destruccion del mundo")+"\n");
        SalidaPorDefecto.terminal("\n##### PROBABILIDADES DE SECUENCIA #####\n");
        SalidaPorDefecto.terminal("Probabilidad de la secuencia LLuvia, Tormenta, Tormenta, Seco, Humedo: "+mom.probabilidadSecuenciaEstados("LLuvia, Tormenta, Tormenta Seco Humedo")+"\n");
        SalidaPorDefecto.terminal("Probabilidad de la secuencia Humedo, Humedo: "+mom.probabilidadSecuenciaEstados("Humedo, Humedo")+"\n");
        SalidaPorDefecto.terminal("Probabilidad de la secuencia Tortilla, Nose, Lluvia: "+mom.probabilidadSecuenciaEstados("Tortilla, Nose, Lluvia")+"\n");
        SalidaPorDefecto.terminal("Probabilidad de la secuencia {vacia}: "+mom.probabilidadSecuenciaEstados(", ,")+"\n");
        SalidaPorDefecto.terminal("Probabilidad de la secuencia {vacia}: "+mom.probabilidadSecuenciaEstados("")+"\n");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazUsuarioMOM().setVisible(true);
            }
        });

    }
}
