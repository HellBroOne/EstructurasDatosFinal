package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.Monticulo;
import utilerias.comunes.TipoOrden;

public class PruebaAM {
    public static void main(String[] args) {
        Monticulo arbolM = new Monticulo(TipoOrden.INC);
        arbolM.agregar(18);
        arbolM.agregar(8);
        arbolM.agregar(10);
        SalidaPorDefecto.terminal("\nUltimo nodo: "+arbolM.obtenerUltimoInfo()+"");
        arbolM.agregar(9);
        arbolM.agregar(20);
        SalidaPorDefecto.terminal("\nUltimo nodo: "+arbolM.obtenerUltimoInfo()+"");
        arbolM.agregar(12);
        SalidaPorDefecto.terminal("\nUltimo nodo: "+arbolM.obtenerUltimoInfo()+"");
        SalidaPorDefecto.terminal("\nPor amplitud: ");
        arbolM.amplitud();
        SalidaPorDefecto.terminal("\nInorden: ");
        arbolM.innorden();
        SalidaPorDefecto.terminal("\n\nPRUEBA DE ELIMINACION: "+arbolM.eliminar()+"");
        SalidaPorDefecto.terminal("\nAmplitud: ");
        arbolM.amplitud();
        SalidaPorDefecto.terminal("\nInorden: ");
        arbolM.innorden();
        SalidaPorDefecto.terminal("\n\nPRUEBA DE ELIMINACION 2: "+arbolM.eliminar()+"");
        SalidaPorDefecto.terminal("\nAmplitud: ");
        arbolM.amplitud();
        SalidaPorDefecto.terminal("\nInorden: ");
        arbolM.innorden();
    }
}
