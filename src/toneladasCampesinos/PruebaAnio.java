package toneladasCampesinos;

import entradasalida.SalidaPorDefecto;

public class PruebaAnio {
    public static void main(String[] args) {
        SalidaPorDefecto.terminal("Salida");
        Anio anio = new Anio(1999);

        //llenar el anio
        for (int tonelada = 1; tonelada <=12; tonelada++){
            anio.agregarTonelada(tonelada);
        }
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(anio.obtenerEstacionMayorGanancia());
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+anio.obtenerPromedioToneladasAnuales());
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+anio.obtenerMayorAnual());
        SalidaPorDefecto.terminal("\n");
        anio.mesesMayoresAPromedio();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+anio.menorToneladaAnio());
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+anio.obtenerToneladasUltimoMes());
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+anio.obtenerTotalSegundoTrimestre());
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+anio.obtenerSumaTotal());
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal(""+anio.obtenerTotalSegundoTrimestre());
    }
}
