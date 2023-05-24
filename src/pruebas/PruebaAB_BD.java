package pruebas;

import BaseDeDatos.BusquedaTablas.BusquedaTablasBD;
import entradasalida.SalidaPorDefecto;

import java.io.IOException;

public class PruebaAB_BD {
    public static void main(String[] args){
        BusquedaTablasBD base = new BusquedaTablasBD();
        base.guardar();
        SalidaPorDefecto.terminal("\n");
        base.obtenerRegistroPorID(240);
        SalidaPorDefecto.terminal("\n");
        base.agregarRegistro(240, ",Dan,James,oracle.sql.STRUCT@2a7004e5,[+1 332 123 4167],us,AMERICA,3700,Dan.Jones@DANE.COM,321,oracle.sql.STRUCT@21846fb7,21/09/1980,single,F,G: 130000 - 149999");
        SalidaPorDefecto.terminal("\n");
        base.agregarRegistro(990, "Dan\tJames\toracle.sql.STRUCT@2a7004e5\t[+1 332 123 4167]\tus\tAMERICA\t3700\tDan.Jones@DANE.COM\t321\toracle.sql.STRUCT@21846fb7\t21/09/1980\tsingle\tF\tG: 130,000 - 149,999\t\n");
        SalidaPorDefecto.terminal("\n\nValores repetidos: ");
        base.obtenerPorValor("ORDER_MODE", "direct");
        SalidaPorDefecto.terminal("\n");
    }
}
